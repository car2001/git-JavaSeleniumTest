package Applications.OSM;

import Helpers.*;
import Forms.FormsOSM;
import HomePage.Login;
import HomePage.LoginApplications;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;


public class OSM_Location {

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

    String company = "Company Selenium";                    //Compañia
    String location = "Location";                           // Componente a Buscar
    String newLocation = "Location Selenium";               // Location Creado
    String editLocation = "Location Selenium Editado";      // Location Editado
    int exist = -1;                                         // Posición del componente Buscado



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
    public void crearLocation() throws InterruptedException {
        exist = searchScrollElement.elementSearch(company);
        if(exist != -1){
            accessBranch.clickBranches(exist);
            exist = searchScrollElement.elementSearch(location);
            if(exist !=-1){
                WebElement elementLocation = driver.findElement(By.xpath("//span[normalize-space()='"+location+"']"));
                action.contextClick(elementLocation).perform();
                driver.findElement(By.xpath("//div[normalize-space()='New "+location+"']")).click();
                FormsOSM.formCreateLocation(driver,"Location Selenium");
                String message = driver.findElement(By.className("sapMMsgStripMessage")).getAttribute("textContent");
                Assert.assertEquals(message,"The Operation has been Completed Successfully."+ "\n");
            }else{
                Assert.assertEquals("No hay Location","The Operation has been Completed Successfully.");
            }
        }else{
            Assert.assertEquals("No hay Company","The Operation has been Completed Successfully.");
        }
    }

    @Test(priority = 1)
    public void doubleCheckLocation() throws InterruptedException {
        exist = searchScrollElement.elementSearch(company);
        if(exist != -1){
            accessBranch.clickBranches(exist);
            exist = searchScrollElement.elementSearch(location);
            if(exist !=-1){
                WebElement elementLocation = driver.findElement(By.xpath("//span[normalize-space()='"+location+"']"));
                action.contextClick(elementLocation).perform();
                driver.findElement(By.xpath("//div[normalize-space()='New "+location+"']")).click();
                //Llenando Formulario
                FormsOSM.formCreateLocation(driver,"Location Selenium");
                asserts.assertDoubleCheck("Location  Already Exist");
            }else{
                Assert.assertEquals("No hay Location","The Operation has been Completed Successfully.");
            }
        }else{
            Assert.assertEquals("No hay Company","The Operation has been Completed Successfully.");
        }
    }

    @Test(priority = 2)
    public void viewLocationDependencies(){
        exist = searchScrollElement.elementSearch(company);
        if(exist != -1){
            accessBranch.clickBranches(exist);
            exist = searchScrollElement.elementSearch(location);
            if(exist !=-1){
                accessBranch.clickBranches(exist);
                exist = searchScrollElement.elementSearch(newLocation);
                if(exist !=-1){
                    driver.findElement(By.xpath("//span[normalize-space()='"+newLocation+"']")).click();
                    basicControl.btnDependecies();
                    asserts.assertDependecies();
                }else{
                    Assert.assertEquals("No hay"+ newLocation,"The Operation has been Completed Successfully.");
                }
            }else{
                Assert.assertEquals("No hay Location","The Operation has been Completed Successfully.");
            }
        }else{
            Assert.assertEquals("No hay Company","The Operation has been Completed Successfully.");
        }
    }

    @Test(priority = 3)
    public void editarLocation() throws InterruptedException {
        exist = searchScrollElement.elementSearch(company);
        if(exist != -1){
            accessBranch.clickBranches(exist);
            exist = searchScrollElement.elementSearch(location);
            if(exist !=-1){
                accessBranch.clickBranches(exist);
                exist = searchScrollElement.elementSearch(newLocation);
                if(exist !=-1){
                    driver.findElement(By.xpath("//span[normalize-space()='"+newLocation+"']")).click();
                    //Llenando Formulario
                    FormsOSM.formEditLocation(driver,editLocation);
                    String message = driver.findElement(By.className("sapMMsgStripMessage")).getAttribute("textContent");
                    Assert.assertEquals(message,"The Operation has been Completed Successfully."+ "\n");
                }else{
                    Assert.assertEquals("No hay "+ newLocation,"The Operation has been Completed Successfully.");
                }
            }else{
                Assert.assertEquals("No hay Location","The Operation has been Completed Successfully.");
            }
        }else{
            Assert.assertEquals("No hay Company","The Operation has been Completed Successfully.");
        }
    }

