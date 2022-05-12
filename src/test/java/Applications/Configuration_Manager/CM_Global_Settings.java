package Applications.Configuration_Manager;


import Helpers.SelectBrowser;
import HomepageFunctions.Home_Page;
import HomepageFunctions.Login_Applications;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class CM_Global_Settings {
    private WebDriver driver;
    private String chosen_browser = "Chrome";

    SelectBrowser browser = new SelectBrowser(driver);
    Home_Page login;

    @BeforeMethod
    public void setUp(){
        browser.chooseBrowser(chosen_browser);
        driver = browser.getDriver();
        login = new Home_Page(driver);
        login.loginPage("jjuarez","1234");
    }

    @Test
    public void ocultarAplicaciones(){
        loginApplications();
        Login_Applications.loginCM(driver,"Global Settings");
        ocultarApliaciones();
    }


    @AfterMethod
    public void tearDown(){
        if (driver != null){
            ///driver.quit();
        }
    }


    public void loginApplications(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("__image0")));
        driver.findElement(By.id("__image0")).click();
    }

    public void ocultarApliaciones(){
        driver.findElement(By.id("__xmlview5--edit-img")).click();
        List<WebElement> botones = driver.findElements(By.className("sapMSwtHandle"));
        botones.get(1).click();
        driver.findElement(By.id("__xmlview5--save-img")).click();
    }




}
