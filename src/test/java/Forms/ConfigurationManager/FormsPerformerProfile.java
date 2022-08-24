package Forms.ConfigurationManager;

import Helpers.BasicControl;
import Helpers.FormsControl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



import java.util.List;

public class FormsPerformerProfile {
    private static List<WebElement> listForm;
    private static BasicControl basicControl;
    private static String reusePP = "//div[contains(@id,'--reusePerformer-handle')]";
    private static String currentUser = "//div[contains(@id,'--assignCurrentUser-switch')]";
    private static String assignMethod = "//span[contains(@id,'--assignmentMethod-arrow')]";

    public static void formCreatePerformer(WebDriver driver, String performer){
        basicControl = new BasicControl(driver);
        basicControl.btnAdd();
        listForm = FormsControl.controlNew(driver,"perfil de ejecutor","Performer Profile");
        listForm.get(2).click();
        listForm.get(2).sendKeys(performer);
        listForm.get(3).click();
        listForm.get(3).sendKeys(performer);
        listForm.get(4).click();
        listForm.get(4).sendKeys("Descripción " + performer);
        driver.findElement(By.xpath(assignMethod)).click();
        driver.findElement(By.xpath("//div[@class='sapMSLITitleOnly'][normalize-space()='By Group']")).click();
        basicControl.btnSave();
    }

    public static void formEditPerformer(WebDriver driver,String performer) throws InterruptedException {
        basicControl = new BasicControl(driver);
        listForm = FormsControl.controlEdit(driver,"perfil de ejecutor","Performer Profile");
        listForm.get(2).clear();
        listForm.get(2).sendKeys(performer);
        listForm.get(3).clear();
        listForm.get(3).sendKeys(performer);
        listForm.get(4).clear();
        listForm.get(4).sendKeys("Descripción " + performer);
        driver.findElement(By.xpath(assignMethod)).click();
        driver.findElement(By.xpath("//div[@class='sapMSLITitleOnly'][normalize-space()='By Experience']")).click();
        basicControl.btnSave();
    }

    public static void MayorVersionPerformer(WebDriver driver, String mayor) throws InterruptedException {
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
        listForm.get(4).sendKeys("Descripción " + mayor);
        listForm.get(6).click();
        listForm.get(6).sendKeys("Version Mayor");
        driver.findElement(By.xpath(assignMethod)).click();
        driver.findElement(By.xpath("//div[@class='sapMSLITitleOnly'][normalize-space()='By Group']")).click();
        basicControl.btnSave();
    }

    public static void MenorVersionPerformer(WebDriver driver, String menor) throws InterruptedException {
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
        listForm.get(4).sendKeys("Descripción " + menor);
        listForm.get(6).click();
        listForm.get(6).sendKeys("Version Menor");
        basicControl.btnSave();
    }

    public static void restoreVersion_PP(WebDriver driver,String restore){
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
