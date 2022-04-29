package Applications.OSM;

import Helpers.Dynamic_Scroll_Search;
import Helpers.FormsOSM;
import Helpers.Object_Save_Cancel;
import Helpers.SelectBrowser;
import HomepageFunctions.Home_Page;
import HomepageFunctions.Login_Applications;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

/**
 *
 * @author Carlos Alberto
 */

public class OSM_Location {
    private WebDriver driver;
    private String chosen_browser = "Chrome";

    Home_Page login;
    Dynamic_Scroll_Search searchScrollElement;
    Object_Save_Cancel save_cancel;
    SelectBrowser browser = new SelectBrowser(driver);
    Actions action;
    JavascriptExecutor js;

    String company = "Company Selenium";
    String location = "Location";
    String newLocation = "Location Selenium";
    String editLocation = "Location Selenium Editado";
    char decision;
    int exist = -1;
    String desple;


    @BeforeMethod
    public void setup() throws InterruptedException {
        browser.chooseBrowser(chosen_browser);
        driver = browser.getDriver();
        js = (JavascriptExecutor) driver;
        action = new Actions(driver);
        searchScrollElement = new Dynamic_Scroll_Search(driver);
        save_cancel = new Object_Save_Cancel(driver);
        login = new Home_Page(driver);
        login.homeSettings();
    }

    @Test(priority = 0)
    public void crearLocation() throws InterruptedException {
        login.loginPage("cpingo","1234");
        Login_Applications.loginOSM(driver);
        exist = searchScrollElement.elementSearch(company);
        if(exist != -1){
            desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
            driver.findElement(By.id(desple)).click();
            exist = searchScrollElement.elementSearch(location);
            if(exist !=-1){
                WebElement elementLocation = driver.findElement(By.xpath("//span[normalize-space()='"+location+"']"));
                action.contextClick(elementLocation).perform();
                driver.findElement(By.xpath("//div[normalize-space()='New "+location+"']")).click();
                Thread.sleep(1000);
                FormsOSM.formCreateLocation(driver,"Location Selenium");
                decision = 'G';
                save_cancel.save_Cancel(decision);
                Thread.sleep(2000);
                String message = driver.findElement(By.className("sapMMsgStripMessage")).getAttribute("textContent");
                Assert.assertEquals(message,"The Operation has been Completed Successfully."+ "\n");
            }else{
                System.out.println("No hay Location");
            }
        }else{
            System.out.println("No hay Company");
        }
    }


    @Test(priority = 1)
    public void doubleCheckLocation() throws InterruptedException {
        login.loginPage("cpingo","1234");
        Login_Applications.loginOSM(driver);
        exist = searchScrollElement.elementSearch(company);
        if(exist != -1){
            desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
            driver.findElement(By.id(desple)).click();
            exist = searchScrollElement.elementSearch(location);
            if(exist !=-1){
                WebElement elementLocation = driver.findElement(By.xpath("//span[normalize-space()='"+location+"']"));
                action.contextClick(elementLocation).perform();
                driver.findElement(By.xpath("//div[normalize-space()='New "+location+"']")).click();
                Thread.sleep(1000);
                //Llenando Formulario
                FormsOSM.formCreateLocation(driver,"Location Selenium");
                decision = 'G';
                save_cancel.save_Cancel(decision);
                Thread.sleep(2000);
                String message = driver.findElement(By.className("sapMMsgStripMessage")).getAttribute("textContent");
                Assert.assertEquals(message,"Location Already Exist");
            }else{
                System.out.println("No hay Location");
            }
        }else{
            System.out.println("No hay Company");
        }
    }


    @Test(priority = 2)
    public void viewLocationDependencies() throws InterruptedException {
        login.loginPage("cpingo","1234");
        Login_Applications.loginOSM(driver);
        exist = searchScrollElement.elementSearch(company);
        if(exist != -1){
            desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
            driver.findElement(By.id(desple)).click();
            exist = searchScrollElement.elementSearch(location);
            if(exist !=-1){
                driver.findElement(By.id(desple)).click();
                exist = searchScrollElement.elementSearch(newLocation);
                if(exist !=-1){
                    driver.findElement(By.xpath("//span[normalize-space()='"+newLocation+"']")).click();
                    Thread.sleep(100);
                    driver.findElement(By.id("__xmlview4--viewDependencies-img")).click();
                    Thread.sleep(2000);
                    String message = driver.findElement(By.id("__xmlview4--dependenciesTableTitle-inner")).getText();
                    Assert.assertEquals(message,"Dependencies List");
                    Thread.sleep(500);
                }else{
                    System.out.println("No hay"+ newLocation );
                }
            }else{
                System.out.println("No hay Location");
            }
        }else{
            System.out.println("No hay Company");
        }
    }


