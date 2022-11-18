package Forms.SecurityManager;

import Helpers.BasicControl;
import Helpers.FormsControl;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FormsRole {
    private WebDriver driver;
    private List<WebElement> listForm;
    private BasicControl basicControl;
    private String useAttributes = "//div[contains(@id,'--useAttributesRole-handle')]";
    private String isComposite = "//div[contains(@id,'--isCompositeRole-handle')]";

    public FormsRole(WebDriver driver){
        this.driver = driver;
        basicControl = new BasicControl(driver);
    }

    public void formCreateRole(String role){
        basicControl.btnAdd();
        listForm = FormsControl.controlNew(driver,"Rol","Role");
        listForm.get(0).click();
        listForm.get(0).sendKeys(role);
        listForm.get(1).click();
        listForm.get(1).sendKeys(role);
        listForm.get(2).click();
        listForm.get(2).sendKeys(role);
        basicControl.btnSave();
    }

    public void formEditRole(String role) throws InterruptedException {
        listForm = FormsControl.controlEdit(driver,"Rol","Role");
        listForm.get(0).click();
        listForm.get(0).clear();
        listForm.get(0).sendKeys(role);
        listForm.get(1).click();
        listForm.get(1).clear();
        listForm.get(1).sendKeys(role);
        listForm.get(2).click();
        listForm.get(2).clear();
        listForm.get(2).sendKeys(role);
        basicControl.btnSave();
    }


}
