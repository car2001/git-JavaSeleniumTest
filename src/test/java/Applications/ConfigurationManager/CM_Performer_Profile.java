package Applications.ConfigurationManager;

import Forms.FormsCM;
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

public class CM_Performer_Profile {

    private WebDriver driver;
    private String chosen_browser = "Chrome";

    SelectBrowser browser = new SelectBrowser(driver);
    Login login;
    Asserts asserts;
    BasicControl basicControl;

    String componente = "Performer Profile";
    String newPerformer = "Performer Selenium";
    String editPerfomer = "Performer Edit Selenium";
    String versionMayor_PP = "Performer Selenium Version Mayor";
    String versionMenor_PP = "Performer Selenium Version Menor";
    String restoreVersion_PP = "Performer Selenium Restaurado";

    @BeforeMethod
    public void SetUp(){
        browser.chooseBrowser(chosen_browser);
        driver = browser.getDriver();
        asserts = new Asserts(driver);
        basicControl = new BasicControl(driver);
        login = new Login(driver);
        login.loginPage();
        LoginApplications.loginCM(driver,componente);
    }

    @Test
    public void crearPerformerProfile(){
        FormsCM.formCreatePerformer(driver,newPerformer);
        asserts.assertSave();
    }

    @Test(priority = 1)
    public void viewDependecies_PP(){
        driver.findElement(By.xpath("//div[text()='"+newPerformer+"']")).click();
        basicControl.btnDependecies();
        asserts.assertDependecies();
    }

    @Test(priority = 2)
    public void editarPerformerProfile(){
        driver.findElement(By.xpath("//div[text()='"+newPerformer+"']")).click();
        FormsCM.formEditPerformer(driver,editPerfomer);
        asserts.assertSave();
    }

    @Test(priority = 3)
    public void versionMayor_PP(){
        driver.findElement(By.xpath("//div[text()='"+editPerfomer+"']")).click();
        FormsCM.MayorVersionPerformer(driver,versionMayor_PP);
        asserts.assertSave();
    }

    @Test(priority = 4)
    public void versionMenor_PP(){
        driver.findElement(By.xpath("//div[text()='"+versionMayor_PP+"']")).click();
        FormsCM.MenorVersionPerformer(driver,versionMenor_PP);
        asserts.assertSave();
    }

    @Test(priority = 5)
    public void restoreVersion_PP(){
        driver.findElement(By.xpath("//div[text()='"+versionMenor_PP+"']")).click();
        FormsCM.restoreVersion_PP(driver,restoreVersion_PP);
        asserts.assertSave();
    }

    @Test(priority = 6)
    public void eliminarPerformerProfile(){
        FormsControl.controlDelete(driver,restoreVersion_PP);
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