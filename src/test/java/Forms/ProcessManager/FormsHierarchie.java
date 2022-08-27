package Forms.ProcessManager;


import Helpers.BasicControl;
import Helpers.FormsControl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import java.util.List;

public class FormsHierarchie {
    private static List<WebElement> listForm;
    private static BasicControl basicControl;

    public static void createNewHierarchie(WebDriver driver, String hierarchie){
        basicControl = new BasicControl(driver);
        listForm = FormsControl.controlNew(driver,"nivel","Level");
        listForm.get(2).click();
        listForm.get(2).sendKeys(hierarchie);
        listForm.get(3).click();
        listForm.get(3).sendKeys(hierarchie);
        listForm.get(4).click();
        listForm.get(4).sendKeys(hierarchie);
        basicControl.btnSave();
    }


}
