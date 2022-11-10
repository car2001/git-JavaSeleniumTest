package Applications.ReleaseManager;

import Forms.FormsRM;
import Forms.OSM.FormsLocation;
import Forms.ReleaseManager.FormsProject;
import Helpers.*;
import HomePage.Login;
import HomePage.LoginApplications;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static HomePage.LoginApplications.accessBranch;

public class RM_Project {

    private WebDriver driver;
    private DynamicScroll searchScrollElement;
    private AccessBranch accessBranch;
    private Actions action;
    private JavascriptExecutor js;
    private Asserts asserts;
    private BasicControl basicControl;
    private int exist = -1;
    private FormsProject formsProject;


    String componente = "Project";
    String newProject = "Proyecto Selenium2";
    String editProject = "Proyecto Selenium Editado";


    public  RM_Project(WebDriver driver){
        this.driver = driver;
        this.action = new Actions(driver);
        this.searchScrollElement = new DynamicScroll(driver);
        this.accessBranch = new AccessBranch(driver);
        this.js = (JavascriptExecutor) driver;
        this.asserts = new Asserts(driver);
        this.basicControl = new BasicControl(driver);
        this.formsProject = new FormsProject(driver);
    }


/*    @Test
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
    public void editarProyecto() throws InterruptedException {
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
    }*/
}
