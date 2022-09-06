package Forms.ProcessManager;

import Helpers.BasicControl;
import Helpers.FormsControl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FormsActivityForm {

    private static List<WebElement> listForm;
    private static BasicControl basicControl;

    public static void createNewActivityForm(WebDriver driver , String nameAf){
        basicControl = new BasicControl(driver);
        listForm = FormsControl.controlNew(driver,"formulario de actividad","");
        listForm.get(2).click();
        listForm.get(2).sendKeys(nameAf);
        listForm.get(3).click();
        listForm.get(3).sendKeys(nameAf);
        listForm.get(4).click();
        listForm.get(4).sendKeys(nameAf);
        basicControl.btnSave();
    }

}
