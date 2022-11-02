package test;


import Applications.OSM.OSM_Company;
import Helpers.*;
import HomePage.Login;
import HomePage.LoginApplications;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestOSM {
    private WebDriver driver;
    private String chosen_browser = "Chrome";

    private Login login;
    private DynamicScroll searchScrollElement;
    private SelectBrowser browser = new SelectBrowser(driver);
    private Actions action;
    private JavascriptExecutor js;
    private Asserts asserts;
    private BasicControl basicControl;
    private OSM_Company osmCompany;


    @BeforeTest
    public void setup(){
        browser.chooseBrowser(chosen_browser);
        driver = browser.getDriver();
        login = new Login(driver);
        asserts = new Asserts(driver);
        js = (JavascriptExecutor) driver;
        basicControl = new BasicControl(driver);
        searchScrollElement = new DynamicScroll(driver);
        osmCompany = new OSM_Company(driver);
        //Iniciamos Sesión
        login.loginPage();
        LoginApplications.loginOSM(driver);
    }

    @Test
    public void testOSM() throws InterruptedException {



    }

    public void crearComponentesOSM(){
        osmCompany.crearCompany("Coca-Cola");
        osmCompany.doubleCheckCompany("Coca-Cola");
        osmCompany.viewCompanyDependencies("Coca-Cola");
    }

    public void editarComponentesOSM() throws InterruptedException {
        osmCompany.editarCompany("Coca-Cola","Coca-2");
    }

    public void eliminarComponentesOSM(){
        osmCompany.eliminarCompany("Coca-2");
    }

}
