package Applications.ConfigurationManager;


import Forms.ConfigurationManager.FormsFormUI;
import Helpers.Asserts;
import Helpers.BasicControl;
import Helpers.FormsControl;
import Helpers.SelectBrowser;
import HomePage.Login;
import HomePage.LoginApplications;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;


public class CM_Form_UI {

    private WebDriver driver;
    private final String chosen_browser = "Chrome";

    SelectBrowser browser = new SelectBrowser(driver);
    Login login;
    Asserts asserts;
    BasicControl basicControl;

    final String URL = "http://wedox.sytes.net/buplat_dev/";
    final String componente = "Form UI Configuration";
    final String newFormUI = "Form UI Selenium";
    final String editFormUI = "Form UI Selenium Editado";
    final String versionMayor_FormUI = "Form UI Selenium versionMayor";
    final String versionMenor_FormUI = "Form UI Selenium versionMenor";
    final String restoreVersion = "Form UI Restaurado Selenium";

    @Parameters("url")
    @BeforeMethod
    public void setup(@Optional(URL) String url) throws InterruptedException {
        browser.chooseBrowser(chosen_browser);
        driver = browser.getDriver();
        asserts = new Asserts(driver);
        basicControl = new BasicControl(driver);
        login = new Login(driver);
        login.loginPage();
        LoginApplications.loginCM(driver,componente);
    }

    @Parameters("formUI")
    @Test
    public void crear_FormUI(@Optional(newFormUI) String formUI){
        FormsFormUI.formCreateFormUI(driver,formUI);
        asserts.assertSave();
    }

    @Parameters("formUI")
    @Test
    public void viewDependecies_FormUI(@Optional(newFormUI) String formUI){
        driver.findElement(By.xpath("//div[text()='"+formUI+"']")).click();
        basicControl.btnDependecies();
        asserts.assertDependecies();
    }

    @Parameters({"formUI","formUI_edit"})
    @Test
    public void editar_FormUI(@Optional(newFormUI) String formUI,@Optional(editFormUI) String formUI_edit) throws InterruptedException {
        driver.findElement(By.xpath("//div[text()='"+formUI+"']")).click();
        FormsFormUI.formEditFormUI(driver,formUI_edit);
        asserts.assertSave();
    }

    @Parameters({"formUI_edit","versionMayorFormUI"})
    @Test
    public void versionMayor_FormUI(@Optional(editFormUI) String formUI_edit,@Optional(versionMayor_FormUI) String versionMayorFormUI) throws InterruptedException {
        driver.findElement(By.xpath("//div[text()='"+formUI_edit+"']")).click();
        FormsFormUI.MayorVersionFormUI(driver,versionMayorFormUI);
        asserts.assertSave();
    }

    @Parameters({"versionMayorFormUI","versionMenorFormUI"})
    @Test
    public void versionMenor_FormUI(@Optional(versionMayor_FormUI) String versionMayorFormUI , @Optional(versionMenor_FormUI) String versionMenorFormUI) throws InterruptedException {
        driver.findElement(By.xpath("//div[text()='"+versionMayorFormUI+"']")).click();
        FormsFormUI.MenorVersionFormUI(driver,versionMenorFormUI);
        asserts.assertSave();
    }

    @Parameters({"versionMenorFormUI","restore_Version"})
    @Test
    public void restoreVersion_FormUI(@Optional(versionMenor_FormUI) String versionMenorFormUI,@Optional(restoreVersion) String restore_Version)  throws InterruptedException{
        driver.findElement(By.xpath("//div[text()='"+versionMenorFormUI+"']")).click();
        FormsFormUI.restoreVersion_FormUI(driver,restore_Version);
        asserts.assertSave();
    }

    @Parameters("delete_FormUI")
    @Test
    public void eliminar_FormUI(@Optional(restoreVersion) String delete_FormUI){
        FormsControl.controlDelete(driver,delete_FormUI);
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