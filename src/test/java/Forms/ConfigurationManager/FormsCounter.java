package Forms.ConfigurationManager;

import Helpers.BasicControl;
import Helpers.FormsControl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FormsCounter {
    private static List<WebElement> listForm;
    private static BasicControl basicControl;

    public static void formCreateCounter(WebDriver driver, String Counter, String inicio , String incremento){
        basicControl = new BasicControl(driver);
        basicControl.btnAdd();
        listForm = FormsControl.controlNew(driver,"contador","Counter");
        listForm.get(2).click();
        listForm.get(2).sendKeys(Counter);
        listForm.get(3).click();
        listForm.get(3).sendKeys(Counter);
        listForm.get(4).click();
        listForm.get(4).sendKeys(Counter);
        listForm.get(6).click();
        listForm.get(6).sendKeys(inicio);
        listForm.get(7).click();
        listForm.get(7).sendKeys(incremento);
        basicControl.btnSave();
    }


}
