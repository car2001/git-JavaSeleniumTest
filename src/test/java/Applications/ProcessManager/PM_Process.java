package Applications.ProcessManager;

import Forms.FormsPM;
import Helpers.*;
import HomepageFunctions.Home_Page;
import HomepageFunctions.Login_Applications;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.time.Duration;
import java.util.List;

public class PM_Process {

    private WebDriver driver;
    private final String chosen_browser = "Chrome";

    Actions action;
    SelectBrowser browser = new SelectBrowser(driver);
    Home_Page login;
    AccessBranches accessBranch;
    Dynamic_Scroll_Search searchScrollElement;
    Asserts asserts;
    JavascriptExecutor js;
    WebDriverWait wait;

    String component = "Processes";
    String nameLevel = "Jerarquia Selenium";
    String nameProcess = "Proceso Selenium";
    String INS = "INS Selenium";
    String SLA = "SLA Selenium";
    String AF = "Activity Form Selenium";
    int xpos;


    @BeforeMethod
    public void setUp() throws InterruptedException {
        browser.chooseBrowser(chosen_browser);
        driver = browser.getDriver();
        action = new Actions(driver);
        asserts = new Asserts(driver);
        accessBranch = new AccessBranches(driver);
        js= (JavascriptExecutor) driver;
        searchScrollElement = new Dynamic_Scroll_Search(driver);
        login = new Home_Page(driver);
        login.loginPage();
        Login_Applications.loginPM(driver);
    }

    @Test
    public void crearProceso(){
        xpos = searchScrollElement.elementSearch(nameLevel);
        if(xpos != -1){
            accessBranch.clickBranches(xpos);
            xpos = searchScrollElement.elementSearch(component);
            if(xpos!=-1){
                WebElement process = driver.findElement(By.xpath("//span[text()='"+component+"']"));
                action.contextClick(process).perform();
                driver.findElement(By.xpath("//div[normalize-space()='New Process']")).click();
                FormsPM.creteNewProcess(driver,nameProcess,action,INS,SLA,js);
                asserts.assertSave();
            }else{
                Assert.assertEquals("No se encontro la jerarquia","NO");

            }
        }
    }

    @Test
    public void stepsProcess() throws InterruptedException, AWTException {
        //PASOS TOTALES
        openWizard();
        step1Process();
        step2Process();
        step3Process();
    }

    @Test
    public void testPruebaForm() throws InterruptedException, AWTException {
        openWizard2();
        xpos = searchScrollElement.elementSearch("Activity Form");
        if (xpos!=-1){
            driver.findElement(By.xpath("//span[text()='Activity Form']")).click();
            driver.findElement(By.xpath("//span[text()='Activity Form Selenium']")).click();
            driver.findElement(By.xpath("//button[@aria-label='Desing Form']")).click();
            WebElement popupCarga = driver.findElement(By.cssSelector("#sapUiBusyIndicator.sapUiUserSelectable"));
            wait.until(ExpectedConditions.visibilityOf(popupCarga));
            wait.until(ExpectedConditions.invisibilityOf(popupCarga));
            Forms.FormsPM.panelActivityForm(driver,action,3,js);
        }

    }

