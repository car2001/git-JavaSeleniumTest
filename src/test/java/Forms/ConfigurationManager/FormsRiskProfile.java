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

public class FormsRiskProfile {
    private static List<WebElement> listForm;
    private static BasicControl basicControl;
    private static String typeRisk = "//span[contains(@id,'--typeRisk-arrow')]";
    private static String fixedValue = "//li[contains(@id,'--typeRisk-0')]";
    private static String timeRate = "//li[contains(@id,'--typeRisk-1')]";
    private static String numberHours = "//input[contains(@id,'--numberHoursRisk-inner')]";
    private static String numberMinutes = "//input[contains(@id,'--numberMinutesRisk-inner')]";
    private static String rateRisk = "//input[contains(@id,'--RateRisk-inner')]";

    public static void formCreateRisk(WebDriver driver, String risk){
        basicControl = new BasicControl(driver);
        basicControl.btnAdd();
        listForm = FormsControl.controlNew(driver,"perfil de riesgo","Risk Profile");
        listForm.get(2).click();
        listForm.get(2).sendKeys(risk);
        listForm.get(3).click();
        listForm.get(3).sendKeys(risk);
        listForm.get(4).click();
        listForm.get(4).sendKeys("Descripción " + risk);
        driver.findElement(By.xpath(typeRisk)).click();
        driver.findElement(By.xpath(fixedValue)).click();
        driver.findElement(By.xpath(numberHours)).sendKeys("4");
        driver.findElement(By.xpath(numberMinutes)).sendKeys("60");
        basicControl.btnSave();
    }
    public static void formEditRisk(WebDriver driver,String risk) throws InterruptedException {
        basicControl = new BasicControl(driver);
        listForm = FormsControl.controlEdit(driver,"perfil de riesgo","Risk Profile");
        listForm.get(2).click();
        listForm.get(2).clear();
        listForm.get(2).sendKeys(risk);
        listForm.get(3).click();
        listForm.get(3).clear();
        listForm.get(3).sendKeys(risk);
        listForm.get(4).click();
        listForm.get(4).clear();
        listForm.get(4).sendKeys("Descripción " + risk);
        driver.findElement(By.xpath(typeRisk)).click();
        driver.findElement(By.xpath(timeRate)).click();
        driver.findElement(By.xpath(rateRisk)).sendKeys("5");
        basicControl.btnSave();
    }

    public static void MayorVersionRisk(WebDriver driver,String risk) throws InterruptedException {
        basicControl = new BasicControl(driver);
        basicControl.btnNewVersion("mayor");
        listForm = FormsControl.controlNew(driver,"versión","Version");
        listForm.get(2).click();
        listForm.get(2).clear();
        listForm.get(2).sendKeys(risk);
        listForm.get(3).click();
        listForm.get(3).clear();
        listForm.get(3).sendKeys(risk);
        listForm.get(4).click();
        listForm.get(4).clear();
        listForm.get(4).sendKeys("Descripción " + risk);
        listForm.get(6).click();
        listForm.get(6).sendKeys("Version Mayor");
        driver.findElement(By.xpath(typeRisk)).click();
        driver.findElement(By.xpath(fixedValue)).click();
        basicControl.btnSave();
    }

    public static void MenorVersionRisk(WebDriver driver,String risk) throws InterruptedException {
        basicControl = new BasicControl(driver);
        basicControl.btnNewVersion("menor");
        listForm = FormsControl.controlNew(driver,"versión","Version");
        listForm.get(2).click();
        listForm.get(2).clear();
        listForm.get(2).sendKeys(risk);
        listForm.get(3).click();
        listForm.get(3).clear();
        listForm.get(3).sendKeys(risk);
        listForm.get(4).click();
        listForm.get(4).clear();
        listForm.get(4).sendKeys("Descripción " + risk);
        listForm.get(6).click();
        listForm.get(6).sendKeys("Version Menor");
        basicControl.btnSave();
    }

    public static void restoreVersionRisk(WebDriver driver,String risk){
        basicControl = new BasicControl(driver);
        basicControl.btnVersionHistory(1,"menor");
        listForm = FormsControl.controlNew(driver,"versión","Version");
        listForm.get(2).click();
        listForm.get(2).clear();
        listForm.get(2).sendKeys(risk);
        listForm.get(3).click();
        listForm.get(3).clear();
        listForm.get(3).sendKeys(risk);
        listForm.get(4).click();
        listForm.get(4).clear();
        listForm.get(4).sendKeys("Descripción " + risk);
        listForm.get(6).click();
        listForm.get(6).sendKeys("Version restore");
        basicControl.btnSave();
    }

}
