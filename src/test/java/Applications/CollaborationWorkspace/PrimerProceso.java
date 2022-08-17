package Applications.CollaborationWorkspace;

import Forms.FormsColl;
import Helpers.*;
import HomePage.Login;
import HomePage.LoginApplications;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PrimerProceso {

    private WebDriver driver;
    private String chosen_browser = "Chrome";

    Login login;
    DynamicScroll searchScrollElement;
    SelectBrowser browser = new SelectBrowser(driver);
    Actions action;
    JavascriptExecutor js;
    Asserts asserts;
    BasicControl basicControl;

    private String nameProcess = "Proceso Selenium";
    String urlQA = "http://wedox.sytes.net/buplat_QA/";
    String urlPROD = "http://wedox.sytes.net/buplat/";

    @BeforeMethod
    public void setUp() {
        browser.chooseBrowser(chosen_browser);
        driver = browser.getDriver();
        login = new Login(driver);
        action = new Actions(driver);
        asserts = new Asserts(driver);
        js = (JavascriptExecutor) driver;
        basicControl = new BasicControl(driver);
        searchScrollElement = new DynamicScroll(driver);
        login.loginPage();
        LoginApplications.loginColl(driver, nameProcess);
    }

    @Test
    public void runProcessSi() {
        basicControl.claim();
        FormsColl.primerProcesoSi(driver);
    }

    @Test
    public void runProcessNo(){
        basicControl.claim();
        FormsColl.primerProcesoNo(driver);
    }


    @Test
    public void runProcessSiQA() {
        login.loginPage(urlQA);
        LoginApplications.loginColl(driver, nameProcess);
        basicControl.claim();
        FormsColl.primerProcesoSi(driver);
    }

    @Test
    public void runProcessNoQA(){
        login.loginPage(urlQA);
        LoginApplications.loginColl(driver, nameProcess);
        basicControl.claim();
        FormsColl.primerProcesoNo(driver);
    }

    @Test
    public void runProcessSiPROD() {
        login.loginPage(urlPROD);
        LoginApplications.loginColl(driver, nameProcess);
        basicControl.claim();
        FormsColl.primerProcesoSi(driver);
    }

    @Test
    public void runProcessNoPROD(){
        login.loginPage(urlPROD);
        LoginApplications.loginColl(driver, nameProcess);
        basicControl.claim();
        FormsColl.primerProcesoNo(driver);
    }

    @AfterMethod
    public void tearDown(){
        if (driver != null){
            driver.quit();
        }
    }

}

