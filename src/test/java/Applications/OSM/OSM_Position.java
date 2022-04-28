package Applications.OSM;

import Helpers.Dynamic_Scroll_Search;
import Helpers.Object_Save_Cancel;
import Helpers.SelectBrowser;
import HomepageFunctions.Home_Page;
import HomepageFunctions.Login_Applications;
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
    private String chosen_browser = "Chrome";

    Home_Page login;
    Dynamic_Scroll_Search searchScrollElement;
    Object_Save_Cancel save_cancel;
    SelectBrowser browser = new SelectBrowser(driver);
    Actions action;

    String company = "Company Selenium";
    final String unit = "Organizational Unit";
    String newO_Unit = "Organizational Unit Selenium";
    final String position = "Position";
    String newPosition = "Gerente Calidad";
    int exist = -1;
    String desple;

    @BeforeMethod
    public void setup() throws InterruptedException {
        browser.chooseBrowser(chosen_browser);
        driver = browser.getDriver();
        action = new Actions(driver);
        login = new Home_Page(driver);
        login.homeSettings();
    }

    @Test(enabled = false)
    public void crearPosition() throws InterruptedException {
        login.loginPage("cpingo","1234");
        Login_Applications.loginOSM(driver);
        searchScrollElement = new Dynamic_Scroll_Search(driver);
        exist = searchScrollElement.elementSearch(company);
        if( exist != -1){
            desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
            driver.findElement(By.id(desple)).click();
            Thread.sleep(1000);
            exist = searchScrollElement.elementSearch(unit);
            if(exist != -1){
                desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
                driver.findElement(By.id(desple)).click();
                Thread.sleep(1000);
                exist = searchScrollElement.elementSearch(newO_Unit);
                if(exist != -1){
                    desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
                    driver.findElement(By.id(desple)).click();
                    exist = searchScrollElement.elementSearch(position);
                    if(exist != -1){
                        WebElement elementPosition = driver.findElement(By.xpath("//span[normalize-space()='"+position+"']"));
                        action.contextClick(elementPosition).perform();
                        driver.findElement(By.xpath("//div[normalize-space()='New "+position+"']")).click();
                        Thread.sleep(2000);
                        //Llenando Formulario
                        List<WebElement> listForm = driver.findElements(By.className("sapMInputBaseInner"));
                        listForm.get(2).sendKeys(newPosition);
                        listForm.get(3).sendKeys(newPosition);
                        listForm.get(4).sendKeys(newPosition);
                        char decision = 'G';
                        save_cancel = new Object_Save_Cancel(driver);
                        save_cancel.save_Cancel(decision);
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
        login.loginPage("cpingo","1234");
        Login_Applications.loginOSM(driver);
        searchScrollElement = new Dynamic_Scroll_Search(driver);
        exist = searchScrollElement.elementSearch(company);
        if( exist != -1){
            desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
            driver.findElement(By.id(desple)).click();
            Thread.sleep(1000);
            exist = searchScrollElement.elementSearch(unit);
            if(exist != -1){
                desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
                driver.findElement(By.id(desple)).click();
                Thread.sleep(1000);
                exist = searchScrollElement.elementSearch(newO_Unit);
                if(exist != -1){
                    desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
                    driver.findElement(By.id(desple)).click();
                    exist = searchScrollElement.elementSearch(position);
                    if(exist != -1){
                        WebElement elementPosition = driver.findElement(By.xpath("//span[normalize-space()='"+position+"']"));
                        action.contextClick(elementPosition).perform();
                        driver.findElement(By.xpath("//div[normalize-space()='New "+position+"']")).click();
                        Thread.sleep(2000);
                        //Llenando Formulario
                        List<WebElement> listForm = driver.findElements(By.className("sapMInputBaseInner"));
                        listForm.get(2).sendKeys(newPosition);
                        listForm.get(3).sendKeys(newPosition);
                        listForm.get(4).sendKeys(newPosition);
                        char decision = 'G';
                        save_cancel = new Object_Save_Cancel(driver);
                        save_cancel.save_Cancel(decision);
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
        login.loginPage("cpingo","1234");
        Login_Applications.loginOSM(driver);
        searchScrollElement = new Dynamic_Scroll_Search(driver);
        exist = searchScrollElement.elementSearch(company);
        if(exist !=-1){
            desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
            driver.findElement(By.id(desple)).click();
            Thread.sleep(1000);
            exist = searchScrollElement.elementSearch(unit);
            if(exist != -1){
                desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
                driver.findElement(By.id(desple)).click();
                Thread.sleep(1000);
                exist = searchScrollElement.elementSearch(newO_Unit);
                if(exist != -1){
                    desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
                    driver.findElement(By.id(desple)).click();
                    Thread.sleep(1000);
                    exist = searchScrollElement.elementSearch(position);
                    if(exist !=-1){
                        desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
                        driver.findElement(By.id(desple)).click();
                        Thread.sleep(1000);
                        exist = searchScrollElement.elementSearch(newPosition);
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
        login.loginPage("cpingo","1234");
        Login_Applications.loginOSM(driver);
        if(exist !=-1){
            desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
            driver.findElement(By.id(desple)).click();
            Thread.sleep(1000);
            exist = searchScrollElement.elementSearch(unit);
            if(exist != -1){
                desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
                driver.findElement(By.id(desple)).click();
                Thread.sleep(1000);
                exist = searchScrollElement.elementSearch(newO_Unit);
                if(exist != -1){
                    desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
                    driver.findElement(By.id(desple)).click();
                    Thread.sleep(1000);
                    exist = searchScrollElement.elementSearch(position);
                    if(exist !=-1){
                        desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
                        driver.findElement(By.id(desple)).click();
                        Thread.sleep(1000);
                        exist = searchScrollElement.elementSearch(newPosition);
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
                            save_cancel.save_Cancel(decision);
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
        login.loginPage("cpingo","1234");
        Login_Applications.loginOSM(driver);
        searchScrollElement = new Dynamic_Scroll_Search(driver);
        exist = searchScrollElement.elementSearch(company);
        if(exist !=-1){
            desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
            driver.findElement(By.id(desple)).click();
            Thread.sleep(1000);
            exist = searchScrollElement.elementSearch(unit);
            if(exist != -1){
                desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
                driver.findElement(By.id(desple)).click();
                Thread.sleep(1000);
                exist = searchScrollElement.elementSearch(newO_Unit);
                if(exist != -1){
                    desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
                    driver.findElement(By.id(desple)).click();
                    Thread.sleep(1000);
                    exist = searchScrollElement.elementSearch(position);
                    if(exist !=-1){
                        desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
                        driver.findElement(By.id(desple)).click();
                        Thread.sleep(1000);
                        exist = searchScrollElement.elementSearch(newPosition);
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
        driver.quit();
    }

    @AfterClass
    public static void tearDownAfterClass(){
        System.out.println("Terminaron los test");
    }

}
