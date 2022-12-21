package Applications.SecurityManager;

import Forms.SecurityManager.FormsRole;
import Forms.SecurityManager.FormsUser;
import Helpers.Asserts;
import Helpers.BasicControl;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class SM_User {

    private WebDriver driver;
    private Asserts asserts;
    private BasicControl basicControl;
    final String componente = "Users";
    private FormsUser formsUser;

    public SM_User(WebDriver driver){
        this.driver = driver;
        this.asserts = new Asserts(driver);
        this.basicControl = new BasicControl(driver);
        this.formsUser = new FormsUser(driver);
    }

    @Test
    public void crearUser(String user,String name,String lastName){
        basicControl.btn_More(componente);
        formsUser.formCreateUser(user,name,lastName);
    }
}
