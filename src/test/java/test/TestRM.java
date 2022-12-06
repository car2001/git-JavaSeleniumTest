package test;

import Applications.ReleaseManager.RM_ChangeContainer;
import Applications.ReleaseManager.RM_DeploymentPackage;
import Applications.ReleaseManager.RM_Project;
import Applications.ReleaseManager.RM_Release;
import Helpers.*;
import HomePage.Login;
import HomePage.LoginApplications;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
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
    private RM_DeploymentPackage rmDeploymentPackage;

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
        rmDeploymentPackage = new RM_DeploymentPackage(driver);
        //Iniciamos Sesión
        login.loginPage();
        LoginApplications.loginRM(driver,"xd");
    }

    @Test
    public void testReleaseManager() throws InterruptedException {
        lifeCycleProject();
        lifeCycleRelease();
        lifeCycleChangeContainer();
        //lifeCycleDeploymentPackage();
    }

    public void lifeCycleProject() throws InterruptedException {
        rmProject.crearProyectoConRelease("Proyecto-1");
        rmProject.editarProyecto("Proyecto-1","Proyecto-20");
        rmProject.eliminarProyecto("Proyecto-20");
        accessBranch.clickBranches(searchScrollElement.elementSearch("Projects"));
    }

    public void lifeCycleRelease() throws InterruptedException {
        rmProject.crearProyectoConRelease("Proyecto-1");
        rmRelease.crearRelease("Proyecto-1","Release-1");
        rmRelease.editarRelease("Proyecto-1","Release-1","Release-20");
        rmRelease.eliminarRelease("Proyecto-1","Release-20");
        rmProject.eliminarProyecto("Proyecto-1");
        accessBranch.clickBranches(searchScrollElement.elementSearch("Projects"));
    }

    public void lifeCycleChangeContainer() throws InterruptedException {
        rmProject.crearProyectoConRelease("Proyecto-1");
        rmRelease.crearRelease("Proyecto-1","Release-1");
        accessBranch.clickBranches(searchScrollElement.elementSearch("Projects"));
        rmChangeContainer.crearChangeContainerArbol("CC-WE1", "Proyecto-1", "Release-1", login.getUser());
        rmChangeContainer.editarChangeContainerTabla("Proyecto-1","CC-WE20", login.getUser(), "Release-1" ,"CC-WE1");
        rmChangeContainer.eliminarChangeContainerTabla("Proyecto-1", login.getUser(), "Release-1" ,"CC-WE20");
        accessBranch.clickBranches(searchScrollElement.elementSearch("Change Containers"));
        accessBranch.clickBranches(searchScrollElement.elementSearch("Projects"));
        accessBranch.clickBranches(searchScrollElement.elementSearch("Proyecto-1"));
        accessBranch.clickBranches(searchScrollElement.elementSearch("Releases"));
        rmRelease.eliminarRelease("Proyecto-1","Release-1");
        rmProject.eliminarProyecto("Proyecto-1");
        accessBranch.clickBranches(searchScrollElement.elementSearch("Projects"));
    }

    public void lifeCycleDeploymentPackage() throws InterruptedException {
        rmProject.crearProyectoConRelease("Proyecto-1");
        rmRelease.crearRelease("Proyecto-1","Release-1");
        rmDeploymentPackage.crearDeploymentPackageArbol("DP-1","Proyecto-1","Release-1");

    }






}
