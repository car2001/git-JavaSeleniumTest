package Applications.OSM;

import Helpers.Dynamic_Scroll_Search;
import Helpers.Object_Save_Cancel;
import Helpers.SelectBrowser;
import HomepageFunctions.Home_Page;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 *
 * @author Carlos Alberto
 */

public class OSM_Location {
    private WebDriver driver;
    private String url = "http://wedox.sytes.net/buplat_dev/";
    private String chosen_browser = "Chrome";

    Home_Page login;
    Dynamic_Scroll_Search searchScrollElement;
    Object_Save_Cancel save_cancel;
    SelectBrowser browser = new SelectBrowser(driver);
    Actions action;
    JavascriptExecutor js;

    @BeforeMethod
    public void setup() throws InterruptedException {
        driver = browser.chooseBrowser(chosen_browser);
        js = (JavascriptExecutor) driver;
        action = new Actions(driver);
        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(3000);
    }

    @Test(enabled = false)
    public void crearLocation() throws InterruptedException {
        login = new Home_Page(driver);
        login.loginPage("cpingo","1234");
        Thread.sleep(4000);
        //Ingresamos al OSM
        WebElement aplication = driver.findElement(By.xpath("//span[@class='sapMTextMaxLine sapMTextLineClamp' and normalize-space()='Organizational Structure Manager']"));
        aplication.click();
        Thread.sleep(6000);
        //Desplegamos Client
        driver.findElement(By.id("__xmlview4--mainTree-rows-row0-treeicon")).click();
        //Desplegamos Company
        driver.findElement(By.id("__xmlview4--mainTree-rows-row1-treeicon")).click();
        Thread.sleep(5000);
        String company = "Company Selenium";
        String location = "Location";
        int exist;
        String desple;
        searchScrollElement = new Dynamic_Scroll_Search(driver);
        exist = searchScrollElement.ElementSearch(company);
        if(exist != -1){
            desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
            driver.findElement(By.id(desple)).click();
            exist = searchScrollElement.ElementSearch(location);
            if(exist !=-1){
                WebElement elementLocation = driver.findElement(By.xpath("//span[normalize-space()='"+location+"']"));
                action.contextClick(elementLocation).perform();
                driver.findElement(By.xpath("//div[normalize-space()='New "+location+"']")).click();
                Thread.sleep(1000);
                //Llenando Formulario
                List<WebElement> listForm = driver.findElements(By.className("sapMInputBaseInner"));
                listForm.get(2).sendKeys("Location Selenium");
                listForm.get(3).sendKeys("Location Selenium");
                listForm.get(4).sendKeys("Location Selenium");
                char decision = 'G';
                save_cancel = new Object_Save_Cancel(driver);
                save_cancel.Save_Cancel(decision);
                Thread.sleep(2000);
            }else{
                System.out.println("No hay Location");
            }
        }else{
            System.out.println("No hay Company");
        }
    }


    @Test
    public void doubleCheckLocation() throws InterruptedException {
        login = new Home_Page(driver);
        login.loginPage("cpingo","1234");
        Thread.sleep(4000);
        //Ingresamos al OSM
        WebElement aplication = driver.findElement(By.xpath("//span[@class='sapMTextMaxLine sapMTextLineClamp' and normalize-space()='Organizational Structure Manager']"));
        aplication.click();
        Thread.sleep(6000);
        //Desplegamos Client
        driver.findElement(By.id("__xmlview4--mainTree-rows-row0-treeicon")).click();
        //Desplegamos Company
        driver.findElement(By.id("__xmlview4--mainTree-rows-row1-treeicon")).click();
        Thread.sleep(5000);
        String company = "Company Selenium";
        String location = "Location";
        int exist;
        String desple;
        searchScrollElement = new Dynamic_Scroll_Search(driver);
        exist = searchScrollElement.ElementSearch(company);
        if(exist != -1){
            desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
            driver.findElement(By.id(desple)).click();
            exist = searchScrollElement.ElementSearch(location);
            if(exist !=-1){
                WebElement elementLocation = driver.findElement(By.xpath("//span[normalize-space()='"+location+"']"));
                action.contextClick(elementLocation).perform();
                driver.findElement(By.xpath("//div[normalize-space()='New "+location+"']")).click();
                Thread.sleep(1000);
                //Llenando Formulario
                List<WebElement> listForm = driver.findElements(By.className("sapMInputBaseInner"));
                listForm.get(2).sendKeys("Location Selenium");
                listForm.get(3).sendKeys("Location Selenium");
                listForm.get(4).sendKeys("Location Selenium");
                char decision = 'G';
                save_cancel = new Object_Save_Cancel(driver);
                save_cancel.Save_Cancel(decision);
                Thread.sleep(2000);
                String message = driver.findElement(By.className("sapMMsgStripMessage")).getAttribute("textContent");
                Assert.assertEquals(message,"Location Already Exist");
                decision = 'C';
                save_cancel.Save_Cancel(decision);
            }else{
                System.out.println("No hay Location");
            }
        }else{
            System.out.println("No hay Company");
        }
    }


