package Applications.ProcessManager;

import Forms.FormsPM;
import Forms.ProcessManager.FormsProcess;
import Helpers.*;
import HomePage.Login;
import HomePage.LoginApplications;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class PM_Process {

    private WebDriver driver;
    private final String chosen_browser = "Chrome";
    private static BasicControl basicControl;

    Actions action;
    SelectBrowser browser = new SelectBrowser(driver);
    Login login;
    AccessBranch accessBranch;
    DynamicScroll searchScrollElement;
    Asserts asserts;
    JavascriptExecutor js;
    WebDriverWait wait;


    String component = "Processes";
    String nameLevel = "Jerarquia Selenium";
    String nameProcess = "Proceso Selenium2";
    String INS = "INS Selenium2";
    String SLA = "SLA Selenium2";
    String AF = "Activity Form Selenium2";
    String PP = "Performer Selenium2";
    int xpos;


    @BeforeMethod
    public void setUp() throws InterruptedException {
        browser.chooseBrowser(chosen_browser);
        driver = browser.getDriver();
        action = new Actions(driver);
        asserts = new Asserts(driver);
        accessBranch = new AccessBranch(driver);
        js= (JavascriptExecutor) driver;
        basicControl = new BasicControl(driver);
        searchScrollElement = new DynamicScroll(driver);
        login = new Login(driver);
        login.loginPage();
        LoginApplications.loginPM(driver);
    }

    @Test
    public void crearProceso() throws InterruptedException, AWTException, IOException {
        wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        xpos = searchScrollElement.elementSearch(nameLevel);
        if(xpos != -1){
            accessBranch.clickBranches(xpos);
            xpos = searchScrollElement.elementSearch(component);
            if(xpos!=-1){
                WebElement process = driver.findElement(By.xpath("//span[text()='"+component+"']"));
                action.contextClick(process).perform();
                driver.findElement(By.xpath("//div[normalize-space()='New Process']")).click();
                Thread.sleep(1000);
                FormsProcess.createProcess(driver,nameProcess,action,INS,SLA);
                ChargePopPup.PopPupMain(driver,wait);
                asserts.assertSave();
                //Realizamos los pasos
                stepsProcess();
            }else{
                Assert.assertEquals("No se encontro la jerarquia","NO");

            }
        }
    }

    @Test
    public void stepsProcess() throws InterruptedException, AWTException, IOException {
        //PASOS TOTALES
        openWizard();
        step1Process();
        //step2Process();
        //step3Process();
        //step4Process();
        //step5Process();
        //step8Process();
    }

    public void openWizard(){
        WebElement verticalbar = driver.findElement(By.xpath("//div[@title='Ajustar el tamaño entre el panel 1 y el panel 2' or @title='Resize between pane 1 and pane 2']"));// este el original
        action.doubleClick(verticalbar).build().perform();
        driver.findElement(By.xpath("//span[contains(@id,'--btnGoToWizard-content')]")).click();
        ChargePopPup.PopPupDetail(driver,wait);
    }

    public void step1Process() throws InterruptedException, IOException {
        //Ingrsamos al paso 1
        WebElement titleStep1 = driver.findElement(By.xpath("//span[text()='Modelar proceso' and contains(@id,'--objFormTitle')]"));
        wait.until(ExpectedConditions.visibilityOf(titleStep1));
        //Editamos el paso 1
        basicControl.btnEdit("--btnEdit-img");
        Thread.sleep(800);

        //Empezamos a crear el diagrama
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
        ChargePopPup.PopPupGeneral(driver,wait);
        asserts.assertSaveDiagram();
    }

    public void step2Process() throws InterruptedException {
        //Ingresamos al paso 2
        driver.findElements(By.cssSelector(".sapUiIcon.sapUiIconMirrorInRTL.sapMITBFilterIcon.sapMITBBadgeHolder.sapMITBFilterDefault")).get(1).click();
        WebElement titleStep2 = driver.findElement(By.xpath("//span[text()='Definir el modelo de datos de proceso' and contains(@id,'--objFormTitle')]"));
        wait.until(ExpectedConditions.visibilityOf(titleStep2));
        //Editamos al paso 2
        basicControl.btnEdit("--btnEditModelData-img");
        //Añadimos los atributos al modelo
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

    public void step3Process()  throws InterruptedException, AWTException {
        //Ingreso al paso 3
        driver.findElements(By.cssSelector(".sapUiIcon.sapUiIconMirrorInRTL.sapMITBFilterIcon.sapMITBBadgeHolder.sapMITBFilterDefault")).get(2).click();
        WebElement titleStep3 = driver.findElement(By.xpath("//span[text()='Asignar formularios' and contains(@id,'--objFormTitle')]"));
        wait.until(ExpectedConditions.visibilityOf(titleStep3));
        //Editamos el paso 3
        basicControl.btnEdit("--btnEditModelerFB-inner");
        //Ingresamos a la actividad 1
        String rect = "#__xmlview4--js-canvas-fb > div > div > svg > g";
        String task1 = "#__xmlview4--js-canvas-fb > div > div > svg > g > g > g > g.djs-children > g:nth-child(9) > g > rect.djs-hit.djs-hit-all";
        ElementSVG.clickSVGElements(task1,js,action,driver,rect,0);
        Thread.sleep(1000);
        driver.findElement(By.id("__xmlview4--btnAddACTF-inner")).click();
        Forms.FormsPM.createNewActivityForm(driver,AF);
        ChargePopPup.PopPupMain(driver,wait);
        Forms.FormsPM.panelActivityForm(driver,action,3,js);
        //Ingresamos a la actividad 2
        String task2 = "#__xmlview4--js-canvas-fb > div > div > svg > g > g > g > g.djs-children > g:nth-child(13) > g > rect.djs-hit.djs-hit-all";
        ElementSVG.clickSVGElements(task2,js,action,driver,rect,0);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//input[contains(@id,'--cbActivityForm-inner') and @placeholder = 'Seleccione una opción']")).click();
        driver.findElement(By.xpath("//input[contains(@id,'--cbActivityForm-inner') and @placeholder = 'Seleccione una opción']")).sendKeys(AF);
        driver.findElement(By.xpath("//input[contains(@id,'--cbActivityForm-inner') and @placeholder = 'Seleccione una opción']")).sendKeys(Keys.TAB);
        driver.findElement(By.xpath("//button[@title='Rechazar']")).click();
        Thread.sleep(1000);
        //Ingresamos a la actividad 3
        String task3 = "#__xmlview4--js-canvas-fb > div > div > svg > g > g > g > g.djs-children > g:nth-child(16) > g > rect.djs-outline";
        ElementSVG.clickSVGElements(task3,js,action,driver,rect,0);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[contains(@id,'--btnEmailDefinition-inner')]")).click();
        WebElement popEmailDefinition = driver.findElement(By.xpath("//span[text()='Editor de correo electrónico']"));
        wait.until(ExpectedConditions.visibilityOf(popEmailDefinition));
        driver.findElement(By.xpath("//input[contains(@id,'--miTO-inner')]")).click();
        driver.findElement(By.xpath("//input[contains(@id,'--miTO-inner')]")).sendKeys("role: Superadmin");
        driver.findElement(By.xpath("//input[contains(@id,'--miTO-inner')]")).sendKeys(Keys.TAB);
        driver.findElement(By.xpath("//input[contains(@id,'--txtSubject-inner')]")).click();
        driver.findElement(By.xpath("//input[contains(@id,'--txtSubject-inner')]")).sendKeys("Proceso Selenium iniciooo");
        driver.findElement(By.xpath("//div[@aria-label='Rich Text Editor, main']")).click();
        driver.findElement(By.xpath("//div[@aria-label='Rich Text Editor, main']")).sendKeys("Proceso Selenium");
        driver.findElement(By.xpath("//span[contains(@id,'--btnSaveEE-content')]")).click();
        driver.findElement(By.xpath("//button[@title='Rechazar']")).click();
        driver.findElement(By.xpath("//span[contains(@id,'--btnSaveModelerFB-inner')]")).click();
        asserts.assertSave();
    }

    public void step4Process() throws InterruptedException {
        //Ingresamos al paso 4
        driver.findElements(By.cssSelector(".sapUiIcon.sapUiIconMirrorInRTL.sapMITBFilterIcon.sapMITBBadgeHolder.sapMITBFilterDefault")).get(3).click();
        ChargePopPup.PopPupDetail(driver,wait);
        WebElement titleStep3 = driver.findElement(By.xpath("//span[text()='Configurar ejecutantes' and contains(@id,'--objFormTitle')]"));
        wait.until(ExpectedConditions.visibilityOf(titleStep3));
        //Editamos el paso 4
        basicControl.btnEdit("--btnEditModelerSP-img");
        // Ingresamos a la actividad 1
        String rect = "#__xmlview4--js-canvas-sp > div > div > svg > g > g > g > g.djs-element.djs-shape.highlight-gray > g";
        String task1 = "#__xmlview4--js-canvas-sp > div > div > svg > g > g > g > g.djs-children > g:nth-child(9) > g > rect.djs-hit.djs-hit-all";
        ElementSVG.clickSVGElements(task1,js,action,driver,rect,0);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[contains(@id,'--allowReassigmentSUP-handle')]")).click();
        driver.findElement(By.xpath("//div[contains(@id,'--allowInsCancellationSUP-handle')]")).click();
        driver.findElement(By.xpath("//input[contains(@id,'--selectPerfProfileProperty-inner')]")).click();
        driver.findElement(By.xpath("//input[contains(@id,'--selectPerfProfileProperty-inner')]")).sendKeys(PP);
        driver.findElement(By.xpath("//input[contains(@id,'--selectPerfProfileProperty-inner')]")).sendKeys(Keys.TAB);
        WebElement btnAdd = driver.findElement(By.xpath("//span[contains(@id,'--addItem-img')]"));
        js.executeScript("arguments[0].scrollIntoView();",btnAdd);
        btnAdd.click();
        WebElement verticalbar = driver.findElement(By.xpath("//div[@title='Ajustar el tamaño entre el panel 1 y el panel 2']"));// este el original
        action.doubleClick(verticalbar).build().perform();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[contains(@id,'-performerTypeSelect') and @class='sapMSltArrow']")).click();
        driver.findElement(By.xpath("//li[text()='Role' and contains(@id,'--performerTypeSelect-')]")).click();
        driver.findElement(By.xpath("//span[contains(@id,'--performerValueSelect-') and @class='sapMSltArrow']")).click();
        driver.findElement(By.xpath("//li[text()='Superadmin' and contains(@id,'--performerValueSelect-')]")).click();
        action.doubleClick(verticalbar).build().perform();
        Thread.sleep(1000);
        js.executeScript("let y = document.getElementById('__xmlview4--detail-cont');y.scroll(0,0)");
        driver.findElement(By.xpath("//button[@title='Rechazar']")).click();
        Thread.sleep(1000);
        // Ingresamos a la actividad 2
        String task2 = "#__xmlview4--js-canvas-sp > div > div > svg > g > g > g > g.djs-children > g:nth-child(13) > g > rect.djs-hit.djs-hit-all";
        ElementSVG.clickSVGElements(task2,js,action,driver,rect,0);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[contains(@id,'--allowReassigmentSUP-handle')]")).click();
        driver.findElement(By.xpath("//div[contains(@id,'--allowInsCancellationSUP-handle')]")).click();
        driver.findElement(By.xpath("//input[contains(@id,'--selectPerfProfileProperty-inner')]")).click();
        driver.findElement(By.xpath("//input[contains(@id,'--selectPerfProfileProperty-inner')]")).sendKeys(PP);
        driver.findElement(By.xpath("//input[contains(@id,'--selectPerfProfileProperty-inner')]")).sendKeys(Keys.TAB);
        btnAdd = driver.findElement(By.xpath("//span[contains(@id,'--addItem-img')]"));
        js.executeScript("arguments[0].scrollIntoView();",btnAdd);
        btnAdd.click();
        action.doubleClick(verticalbar).build().perform();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[contains(@id,'-performerTypeSelect') and @class='sapMSltArrow']")).click();
        driver.findElement(By.xpath("//li[text()='Role' and contains(@id,'--performerTypeSelect-')]")).click();
        driver.findElement(By.xpath("//span[contains(@id,'--performerValueSelect-') and @class='sapMSltArrow']")).click();
        driver.findElement(By.xpath("//li[text()='Superadmin' and contains(@id,'--performerValueSelect-')]")).click();
        action.doubleClick(verticalbar).build().perform();
        js.executeScript("let y = document.getElementById('__xmlview4--detail-cont');y.scroll(0,0)");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@title='Rechazar']")).click();
        driver.findElement(By.xpath("//span[contains(@id,'--btnSaveModelerSP-img')]")).click();
        asserts.assertSave();

    }

    public void step5Process() throws InterruptedException {
        //Ingresamos al paso 5

        driver.findElements(By.cssSelector(".sapUiIcon.sapUiIconMirrorInRTL.sapMITBFilterIcon.sapMITBBadgeHolder.sapMITBFilterDefault")).get(4).click();
        ChargePopPup.PopPupGeneral(driver,wait);
        WebElement titleStep5 = driver.findElement(By.xpath("//span[text()='Integrar código' and contains(@id,'--objFormTitle')]"));
        wait.until(ExpectedConditions.visibilityOf(titleStep5));
        //Editamos el paso 5
        basicControl.btnEdit("--btnEditModelerIC-img");
        // Ingresamos a la actividad 1
        String rect ="#__xmlview4--js-canvas-ic > div > div > svg > g > g > g > g.djs-element.djs-shape.highlight-gray > g";
        String task1 = "#__xmlview4--js-canvas-ic > div > div > svg > g > g > g > g.djs-children > g:nth-child(9) > g > rect.djs-hit.djs-hit-all";
        ElementSVG.clickSVGElements(task1,js,action,driver,rect,0);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[contains(@id,'--allowDueDateChangeIC-handle')]")).click();
        driver.findElement(By.xpath("//input[contains(@id,'--selectSLAProperty-inner')]")).click();
        driver.findElement(By.xpath("//input[contains(@id,'--selectSLAProperty-inner')]")).sendKeys(SLA);
        driver.findElement(By.xpath("//input[contains(@id,'--selectSLAProperty-inner')]")).sendKeys(Keys.TAB);
        driver.findElement(By.xpath("//input[contains(@id,'--numberOfDays-inner')]")).click();
        driver.findElement(By.xpath("//input[contains(@id,'--numberOfDays-inner')]")).sendKeys("10");
        driver.findElement(By.xpath("//button[@title='Rechazar']")).click();
        Thread.sleep(1000);
        //Ingresamos a la actividad 2
        String task2 = "#__xmlview4--js-canvas-ic > div > div > svg > g > g > g > g.djs-children > g:nth-child(13) > g > rect.djs-hit.djs-hit-all";
        ElementSVG.clickSVGElements(task2,js,action,driver,rect,0);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[contains(@id,'--allowDueDateChangeIC-handle')]")).click();
        driver.findElement(By.xpath("//input[contains(@id,'--selectSLAProperty-inner')]")).click();
        driver.findElement(By.xpath("//input[contains(@id,'--selectSLAProperty-inner')]")).sendKeys(SLA);
        driver.findElement(By.xpath("//input[contains(@id,'--selectSLAProperty-inner')]")).sendKeys(Keys.TAB);
        driver.findElement(By.xpath("//input[contains(@id,'--numberOfDays-inner')]")).click();
        driver.findElement(By.xpath("//input[contains(@id,'--numberOfDays-inner')]")).sendKeys("2");
        driver.findElement(By.xpath("//button[@title='Rechazar']")).click();
        Thread.sleep(1000);
        //Creamos a la regla Si
        String reglaSi = "#__xmlview4--js-canvas-ic > div > div > svg > g > g > g > g.djs-children > g:nth-child(4) > g > polyline";
        ElementSVG.clickSVGElements(reglaSi,js,action,driver,rect,0);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[contains(@id,'--btnAddRE-img')]")).click();
        ChargePopPup.PopPupGeneral(driver,wait);
        driver.findElement(By.xpath("//input[contains(@id,'--ruleName-inner')]")).click();
        driver.findElement(By.xpath("//input[contains(@id,'--ruleName-inner')]")).sendKeys("Regla SI");
        driver.findElement(By.xpath("//input[contains(@id,'--ruleDisplayName-inner')]")).click();
        driver.findElement(By.xpath("//input[contains(@id,'--ruleDisplayName-inner')]")).sendKeys("Regla SI");
        driver.findElement(By.xpath("//button[@aria-label='Añadir' and @class='sapMBtnBase sapMBtn sapMBarChild']")).click();
        List<WebElement> arrow = driver.findElements(By.cssSelector(".sapMSltArrow"));
        arrow.get(0).click();
        driver.findElement(By.xpath("//li[text()='Data Model Attribute' and @class='sapMSelectListItemBase sapMSelectListItem sapMSelectListItemBaseHoverable sapMSelectListItemBaseSelected']")).click();
        driver.findElements(By.xpath("//span[@aria-label='Mostrar ayuda para entradas' and contains(@id,'-vhi') ]")).get(0).click();
        ChargePopPup.PopPupGeneral(driver,wait);
        driver.findElement(By.xpath("//span[contains(@id,'--treeDM-rows-row0') and @title='Expandir nodos']")).click();
        driver.findElement(By.xpath("//span[text()='¿Acepta? : Boolean']")).click();
        Thread.sleep(800);
        arrow = driver.findElements(By.cssSelector(".sapMSltArrow"));
        arrow.get(1).click();
        Thread.sleep(800);
        driver.findElements(By.xpath("//li[text()='Is True']")).get(1).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//bdi[text()='Guardar']")).click();
        ChargePopPup.PopPupGeneral(driver,wait);
        //Creamos regla NO
        String reglaNo = "#__xmlview4--js-canvas-ic > div > div > svg > g > g > g > g.djs-children > g:nth-child(2) > g > polyline";
        ElementSVG.clickSVGElements(reglaNo,js,action,driver,rect,-40);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[contains(@id,'--btnAddRE-img')]")).click();
        ChargePopPup.PopPupGeneral(driver,wait);
        driver.findElement(By.xpath("//input[contains(@id,'--ruleName-inner')]")).click();
        driver.findElement(By.xpath("//input[contains(@id,'--ruleName-inner')]")).sendKeys("Regla NO");
        driver.findElement(By.xpath("//input[contains(@id,'--ruleDisplayName-inner')]")).click();
        driver.findElement(By.xpath("//input[contains(@id,'--ruleDisplayName-inner')]")).sendKeys("Regla NO");
        driver.findElement(By.xpath("//button[@aria-label='Añadir' and @class='sapMBtnBase sapMBtn sapMBarChild']")).click();
        arrow = driver.findElements(By.cssSelector(".sapMSltArrow"));
        arrow.get(0).click();
        driver.findElement(By.xpath("//li[text()='Data Model Attribute' and @class='sapMSelectListItemBase sapMSelectListItem sapMSelectListItemBaseHoverable sapMSelectListItemBaseSelected']")).click();
        driver.findElements(By.xpath("//span[@aria-label='Mostrar ayuda para entradas' and contains(@id,'-vhi') ]")).get(0).click();
        ChargePopPup.PopPupGeneral(driver,wait);
        driver.findElement(By.xpath("//span[contains(@id,'--treeDM-rows-row0') and @title='Expandir nodos']")).click();
        driver.findElement(By.xpath("//span[text()='¿Acepta? : Boolean']")).click();
        arrow = driver.findElements(By.cssSelector(".sapMSltArrow"));
        arrow.get(1).click();
        Thread.sleep(800);
        driver.findElements(By.xpath("//li[text()='Is False']")).get(1).click();
        driver.findElement(By.xpath("//bdi[text()='Guardar']")).click();
        ChargePopPup.PopPupGeneral(driver,wait);
        driver.findElement(By.xpath("//button[@title='Rechazar']")).click();
        basicControl.btnSave("--btnSaveModelerIC-img");
        //Guardamos cambios
        asserts.assertSave();

    }

    public void step8Process(){
        //Ingresamos al paso 8
        driver.findElements(By.cssSelector(".sapUiIcon.sapUiIconMirrorInRTL.sapMITBFilterIcon.sapMITBBadgeHolder.sapMITBFilterDefault")).get(5).click();
        WebElement titleStep8 = driver.findElement(By.xpath("//span[text()='Desplegar' and contains(@id,'--objFormTitle')]"));
        wait.until(ExpectedConditions.visibilityOf(titleStep8));
        driver.findElement(By.xpath("//bdi[text()='Validar']")).click();
        ChargePopPup.PopPupGeneral(driver,wait);
        WebElement verticalbar = driver.findElement(By.xpath("//div[@title='Ajustar el tamaño entre el panel 1 y el panel 2']"));// este el original
        action.doubleClick(verticalbar).build().perform();
    }

    @AfterMethod
    public void tearDown(){
        if (driver != null){
            //driver.quit();
        }
    }

}
