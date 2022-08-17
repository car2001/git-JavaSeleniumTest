package Applications.ReleaseManager;

import Forms.FormsRM;
import Helpers.AccessBranch;
import Helpers.Asserts;
import Helpers.DynamicScroll;
import Helpers.SelectBrowser;
import HomePage.Login;
import HomePage.LoginApplications;
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
    Login login;
    DynamicScroll searchScrollElement;
    Asserts asserts;
    AccessBranch accessBranch;

    String componente = "Deployment Request";
    String newDR = "DR_SELENIUM";
    String project = "Proyecto Release Selenium";
    String release = "Release Selenium";

    @BeforeMethod
    public void setUp(){
        browser.chooseBrowser(chosen_browser);
        driver = browser.getDriver();
        login = new Login(driver);
        action = new Actions(driver);
        asserts = new Asserts(driver);
        searchScrollElement = new DynamicScroll(driver);
        accessBranch = new AccessBranch(driver);
        login.loginPage();
        LoginApplications.loginRM(driver, componente);
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
            driver.quit();
        }
    }

}
