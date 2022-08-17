package Applications.OSM;

import Forms.FormsOSM;
import Helpers.*;
import HomePage.Login;
import HomePage.LoginApplications;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class OSM_Company {
    private WebDriver driver;
    private String chosen_browser = "Chrome";

    Login login;
    DynamicScroll searchScrollElement;
    AccessBranch accessBranch;
    SelectBrowser browser = new SelectBrowser(driver);
    Actions action;
    JavascriptExecutor js;
    Asserts asserts;
    BasicControl basicControl;


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
        searchScrollElement = new DynamicScroll(driver);
        asserts = new Asserts(driver);
        basicControl = new BasicControl(driver);
        accessBranch = new AccessBranch(driver);
        login = new Login(driver);
        login.loginPage();
        LoginApplications.loginOSM(driver);
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
    }

    @Test
    public void viewCompanyDependencies(){
        exist = searchScrollElement.elementSearch(newCompany);
        if (exist != -1){
            driver.findElement(By.xpath("//span[normalize-space()='"+newCompany+"']")).click();
            basicControl.btnDependecies();
            asserts.assertDependecies();
        }else{
            Assert.assertEquals("No hay Company","The Operation has been Completed Successfully.");
        }
    }


    @Test
    public void editarCompany() throws InterruptedException {
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
