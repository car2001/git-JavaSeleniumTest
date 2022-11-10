package Forms.OSM;

import Helpers.BasicControl;
import Helpers.FormsControl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FormsOrgUnit {
    private WebDriver driver;
    private List<WebElement> listForm;
    private BasicControl basicControl;

    public FormsOrgUnit(WebDriver driver){
        this.driver = driver;
        basicControl = new BasicControl(driver);
    }

    public void formCreateOrganization(String organización){
        listForm = FormsControl.controlNew(driver,"Unidad de Organización","Organization Unit");
        listForm.get(0).click();
        listForm.get(0).sendKeys(organización);
        listForm.get(1).click();
        listForm.get(1).sendKeys(organización);
        listForm.get(2).click();
        listForm.get(2).sendKeys("Creado por " + organización);
        basicControl.btnSave();
    }

    public void formEditOrganization(String organización) throws InterruptedException{
        listForm = FormsControl.controlEdit(driver,"Unidad de Organización","Organization Unit");
        listForm.get(0).click();
        listForm.get(0).clear();
        listForm.get(0).sendKeys(organización);
        listForm.get(1).click();
        listForm.get(1).clear();
        listForm.get(1).sendKeys(organización);
        listForm.get(2).click();
        listForm.get(2).clear();
        listForm.get(2).sendKeys(organización);
        basicControl.btnSave();
    }

}
