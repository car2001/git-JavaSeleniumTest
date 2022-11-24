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


/*    public static void MayorVersionNotification(WebDriver driver, String mayor){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.id(version)));
        driver.findElement(By.id(version)).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//bdi[text()='Major Version']")));
        driver.findElement(By.xpath("//bdi[text()='Major Version']")).click();
        driver.findElement(By.xpath("//bdi[text()='Create Version']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id(save)));
        listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(2).clear();
        listForm.get(2).sendKeys(mayor);
        listForm.get(3).clear();
        listForm.get(3).sendKeys(mayor);
        listForm.get(4).clear();
        listForm.get(4).sendKeys("Descripción " + mayor);
        listForm.get(6).sendKeys("Version Mayor");
        driver.findElement(By.className("sapMSltArrow")).click();
        driver.findElement(By.xpath("//li[text()='Abort' and @class ='sapMSelectListItemBase sapMSelectListItem sapMSelectListItemBaseHoverable']")).click();
        driver.findElement(By.id(save)).click();
    }*/

/*    public static void MenorVersionNotification(WebDriver driver, String menor){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.id(version)));
        driver.findElement(By.id(version)).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//bdi[text()='Minor Version']")));
        driver.findElement(By.xpath("//bdi[text()='Minor Version']")).click();
        driver.findElement(By.xpath("//bdi[text()='Create Version']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id(save)));
        listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(2).clear();
        listForm.get(2).sendKeys(menor);
        listForm.get(3).clear();
        listForm.get(3).sendKeys(menor);
        listForm.get(4).clear();
        listForm.get(4).sendKeys("Descripción " + menor);
        listForm.get(6).sendKeys("Version Menor");
        driver.findElement(By.className("sapMSltArrow")).click();
        driver.findElement(By.xpath("//li[text()='Allocate' and @class ='sapMSelectListItemBase sapMSelectListItem sapMSelectListItemBaseHoverable']")).click();
        driver.findElement(By.id(save)).click();
    }

    public static void restoreVersion_NP(WebDriver driver,String restore){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.id(versionHistory)));
        driver.findElement(By.id(versionHistory)).click();
        String btn_xpath = "//span[@class='sapMBtnInner sapMBtnHoverable sapMFocusable sapMBtnIconFirst sapMBtnDefault']/parent::button[@class='sapMBtnBase sapMBtn']";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(btn_xpath)));
        List<WebElement> btn_restore = driver.findElements(By.xpath(btn_xpath));
        btn_restore.get(1).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//bdi[text()='Minor Version']")));
        driver.findElement(By.xpath("//bdi[text()='Minor Version']")).click();
        driver.findElement(By.xpath("//bdi[text()='Create Version']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id(save)));
        listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(2).clear();
        listForm.get(2).sendKeys(restore);
        listForm.get(3).clear();
        listForm.get(3).sendKeys(restore);
        listForm.get(4).clear();
        listForm.get(4).sendKeys("Descripción " + restore);
        listForm.get(6).sendKeys("Version restore");
        driver.findElement(By.id(save)).click();
    }*/

}