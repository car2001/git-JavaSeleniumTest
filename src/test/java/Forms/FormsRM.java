package Forms;

import Helpers.AccessBranch;
import Helpers.BasicControl;
import Helpers.FormsControl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import java.time.LocalDate;
import java.util.List;



public class FormsRM {
    private static List<WebElement> listForm;
    private static String save = "__xmlview4--save-img";
    private static String edit= "__xmlview4--edit-img";
    private static String version = "__xmlview4--newVersion-inner";
    private static String versionHistory = "__xmlview4--versionHistory-img";
    private static String num = "4";
    private static BasicControl basicControl;


    //Project

    public static void formCreateProject(WebDriver driver,String proyecto){
        listForm = FormsControl.controlNew(driver,"proyecto","");
        listForm.get(2).sendKeys(proyecto);
        listForm.get(3).sendKeys(proyecto);
        listForm.get(4).sendKeys("Proyecto Creado en Selenium");
        //Thread.sleep(500);
        driver.findElement(By.id("__xmlview4--ProjectStartDate-icon")).click();
        LocalDate date = LocalDate.now();
        driver.findElement(By.xpath("//span[normalize-space()='"+date.getDayOfMonth()+"']")).click();
        driver.findElement(By.id("__xmlview4--ProjectEndDate-icon")).click();
        driver.findElement(By.id("__xmlview4--ProjectEndDate-cal--Head-B2")).click();
        driver.findElement(By.xpath("//div[text()='"+(date.getYear()+5)+"']")).click();
        List<WebElement> Day = driver.findElements(By.xpath("//span[normalize-space()='"+date.getDayOfMonth()+"']"));
        Day.get(1).click();
        driver.findElement(By.id("__xmlview4--selectProjectState-label")).click();
        driver.findElement(By.xpath("//li[text()='Open']")).click();
        driver.findElement(By.id("__xmlview4--useInProcess-handle")).click();
        driver.findElement(By.id("__xmlview4--useInReleases-handle")).click();
        driver.findElement(By.id(save)).click();
    }

    public static void formEditProject(WebDriver driver,String proyecto) throws InterruptedException {
        listForm = FormsControl.controlEdit(driver,"proyecto","");
        listForm.get(2).clear();
        listForm.get(2).sendKeys(proyecto);
        listForm.get(3).clear();
        listForm.get(3).sendKeys(proyecto);
        listForm.get(4).clear();
        listForm.get(4).sendKeys("Creado en Selenium");
        driver.findElement(By.id("__xmlview4--selectProjectState-label")).click();
        driver.findElement(By.xpath("//li[text()='Closed']")).click();
        driver.findElement(By.id(save)).click();

    }

    public static void formCreateProjectWithoutRelease(WebDriver driver,String proyecto){
        listForm = FormsControl.controlNew(driver,"proyecto","");
        listForm.get(2).sendKeys(proyecto);
        listForm.get(3).sendKeys(proyecto);
        listForm.get(4).sendKeys(" Proyecto sin Release Creado en Selenium");
        driver.findElement(By.id("__xmlview4--ProjectStartDate-icon")).click();
        LocalDate date = LocalDate.now();
        driver.findElement(By.xpath("//span[normalize-space()='"+date.getDayOfMonth()+"']")).click();
        driver.findElement(By.id("__xmlview4--ProjectEndDate-icon")).click();
        driver.findElement(By.id("__xmlview4--ProjectEndDate-cal--Head-B2")).click();
        driver.findElement(By.xpath("//div[text()='"+(date.getYear()+5)+"']")).click();
        List<WebElement> endDay = driver.findElements(By.xpath("//span[normalize-space()='"+date.getDayOfMonth()+"']"));
        endDay.get(1).click();
        driver.findElement(By.id("__xmlview4--selectProjectState-label")).click();
        driver.findElement(By.xpath("//li[text()='Open']")).click();
        driver.findElement(By.id(save)).click();
    }

    //Release

    public static void formCreateRelease(WebDriver driver,String release){
        listForm = FormsControl.controlNew(driver,"liberación","");
        listForm.get(2).sendKeys(release);
        listForm.get(3).sendKeys(release);
        listForm.get(4).sendKeys("Release Creado en Selenium");
        driver.findElement(By.id("__xmlview4--ReleaseStartDate-icon")).click();
        LocalDate date = LocalDate.now();
        List<WebElement> Day = driver.findElements(By.xpath("//span[normalize-space()='"+date.getDayOfMonth()+"']"));
        Day.get(2).click();
        driver.findElement(By.id("__xmlview4--ReleaseEndDate-icon")).click();
        driver.findElement(By.id("__xmlview4--ReleaseEndDate-cal--Head-B2")).click();
        driver.findElement(By.xpath("//div[text()='"+(date.getYear()+5)+"']")).click();
        Day = driver.findElements(By.xpath("//span[normalize-space()='"+date.getDayOfMonth()+"']"));
        Day.get(3).click();
        listForm.get(8).sendKeys("Release Selenium");
        driver.findElement(By.id("__xmlview4--selectReleaseState-label")).click();
        List<WebElement> stateRelease =  driver.findElements(By.xpath("//li[text()='Open']"));
        stateRelease.get(1).click();
        driver.findElement(By.id(save)).click();
    }

