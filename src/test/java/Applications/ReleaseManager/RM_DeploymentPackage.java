package Applications.ReleaseManager;

import Forms.ReleaseManager.FormsDeployPackage;
import Helpers.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class RM_DeploymentPackage {

    private WebDriver driver;
    private DynamicScroll searchScrollElement;
    private AccessBranch accessBranch;
    private Actions action;
    private Asserts asserts;
    private int exist = -1;
    private WebDriverWait wait;
    private FormsDeployPackage formsDeployPackage;



    public RM_DeploymentPackage(WebDriver driver){
        this.driver = driver;
        this.action = new Actions(driver);
        this.searchScrollElement = new DynamicScroll(driver);
        this.accessBranch = new AccessBranch(driver);
        this.asserts = new Asserts(driver);
        this.formsDeployPackage = new FormsDeployPackage(driver);
        this.wait = new WebDriverWait(driver,Duration.ofSeconds(100));
    }


    @Test
    public void crearDeploymentPackageArbol(String nameDP, String project, String release) throws InterruptedException {
        exist = searchScrollElement.elementSearch("Deployment Packages");
        if (exist != -1) {
            accessBranch.clickBranches(exist);
            WebElement btnOpen = driver.findElement(By.xpath("//span[text()='Open']"));
            action.contextClick(btnOpen).perform();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//div[text()='New Deployment Package' or text()='Nuevo Paquete de Despliegue']")).click();
            formsDeployPackage.createDeploymentPackage(nameDP,project,release);
            asserts.assertSaveDP();
        }

    }

    public void editarDeploymentPackageTabla(String nameDP,String editDP, String project, String release) throws InterruptedException {
        driver.findElement(By.xpath("//span[text()='Open']")).click();
        ChargePopPup.PopPupGeneral(driver,wait);
        WebElement titulo = driver.findElement(By.xpath("//span[text()='Lista de Paquetes de Instalación' or text()='Deployment Package List']"));
        wait.until(ExpectedConditions.visibilityOf(titulo));
        List<WebElement> buttons = driver.findElements(By.xpath("//span[@class ='sapMBtnInner sapMBtnHoverable sapMFocusable sapMBtnIconFirst sapMBtnDefault']"));
        String xpos = searchScrollElement.searchElementTable(project,"Open",release,nameDP);
        xpos = xpos.substring(0,2);
        if(xpos != " "){
            driver.findElement(By.xpath("//tr//span[text()='"+xpos+"']")).click();
            action.moveToElement(buttons.get(1)).click().perform();
            formsDeployPackage.editDeploymentPackage(editDP);
            ChargePopPup.PopPupGeneral(driver,wait);
            asserts.assertSave();
        }else{
            Assert.assertEquals("No se encontro el CC","Si hay CC");
        }

    }

    public void eliminarDeploymentPackage(String project,String release,String nameDP) {
        WebElement titulo = driver.findElement(By.xpath("//span[text()='Lista de Paquetes de Instalación' or text()='Deployment Package List']"));
        wait.until(ExpectedConditions.visibilityOf(titulo));
        String xpos = searchScrollElement.searchElementTable(project,"Open",release,nameDP);
        xpos = xpos.substring(0,2);
        System.out.println(xpos);
        if(xpos != " "){
            driver.findElement(By.xpath("//tr//span[text()='"+xpos+"']")).click();
            driver.findElement(By.xpath("//button[@title='Borrar' or @title='Delete']")).click();
            driver.findElement(By.xpath("//bdi[normalize-space()='Sí' or normalize-space()='Yes']")).click();
            asserts.assertSave();
        }else{
            Assert.assertEquals("No se encontro el DP","Si hay DP");
        }

    }

}
