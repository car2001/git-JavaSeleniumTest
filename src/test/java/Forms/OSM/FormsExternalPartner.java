package Forms.OSM;

import Helpers.BasicControl;
import Helpers.FormsControl;
import Helpers.SelectListItem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FormsExternalPartner {
    private WebDriver driver;
    private List<WebElement> listForm;
    private BasicControl basicControl;
    private SelectListItem selectListItem;
    private String arrowItems ="//span[contains(@id,'-arrow')][@class='sapUiIcon sapUiIconMirrorInRTL sapUiIconPointer sapMInputBaseIcon']";

    public FormsExternalPartner(WebDriver driver){
        this.driver = driver;
        this.basicControl = new BasicControl(driver);
        this.selectListItem = new SelectListItem(driver);
    }

    public void formCreateExternalPartner(String externalPartner,String usuario){
        listForm = FormsControl.controlNew(driver,"Socio Externo","External Partner");
        listForm.get(0).click();
        listForm.get(0).sendKeys(externalPartner);
        listForm.get(1).click();
        listForm.get(1).sendKeys(externalPartner);
        listForm.get(2).click();
        listForm.get(2).sendKeys(externalPartner);
        listForm.get(3).click();
        listForm.get(3).sendKeys(externalPartner);
        listForm.get(4).click();
        listForm.get(4).sendKeys(externalPartner);
        listForm.get(5).click();
        listForm.get(5).sendKeys(externalPartner);

        driver.findElements(By.xpath(arrowItems)).get(0).click();
        selectListItem.SelectItemDiv("Customer");

        driver.findElements(By.xpath(arrowItems)).get(1).click();
        selectListItem.SelectItemDiv(usuario);

        listForm.get(6).click();
        listForm.get(6).sendKeys("https://wedox.co");
        listForm.get(7).click();
        listForm.get(7).sendKeys("https://wedox.co");
        listForm.get(8).click();
        listForm.get(8).sendKeys("https://wedox.co");
        listForm.get(9).click();
        listForm.get(9).sendKeys("test4@wedox.co");

        driver.findElements(By.xpath(arrowItems)).get(2).click();
        selectListItem.SelectItemDiv("Perú");

        listForm.get(10).click();
        listForm.get(10).sendKeys("Trujillo");
        listForm.get(11).click();
        listForm.get(11).sendKeys("15003");
        listForm.get(12).click();
        listForm.get(12).sendKeys("Jhoser");
        listForm.get(13).click();
        listForm.get(13).sendKeys("Eduardo");
        listForm.get(14).click();
        listForm.get(14).sendKeys("test4@wedox.co");
        listForm.get(15).click();
        listForm.get(15).sendKeys("test");
        listForm.get(16).click();
        listForm.get(16).sendKeys("963852741");
        basicControl.btnSave();
    }

    public void formEditExternalPartner(String externalPartner) throws InterruptedException {
        listForm = FormsControl.controlNew(driver,"Socio Externo","External Partner");
        listForm.get(0).click();
        listForm.get(0).clear();
        listForm.get(0).sendKeys(externalPartner);
        listForm.get(1).click();
        listForm.get(1).clear();
        listForm.get(1).sendKeys(externalPartner);
        listForm.get(2).click();
        listForm.get(2).clear();
        listForm.get(2).sendKeys(externalPartner);
        listForm.get(3).click();
        listForm.get(3).clear();
        listForm.get(3).sendKeys(externalPartner);
        listForm.get(4).click();
        listForm.get(4).clear();
        listForm.get(4).sendKeys(externalPartner);
        listForm.get(5).click();
        listForm.get(5).clear();
        listForm.get(5).sendKeys(externalPartner);
        basicControl.btnSave();
    }

}
