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
    private WebDriver driver;
    private List<WebElement> listForm;
    private BasicControl basicControl;
    private String typeRisk;
    private String fixedValue;
    private String timeRate;
    private String numberHours;
    private String numberMinutes;
    private String rateRisk;

    public FormsRiskProfile(WebDriver driver){
        this.driver = driver;
        this.basicControl = new BasicControl(driver);
        this.typeRisk = "//span[contains(@id,'--typeRisk-arrow')]";
        this.fixedValue = "//li[contains(@id,'--typeRisk-0')]";
        this.timeRate = "//li[contains(@id,'--typeRisk-1')]";
        this.numberHours = "//input[contains(@id,'--numberHoursRisk-inner')]";
        this.numberMinutes = "//input[contains(@id,'--numberMinutesRisk-inner')]";
        this.rateRisk = "//input[contains(@id,'--RateRisk-inner')]";
    }

    public void formCreateRisk(WebDriver driver, String risk){
        basicControl.btnAdd();
        listForm = FormsControl.controlNew(driver,"Perfil de Riesgo","Risk Profile");
        listForm.get(0).click();
        listForm.get(0).sendKeys(risk);
        listForm.get(1).click();
        listForm.get(1).sendKeys(risk);
        listForm.get(2).click();
        listForm.get(2).sendKeys("Descripción " + risk);
        driver.findElement(By.xpath(typeRisk)).click();
        driver.findElement(By.xpath(fixedValue)).click();
        driver.findElement(By.xpath(numberHours)).sendKeys("4");
        driver.findElement(By.xpath(numberMinutes)).sendKeys("60");
        basicControl.btnSave();
    }
    public void formEditRisk(WebDriver driver,String risk) throws InterruptedException {
        listForm = FormsControl.controlEdit(driver,"Perfil de Riesgo","Risk Profile");
        listForm.get(0).click();
        listForm.get(0).clear();
        listForm.get(0).sendKeys(risk);
        listForm.get(1).click();
        listForm.get(1).clear();
        listForm.get(1).sendKeys(risk);
        listForm.get(2).click();
        listForm.get(2).clear();
        listForm.get(2).sendKeys("Descripción " + risk);
        driver.findElement(By.xpath(typeRisk)).click();
        driver.findElement(By.xpath(timeRate)).click();
        driver.findElement(By.xpath(rateRisk)).sendKeys("5");
        basicControl.btnSave();
    }


}