    @Test(priority = 3)
    public void editarLocation() throws InterruptedException {
        login.loginPage("cpingo","1234");
        Login_Applications.loginOSM(driver);
        exist = searchScrollElement.elementSearch(company);
        if(exist != -1){
            desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
            driver.findElement(By.id(desple)).click();
            exist = searchScrollElement.elementSearch(location);
            if(exist !=-1){
                driver.findElement(By.id(desple)).click();
                exist = searchScrollElement.elementSearch(newLocation);
                if(exist !=-1){
                    driver.findElement(By.xpath("//span[normalize-space()='"+newLocation+"']")).click();
                    Thread.sleep(100);
                    driver.findElement(By.id("__xmlview4--edit-img")).click(); // Editar Proyecto
                    Thread.sleep(500);
                    //Llenando Formulario
                    FormsOSM.formEditLocation(driver,editLocation);
                    decision = 'G';
                    save_cancel.save_Cancel(decision);
                    Thread.sleep(2000);
                }else{
                    System.out.println("No hay " +newLocation );
                }
            }else{
                System.out.println("No hay Location");
            }
        }else{
            System.out.println("No hay Company");
        }
    }

    @Test(priority = 4)
    public void eliminarLocation() throws InterruptedException {
        login.loginPage("cpingo","1234");
        Login_Applications.loginOSM(driver);
        exist = searchScrollElement.elementSearch(company);
        if(exist != -1){
            desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
            driver.findElement(By.id(desple)).click();
            exist = searchScrollElement.elementSearch(location);
            if(exist !=-1){
                driver.findElement(By.id(desple)).click();
                exist = searchScrollElement.elementSearch(editLocation);
                if(exist !=-1){
                    WebElement elementLocation = driver.findElement(By.xpath("//span[normalize-space()='"+editLocation+"']"));
                    action.contextClick(elementLocation).perform();
                    driver.findElement(By.xpath("//div[normalize-space()='Delete "+location+"']")).click();
                    Thread.sleep(2000);
                    driver.findElement(By.xpath("//bdi[normalize-space()='Si']")).click();
                    Thread.sleep(2000);

                    String message = driver.findElement(By.xpath("//span[@class='sapMText sapUiSelectable sapMTextMaxWidth sapMMsgBoxText']")).getText();
                    Thread.sleep(2000);
                    if(message.equals("The Operation has been Completed Successfully.")){
                        driver.findElement(By.xpath("//bdi[normalize-space()='OK']")).click();
                    }else{
                        driver.findElement(By.xpath("//bdi[normalize-space()='Cerrar']")).click();
                    }
                    Thread.sleep(100);
                }else{
                    System.out.println("No hay " +editLocation );
                }
            }else{
                System.out.println("No hay Location");
            }
        }else{
            System.out.println("No hay Company");
        }
    }

    @Test(priority = 5)
    public void create_Location_on_Location() throws InterruptedException {
        login.loginPage("cpingo","1234");
        Login_Applications.loginOSM(driver);
        String parentLocation = "Location Selenium Padre";
        String childLocation = "Location Selenium Hijo1.0";
        exist = searchScrollElement.elementSearch(company);
        if(exist != -1){
            desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
            driver.findElement(By.id(desple)).click();
            exist = searchScrollElement.elementSearch(location);
            if(exist !=-1){
                WebElement elementLocation = driver.findElement(By.xpath("//span[normalize-space()='"+location+"']"));
                action.contextClick(elementLocation).perform();
                driver.findElement(By.xpath("//div[normalize-space()='New "+location+"']")).click();
                Thread.sleep(1000);
                //Llenando Formulario Padre
                FormsOSM.formCreateLocation(driver,parentLocation);
                decision = 'G';
                save_cancel.save_Cancel(decision);
                Thread.sleep(2000);
                driver.findElement(By.id(desple)).click();
                exist = searchScrollElement.elementSearch(parentLocation);
                if(exist != -1){
                    driver.findElement(By.id(desple)).click();
                    exist = searchScrollElement.elementSearch(location);
                    if (exist !=-1){
                        WebElement scrollBar = driver.findElement(By.id("__xmlview4--mainTree-vsb"));
                        int clientHeight = js.executeScript("let barra = document.getElementById('__xmlview4--mainTree-vsb');return(barra.clientHeight)").hashCode();
                        int scrollHeight = js.executeScript("let barra = document.getElementById('__xmlview4--mainTree-vsb');return(barra.scrollHeight)").hashCode();
                        js.executeScript("arguments[0].scroll(0,'"+clientHeight*(scrollHeight/clientHeight)+1+"')",scrollBar);
                        List<WebElement> locationList = driver.findElements(By.xpath("//span[normalize-space()='"+location+"']"));
                        action.contextClick(locationList.get(1)).perform();
                        driver.findElement(By.xpath("//div[normalize-space()='New "+location+"']")).click();
                        Thread.sleep(1000);
                        //Llenando Formulario Hijo
                        FormsOSM.formCreateLocation(driver,childLocation);
                        decision = 'G';
                        save_cancel.save_Cancel(decision);
                        Thread.sleep(2000);
                    }else{
                        System.out.println("No hay Location2");
                    }
                }else{
                    System.out.println("No hay " +parentLocation);
                }

                Thread.sleep(2000);
            }else{
                System.out.println("No hay Location");
            }
        }else{
            System.out.println("No hay Company");
        }
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @AfterClass
    public static void tearDownAfterClass(){
        System.out.println("Terminaron los test");
    }
}