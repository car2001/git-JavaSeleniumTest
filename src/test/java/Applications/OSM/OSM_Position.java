package Applications.OSM;

import Helpers.Dynamic_Scroll_Search;
import Helpers.Object_Save_Cancel;
import Helpers.SelectBrowser;
import HomepageFunctions.Home_Page;
import org.openqa.selenium.By;
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

public class OSM_Position {
    private WebDriver driver;
    private String url = "http://wedox.sytes.net/buplat_config/";
    private String chosen_browser = "Chrome";

    Home_Page login;
    Dynamic_Scroll_Search searchScrollElement;
    Object_Save_Cancel save_cancel;
    SelectBrowser browser = new SelectBrowser(driver);
    Actions action;

    @BeforeMethod
    public void setup() throws InterruptedException {
        driver = browser.chooseBrowser(chosen_browser);
        action = new Actions(driver);
        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(4000);
    }

    @Test(enabled = false)
    public void crearPosition() throws InterruptedException {
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
        Thread.sleep(3000);
        String company = "Company Selenium";
        String unit = "Organizational Unit";
        String newO_Unit = "Organizational Unit Selenium";
        String position = "Position";
        int exist;
        String desple;
        searchScrollElement = new Dynamic_Scroll_Search(driver);
        exist = searchScrollElement.ElementSearch(company);
        if( exist != -1){
            desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
            driver.findElement(By.id(desple)).click();
            Thread.sleep(1000);
            exist = searchScrollElement.ElementSearch(unit);
            if(exist != -1){
                desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
                driver.findElement(By.id(desple)).click();
                Thread.sleep(1000);
                exist = searchScrollElement.ElementSearch(newO_Unit);
                if(exist != -1){
                    desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
                    driver.findElement(By.id(desple)).click();
                    exist = searchScrollElement.ElementSearch(position);
                    if(exist != -1){
                        WebElement elementPosition = driver.findElement(By.xpath("//span[normalize-space()='Position']"));
                        action.contextClick(elementPosition).perform();
                        driver.findElement(By.xpath("//div[normalize-space()='New "+position+"']")).click();
                        Thread.sleep(2000);
                        //Llenando Formulario
                        List<WebElement> listForm = driver.findElements(By.className("sapMInputBaseInner"));
                        listForm.get(2).sendKeys("Gerente Calidad");
                        listForm.get(3).sendKeys("Gerente Calidad");
                        listForm.get(4).sendKeys("Gerente de Calidad encargado de la supervisión");
                        char decision = 'G';
                        save_cancel = new Object_Save_Cancel(driver);
                        save_cancel.Save_Cancel(decision);
                        Thread.sleep(2000);
                    }else{
                        System.out.println("No hay Position");
                    }
                }else{
                    System.out.println("No hay" + newO_Unit);
                }
            }else{
                System.out.println("No hay Organizational Unit");
            }
        }else{
            System.out.println("No hay compañia");
        }
    }

    @Test(priority = 0)
    public void doubleCheckPosition() throws InterruptedException {
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
        Thread.sleep(3000);
        String company = "Company Selenium";
        String unit = "Organizational Unit";
        String newO_Unit = "Organizational Unit Selenium";
        String position = "Position";
        int exist;
        String desple;
        searchScrollElement = new Dynamic_Scroll_Search(driver);
        exist = searchScrollElement.ElementSearch(company);
        if( exist != -1){
            desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
            driver.findElement(By.id(desple)).click();
            Thread.sleep(1000);
            exist = searchScrollElement.ElementSearch(unit);
            if(exist != -1){
                desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
                driver.findElement(By.id(desple)).click();
                Thread.sleep(1000);
                exist = searchScrollElement.ElementSearch(newO_Unit);
                if(exist != -1){
                    desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
                    driver.findElement(By.id(desple)).click();
                    exist = searchScrollElement.ElementSearch(position);
                    if(exist != -1){
                        WebElement elementPosition = driver.findElement(By.xpath("//span[normalize-space()='Position']"));
                        action.contextClick(elementPosition).perform();
                        driver.findElement(By.xpath("//div[normalize-space()='New "+position+"']")).click();
                        Thread.sleep(2000);
                        //Llenando Formulario
                        List<WebElement> listForm = driver.findElements(By.className("sapMInputBaseInner"));
                        listForm.get(2).sendKeys("Gerente Calidad");
                        listForm.get(3).sendKeys("Gerente Calidad");
                        listForm.get(4).sendKeys("Gerente de Calidad encargado de la supervisión");
                        char decision = 'G';
                        save_cancel = new Object_Save_Cancel(driver);
                        save_cancel.Save_Cancel(decision);
                        Thread.sleep(2000);
                        String message = driver.findElement(By.className("sapMMsgStripMessage")).getAttribute("textContent");
                        Assert.assertEquals(message,"Position Already Exist");
                    }else{
                        System.out.println("No hay Position");
                    }
                }else{
                    System.out.println("No hay" + newO_Unit);
                }
            }else{
                System.out.println("No hay Organizational Unit");
            }
        }else{
            System.out.println("No hay compañia");
        }
    }

