package Applications.ReleaseManager;

import Helpers.SelectBrowser;
import HomepageFunctions.Home_Page;
import HomepageFunctions.Login_Applications;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RM_Project {
    WebDriver driver;
    private final String chosen_browser = "Chrome";

    SelectBrowser browser = new SelectBrowser(driver);
    Home_Page login;

    String componente = "Project";


    @BeforeMethod
    public void setUp(){
        browser.chooseBrowser(chosen_browser);
        driver = browser.getDriver();
        login = new Home_Page(driver);
        login.loginPage("cpingo","1234");
        Login_Applications.loginRM(driver,componente);
    }

    @Test
    public void crearProyecto(){
        driver.findElement(By.id("__text46-__clone1")).click();
    }

    @Test
    public void editarProyecto(){

    }


    @AfterMethod
    public void tearDown(){
        //driver.quit();
    }



}
