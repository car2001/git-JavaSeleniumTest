package Applications.OSM;

import Forms.OSM.FormsExternalPartner;
import Helpers.Asserts;
import Helpers.BasicControl;
import Helpers.DynamicScroll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class OSM_ExternalPartner {

    private WebDriver driver;
    private Asserts asserts;
    private BasicControl basicControl;
    private DynamicScroll searchScrollElement;
    private Actions action;
    int exist = -1;
    private FormsExternalPartner formsExternalPartner;


    public OSM_ExternalPartner(WebDriver driver){
        this.driver = driver;
        this.action = new Actions(driver);
        this.asserts = new Asserts(driver);
        this.basicControl = new BasicControl(driver);
        this.searchScrollElement = new DynamicScroll(driver);
        this.formsExternalPartner = new FormsExternalPartner(driver);
    }


    @Test
    public void crearExternalPartner(String nameExPartner,String user){
        searchScrollElement.elementSearch("External Partners");
        driver.findElement(By.xpath("//span[normalize-space()='External Partners']")).click();
        WebElement titleExternal = driver.findElement(By.xpath("//span[contains(@id,'--titleComponentList')][text()='External Partner List' or text()='Lista de Socios Externos']"));
        driver.findElement(By.xpath("//button[@title='Add' or @title='Agregar'][@aria-label='Add' or @aria-label='Agregar']")).click();
        formsExternalPartner.formCreateExternalPartner(nameExPartner,user);
        asserts.assertSave();
    }

    @Test
    public void editarExternalPartner(String nameExPartner,String user,String editExPartner) throws InterruptedException {
        int exist = searchScrollElement.searchElementTableOSM(nameExPartner,user);

        driver.findElement(By.xpath("//button[@title='Edit' or @title='Editar'][@aria-label='Edit' or @aria-label='Editar']")).click();
        formsExternalPartner.formEditExternalPartner(editExPartner);
        asserts.assertSave();
    }

    @Test
    public void eliminarExternalPartner(String nameExPartner,String user) throws InterruptedException {
        int exist = searchScrollElement.searchElementTableOSM(nameExPartner, user);
        driver.findElement(By.xpath("//td[contains(@id,'--tblComponentList-rows-row"+exist+"-col0')]")).click();
        System.out.println(exist);
        driver.findElement(By.xpath("//button[@title='Delete' or @title='Borrar'][@aria-label='Delete' or @aria-label='Borrar']")).click();
        driver.findElement(By.xpath("//bdi[normalize-space()='Sí' or normalize-space()='Yes']")).click();
        asserts.assertSave();
    }
}
