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

public class CM_SLA {
    String componente = "SLA Definition";
    String newSLA = "SLA Selenium";
    String editSLA = "SLA Edit Selenium";
    String versionMayor_Sla = "Sla Version Mayor Seleneium";
    String versionMenor_Sla = "Sla Version Menor Seleneium";
    String restoreVersion = "SLA Restaurado Selenium";

    private WebDriver driver;
    private String chosen_browser = "Chrome";

    SelectBrowser browser = new SelectBrowser(driver);
    Home_Page login;
    Asserts asserts;

    @BeforeMethod
    public void setUp(){
        browser.chooseBrowser(chosen_browser);
        driver = browser.getDriver();
        asserts = new Asserts(driver);
        login = new Home_Page(driver);
        login.loginPage("cpingo","1234");
        Login_Applications.loginCM(driver,componente);
    }

    @Test()
    public void crear_SLA() {
        FormsCM.formCreateSLA(driver,newSLA);
        asserts.assertSave();
    }

    @Test(priority = 1)
    public void viewDependecies_SLA(){
        driver.findElement(By.xpath("//div[text()='"+newSLA+"']")).click();
        driver.findElement(By.id("__xmlview5--viewDependencies-img")).click();
        asserts.assertDependecies(5);
    }

    @Test(priority = 2)
    public void editar_SLA(){
        driver.findElement(By.xpath("//div[text()='"+newSLA+"']")).click();
        FormsCM.formEditSLA(driver,editSLA);
        asserts.assertSave();
    }

    @Test(priority = 3)
    public void versionMayor_SLA(){
        driver.findElement(By.xpath("//div[text()='"+editSLA+"']")).click();
        FormsCM.MayorVersionSLA(driver,versionMayor_Sla);
        asserts.assertSave();
    }

    @Test(priority = 4)
    public void versionMenor_SLA(){
        driver.findElement(By.xpath("//div[text()='"+versionMayor_Sla+"']")).click();
        FormsCM.MenorVersionSLA(driver,versionMenor_Sla);
        asserts.assertSave();
    }

    @Test(priority = 5)
    public void restoreVersion_SLA(){
        driver.findElement(By.xpath("//div[text()='"+versionMenor_Sla+"']")).click();
        FormsCM.restoreVersion_SLA(driver,restoreVersion);
        asserts.assertSave();
    }

    @Test(priority = 6)
    public void eliminar_SLA(){
        driver.findElement(By.xpath("//div[text()='"+restoreVersion+"']/parent::div/parent::div/following-sibling::button")).click();
        driver.findElement(By.xpath("//bdi[normalize-space()='Si']")).click();
        String xpathMessage = "//span[@class='sapMText sapUiSelectable sapMTextMaxWidth sapMMsgBoxText']";
        driver.findElement(By.xpath("//bdi[normalize-space()='OK']")).click();
        asserts.assertDelete(xpathMessage);
    }

    @AfterMethod
    public void tearDown(){
        if (driver != null){
            driver.quit();
        }
    }
}