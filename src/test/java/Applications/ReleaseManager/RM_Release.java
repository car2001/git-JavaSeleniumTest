package Applications.ReleaseManager;

import Forms.FormsRM;
import Forms.ReleaseManager.FormsProject;
import Forms.ReleaseManager.FormsRelease;
import Helpers.*;
import HomePage.Login;
import HomePage.LoginApplications;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class RM_Release {
    private WebDriver driver;
    private DynamicScroll searchScrollElement;
    private AccessBranch accessBranch;
    private Actions action;
    private JavascriptExecutor js;
    private Asserts asserts;
    private BasicControl basicControl;
    private int exist = -1;
    private FormsRelease formsRelease;

    public RM_Release(WebDriver driver) {
        this.driver = driver;
        this.action = new Actions(driver);
        this.searchScrollElement = new DynamicScroll(driver);
        this.accessBranch = new AccessBranch(driver);
        this.js = (JavascriptExecutor) driver;
        this.asserts = new Asserts(driver);
        this.basicControl = new BasicControl(driver);
        this.formsRelease = new FormsRelease(driver);
    }


    @Test
    public void crearRelease(String project,String release) throws InterruptedException {
        exist = searchScrollElement.elementSearch(project);
        if (exist != -1) {
            accessBranch.clickBranches(exist);
            exist = searchScrollElement.elementSearch("Releases");
            if (exist != -1) {
                WebElement elementRelease = driver.findElement(By.xpath("//span[normalize-space()='Releases']"));
                action.contextClick(elementRelease).perform();
                driver.findElement(By.xpath("//div[text()='New Release' or text()='Nueva Liberación']")).click();
                formsRelease.createRelease(release);
                asserts.assertSave();
            } else {
                Assert.assertEquals("No hay Release", "Si hay Release");
            }
        } else {
            Assert.assertEquals("No hay Proyecto", "Si hay Proyecto");
        }
    }
}


/*
    @Test
    public void editarRelease() throws InterruptedException {
        exist = searchScrollElement.elementSearch(project);
        if(exist != -1){
            accessBranch.clickBranches(exist);
            exist= searchScrollElement.elementSearch(component);
            if(exist != -1){
                accessBranch.clickBranches(exist);
                exist=searchScrollElement.elementSearch(newRelease);
                if(exist !=-1){
                    driver.findElement(By.xpath("//span[normalize-space()='"+newRelease+"']")).click();
                    FormsRM.formEditRelease(driver,editRelease);
                    asserts.assertSave();
                    accessBranch.clickBranches(0);
                }else{
                    Assert.assertEquals("No hay " + newRelease,"Si hay Release");
                }
            }else{
                Assert.assertEquals("No hay Release","Si hay Release");
            }
        }else{
            Assert.assertEquals("No hay Proyecto","Si hay Proyecto");
        }
    }

    @Test
    public void eliminarRelease(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        exist = searchScrollElement.elementSearch(project);
        if(exist != -1){
            accessBranch.clickBranches(exist);
            exist= searchScrollElement.elementSearch(component);
            if(exist != -1){
                accessBranch.clickBranches(exist);
                exist=searchScrollElement.elementSearch(editRelease);
                if(exist !=-1){
                    WebElement release = driver.findElement(By.xpath("//span[normalize-space()='"+editRelease+"']"));
                    action.contextClick(release).perform();
                    driver.findElement(By.xpath("//div[normalize-space()='Delete "+component+"']")).click();
                    driver.findElement(By.xpath("//bdi[normalize-space()='Sí']")).click();
                    String xpathMessage = "//span[@class='sapMText sapUiSelectable sapMTextMaxWidth sapMMsgBoxText']";
                    driver.findElement(By.xpath("//bdi[normalize-space()='OK']")).click();
                    asserts.assertDelete(xpathMessage);
                    //-------espera que el pop-up de eliminar desaparezca
                    WebElement popUp = driver.findElement(By.id("sap-ui-blocklayer-popup"));
                    wait.until(ExpectedConditions.invisibilityOf(popUp));
                    //--------------------------
                    WebElement spanProject = driver.findElement(By.xpath("//span[normalize-space()='"+project+"']"));
                    action.contextClick(spanProject).perform();
                    driver.findElement(By.xpath("//div[normalize-space()='Delete Project']")).click();
                    driver.findElement(By.xpath("//bdi[normalize-space()='Sí'][last()]")).click();
                    driver.findElement(By.xpath("//bdi[normalize-space()='OK'][last()]")).click();
                }else{
                    Assert.assertEquals("No hay " + newRelease,"Si hay Release");
                }
            }else{
                Assert.assertEquals("No hay Release","Si hay Release");
            }
        }else{
            Assert.assertEquals("No hay Proyecto","Si hay Proyecto");
        }
    }

    @AfterMethod
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }

*/

/*
    public void crearProyecto(String proyecto){
        WebElement despleProyecto = driver.findElement(By.id("__xmlview4--mainTree-rows-row0-treeicon"));
        action.contextClick(despleProyecto).perform();
        driver.findElement(By.xpath("//div[normalize-space()='New Project']")).click();
        FormsRM.formCreateProject(driver, proyecto);
        asserts.assertSave();
    }
*/



