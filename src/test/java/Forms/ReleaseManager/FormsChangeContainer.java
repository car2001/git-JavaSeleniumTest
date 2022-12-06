package Forms.ReleaseManager;

import Helpers.AccessBranch;
import Helpers.BasicControl;
import Helpers.FormsControl;
import Helpers.SelectListItem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FormsChangeContainer {

    private WebDriver driver;
    private List<WebElement> listForm;
    private BasicControl basicControl;
    private SelectListItem selectListItem;
    private AccessBranch accessBranch;
    private String arrowProject = "//span[contains(@id,'--selectCCProject-arrow')]";
    private String arrowRelease = "//span[contains(@id,'--selectCCRelease-arrow')]";
    private String arrowOwner   = "//span[contains(@id,'--selectCCOwner-arrow')]";


    public FormsChangeContainer(WebDriver driver){
        this.driver = driver;
        this.basicControl = new BasicControl(driver);
        this.accessBranch = new AccessBranch(driver);
        this.selectListItem = new SelectListItem(driver);
    }


    public void createChangeContainer(String changeContainer, String proyecto, String release, String user) throws InterruptedException {
        listForm = FormsControl.controlNew(driver,"Contenedor de Cambios","Change Container");
        Thread.sleep(500);
        listForm.get(0).click();
        listForm.get(0).sendKeys(changeContainer);
        listForm.get(1).click();
        listForm.get(1).sendKeys(changeContainer);
        listForm.get(2).click();
        listForm.get(2).sendKeys(changeContainer);

        Thread.sleep(800);
        driver.findElement(By.xpath(arrowProject)).click();
        selectListItem.SelectItemLi(proyecto);

        Thread.sleep(800);
        driver.findElement(By.xpath(arrowRelease)).click();
        selectListItem.SelectItemLi(release);

        Thread.sleep(800);
        driver.findElement(By.xpath(arrowOwner)).click();
        selectListItem.SelectItemLi(user);

        basicControl.btnSave();
    }

    public void editChangeContainer(String changeContainer) throws InterruptedException {
        listForm = FormsControl.controlNew(driver,"Contenedor de Cambios","Change Container");
        Thread.sleep(500);
        listForm.get(0).click();
        listForm.get(0).clear();
        listForm.get(0).sendKeys(changeContainer);

        listForm.get(1).click();
        listForm.get(1).clear();
        listForm.get(1).sendKeys(changeContainer);

        listForm.get(2).click();
        listForm.get(2).clear();
        listForm.get(2).sendKeys(changeContainer);

        basicControl.btnSave();
    }

    public void releaseCC(){
        basicControl = new BasicControl(driver);
        listForm = FormsControl.controlNew(driver,"Liberación","");
        driver.findElement(By.xpath("//span[contains(@id,'--cbDP-arrow')]")).click();
        driver.findElement(By.xpath("//div[text()='DP_SELENIUM' and @class='sapMSLITitleOnly']")).click();
        basicControl.btnSave();
        driver.findElement(By.xpath("//bdi[text()='OK']")).click();
        accessBranch.clickBranches(1);
        accessBranch.clickBranches(2);
    }


}
