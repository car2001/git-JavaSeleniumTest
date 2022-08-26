package Applications.ConfigurationManager;

import Forms.ConfigurationManager.FormsCounter;
import Helpers.Asserts;
import Helpers.FormsControl;
import Helpers.SelectBrowser;
import HomePage.Login;
import HomePage.LoginApplications;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class CM_Counter {
    private WebDriver driver;
    private final String chosen_browser = "Chrome";
    final String URL = "http://wedox.sytes.net/buplat_config/";

    SelectBrowser browser = new SelectBrowser(driver);
    Login login;
    Asserts asserts;

    final String componente = "Counter ";
    final String newCounter = "Counter Selenium";
    final String start = "100";
    final String increment = "1";



    @Parameters("url")
    @BeforeMethod
    public void setup(@Optional(URL) String url) throws InterruptedException {
        browser.chooseBrowser(chosen_browser);
        driver = browser.getDriver();
        asserts = new Asserts(driver);
        login = new Login(driver);
        login.loginPage();
        LoginApplications.loginCM(driver,componente);
    }

    @Parameters({"counter","inicio","incremento"})
    @Test
    public void crearCounter (@Optional(newCounter) String counter, @Optional(start) String inicio, @Optional(increment) String aumento)
    {
        FormsCounter.formCreateCounter(driver,counter,inicio,aumento);
        asserts.assertSave();
    }

    @Parameters("counter")
    @Test
    public void eliminarCounter(@Optional(newCounter) String counter){
        FormsControl.controlDelete(driver,counter);
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
