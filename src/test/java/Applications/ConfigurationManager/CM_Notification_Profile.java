package Applications.ConfigurationManager;

import Forms.FormsCM;
import Helpers.Asserts;
import Helpers.SelectBrowser;
import HomepageFunctions.Home_Page;
import HomepageFunctions.Login_Applications;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CM_Notification_Profile {
    private WebDriver driver;
    private String chosen_browser = "Chrome";

    SelectBrowser browser = new SelectBrowser(driver);
    Home_Page login;
    Asserts asserts;

    String componente = "Notification Profile";
    String newNotification = "Notification Profile Selenium";
    String editNotification = "Notification Profile Selenium Editado";
    String versionMayor_NP = "Notification Profile Selenium version Mayor";
    String versionMenor_NP = "Notification Profile Selenium version Menor";
    String restoreVersion = "Notification Profile Selenium version Restaurado";

    @BeforeMethod
    public void setUp(){
        browser.chooseBrowser(chosen_browser);
        driver = browser.getDriver();
        asserts = new Asserts(driver);
        login = new Home_Page(driver);
        login.loginPage("cpingo","1234");
        Login_Applications.loginCM(driver,componente);
    }

    @Test
    public void crearNotification(){
        FormsCM.formCreateNotification(driver,newNotification);
        asserts.assertSave();
    }

    @Test(priority = 1)
    public void viewDependecies_Notification(){
        driver.findElement(By.xpath("//div[text()='"+newNotification+"']")).click();
        driver.findElement(By.id("__xmlview5--viewDependencies-img")).click();
        asserts.assertDependecies(5);
    }

    @Test(priority = 2)
    public void editarNotification(){
        driver.findElement(By.xpath("//div[text()='"+newNotification+"']")).click();
        FormsCM.formEditNotification(driver,editNotification);
        asserts.assertSave();
    }

    @Test(priority = 3)
    public void versionMayor_SLA(){
        driver.findElement(By.xpath("//div[text()='"+editNotification+"']")).click();
        FormsCM.MayorVersionNotification(driver,versionMayor_NP);
        asserts.assertSave();
    }

    @Test(priority = 4)
    public void versionMenor_SLA(){
        driver.findElement(By.xpath("//div[text()='"+versionMayor_NP+"']")).click();
        FormsCM.MenorVersionNotification(driver,versionMenor_NP);
        asserts.assertSave();
    }

    @Test(priority = 5)
    public void restoreVersion_SLA(){
        driver.findElement(By.xpath("//div[text()='"+versionMenor_NP+"']")).click();
        FormsCM.restoreVersion_NP(driver,restoreVersion);
        asserts.assertSave();
    }

    @Test(priority = 6)
    public void eliminarNotification(){
        driver.findElement(By.xpath("//div[text()='"+restoreVersion+"']/parent::div/parent::div/following-sibling::button")).click();
        driver.findElement(By.xpath("//bdi[normalize-space()='Si']")).click();
        String xpathMessage = "//span[@class='sapMText sapUiSelectable sapMTextMaxWidth sapMMsgBoxText']";
        driver.findElement(By.xpath("//bdi[normalize-space()='OK']")).click();
        asserts.assertDelete(xpathMessage);
    }

    @AfterMethod
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }
}