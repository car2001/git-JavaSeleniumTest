package test;

import Applications.ReleaseManager.RM_ChangeContainer;
import Applications.ReleaseManager.RM_Project;
import Applications.ReleaseManager.RM_Release;
import Helpers.*;
import HomePage.Login;
import HomePage.LoginApplications;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestRM {
    private WebDriver driver;
    private String chosen_browser = "Chrome";
    private Login login;
    private DynamicScroll searchScrollElement;
    private SelectBrowser browser = new SelectBrowser(driver);
    private AccessBranch accessBranch;
    private JavascriptExecutor js;
    private Asserts asserts;
    private BasicControl basicControl;
    private RM_Project rmProject;
    private RM_ChangeContainer rmChangeContainer;
    private RM_Release rmRelease;

    @BeforeTest
    public void setup(){
        browser.chooseBrowser(chosen_browser);
        driver = browser.getDriver();
        login = new Login(driver);
        asserts = new Asserts(driver);
        js = (JavascriptExecutor) driver;
        basicControl = new BasicControl(driver);
        searchScrollElement = new DynamicScroll(driver);
        accessBranch = new AccessBranch(driver);
        rmProject = new RM_Project(driver);
        rmChangeContainer = new RM_ChangeContainer(driver);
        rmRelease = new RM_Release(driver);
        //Iniciamos Sesión
        login.loginPage();
        LoginApplications.loginRM(driver,"xd");
    }

    @Test
    public void testReleaseManager() throws InterruptedException {
        lifeCycleProject();
        //lifeCycleRelease();
        lifeCycleChangeContainer();
    }

    public void lifeCycleProject() throws InterruptedException {
        rmProject.crearProyectoYRelease("Proyecto-1");
        rmProject.editarProyecto("Proyecto-1","Proyecto-20");
        rmProject.eliminarProyecto("Proyecto-20");
        accessBranch.clickBranches(searchScrollElement.elementSearch("Projects"));
    }

    public void lifeCycleRelease() throws InterruptedException {
        rmProject.crearProyectoYRelease("Proyecto-1");
        rmRelease.crearRelease("Proyecto-1","Release-1");
        rmRelease.editarRelease("Proyecto-1","Release-1","Release-20");
        rmRelease.eliminarRelease("Proyecto-1","Release-20");
        accessBranch.clickBranches(searchScrollElement.elementSearch("Projects"));
    }

    public void lifeCycleChangeContainer() throws InterruptedException {
        rmProject.crearProyectoYRelease("Proyecto-1");
        rmRelease.crearRelease("Proyecto-1","Release-1");
        accessBranch.clickBranches(searchScrollElement.elementSearch("Projects"));
        rmChangeContainer.crearChangeContainerArbol("CC-WE1", "Proyecto-1", "Release-1", login.getUser());
    }


}
