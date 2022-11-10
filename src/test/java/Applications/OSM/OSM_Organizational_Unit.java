package Applications.OSM;

import Forms.OSM.FormsOrgUnit;
import Helpers.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 *
 * @author Carlos Alberto
 */
public class OSM_Organizational_Unit {

    private WebDriver driver;
    private DynamicScroll searchScrollElement;
    private AccessBranch accessBranch;
    private Actions action;
    private JavascriptExecutor js;
    private Asserts asserts;
    private BasicControl basicControl;
    private int exist = -1;
    private FormsOrgUnit formsOrgUnit;

    public OSM_Organizational_Unit(WebDriver driver){
        this.driver = driver;
        this.action = new Actions(driver);
        this.searchScrollElement = new DynamicScroll(driver);
        this.accessBranch = new AccessBranch(driver);
        this.js = (JavascriptExecutor) driver;
        this.asserts = new Asserts(driver);
        this.basicControl = new BasicControl(driver);
        this.formsOrgUnit = new FormsOrgUnit(driver);
    }

    @Test
    public void crearOrgani_Unit(String company, String orgUnit) {
        exist = searchScrollElement.elementSearch(company);
        if(exist != -1){
            exist = searchScrollElement.elementSearch("Organizational Units");
            if(exist!=-1){
                WebElement element = driver.findElement(By.xpath("//span[normalize-space()='Organizational Units']"));
                action.contextClick(element).perform();
                driver.findElement(By.xpath("//div[normalize-space()='New Organizational Unit' or normalize-space()='Nueva Unidad Organizacional']")).click();
                formsOrgUnit.formCreateOrganization(orgUnit);
                asserts.assertSave();
            }else{
                Assert.assertEquals("No hay Organizational Unit", "The Operation has been Completed Successfully.");
           }
        }else{
            Assert.assertEquals("No se encontro la compañia "+company, "The Operation has been Completed Successfully.");
        }
    }

    @Test(priority = 1)
    public void doubleCheckOrgani_Unit(String company, String orgUnit)  {
        exist = searchScrollElement.elementSearch(company);
        if(exist != -1){
            exist = searchScrollElement.elementSearch("Organizational Units");
            if(exist!=-1){
                WebElement element = driver.findElement(By.xpath("//span[normalize-space()='Organizational Units']"));
                action.contextClick(element).perform();
                driver.findElement(By.xpath("//div[normalize-space()='New Organizational Unit' or normalize-space()='Nueva Unidad Organizacional']")).click();
                formsOrgUnit.formCreateOrganization(orgUnit);
                String idioma = basicControl.getLanguage();
                if(idioma.equals("en")){
                    asserts.assertDoubleCheck("Organizational Unit Already Exist");
                }else{
                    asserts.assertDoubleCheck("La unidad organizacional ya existe.");
                }
            }else{
                Assert.assertEquals("No hay Organizational Unit", "Organizational Unit Already Exist");
            }
        }else{
            Assert.assertEquals("No se encontro la compañia "+ company, "Organizational Unit Already Exist");
        }
    }


    @Test(priority = 2)
    public void viewOrgani_UnitDependencies(String company,String orgUnit){
        exist = searchScrollElement.elementSearch(company);
        if(exist != -1){
            exist = searchScrollElement.elementSearch("Organizational Units");
            if(exist!=-1){
                accessBranch.clickBranches(exist);
                exist = searchScrollElement.elementSearch(orgUnit);
                if(exist !=1){
                    driver.findElement(By.xpath("//span[normalize-space()='"+orgUnit+"']")).click();
                    basicControl.btnDependecies();
                    asserts.assertDependecies();
                }else{
                    Assert.assertEquals("No hay Sub" +orgUnit,"Dependencies List");
                }
            }else{
                Assert.assertEquals("No hay Organizational Unit","Dependencies List");
            }
        }else{
            Assert.assertEquals("No se encontro la compañia "+ company,"Dependencies List");
        }
    }

    @Test(priority = 3)
    public void editarOrgani_Unit(String company , String orgUnit, String editOrgUnit) throws InterruptedException {
        exist = searchScrollElement.elementSearch(company);
        if(exist != -1){
            exist = searchScrollElement.elementSearch("Organizational Units");
            if(exist!=-1){
                exist = searchScrollElement.elementSearch(orgUnit);
                if(exist !=1){
                    driver.findElement(By.xpath("//span[normalize-space()='"+orgUnit+"']")).click();
                    formsOrgUnit.formEditOrganization(editOrgUnit);
                    asserts.assertSave();
                    exist = searchScrollElement.elementSearch("Organizational Units");
                    accessBranch.clickBranches(exist);
                }else{
                    System.out.println("No hay Sub" +orgUnit );
                }
            }else{
                System.out.println("No hay Organizational Unit");
            }
        }else{
            Assert.assertEquals("No se encontro la compañia "+ company,"Dependencies List");
        }
    }

    @Test(priority = 4)
    public void eliminarOrgani_Unit(String company , String orgUnit){
        exist = searchScrollElement.elementSearch(company);
        if(exist != -1){
            exist = searchScrollElement.elementSearch("Organizational Units");
            if(exist!=-1){
                accessBranch.clickBranches(exist);
                exist = searchScrollElement.elementSearch(orgUnit);
                if(exist != -1){
                    WebElement org_Unit = driver.findElement(By.xpath("//span[normalize-space()='"+orgUnit+"']"));
                    String xpathMessage = "//span[@class='sapMText sapUiSelectable sapMTextMaxWidth sapMMsgBoxText']";
                    FormsControl.controlDelete(driver,action,org_Unit,"Organizational Unit");
                    asserts.assertDelete(xpathMessage);
                }else{
                    System.out.println("No hay" +orgUnit );
                }
            }else{
                System.out.println("No hay componente Organizational Unit");
            }
        }else{
            Assert.assertEquals("No se encontro la compañia "+ company,"Dependencies List");
        }
    }



}
