package Forms.ConfigurationManager;

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

    public FormsNotificationProfile(WebDriver driver){
        this.driver = driver;
        this.basicControl = new BasicControl(driver);
        this.addItem = "//span[contains(@id,'--addItem-img')]";
        this.typeEvent = "//span[contains(@id,'-arrow') and @class='sapMSltArrow']";
    }

    public void formCreateNotification(WebDriver driver, String notification){
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

    public void formEditNotification(WebDriver driver,String notification) throws InterruptedException {
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


}