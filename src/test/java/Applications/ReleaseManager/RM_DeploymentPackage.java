package Applications.ReleaseManager;

import Forms.ReleaseManager.FormsDeployPackage;
import Helpers.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class RM_DeploymentPackage {

    private WebDriver driver;
    private DynamicScroll searchScrollElement;
    private AccessBranch accessBranch;
    private Actions action;
    private JavascriptExecutor js;
    private Asserts asserts;
    private BasicControl basicControl;
    private int exist = -1;
    private FormsDeployPackage formsDeployPackage;
    private WebDriverWait wait;

    String newDR = "DR_SELENIUM";
    String componente = "Deployment Package";
    String newDeployment = "DP_SELENIUM";
    String project = "Proyecto Release Selenium";
    String release = "Release Selenium";

    public RM_DeploymentPackage(WebDriver driver){
        this.driver = driver;
        this.action = new Actions(driver);
        this.searchScrollElement = new DynamicScroll(driver);
        this.accessBranch = new AccessBranch(driver);
        this.js = (JavascriptExecutor) driver;
        this.asserts = new Asserts(driver);
        this.basicControl = new BasicControl(driver);
        this.formsDeployPackage = new FormsDeployPackage(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
            asserts.assertSaveDR();
        }

    }

    public void editarDeploymentPackageArbol(String nameDP,String editDP, String project, String release) throws InterruptedException {
        exist = searchScrollElement.elementSearch("Deployment Packages");
        if (exist != -1) {
            accessBranch.clickBranches(exist);
            WebElement btnOpen = driver.findElement(By.xpath("//span[text()='Open']"));
            action.contextClick(btnOpen).perform();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//div[text()='New Deployment Package' or text()='Nuevo Paquete de Despliegue']")).click();
            formsDeployPackage.createDeploymentPackage(nameDP,project,release);
            asserts.assertSaveDR();
        }

    }


}