    @Test(enabled = false)
    public void viewLocationDependencies() throws InterruptedException {
        login = new Home_Page(driver);
        login.loginPage("cpingo","1234");
        //Ingresamos al OSM
        WebElement aplication = driver.findElement(By.xpath("//span[@class='sapMTextMaxLine sapMTextLineClamp' and normalize-space()='Organizational Structure Manager']"));
        aplication.click();
        Thread.sleep(6000);
        //Desplegamos Client
        driver.findElement(By.id("__xmlview4--mainTree-rows-row0-treeicon")).click();
        //Desplegamos Company
        driver.findElement(By.id("__xmlview4--mainTree-rows-row1-treeicon")).click();
        Thread.sleep(5000);
        String company = "Company Selenium";
        String location = "Location";
        String newLocation = "Location Selenium";
        int exist;
        String desple;
        searchScrollElement = new Dynamic_Scroll_Search(driver);
        exist = searchScrollElement.ElementSearch(company);
        if(exist != -1){
            desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
            driver.findElement(By.id(desple)).click();
            exist = searchScrollElement.ElementSearch(location);
            if(exist !=-1){
                desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
                driver.findElement(By.id(desple)).click();
                exist = searchScrollElement.ElementSearch(newLocation);
                if(exist !=-1){
                    driver.findElement(By.xpath("//span[normalize-space()='"+newLocation+"']")).click();
                    Thread.sleep(100);
                    driver.findElement(By.id("__xmlview4--viewDependencies-img")).click();
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


    @Test(enabled = false)
    public void editarLocation() throws InterruptedException {
        login = new Home_Page(driver);
        login.loginPage("cpingo","1234");
        Thread.sleep(4000);
        //Ingresamos al OSM
        WebElement aplication = driver.findElement(By.xpath("//span[@class='sapMTextMaxLine sapMTextLineClamp' and normalize-space()='Organizational Structure Manager']"));
        aplication.click();
        Thread.sleep(6000);
        //Desplegamos Client
        driver.findElement(By.id("__xmlview4--mainTree-rows-row0-treeicon")).click();
        //Desplegamos Company
        driver.findElement(By.id("__xmlview4--mainTree-rows-row1-treeicon")).click();
        Thread.sleep(5000);
        String company = "Company Selenium";
        String location = "Location";
        String newLocation = "Location Selenium";
        int exist;
        String desple;
        searchScrollElement = new Dynamic_Scroll_Search(driver);
        exist = searchScrollElement.ElementSearch(company);
        if(exist != -1){
            desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
            driver.findElement(By.id(desple)).click();
            exist = searchScrollElement.ElementSearch(location);
            if(exist !=-1){
                desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
                driver.findElement(By.id(desple)).click();
                exist = searchScrollElement.ElementSearch(newLocation);
                if(exist !=-1){
                    driver.findElement(By.xpath("//span[normalize-space()='"+newLocation+"']")).click();
                    Thread.sleep(100);
                    driver.findElement(By.id("__xmlview4--edit-img")).click(); // Editar Proyecto
                    Thread.sleep(500);
                    //Llenando Formulario
                    List<WebElement> listForm = driver.findElements(By.className("sapMInputBaseInner"));
                    listForm.get(2).clear();
                    listForm.get(2).sendKeys("Location Selenium1");
                    listForm.get(3).clear();
                    listForm.get(3).sendKeys("Location Selenium1");
                    listForm.get(4).clear();
                    listForm.get(4).sendKeys("Location Selenium1");
                    char decision = 'G';
                    save_cancel = new Object_Save_Cancel(driver);
                    save_cancel.Save_Cancel(decision);
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

    @Test(enabled = false)
    public void eliminarLocation() throws InterruptedException {
        login = new Home_Page(driver);
        login.loginPage("cpingo","1234");
        Thread.sleep(4000);
        //Ingresamos al OSM
        WebElement aplication = driver.findElement(By.xpath("//span[@class='sapMTextMaxLine sapMTextLineClamp' and normalize-space()='Organizational Structure Manager']"));
        aplication.click();
        Thread.sleep(6000);
        //Desplegamos Client
        driver.findElement(By.id("__xmlview4--mainTree-rows-row0-treeicon")).click();
        //Desplegamos Company
        driver.findElement(By.id("__xmlview4--mainTree-rows-row1-treeicon")).click();
        Thread.sleep(5000);
        String company = "Company Selenium";
        String location = "Location";
        String newLocation = "Location Selenium";
        int exist;
        String desple;
        searchScrollElement = new Dynamic_Scroll_Search(driver);
        exist = searchScrollElement.ElementSearch(company);
        if(exist != -1){
            desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
            driver.findElement(By.id(desple)).click();
            exist = searchScrollElement.ElementSearch(location);
            if(exist !=-1){
                desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
                driver.findElement(By.id(desple)).click();
                exist = searchScrollElement.ElementSearch(newLocation);
                if(exist !=-1){
                    WebElement elementLocation = driver.findElement(By.xpath("//span[normalize-space()='"+newLocation+"']"));
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
                    System.out.println("No hay " +newLocation );
                }
            }else{
                System.out.println("No hay Location");
            }
        }else{
            System.out.println("No hay Company");
        }
    }

    @Test
    public void create_Location_on_Location() throws InterruptedException {
        login = new Home_Page(driver);
        login.loginPage("cpingo","1234");
        //Ingresamos al OSM
        WebElement aplication = driver.findElement(By.xpath("//span[@class='sapMTextMaxLine sapMTextLineClamp' and normalize-space()='Organizational Structure Manager']"));
        aplication.click();
        Thread.sleep(4000);
        //Desplegamos Client
        driver.findElement(By.id("__xmlview4--mainTree-rows-row0-treeicon")).click();
        //Desplegamos Company
        driver.findElement(By.id("__xmlview4--mainTree-rows-row1-treeicon")).click();
        Thread.sleep(5000);
        String company = "Company Selenium";
        String location = "Location";
        String newlocation = "Location Selenium Padre";
        String childLocation = "Location Selenium Hijo";
        int exist;
        String desple;
        searchScrollElement = new Dynamic_Scroll_Search(driver);
        exist = searchScrollElement.ElementSearch(company);
        if(exist != -1){
            desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
            driver.findElement(By.id(desple)).click();
            exist = searchScrollElement.ElementSearch(location);
            if(exist !=-1){
                WebElement elementLocation = driver.findElement(By.xpath("//span[normalize-space()='"+location+"']"));
                action.contextClick(elementLocation).perform();
                driver.findElement(By.xpath("//div[normalize-space()='New "+location+"']")).click();
                Thread.sleep(1000);
                //Llenando Formulario
                List<WebElement> listForm = driver.findElements(By.className("sapMInputBaseInner"));
                listForm.get(2).sendKeys(newlocation);
                listForm.get(3).sendKeys(newlocation);
                listForm.get(4).sendKeys(newlocation);
                char decision = 'G';
                save_cancel = new Object_Save_Cancel(driver);
                save_cancel.Save_Cancel(decision);
                Thread.sleep(4000);
                desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
                driver.findElement(By.id(desple)).click();
                exist = searchScrollElement.ElementSearch(newlocation);
                if(exist != -1){
                    desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
                    driver.findElement(By.id(desple)).click();
                    exist = searchScrollElement.ElementSearch(location);
                    if (exist !=-1){
                        WebElement scrollBar = driver.findElement(By.id("__xmlview4--mainTree-vsb"));
                        int clientHeight = js.executeScript("let barra = document.getElementById('__xmlview4--mainTree-vsb');return(barra.clientHeight)").hashCode();
                        int scrollHeight = js.executeScript("let barra = document.getElementById('__xmlview4--mainTree-vsb');return(barra.scrollHeight)").hashCode();
                        js.executeScript("arguments[0].scroll(0,'"+clientHeight*(scrollHeight/clientHeight)+1+"')",scrollBar);
                        List<WebElement> locationList = driver.findElements(By.xpath("//span[normalize-space()='"+location+"']"));
                        action.contextClick(locationList.get(1)).perform();
                        driver.findElement(By.xpath("//div[normalize-space()='New "+location+"']")).click();
                        Thread.sleep(1000);
                        //Llenando Formulario
                        listForm = driver.findElements(By.className("sapMInputBaseInner"));
                        listForm.get(2).sendKeys(childLocation);
                        listForm.get(3).sendKeys(childLocation);
                        listForm.get(4).sendKeys(childLocation);
                        decision = 'G';
                        save_cancel.Save_Cancel(decision);
                        Thread.sleep(4000);
                    }else{
                        System.out.println("No hay Location2");
                    }
                }else{
                    System.out.println("No hay " + newlocation);
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
        //driver.close();
    }

    @AfterClass
    public static void tearDownAfterClass(){
        System.out.println("Terminaron los test");
    }
}
