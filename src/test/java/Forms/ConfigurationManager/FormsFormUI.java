package Forms.ConfigurationManager;

import Helpers.BasicControl;
import Helpers.FormsControl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import java.util.List;

public class FormsFormUI {
    private WebDriver driver;
    private List<WebElement> listForm;
    private BasicControl basicControl;
    private String comment;
    private String attachment;
    private String instructions;

    public FormsFormUI(WebDriver driver){
        this.driver = driver;
        this.basicControl = new BasicControl(driver);
        this.comment = "//div[contains(@id,'--comments-switch')]";
        this.attachment = "//div[contains(@id,'--attachments-switch')]";
        this.instructions = "//div[contains(@id,'--instructions-switch')]";
    }

    public void formCreateFormUI(String UI){
        basicControl.btnAdd();
        listForm = FormsControl.controlNew(driver,"UI","UI");
        listForm.get(0).click();
        listForm.get(0).sendKeys(UI);
        listForm.get(1).click();
        listForm.get(1).sendKeys(UI);
        listForm.get(2).click();
        listForm.get(2).sendKeys(UI);
        driver.findElement(By.xpath(comment)).click();
        driver.findElement(By.xpath(attachment)).click();
        driver.findElement(By.xpath(instructions)).click();
        basicControl.btnSave();
    }

    public void formEditFormUI(String UI) throws InterruptedException {
        basicControl = new BasicControl(driver);
        listForm = FormsControl.controlEdit(driver,"UI","UI");
        listForm.get(0).click();
        listForm.get(0).clear();
        listForm.get(0).sendKeys(UI);
        listForm.get(1).click();
        listForm.get(1).clear();
        listForm.get(1).sendKeys(UI);
        listForm.get(2).click();
        listForm.get(2).clear();
        listForm.get(2).sendKeys(UI);
        basicControl.btnSave();
    }

/*
    public static void MayorVersionFormUI(WebDriver driver,String mayor){
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
        listForm.get(4).sendKeys("Descripción "+ mayor);
        listForm.get(6).sendKeys("Version Mayor");
        driver.findElement(By.id(save)).click();
    }

    public static void MenorVersionFormUI(WebDriver driver,String menor){
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
        listForm.get(4).sendKeys("Descripción "+ menor);
        listForm.get(6).sendKeys("Version Menor");
        driver.findElement(By.id(save)).click();
    }

    public static void restoreVersion_FormUI(WebDriver driver,String restore){
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
        listForm.get(4).sendKeys("Descripción "+ restore);
        listForm.get(6).sendKeys("Version restore");
        driver.findElement(By.id(save)).click();
    }
*/


}