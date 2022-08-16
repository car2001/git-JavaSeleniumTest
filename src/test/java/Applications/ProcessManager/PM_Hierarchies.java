package Applications.ProcessManager;

import Forms.FormsPM;
import Helpers.*;
import HomepageFunctions.Home_Page;
import HomepageFunctions.Login_Applications;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PM_Hierarchies {

    private WebDriver driver;
    private final String chosen_browser = "Chrome";

    Actions action;
    SelectBrowser browser = new SelectBrowser(driver);
    Home_Page login;
    AccessBranches accessBranch;
    Dynamic_Scroll_Search searchScrollElement;
    Asserts asserts;

    String component = "Process Hierarchies";
    String nameLevel = "Jerarquia Selenium";



    @BeforeMethod
    public void setUp() throws InterruptedException {
        browser.chooseBrowser(chosen_browser);
        driver = browser.getDriver();
        action = new Actions(driver);
        asserts = new Asserts(driver);
        accessBranch = new AccessBranches(driver);
        searchScrollElement = new Dynamic_Scroll_Search(driver);
        login = new Home_Page(driver);
        login.loginPage();
        Login_Applications.loginPM(driver);
    }

    @Test
    public void crearHierarchies() throws InterruptedException {
        WebElement hierarchies = driver.findElement(By.xpath("//span[text()='"+component+"']"));
        action.contextClick(hierarchies).perform();
        driver.findElement(By.xpath("//div[normalize-space()='New Level']")).click();
        Thread.sleep(1000);
        FormsPM.createNewHierarchie(driver,nameLevel);
        asserts.assertSave();
    }

    @Test
    public void eliminarHierarchies(){
        int xpos = searchScrollElement.elementSearch(nameLevel);
        if(xpos != -1){
            WebElement hierarchie = driver.findElement(By.xpath("//span[text()='"+nameLevel+"']"));
            FormsControl.controlDelete(driver,action,hierarchie,"Level");
        }else{
            Assert.assertEquals("No se encontro la jerarquia","NO");
        }
    }


}
