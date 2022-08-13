package Applications.ReleaseManager;

import Forms.FormsRM;
import Helpers.AccessBranches;
import Helpers.Asserts;
import Helpers.Dynamic_Scroll_Search;
import Helpers.SelectBrowser;
import HomepageFunctions.Home_Page;
import HomepageFunctions.Login_Applications;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RM_DeploymentRequest {

    private WebDriver driver;
    private final String chosen_browser = "Chrome";

    Actions action;
    SelectBrowser browser = new SelectBrowser(driver);
    Home_Page login;
    Dynamic_Scroll_Search searchScrollElement;
    Asserts asserts;
    AccessBranches accessBranch;

    String componente = "Deployment Request";
    String newDR = "DR_SELENIUM";
    String project = "Proyecto Release Selenium";
    String release = "Release Selenium";

    @BeforeMethod
    public void setUp(){
        browser.chooseBrowser(chosen_browser);
        driver = browser.getDriver();
        login = new Home_Page(driver);
        action = new Actions(driver);
        asserts = new Asserts(driver);
        searchScrollElement = new Dynamic_Scroll_Search(driver);
        accessBranch = new AccessBranches(driver);
        login.loginPage();
        Login_Applications.loginRM(driver, componente);
    }

    @Test
    public void createDeploymentRequest() throws InterruptedException {
        WebElement btnOpen = driver.findElement(By.xpath("//span[text()='Open']"));
        action.contextClick(btnOpen).build().perform();
        driver.findElement(By.xpath("//div[normalize-space()='New " + componente + "']")).click();
        FormsRM.formDeploymentRequest(driver,newDR,project,release);
        asserts.assertSaveDR();
    }


    @AfterMethod
    public void tearDown(){
        if (driver != null){
            //driver.quit();
        }
    }

}
