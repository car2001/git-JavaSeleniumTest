package Applications.ReleaseManager;

import Forms.ReleaseManager.FormsChangeContainer;
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
    private WebDriverWait wait;

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
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
            formsChangeContainer.createChangeContainer(changeContainer, project,release,user);
            asserts.assertSave();
        }
    }


    public void editarChangeContainerTabla(String project,String editCC,String user,String release,String nameCC) throws InterruptedException {
        driver.findElement(By.xpath("//span[text()='Open']")).click();
        ChargePopPup.PopPupGeneral(driver,wait);
        WebElement titulo = driver.findElement(By.xpath("//span[text()='Lista de Contenedor de Cambios' or text()='Change Container List']"));
        wait.until(ExpectedConditions.visibilityOf(titulo));
        List<WebElement> buttons = driver.findElements(By.xpath("//span[@class ='sapMBtnInner sapMBtnHoverable sapMFocusable sapMBtnIconFirst sapMBtnDefault']"));
        String xpos = searchScrollElement.searchElementTable(project,user,"Open",release,nameCC);
        if(xpos != " "){
            driver.findElement(By.xpath("//tr//span[text()='"+xpos+"']")).click();
            action.moveToElement(buttons.get(1)).click().perform();
            formsChangeContainer.editChangeContainer(editCC);
            ChargePopPup.PopPupGeneral(driver,wait);
            asserts.assertSave();
        }else{
            Assert.assertEquals("No se encontro el CC","Si hay CC");
        }

    }



    public void eliminarChangeContainerTabla(String project,String user,String release,String nameCC) throws InterruptedException {
        WebElement titulo = driver.findElement(By.xpath("//span[text()='Lista de Contenedor de Cambios' or text()='Change Container List']"));
        wait.until(ExpectedConditions.visibilityOf(titulo));
        String xpos = searchScrollElement.searchElementTable(project,user,"Open",release,nameCC);
        if(xpos != ""){
            driver.findElement(By.xpath("//tr//span[text()='"+xpos+"']")).click();
            driver.findElement(By.xpath("//button[@title='Borrar' or @title='Delete']")).click();
            driver.findElement(By.xpath("//bdi[normalize-space()='Sí' or normalize-space()='Yes']")).click();
            asserts.assertSave();
        }else{
            Assert.assertEquals("No se encontro el CC","Si hay CC");
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

