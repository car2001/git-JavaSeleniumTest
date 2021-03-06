package Applications.ReleaseManager;

import Forms.FormsRM;
import Helpers.AccessBranches;
import Helpers.Asserts;
import Helpers.Dynamic_Scroll_Search;
import Helpers.SelectBrowser;
import HomepageFunctions.Home_Page;
import HomepageFunctions.Login_Applications;
import org.apache.hc.client5.http.psl.PublicSuffixList;
import org.openqa.selenium.By;
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
import java.util.List;

public class RM_Release  {
    private WebDriver driver;
    private final String chosen_browser = "Chrome";

    Actions action;
    SelectBrowser browser = new SelectBrowser(driver);
    Home_Page login;
    AccessBranches accessBranch;
    Dynamic_Scroll_Search searchScrollElement;
    Asserts asserts;

    String component = "Release";
    String project = "Proyecto Release Selenium";
    String newRelease = "Release Selenium";
    String editRelease = "Release Selenium Editado";
    int exist = -1;
    String desple;

    @BeforeMethod
    public void setUp(){
        browser.chooseBrowser(chosen_browser);
        driver = browser.getDriver();
        action = new Actions(driver);
        asserts = new Asserts(driver);
        accessBranch = new AccessBranches(driver);
        searchScrollElement = new Dynamic_Scroll_Search(driver);
        login = new Home_Page(driver);
        login.loginPage("cpingo","1234");
        Login_Applications.loginRM(driver,"Project");
    }

    @Test
    public void crearRelease() throws InterruptedException {
        crearProyecto(project);
        exist = searchScrollElement.elementSearch(project);
        if(exist != -1){
            accessBranch.clickBranches(exist);
            exist= searchScrollElement.elementSearch(component);
            if(exist != -1){
                WebElement release = driver.findElement(By.xpath("//span[normalize-space()='Release']"));
                action.contextClick(release).perform();
                driver.findElement(By.xpath("//div[normalize-space()='New " + component + "']")).click();
                FormsRM.formCreateRelease(driver,newRelease);
                asserts.assertSave();
            }else{
                Assert.assertEquals("No hay Release","Si hay Release");
            }
        }else{
            Assert.assertEquals("No hay Proyecto","Si hay Proyecto");
        }
    }

    @Test
    public void editarRelease(){
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
                    driver.findElement(By.xpath("//bdi[normalize-space()='Si']")).click();
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
                    driver.findElement(By.xpath("//bdi[normalize-space()='Si'][last()]")).click();
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

    public void crearProyecto(String proyecto){
        WebElement despleProyecto = driver.findElement(By.id("__xmlview4--mainTree-rows-row0-treeicon"));
        action.contextClick(despleProyecto).perform();
        driver.findElement(By.xpath("//div[normalize-space()='New Project']")).click();
        FormsRM.formCreateProject(driver, proyecto);
        asserts.assertSave();
    }


}
