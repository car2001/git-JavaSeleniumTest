package Applications.ReleaseManager;

import Forms.FormsRM;
import Forms.ReleaseManager.FormsChangeContainer;
import Forms.ReleaseManager.FormsProject;
import Helpers.*;
import HomePage.Login;
import HomePage.LoginApplications;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;


public class RM_ChangeContainer {
    private WebDriver driver;
    private DynamicScroll searchScrollElement;
    private AccessBranch accessBranch;
    private Actions action;
    private JavascriptExecutor js;
    private Asserts asserts;
    private BasicControl basicControl;
    private int exist = -1;
    private FormsChangeContainer formsChangeContainer;

    String componente = "Change Containers";
    String newChangeContainer = "CC_SELENIUM";
    String project = "Proyecto Release Selenium";
    String release = "Release Selenium";
    String DR = "DR_SELENIUM";
    String DP = "DP_SELENIUM";
    String urlQA = "http://wedox.sytes.net/buplat_QA/";
    String urlPROD = "http://wedox.sytes.net/buplat/";


    public RM_ChangeContainer(WebDriver driver) {
        this.driver = driver;
        this.action = new Actions(driver);
        this.searchScrollElement = new DynamicScroll(driver);
        this.accessBranch = new AccessBranch(driver);
        this.js = (JavascriptExecutor) driver;
        this.asserts = new Asserts(driver);
        this.basicControl = new BasicControl(driver);
        this.formsChangeContainer = new FormsChangeContainer(driver);
    }

    @Test
    public void crearChangeContainerArbol(String changeContainer, String project, String release, String user) throws InterruptedException {
        exist = searchScrollElement.elementSearch("Change Containers");
        if (exist != -1) {
            accessBranch.clickBranches(exist);
            WebElement btnOpen = driver.findElement(By.xpath("//span[text()='Open']"));
            action.contextClick(btnOpen).perform();
            Thread.sleep(1000);
            driver.findElement(By.xpath("//div[text()='New Change Container' or text()='Nuevo Contenedor de Cambios']")).click();
            formsChangeContainer.createChangeContainer(changeContainer, project, user, release);
            asserts.assertSave();
        }
    }
}



