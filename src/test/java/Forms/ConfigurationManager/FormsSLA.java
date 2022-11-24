package Forms.ConfigurationManager;

import Helpers.Asserts;
import Helpers.BasicControl;
import Helpers.FormsControl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import java.awt.font.NumericShaper;
import java.time.Duration;
import java.util.List;

public class FormsSLA {
    private WebDriver driver;
    private List<WebElement> listForm;
    private BasicControl basicControl;
    private String slaType;
    private String useNumberDays;
    private String inputVersion;
    private Asserts asserts;

    public FormsSLA(WebDriver driver){
        this.driver = driver;
        this.basicControl = new BasicControl(driver);
        this.asserts = new Asserts(driver);
        this.slaType = "//span[contains(@id,'--slaType-arrow')]";
        this.useNumberDays = "//div[contains(@id,'--useNumberDays-switch')]";
        this.inputVersion = "//input[contains(@id,'--txtVersion-inner')]";
    }

    public void formCreateSLA(WebDriver driver, String SLA) throws InterruptedException {
        basicControl.btnAdd();
        listForm = FormsControl.controlNew(driver,"SLA","SLA");
        listForm.get(0).click();
        listForm.get(0).sendKeys(SLA);
        listForm.get(1).click();
        listForm.get(1).sendKeys(SLA);
        listForm.get(2).click();
        listForm.get(2).sendKeys("Es un "+ SLA);
        //Tipo de SLA
        driver.findElement(By.xpath(slaType)).click();
        driver.findElement(By.xpath("//ul[contains(@id,'--slaType-popup-lis')]//li//div[@class='sapMSLITitleOnly'][text()='Fixed Value']")).click();
        driver.findElement(By.xpath(useNumberDays)).click();
        basicControl.btnSave();
    }

    public void formEditSLA(WebDriver driver, String SLA) throws InterruptedException {
        listForm = FormsControl.controlEdit(driver,"SLA","SLA");
        listForm.get(0).click();
        listForm.get(0).clear();
        listForm.get(0).sendKeys(SLA);
        listForm.get(1).click();
        listForm.get(1).clear();
        listForm.get(1).sendKeys(SLA);
        listForm.get(2).click();
        listForm.get(2).clear();
        listForm.get(2).sendKeys("Descripción "+ SLA);
        driver.findElement(By.xpath(slaType)).click();
        driver.findElement(By.xpath("//ul[contains(@id,'--slaType-popup-lis')]//li//div[@class='sapMSLITitleOnly'][text()='Depends on a Condition']")).click();
        basicControl.btnSave();
    }


    public void MayorVersionSLA(String mayor) throws InterruptedException {
        listForm = FormsControl.controlNewVersionMayor(driver,"SLA","SLA");

        listForm.get(0).click();
        listForm.get(0).clear();
        listForm.get(0).sendKeys(mayor);

        listForm.get(1).click();
        listForm.get(1).clear();
        listForm.get(1).sendKeys(mayor);

        listForm.get(2).click();
        listForm.get(2).clear();
        listForm.get(2).sendKeys("Descripción "+ mayor);

        listForm.get(3).sendKeys("Version Mayor");

        String versionActual = driver.findElement(By.xpath(inputVersion)).getAttribute("value");

        driver.findElement(By.xpath(slaType)).click();
        driver.findElement(By.xpath("//ul[contains(@id,'--slaType-popup-lis')]//li//div[@class='sapMSLITitleOnly'][text()='Fixed Value']")).click();
        basicControl.btnSave();

        Thread.sleep(1500);
        String versionMayor = driver.findElement(By.xpath(inputVersion)).getAttribute("value");
        asserts.assertVersionMayor(versionActual,versionMayor);

    }




    public void MenorVersionSLA(String menor) throws InterruptedException {
        listForm = FormsControl.controlNewVersionMenor(driver,"SLA","SLA");
        listForm.get(0).click();
        listForm.get(0).clear();
        listForm.get(0).sendKeys(menor);

        listForm.get(1).click();
        listForm.get(1).clear();
        listForm.get(1).sendKeys(menor);

        listForm.get(2).click();
        listForm.get(2).clear();
        listForm.get(2).sendKeys("Descripción "+ menor);

        listForm.get(3).sendKeys("Version Menor");
        String versionActual = driver.findElement(By.xpath(inputVersion)).getAttribute("value");

        driver.findElement(By.xpath(slaType)).click();
        driver.findElement(By.xpath("//ul[contains(@id,'--slaType-popup-lis')]//li//div[@class='sapMSLITitleOnly'][text()='Fixed Value']")).click();
        basicControl.btnSave();

        Thread.sleep(1500);

        String versionMenor = driver.findElement(By.xpath(inputVersion)).getAttribute("value");
        System.out.println(versionActual);
        System.out.println(versionMenor);
        asserts.assertVersionMenor(versionActual,versionMenor);
    }
/*
    public static void restoreVersion_SLA(WebDriver driver,String restore){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.id(versionHistory)));
        driver.findElement(By.id(versionHistory)).click();
        String btn_xpath = "//span[@class='sapMBtnInner sapMBtnHoverable sapMFocusable sapMBtnIconFirst sapMBtnDefault']/parent::button[@class='sapMBtnBase sapMBtn']";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(btn_xpath)));
        List<WebElement> btn_restore = driver.findElements(By.xpath(btn_xpath));
        btn_restore.get(1).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//bdi[text()='Major Version']")));
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