    @Test
    public void step3Process() throws InterruptedException, AWTException {
        openWizard();
        driver.findElements(By.cssSelector(".sapUiIcon.sapUiIconMirrorInRTL.sapMITBFilterIcon.sapMITBBadgeHolder.sapMITBFilterDefault")).get(2).click();
        driver.findElement(By.id("__xmlview4--btnEditModelerFB-inner")).click();
        FormsControl.controlLook(driver,"__xmlview4--btnEditModelerFB-inner");
        js.executeScript("let g = document.querySelector('#__xmlview4--js-canvas-fb > div > div > svg > g'); g.scrollIntoView();");
        WebElement task1 =  driver.findElement(By.cssSelector("#__xmlview4--js-canvas-fb > div > div > svg > g > g > g > g.djs-children > g:nth-child(9) > g > rect.djs-hit.djs-hit-all"));
        action.moveToElement(task1).click().build().perform();
        Thread.sleep(1000);
        js.executeScript("let y = document.getElementById('__xmlview4--detail-cont');y.scroll(0,0)");
        driver.findElement(By.id("__xmlview4--btnAddACTF-inner")).click();
        Forms.FormsPM.createNewActivityForm(driver,AF);
        WebElement popupCarga = driver.findElement(By.xpath("//div[contains(@id,'--resSplitMain-busyIndicator')]"));
        wait.until(ExpectedConditions.visibilityOf(popupCarga));
        wait.until(ExpectedConditions.invisibilityOf(popupCarga));
        Forms.FormsPM.panelActivityForm(driver,action,3,js);
        js.executeScript("let g = document.querySelector('#__xmlview4--js-canvas-fb > div > div > svg > g'); g.scrollIntoView();");
        WebElement task2 = driver.findElement(By.cssSelector("#__xmlview4--js-canvas-fb > div > div > svg > g > g > g > g.djs-children > g:nth-child(13) > g > rect.djs-hit.djs-hit-all"));
        action.moveToElement(task2).click().build().perform();
        js.executeScript("let y = document.getElementById('__xmlview4--detail-cont');y.scroll(0,0)");
        driver.findElement(By.xpath("//input[contains(@id,'--cbActivityForm-inner') and @placeholder = 'Seleccione una opción']")).click();
        driver.findElement(By.xpath("//input[contains(@id,'--cbActivityForm-inner') and @placeholder = 'Seleccione una opción']")).sendKeys(AF);
        driver.findElement(By.xpath("//button[@title='Rechazar']")).click();
        js.executeScript("let g = document.querySelector('#__xmlview4--js-canvas-fb > div > div > svg > g'); g.scrollIntoView();");
        WebElement task3 = driver.findElement(By.cssSelector("#__xmlview4--js-canvas-fb > div > div > svg > g > g > g > g.djs-children > g:nth-child(16) > g > rect.djs-hit.djs-hit-all"));
        action.moveToElement(task3).click().build().perform();
        Thread.sleep(1000);
        js.executeScript("let y = document.getElementById('__xmlview4--detail-cont');y.scroll(0,0)");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[contains(@id,'--btnEmailDefinition-img')]")).click();
        WebElement popEmailDefinition = driver.findElement(By.xpath("//span[text()='Editor de correo electrónico']"));
        wait.until(ExpectedConditions.visibilityOf(popEmailDefinition));
        driver.findElement(By.xpath("//input[contains(@id,'--miTO-inner')]")).click();
        driver.findElement(By.xpath("//input[contains(@id,'--miTO-inner')]")).sendKeys("role: Superadmin");
        driver.findElement(By.xpath("//input[contains(@id,'--miTO-inner')]")).sendKeys(Keys.TAB);
        driver.findElement(By.xpath("//input[contains(@id,'--txtSubject-inner')]")).click();
        driver.findElement(By.xpath("//input[contains(@id,'--txtSubject-inner')]")).sendKeys("Proceso Selenium iniciooo");

    }


    public void step2Process()  {
        driver.findElements(By.cssSelector(".sapUiIcon.sapUiIconMirrorInRTL.sapMITBFilterIcon.sapMITBBadgeHolder.sapMITBFilterDefault")).get(1).click();
        driver.findElement(By.id("__xmlview4--btnEditModelData-img")).click();
        FormsControl.controlLook(driver,"__xmlview4--btnEditModelData-img");
        WebElement addAtributte = driver.findElement(By.xpath("//button[@title='Añadir']"));
        action.doubleClick(addAtributte).perform();
        addAtributte.click();
        List<WebElement> listform = driver.findElements(By.className("sapMInputBaseInner"));
        listform.get(2).click();
        listform.get(2).sendKeys("Solicitud");
        listform.get(4).click();
        listform.get(4).sendKeys("¿Acepta?");
        listform.get(6).click();
        listform.get(6).sendKeys("Usuario");
        List<WebElement> cboForm = driver.findElements(By.className("sapMSltArrow"));
        cboForm.get(0).click();
        driver.findElements(By.cssSelector(".sapMSelectListItemWithIcon.sapMSelectListItemBase.sapMSelectListItem.sapMSelectListItemBaseHoverable")).get(10).click();
        cboForm.get(1).click();
        driver.findElements(By.cssSelector(".sapMSelectListItemWithIcon.sapMSelectListItemBase.sapMSelectListItem.sapMSelectListItemBaseHoverable")).get(15).click();
        cboForm.get(2).click();
        driver.findElements(By.cssSelector(".sapMSelectListItemWithIcon.sapMSelectListItemBase.sapMSelectListItem.sapMSelectListItemBaseHoverable")).get(38).click();
        driver.findElement(By.id("__xmlview4--btnSaveModelData-img")).click();
        asserts.assertSaveModelData();
    }