    public static void formEditRelease(WebDriver driver,String release) throws InterruptedException {
        listForm = FormsControl.controlEdit(driver,"liberacion","");
        listForm.get(2).clear();
        listForm.get(2).sendKeys(release);
        listForm.get(3).clear();
        listForm.get(3).sendKeys(release);
        driver.findElement(By.id("__xmlview4--ReleaseStartDate-icon")).click();
        LocalDate date = LocalDate.now();
        List<WebElement> Day = driver.findElements(By.xpath("//span[normalize-space()='"+date.getDayOfMonth()+"']"));
        Day.get(0).click();
        driver.findElement(By.id("__xmlview4--ReleaseEndDate-icon")).click();
        driver.findElement(By.id("__xmlview4--ReleaseEndDate-cal--Head-B2")).click();
        driver.findElement(By.xpath("//div[text()='"+(date.getYear()+5)+"']")).click();
        Day = driver.findElements(By.xpath("//span[normalize-space()='"+date.getDayOfMonth()+"']"));
        Day.get(1).click();
        listForm.get(8).clear();
        listForm.get(8).sendKeys("Release Selenium");
        driver.findElement(By.id(save)).click();
    }

    // Change Container

    public static void formCreateChangeContainer(WebDriver driver,String ChangeContainer,String Proyecto,String release,String user) throws InterruptedException {
        listForm = FormsControl.controlNew(driver,"contenedor de cambios","");
        listForm.get(2).click();
        listForm.get(2).sendKeys(ChangeContainer);
        listForm.get(3).click();
        listForm.get(3).sendKeys(ChangeContainer);
        listForm.get(4).click();
        listForm.get(4).sendKeys( ChangeContainer);
        List<WebElement> listComboBox = driver.findElements(By.className("sapMSltLabel"));
        listComboBox.get(0).click();
        driver.findElement(By.xpath("//li[text()='"+Proyecto+"']")).click();
        listComboBox.get(1).click();
        driver.findElement(By.xpath("//li[text()='"+release+"']")).click();
        listComboBox.get(2).click();
        driver.findElement(By.xpath("//li[text()='"+user+"']")).click();
        driver.findElement(By.id(save)).click();
    }

    //Deployment Package

    public static void formDeploymentPackage(WebDriver driver , String DeploymentPackage,String Proyecto,String release){
        listForm = FormsControl.controlNew(driver,"paquete de instalación","");
        listForm.get(3).click();
        listForm.get(3).sendKeys(DeploymentPackage);
        listForm.get(4).click();
        listForm.get(4).sendKeys(DeploymentPackage);
        List<WebElement> listComboBox = driver.findElements(By.className("sapMSltLabel"));
        listComboBox.get(0).click();
        driver.findElement(By.xpath("//li[text()='"+Proyecto+"']")).click();
        listComboBox.get(1).click();
        driver.findElement(By.xpath("//li[text()='"+release+"']")).click();
        listComboBox.get(2).click();
        driver.findElement(By.id(save)).click();
    }

    //Deployment Request

    public static void formDeploymentRequest(WebDriver driver , String DeploymentRequest,String Proyecto,String release) throws InterruptedException {
        listForm = FormsControl.controlNew(driver,"solicitud de instalación","");
        listForm.get(3).click();
        listForm.get(3).sendKeys(DeploymentRequest);
        listForm.get(4).click();
        listForm.get(4).sendKeys(DeploymentRequest);
        List<WebElement> listComboBox = driver.findElements(By.className("sapMSltLabel"));
        listComboBox.get(0).click();
        driver.findElements(By.xpath("//li[text()='"+Proyecto+"']")).get(1).click();
        listComboBox.get(1).click();
        Thread.sleep(1000);
        driver.findElements(By.xpath("//li[text()='"+release+"']")).get(1).click();
        listComboBox.get(2).click();
        driver.findElement(By.id(save)).click();
    }

    //lIBERACION
    public static void formReleaseCC(WebDriver driver, AccessBranch accessBranches){
        basicControl = new BasicControl(driver);
        listForm = FormsControl.controlNew(driver,"Liberación","");
        driver.findElement(By.xpath("//span[contains(@id,'--cbDP-arrow')]")).click();
        driver.findElement(By.xpath("//div[text()='DP_SELENIUM' and @class='sapMSLITitleOnly']")).click();
        basicControl.btnSave();
        driver.findElement(By.xpath("//bdi[text()='OK']")).click();
        accessBranches.clickBranches(1);
        accessBranches.clickBranches(2);

    }
    public static  void formReleaseDP(WebDriver driver, AccessBranch accessBranches){
        basicControl = new BasicControl(driver);
        listForm = FormsControl.controlNew(driver,"Liberación","");
        driver.findElement(By.xpath("//span[contains(@id,'--cbDR-arrow') and @class='sapUiIcon sapUiIconMirrorInRTL sapUiIconPointer sapMInputBaseIcon']")).click();
        driver.findElement(By.xpath("//div[text()='DR_SELENIUM' and @class='sapMSLITitleOnly']")).click();
        basicControl.btnSave();
        driver.findElement(By.xpath("//bdi[text()='OK']")).click();
        accessBranches.clickBranches(2);
        accessBranches.clickBranches(3);
    }

}
