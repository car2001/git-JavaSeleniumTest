package Applications.OSM;

import Helpers.Dynamic_Scroll_Search;
import Helpers.FormsOSM;
import Helpers.Options;
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
    Options options;
    SelectBrowser browser = new SelectBrowser(driver);
    Actions action;

    String company = "Company Selenium";
    String unit = "Organizational Unit";
    String new_Unit = "Organizational Unit Selenium";
    String position = "Position";
    String newPosition = "Gerente de Calidad";
    String editPosition = "Gerente de Calidad Selenium";
    int exist = -1;
    String desple;

    @BeforeMethod
    public void setup() throws InterruptedException {
        browser.chooseBrowser(chosen_browser);
        driver = browser.getDriver();
        action = new Actions(driver);
        searchScrollElement = new Dynamic_Scroll_Search(driver);
        options = new Options(driver);
        login = new Home_Page(driver);
    }

    @Test(priority = 0)
    public void crearPosition() throws InterruptedException {

        login.loginPage("cpingo","1234");
        Login_Applications.loginOSM(driver);
        exist = searchScrollElement.elementSearch(company);
        if( exist != -1){
            desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
            driver.findElement(By.id(desple)).click();
            exist = searchScrollElement.elementSearch(unit);
            if(exist != -1){
                desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
                driver.findElement(By.id(desple)).click();
                exist = searchScrollElement.elementSearch(new_Unit);
                if(exist != -1){
                    desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
                    driver.findElement(By.id(desple)).click();
                    exist = searchScrollElement.elementSearch(position);
                    if(exist != -1){
                        WebElement elementPosition = driver.findElement(By.xpath("//span[normalize-space()='"+position+"']"));
                        action.contextClick(elementPosition).perform();
                        driver.findElement(By.xpath("//div[normalize-space()='New "+position+"']")).click();
                        Thread.sleep(500);
                        //LLenando Formulario
                        FormsOSM.formCreatePosition(driver,newPosition);
                        options.save();
                        String message = driver.findElement(By.className("sapMMsgStripMessage")).getAttribute("textContent");
                        Assert.assertEquals(message,"The Operation has been Completed Successfully."+ "\n");
                    }else{
                        System.out.println("No hay Position");
                    }
                }else{
                    System.out.println("No hay " + new_Unit);
                }
            }else{
                System.out.println("No hay Organizational Unit");
            }
        }else{
            System.out.println("No hay compañia");
        }
    }

    @Test(priority = 1)
    public void doubleCheckPosition() throws InterruptedException {
        login.loginPage("cpingo","1234");
        Login_Applications.loginOSM(driver);
        exist = searchScrollElement.elementSearch(company);
        if( exist != -1){
            desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
            driver.findElement(By.id(desple)).click();
            exist = searchScrollElement.elementSearch(unit);
            if(exist != -1){
                desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
                driver.findElement(By.id(desple)).click();
                exist = searchScrollElement.elementSearch(new_Unit);
                if(exist != -1){
                    desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
                    driver.findElement(By.id(desple)).click();
                    exist = searchScrollElement.elementSearch(position);
                    if(exist != -1){
                        WebElement elementPosition = driver.findElement(By.xpath("//span[normalize-space()='"+position+"']"));
                        action.contextClick(elementPosition).perform();
                        driver.findElement(By.xpath("//div[normalize-space()='New "+position+"']")).click();
                        Thread.sleep(500);
                        //LLenando Formulario
                        FormsOSM.formCreatePosition(driver,newPosition);
                        options.save();
                        String message = driver.findElement(By.className("sapMMsgStripMessage")).getAttribute("textContent");
                        Assert.assertEquals(message,"Position Already Exist");
                    }else{
                        System.out.println("No hay Position");
                    }
                }else{
                    System.out.println("No hay" + new_Unit);
                }
            }else{
                System.out.println("No hay Organizational Unit");
            }
        }else{
            System.out.println("No hay compañia");
        }
    }

    @Test(priority = 2)
    public void viewPositionDependencies() throws InterruptedException{
        login.loginPage("cpingo","1234");
        Login_Applications.loginOSM(driver);
        exist = searchScrollElement.elementSearch(company);
        if(exist !=-1){
            desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
            driver.findElement(By.id(desple)).click();
            exist = searchScrollElement.elementSearch(unit);
            if(exist != -1){
                desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
                driver.findElement(By.id(desple)).click();
                exist = searchScrollElement.elementSearch(new_Unit);
                if(exist != -1){
                    desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
                    driver.findElement(By.id(desple)).click();
                    exist = searchScrollElement.elementSearch(position);
                    if(exist !=-1){
                        desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
                        driver.findElement(By.id(desple)).click();
                        exist = searchScrollElement.elementSearch(newPosition);
                        if(exist !=-1){
                            driver.findElement(By.xpath("//span[normalize-space()='"+newPosition+"']")).click();
                            Thread.sleep(500);
                            options.viewDependecies();
                            String message = driver.findElement(By.id("__xmlview4--dependenciesTableTitle-inner")).getText();
                            Assert.assertEquals(message,"Dependencies List");
                            Thread.sleep(500);
                        }else{
                            System.out.println("No hay " + newPosition);
                        }
                    }else{
                        System.out.println("No hay position");
                    }
                }else{
                    System.out.println("No hay" + new_Unit);
                }
            }else{
                System.out.println("No hay Organizational Unit");
            }
        }else{
            System.out.println("No hay company");
        }
    }

    @Test(priority = 3)
    public void editarPosition() throws InterruptedException {
        login.loginPage("cpingo","1234");
        Login_Applications.loginOSM(driver);
        exist = searchScrollElement.elementSearch(company);
        if(exist !=-1){
            desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
            driver.findElement(By.id(desple)).click();
            exist = searchScrollElement.elementSearch(unit);
            if(exist != -1){
                desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
                driver.findElement(By.id(desple)).click();
                exist = searchScrollElement.elementSearch(new_Unit);
                if(exist != -1){
                    desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
                    driver.findElement(By.id(desple)).click();
                    exist = searchScrollElement.elementSearch(position);
                    if(exist != -1){
                        desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
                        driver.findElement(By.id(desple)).click();
                        exist = searchScrollElement.elementSearch(newPosition);
                        if(exist !=-1){
                            driver.findElement(By.xpath("//span[normalize-space()='"+newPosition+"']")).click();
                            Thread.sleep(500);
                            options.edit();
                            //Llenando Formulario
                            FormsOSM.formEditPosition(driver,editPosition);
                            options.save();
                            Thread.sleep(500);
                        }else{
                            System.out.println("No hay " + newPosition);
                        }
                    }else{
                        System.out.println("No hay position");
                    }
                }else{
                    System.out.println("No hay" + new_Unit);
                }
            }else{
                System.out.println("No hay Organizational Unit");
            }
        }else{
            System.out.println("No hay company");
        }
    }

    @Test(priority = 4)
    public void eliminarPosition() throws InterruptedException {
        login.loginPage("cpingo","1234");
        Login_Applications.loginOSM(driver);
        exist = searchScrollElement.elementSearch(company);
        if(exist !=-1){
            desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
            driver.findElement(By.id(desple)).click();
            exist = searchScrollElement.elementSearch(unit);
            if(exist != -1){
                desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
                driver.findElement(By.id(desple)).click();
                exist = searchScrollElement.elementSearch(new_Unit);
                if(exist != -1){
                    desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
                    driver.findElement(By.id(desple)).click();
                    exist = searchScrollElement.elementSearch(position);
                    if(exist !=-1){
                        desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
                        driver.findElement(By.id(desple)).click();
                        exist = searchScrollElement.elementSearch(editPosition);
                        if(exist !=-1){
                            WebElement elementNewPosition =driver.findElement(By.xpath("//span[normalize-space()='"+editPosition+"']"));
                            action.contextClick(elementNewPosition).perform();
                            driver.findElement(By.xpath("//div[normalize-space()='Delete "+position+"']")).click();
                            Thread.sleep(1000);
                            driver.findElement(By.xpath("//bdi[normalize-space()='Si']")).click();
                            Thread.sleep(1000);

                            String message = driver.findElement(By.xpath("//span[@class='sapMText sapUiSelectable sapMTextMaxWidth sapMMsgBoxText']")).getText();
                            Thread.sleep(1000);
                            if(message.equals("The Operation has been Completed Successfully.")){
                                driver.findElement(By.xpath("//bdi[normalize-space()='OK']")).click();
                            }else{
                                driver.findElement(By.xpath("//bdi[normalize-space()='Cerrar']")).click();
                            }
                        }else{
                            System.out.println("No hay " + newPosition);
                        }
                    }else{
                        System.out.println("No hay position");
                    }
                }else{
                    System.out.println("No hay" + new_Unit);
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
        if (driver != null){
            driver.quit();
        }
    }

    @AfterClass
    public static void tearDownAfterClass(){
        System.out.println("Terminaron los test");
    }

}
