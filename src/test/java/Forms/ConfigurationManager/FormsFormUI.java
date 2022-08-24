package Forms.ConfigurationManager;

import Helpers.BasicControl;
import Helpers.FormsControl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import java.util.List;

public class FormsFormUI {
    private static List<WebElement> listForm;
    private static BasicControl basicControl;
    private static String comment = "//div[contains(@id,'--comments-switch')]";
    private static String attachment = "//div[contains(@id,'--attachments-switch')]";
    private static String instructions = "//div[contains(@id,'--instructions-switch')]";

    public static void formCreateFormUI(WebDriver driver, String UI){
        basicControl = new BasicControl(driver);
        basicControl.btnAdd();
        listForm = FormsControl.controlNew(driver,"UI","UI");
        listForm.get(2).click();
        listForm.get(2).sendKeys(UI);
        listForm.get(3).click();
        listForm.get(3).sendKeys(UI);
        listForm.get(4).click();
        listForm.get(4).sendKeys(UI);
        driver.findElement(By.xpath(comment)).click();
        driver.findElement(By.xpath(attachment)).click();
        driver.findElement(By.xpath(instructions)).click();
        basicControl.btnSave();
    }

    public static void formEditFormUI(WebDriver driver, String UI) throws InterruptedException {
        basicControl = new BasicControl(driver);
        listForm = FormsControl.controlEdit(driver,"UI","UI");
        listForm.get(2).click();
        listForm.get(2).clear();
        listForm.get(2).sendKeys(UI);
        listForm.get(3).click();
        listForm.get(3).clear();
        listForm.get(3).sendKeys(UI);
        listForm.get(4).click();
        listForm.get(4).clear();
        listForm.get(4).sendKeys(UI);
        basicControl.btnSave();
    }

    public static void MayorVersionFormUI(WebDriver driver,String mayor) throws InterruptedException {
        basicControl = new BasicControl(driver);
        basicControl.btnNewVersion("mayor");
        listForm = FormsControl.controlNew(driver,"versión","Version");
        listForm.get(2).click();
        listForm.get(2).clear();
        listForm.get(2).sendKeys(mayor);
        listForm.get(3).click();
        listForm.get(3).clear();
        listForm.get(3).sendKeys(mayor);
        listForm.get(4).click();
        listForm.get(4).clear();
        listForm.get(4).sendKeys("Descripción "+ mayor);
        listForm.get(6).click();
        listForm.get(6).sendKeys("Version Mayor");
        basicControl.btnSave();
    }

    public static void MenorVersionFormUI(WebDriver driver,String menor) throws InterruptedException {
        basicControl = new BasicControl(driver);
        basicControl.btnNewVersion("menor");
        listForm = FormsControl.controlNew(driver,"versión","Version");
        listForm.get(2).click();
        listForm.get(2).clear();
        listForm.get(2).sendKeys(menor);
        listForm.get(3).click();
        listForm.get(3).clear();
        listForm.get(3).sendKeys(menor);
        listForm.get(4).click();
        listForm.get(4).clear();
        listForm.get(4).sendKeys("Descripción "+ menor);
        listForm.get(6).click();
        listForm.get(6).sendKeys("Version Menor");
        basicControl.btnSave();
    }

    public static void restoreVersion_FormUI(WebDriver driver,String restore){
        basicControl = new BasicControl(driver);
        basicControl.btnVersionHistory(1,"menor");
        listForm = FormsControl.controlNew(driver,"versión","Version");
        listForm.get(2).click();
        listForm.get(2).clear();
        listForm.get(2).sendKeys(restore);
        listForm.get(3).click();
        listForm.get(3).clear();
        listForm.get(3).sendKeys(restore);
        listForm.get(4).click();
        listForm.get(4).clear();
        listForm.get(4).sendKeys("Descripción "+ restore);
        listForm.get(6).click();
        listForm.get(6).sendKeys("Version restore");
        basicControl.btnSave();
    }

}
