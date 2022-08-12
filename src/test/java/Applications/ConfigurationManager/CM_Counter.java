package Applications.ConfigurationManager;

import Forms.FormsCM;
import Helpers.Asserts;
import Helpers.FormsControl;
import Helpers.SelectBrowser;
import HomepageFunctions.Home_Page;
import HomepageFunctions.Login_Applications;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CM_Counter {
    private WebDriver driver;
    private final String chosen_browser = "Chrome";

    SelectBrowser browser = new SelectBrowser(driver);
    Home_Page login;
    Asserts asserts;

    String componente = "Counter ";
    String newCounter = "Counter Selenium lol";
    String editCounter = "Counter Selenium Editado";
    String inicio = "0";
    String incremento = "1";

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
    public void crearCounter(){
        FormsCM.formCreateCounter(driver,newCounter,inicio,incremento);
        asserts.assertSave();
    }

    @Test
    public void eliminarCounter(){
        FormsControl.controlDelete(driver,newCounter);
        String xpathMessage = "//span[@class='sapMText sapUiSelectable sapMTextMaxWidth sapMMsgBoxText']";
        asserts.assertDelete(xpathMessage);
    }
}
