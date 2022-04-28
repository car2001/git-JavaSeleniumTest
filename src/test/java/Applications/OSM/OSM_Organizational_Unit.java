package Applications.OSM;

import Helpers.*;
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
public class OSM_Organizational_Unit {
    private WebDriver driver;
    private String url = "http://wedox.sytes.net/buplat_config/";
    private String chosen_browser = "Chrome";

    Home_Page login;
    Dynamic_Scroll_Search searchScrollElement;
    Object_Save_Cancel save_cancel;
    SelectBrowser browser = new SelectBrowser(driver);
    Actions action;
    JavascriptExecutor js;

    @BeforeMethod
    public void setUp() throws InterruptedException {
        browser.chooseBrowser(chosen_browser);
        driver = browser.getDriver();
        action = new Actions(driver);
        js = (JavascriptExecutor) driver;
        login = new Home_Page(driver);
        login.homeSettings();
    }

    @Test(priority = 0)
    public void crearOrgani_Unit() throws InterruptedException {
        login.loginPage("cpingo","1234");
        Login_Applications.loginOSM(driver);
        String company = "Company Selenium";
        String unit = "Organizational Unit";
        int exist;
        String desple;
        searchScrollElement = new Dynamic_Scroll_Search(driver);
        exist = searchScrollElement.elementSearch(company);
        if(exist != -1){
            desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
            driver.findElement(By.id(desple)).click();
            Thread.sleep(1000);
            exist = searchScrollElement.elementSearch(unit);
            if(exist!=-1){
                WebElement element = driver.findElement(By.xpath("//span[normalize-space()='Organizational Unit']"));
                action.contextClick(element).perform();
                driver.findElement(By.xpath("//div[normalize-space()='New "+unit+"']")).click();
                Thread.sleep(2000);
                //Llenando Formulario
                List<WebElement> listForm = driver.findElements(By.className("sapMInputBaseInner"));
                listForm.get(2).sendKeys("Organizational Unit Selenium");
                listForm.get(3).sendKeys("Organizational Unit Selenium");
                listForm.get(4).sendKeys("Organizational Unit Selenium");
                //Decidimos si guardar(G) o cancelar(C)
                char decision = 'G';
                save_cancel = new Object_Save_Cancel(driver);
                save_cancel.save_Cancel(decision);
                Thread.sleep(500);
                String message = driver.findElement(By.className("sapMMsgStripMessage")).getAttribute("textContent");
                Assert.assertEquals(message, "The Operation has been Completed Successfully." + "\n");
            }else{
                System.out.println("No hay Organizational Unit");
           }
        }else{
            js.executeScript("alert('"+" No se encontro la compañia "+company+"')");
            Thread.sleep(2900);
        }
    }

    @Test(priority = 1)
    public void doubleCheckOrgani_Unit() throws InterruptedException {
        login.loginPage("cpingo","1234");
        Login_Applications.loginOSM(driver);
        String company = "Company Selenium";
        String unit = "Organizational Unit";
        int exist;
        String desple;
        searchScrollElement = new Dynamic_Scroll_Search(driver);
        exist = searchScrollElement.elementSearch(company);
        if(exist != -1){
            desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
            driver.findElement(By.id(desple)).click();
            Thread.sleep(1000);
            exist = searchScrollElement.elementSearch(unit);
            if(exist!=-1){
                WebElement element = driver.findElement(By.xpath("//span[normalize-space()='Organizational Unit']"));
                action.contextClick(element).perform();
                driver.findElement(By.xpath("//div[normalize-space()='New "+unit+"']")).click();
                Thread.sleep(2000);
                //Llenando Formulario
                List<WebElement> listForm = driver.findElements(By.className("sapMInputBaseInner"));
                listForm.get(2).sendKeys("Organizational Unit Selenium");
                listForm.get(3).sendKeys("Organizational Unit Selenium");
                listForm.get(4).sendKeys("Organizational Unit Selenium");
                //Decidimos si guardar(G) o cancelar(C)
                char decision = 'G';
                save_cancel = new Object_Save_Cancel(driver);
                save_cancel.save_Cancel(decision);
                Thread.sleep(500);
                String message = driver.findElement(By.className("sapMMsgStripMessage")).getAttribute("textContent");
                Assert.assertEquals(message,"Organizational Unit Already Exist");
            }else{
                System.out.println("No hay Organizational Unit");
            }
        }else{
            js.executeScript("alert('"+" No se encontro la compañia "+company+"')");
            Thread.sleep(2900);
        }
    }


