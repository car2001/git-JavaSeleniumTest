package Forms.OSM;

import Helpers.BasicControl;
import Helpers.FormsControl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FormsCompany {
    private WebDriver driver;
    private List<WebElement> listForm;
    private BasicControl basicControl;

    public FormsCompany(WebDriver driver){
        this.driver = driver;
        basicControl = new BasicControl(driver);
    }

    public void formCreateCompany(String company) {
        listForm = FormsControl.controlNew(driver,"Empresa","Company");
        listForm.get(0).click();
        listForm.get(0).sendKeys(company);
        listForm.get(1).click();
        listForm.get(1).sendKeys(company);
        listForm.get(2).click();
        listForm.get(2).sendKeys("Compañia Creada en Selenium");
        listForm.get(3).click();
        listForm.get(3).sendKeys("123456");
        basicControl.btnSave();
    }

    public void formEditCompany(String company) throws InterruptedException {
        listForm = FormsControl.controlEdit(driver,"Empresa","Company");
        listForm.get(0).click();
        listForm.get(0).clear();
        listForm.get(0).sendKeys(company);
        listForm.get(1).click();
        listForm.get(1).clear();
        listForm.get(1).sendKeys(company);
        listForm.get(2).click();
        listForm.get(2).clear();
        listForm.get(2).sendKeys("Compañia Editada en Selenium");
        listForm.get(3).click();
        listForm.get(3).clear();
        listForm.get(3).sendKeys("123456");
        basicControl.btnSave();
    }


}
