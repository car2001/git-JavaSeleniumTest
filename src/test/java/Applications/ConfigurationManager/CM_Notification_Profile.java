package Applications.ConfigurationManager;


import Forms.ConfigurationManager.FormsNotificationProfile;
import Helpers.Asserts;
import Helpers.BasicControl;
import Helpers.FormsControl;
import Helpers.SelectBrowser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class CM_Notification_Profile {
    private WebDriver driver;
    private SelectBrowser browser = new SelectBrowser(driver);
    private Asserts asserts;
    private BasicControl basicControl;
    private FormsNotificationProfile formsNotificationProfile;

    private final String componente = "Notification Profiles";


    public CM_Notification_Profile(WebDriver driver){
        this.driver = driver;
        this.browser = new SelectBrowser(driver);
        this.asserts = new Asserts(driver);
        this.basicControl  = new BasicControl(driver);
        this.formsNotificationProfile = new FormsNotificationProfile(driver);

    }


    @Test
    public void crearNotification(String notificationProfile){
        basicControl.btn_More(componente);
        formsNotificationProfile.formCreateNotification(notificationProfile);
        asserts.assertSave();
    }


    @Test
    public void editarNotification(String notificationProfile,String editNotificationProfile) throws InterruptedException {
        basicControl.btn_More(componente);
        String xmlview = basicControl.getXmlview();
        driver.findElement(By.xpath("//div[@id='"+xmlview+"--listObject']//div[text()='"+notificationProfile+"']")).click();
        formsNotificationProfile.formEditNotification(editNotificationProfile);
        asserts.assertSave();
    }

    @Test
    public void versionMayor_NP(String notificationProfile,String vMayorNotificationProfile) throws InterruptedException {
        basicControl.btn_More(componente);
        String xmlview = basicControl.getXmlview();
        driver.findElement(By.xpath("//div[@id='"+xmlview+"--listObject']//div[text()='"+notificationProfile+"']")).click();
        formsNotificationProfile.MayorVersionNotification(vMayorNotificationProfile);
        asserts.assertSave();
    }

    @Test
    public void versionMenor_NP(String notificationProfile,String vMenorNotificationProfile) throws InterruptedException {
        basicControl.btn_More(componente);
        String xmlview = basicControl.getXmlview();
        driver.findElement(By.xpath("//div[@id='"+xmlview+"--listObject']//div[text()='"+notificationProfile+"']")).click();
        formsNotificationProfile.MenorVersionNotification(vMenorNotificationProfile);
        asserts.assertSave();
    }

    @Test
    public void restoreVersion_NP(String notificationProfile,String vRestoreNotificationProfile) throws InterruptedException {
        basicControl.btn_More(componente);
        String xmlview = basicControl.getXmlview();
        driver.findElement(By.xpath("//div[@id='" + xmlview + "--listObject']//div[text()='" + notificationProfile + "']")).click();
        String versionActual = driver.findElement(By.xpath("//input[contains(@id,'--txtVersion-inner')]")).getAttribute("value");
        formsNotificationProfile.restoreVersion_NP(vRestoreNotificationProfile,versionActual);
        asserts.assertSave();
    }


    @Test
    public void eliminarNotification(String delete_NP){
        basicControl.btn_More(componente);
        FormsControl.controlDelete(driver,delete_NP);
        String xpathMessage = "//span[@class='sapMText sapUiSelectable sapMTextMaxWidth sapMMsgBoxText']";
        asserts.assertDelete(xpathMessage);
    }

}