    @Test(enabled = false)
    public void viewPositionDependencies() throws InterruptedException{
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
        String unit = "Organizational Unit";
        String newO_Unit = "Organizational Unit Selenium";
        String position = "Position";
        String newPosition = "Gerente Calidad";
        int exist;
        String desple;
        searchScrollElement = new Dynamic_Scroll_Search(driver);
        exist = searchScrollElement.ElementSearch(company);
        if(exist !=-1){
            desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
            driver.findElement(By.id(desple)).click();
            Thread.sleep(1000);
            exist = searchScrollElement.ElementSearch(unit);
            if(exist != -1){
                desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
                driver.findElement(By.id(desple)).click();
                Thread.sleep(1000);
                exist = searchScrollElement.ElementSearch(newO_Unit);
                if(exist != -1){
                    desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
                    driver.findElement(By.id(desple)).click();
                    Thread.sleep(1000);
                    exist = searchScrollElement.ElementSearch(position);
                    if(exist !=-1){
                        desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
                        driver.findElement(By.id(desple)).click();
                        Thread.sleep(1000);
                        exist = searchScrollElement.ElementSearch(newPosition);
                        if(exist !=-1){
                            driver.findElement(By.xpath("//span[normalize-space()='"+newPosition+"']")).click();
                            Thread.sleep(500);
                            driver.findElement(By.id("__xmlview4--viewDependencies-img")).click();
                            Thread.sleep(2000);
                        }else{
                            System.out.println("No hay " + newPosition);
                        }
                    }else{
                        System.out.println("No hay position");
                    }
                }else{
                    System.out.println("No hay" + newO_Unit);
                }
            }else{
                System.out.println("No hay Organizational Unit");
            }
        }else{
            System.out.println("No hay company");
        }
    }

    @Test(enabled = false)
    public void editarPosition() throws InterruptedException {
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
        Thread.sleep(3000);
        String company = "Company Selenium";
        String unit = "Organizational Unit";
        String newO_Unit = "Organizational Unit Selenium";
        String position = "Position";
        String newPosition = "Gerente Calidad";
        int exist;
        String desple;
        searchScrollElement = new Dynamic_Scroll_Search(driver);
        exist = searchScrollElement.ElementSearch(company);
        if(exist !=-1){
            desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
            driver.findElement(By.id(desple)).click();
            Thread.sleep(1000);
            exist = searchScrollElement.ElementSearch(unit);
            if(exist != -1){
                desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
                driver.findElement(By.id(desple)).click();
                Thread.sleep(1000);
                exist = searchScrollElement.ElementSearch(newO_Unit);
                if(exist != -1){
                    desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
                    driver.findElement(By.id(desple)).click();
                    Thread.sleep(1000);
                    exist = searchScrollElement.ElementSearch(position);
                    if(exist !=-1){
                        desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
                        driver.findElement(By.id(desple)).click();
                        Thread.sleep(1000);
                        exist = searchScrollElement.ElementSearch(newPosition);
                        if(exist !=-1){
                            driver.findElement(By.xpath("//span[normalize-space()='"+newPosition+"']")).click();
                            driver.findElement(By.id("__xmlview4--edit-img")).click(); // Editar Proyecto
                            Thread.sleep(500);
                            //Llenando Formulario
                            List<WebElement> listForm = driver.findElements(By.className("sapMInputBaseInner"));
                            listForm.get(2).clear();
                            listForm.get(2).sendKeys("Gerente Calidad1");
                            listForm.get(3).clear();
                            listForm.get(3).sendKeys("Gerente Calidad1");
                            listForm.get(4).clear();
                            listForm.get(4).sendKeys("Gerente de Calidad1 encargado de la supervisión");
                            char decision = 'G';
                            save_cancel = new Object_Save_Cancel(driver);
                            save_cancel.Save_Cancel(decision);
                            Thread.sleep(2000);
                        }else{
                            System.out.println("No hay " + newPosition);
                        }
                    }else{
                        System.out.println("No hay position");
                    }
                }else{
                    System.out.println("No hay" + newO_Unit);
                }
            }else{
                System.out.println("No hay Organizational Unit");
            }
        }else{
            System.out.println("No hay company");
        }

    }

    @Test(enabled = false)
    public void eliminarPosition() throws InterruptedException {
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
        Thread.sleep(3000);
        String company = "Company Selenium";
        String unit = "Organizational Unit";
        String newO_Unit = "Organizational Unit Selenium";
        String position = "Position";
        String newPosition = "Gerente Calidad";
        int exist;
        String desple;
        searchScrollElement = new Dynamic_Scroll_Search(driver);
        exist = searchScrollElement.ElementSearch(company);
        if(exist !=-1){
            desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
            driver.findElement(By.id(desple)).click();
            Thread.sleep(1000);
            exist = searchScrollElement.ElementSearch(unit);
            if(exist != -1){
                desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
                driver.findElement(By.id(desple)).click();
                Thread.sleep(1000);
                exist = searchScrollElement.ElementSearch(newO_Unit);
                if(exist != -1){
                    desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
                    driver.findElement(By.id(desple)).click();
                    Thread.sleep(1000);
                    exist = searchScrollElement.ElementSearch(position);
                    if(exist !=-1){
                        desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
                        driver.findElement(By.id(desple)).click();
                        Thread.sleep(1000);
                        exist = searchScrollElement.ElementSearch(newPosition);
                        if(exist !=-1){
                            WebElement elementNewPosition =driver.findElement(By.xpath("//span[normalize-space()='"+newPosition+"']"));
                            action.contextClick(elementNewPosition).perform();
                            driver.findElement(By.xpath("//div[normalize-space()='Delete "+position+"']")).click();
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
                            System.out.println("No hay " + newPosition);
                        }
                    }else{
                        System.out.println("No hay position");
                    }
                }else{
                    System.out.println("No hay" + newO_Unit);
                }
            }else{
                System.out.println("No hay Organizational Unit");
            }
        }else{
            System.out.println("No hay company");
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
