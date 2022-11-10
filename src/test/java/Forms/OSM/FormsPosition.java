package Forms.OSM;

import Helpers.BasicControl;
import Helpers.FormsControl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FormsPosition {

    private WebDriver driver;
    private List<WebElement> listForm;
    private BasicControl basicControl;

    public FormsPosition(WebDriver driver){
        this.driver = driver;
        basicControl = new BasicControl(driver);
    }

    public void formCreatePosition(String position)  {
        listForm = FormsControl.controlNew(driver,"Posición","Position");
        listForm.get(0).click();
        listForm.get(0).sendKeys(position);
        listForm.get(1).click();
        listForm.get(1).sendKeys(position);
        listForm.get(2).click();
        listForm.get(2).sendKeys("Posicion es " + position);
        basicControl.btnSave();
    }

    public void formEditPosition(String position) throws InterruptedException {
        listForm = FormsControl.controlEdit(driver,"Posición","Position");
        listForm.get(0).click();
        listForm.get(0).clear();
        listForm.get(0).sendKeys(position);
        listForm.get(1).click();
        listForm.get(1).clear();
        listForm.get(1).sendKeys(position);
        listForm.get(2).click();
        listForm.get(2).clear();
        listForm.get(2).sendKeys("Posicion es " + position);
        basicControl.btnSave();
    }

}