    public void step1Process(){
        driver.findElement(By.id("__xmlview4--btnEdit-img")).click();
        FormsControl.controlLook(driver,"__xmlview4--btnEdit-img");
        List<WebElement> start = driver.findElements(By.cssSelector("#__xmlview4--js-canvas > div > div > svg > g > g.layer-base > g > g.djs-children > g:nth-child(1) > g > rect.djs-outline"));
        action.moveToElement(start.get(0)).click().build().perform();
        driver.findElement(By.xpath("//div[@title='Append User Task']")).click();
        driver.findElement(By.xpath("//div[@title='Append Exclusive Gateway']")).click();
        driver.findElement(By.xpath("//div[@title='Append User Task']")).click();
        driver.findElement(By.xpath("//div[@title='Append EndEvent']")).click();
        WebElement gateWay =  driver.findElement(By.cssSelector("#__xmlview4--js-canvas > div > div > svg > g > g.layer-base > g > g.djs-children > g:nth-child(8) > g > rect.djs-hit.djs-hit-all"));
        action.moveToElement(gateWay).click().build().perform();
        driver.findElement(By.xpath("//div[@title='Append User Task']")).click();
        driver.findElement(By.xpath("//div[@title='Append EndEvent']")).click();
        driver.findElement(By.cssSelector("#__xmlview4--js-canvas > div > div > svg > g > g.layer-base > g > g.djs-children > g:nth-child(11) > g > rect.djs-hit.djs-hit-all")).click();
        driver.findElement(By.xpath("//div[@title='Change type']")).click();
        driver.findElement(By.xpath("//span[normalize-space()='Send Task']")).click();
        driver.findElement(By.id("__xmlview4--btnSave-inner")).click();
        WebElement popupCarga = driver.findElement(By.cssSelector("#sapUiBusyIndicator.sapUiUserSelectable"));
        wait.until(ExpectedConditions.visibilityOf(popupCarga));
        wait.until(ExpectedConditions.invisibilityOf(popupCarga));
        asserts.assertSaveDiagram();
    }


    public void openWizard() throws InterruptedException {
        wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        xpos = searchScrollElement.elementSearch(nameLevel);
        if(xpos != -1){
            accessBranch.clickBranches(xpos);
            xpos = searchScrollElement.elementSearch(component);
            if(xpos!=-1){
                accessBranch.clickBranches(xpos);
                xpos = searchScrollElement.elementSearch(nameProcess);
                if(xpos!=-1){
                    driver.findElement(By.xpath("//span[text()='"+nameProcess+"']")).click();
                    WebElement popupCarga = driver.findElement(By.cssSelector("#sapUiBusyIndicator.sapUiUserSelectable"));
                    wait.until(ExpectedConditions.visibilityOf(popupCarga));
                    wait.until(ExpectedConditions.invisibilityOf(popupCarga));
                    driver.findElement(By.id("__xmlview4--btnGoToWizard-content")).click(); // este el original
                    Thread.sleep(3000);
                }
            }
        }else{
            Assert.assertEquals("No se encontro la jerarquia","NO");
        }
    }


    public void openWizard2() throws InterruptedException { //este es de pri}ueba
        wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        xpos = searchScrollElement.elementSearch(nameLevel);
        if(xpos != -1){
            accessBranch.clickBranches(xpos);
            xpos = searchScrollElement.elementSearch(component);
            if(xpos!=-1){
                accessBranch.clickBranches(xpos);
                xpos = searchScrollElement.elementSearch(nameProcess);
                if(xpos!=-1){
                    driver.findElement(By.xpath("//span[text()='"+nameProcess+"']")).click();

                    WebElement popupCarga = driver.findElement(By.cssSelector("#sapUiBusyIndicator.sapUiUserSelectable"));
                    wait.until(ExpectedConditions.visibilityOf(popupCarga));
                    wait.until(ExpectedConditions.invisibilityOf(popupCarga));
                    accessBranch.clickBranches(xpos); // agrgado paara probar
                    //driver.findElement(By.id("__xmlview4--btnGoToWizard-content")).click(); // este el original
                    Thread.sleep(3000);
                }
            }
        }else{
            Assert.assertEquals("No se encontro la jerarquia","NO");
        }
    }




}
