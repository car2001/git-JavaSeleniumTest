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

    //Release





    //Deployment Package

    public static void formDeploymentPackage(WebDriver driver , String DeploymentPackage,String Proyecto,String release){
        listForm = FormsControl.controlNew(driver,"paquete de instalación","Deployment Package");
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
        listForm = FormsControl.controlNew(driver,"solicitud de instalación","Deployment Request");
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
        FormsControl.controlTitle(driver,"Liberación","Release");
        driver.findElement(By.xpath("//span[contains(@id,'--cbDP-arrow') and @class='sapUiIcon sapUiIconMirrorInRTL sapUiIconPointer sapMInputBaseIcon']")).click();
        driver.findElement(By.xpath("//div[text()='DP_SELENIUM' and @class='sapMSLITitleOnly']")).click();
        basicControl.btnSave();
        driver.findElement(By.xpath("//bdi[text()='OK']")).click();
        accessBranches.clickBranches(1);
        accessBranches.clickBranches(2);

    }
    public static  void formReleaseDP(WebDriver driver, AccessBranch accessBranches){
        basicControl = new BasicControl(driver);
        FormsControl.controlTitle(driver,"Liberación","Release");
        driver.findElement(By.xpath("//span[contains(@id,'--cbDR-arrow') and @class='sapUiIcon sapUiIconMirrorInRTL sapUiIconPointer sapMInputBaseIcon']")).click();
        driver.findElement(By.xpath("//div[text()='DR_SELENIUM' and @class='sapMSLITitleOnly']")).click();
        basicControl.btnSave();
        driver.findElement(By.xpath("//bdi[text()='OK']")).click();
        accessBranches.clickBranches(2);
        accessBranches.clickBranches(3);
    }

}
