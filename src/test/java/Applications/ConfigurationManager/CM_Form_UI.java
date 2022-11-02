package Applications.ConfigurationManager;


import Forms.ConfigurationManager.FormsFormUI;
import Helpers.Asserts;
import Helpers.BasicControl;
import Helpers.FormsControl;
import Helpers.SelectBrowser;
import HomePage.Login;
import HomePage.LoginApplications;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;


public class CM_Form_UI {
    private WebDriver driver;

    SelectBrowser browser;
    Asserts asserts;
    BasicControl basicControl;
    FormsFormUI formsFormUI;

    final String URL = "http://wedox.sytes.net/buplat_dev/";
    final String componente = "Form UI Configuration";
    final String newFormUI = "Form UI Selenium";
    final String editFormUI = "Form UI Selenium Editado";

    public CM_Form_UI(WebDriver driver){
        this.driver = driver;
        this.browser = new SelectBrowser(driver);
        this.asserts = new Asserts(driver);
        this.basicControl = new BasicControl(driver);
        this.formsFormUI = new FormsFormUI(driver);
    }


    @BeforeMethod
    public void setup() throws InterruptedException {
        basicControl.btn_More(componente);
    }


    @Test
    public void crear_FormUI(String formUI){
        basicControl.btn_More(componente);
        formsFormUI.formCreateFormUI(formUI);
        asserts.assertSave();
    }


    @Test
    public void editar_FormUI(String formUI,String formUI_edit) throws InterruptedException {
        basicControl.btn_More(componente);
        String xmlview = basicControl.getXmlview();
        driver.findElement(By.xpath("//div[@id='"+xmlview+"--listObject']//div[text()='"+formUI+"']")).click();
        formsFormUI.formEditFormUI(formUI_edit);
        asserts.assertSave();
    }

    @Parameters("delete_FormUI")
    @Test
    public void eliminar_FormUI(@Optional(editFormUI) String delete_FormUI){
        basicControl.btn_More(componente);
        FormsControl.controlDelete(driver,delete_FormUI);
        String xpathMessage = "//span[@class='sapMText sapUiSelectable sapMTextMaxWidth sapMMsgBoxText']";
        asserts.assertDelete(xpathMessage);
    }


}