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
    private static List<WebElement> listForm;
    private static BasicControl basicControl;
    private static String addItem = "//span[contains(@id,'--addItem-img')]";
    private static String typeEvent = "//span[contains(@id,'-arrow') and @class='sapMSltArrow']";

    public static void formCreateNotification(WebDriver driver, String notification){
        basicControl = new BasicControl(driver);
        basicControl.btnAdd();
        listForm = FormsControl.controlNew(driver,"perfil de notificación","Notification Profile");
        listForm.get(2).click();
        listForm.get(2).sendKeys(notification);
        listForm.get(3).click();
        listForm.get(3).sendKeys(notification);
        listForm.get(4).click();
        listForm.get(4).sendKeys("Descripción " + notification);
        driver.findElement(By.xpath(addItem)).click();
        driver.findElement(By.xpath(typeEvent)).click();
        driver.findElement(By.xpath("//li[text()='Allocate' and @class ='sapMSelectListItemBase sapMSelectListItem sapMSelectListItemBaseHoverable sapMSelectListItemBaseSelected']")).click();
        basicControl.btnSave();
    }

    public static void formEditNotification(WebDriver driver,String notification) throws InterruptedException {
        basicControl = new BasicControl(driver);
        listForm = FormsControl.controlEdit(driver,"perfil de notificación","Notification Profile");
        listForm.get(2).click();
        listForm.get(2).clear();
        listForm.get(2).sendKeys(notification);
        listForm.get(3).click();
        listForm.get(3).clear();
        listForm.get(3).sendKeys(notification);
        listForm.get(4).click();
        listForm.get(4).clear();
        listForm.get(4).sendKeys("Descripción " + notification);
        basicControl.btnSave();
    }

}
