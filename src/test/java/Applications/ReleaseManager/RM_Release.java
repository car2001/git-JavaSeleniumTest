package Applications.ReleaseManager;

import Forms.ReleaseManager.FormsRelease;
import Helpers.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;



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
    public void crearRelease(String project, String release) throws InterruptedException {
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


    @Test
    public void editarRelease(String project, String release,String editRelease) throws InterruptedException {
        exist = searchScrollElement.elementSearch(project);
        if(exist != -1){
            exist= searchScrollElement.elementSearch("Releases");
            if(exist != -1){
                accessBranch.clickBranches(exist);
                exist=searchScrollElement.elementSearch(release);
                if(exist !=-1){
                    driver.findElement(By.xpath("//span[normalize-space()='"+release+"']")).click();
                    formsRelease.editRelease(editRelease);
                    asserts.assertSave();
                }else{
                    Assert.assertEquals("No hay " + release,"Si hay Release");
                }
            }else{
                Assert.assertEquals("No hay Releases","Si hay Release");
            }
        }else{
            Assert.assertEquals("No hay Proyecto","Si hay Proyecto");
        }
    }


    @Test
    public void eliminarRelease(String project, String release){
        exist = searchScrollElement.elementSearch(project);
        if(exist != -1){
            exist= searchScrollElement.elementSearch("Releases");
            if(exist != -1){
                exist=searchScrollElement.elementSearch(release);
                if(exist !=-1){
                    WebElement elementRelease = driver.findElement(By.xpath("//span[normalize-space()='"+release+"']"));
                    String xpathMessage = "//span[@class='sapMText sapUiSelectable sapMTextMaxWidth sapMMsgBoxText']";
                    FormsControl.controlDelete(driver,action,elementRelease,"Release","Liberación");
                    asserts.assertDelete(xpathMessage);
                }else{
                    Assert.assertEquals("No hay " + release,"Si hay Release");
                }
            }else{
                Assert.assertEquals("No hay Release","Si hay Release");
            }
        }else{
            Assert.assertEquals("No hay Proyecto","Si hay Proyecto");
        }
    }




}





