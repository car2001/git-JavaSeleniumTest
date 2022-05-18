package Applications.ReleaseManager;

import Forms.FormsRM;
import Helpers.Asserts;
import Helpers.Dynamic_Scroll_Search;
import Helpers.SelectBrowser;
import HomepageFunctions.Home_Page;
import HomepageFunctions.Login_Applications;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class RM_Project {
    private WebDriver driver;
    private final String chosen_browser = "Chrome";

    WebDriverWait wait;
    Actions action;
    SelectBrowser browser = new SelectBrowser(driver);
    Home_Page login;
    Dynamic_Scroll_Search searchScrollElement;
    Asserts asserts;

    String componente = "Project";
    String newProject = "Proyecto Selenium";
    String editProject = "Proyecto Selenium Editado";
    int exist = -1;
    String desple;


    @BeforeMethod
    public void setUp() throws InterruptedException {
        browser.chooseBrowser(chosen_browser);
        driver = browser.getDriver();
        login = new Home_Page(driver);
        action = new Actions(driver);
        asserts = new Asserts(driver);
        searchScrollElement = new Dynamic_Scroll_Search(driver);
        login.loginPage("cpingo","1234");
        Login_Applications.loginRM(driver,componente);
    }

    @Test
    public void crearProyecto(){
        WebElement proyecto = driver.findElement(By.id("__xmlview4--mainTree-rows-row0-treeicon"));
        action.contextClick(proyecto).perform();
        driver.findElement(By.xpath("//div[normalize-space()='New "+componente+"']")).click();
        FormsRM.formCreateProject(driver,newProject);
        asserts.assertSave();
    }

    @Test
    public void editarProyecto(){
        exist= searchScrollElement.elementSearch(newProject);
        if(exist !=-1){
            driver.findElement(By.xpath("//span[normalize-space()='"+newProject+"']")).click();
            FormsRM.formEditProject(driver,editProject);
            asserts.assertSave();
        }else{
            System.out.println("No hay " + newProject);
        }
    }

    @Test
    public void verifyRelease(){
        exist= searchScrollElement.elementSearch(editProject);
        if(exist !=-1){
            desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
            driver.findElement(By.id(desple)).click();
            exist=searchScrollElement.elementSearch("Release");
            if(exist !=-1){
                Assert.assertEquals("Si hay Release","Si hay Release");
            }else{Assert.assertEquals("No hay Release","Si hay Release");}
        }else{
            System.out.println("No hay " + newProject);
        }
    }

    @Test
    public void eliminarProject(){
        exist= searchScrollElement.elementSearch(editProject);
        if(exist !=-1){

            //WebElement editProject = driver.findElement(By.xpath("//span[normalize-space()='"+newProject+"']"));
            action.contextClick(editProject).perform();
            driver.findElement(By.xpath("//div[normalize-space()='Delete "+componente+"']")).click();
            driver.findElement(By.xpath("//bdi[normalize-space()='Si']")).click();
            String xpathMessage = "//span[@class='sapMText sapUiSelectable sapMTextMaxWidth sapMMsgBoxText']";
            driver.findElement(By.xpath("//bdi[normalize-space()='OK']")).click();
            asserts.assertDelete(xpathMessage);
        }else{
            System.out.println("No hay " + newProject);
        }
    }

    @AfterMethod
    public void tearDown(){
        if (driver != null){
            driver.quit();
        }
    }
}
