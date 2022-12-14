package Applications.ReleaseManager;


import Forms.ReleaseManager.FormsDeployRequest;
import Helpers.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class RM_DeploymentRequest {

    private WebDriver driver;
    private DynamicScroll searchScrollElement;
    private AccessBranch accessBranch;
    private Actions action;
    private Asserts asserts;
    private int exist = -1;
    private WebDriverWait wait;
    private FormsDeployRequest formsDeployRequest;


    public RM_DeploymentRequest(WebDriver driver) {
        this.driver = driver;
        this.action = new Actions(driver);
        this.searchScrollElement = new DynamicScroll(driver);
        this.accessBranch = new AccessBranch(driver);
        this.asserts = new Asserts(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        this.formsDeployRequest = new FormsDeployRequest(driver);
    }

    @Test
    public void createDeploymentRequestArbol(String nameDR, String project, String release) throws InterruptedException {
        exist = searchScrollElement.elementSearch("Deployment Requests");
        if (exist != -1) {
            accessBranch.clickBranches(exist);
            WebElement btnOpen = driver.findElement(By.xpath("//span[text()='Open']"));
            action.contextClick(btnOpen).build().perform();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//div[text()='New Deployment Request' or text()='Nueva Solicitud de Despliegue']")).click();
            formsDeployRequest.createDeploymentRequest(nameDR, project, release);
            asserts.assertSaveDR();
        }

    }


    @Test
    public void editDeploymentRequestTabla(String nameDR, String editDR, String project, String release) throws InterruptedException {
        driver.findElement(By.xpath("//span[text()='Open']")).click();
        ChargePopPup.PopPupGeneral(driver,wait);
        WebElement titulo = driver.findElement(By.xpath("//span[text()='Lista de Solicitudes de Instalación' or text()='Deployment Package List']"));
        wait.until(ExpectedConditions.visibilityOf(titulo));
        List<WebElement> buttons = driver.findElements(By.xpath("//span[@class ='sapMBtnInner sapMBtnHoverable sapMFocusable sapMBtnIconFirst sapMBtnDefault']"));
        String xpos = searchScrollElement.searchElementTable(project,"Open",release,nameDR);
        xpos = xpos.substring(0,2);
        if(xpos != " "){
            driver.findElement(By.xpath("//tr//span[text()='"+xpos+"']")).click();
            action.moveToElement(buttons.get(1)).click().perform();
            formsDeployRequest.editDeploymentRequest(editDR);
            ChargePopPup.PopPupGeneral(driver,wait);
            asserts.assertSave();
        }else{
            Assert.assertEquals("No se encontro el DR","Si hay DR");
        }

    }

    public void eliminarDeploymentRequest(String project,String release,String nameDR) {
        WebElement titulo = driver.findElement(By.xpath("//span[text()='Lista de Solicitudes de Instalación' or text()='Deployment Package List']"));
        wait.until(ExpectedConditions.visibilityOf(titulo));
        String xpos = searchScrollElement.searchElementTable(project,"Open",release,nameDR);
        xpos = xpos.substring(0,2);
        if(xpos != ""){
            driver.findElement(By.xpath("//tr//span[text()='"+xpos+"']")).click();
            driver.findElement(By.xpath("//button[@title='Borrar' or @title='Delete']")).click();
            driver.findElement(By.xpath("//bdi[normalize-space()='Sí' or normalize-space()='Yes']")).click();
            asserts.assertSave();
        }else{
            Assert.assertEquals("No se encontro el DR","Si hay DR");
        }

    }

}
