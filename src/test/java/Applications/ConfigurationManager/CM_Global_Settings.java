package Applications.ConfigurationManager;


import Helpers.SelectBrowser;
import HomePage.Login;
import HomePage.LoginApplications;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class CM_Global_Settings {
    private WebDriver driver;
    private String chosen_browser = "Chrome";

    SelectBrowser browser = new SelectBrowser(driver);
    Login login;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp(){
        browser.chooseBrowser(chosen_browser);
        driver = browser.getDriver();
        login = new Login(driver);
        login.loginPage("jjuarez","1234");
    }

    @Test
    public void checkHideAplicaciones(){
        loginApplication();
        LoginApplications.loginCM(driver,"Global Settings");
        hideApps();
        loginApplication();
        findHiddenApps();
        LoginApplications.loginCM(driver,"Global Settings");
        restoreApps();
    }


    @AfterMethod
    public void tearDown(){
        if (driver != null){
            ///driver.quit();
        }
    }


    public void loginApplication(){
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("__image0")));
        driver.findElement(By.id("__image0")).click();
    }

    public void hideApps(){
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.findElement(By.id("__xmlview5--edit-img")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("__xmlview5--save-img")));
        List<WebElement> botones = driver.findElements(By.className("sapMSwtCont"));
        if(botones.get(7).getAttribute("aria-checked").equals("true")){
            botones.get(7).click();
        }
        if(botones.get(9).getAttribute("aria-checked").equals("true")){
            botones.get(9).click();
        }
        driver.findElement(By.id("__xmlview5--save-img")).click();
        driver.findElement(By.id("__xmlview2--avatarUser")).click();
        driver.findElement(By.xpath(" //bdi[text()='Disconnect']")).click();
        login.loginPage("jjuarez","1234");
    }

    public void findHiddenApps(){
        wait.until(ExpectedConditions.elementToBeClickable(By.id("__image1")));
        List<WebElement> aplicaciones = driver.findElements(By.xpath("//span[@class='sapMTextMaxLine sapMTextLineClamp']"));
        if(aplicaciones.contains("Integration Studio") && aplicaciones.contains("Technical Administrator")){
            Assert.assertEquals("Error","No se Ocultaron");
        }else{
            Assert.assertEquals("Se oculto","Se oculto");
        }
    }

    public void restoreApps(){
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.findElement(By.id("__xmlview5--edit-img")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("__xmlview5--save-img")));
        List<WebElement> botones = driver.findElements(By.className("sapMSwtCont"));
        if(botones.get(7).getAttribute("aria-checked").equals("false")){
            botones.get(7).click();
        }
        if(botones.get(9).getAttribute("aria-checked").equals("false")){
            botones.get(9).click();
        }
        driver.findElement(By.id("__xmlview5--save-img")).click();
    }







}
