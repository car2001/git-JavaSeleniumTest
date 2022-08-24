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

public class FormsSLA {
    private static List<WebElement> listForm;
    private static BasicControl basicControl;
    private static String slaType = "//span[contains(@id,'--slaType-arrow')]";

    public static void formCreateSLA(WebDriver driver, String SLA){
        basicControl = new BasicControl(driver);
        basicControl.btnAdd();
        listForm = FormsControl.controlNew(driver,"SLA","SLA");
        listForm.get(2).click();
        listForm.get(2).sendKeys(SLA);
        listForm.get(3).click();
        listForm.get(3).sendKeys(SLA);
        listForm.get(4).click();
        listForm.get(4).sendKeys("Es un "+ SLA);
        //Tipo de SLA
        driver.findElement(By.xpath(slaType)).click();
        driver.findElement(By.xpath("//div[@class='sapMSLITitleOnly'][text()='Fixed Value']")).click();
        driver.findElement(By.xpath("//div[contains(@id,'--useNumberDays-switch')]")).click();
        basicControl.btnSave();
    }

    public static void formEditSLA(WebDriver driver, String SLA) throws InterruptedException {
        basicControl = new BasicControl(driver);
        listForm = FormsControl.controlEdit(driver,"SLA","SLA");
        listForm.get(2).click();
        listForm.get(2).clear();
        listForm.get(2).sendKeys(SLA);
        listForm.get(3).click();
        listForm.get(3).clear();
        listForm.get(3).sendKeys(SLA);
        listForm.get(4).click();
        listForm.get(4).clear();
        listForm.get(4).sendKeys("Descripción "+ SLA);
        driver.findElement(By.xpath(slaType)).click();
        driver.findElement(By.xpath("//div[@class='sapMSLITitleOnly'][normalize-space()='Depends on a Condition']")).click();
        basicControl.btnSave();
    }

    public static void MayorVersionSLA(WebDriver driver,String mayorSLA) throws InterruptedException {
        basicControl = new BasicControl(driver);
        basicControl.btnNewVersion("mayor");
        listForm = FormsControl.controlNew(driver,"versión","Version");
        listForm.get(2).click();
        listForm.get(2).clear();
        listForm.get(2).sendKeys(mayorSLA);
        listForm.get(3).click();
        listForm.get(3).clear();
        listForm.get(3).sendKeys(mayorSLA);
        listForm.get(4).click();
        listForm.get(4).clear();
        listForm.get(4).sendKeys("Descripción "+ mayorSLA);
        listForm.get(6).click();
        listForm.get(6).sendKeys("Version Mayor");
        driver.findElement(By.xpath(slaType)).click();
        driver.findElement(By.xpath("//div[@class='sapMSLITitleOnly'][normalize-space()='Fixed Value']")).click();
        basicControl.btnSave();
    }

    public static void MenorVersionSLA(WebDriver driver,String menor) throws InterruptedException {
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
        driver.findElement(By.xpath(slaType)).click();
        driver.findElement(By.xpath("//div[@class='sapMSLITitleOnly'][normalize-space()='Fixed Value']")).click();
        basicControl.btnSave();
    }

    public static void restoreVersion_SLA(WebDriver driver,String restore){
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
