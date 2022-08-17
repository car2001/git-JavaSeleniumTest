package Applications.OSM;

import Forms.FormsOSM;
import Helpers.*;
import HomePage.Login;
import HomePage.LoginApplications;
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
    private String chosen_browser = "Chrome";

    Login login;
    DynamicScroll searchScrollElement;
    SelectBrowser browser = new SelectBrowser(driver);
    AccessBranch accessBranch;
    Actions action;
    JavascriptExecutor js;
    Asserts asserts;
    BasicControl basicControl;

    String company = "Company Selenium";
    String elemen_unit = "Organizational Unit";
    String elemen_org = "Organizational Unit Selenium";
    String elemen_org_edit = "Organizational Unit Selenium 1.0.1";
    int exist ;

    @BeforeMethod
    public void setUp() throws InterruptedException {
        browser.chooseBrowser(chosen_browser);
        driver = browser.getDriver();
        action = new Actions(driver);
        js = (JavascriptExecutor) driver;
        accessBranch = new AccessBranch(driver);
        asserts = new Asserts(driver);
        basicControl = new BasicControl(driver);
        searchScrollElement = new DynamicScroll(driver);
        login = new Login(driver);
        login.loginPage();
        LoginApplications.loginOSM(driver);
    }

    @Test
    public void crearOrgani_Unit() throws InterruptedException {
        exist = searchScrollElement.elementSearch(company);
        if(exist != -1){
            accessBranch.clickBranches(exist);
            exist = searchScrollElement.elementSearch(elemen_unit);
            if(exist!=-1){
                WebElement element = driver.findElement(By.xpath("//span[normalize-space()='"+elemen_unit+"']"));
                action.contextClick(element).perform();
                driver.findElement(By.xpath("//div[normalize-space()='New "+elemen_unit+"']")).click();
                Thread.sleep(500);
                //Llenando Formulario
                FormsOSM.formCreateOrganization(driver,elemen_org);
                asserts.assertSave();
            }else{
                Assert.assertEquals("No hay Organizational Unit", "The Operation has been Completed Successfully.");
           }
        }else{
            Assert.assertEquals("No se encontro la compañia "+company, "The Operation has been Completed Successfully.");
        }
    }

    @Test(priority = 1)
    public void doubleCheckOrgani_Unit() throws InterruptedException {
        exist = searchScrollElement.elementSearch(company);
        if(exist != -1){
            accessBranch.clickBranches(exist);
            exist = searchScrollElement.elementSearch(elemen_unit);
            if(exist!=-1){
                WebElement element = driver.findElement(By.xpath("//span[normalize-space()='"+elemen_unit+"']"));
                action.contextClick(element).perform();
                driver.findElement(By.xpath("//div[normalize-space()='New "+elemen_unit+"']")).click();
                Thread.sleep(500);
                //Llenando Formulario
                FormsOSM.formCreateOrganization(driver,elemen_org);
                asserts.assertDoubleCheck("Organizational Unit Already Exist");
            }else{
                Assert.assertEquals("No hay Organizational Unit", "Organizational Unit Already Exist");
            }
        }else{
            Assert.assertEquals("No se encontro la compañia "+ company, "Organizational Unit Already Exist");
        }
    }


    @Test(priority = 2)
    public void viewOrgani_UnitDependencies() throws InterruptedException {
        exist = searchScrollElement.elementSearch(company);
        if(exist != -1){
            accessBranch.clickBranches(exist);
            exist = searchScrollElement.elementSearch(elemen_unit);
            if(exist!=-1){
                accessBranch.clickBranches(exist);
                exist = searchScrollElement.elementSearch(elemen_org);
                if(exist !=1){
                    driver.findElement(By.xpath("//span[normalize-space()='"+elemen_org+"']")).click();
                    Thread.sleep(200);
                    driver.findElement(By.id("__xmlview4--viewDependencies-img")).click();
                    Thread.sleep(1200);
                    String message = driver.findElement(By.id("__xmlview4--dependenciesTableTitle-inner")).getText();
                    Assert.assertEquals(message,"Dependencies List");
                    Thread.sleep(500);
                }else{
                    Assert.assertEquals("No hay Sub" +elemen_org,"Dependencies List");
                }
            }else{
                Assert.assertEquals("No hay Organizational Unit","Dependencies List");
            }
        }else{
            Assert.assertEquals("No se encontro la compañia "+ company,"Dependencies List");
        }
    }

    @Test(priority = 3)
    public void editarOrgani_Unit() throws InterruptedException {
        exist = searchScrollElement.elementSearch(company);
        if(exist != -1){
            accessBranch.clickBranches(exist);
            exist = searchScrollElement.elementSearch(elemen_unit);
            if(exist!=-1){
                accessBranch.clickBranches(exist);
                exist = searchScrollElement.elementSearch(elemen_org);
                if(exist !=1){
                    driver.findElement(By.xpath("//span[normalize-space()='"+elemen_org+"']")).click();
                    Thread.sleep(1000);
                    FormsOSM.formEditOrganization(driver,elemen_org_edit);
                }else{
                    System.out.println("No hay Sub" +elemen_org );
                }
            }else{
                System.out.println("No hay Organizational Unit");
            }
        }else{
            js.executeScript("alert('"+" No se encontro la compañia "+company+"')");
            Thread.sleep(500);
        }
    }

    @Test(priority = 4)
    public void eliminarOrgani_Unit() throws InterruptedException {
        exist = searchScrollElement.elementSearch(company);
        if(exist != -1){
            accessBranch.clickBranches(exist);
            exist = searchScrollElement.elementSearch(elemen_unit);
            if(exist!=-1){
                accessBranch.clickBranches(exist);
                exist = searchScrollElement.elementSearch(elemen_org_edit);
                if(exist != -1){
                    WebElement org_delete = driver.findElement(By.xpath("//span[normalize-space()='"+elemen_org_edit+"']"));
                    action.contextClick(org_delete).perform();
                    driver.findElement(By.xpath("//div[normalize-space()='Delete "+elemen_unit+"']")).click();
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
                    System.out.println("No hay" +elemen_org_edit );
                }
            }else{
                System.out.println("No hay componente Organizational Unit");
            }
        }else{
            js.executeScript("alert('"+" No se encontro la compañia "+company+"')");
            Thread.sleep(500);
        }
    }

    @Test(enabled = false)
    public void crearOrgani_Unit_on_OrganiUnit() throws InterruptedException {
        String parentUnit = "Organizational Unit Selenium Padre";
        String childUnit = "Organizational Unit Selenium Hijo";
        exist = searchScrollElement.elementSearch(company);
        if(exist != -1){
            accessBranch.clickBranches(exist);
            exist = searchScrollElement.elementSearch(elemen_unit);
            if(exist!=-1){
                accessBranch.clickBranches(exist);
                WebElement element = driver.findElement(By.xpath("//span[normalize-space()='Organizational Unit']"));
                action.contextClick(element).perform();
                driver.findElement(By.xpath("//div[normalize-space()='New "+elemen_unit+"']")).click();
                Thread.sleep(2000);
                FormsOSM.formCreateOrganization(driver,parentUnit);
                String message = driver.findElement(By.className("sapMMsgStripMessage")).getAttribute("textContent");
                Assert.assertEquals(message,"The Operation has been Completed Successfully."+ "\n");
                Thread.sleep(300);
                exist = searchScrollElement.elementSearch(parentUnit);
                if(exist != -1){
                    accessBranch.clickBranches(exist);
                    exist = searchScrollElement.elementSearch(elemen_unit);
                    if (exist !=-1){
                        Boolean existScroll = driver.findElement(By.id("__xmlview4--mainTree-vsb")).isDisplayed();
                        if(existScroll){
                            WebElement scrollBar = driver.findElement(By.id("__xmlview4--mainTree-vsb"));
                            int scrollTop = js.executeScript("let barra = document.getElementById('__xmlview4--mainTree-vsb');return(barra.scrollTop)").hashCode();
                            js.executeScript("arguments[0].scroll(0,'"+(scrollTop+100)+"')",scrollBar);
                        }
                        List<WebElement> locationList = driver.findElements(By.xpath("//span[normalize-space()='"+elemen_unit+"']"));
                        action.contextClick(locationList.get(1)).perform();
                        driver.findElement(By.xpath("//div[normalize-space()='New "+elemen_unit+"']")).click();
                        Thread.sleep(500);
                        //Llenando Formulario
                        FormsOSM.formCreateOrganization(driver,childUnit);
                        message = driver.findElement(By.className("sapMMsgStripMessage")).getAttribute("textContent");
                        Assert.assertEquals(message,"The Operation has been Completed Successfully."+ "\n");
                    }else{
                        System.out.println("No hay Organizational Unit2");
                    }
                }else{
                    System.out.println("No hay " + parentUnit);
                }

            }else{
                System.out.println("No hay Organizational Unit");
            }
        }else{
            js.executeScript("alert('"+" No se encontro la compañia "+company+"')");
            Thread.sleep(500);
        }
    }

    @AfterMethod
    public void tearDown()  {
        if (driver != null){
            //driver.quit();
        }
    }

    @AfterClass
    public static void tearDownAfterClass(){
        System.out.println("Terminaron los test");
    }

}
