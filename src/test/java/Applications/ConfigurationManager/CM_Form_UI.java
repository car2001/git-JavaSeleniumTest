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


public class CM_Form_UI {

    private WebDriver driver;
    private final String chosen_browser = "Chrome";

    SelectBrowser browser = new SelectBrowser(driver);
    Login login;
    Asserts asserts;
    BasicControl basicControl;

    String componente = "Form UI Configuration";
    String newFormUI = "Form UI Selenium";
    String editFormI = "Form UI Selenium Editado";
    String versionMayor_FormUI = "Form UI Selenium versionMayor";
    String versionMenor_FormUI = "Form UI Selenium versionMenor";
    String restoreVersion = "Form UI Restaurado Selenium";

    @BeforeMethod
    public void setup() throws InterruptedException {
        browser.chooseBrowser(chosen_browser);
        driver = browser.getDriver();
        asserts = new Asserts(driver);
        basicControl = new BasicControl(driver);
        login = new Login(driver);
        login.loginPage();
        LoginApplications.loginCM(driver,componente);
    }

    @Test
    public void crear_FormUI() throws InterruptedException {
        FormsCM.formCreateFormUI(driver,newFormUI);
        asserts.assertSave();
    }

    @Test(priority = 1)
    public void viewDependecies_FormUI(){
        driver.findElement(By.xpath("//div[text()='"+newFormUI+"']")).click();
        basicControl.btnDependecies();
        asserts.assertDependecies();
    }

    @Test(priority = 2)
    public void editar_FormUI(){
        driver.findElement(By.xpath("//div[text()='"+newFormUI+"']")).click();
        FormsCM.formEditFormUI(driver,editFormI);
        asserts.assertSave();
    }

    @Test(priority = 3)
    public void versionMayor_FormUI(){
        driver.findElement(By.xpath("//div[text()='"+editFormI+"']")).click();
        FormsCM.MayorVersionFormUI(driver,versionMayor_FormUI);
        asserts.assertSave();
    }

    @Test(priority = 4)
    public void versionMenor_FormUI(){
        driver.findElement(By.xpath("//div[text()='"+versionMayor_FormUI+"']")).click();
        FormsCM.MenorVersionFormUI(driver,versionMenor_FormUI);
        asserts.assertSave();
    }

    @Test(priority = 5)
    public void restoreVersion_FormUI()  throws InterruptedException{
        driver.findElement(By.xpath("//div[text()='"+versionMenor_FormUI+"']")).click();
        FormsCM.restoreVersion_FormUI(driver,restoreVersion);
        asserts.assertSave();
    }

    @Test(priority = 6)
    public void eliminar_FormUI(){
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