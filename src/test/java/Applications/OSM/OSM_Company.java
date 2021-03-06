package Applications.OSM;

import Forms.FormsOSM;
import Helpers.*;
import HomepageFunctions.Home_Page;
import HomepageFunctions.Login_Applications;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class OSM_Company {

    private WebDriver driver;
    private String chosen_browser = "Chrome";

    Home_Page login;
    Dynamic_Scroll_Search searchScrollElement;
    AccessBranches accessBranch;
    SelectBrowser browser = new SelectBrowser(driver);
    Actions action;
    JavascriptExecutor js;
    Asserts asserts;

    String component = "Company";
    String newCompany = "Company Selenium";
    String editCompany = "Company Selenium Editado";

    int exist = -1;


    @BeforeMethod
    public void setup(){
        browser.chooseBrowser(chosen_browser);
        driver = browser.getDriver();
        js = (JavascriptExecutor) driver;
        action = new Actions(driver);
        searchScrollElement = new Dynamic_Scroll_Search(driver);
        asserts = new Asserts(driver);
        accessBranch = new AccessBranches(driver);
        login = new Home_Page(driver);
        login.loginPage("cpingo","1234");
        Login_Applications.loginOSM(driver);
    }

    @Test()
    public void crearCompany(){
        WebElement element = driver.findElement(By.xpath("//span[normalize-space()='Company']"));
        action.contextClick(element).perform();
        driver.findElement(By.xpath("//div[normalize-space()='New " + component + "']")).click();
        FormsOSM.formCreateCompany(driver,newCompany);
        asserts.assertSave();
    }

    @Test
    public void doubleCheckCompany(){
        WebElement element = driver.findElement(By.xpath("//span[normalize-space()='Company']"));
        action.contextClick(element).perform();
        driver.findElement(By.xpath("//div[normalize-space()='New " + component + "']")).click();
        FormsOSM.formCreateCompany(driver,newCompany);
        asserts.assertDoubleCheck("Company Already Exist");
        driver.findElement(By.id("__xmlview4--cancel-img")).click();
    }

    @Test
    public void viewCompanyDependencies(){
        exist = searchScrollElement.elementSearch(newCompany);
        if (exist != -1){
            driver.findElement(By.xpath("//span[normalize-space()='"+newCompany+"']")).click();
            driver.findElement(By.id("__xmlview4--viewDependencies-img")).click();
            asserts.assertDependecies(4);
        }else{
            Assert.assertEquals("No hay Company","The Operation has been Completed Successfully.");
        }
    }


    @Test
    public void editarCompany(){
        exist = searchScrollElement.elementSearch(newCompany);
        if (exist != -1){
            driver.findElement(By.xpath("//span[normalize-space()='"+newCompany+"']")).click();
            FormsOSM.formEditCompany(driver,editCompany);
            asserts.assertSave();
        }else{
            Assert.assertEquals("No hay Company","The Operation has been Completed Successfully.");
        }
    }

    @Test
    public void eliminarCompany()  {
        exist = searchScrollElement.elementSearch(newCompany);
        if (exist != -1){
            WebElement empresa = driver.findElement(By.xpath("//span[normalize-space()='"+newCompany+"']"));
            String xpathMessage = "//span[@class='sapMText sapUiSelectable sapMTextMaxWidth sapMMsgBoxText']";
            FormsControl.controlDelete(driver,action,empresa,"Company");
            asserts.assertDelete(xpathMessage);
        }else{
            Assert.assertEquals("No hay Company","The Operation has been Completed Successfully.");
        }
    }

}
