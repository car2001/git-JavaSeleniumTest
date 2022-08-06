package Applications.ConfigurationManager;

import Forms.FormsCM;
import Helpers.Asserts;
import Helpers.FormsControl;
import Helpers.SelectBrowser;
import HomepageFunctions.Home_Page;
import HomepageFunctions.Login_Applications;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CM_INS {

    private WebDriver driver;
    private final String chosen_browser = "Chrome";

    SelectBrowser browser = new SelectBrowser(driver);
    Home_Page login;
    Asserts asserts;

    String componente = "Instance Numbering Schema";
    String newINS = "INS Selenium";
    String editINS = "INS Selenium Editado";
    String separador = "-";
    String fixedValue = "SELENIUM";
    String counter = "Counter Selenium";


    @BeforeMethod
    public void setup() throws InterruptedException {
        browser.chooseBrowser(chosen_browser);
        driver = browser.getDriver();
        asserts = new Asserts(driver);
        login = new Home_Page(driver);
        login.loginPage();
        Login_Applications.loginCM(driver,componente);
    }


    @Test
    public void crearINS(){
        FormsCM.formCreateINS(driver,newINS,separador,fixedValue,counter);
        asserts.assertSave();
    }

    @Test
    public void eliminarINS(){
        FormsControl.controlDelete(driver,newINS);
        String xpathMessage = "//span[@class='sapMText sapUiSelectable sapMTextMaxWidth sapMMsgBoxText']";
        asserts.assertDelete(xpathMessage);
    }

}
