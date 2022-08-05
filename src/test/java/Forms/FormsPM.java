package Forms;

import Helpers.FormsControl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FormsPM {
    private static List<WebElement> listForm;
    private static String save = "__xmlview4--save-img";
    private static String edit= "__xmlview4--edit-img";
    private static String version = "__xmlview4--newVersion-inner";
    private static String versionHistory = "__xmlview4--versionHistory-img";
    private static String num = "4";

    public static void createNewHierarchie(WebDriver driver,String hierarchie){
        listForm = FormsControl.controlNewWithoutFocus(driver,"nivel",num);
        listForm.get(2).click();
        listForm.get(2).sendKeys(hierarchie);
        listForm.get(3).click();
        listForm.get(3).sendKeys(hierarchie);
        listForm.get(4).click();
        listForm.get(4).sendKeys(hierarchie);
        driver.findElement(By.id(save)).click();
    }
}
