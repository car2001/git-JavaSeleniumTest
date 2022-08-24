package Applications.ConfigurationManager;

import Forms.ConfigurationManager.FormsCM;
import Forms.ConfigurationManager.FormsNotificationProfile;
import Helpers.Asserts;
import Helpers.BasicControl;
import Helpers.FormsControl;
import Helpers.SelectBrowser;
import HomePage.Login;
import HomePage.LoginApplications;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CM_Notification_Profile {
    private WebDriver driver;
    private String chosen_browser = "Chrome";

    SelectBrowser browser = new SelectBrowser(driver);
    Login login;
    Asserts asserts;
    BasicControl basicControl;

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
        basicControl = new BasicControl(driver);
        login = new Login(driver);
        login.loginPage();
        LoginApplications.loginCM(driver,componente);
    }

    @Test
    public void crearNotification(){
        FormsNotificationProfile.formCreateNotification(driver,newNotification);
        asserts.assertSave();
    }

    @Test(priority = 1)
    public void viewDependecies_Notification(){
        driver.findElement(By.xpath("//div[text()='"+newNotification+"']")).click();
        basicControl.btnDependecies();
        asserts.assertDependecies();
    }

    @Test(priority = 2)
    public void editarNotification() throws InterruptedException {
        driver.findElement(By.xpath("//div[text()='"+newNotification+"']")).click();
        FormsNotificationProfile.formEditNotification(driver,editNotification);
        asserts.assertSave();
    }

    @Test(priority = 3)
    public void versionMayor_Notification(){
        driver.findElement(By.xpath("//div[text()='"+editNotification+"']")).click();
        FormsCM.MayorVersionNotification(driver,versionMayor_NP);
        asserts.assertSave();
    }

    @Test(priority = 4)
    public void versionMenor_Notification(){
        driver.findElement(By.xpath("//div[text()='"+versionMayor_NP+"']")).click();
        FormsCM.MenorVersionNotification(driver,versionMenor_NP);
        asserts.assertSave();
    }

    @Test(priority = 5)
    public void restoreVersion_Notification(){
        driver.findElement(By.xpath("//div[text()='"+versionMenor_NP+"']")).click();
        FormsCM.restoreVersion_NP(driver,restoreVersion);
        asserts.assertSave();
    }

    @Test(priority = 6)
    public void eliminarNotification(){
        FormsControl.controlDelete(driver,restoreVersion);
        String xpathMessage = "//span[@class='sapMText sapUiSelectable sapMTextMaxWidth sapMMsgBoxText']";
        asserts.assertDelete(xpathMessage);
    }

    @AfterMethod
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }
}