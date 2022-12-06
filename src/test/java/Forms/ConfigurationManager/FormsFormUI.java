package Forms.ConfigurationManager;

import Helpers.Asserts;
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
    private String inputVersion;
    private Asserts asserts;

    public FormsFormUI(WebDriver driver){
        this.driver = driver;
        this.basicControl = new BasicControl(driver);
        this.comment = "//div[contains(@id,'--comments-switch')]";
        this.attachment = "//div[contains(@id,'--attachments-switch')]";
        this.instructions = "//div[contains(@id,'--instructions-switch')]";
        this.inputVersion = "//input[contains(@id,'--txtVersion-inner')]";
        this.asserts = new Asserts(driver);
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


    public void MayorVersionFormUI(String vMayorFormUI) throws InterruptedException {
        listForm = FormsControl.controlNewVersionMayor(driver, "UI", "UI");

        listForm.get(0).click();
        listForm.get(0).clear();
        listForm.get(0).sendKeys(vMayorFormUI);

        listForm.get(1).click();
        listForm.get(1).clear();
        listForm.get(1).sendKeys(vMayorFormUI);

        listForm.get(2).click();
        listForm.get(2).clear();
        listForm.get(2).sendKeys("Descripción "+ vMayorFormUI);

        String versionActual = driver.findElement(By.xpath(inputVersion)).getAttribute("value");

        listForm.get(3).click();
        listForm.get(3).sendKeys("Version Mayor");
        basicControl.btnSave();

        Thread.sleep(1500);
        String versionMayor = driver.findElement(By.xpath(inputVersion)).getAttribute("value");
        asserts.assertVersionMayor(versionActual,versionMayor);
    }


    public void MenorVersionFormUI(String vMenorFormUI) throws InterruptedException {
        listForm = FormsControl.controlNewVersionMenor(driver, "UI", "UI");

        listForm.get(0).click();
        listForm.get(0).clear();
        listForm.get(0).sendKeys(vMenorFormUI);

        listForm.get(1).click();
        listForm.get(1).clear();
        listForm.get(1).sendKeys(vMenorFormUI);

        listForm.get(2).click();
        listForm.get(2).clear();
        listForm.get(2).sendKeys("Descripción "+ vMenorFormUI);

        String versionActual = driver.findElement(By.xpath(inputVersion)).getAttribute("value");

        listForm.get(3).click();
        listForm.get(3).sendKeys("Version Menor");

        basicControl.btnSave();

        Thread.sleep(1500);
        String versionMenor = driver.findElement(By.xpath(inputVersion)).getAttribute("value");
        asserts.assertVersionMenor(versionActual,versionMenor);
    }


    public void restoreVersion_FormUI(String restore,String versionActual) throws InterruptedException {
        listForm = FormsControl.controlRestoreVersion(driver, "mayor", "UI", "UI");

        listForm.get(0).click();
        listForm.get(0).clear();
        listForm.get(0).sendKeys(restore);

        listForm.get(1).click();
        listForm.get(1).clear();
        listForm.get(1).sendKeys(restore);

        listForm.get(2).click();
        listForm.get(2).clear();
        listForm.get(2).sendKeys("Descripción " + restore);

        listForm.get(3).click();
        listForm.get(3).sendKeys("Version restore");

        basicControl.btnSave();

        Thread.sleep(1500);
        String versionMayor = driver.findElement(By.xpath(inputVersion)).getAttribute("value");
        asserts.assertVersionMayor(versionActual,versionMayor);
    }



}