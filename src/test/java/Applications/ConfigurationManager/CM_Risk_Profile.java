package Applications.ConfigurationManager;

import Forms.ConfigurationManager.FormsCM;
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

public class CM_Risk_Profile {
    private WebDriver driver;
    private String chosen_browser = "Chrome";

    SelectBrowser browser = new SelectBrowser(driver);
    Login login;
    Asserts asserts;
    BasicControl basicControl;

    String componente = "Risk Profile";
    String newRiskProfile = "Risk Profile Selenium";
    String editRiskProfile = "Risk Profile Selenium Editado";
    String versionMayor_PP = "Risk Profile Selenium version Mayor";
    String versionMenor_PP = "Risk Profile Selenium version Menor";
    String restoreVersion = "Risk Profile Selenium version Restaurada";

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
    public void crearRiskProfile(){
        FormsCM.formCreateRisk(driver,newRiskProfile);
        asserts.assertSave();
    }

    @Test(priority = 1)
    public void viewDependecies_RP(){
        driver.findElement(By.xpath("//div[text()='"+newRiskProfile+"']")).click();
        basicControl.btnDependecies();
        asserts.assertDependecies();
    }

    @Test(priority = 2)
    public void editRiskProfile(){
        driver.findElement(By.xpath("//div[text()='"+newRiskProfile+"']")).click();
        FormsCM.formEditRisk(driver,editRiskProfile);
        asserts.assertSave();
    }

    @Test(priority = 3)
    public void versionMayor_RP(){
        driver.findElement(By.xpath("//div[text()='"+editRiskProfile+"']")).click();
        FormsCM.MayorVersionRisk(driver,versionMayor_PP);
        asserts.assertSave();
    }

    @Test(priority = 4)
    public void versionMenor_RP(){
        driver.findElement(By.xpath("//div[text()='"+versionMayor_PP+"']")).click();
        FormsCM.MenorVersionRisk(driver,versionMenor_PP);
        asserts.assertSave();
    }

    @Test(priority = 5)
    public void restoreVersion_RP(){
        driver.findElement(By.xpath("//div[text()='"+versionMenor_PP+"']")).click();
        FormsCM.restoreVersionRisk(driver,restoreVersion);
        asserts.assertSave();
    }

    @Test(priority = 6)
    public void eliminar_RP(){
        FormsControl.controlDelete(driver,restoreVersion);
        String xpathMessage = "//span[@class='sapMText sapUiSelectable sapMTextMaxWidth sapMMsgBoxText']";
        asserts.assertDelete(xpathMessage);
    }

    @AfterMethod
    public void tearDown(){
        if (driver != null){
            driver.quit();
        }
    }
}