    @Test(priority = 4)
    public void eliminarLocation() throws InterruptedException {
        exist = searchScrollElement.elementSearch(company);
        if(exist != -1){
            accessBranch.clickBranches(exist);
            exist = searchScrollElement.elementSearch(location);
            if(exist !=-1){
                accessBranch.clickBranches(exist);
                exist = searchScrollElement.elementSearch(editLocation);
                if(exist !=-1){
                    WebElement elementLocation = driver.findElement(By.xpath("//span[normalize-space()='"+editLocation+"']"));
                    action.contextClick(elementLocation).perform();
                    driver.findElement(By.xpath("//div[normalize-space()='Delete "+location+"']")).click();
                    driver.findElement(By.xpath("//bdi[normalize-space()='Si']")).click();
                    Thread.sleep(500);
                    String message = driver.findElement(By.xpath("//span[@class='sapMText sapUiSelectable sapMTextMaxWidth sapMMsgBoxText']")).getText();
                    if(message.equals("The Operation has been Completed Successfully.")){
                        driver.findElement(By.xpath("//bdi[normalize-space()='OK']")).click();
                    }else{
                        driver.findElement(By.xpath("//bdi[normalize-space()='Cerrar']")).click();
                    }
                }else{
                    Assert.assertEquals("No hay "+editLocation,"The Operation has been Completed Successfully.");
                }
            }else{
                Assert.assertEquals("No hay Location","The Operation has been Completed Successfully.");
            }
        }else{
            Assert.assertEquals("No hay Company","The Operation has been Completed Successfully.");
        }
    }

    @Test(priority = 5)
    public void create_Location_on_Location() throws InterruptedException {
        String parentLocation = "Location Selenium Padre";
        String childLocation = "Location Selenium Hijo 1.2.1";
        exist = searchScrollElement.elementSearch(company);
        if(exist != -1){
            accessBranch.clickBranches(exist);
            exist = searchScrollElement.elementSearch(location);
            if(exist !=-1){
                accessBranch.clickBranches(exist);
                WebElement elementLocation = driver.findElement(By.xpath("//span[normalize-space()='"+location+"']"));
                action.contextClick(elementLocation).perform();
                driver.findElement(By.xpath("//div[normalize-space()='New "+location+"']")).click();
                //Llenando Formulario Padre
                FormsOSM.formCreateLocation(driver,parentLocation);
                String message = driver.findElement(By.className("sapMMsgStripMessage")).getAttribute("textContent");
                Assert.assertEquals(message,"The Operation has been Completed Successfully."+ "\n");
                Thread.sleep(300);
                exist = searchScrollElement.elementSearch(parentLocation);
                if(exist != -1){
                    accessBranch.clickBranches(exist);
                    exist = searchScrollElement.elementSearch(location);
                    if (exist !=-1){
                        Boolean existScroll = driver.findElement(By.id("__xmlview4--mainTree-vsb")).isDisplayed();
                        if(existScroll){
                            WebElement scrollBar = driver.findElement(By.id("__xmlview4--mainTree-vsb"));
                            int scrollTop = js.executeScript("let barra = document.getElementById('__xmlview4--mainTree-vsb');return(barra.scrollTop)").hashCode();
                            js.executeScript("arguments[0].scroll(0,'"+(scrollTop+100)+"')",scrollBar);
                        }
                        List<WebElement> locationList = driver.findElements(By.xpath("//span[normalize-space()='"+location+"']"));
                        action.contextClick(locationList.get(1)).perform();
                        driver.findElement(By.xpath("//div[normalize-space()='New "+location+"']")).click();
                        Thread.sleep(800);
                        //Llenando Formulario Hijo
                        FormsOSM.formCreateLocation(driver,childLocation);
                        message = driver.findElement(By.className("sapMMsgStripMessage")).getAttribute("textContent");
                        Assert.assertEquals(message,"The Operation has been Completed Successfully."+ "\n");
                    }else{
                        Assert.assertEquals("No hay Location 2","The Operation has been Completed Successfully.");
                    }
                }else{
                    Assert.assertEquals("No hay "+parentLocation,"The Operation has been Completed Successfully.");
                }
            }else{
                Assert.assertEquals("No hay Location","The Operation has been Completed Successfully.");
            }
        }else{
            Assert.assertEquals("No hay Company","The Operation has been Completed Successfully.");
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