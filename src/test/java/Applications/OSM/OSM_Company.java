package Applications.OSM;

import Forms.OSM.FormsCompany;
import Helpers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;


public class OSM_Company {

    private WebDriver driver;
    private Asserts asserts;
    private BasicControl basicControl;
    private DynamicScroll searchScrollElement;
    private Actions action;
    int exist = -1;
    private FormsCompany formsCompany;


    public OSM_Company(WebDriver driver){
        this.driver = driver;
        this.action = new Actions(driver);
        this.asserts = new Asserts(driver);
        this.basicControl = new BasicControl(driver);
        this.searchScrollElement = new DynamicScroll(driver);
        this.formsCompany = new FormsCompany(driver);
    }

    @Test
    public void crearCompany(String nameCompany){
        WebElement element = driver.findElement(By.xpath("//span[normalize-space()='Companies']"));
        action.contextClick(element).perform();
        driver.findElement(By.xpath("//div[text()='New Company' or text()='Nueva Compañía']")).click();
        formsCompany.formCreateCompany(nameCompany);
        asserts.assertSave();
    }

    @Test
    public void doubleCheckCompany(String nameCompany){
        searchScrollElement.elementSearch("Companies");
        WebElement element = driver.findElement(By.xpath("//span[normalize-space()='Companies']"));
        action.contextClick(element).perform();
        driver.findElement(By.xpath("//div[text()='New Company' or text()='Nueva Compañía']")).click();
        formsCompany.formCreateCompany(nameCompany);
        asserts.assertDoubleCheck("Company Already Exist","La empresa ya existe.");
    }

    @Test
    public void viewCompanyDependencies(String nameCompany){
        exist = searchScrollElement.elementSearch(nameCompany);
        if (exist != -1){
            String xmlview = basicControl.getXmlview();
            driver.findElement(By.xpath("//div[@id='"+xmlview+"--mainTree-tableCtrlCnt']//span[text()='"+nameCompany+"']")).click();
            basicControl.btnDependecies();
            asserts.assertDependecies();
        }else{
            Assert.assertEquals("No hay Company","The Operation has been Completed Successfully.");
        }
    }


    @Test
    public void editarCompany(String nameCompany , String editCompany) throws InterruptedException {
        exist = searchScrollElement.elementSearch(nameCompany);
        if (exist != -1){
            String xmlview = basicControl.getXmlview();
            driver.findElement(By.xpath("//div[@id='"+xmlview+"--mainTree-tableCtrlCnt']//span[text()='"+nameCompany+"']")).click();
            formsCompany.formEditCompany(editCompany);
            asserts.assertSave();
        }else{
            Assert.assertEquals("No hay Company","The Operation has been Completed Successfully.");
        }
    }

    @Test
    public void eliminarCompany(String nameCompany)  {
        exist = searchScrollElement.elementSearch(nameCompany);
        if (exist != -1){
            WebElement empresa = driver.findElement(By.xpath("//span[normalize-space()='"+nameCompany+"']"));
            String xpathMessage = "//span[@class='sapMText sapUiSelectable sapMTextMaxWidth sapMMsgBoxText']";
            FormsControl.controlDelete(driver,action,empresa,"Company","Compañía");
            asserts.assertDelete(xpathMessage);
        }else{
            Assert.assertEquals("No hay Company","The Operation has been Completed Successfully.");
        }
    }

}
