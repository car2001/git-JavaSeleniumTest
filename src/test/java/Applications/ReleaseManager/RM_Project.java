package Applications.ReleaseManager;

import Forms.FormsRM;
import Helpers.Asserts;
import Helpers.Dynamic_Scroll_Search;
import Helpers.FormsControl;
import Helpers.SelectBrowser;
import HomepageFunctions.Home_Page;
import HomepageFunctions.Login_Applications;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static HomepageFunctions.Login_Applications.accessBranch;

public class RM_Project {
    private WebDriver driver;
    private final String chosen_browser = "Chrome";


    Actions action;
    SelectBrowser browser = new SelectBrowser(driver);
    Home_Page login;
    Dynamic_Scroll_Search searchScrollElement;
    Asserts asserts;

    String componente = "Project";
    String newProject = "Proyecto Selenium";
    String editProject = "Proyecto Selenium Editado";
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

    @Test
    public void crearProyecto() {
        WebElement proyecto = driver.findElement(By.id("__xmlview4--mainTree-rows-row0-treeicon"));
        action.contextClick(proyecto).perform();
        driver.findElement(By.xpath("//div[normalize-space()='New " + componente + "']")).click();
        FormsRM.formCreateProject(driver, newProject);
        asserts.assertSave();
    }

    @Test(priority = 1)
    public void verifyRelease() {
        String stateRelease = estadoRelease(newProject);
        Assert.assertEquals(stateRelease, "Si hay Release");
    }

    @Test(priority = 2)
    public void editarProyecto(){
        exist= searchScrollElement.elementSearch(newProject);
        if(exist !=-1){
            driver.findElement(By.xpath("//span[normalize-space()='"+newProject+"']")).click();
            FormsRM.formEditProject(driver,editProject);
            asserts.assertSave();
        }else{ Assert.assertEquals("No hay "+newProject, "Si hay Proyecto");}
    }


    @Test(priority = 3)
    public void eliminarProyecto(){
        metodoEliminarProyecto(editProject);
    }

    @Test(priority = 4)
    public void crearProyectoSinRelease(){
        String projectWithoutRelease = "Proyecto Sin Release Selenium";
        WebElement proyecto = driver.findElement(By.id("__xmlview4--mainTree-rows-row0-treeicon"));
        action.contextClick(proyecto).perform();
        driver.findElement(By.xpath("//div[normalize-space()='New "+componente+"']")).click();
        FormsRM.formCreateProjectWithoutRelease(driver,projectWithoutRelease);
        asserts.assertSave();
        String stateRelease = estadoRelease(projectWithoutRelease);
        Assert.assertEquals(stateRelease,"No hay Release");
        metodoEliminarProyecto(projectWithoutRelease);
    }

    @AfterMethod
    public void tearDown(){
        if (driver != null){
            //driver.quit();
        }
    }

    public String estadoRelease(String proyecto){
        exist= searchScrollElement.elementSearch(proyecto);
        if(exist !=-1){
            accessBranch.clickBranches(exist);
            exist=searchScrollElement.elementSearch("Release");
            if(exist !=-1){
                 return "Si hay Release" ;
            }else{
                return "No hay Release" ;
            }
        }else{return "No hay Proyecto" ;}
    }

    public void metodoEliminarProyecto(String proyecto){
        exist= searchScrollElement.elementSearch(proyecto);
        if(exist !=-1){
            WebElement btnEditProject = driver.findElement(By.xpath("//span[normalize-space()='"+proyecto+"']"));
            String xpathMessage = "//span[@class='sapMText sapUiSelectable sapMTextMaxWidth sapMMsgBoxText']";
            FormsControl.controlDelete(driver,action,btnEditProject,componente);
            asserts.assertDelete(xpathMessage);
        }else{Assert.assertEquals("No hay "+proyecto, "Si hay Proyecto");}
    }
}
