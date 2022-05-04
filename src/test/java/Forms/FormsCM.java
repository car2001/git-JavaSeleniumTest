package Forms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FormsCM {
    private static List<WebElement> listForm;
    private static String save = "__xmlview5--save-img";
    private static String edit= "__xmlview5--edit-img";
    private static String version = "__xmlview5--newVersion-inner";

    //SLA
    public static void formCreateSLA(WebDriver driver, String SLA) throws InterruptedException {
        listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(2).sendKeys(SLA);
        listForm.get(3).sendKeys(SLA);
        listForm.get(4).sendKeys("Es un "+ SLA);
        driver.findElement(By.id("__xmlview5--slaType-arrow")).click();
        driver.findElement(By.xpath("//div[@class='sapMSLITitleOnly'][normalize-space()='Fixed Value']")).click();
        driver.findElement(By.id("__xmlview5--useNumberDays-switch")).click();
        driver.findElement(By.id(save)).click();
        Thread.sleep(500);
    }

    public static void formEditSLA(WebDriver driver, String SLA) throws InterruptedException {
        driver.findElement(By.id(edit)).click();
        Thread.sleep(200);
        listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(2).clear();
        listForm.get(2).sendKeys(SLA);
        listForm.get(3).clear();
        listForm.get(3).sendKeys(SLA);
        listForm.get(4).clear();
        listForm.get(4).sendKeys("Descripción "+ SLA);
        driver.findElement(By.id("__xmlview5--slaType-arrow")).click();
        driver.findElement(By.xpath("//div[@class='sapMSLITitleOnly'][normalize-space()='Depends on a Condition']")).click();
        driver.findElement(By.id(save)).click();
        Thread.sleep(500);
    }

    public static void MayorVersionSLA(WebDriver driver,String mayor) throws InterruptedException{
        driver.findElement(By.id(version)).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//bdi[text()='Major Version']")).click();
        driver.findElement(By.xpath("//bdi[text()='Create Version']")).click();
        Thread.sleep(500);
        listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(2).clear();
        listForm.get(2).sendKeys(mayor);
        listForm.get(3).clear();
        listForm.get(3).sendKeys(mayor);
        listForm.get(4).clear();
        listForm.get(4).sendKeys("Descripción "+ mayor);
        listForm.get(6).sendKeys("Version Mayor");
        driver.findElement(By.id("__xmlview5--slaType-arrow")).click();
        driver.findElement(By.xpath("//div[@class='sapMSLITitleOnly'][normalize-space()='Fixed Value']")).click();
        driver.findElement(By.id(save)).click();
        Thread.sleep(500);
    }

    public static void MenorVersionSLA(WebDriver driver,String menor) throws InterruptedException{
        driver.findElement(By.id(version)).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//bdi[text()='Minor Version']")).click();
        driver.findElement(By.xpath("//bdi[text()='Create Version']")).click();
        Thread.sleep(500);
        listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(2).clear();
        listForm.get(2).sendKeys(menor);
        listForm.get(3).clear();
        listForm.get(3).sendKeys(menor);
        listForm.get(4).clear();
        listForm.get(4).sendKeys("Descripción "+ menor);
        listForm.get(6).sendKeys("Version Menor");
        driver.findElement(By.id("__xmlview5--slaType-arrow")).click();
        driver.findElement(By.xpath("//div[@class='sapMSLITitleOnly'][normalize-space()='Fixed Value']")).click();
        driver.findElement(By.id(save)).click();
        Thread.sleep(500);
    }

    public static void restoreVersion_SLA(WebDriver driver,String restore) throws InterruptedException{
        driver.findElement(By.id("__xmlview5--versionHistory-img")).click();
        Thread.sleep(500);
        String btn_xpath = "//span[@class='sapMBtnInner sapMBtnHoverable sapMFocusable sapMBtnIconFirst sapMBtnDefault']/parent::button[@class='sapMBtnBase sapMBtn']";
        List<WebElement> btn_restore = driver.findElements(By.xpath(btn_xpath));
        btn_restore.get(1).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//bdi[text()='Minor Version']")).click();
        driver.findElement(By.xpath("//bdi[text()='Create Version']")).click();
        Thread.sleep(500);
        listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(2).clear();
        listForm.get(2).sendKeys(restore);
        listForm.get(3).clear();
        listForm.get(3).sendKeys(restore);
        listForm.get(4).clear();
        listForm.get(4).sendKeys("Descripción "+ restore);
        listForm.get(6).sendKeys("Version restore");
        driver.findElement(By.id(save)).click();
        Thread.sleep(500);
    }




}