package Forms.SecurityManager;

import Helpers.BasicControl;
import Helpers.FormsControl;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FormsUser {
    private WebDriver driver;
    private List<WebElement> listForm;
    private BasicControl basicControl;


    public FormsUser(WebDriver driver){
        this.driver = driver;
        basicControl = new BasicControl(driver);
    }

    public void formCreateUser(String user){
        basicControl.btnAdd();
        listForm = FormsControl.controlNew(driver,"Usuario","User");

    }

}
