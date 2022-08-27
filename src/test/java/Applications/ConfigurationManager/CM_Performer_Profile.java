package Applications.ConfigurationManager;


import Forms.ConfigurationManager.FormsPerformerProfile;
import Helpers.Asserts;
import Helpers.BasicControl;
import Helpers.FormsControl;
import Helpers.SelectBrowser;
import HomePage.Login;
import HomePage.LoginApplications;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class CM_Performer_Profile {

    private WebDriver driver;
    private String chosen_browser = "Chrome";

    SelectBrowser browser = new SelectBrowser(driver);
    Login login;
    Asserts asserts;
    BasicControl basicControl;

    final String componente = "Performer Profile";
    final String newPerformer = "Performer Selenium2";
    final String editPerformer = "Performer Edit Selenium";
    final String versionMayor_PP = "Performer Selenium Version Mayor";
    final String versionMenor_PP = "Performer Selenium Version Menor";
    final String restoreVersion_PP = "Performer Selenium Restaurado";

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

    @Parameters("PP")
    @Test
    public void crearPerformerProfile(@Optional(newPerformer) String PP){
        FormsPerformerProfile.formCreatePerformer(driver,PP);
        asserts.assertSave();
    }

    @Parameters("PP")
    @Test
    public void viewDependecies_PP(@Optional(newPerformer) String PP){
        driver.findElement(By.xpath("//div[text()='"+PP+"']")).click();
        basicControl.btnDependecies();
        asserts.assertDependecies();
    }

    @Parameters({"PP","PP_edit"})
    @Test
    public void editarPerformerProfile(@Optional(newPerformer) String PP, @Optional(editPerformer) String PP_edit ) throws InterruptedException {
        driver.findElement(By.xpath("//div[text()='"+PP+"']")).click();
        FormsPerformerProfile.formEditPerformer(driver,PP_edit);
        asserts.assertSave();
    }

    @Parameters({"PP_edit","versionMayorPP"})
    @Test
    public void versionMayor_PP(@Optional(editPerformer) String PP_edit, @Optional(versionMayor_PP) String versionMayorPP) throws InterruptedException {
        driver.findElement(By.xpath("//div[text()='"+PP_edit+"']")).click();
        FormsPerformerProfile.MayorVersionPerformer(driver,versionMayorPP);
        asserts.assertSave();
    }

    @Parameters({"versionMayorPP","versionMenorPP"})
    @Test
    public void versionMenor_PP(@Optional(versionMayor_PP) String versionMayorPP, @Optional(versionMenor_PP) String versionMenorPP) throws InterruptedException {
        driver.findElement(By.xpath("//div[text()='"+versionMayorPP+"']")).click();
        FormsPerformerProfile.MenorVersionPerformer(driver,versionMenorPP);
        asserts.assertSave();
    }

    @Parameters({"versionMenorPP","restore_Version"})
    @Test
    public void restoreVersion_PP(@Optional(versionMenor_PP) String versionMenorPP,@Optional(restoreVersion_PP) String restore_Version){
        driver.findElement(By.xpath("//div[text()='"+versionMenorPP+"']")).click();
        FormsPerformerProfile.restoreVersion_PP(driver,restore_Version);
        asserts.assertSave();
    }

    @Parameters("delete_PP")
    @Test
    public void eliminarPerformerProfile(@Optional(restoreVersion_PP) String delete_PP){
        FormsControl.controlDelete(driver,delete_PP);
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