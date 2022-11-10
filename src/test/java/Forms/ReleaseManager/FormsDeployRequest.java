package Forms.ReleaseManager;

import Helpers.BasicControl;
import Helpers.FormsControl;
import Helpers.SelectListItem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FormsDeployRequest {

    private WebDriver driver;
    private BasicControl basicControl;
    private List<WebElement> listForm;
    private SelectListItem selectListItem;
    private String arrowProject = "//span[contains(@id,'--selectDRProject-arrow')]";
    private String arrowRelease = "//span[contains(@id,'--selectDRRelease-arrow')]";

    public FormsDeployRequest(WebDriver driver){
        this.driver = driver;
        this.basicControl = new BasicControl(driver);
        this.selectListItem = new SelectListItem(driver);
    }

    public void createDeploymentRequest(String nameDR,String proyecto,String release) throws InterruptedException {
        listForm = FormsControl.controlNew(driver,"Solicitud de Instalación","Deployment Request");
        listForm.get(0).click();
        listForm.get(0).sendKeys(nameDR);
        listForm.get(1).click();
        listForm.get(1).sendKeys(nameDR);


        driver.findElement(By.xpath(arrowProject)).click();
        selectListItem.SelectItemLi(proyecto);

        driver.findElement(By.xpath(arrowRelease)).click();
        selectListItem.SelectItemLi(release);

        basicControl.btnSave();
    }

}
