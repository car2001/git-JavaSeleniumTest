package Forms.OSM;

import Helpers.BasicControl;
import Helpers.FormsControl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FormsLocation {
    private WebDriver driver;
    private List<WebElement> listForm;
    private BasicControl basicControl;

    public FormsLocation(WebDriver driver){
        this.driver = driver;
        basicControl = new BasicControl(driver);
    }

    public void formCreateLocation(String location){
        listForm = FormsControl.controlNew(driver,"Ubicación","Location");
        listForm.get(0).click();
        listForm.get(0).sendKeys(location);
        listForm.get(1).click();
        listForm.get(1).sendKeys(location);
        listForm.get(2).click();
        listForm.get(2).sendKeys("Location es" + location);
        basicControl.btnSave();
    }

    public void formEditLocation(String location) throws InterruptedException {
        listForm = FormsControl.controlEdit(driver,"Ubicación","Location");
        listForm.get(0).clear();
        listForm.get(0).click();
        listForm.get(0).sendKeys(location);
        listForm.get(1).clear();
        listForm.get(1).click();
        listForm.get(1).sendKeys(location);
        listForm.get(2).clear();
        listForm.get(2).click();
        listForm.get(2).sendKeys("Location es" + location);
        basicControl.btnSave();
    }

}
