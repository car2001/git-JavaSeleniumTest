package test;


import Applications.OSM.*;
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
    private OSM_Location osmLocation;
    private OSM_Organizational_Unit osmOrganizationalUnit;
    private OSM_ExternalPartner osmExternalPartner;
    private OSM_Position osmPosition;


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
        osmLocation = new OSM_Location(driver);
        osmExternalPartner = new OSM_ExternalPartner(driver);
        osmOrganizationalUnit = new OSM_Organizational_Unit(driver);
        osmPosition = new OSM_Position(driver);

        //Iniciamos Sesión
        login.loginPage();
        LoginApplications.loginOSM(driver);
    }

    @Test
    public void testOSM() throws InterruptedException {
        crearComponentesOSM();
        doubleCheckOSM();
        viewDependenciesOSM();
        editarComponentesOSM();
        eliminarComponentesOSM();
        lifeCycleExternal();
    }

    public void crearComponentesOSM() throws InterruptedException {
        osmCompany.crearCompany("Coca-Cola");
        osmLocation.crearLocation("Coca-Cola","Av.Wedox");
        osmOrganizationalUnit.crearOrgani_Unit("Coca-Cola","QA");
        lifeCyclePosition();
    }

    public void doubleCheckOSM(){
        osmCompany.doubleCheckCompany("Coca-Cola");
        osmLocation.doubleCheckLocation("Coca-Cola","Av.Wedox");
        osmOrganizationalUnit.doubleCheckOrgani_Unit("Coca-Cola","QA");
    }

    public void viewDependenciesOSM() {
        osmCompany.viewCompanyDependencies("Coca-Cola");
        osmLocation.viewLocationDependencies("Coca-Cola","Av.Wedox");
        osmOrganizationalUnit.viewOrgani_UnitDependencies("Coca-Cola","QA");

    }

    public void editarComponentesOSM() throws InterruptedException {
        osmLocation.editarLocation("Coca-Cola","Av.Wedox","Av.Buplat");
        osmOrganizationalUnit.editarOrgani_Unit("Coca-Cola","QA","QA-2");
        osmCompany.editarCompany("Coca-Cola","Coca-2");


    }

    public void eliminarComponentesOSM(){
        osmLocation.eliminarLocation("Coca-2","Av.Buplat");
        osmOrganizationalUnit.eliminarOrgani_Unit("Coca-2","QA-2");
        osmCompany.eliminarCompany("Coca-2");
    }

    public void lifeCyclePosition() throws InterruptedException {
        osmPosition.crearPosition("Coca-Cola","QA","Tester");
        osmPosition.viewPositionDependencies("Coca-Cola","QA","Tester");
        osmPosition.doubleCheckPosition("Coca-Cola","QA","Tester");
        osmPosition.editarPosition("Coca-Cola","QA","Tester","Tester Manual");
        osmPosition.eliminarPosition("Coca-Cola","QA","Tester Manual");
    }

    public void lifeCycleExternal() throws InterruptedException {
        osmExternalPartner.crearExternalPartner("Nuevo ExPe", login.getUser());
        Thread.sleep(1000);
        osmExternalPartner.editarExternalPartner("Nuevo ExPe", login.getUser(),"Edit ExPe");
        Thread.sleep(1000);
        osmExternalPartner.eliminarExternalPartner("Edit ExPe", login.getUser());
    }

}
