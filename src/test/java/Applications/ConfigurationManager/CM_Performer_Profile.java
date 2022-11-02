package Applications.ConfigurationManager;


import Forms.ConfigurationManager.FormsPerformerProfile;
import Helpers.Asserts;
import Helpers.BasicControl;
import Helpers.FormsControl;
import Helpers.SelectBrowser;
import HomePage.Login;
import HomePage.LoginApplications;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class CM_Performer_Profile {
    private WebDriver driver;

    SelectBrowser browser;
    Asserts asserts;
    BasicControl basicControl;
    FormsPerformerProfile formsPerformerProfile;

    final String componente = "Performer Profiles";
    final String newPerformer = "Performer Selenium";
    final String editPerformer = "Performer Edit Selenium";

    public CM_Performer_Profile(WebDriver driver){
        this.driver = driver;
        this.browser = new SelectBrowser(driver);
        this.asserts = new Asserts(driver);
        this.basicControl = new BasicControl(driver);
        this.formsPerformerProfile = new FormsPerformerProfile(driver);
    }


    @BeforeMethod
    public void SetUp(){
        basicControl.btn_More(componente);
    }

    @Parameters("PP")
    @Test
    public void crearPerformerProfile(@Optional(newPerformer) String PP){
        basicControl.btn_More(componente);
        formsPerformerProfile.formCreatePerformer(PP);
        asserts.assertSave();
    }

    @Parameters({"PP","PP_edit"})
    @Test
    public void editarPerformerProfile(String PP, String PP_edit ) throws InterruptedException {
        basicControl.btn_More(componente);
        String xmlview = basicControl.getXmlview();
        driver.findElement(By.xpath("//div[@id='"+xmlview+"--listObject']//div[text()='"+PP+"']")).click();
        formsPerformerProfile.formEditPerformer(PP_edit);
        asserts.assertSave();
    }

    @Parameters("delete_PP")
    @Test
    public void eliminarPerformerProfile(@Optional(editPerformer) String delete_PP){
        basicControl.btn_More(componente);
        FormsControl.controlDelete(driver,delete_PP);
        String xpathMessage = "//span[@class='sapMText sapUiSelectable sapMTextMaxWidth sapMMsgBoxText']";
        asserts.assertDelete(xpathMessage);
    }

}