package Applications.OSM;

import Helpers.*;
import HomepageFunctions.Home_Page;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;

import java.util.List;

/**
 *
 * @author Carlos Alberto
 */

public class OSM_Organizational_Unit {
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
    public void setUp() throws InterruptedException {
        driver = browser.chooseBrowser(chosen_browser);
        action = new Actions(driver);
        js = (JavascriptExecutor) driver;
        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);
    }

    @Test(priority = 1)
    public void crearOrgani_Unit() throws InterruptedException {
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
        String company = "Test";
        String unit = "Organizational Unit";
        int exist;
        searchScrollElement = new Dynamic_Scroll_Search(driver);
        exist = searchScrollElement.ElementSearch(company);
        String desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
        if(exist != -1){
            driver.findElement(By.id(desple)).click();
            Thread.sleep(2000);
            exist = searchScrollElement.ElementSearch(unit);
            if(exist!=-1){
                WebElement element = driver.findElement(By.xpath("//span[normalize-space()='Organizational Unit']"));
                action.contextClick(element).perform();
                driver.findElement(By.xpath("//div[normalize-space()='New "+unit+"']")).click();
                Thread.sleep(3000);
                //Llenando Formulario
                List<WebElement> listForm = driver.findElements(By.className("sapMInputBaseInner"));
                listForm.get(2).sendKeys("Organizational Unit Selenium");
                listForm.get(3).sendKeys("Organizational Unit Selenium");
                listForm.get(4).sendKeys("Organizational Unit Selenium");
                //Decidimos si guardar(G) o cancelar(C)
                char decision = 'G';
                save_cancel = new Object_Save_Cancel(driver);
                save_cancel.Save_Cancel(decision);
                Thread.sleep(2000);
            }else{
                System.out.println("No hay Organizational Unit");
                Thread.sleep(2000);
           }
            Thread.sleep(2000);
        }else{
            js.executeScript("alert('"+" No se encontro la compañia "+company+"')");
            Thread.sleep(3000);
        }
    }

    @Test(priority = 2)
    public void editarOrgani_Unit() throws InterruptedException {
        login = new Home_Page(driver);
        login.loginPage("cpingo","1234");
        Thread.sleep(4000);
        //Ingresamos al OSM
        WebElement aplication = driver.findElement(By.xpath("//span[@class='sapMTextMaxLine sapMTextLineClamp' and normalize-space()='Organizational Structure Manager']"));
        aplication.click();
        Thread.sleep(9000);
        //Desplegamos Client
        driver.findElement(By.id("__xmlview4--mainTree-rows-row0-treeicon")).click();
        //Desplegamos Company
        driver.findElement(By.id("__xmlview4--mainTree-rows-row1-treeicon")).click();
        Thread.sleep(5000);
        String company = "Test";
        String elemen_unit = "Organizational Unit";
        String elemen_org = "Organizational Unit Selenium";
        int exist;
        searchScrollElement = new Dynamic_Scroll_Search(driver);
        exist = searchScrollElement.ElementSearch(company);
        String desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
        if(exist != -1){
            driver.findElement(By.id(desple)).click();
            Thread.sleep(2000);
            exist = searchScrollElement.ElementSearch(elemen_unit);
            desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
            if(exist!=-1){
                driver.findElement(By.id(desple)).click();
                Thread.sleep(2000);
                exist = searchScrollElement.ElementSearch(elemen_org);
                if(exist !=1){
                    driver.findElement(By.xpath("//span[normalize-space()='"+elemen_org+"']")).click();
                    Thread.sleep(1000);
                    driver.findElement(By.id("__xmlview4--edit-img")).click(); // Editar Proyecto
                    Thread.sleep(1000);
                    //Llenando Formulario
                    List<WebElement> listForm = driver.findElements(By.className("sapMInputBaseInner"));
                    listForm.get(2).clear();
                    listForm.get(2).sendKeys("Organizational Unit Selenium1");
                    listForm.get(3).clear();
                    listForm.get(3).sendKeys("Organizational Unit Selenium1");
                    listForm.get(4).clear();
                    listForm.get(4).sendKeys("Organizational Unit Selenium1");
                    //Decidimos si guardar(G) o cancelar(C)
                    char decision = 'G';
                    save_cancel = new Object_Save_Cancel(driver);
                    save_cancel.Save_Cancel(decision);
                    Thread.sleep(2000);
                }else{
                    System.out.println("No hay Sub" +elemen_org );
                }
            }else{
                System.out.println("No hay Organizational Unit");
            }
            Thread.sleep(2000);
        }else{
            js.executeScript("alert('"+" No se encontro la compañia "+company+"')");
            Thread.sleep(3000);
        }
    }

    @Test(priority = 3)
    public void eliminarOrgani_Unit() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        login = new Home_Page(driver);
        login.loginPage("cpingo","1234");
        Thread.sleep(4000);
        //Ingresamos al OSM
        WebElement aplication = driver.findElement(By.xpath("//span[@class='sapMTextMaxLine sapMTextLineClamp' and normalize-space()='Organizational Structure Manager']"));
        aplication.click();
        Thread.sleep(5000);
        //Desplegamos Client
        driver.findElement(By.id("__xmlview4--mainTree-rows-row0-treeicon")).click();
        //Desplegamos Company
        driver.findElement(By.id("__xmlview4--mainTree-rows-row1-treeicon")).click();
        Thread.sleep(5000);
        String company = "Test";
        String elemen_unit = "Organizational Unit";
        String elemen_org = "Organizational Unit Selenium1";
        int exist;
        searchScrollElement = new Dynamic_Scroll_Search(driver);
        exist = searchScrollElement.ElementSearch(company);
        String desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
        if(exist != -1){
            driver.findElement(By.id(desple)).click();
            Thread.sleep(2000);
            exist = searchScrollElement.ElementSearch(elemen_unit);
            desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
            if(exist!=-1){
                driver.findElement(By.id(desple)).click();
                Thread.sleep(2000);
                exist = searchScrollElement.ElementSearch(elemen_org);
                if(exist !=1){
                    WebElement org_delete = driver.findElement(By.xpath("//span[normalize-space()='"+elemen_org+"']"));
                    action.contextClick(org_delete).perform();
                    driver.findElement(By.xpath("//div[normalize-space()='Delete "+elemen_unit+"']")).click();
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

                    Thread.sleep(1000);
                }else{
                    System.out.println("No hay Sub" +elemen_org );
                }
            }else{
                System.out.println("No hay Organizational Unit");
            }
            Thread.sleep(2000);
        }else{
            js.executeScript("alert('"+" No se encontro la compañia "+company+"')");
            Thread.sleep(3000);
        }
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        driver.close();
    }

    @AfterClass
    public static void tearDownAfterClass(){
        System.out.println("Terminaron los test");
    }

}
