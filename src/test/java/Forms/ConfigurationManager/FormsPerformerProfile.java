package Forms.ConfigurationManager;

import Helpers.Asserts;
import Helpers.BasicControl;
import Helpers.FormsControl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


import java.util.List;

public class FormsPerformerProfile {
    private WebDriver driver;
    private List<WebElement> listForm;
    private BasicControl basicControl;
    private String reusePP;
    private String currentUser;
    private String assignMethod;
    private Asserts asserts;
    private String inputVersion;

    public FormsPerformerProfile(WebDriver driver){
        this.driver = driver;
        this.basicControl = new BasicControl(driver);
        this.reusePP = "//div[contains(@id,'--reusePerformer-handle')]";
        this.currentUser = "//div[contains(@id,'--assignCurrentUser-switch')]";
        this.assignMethod = "//span[contains(@id,'--assignmentMethod-arrow')]";
        this.asserts = new Asserts(driver);
        this.inputVersion = "//input[contains(@id,'--txtVersion-inner')]";
    }

    public void formCreatePerformer(String performer){
        basicControl.btnAdd();
        listForm = FormsControl.controlNew(driver,"Perfil de Ejecutor","Performer Profile");
        listForm.get(0).click();
        listForm.get(0).sendKeys(performer);
        listForm.get(1).click();
        listForm.get(1).sendKeys(performer);
        listForm.get(2).click();
        listForm.get(2).sendKeys("Descripción " + performer);
        driver.findElement(By.xpath(assignMethod)).click();
        driver.findElement(By.xpath("//div[@class='sapMSLITitleOnly'][normalize-space()='By Group']")).click();
        basicControl.btnSave();
    }

    public void formEditPerformer(String performer) throws InterruptedException {
        listForm = FormsControl.controlEdit(driver,"Perfil de Ejecutor","Performer Profile");
        listForm.get(0).clear();
        listForm.get(0).sendKeys(performer);
        listForm.get(1).clear();
        listForm.get(1).sendKeys(performer);
        listForm.get(2).clear();
        listForm.get(2).sendKeys("Descripción " + performer);
        driver.findElement(By.xpath(assignMethod)).click();
        driver.findElement(By.xpath("//div[@class='sapMSLITitleOnly'][normalize-space()='By Experience']")).click();
        basicControl.btnSave();
    }


    public void MayorVersionPerformer(String mayor) throws InterruptedException {
        listForm = FormsControl.controlNewVersionMayor(driver,"Perfil de Ejecutor","Performer Profile");

        listForm.get(0).click();
        listForm.get(0).clear();
        listForm.get(0).sendKeys(mayor);

        listForm.get(1).click();
        listForm.get(1).clear();
        listForm.get(1).sendKeys(mayor);

        listForm.get(2).click();
        listForm.get(2).clear();
        listForm.get(2).sendKeys("Descripción " + mayor);

        listForm.get(3).click();
        listForm.get(3).sendKeys("Version Mayor");

        String versionActual = driver.findElement(By.xpath(inputVersion)).getAttribute("value");

        driver.findElement(By.xpath(assignMethod)).click();
        driver.findElement(By.xpath("//div[@class='sapMSLITitleOnly'][normalize-space()='By Group']")).click();
        basicControl.btnSave();

        Thread.sleep(1500);
        String versionMayor = driver.findElement(By.xpath(inputVersion)).getAttribute("value");
        asserts.assertVersionMayor(versionActual,versionMayor);
    }

    public void MenorVersionPerformer(String menor) throws InterruptedException {
        listForm = FormsControl.controlNewVersionMenor(driver,"Perfil de Ejecutor","Performer Profile");

        listForm.get(0).click();
        listForm.get(0).clear();
        listForm.get(0).sendKeys(menor);

        listForm.get(1).click();
        listForm.get(1).clear();
        listForm.get(1).sendKeys(menor);

        listForm.get(2).click();
        listForm.get(2).clear();
        listForm.get(2).sendKeys("Descripción " + menor);

        listForm.get(3).sendKeys("Version Menor");

        String versionActual = driver.findElement(By.xpath(inputVersion)).getAttribute("value");

        basicControl.btnSave();

        Thread.sleep(1500);
        String versionMenor = driver.findElement(By.xpath(inputVersion)).getAttribute("value");
        asserts.assertVersionMenor(versionActual,versionMenor);
    }

    public void restoreVersion_PP(String restore, String versionActual) throws InterruptedException {
        listForm = FormsControl.controlRestoreVersion(driver,"mayor","Perfil de Ejecutor","Performer Profile");

        listForm.get(0).click();
        listForm.get(0).clear();
        listForm.get(0).sendKeys(restore);

        listForm.get(1).click();
        listForm.get(1).clear();
        listForm.get(1).sendKeys(restore);

        listForm.get(2).click();
        listForm.get(2).clear();
        listForm.get(2).sendKeys("Descripción "+ restore);

        listForm.get(3).click();
        listForm.get(3).sendKeys("Version restore");

        basicControl.btnSave();

        Thread.sleep(1500);
        String versionMayor = driver.findElement(By.xpath(inputVersion)).getAttribute("value");
        asserts.assertVersionMayor(versionActual,versionMayor);

    }



}