    @Test(priority = 2)
    public void viewOrgani_UnitDependencies() throws InterruptedException {
        login.loginPage("cpingo","1234");
        Login_Applications.loginOSM(driver);
        String company = "Company Selenium";
        String elemen_unit = "Organizational Unit";
        String elemen_org = "Organizational Unit Selenium";
        int exist;
        searchScrollElement = new Dynamic_Scroll_Search(driver);
        exist = searchScrollElement.elementSearch(company);
        String desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
        if(exist != -1){
            driver.findElement(By.id(desple)).click();
            Thread.sleep(2000);
            exist = searchScrollElement.elementSearch(elemen_unit);
            if(exist!=-1){
                desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
                driver.findElement(By.id(desple)).click();
                Thread.sleep(2000);
                exist = searchScrollElement.elementSearch(elemen_org);
                if(exist !=1){
                    driver.findElement(By.xpath("//span[normalize-space()='"+elemen_org+"']")).click();
                    Thread.sleep(200);
                    driver.findElement(By.id("__xmlview4--viewDependencies-img")).click();
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
            Thread.sleep(2900);
        }
    }

    @Test(priority = 3)
    public void editarOrgani_Unit() throws InterruptedException {
        login.loginPage("cpingo","1234");
        Login_Applications.loginOSM(driver);
        String company = "Company Selenium";
        String elemen_unit = "Organizational Unit";
        String elemen_org = "Organizational Unit Selenium";
        int exist;
        searchScrollElement = new Dynamic_Scroll_Search(driver);
        exist = searchScrollElement.elementSearch(company);
        String desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
        if(exist != -1){
            driver.findElement(By.id(desple)).click();
            Thread.sleep(2000);
            exist = searchScrollElement.elementSearch(elemen_unit);
            if(exist!=-1){
                desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
                driver.findElement(By.id(desple)).click();
                Thread.sleep(2000);
                exist = searchScrollElement.elementSearch(elemen_org);
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
                    save_cancel.save_Cancel(decision);
                    Thread.sleep(2000);
                }else{
                    System.out.println("No hay Sub" +elemen_org );
                }
            }else{
                System.out.println("No hay Organizational Unit");
            }
        }else{
            js.executeScript("alert('"+" No se encontro la compañia "+company+"')");
            Thread.sleep(2900);
        }
    }

    @Test(priority = 4)
    public void eliminarOrgani_Unit() throws InterruptedException {
        login.loginPage("cpingo","1234");
        Login_Applications.loginOSM(driver);
        String company = "Company Selenium";
        String elemen_unit = "Organizational Unit";
        String elemen_org = "Organizational Unit Selenium";
        int exist;
        searchScrollElement = new Dynamic_Scroll_Search(driver);
        exist = searchScrollElement.elementSearch(company);
        String desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
        if(exist != -1){
            driver.findElement(By.id(desple)).click();
            Thread.sleep(2000);
            exist = searchScrollElement.elementSearch(elemen_unit);
            desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
            if(exist!=-1){
                driver.findElement(By.id(desple)).click();
                Thread.sleep(2000);
                exist = searchScrollElement.elementSearch(elemen_org);
                if(exist != -1){
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
            Thread.sleep(2900);
        }
    }

    @Test(priority = 5)
    public void crearOrgani_Unit_on_OrganiUnit() throws InterruptedException {
        login.loginPage("cpingo","1234");
        Login_Applications.loginOSM(driver);
        String company = "Company Selenium";
        String unit = "Organizational Unit";
        String parentUnit = "Organizational Unit Selenium Padre";
        String childUnit = "Organizational Unit Selenium Hijo";
        int exist;
        String desple;
        searchScrollElement = new Dynamic_Scroll_Search(driver);
        exist = searchScrollElement.elementSearch(company);
        if(exist != -1){
            desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
            driver.findElement(By.id(desple)).click();
            Thread.sleep(1000);
            exist = searchScrollElement.elementSearch(unit);
            if(exist!=-1){
                WebElement element = driver.findElement(By.xpath("//span[normalize-space()='Organizational Unit']"));
                action.contextClick(element).perform();
                driver.findElement(By.xpath("//div[normalize-space()='New "+unit+"']")).click();
                Thread.sleep(2000);
                //Llenando Formulario
                List<WebElement> listForm = driver.findElements(By.className("sapMInputBaseInner"));
                listForm.get(2).sendKeys(parentUnit);
                listForm.get(3).sendKeys(parentUnit);
                listForm.get(4).sendKeys(parentUnit);
                //Decidimos si guardar(G) o cancelar(C)
                char decision = 'G';
                save_cancel = new Object_Save_Cancel(driver);
                save_cancel.save_Cancel(decision);
                Thread.sleep(2000);
                desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
                driver.findElement(By.id(desple)).click();
                exist = searchScrollElement.elementSearch(parentUnit);
                if(exist != -1){
                    desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
                    driver.findElement(By.id(desple)).click();
                    exist = searchScrollElement.elementSearch(unit);
                    if (exist !=-1){
                        WebElement scrollBar = driver.findElement(By.id("__xmlview4--mainTree-vsb"));
                        int clientHeight = js.executeScript("let barra = document.getElementById('__xmlview4--mainTree-vsb');return(barra.clientHeight)").hashCode();
                        int scrollHeight = js.executeScript("let barra = document.getElementById('__xmlview4--mainTree-vsb');return(barra.scrollHeight)").hashCode();
                        js.executeScript("arguments[0].scroll(0,'"+clientHeight*(scrollHeight/clientHeight)+1+"')",scrollBar);
                        List<WebElement> locationList = driver.findElements(By.xpath("//span[normalize-space()='"+unit+"']"));
                        action.contextClick(locationList.get(1)).perform();
                        driver.findElement(By.xpath("//div[normalize-space()='New "+unit+"']")).click();
                        Thread.sleep(1000);
                        //Llenando Formulario
                        listForm = driver.findElements(By.className("sapMInputBaseInner"));
                        listForm.get(2).sendKeys(childUnit);
                        listForm.get(3).sendKeys(childUnit);
                        listForm.get(4).sendKeys(childUnit);
                        decision = 'G';
                        save_cancel.save_Cancel(decision);
                        Thread.sleep(4000);
                    }else{
                        System.out.println("No hay Organizational Unit2");
                    }
                }else{
                    System.out.println("No hay " + parentUnit);
                }

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

    @AfterMethod
    public void tearDown() throws InterruptedException {
        //driver.quit();
    }

    @AfterClass
    public static void tearDownAfterClass(){
        System.out.println("Terminaron los test");
    }

}
