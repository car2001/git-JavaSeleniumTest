package Applications.ConfigurationManager;

import Forms.ConfigurationManager.FormsCM;
import Forms.ConfigurationManager.FormsSLA;
import Helpers.Asserts;
import Helpers.BasicControl;
import Helpers.FormsControl;
import Helpers.SelectBrowser;
import HomePage.Login;
import HomePage.LoginApplications;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class CM_SLA {
    private WebDriver driver;
    private String chosen_browser = "Chrome";
    final String URL = "http://wedox.sytes.net/buplat_dev/";

    final String componente = "SLA Definition";
    final String newSLA = "SLA Selenium";
    final String editSLA = "SLA Edit Selenium";
    final String versionMayor_Sla = "Sla Version Mayor Seleneium";
    final String versionMenor_Sla = "Sla Version Menor Seleneium";
    final String restoreVersion = "SLA Restaurado Selenium";

    SelectBrowser browser = new SelectBrowser(driver);
    Login login;
    Asserts asserts;
    BasicControl basicControl;

    @Parameters("url")
    @BeforeMethod
    public void setUp(@Optional(URL) String url){
        browser.chooseBrowser(chosen_browser);
        driver = browser.getDriver();
        asserts = new Asserts(driver);
        basicControl = new BasicControl(driver);
        login = new Login(driver);
        login.loginPage(URL);
        LoginApplications.loginCM(driver,componente);
    }

    @Parameters("SLA")
    @Test()
    public void crear_SLA(@Optional(newSLA) String SLA) {
        FormsSLA.formCreateSLA(driver,SLA);
        asserts.assertSave();
    }

    @Parameters("SLA")
    @Test(priority = 1)
    public void viewDependecies_SLA(@Optional(newSLA) String SLA){
        driver.findElement(By.xpath("//div[text()='"+SLA+"']")).click();
        basicControl.btnDependecies();
        asserts.assertDependecies();
    }

    @Parameters({"SLA","SLA_edit"})
    @Test(priority = 2)
    public void editar_SLA(@Optional(newSLA) String SLA, @Optional(editSLA) String SLA_edit) throws InterruptedException{
        driver.findElement(By.xpath("//div[text()='"+SLA+"']")).click();
        FormsSLA.formEditSLA(driver,SLA_edit);
        asserts.assertSave();
    }

    @Parameters({"SLA_edit","versionMayorSLA"})
    @Test(priority = 3)
    public void versionMayor_SLA(@Optional(editSLA) String SLA_edit, @Optional(versionMayor_Sla) String versionMayorSLA) throws InterruptedException {
        driver.findElement(By.xpath("//div[text()='"+SLA_edit+"']")).click();
        FormsCM.MayorVersionSLA(driver,versionMayorSLA);
        asserts.assertSave();
    }

    @Parameters({"versionMayorSLA","versionMenorSLA"})
    @Test(priority = 4)
    public void versionMenor_SLA(@Optional(versionMayor_Sla) String versionMayorSLA,@Optional(versionMenor_Sla) String versionMenorSLA){
        driver.findElement(By.xpath("//div[text()='"+versionMayorSLA+"']")).click();
        FormsCM.MenorVersionSLA(driver,versionMenorSLA);
        asserts.assertSave();
    }

    @Parameters({"versionMenorSLA","restore_Version"})
    @Test(priority = 5)
    public void restoreVersion_SLA(@Optional(versionMenor_Sla) String versionMenorSLA,@Optional(restoreVersion) String restore_Version){
        driver.findElement(By.xpath("//div[text()='"+versionMenorSLA+"']")).click();
        FormsCM.restoreVersion_SLA(driver,restore_Version);
        asserts.assertSave();
    }

    @Parameters("restore_Version")
    @Test(priority = 6)
    public void eliminar_SLA(@Optional(restoreVersion) String restore_Version){
        FormsControl.controlDelete(driver,restore_Version);
        String xpathMessage = "//span[@class='sapMText sapUiSelectable sapMTextMaxWidth sapMMsgBoxText']";
        asserts.assertDelete(xpathMessage);
    }

    @AfterMethod
    public void tearDown(){
        if (driver != null){
            //driver.quit();
        }
    }
}