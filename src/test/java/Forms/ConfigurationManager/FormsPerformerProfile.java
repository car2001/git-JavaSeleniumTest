package Forms.ConfigurationManager;

import Helpers.BasicControl;
import Helpers.FormsControl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



import java.util.List;

public class FormsPerformerProfile {
    private WebDriver driver;
    private List<WebElement> listForm;
    private BasicControl basicControl;
    private String reusePP;
    private String currentUser;
    private String assignMethod;

    public FormsPerformerProfile(WebDriver driver){
        this.driver = driver;
        this.basicControl = new BasicControl(driver);
        this.reusePP = "//div[contains(@id,'--reusePerformer-handle')]";
        this.currentUser = "//div[contains(@id,'--assignCurrentUser-switch')]";
        this.assignMethod = "//span[contains(@id,'--assignmentMethod-arrow')]";
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

/*
    public static void MayorVersionPerformer(WebDriver driver, String mayor){
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
        listForm.get(4).sendKeys("Descripción " + mayor);
        listForm.get(6).sendKeys("Version Mayor");
        driver.findElement(By.id("__xmlview5--assignmentMethod-arrow")).click();
        driver.findElement(By.xpath("//div[@class='sapMSLITitleOnly'][normalize-space()='By Group']")).click();
        driver.findElement(By.id(save)).click();
    }

    public static void MenorVersionPerformer(WebDriver driver, String menor){
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
        listForm.get(4).sendKeys("Descripción " + menor);
        listForm.get(6).sendKeys("Version Menor");
        driver.findElement(By.id(save)).click();
    }

    public static void restoreVersion_PP(WebDriver driver,String restore){
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
