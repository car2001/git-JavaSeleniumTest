package Applications.ConfigurationManager;


import Forms.ConfigurationManager.FormsRiskProfile;
import Helpers.Asserts;
import Helpers.BasicControl;
import Helpers.FormsControl;
import Helpers.SelectBrowser;
import HomePage.Login;
import HomePage.LoginApplications;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class CM_Risk_Profile {
    private WebDriver driver;
    private String chosen_browser = "Chrome";

    SelectBrowser browser = new SelectBrowser(driver);
    Login login;
    Asserts asserts;
    BasicControl basicControl;

    final String componente = "Risk Profiles";
    final String newRiskProfile = "Risk Profile Selenium2";
    final String editRiskProfile = "Risk Profile Selenium Editado";
    final String versionMayor_RP = "Risk Profile Selenium version Mayor";
    final String versionMenor_RP = "Risk Profile Selenium version Menor";
    final String restoreVersion = "Risk Profile Selenium version Restaurada";

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

    @Parameters("riskProfile")
    @Test
    public void crearRiskProfile(@Optional(newRiskProfile) String riskProfile){
        FormsRiskProfile.formCreateRisk(driver,riskProfile);
        asserts.assertSave();
    }

    @Parameters("riskProfile")
    @Test
    public void viewDependecies_RP(@Optional(newRiskProfile) String riskProfile){
        driver.findElement(By.xpath("//div[text()='"+riskProfile+"']")).click();
        basicControl.btnDependecies();
        asserts.assertDependecies();
    }

    @Parameters({"riskProfile","edit_RiskProfile"})
    @Test
    public void editRiskProfile(@Optional(newRiskProfile) String riskProfile, @Optional(editRiskProfile) String edit_RiskProfile) throws InterruptedException {
        driver.findElement(By.xpath("//div[text()='"+riskProfile+"']")).click();
        FormsRiskProfile.formEditRisk(driver,edit_RiskProfile);
        asserts.assertSave();
    }

    @Parameters({"edit_RiskProfile","versionMayorRP"})
    @Test
    public void versionMayor_RP(@Optional(editRiskProfile) String edit_RiskProfile,@Optional(versionMayor_RP) String versionMayorRP) throws InterruptedException {
        driver.findElement(By.xpath("//div[text()='"+edit_RiskProfile+"']")).click();
        FormsRiskProfile.MayorVersionRisk(driver,versionMayorRP);
        asserts.assertSave();
    }

    @Parameters({"versionMayorRP","versionMenorRP"})
    @Test
    public void versionMenor_RP(@Optional(versionMayor_RP) String versionMayorRP,@Optional(versionMenor_RP)String versionMenorRP) throws InterruptedException {
        driver.findElement(By.xpath("//div[text()='"+versionMayorRP+"']")).click();
        FormsRiskProfile.MenorVersionRisk(driver,versionMenorRP);
        asserts.assertSave();
    }

    @Parameters({"versionMenorRP","restoreVersionRP"})
    @Test
    public void restoreVersion_RP(@Optional(versionMenor_RP) String versionMenorRP,@Optional(restoreVersion)String restoreVersionRP){
        driver.findElement(By.xpath("//div[text()='"+versionMenorRP+"']")).click();
        FormsRiskProfile.restoreVersionRisk(driver,restoreVersionRP);
        asserts.assertSave();
    }

    @Parameters("delete_RP")
    @Test
    public void eliminar_RP(@Optional(restoreVersion) String delete_RP){
        FormsControl.controlDelete(driver,delete_RP);
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