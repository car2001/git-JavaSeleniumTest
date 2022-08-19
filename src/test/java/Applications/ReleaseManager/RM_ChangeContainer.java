package Applications.ReleaseManager;

import Forms.FormsRM;
import Helpers.*;
import HomePage.Login;
import HomePage.LoginApplications;
import org.openqa.selenium.By;
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
    private final String chosen_browser = "Chrome";

    Actions action;
    SelectBrowser browser = new SelectBrowser(driver);
    Login login;
    DynamicScroll searchScrollElement;
    Asserts asserts;
    AccessBranch accessBranch;
    BasicControl basicControl;

    String componente = "Change Container";
    String newChangeContainer = "CC_SELENIUM";
    String project = "Proyecto Release Selenium";
    String release = "Release Selenium";
    String DR = "DR_SELENIUM";
    String DP = "DP_SELENIUM";
    String urlQA = "http://wedox.sytes.net/buplat_QA/";
    String urlPROD = "http://wedox.sytes.net/buplat/";

    @BeforeMethod
    public void setUp(){
        browser.chooseBrowser(chosen_browser);
        driver = browser.getDriver();
        login = new Login(driver);
        action = new Actions(driver);
        asserts = new Asserts(driver);
        searchScrollElement = new DynamicScroll(driver);
        accessBranch = new AccessBranch(driver);
        basicControl = new BasicControl(driver);
        login.loginPage();
        LoginApplications.loginRM(driver, componente);
    }

    @Test
    public void crearChangeContainerArbol() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String user = login.getUser();
        WebElement btnOpen = driver.findElement(By.xpath("//span[text()='Open']"));
        action.contextClick(btnOpen).perform();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[normalize-space()='New " + componente + "']")).click();
        FormsRM.formCreateChangeContainer(driver,newChangeContainer,project,release,user);
        asserts.assertSave();
    }



    //arreglar estoo
    public void crearChangeContainerTabla() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String user = login.getUser();
        driver.findElement(By.xpath("//span[text()='Open']")).click();
        Thread.sleep(4000);
        WebElement titulo = driver.findElement(By.xpath("//span[text()='Lista de contenedor de cambios']"));
        wait.until(ExpectedConditions.visibilityOf(titulo));
        Thread.sleep(5000);
        List<WebElement> buttons = driver.findElements(By.xpath("//span[@class ='sapMBtnInner sapMBtnHoverable sapMFocusable sapMBtnIconFirst sapMBtnDefault']"));
        action.moveToElement(buttons.get(0)).click().perform();
        Thread.sleep(5000);
        FormsRM.formCreateChangeContainer(driver,newChangeContainer,project,release,user);
        asserts.assertSave();

    }


    public void editarChangeContainerTabla(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.xpath("//span[text()='Open']")).click();
        WebElement titulo = driver.findElement(By.xpath("//span[text()='Lista de contenedor de cambios']"));
        wait.until(ExpectedConditions.visibilityOf(titulo));
        List<WebElement> buttons = driver.findElements(By.xpath("//span[@class ='sapMBtnInner sapMBtnHoverable sapMFocusable sapMBtnIconFirst sapMBtnDefault']"));
        driver.findElement(By.xpath("//span[text()='01']")).click();
        action.moveToElement(buttons.get(1)).click().perform();

        //Aún falta terminar porque nos concentramos en un caso End2End.
    }

    @Test
    public void activarChangeContainerTabla() throws InterruptedException {
        crearChangeContainerArbol();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.xpath("//span[text()='Open']")).click();
        Thread.sleep(1000);
        WebElement titulo = driver.findElement(By.xpath("//span[text()='Lista de contenedor de cambios']"));
        wait.until(ExpectedConditions.visibilityOf(titulo));
        List<WebElement> buttons = driver.findElements(By.xpath("//span[@class ='sapMBtnInner sapMBtnHoverable sapMFocusable sapMBtnIconFirst sapMBtnDefault']"));
        driver.findElement(By.xpath("//span[text()='01']")).click();
        action.moveToElement(buttons.get(2)).click().perform();
        Thread.sleep(1000);
        asserts.assertSave();
    }

    @Test
    public void releaseChangeContainer() throws InterruptedException {
        int exist = -1;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        exist = searchScrollElement.elementSearch("Open");
        if (exist != -1){
            accessBranch.clickBranches(exist);
            exist = searchScrollElement.elementSearch(newChangeContainer);
            if (exist != -1){
                WebElement CC = driver.findElement(By.xpath("//span[text()='"+newChangeContainer+"']"));
                action.contextClick(CC).build().perform();
                driver.findElement(By.xpath("//div[normalize-space()='Release']")).click();
                FormsRM.formReleaseCC(driver,accessBranch);
                exist = searchScrollElement.elementSearch("Open");
                if(exist != -1){
                    accessBranch.clickBranches(exist);
                    exist = searchScrollElement.elementSearch(DP);
                    if(exist !=-1){
                        WebElement newDP = driver.findElement(By.xpath("//span[text()='"+DP+"']"));
                        action.contextClick(newDP).build().perform();
                        driver.findElement(By.xpath("//div[normalize-space()='Release']")).click();
                        FormsRM.formReleaseDP(driver,accessBranch);
                        exist = searchScrollElement.elementSearch("Open");
                        if (exist != -1){
                            driver.findElement(By.xpath("//span[text()='Open']")).click();
                            Thread.sleep(1000);
                            WebElement titulo = driver.findElement(By.xpath("//span[text()='Lista de solicitudes de instalación']"));
                            wait.until(ExpectedConditions.visibilityOf(titulo));
                            List<WebElement> buttons = driver.findElements(By.xpath("//span[@class ='sapMBtnInner sapMBtnHoverable sapMFocusable sapMBtnIconFirst sapMBtnDefault']"));
                            driver.findElement(By.xpath("//span[text()='01']")).click();
                            action.moveToElement(buttons.get(2)).click().perform();
                            Thread.sleep(1000);
                            asserts.assertSave();
                            releaseEnviromentQA(wait);
                            releaseEnviromentPROD(wait);
                        }
                    }

                }
            }else{
                asserts.assertSave();
            }
        }else{
            asserts.assertSave();
        }

    }

    @Test
    public void releaseEnviromentQA(WebDriverWait wait) throws InterruptedException {
        Thread.sleep(1000);
        login.loginPage(urlQA);
        componente = "Deployment Request";
        LoginApplications.loginRM(driver, componente);
        Thread.sleep(1000);
        int exist = -1;
        exist = searchScrollElement.elementSearch("Open");
        if (exist != -1) {
            driver.findElement(By.xpath("//span[text()='Open']")).click();
            ChargePopPup.PopPupMain(driver,wait);
            WebElement titulo = driver.findElement(By.xpath("//span[text()='Lista de solicitudes de instalación']"));
            wait.until(ExpectedConditions.visibilityOf(titulo));
            List<WebElement> buttons = driver.findElements(By.xpath("//span[@class ='sapMBtnInner sapMBtnHoverable sapMFocusable sapMBtnIconFirst sapMBtnDefault']"));
            driver.findElement(By.xpath("//span[text()='01']")).click();
            action.moveToElement(buttons.get(0)).click().perform();
            ChargePopPup.PopPupMain(driver,wait);
            asserts.assertSave();
            driver.findElement(By.xpath("//span[text()='02']")).click();
            Thread.sleep(500);
            driver.findElement(By.xpath("//span[text()='01']")).click();
            buttons = driver.findElements(By.xpath("//span[@class ='sapMBtnInner sapMBtnHoverable sapMFocusable sapMBtnIconFirst sapMBtnDefault']"));
            action.moveToElement(buttons.get(0)).click().perform();
            ChargePopPup.PopPupMain(driver,wait);
            asserts.assertSave();
        }
    }

    @Test
    public void releaseEnviromentPROD(WebDriverWait wait) throws InterruptedException {
        Thread.sleep(1000);
        login.loginPage(urlPROD);
        componente = "Deployment Request";
        LoginApplications.loginRM(driver, componente);
        int exist = -1;
        exist = searchScrollElement.elementSearch("Open");
        if (exist != -1) {
            driver.findElement(By.xpath("//span[text()='Open']")).click();
            Thread.sleep(1000);
            WebElement titulo = driver.findElement(By.xpath("//span[text()='Lista de solicitudes de instalación']"));
            wait.until(ExpectedConditions.visibilityOf(titulo));
            List<WebElement> buttons = driver.findElements(By.xpath("//span[@class ='sapMBtnInner sapMBtnHoverable sapMFocusable sapMBtnIconFirst sapMBtnDefault']"));
            driver.findElement(By.xpath("//span[text()='01']")).click();
            action.moveToElement(buttons.get(0)).click().perform();
            Thread.sleep(1000);
            asserts.assertSave();
        }
    }

    @AfterMethod
    public void tearDown(){
        if (driver != null){
            //driver.quit();
        }
    }

}


