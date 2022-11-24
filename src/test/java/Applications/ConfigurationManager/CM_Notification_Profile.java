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
    private SelectBrowser browser = new SelectBrowser(driver);
    private Asserts asserts;
    private BasicControl basicControl;
    private FormsNotificationProfile formsNotificationProfile;

    final String componente = "Notification Profiles";


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
        formsNotificationProfile.formCreateNotification(driver,notificationProfile);
        asserts.assertSave();
    }


    @Test
    public void editarNotification(String notificationProfile,String editNotificationProfile) throws InterruptedException {
        basicControl.btn_More(componente);
        String xmlview = basicControl.getXmlview();
        driver.findElement(By.xpath("//div[@id='"+xmlview+"--listObject']//div[text()='"+notificationProfile+"']")).click();
        formsNotificationProfile.formEditNotification(driver,editNotificationProfile);
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