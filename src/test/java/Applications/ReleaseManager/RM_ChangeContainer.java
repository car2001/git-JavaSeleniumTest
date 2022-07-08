package Applications.ReleaseManager;

import Forms.FormsRM;
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

import java.util.List;

public class RM_ChangeContainer {
    private WebDriver driver;
    private final String chosen_browser = "Chrome";

    Actions action;
    SelectBrowser browser = new SelectBrowser(driver);
    Home_Page login;
    Dynamic_Scroll_Search searchScrollElement;
    Asserts asserts;

    String componente = "Change Container";
    String newChangeContainer = "CC_SELENIUM";
    int exist = -1;


    @BeforeMethod
    public void setUp(){
        browser.chooseBrowser(chosen_browser);
        driver = browser.getDriver();
        login = new Home_Page(driver);
        action = new Actions(driver);
        asserts = new Asserts(driver);
        searchScrollElement = new Dynamic_Scroll_Search(driver);
        login.loginPage("cpingo", "1234");
        Login_Applications.loginRM(driver, componente);
    }

    @Test()
    public void crearChangeContainerArbol() throws InterruptedException {
        WebElement btnOpen = driver.findElement(By.xpath("//span[text()='Open']"));
        action.contextClick(btnOpen).perform();
        driver.findElement(By.xpath("//div[normalize-space()='New " + componente + "']")).click();
        Thread.sleep(1000);
        FormsRM.formCreateChangeContainer(driver,newChangeContainer);
    }

    @AfterMethod
    public void tearDown(){
        if (driver != null){
            //driver.quit();
        }
    }

}
