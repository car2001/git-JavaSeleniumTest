package Applications.ConfigurationManager;


import Forms.ConfigurationManager.FormsNotificationProfile;
import Helpers.Asserts;
import Helpers.BasicControl;
import Helpers.FormsControl;
import Helpers.SelectBrowser;
import HomePage.Login;
import HomePage.LoginApplications;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class CM_Notification_Profile {
    private WebDriver driver;
    private String chosen_browser = "Chrome";

    SelectBrowser browser = new SelectBrowser(driver);
    Login login;
    Asserts asserts;
    BasicControl basicControl;

    final String componente = "Notification Profile";
    final String newNotification = "Notification Profile Selenium";
    final String editNotification = "Notification Profile Selenium Editado";
    final String versionMayor_NP = "Notification Profile Selenium version Mayor";
    final String versionMenor_NP = "Notification Profile Selenium version Menor";
    final String restoreVersion = "Notification Profile Selenium version Restaurado";

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

    @Parameters("notificationProfile")
    @Test
    public void crearNotification(@Optional(newNotification)String notificationProfile){
        FormsNotificationProfile.formCreateNotification(driver,notificationProfile);
        asserts.assertSave();
    }

    @Parameters("notificationProfile")
    @Test
    public void viewDependecies_Notification(@Optional(newNotification)String notificationProfile){
        driver.findElement(By.xpath("//div[text()='"+notificationProfile+"']")).click();
        basicControl.btnDependecies();
        asserts.assertDependecies();
    }

    @Parameters({"notificationProfile","editNotificationProfile"})
    @Test
    public void editarNotification(@Optional(newNotification)String notificationProfile,@Optional(editNotification) String editNotificationProfile) throws InterruptedException {
        driver.findElement(By.xpath("//div[text()='"+notificationProfile+"']")).click();
        FormsNotificationProfile.formEditNotification(driver,editNotificationProfile);
        asserts.assertSave();
    }

    @Parameters({"editNotificationProfile","versionMayorNP"})
    @Test
    public void versionMayor_Notification(@Optional(editNotification) String editNotificationProfile,@Optional(versionMayor_NP)String versionMayorNP) throws InterruptedException {
        driver.findElement(By.xpath("//div[text()='"+editNotificationProfile+"']")).click();
        FormsNotificationProfile.MayorVersionNotification(driver,versionMayorNP);
        asserts.assertSave();
    }

    @Parameters({"versionMayorNP","versionMenorNP"})
    @Test
    public void versionMenor_Notification(@Optional(versionMayor_NP) String versionMayorNP,@Optional(versionMenor_NP)String versionMenorNP) throws InterruptedException {
        driver.findElement(By.xpath("//div[text()='"+versionMayorNP+"']")).click();
        FormsNotificationProfile.MenorVersionNotification(driver,versionMenorNP);
        asserts.assertSave();
    }

    @Parameters({"versionMenorNP","restoreVersionNP"})
    @Test
    public void restoreVersion_Notification(@Optional(versionMenor_NP) String versionMenorNP,@Optional(restoreVersion)String restoreVersionNP){
        driver.findElement(By.xpath("//div[text()='"+versionMenorNP+"']")).click();
        FormsNotificationProfile.restoreVersion_NP(driver,restoreVersionNP);
        asserts.assertSave();
    }

    @Parameters("delete_NP")
    @Test
    public void eliminarNotification(@Optional(restoreVersion)String delete_NP){
        FormsControl.controlDelete(driver,delete_NP);
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