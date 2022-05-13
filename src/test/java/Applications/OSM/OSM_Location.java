package Applications.OSM;

import Helpers.Dynamic_Scroll_Search;
import Forms.FormsOSM;
import Helpers.SelectBrowser;
import HomepageFunctions.Home_Page;
import HomepageFunctions.Login_Applications;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;


public class OSM_Location {

    private WebDriver driver;
    private String chosen_browser = "Chrome";

    Home_Page login;
    Dynamic_Scroll_Search searchScrollElement;
    SelectBrowser browser = new SelectBrowser(driver);
    Actions action;
    JavascriptExecutor js;

    String company = "Company Selenium";                    //Compañia
    String location = "Location";                           // Componente a Buscar
    String newLocation = "Location Selenium";               // Location Creado
    String editLocation = "Location Selenium Editado";      // Location Editado
    int exist = -1;                                         // Posición del componente Buscado
    String desple;                                          // Desplegar en los componentes


    @BeforeMethod
    public void setup(){
        browser.chooseBrowser(chosen_browser);
        driver = browser.getDriver();
        js = (JavascriptExecutor) driver;
        action = new Actions(driver);
        searchScrollElement = new Dynamic_Scroll_Search(driver);
        login = new Home_Page(driver);
        login.loginPage("cpingo","1234");
        Login_Applications.loginOSM(driver);
    }

    @Test()
    public void crearLocation() throws InterruptedException {
        exist = searchScrollElement.elementSearch(company);
        if(exist != -1){
            desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
            driver.findElement(By.id(desple)).click();
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
            desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
            driver.findElement(By.id(desple)).click();
            exist = searchScrollElement.elementSearch(location);
            if(exist !=-1){
                WebElement elementLocation = driver.findElement(By.xpath("//span[normalize-space()='"+location+"']"));
                action.contextClick(elementLocation).perform();
                driver.findElement(By.xpath("//div[normalize-space()='New "+location+"']")).click();
                //Llenando Formulario
                FormsOSM.formCreateLocation(driver,"Location Selenium");
                String message = driver.findElement(By.className("sapMMsgStripMessage")).getAttribute("textContent");
                Assert.assertEquals(message,"Location Already Exist");
                driver.findElement(By.id("__xmlview4--cancel-img")).click();
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
            desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
            driver.findElement(By.id(desple)).click();
            exist = searchScrollElement.elementSearch(location);
            if(exist !=-1){
                desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
                driver.findElement(By.id(desple)).click();
                exist = searchScrollElement.elementSearch(newLocation);
                if(exist !=-1){
                    driver.findElement(By.xpath("//span[normalize-space()='"+newLocation+"']")).click();
                    driver.findElement(By.id("__xmlview4--viewDependencies-img")).click();
                    String message = driver.findElement(By.id("__xmlview4--dependenciesTableTitle-inner")).getText();
                    Assert.assertEquals(message,"Dependencies List");
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
            desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
            driver.findElement(By.id(desple)).click();
            exist = searchScrollElement.elementSearch(location);
            if(exist !=-1){
                desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
                driver.findElement(By.id(desple)).click();
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
            desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
            driver.findElement(By.id(desple)).click();
            exist = searchScrollElement.elementSearch(location);
            if(exist !=-1){
                desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
                driver.findElement(By.id(desple)).click();
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
        String childLocation = "Location Selenium Hijo 1.2";
        exist = searchScrollElement.elementSearch(company);
        if(exist != -1){
            desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
            driver.findElement(By.id(desple)).click();
            exist = searchScrollElement.elementSearch(location);
            if(exist !=-1){
                WebElement elementLocation = driver.findElement(By.xpath("//span[normalize-space()='"+location+"']"));
                action.contextClick(elementLocation).perform();
                driver.findElement(By.xpath("//div[normalize-space()='New "+location+"']")).click();
                //Llenando Formulario Padre
                FormsOSM.formCreateLocation(driver,parentLocation);
                String message = driver.findElement(By.className("sapMMsgStripMessage")).getAttribute("textContent");
                Assert.assertEquals(message,"The Operation has been Completed Successfully."+ "\n");
                Thread.sleep(300);
                desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
                driver.findElement(By.id(desple)).click();
                exist = searchScrollElement.elementSearch(parentLocation);
                if(exist != -1){
                    desple = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
                    driver.findElement(By.id(desple)).click();
                    exist = searchScrollElement.elementSearch(location);
                    if (exist !=-1){
                        Boolean existScroll = driver.findElement(By.id("__xmlview4--mainTree-vsb")).isDisplayed();
                        if(existScroll){
                            String xpathCompany = "//span[@class='sapMText sapUiSelectable sapMTextMaxWidth {Tree>class}' or @class='sapMText sapUiSelectable sapMTextBreakWord sapMTextMaxWidth {Tree>class}']";

                            //Obtenemos la lista de Objetos
                            List<WebElement>  elementTable = driver.findElements(By.xpath(xpathCompany));
                            //Creamos nuevo arreglo
                            List<String> nameElement = new ArrayList<>();

                            //Pasamos los nombres de los Elementos al nuevo array
                            for(int i = 0; i<=elementTable.size()-1;i=i+1){
                                nameElement.add(i,elementTable.get(i).getText());
                            }
                            WebElement scrollBar = driver.findElement(By.id("__xmlview4--mainTree-vsb"));
                            int clientHeight = js.executeScript("let barra = document.getElementById('__xmlview4--mainTree-vsb');return(barra.clientHeight)").hashCode();
                            int scrollHeight = js.executeScript("let barra = document.getElementById('__xmlview4--mainTree-vsb');return(barra.scrollHeight)").hashCode();
                            int numVeces,iterator;

                            numVeces = scrollHeight/clientHeight; // Numero de veces para repetir el bucle
                            iterator = 0;
                            // Verificamos
                            while (iterator<=numVeces+1){
                                if(nameElement.lastIndexOf("Location") != -1){
                                    break;
                                }else{
                                    iterator = iterator+1;
                                    int multiplo = (clientHeight*iterator)+1 ;
                                    js.executeScript("arguments[0].scroll(0,'"+multiplo+"')",scrollBar);
                                    elementTable = driver.findElements(By.xpath(xpathCompany));
                                    nameElement.clear();
                                    for(int i = 0; i<=elementTable.size()-1;i=i+1){
                                        nameElement.add(i,elementTable.get(i).getText());
                                    }

                                }
                            }
                            Thread.sleep(1000);
                        }
                        List<WebElement> locationList = driver.findElements(By.xpath("//span[normalize-space()='"+location+"']"));
                        action.contextClick(locationList.get(1)).perform();
                        driver.findElement(By.xpath("//div[normalize-space()='New "+location+"']")).click();
                        Thread.sleep(500);
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
        //driver.quit();
    }

    @AfterClass
    public static void tearDownAfterClass(){
        System.out.println("Terminaron los test");
    }


}