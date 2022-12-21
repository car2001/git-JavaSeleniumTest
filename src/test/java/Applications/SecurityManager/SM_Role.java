package Applications.SecurityManager;

import Forms.SecurityManager.FormsRole;
import Helpers.Asserts;
import Helpers.BasicControl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class SM_Role {

    private WebDriver driver;
    private Asserts asserts;
    private BasicControl basicControl;
    final String componente = "Roles";
    private FormsRole formsRole;


    public SM_Role(WebDriver driver){
        this.driver = driver;
        this.asserts = new Asserts(driver);
        this.basicControl = new BasicControl(driver);
        this.formsRole = new FormsRole(driver);
    }

    @Test
    public void crearRole(String role) throws InterruptedException {
        basicControl.btn_More(componente);
        formsRole.formCreateRole(role);
        asserts.assertSave();
    }

    @Test
    public void editarRole(String role,String editRole) throws InterruptedException {
        basicControl.btn_More(componente);
        String xmlview = basicControl.getXmlview();
        driver.findElement(By.xpath("//div[@id='"+xmlview+"--listObject']//div[text()='"+role+"']")).click();
        formsRole.formEditRole(editRole);
        asserts.assertSave();
    }




}
