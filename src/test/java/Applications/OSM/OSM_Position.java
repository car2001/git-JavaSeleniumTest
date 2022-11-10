package Applications.OSM;

import Forms.OSM.FormsPosition;
import Helpers.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

/**
 *
 * @author Carlos Alberto
 */

public class OSM_Position {

    private WebDriver driver;
    private DynamicScroll searchScrollElement;
    private AccessBranch accessBranch;
    private Actions action;
    private JavascriptExecutor js;
    private Asserts asserts;
    private BasicControl basicControl;
    private int exist = -1;
    private FormsPosition formsPosition;


    public OSM_Position(WebDriver driver){
        this.driver = driver;
        this.action = new Actions(driver);
        this.searchScrollElement = new DynamicScroll(driver);
        this.accessBranch = new AccessBranch(driver);
        this.js = (JavascriptExecutor) driver;
        this.asserts = new Asserts(driver);
        this.basicControl = new BasicControl(driver);
        this.formsPosition = new FormsPosition(driver);
    }

    @Test
    public void crearPosition(String company,String orgUnit,String position)  {
        exist = searchScrollElement.elementSearch(company);
        if( exist != -1){
            exist = searchScrollElement.elementSearch("Organizational Units");
            if(exist != -1){
                accessBranch.clickBranches(exist);
                exist = searchScrollElement.elementSearch(orgUnit);
                if(exist != -1){
                    accessBranch.clickBranches(exist);
                    exist = searchScrollElement.elementSearch("Positions");
                    if(exist != -1){
                        WebElement elementPosition = driver.findElement(By.xpath("//span[normalize-space()='Positions']"));
                        action.contextClick(elementPosition).perform();
                        driver.findElement(By.xpath("//div[normalize-space()='New Position' or normalize-space()='Nueva Posición' ]")).click();
                        formsPosition.formCreatePosition(position);
                        asserts.assertSave();
                    }else{
                        asserts.assertSave();
                    }
                }else{
                    asserts.assertSave();
                }
            }else{
                asserts.assertSave();
            }
        }else{
            asserts.assertSave();
        }
    }

    @Test
    public void doubleCheckPosition(String company,String orgUnit,String position){
        exist = searchScrollElement.elementSearch(company);
        if( exist != -1){
            exist = searchScrollElement.elementSearch("Organizational Units");
            if(exist != -1){
                exist = searchScrollElement.elementSearch(orgUnit);
                if(exist != -1){
                    exist = searchScrollElement.elementSearch("Positions");
                    if(exist != -1){
                        WebElement elementPosition = driver.findElement(By.xpath("//span[normalize-space()='Positions']"));
                        action.contextClick(elementPosition).perform();
                        driver.findElement(By.xpath("//div[normalize-space()='New Position' or normalize-space()='Nueva Posición' ]")).click();
                        formsPosition.formCreatePosition(position);
                        String idioma = basicControl.getLanguage();
                        if(idioma.equals("en")){
                            asserts.assertDoubleCheck("Position Already Exist");
                        }else{
                            asserts.assertDoubleCheck("La posición ya existe.");
                        }

                    }else{
                        asserts.assertSave();
                    }
                }else{
                    asserts.assertSave();
                }
            }else{
                asserts.assertSave();
            }
        }else{
            asserts.assertSave();
        }
    }

    @Test
    public void viewPositionDependencies(String company,String orgUnit,String position) {
        exist = searchScrollElement.elementSearch(company);
        if(exist !=-1){
            exist = searchScrollElement.elementSearch("Organizational Units");
            if(exist != -1){
                exist = searchScrollElement.elementSearch(orgUnit);
                if(exist != -1){
                    exist = searchScrollElement.elementSearch("Positions");
                    if(exist !=-1){
                        accessBranch.clickBranches(exist);
                        exist = searchScrollElement.elementSearch(position);
                        if(exist !=-1){
                            driver.findElement(By.xpath("//span[normalize-space()='"+position+"']")).click();
                            basicControl.btnDependecies();
                            asserts.assertDependecies();
                        }else{
                            asserts.assertSave();
                        }
                    }else{
                        asserts.assertSave();
                    }
                }else{
                    asserts.assertSave();
                }
            }else{
                asserts.assertSave();
            }
        }else{
            asserts.assertSave();
        }
    }

    @Test(priority = 3)
    public void editarPosition(String company,String orgUnit,String position,String editPosition) throws InterruptedException {
        exist = searchScrollElement.elementSearch(company);
        if(exist !=-1){
            exist = searchScrollElement.elementSearch("Organizational Units");
            if(exist != -1){
                exist = searchScrollElement.elementSearch(orgUnit);
                if(exist != -1){
                    exist = searchScrollElement.elementSearch("Positions");
                    if(exist != -1){
                        exist = searchScrollElement.elementSearch(position);
                        if(exist !=-1){
                            driver.findElement(By.xpath("//span[normalize-space()='"+position+"']")).click();
                            formsPosition.formEditPosition(editPosition);
                            asserts.assertSave();
                        }else{
                            System.out.println("No hay " + position);
                        }
                    }else{
                        System.out.println("No hay position");
                    }
                }else{
                    System.out.println("No hay" + orgUnit);
                }
            }else{
                System.out.println("No hay Organizational Unit");
            }
        }else{
            System.out.println("No hay company");
        }
    }

    @Test(priority = 4)
    public void eliminarPosition(String company,String orgUnit,String position){
        exist = searchScrollElement.elementSearch(company);
        if(exist !=-1){
            exist = searchScrollElement.elementSearch("Organizational Units");
            if(exist != -1){
                exist = searchScrollElement.elementSearch(orgUnit);
                if(exist != -1){
                    exist = searchScrollElement.elementSearch("Positions");
                    if(exist !=-1){
                        exist = searchScrollElement.elementSearch(position);
                        if(exist !=-1){
                            WebElement elementPosition = driver.findElement(By.xpath("//span[text()='"+position+"']"));
                            String xpathMessage = "//span[@class='sapMText sapUiSelectable sapMTextMaxWidth sapMMsgBoxText']";
                            FormsControl.controlDelete(driver,action,elementPosition,"Position");
                            asserts.assertDelete(xpathMessage);
                            exist = searchScrollElement.elementSearch(orgUnit);
                            accessBranch.clickBranches(exist);
                            exist = searchScrollElement.elementSearch("Organizational Units");
                            accessBranch.clickBranches(exist);
                        }else{
                            System.out.println("No hay " + position);
                        }
                    }else{
                        System.out.println("No hay position");
                    }
                }else{
                    System.out.println("No hay" + orgUnit);
                }
            }else{
                System.out.println("No hay Organizational Unit");
            }
        }else{
            System.out.println("No hay company");
        }
    }



}
