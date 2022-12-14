package Forms.ReleaseManager;

import Helpers.AccessBranch;
import Helpers.BasicControl;
import Helpers.FormsControl;
import Helpers.SelectListItem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FormsDeployPackage {

    private WebDriver driver;
    private List<WebElement> listForm;
    private BasicControl basicControl;
    private AccessBranch accessBranch;
    private SelectListItem selectListItem;
    private String arrowProject = "//span[contains(@id,'--selectDPProject-arrow')]";
    private String arrowRelease = "//span[contains(@id,'--selectDPRelease-arrow')]";

    public FormsDeployPackage(WebDriver driver){
        this.driver = driver;
        this.basicControl = new BasicControl(driver);
        this.selectListItem = new SelectListItem(driver);
        this.accessBranch = new AccessBranch(driver);
    }

    public void createDeploymentPackage(String nameDP,String proyecto,String release) throws InterruptedException {
        listForm = FormsControl.controlNew(driver,"Paquete de Instalación","Deployment Package");
        listForm.get(0).click();
        listForm.get(0).sendKeys(nameDP);
        listForm.get(1).click();
        listForm.get(1).sendKeys(nameDP);

        driver.findElement(By.xpath(arrowProject)).click();
        selectListItem.SelectItemLi(proyecto);
        driver.findElement(By.xpath(arrowRelease)).click();
        Thread.sleep(1000);
        selectListItem.SelectItemLi(release);

        basicControl.btnSave();
    }


    public void editDeploymentPackage(String nameDP){
        listForm = FormsControl.controlNew(driver,"Paquete de Instalación","Deployment Package");

        listForm.get(0).click();
        listForm.get(0).clear();
        listForm.get(0).sendKeys(nameDP);

        listForm.get(1).click();
        listForm.get(1).clear();
        listForm.get(1).sendKeys(nameDP);

        basicControl.btnSave();
    }


    public void releaseDP(){
        basicControl = new BasicControl(driver);
        listForm = FormsControl.controlNew(driver,"Liberación","");
        driver.findElement(By.xpath("//span[contains(@id,'--cbDR-arrow') and @class='sapUiIcon sapUiIconMirrorInRTL sapUiIconPointer sapMInputBaseIcon']")).click();
        driver.findElement(By.xpath("//div[text()='DR_SELENIUM' and @class='sapMSLITitleOnly']")).click();
        basicControl.btnSave();
        driver.findElement(By.xpath("//bdi[text()='OK']")).click();
        accessBranch.clickBranches(2);
        accessBranch.clickBranches(3);
    }


}
