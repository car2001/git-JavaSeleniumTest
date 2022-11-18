package Forms.SecurityManager;

import Helpers.BasicControl;
import Helpers.FormsControl;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FormsUserGroups {

    private WebDriver driver;
    private List<WebElement> listForm;
    private BasicControl basicControl;


    public FormsUserGroups(WebDriver driver){
        this.driver = driver;
        basicControl = new BasicControl(driver);
    }

    public void formCreateUserGroup(String user){
        basicControl.btnAdd();
        listForm = FormsControl.controlNew(driver,"Grupo de Usuarios","User Group");
        listForm.get(0).click();
        listForm.get(0).sendKeys(user);
        listForm.get(1).click();
        listForm.get(1).sendKeys(user);
        basicControl.btnSave();
    }

    public void formEditUserGroup(String editUser) throws InterruptedException {
        listForm = FormsControl.controlEdit(driver, "Grupo de Usuarios", "User Group");
        listForm.get(0).click();
        listForm.get(0).clear();
        listForm.get(0).sendKeys(editUser);
        listForm.get(1).click();
        listForm.get(1).clear();
        listForm.get(1).sendKeys(editUser);
        basicControl.btnSave();
    }

}