//    //arreglar estoo
//    public void crearChangeContainerTabla() throws InterruptedException {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        String user = login.getUser();
//        driver.findElement(By.xpath("//span[text()='Open']")).click();
//        Thread.sleep(4000);
//        WebElement titulo = driver.findElement(By.xpath("//span[text()='Lista de contenedor de cambios']"));
//        wait.until(ExpectedConditions.visibilityOf(titulo));
//        Thread.sleep(5000);
//        List<WebElement> buttons = driver.findElements(By.xpath("//span[@class ='sapMBtnInner sapMBtnHoverable sapMFocusable sapMBtnIconFirst sapMBtnDefault']"));
//        action.moveToElement(buttons.get(0)).click().perform();
//        Thread.sleep(5000);
//        FormsRM.formCreateChangeContainer(driver,newChangeContainer,project,release,user);
//        asserts.assertSave();
//
//    }
//
//
//    public void editarChangeContainerTabla(){
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        driver.findElement(By.xpath("//span[text()='Open']")).click();
//        WebElement titulo = driver.findElement(By.xpath("//span[text()='Lista de contenedor de cambios']"));
//        wait.until(ExpectedConditions.visibilityOf(titulo));
//        List<WebElement> buttons = driver.findElements(By.xpath("//span[@class ='sapMBtnInner sapMBtnHoverable sapMFocusable sapMBtnIconFirst sapMBtnDefault']"));
//        driver.findElement(By.xpath("//span[text()='01']")).click();
//        action.moveToElement(buttons.get(1)).click().perform();
//
//        //Aún falta terminar porque nos concentramos en un caso End2End.
//    }
//
//    @Test
//    public void activarChangeContainerTabla() throws InterruptedException {
//        crearChangeContainerArbol();
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        driver.findElement(By.xpath("//span[text()='Open']")).click();
//        Thread.sleep(1000);
//        WebElement titulo = driver.findElement(By.xpath("//span[text()='Lista de contenedor de cambios']"));
//        wait.until(ExpectedConditions.visibilityOf(titulo));
//        List<WebElement> buttons = driver.findElements(By.xpath("//span[@class ='sapMBtnInner sapMBtnHoverable sapMFocusable sapMBtnIconFirst sapMBtnDefault']"));
//        String existCC = searchScrollElement.searchElementTable(project,user,"Open",release,newChangeContainer);
//        if(existCC != " "){
//            driver.findElement(By.xpath("//span[text()='"+existCC+"']")).click();
//        }else{
//            System.out.println("Error");
//        }
//        action.moveToElement(buttons.get(2)).click().perform();
//        Thread.sleep(1000);
//        asserts.assertSave();
//    }
//
//    @Test
//    public void releaseChangeContainer() throws InterruptedException {
//        int exist = -1;
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        exist = searchScrollElement.elementSearch("Open");
//        if (exist != -1){
//            accessBranch.clickBranches(exist);
//            exist = searchScrollElement.elementSearch(newChangeContainer);
//            if (exist != -1){
//                WebElement CC = driver.findElement(By.xpath("//span[text()='"+newChangeContainer+"']"));
//                action.contextClick(CC).build().perform();
//                driver.findElement(By.xpath("//div[normalize-space()='Release']")).click();
//                FormsRM.formReleaseCC(driver,accessBranch);
//                exist = searchScrollElement.elementSearch("Open");
//                if(exist != -1){
//                    accessBranch.clickBranches(exist);
//                    exist = searchScrollElement.elementSearch(DP);
//                    if(exist !=-1){
//                        WebElement newDP = driver.findElement(By.xpath("//span[text()='"+DP+"']"));
//                        action.contextClick(newDP).build().perform();
//                        driver.findElement(By.xpath("//div[normalize-space()='Release']")).click();
//                        FormsRM.formReleaseDP(driver,accessBranch);
//                        exist = searchScrollElement.elementSearch("Open");
//                        if (exist != -1){
//                            driver.findElement(By.xpath("//span[text()='Open']")).click();
//                            Thread.sleep(1000);
//                            WebElement titulo = driver.findElement(By.xpath("//span[text()='Lista de solicitudes de instalación' or text()='Deployment Request List']"));
//                            wait.until(ExpectedConditions.visibilityOf(titulo));
//                            List<WebElement> buttons = driver.findElements(By.xpath("//span[@class ='sapMBtnInner sapMBtnHoverable sapMFocusable sapMBtnIconFirst sapMBtnDefault']"));
//                            String existDP = searchScrollElement.searchElementTable(project,user,"Open",release,"DR");
//                            if(existDP != " "){
//                                driver.findElement(By.xpath("//span[text()='"+existDP+"']")).click();
//                            }else{
//                                System.out.println("Error");
//                            }
//                            action.moveToElement(buttons.get(2)).click().perform();
//                            Thread.sleep(1000);
//                            asserts.assertSave();
//                            releaseEnviromentQA(wait);
//                            releaseEnviromentPROD(wait);
//                        }
//                    }
//
//                }
//            }else{
//                asserts.assertSave();
//            }
//        }else{
//            asserts.assertSave();
//        }
//
//    }
//
//    @Test
//    public void releaseEnviromentQA(WebDriverWait wait) throws InterruptedException {
//        Thread.sleep(1000);
//        login.loginPage(urlQA);
//        componente = "Deployment Request";
//        LoginApplications.loginRM(driver, componente);
//        Thread.sleep(1000);
//        int exist = -1;
//        exist = searchScrollElement.elementSearch("Open");
//        if (exist != -1) {
//            driver.findElement(By.xpath("//span[text()='Open']")).click();
//            ChargePopPup.PopPupMain(driver,wait);
//            WebElement titulo = driver.findElement(By.xpath("//span[text()='Lista de solicitudes de instalación' or text()='Deployment Request List']"));
//            wait.until(ExpectedConditions.visibilityOf(titulo));
//            List<WebElement> buttons = driver.findElements(By.xpath("//span[@class ='sapMBtnInner sapMBtnHoverable sapMFocusable sapMBtnIconFirst sapMBtnDefault']"));
//            String existDP = searchScrollElement.searchElementTable("",user,"Ready for Deployment","DR_SELENIUM","DR");
//            if(existDP != " "){
//                driver.findElement(By.xpath("//span[text()='"+existDP+"']")).click();
//            }else{
//                System.out.println("Error");
//            }
//            action.moveToElement(buttons.get(0)).click().perform();
//            ChargePopPup.PopPupMain(driver,wait);
//            asserts.assertSave();
//            int num = Integer.parseInt(existDP);
//            driver.findElement(By.xpath("//span[text()='0"+(num+1)+"']")).click();
//            Thread.sleep(1000);
//            driver.findElement(By.xpath("//span[text()='"+existDP+"']")).click();
//
//            buttons = driver.findElements(By.xpath("//span[@class ='sapMBtnInner sapMBtnHoverable sapMFocusable sapMBtnIconFirst sapMBtnDefault']"));
//            action.moveToElement(buttons.get(0)).click().perform();
//            ChargePopPup.PopPupMain(driver,wait);
//            asserts.assertSave();
//        }
//    }
//
//    @Test
//    public void releaseEnviromentPROD(WebDriverWait wait) throws InterruptedException {
//        Thread.sleep(1000);
//        login.loginPage(urlPROD);
//        componente = "Deployment Request";
//        LoginApplications.loginRM(driver, componente);
//        int exist = -1;
//        exist = searchScrollElement.elementSearch("Open");
//        if (exist != -1) {
//            driver.findElement(By.xpath("//span[text()='Open']")).click();
//            Thread.sleep(1000);
//            WebElement titulo = driver.findElement(By.xpath("//span[text()='Lista de solicitudes de instalación' or text()='Deployment Request List']"));
//            wait.until(ExpectedConditions.visibilityOf(titulo));
//            List<WebElement> buttons = driver.findElements(By.xpath("//span[@class ='sapMBtnInner sapMBtnHoverable sapMFocusable sapMBtnIconFirst sapMBtnDefault']"));
//            String existDP = searchScrollElement.searchElementTable("","","Ready for Deployment","DR_SELENIUM","DR");
//            if(existDP != " "){
//                driver.findElement(By.xpath("//span[text()='"+existDP+"']")).click();
//            }else{
//                System.out.println("Error");
//            }
//            action.moveToElement(buttons.get(0)).click().perform();
//            Thread.sleep(1000);
//            asserts.assertSave();
//        }
//    }
//
//    @Test
//    public void releaseEnviromentQA1() throws InterruptedException {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
//        Thread.sleep(1000);
//        login.loginPage(urlQA);
//        componente = "Deployment Request";
//        LoginApplications.loginRM(driver, componente);
//        Thread.sleep(1000);
//        int exist = -1;
//        exist = searchScrollElement.elementSearch("Open");
//        if (exist != -1) {
//            driver.findElement(By.xpath("//span[text()='Open']")).click();
//            ChargePopPup.PopPupMain(driver,wait);
//            WebElement titulo = driver.findElement(By.xpath("//span[text()='Lista de solicitudes de instalación' or text()='Deployment Request List']"));
//            wait.until(ExpectedConditions.visibilityOf(titulo));
//            Thread.sleep(1000);
//            String xd = searchScrollElement.searchElementTable("PR-Pruebasss","Nayeli Reyes","for Deployment","Release PR-Pruebas","DR");
//            driver.findElement(By.xpath("//span[text()='"+xd+"']")).click();
//        }
//    }
//
//
//
//
//
//
//
//    @AfterMethod
//    public void tearDown(){
//        if (driver != null){
//            //driver.quit();
//        }
//    }
//
//}
//

