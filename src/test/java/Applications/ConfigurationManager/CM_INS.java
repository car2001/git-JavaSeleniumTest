package Applications.ConfigurationManager;


import Forms.ConfigurationManager.FormsINS;
import Helpers.Asserts;
import Helpers.FormsControl;
import Helpers.SelectBrowser;
import HomePage.Login;
import HomePage.LoginApplications;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class CM_INS {

    private WebDriver driver;
    private final String chosen_browser = "Chrome";

    SelectBrowser browser = new SelectBrowser(driver);
    Login login;
    Asserts asserts;

    final String componente = "Instance Numbering Schemas";
    final String newINS = "INS Selenium2";
    final String editINS = "INS Selenium Editado";
    final String separator = "-";
    final String fixedValue = "SELENIUM";
    final String Counter = "Counter Selenium2";


    @BeforeMethod
    public void setup() throws InterruptedException {
        browser.chooseBrowser(chosen_browser);
        driver = browser.getDriver();
        asserts = new Asserts(driver);
        login = new Login(driver);
        login.loginPage();
        LoginApplications.loginCM(driver,componente);
    }

    @Parameters({"INS","separador","valorFijo","counter"})
    @Test
    public void crearINS(@Optional(newINS) String INS, @Optional(separator) String separador , @Optional(fixedValue) String valorFijo , @Optional(Counter) String counter){
        FormsINS.formCreateINS(driver,INS,separador,valorFijo,counter);
        asserts.assertSave();
    }

    @Parameters("INS")
    @Test
    public void eliminarINS(@Optional(newINS) String INS){
        FormsControl.controlDelete(driver,INS);
        String xpathMessage = "//span[@class='sapMText sapUiSelectable sapMTextMaxWidth sapMMsgBoxText']";
        asserts.assertDelete(xpathMessage);
    }

    @AfterMethod
    public void tearDown(){
        if (driver != null){
            driver.quit();
        }
    }

}
