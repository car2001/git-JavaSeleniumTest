package Applications.SecurityManager;

import Forms.SecurityManager.FormsUserGroups;
import Helpers.Asserts;
import Helpers.BasicControl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class SM_UserGroup {

    private WebDriver driver;
    private Asserts asserts;
    private BasicControl basicControl;
    final String componente = "User Groups";
    private FormsUserGroups formsUserGroups;

    public SM_UserGroup(WebDriver driver){
        this.driver = driver;
        this.asserts = new Asserts(driver);
        this.basicControl = new BasicControl(driver);
        this.formsUserGroups = new FormsUserGroups(driver);
    }

    @Test
    public void crearUserGroup(String userGroup){
        basicControl.btn_More(componente);
        formsUserGroups.formCreateUserGroup(userGroup);
    }

    @Test
    public void editarUserGroup(String userGroup,String editUserGroup) throws InterruptedException {
        basicControl.btn_More(componente);
        String xmlview = basicControl.getXmlview();
        driver.findElement(By.xpath("//div[@id='"+xmlview+"--listObject']//div[text()='"+userGroup+"']")).click();
        formsUserGroups.formEditUserGroup(editUserGroup);
    }
}
