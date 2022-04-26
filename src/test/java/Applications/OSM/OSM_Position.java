package Applications.OSM;

import Helpers.Dynamic_Scroll_Search;
import Helpers.Object_Save_Cancel;
import Helpers.SelectBrowser;
import HomepageFunctions.Home_Page;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Carlos Alberto
 */

public class OSM_Position {
    private WebDriver driver;
    private String url = "http://wedox.sytes.net/buplat_dev/";
    private String chosen_browser = "Chrome";

    Home_Page login;
    Dynamic_Scroll_Search searchScrollElement;
    Object_Save_Cancel save_cancel;
    SelectBrowser browser = new SelectBrowser(driver);

    @BeforeMethod
    public void setup() throws InterruptedException {
        driver = browser.chooseBrowser(chosen_browser);
        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(4000);
    }

    @Test
    public void crearLocation() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Actions action = new Actions(driver);
        login = new Home_Page(driver);
        login.loginPage("cpingo","1234");
        Thread.sleep(4000);
    }

    @AfterMethod
    public void tearDown(){

    }


}
