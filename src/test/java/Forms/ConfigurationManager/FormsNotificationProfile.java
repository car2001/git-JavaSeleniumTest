package Forms.ConfigurationManager;

import Helpers.Asserts;
import Helpers.BasicControl;
import Helpers.FormsControl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class FormsNotificationProfile {
    private WebDriver driver;
    private List<WebElement> listForm;
    private BasicControl basicControl;
    private String addItem;
    private String typeEvent;
    private String inputVersion;
    private Asserts asserts;

    public FormsNotificationProfile(WebDriver driver){
        this.driver = driver;
        this.basicControl = new BasicControl(driver);
        this.addItem = "//span[contains(@id,'--addItem-img')]";
        this.typeEvent = "//span[contains(@id,'-arrow') and @class='sapMSltArrow']";
        this.asserts = new Asserts(driver);
        this.inputVersion = "//input[contains(@id,'--txtVersion-inner')]";
    }

    public void formCreateNotification(String notification){
        basicControl.btnAdd();
        listForm = FormsControl.controlNew(driver,"Perfil de Notificación","Notification Profile");
        listForm.get(0).click();
        listForm.get(0).sendKeys(notification);
        listForm.get(1).click();
        listForm.get(1).sendKeys(notification);
        listForm.get(2).click();
        listForm.get(2).sendKeys("Descripción " + notification);
        driver.findElement(By.xpath(addItem)).click();
        driver.findElement(By.xpath(typeEvent)).click();
        driver.findElement(By.xpath("//li[text()='Allocate' and @class ='sapMSelectListItemBase sapMSelectListItem sapMSelectListItemBaseHoverable sapMSelectListItemBaseSelected']")).click();
        basicControl.btnSave();
    }

    public void formEditNotification(String notification) throws InterruptedException {
        listForm = FormsControl.controlEdit(driver,"Perfil de Notificación","Notification Profile");
        listForm.get(0).click();
        listForm.get(0).clear();
        listForm.get(0).sendKeys(notification);
        listForm.get(1).click();
        listForm.get(1).clear();
        listForm.get(1).sendKeys(notification);
        listForm.get(2).click();
        listForm.get(2).clear();
        listForm.get(2).sendKeys("Descripción " + notification);
        basicControl.btnSave();
    }

    public void MayorVersionNotification(String mayor) throws InterruptedException {
        listForm = FormsControl.controlNewVersionMayor(driver, "Perfil de Notificación", "Notification Profile");

        listForm.get(0).click();
        listForm.get(0).clear();
        listForm.get(0).sendKeys(mayor);

        listForm.get(1).click();
        listForm.get(1).clear();
        listForm.get(1).sendKeys(mayor);

        listForm.get(2).click();
        listForm.get(2).clear();
        listForm.get(2).sendKeys("Descripción " + mayor);

        listForm.get(3).click();
        listForm.get(3).sendKeys("Version Mayor");

        String versionActual = driver.findElement(By.xpath(inputVersion)).getAttribute("value");

        driver.findElement(By.xpath(typeEvent)).click();
        driver.findElement(By.xpath("//li[text()='Abort' and @class ='sapMSelectListItemBase sapMSelectListItem sapMSelectListItemBaseHoverable']")).click();
        basicControl.btnSave();

        Thread.sleep(1500);
        String versionMayor = driver.findElement(By.xpath(inputVersion)).getAttribute("value");
        asserts.assertVersionMayor(versionActual,versionMayor);
    }

    public void MenorVersionNotification(String menor) throws InterruptedException {
        listForm = FormsControl.controlNewVersionMenor(driver, "Perfil de Notificación", "Notification Profile");

        listForm.get(0).click();
        listForm.get(0).clear();
        listForm.get(0).sendKeys(menor);

        listForm.get(1).click();
        listForm.get(1).clear();
        listForm.get(1).sendKeys(menor);

        listForm.get(2).click();
        listForm.get(2).clear();
        listForm.get(2).sendKeys("Descripción " + menor);

        listForm.get(3).click();
        listForm.get(3).sendKeys("Version Menor");

        String versionActual = driver.findElement(By.xpath(inputVersion)).getAttribute("value");

        driver.findElement(By.xpath(typeEvent)).click();
        driver.findElement(By.xpath("//li[text()='Allocate' and @class ='sapMSelectListItemBase sapMSelectListItem sapMSelectListItemBaseHoverable']")).click();
        basicControl.btnSave();

        Thread.sleep(1500);
        String versionMenor = driver.findElement(By.xpath(inputVersion)).getAttribute("value");
        asserts.assertVersionMenor(versionActual,versionMenor);

    }

    public void restoreVersion_NP(String restore,String versionActual) throws InterruptedException {
        listForm = FormsControl.controlRestoreVersion(driver,"menor","Perfil de Notificación", "Notification Profile");

        listForm.get(0).click();
        listForm.get(0).clear();
        listForm.get(0).sendKeys(restore);

        listForm.get(1).click();
        listForm.get(1).clear();
        listForm.get(1).sendKeys(restore);

        listForm.get(2).click();
        listForm.get(2).clear();
        listForm.get(2).sendKeys("Descripción " + restore);
        listForm.get(3).sendKeys("Version restore");


        basicControl.btnSave();

        Thread.sleep(1500);
        String versionMenor = driver.findElement(By.xpath(inputVersion)).getAttribute("value");
        asserts.assertVersionMenor(versionActual,versionMenor);
    }

}