package Applications.OSM;

import Forms.OSM.FormsLocation;
import Helpers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;


public class OSM_Location {

    private WebDriver driver;
    private DynamicScroll searchScrollElement;
    private AccessBranch accessBranch;
    private Actions action;
    private JavascriptExecutor js;
    private Asserts asserts;
    private BasicControl basicControl;
    private int exist = -1;
    private FormsLocation formsLocation;


    public OSM_Location(WebDriver driver){
        this.driver = driver;
        this.action = new Actions(driver);
        this.searchScrollElement = new DynamicScroll(driver);
        this.accessBranch = new AccessBranch(driver);
        this.js = (JavascriptExecutor) driver;
        this.asserts = new Asserts(driver);
        this.basicControl = new BasicControl(driver);
        this.formsLocation = new FormsLocation(driver);
    }


    @Test()
    public void crearLocation(String company, String location)  {
        exist = searchScrollElement.elementSearch(company);
        if(exist != -1){
            accessBranch.clickBranches(exist);
            exist = searchScrollElement.elementSearch("Locations");
            if(exist !=-1){
                WebElement elementLocation = driver.findElement(By.xpath("//span[normalize-space()='Locations']"));
                action.contextClick(elementLocation).perform();
                driver.findElement(By.xpath("//div[normalize-space()='New Location' or normalize-space()='Nueva Ubicación']")).click();
                formsLocation.formCreateLocation(location);
                asserts.assertSave();
            }else{
                Assert.assertEquals("No hay Location","The Operation has been Completed Successfully.");
            }
        }else{
            Assert.assertEquals("No hay Company","The Operation has been Completed Successfully.");
        }
    }

    @Test
    public void doubleCheckLocation(String company,String location)  {
        exist = searchScrollElement.elementSearch(company);
        if(exist != -1){
            exist = searchScrollElement.elementSearch("Locations");
            if(exist !=-1){
                WebElement elementLocation = driver.findElement(By.xpath("//span[normalize-space()='Locations']"));
                action.contextClick(elementLocation).perform();
                driver.findElement(By.xpath("//div[normalize-space()='New Location' or normalize-space()='Nueva Ubicación']")).click();
                formsLocation.formCreateLocation(location);
                String idioma = basicControl.getLanguage();
                if(idioma.equals("en")){
                    asserts.assertDoubleCheck("Location Already Exist");
                }else{
                    asserts.assertDoubleCheck("La ubicación ya existe.");
                }
            }else{
                Assert.assertEquals("No hay Location","The Operation has been Completed Successfully.");
            }
        }else{
            Assert.assertEquals("No hay Company","The Operation has been Completed Successfully.");
        }
    }

    @Test
    public void viewLocationDependencies(String company,String location){
        exist = searchScrollElement.elementSearch(company);
        if(exist != -1){
            exist = searchScrollElement.elementSearch("Locations");
            if(exist != -1){
                accessBranch.clickBranches(exist);
                exist = searchScrollElement.elementSearch(location);
                if(exist != -1){
                    driver.findElement(By.xpath("//span[text()='"+location+"']")).click();
                    basicControl.btnDependecies();
                    asserts.assertDependecies();
                }
            }
        }

    }

    @Test
    public void editarLocation(String company , String location, String editLocation) throws InterruptedException {
        exist = searchScrollElement.elementSearch(company);
        if(exist != -1){
            exist = searchScrollElement.elementSearch("Locations");
            if(exist != -1){
                exist = searchScrollElement.elementSearch(location);
                if(exist != -1){
                    driver.findElement(By.xpath("//span[text()='"+location+"']")).click();
                    formsLocation.formEditLocation(editLocation);
                    asserts.assertSave();
                    exist = searchScrollElement.elementSearch("Locations");
                    accessBranch.clickBranches(exist);
                }
            }
        }
    }

    @Test
    public void eliminarLocation(String company , String location) {
        exist = searchScrollElement.elementSearch(company);
        if(exist != -1){
            exist = searchScrollElement.elementSearch("Locations");
            if(exist != -1){
                accessBranch.clickBranches(exist);
                exist = searchScrollElement.elementSearch(location);
                if(exist != -1){
                    WebElement elementLocation = driver.findElement(By.xpath("//span[text()='"+location+"']"));
                    String xpathMessage = "//span[@class='sapMText sapUiSelectable sapMTextMaxWidth sapMMsgBoxText']";
                    FormsControl.controlDelete(driver,action,elementLocation,"Location");
                    asserts.assertDelete(xpathMessage);
                }
            }
        }
    }




}