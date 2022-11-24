package Applications.ReleaseManager;

import Forms.ReleaseManager.FormsProject;
import Helpers.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

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
    private RM_Release rmRelease;

    public RM_Project(WebDriver driver) {
        this.driver = driver;
        this.action = new Actions(driver);
        this.searchScrollElement = new DynamicScroll(driver);
        this.accessBranch = new AccessBranch(driver);
        this.js = (JavascriptExecutor) driver;
        this.asserts = new Asserts(driver);
        this.basicControl = new BasicControl(driver);
        this.formsProject = new FormsProject(driver);
        this.rmRelease = new RM_Release(driver);
    }

    @Test
    public void crearProyectoConRelease(String proyecto) throws InterruptedException {
        exist = searchScrollElement.elementSearch("Projects");
        if (exist != -1) {
            accessBranch.clickBranches(exist);
            WebElement elementProject = driver.findElement(By.xpath("//span[text()='Projects']"));
            action.contextClick(elementProject).perform();
            driver.findElement(By.xpath("//div[text()='New Project' or text()='Nuevo Proyecto']")).click();
            formsProject.createProject(proyecto);
            asserts.assertSave();
        } else {
            Assert.assertEquals("No hay Projects", "Si hay Projects");
        }
    }


    @Test
    public void editarProyecto(String proyecto, String editProject) throws InterruptedException {
        exist= searchScrollElement.elementSearch(proyecto);
        if(exist !=-1){
            driver.findElement(By.xpath("//span[normalize-space()='"+proyecto+"']")).click();
            formsProject.editProject(editProject);
            asserts.assertSave();
        }else{
            Assert.assertEquals("No hay "+proyecto, "Si hay Proyecto");
        }
    }


    @Test
    public void eliminarProyecto(String proyecto){
        exist= searchScrollElement.elementSearch(proyecto);
        if(exist !=-1){
            WebElement btnEditProject = driver.findElement(By.xpath("//span[normalize-space()='"+proyecto+"']"));
            String xpathMessage = "//span[@class='sapMText sapUiSelectable sapMTextMaxWidth sapMMsgBoxText']";
            FormsControl.controlDelete(driver,action,btnEditProject,"Project","Proyecto");
            asserts.assertDelete(xpathMessage);
        }else{
            Assert.assertEquals("No hay "+proyecto, "Si hay Proyecto");
        }
    }

    @Test
    public void crearProyectoSinRelease(String proyecto) throws InterruptedException {
        exist = searchScrollElement.elementSearch("Projects");
        if (exist != -1) {
            accessBranch.clickBranches(exist);
            WebElement elementProject = driver.findElement(By.xpath("//span[text()='Projects']"));
            action.contextClick(elementProject).perform();
            driver.findElement(By.xpath("//div[text()='New Project' or text()='Nuevo Proyecto']")).click();
            formsProject.createProjectWithoutRelease(proyecto);
            asserts.assertSave();
            String stateRelease = estadoRelease(proyecto);
            Assert.assertEquals(stateRelease,"No hay Release");
            eliminarProyecto(proyecto);
        } else {
            Assert.assertEquals("No hay Projects", "Si hay Projects");
        }
    }



    private String estadoRelease(String proyecto){
        exist= searchScrollElement.elementSearch(proyecto);
        if(exist !=-1){
            exist=searchScrollElement.elementSearch("Releases");
            if(exist !=-1){
                 return "Si hay Release" ;
            }else{
                return "No hay Release" ;
            }
        }else{return "No hay Proyecto" ;}
    }


}
