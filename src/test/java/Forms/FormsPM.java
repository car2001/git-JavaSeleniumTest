package Forms;

import Helpers.FormsControl;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//js.executeScript("let x = document.getElementById('__xmlview4--TreeDMFB-rows-row1');x.addEventListener('dragstart',e=>{console.log('Drag Start')});x.addEventListener('dragend',e=>{console.log('Drag End')});x.addEventListener('drag',e=>{console.log('Drag')});");
// js.executeScript("function mousemove(event){console.log('pagex:',event.pageX,'page Y:',event.pageY,'clientX',event.clientX,'clientY',event.clientY,'screenx',event.screenX,'screenY',event.screenY)};window.addEventListener('mousemove',mousemove)");


import java.awt.*;
import java.awt.event.InputEvent;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FormsPM {
    private static List<WebElement> listForm;
    private static String save = "__xmlview4--save-img";  ///
    private static String edit= "__xmlview4--edit-img";
    private static String version = "__xmlview4--newVersion-inner";
    private static String versionHistory = "__xmlview4--versionHistory-img";
    private static String num = "4";
    private static WebDriverWait wait;
    private static String isAutomated = "//div[contains(@id,'--isAutomated-handle')]";
    private static String addSecurityProfile = "//span[contains(@id,'--addSecurityProfile-inner')]";
    private static String groupPermisos = "//span[ contains(@id,'__select') and @class='sapMSltArrow']";
    private static String selectType  = "//span[contains(@id,'--typeSelect-') and @class='sapMSltArrow' ]";
    private static String valueA = "//span[contains(@id,'--valueSelect-') and @class='sapMSltArrow']";



    //PROCESS
    public static void creteNewProcess(WebDriver driver, String process , Actions action, String INS, String SLA , JavascriptExecutor js) throws InterruptedException {
        listForm = FormsControl.controlNew(driver,"proceso","");
        listForm.get(2).click();
        listForm.get(2).sendKeys(process);
        listForm.get(3).click();
        listForm.get(3).sendKeys(process);
        listForm.get(4).click();
        listForm.get(4).sendKeys(process);
        Thread.sleep(1000);
        driver.findElement(By.id("__xmlview4--isAutomated-handle")).click();
        //SECURITY PROFILE
        securityProfile(driver,action);
        Thread.sleep(1000);
        // INS
        if(js.executeScript("let INS = document.getElementById('__xmlview4--itfInstanceNumbering-icon');return INS.clientHeight").hashCode() == 0){
            driver.findElement(By.id("__xmlview4--itbAutomationProcess--header-overflow-text")).click();
            driver.findElement(By.xpath("//li[@title = 'Creación de Instancias']")).click();
        }else{
            driver.findElement(By.id("__xmlview4--itfInstanceNumbering-icon")).click();
        }
        driver.findElement(By.id("__xmlview4--instanceNumbering-inner")).click();
        driver.findElement(By.id("__xmlview4--instanceNumbering-inner")).sendKeys(INS);
        //SLA
        if(js.executeScript("let SLA = document.getElementById('__xmlview4--itfSLAConfiguration-icon');return SLA.clientHeight").hashCode() == 0){
            driver.findElement(By.id("__xmlview4--itbAutomationProcess--header-overflow-text")).click();
            driver.findElement(By.xpath("//li[@title = 'SLA']")).click();
        }else{
            driver.findElement(By.id("__xmlview4--itfSLAConfiguration-icon")).click();
        }
        driver.findElement(By.id("__xmlview4--selectSLAPROProperty-inner")).click();
        driver.findElement(By.id("__xmlview4--selectSLAPROProperty-inner")).sendKeys(SLA);
        driver.findElement(By.id("__xmlview4--selectSLAPROProperty-inner")).sendKeys(Keys.TAB);
        driver.findElement(By.id("__xmlview4--numberOfDaysPRO-inner")).click();
        driver.findElement(By.id("__xmlview4--numberOfDaysPRO-inner")).sendKeys("5");
        driver.findElement(By.id(save)).click();
    }

    public static void securityProfile(WebDriver driver,Actions action){
        //SECURITY PROFILE
        WebElement btnSecurity =  driver.findElement(By.xpath(addSecurityProfile));
        action.doubleClick(btnSecurity).perform();
        //Permisos
        List<WebElement> permisos = driver.findElements(By.xpath(groupPermisos));
        permisos.get(0).click();
        driver.findElement(By.xpath("//li[contains(@id,'__select') and contains(@class,'sapMSelectListItemBase sapMSelectListItem sapMSelectListItemBaseHoverable') and text()='Instance Creator']")).click();
        permisos.get(1).click();
        driver.findElements(By.xpath("//li[contains(@id,'__select') and contains(@class,'sapMSelectListItemBase sapMSelectListItem sapMSelectListItemBaseHoverable') and text()='Administrator']")).get(1).click();
        //Tipo
        List<WebElement> tipo = driver.findElements(By.xpath(selectType));
        tipo.get(0).click();
        String role = "//li[contains(@id,'--typeSelect-') and contains(@class,'sapMSelectListItemBase sapMSelectListItem sapMSelectListItemBaseHoverable') and text()='Role']";
        driver.findElement(By.xpath(role)).click();
        tipo.get(1).click();
        driver.findElements(By.xpath(role)).get(1).click();
        //Value
        List<WebElement> value = driver.findElements(By.xpath(valueA));
        value.get(0).click();
        String superadmin = "//li[contains(@id,'--valueSelect-') and contains(@class,'sapMSelectListItemBase sapMSelectListItem sapMSelectListItemBaseHoverable') and text()='Superadmin']";
        driver.findElement(By.xpath(superadmin)).click();
        value.get(1).click();
        driver.findElements(By.xpath(superadmin)).get(1).click();
    }

    //Activity Form
    public static void createNewActivityForm(WebDriver driver , String nameAf){
        listForm = FormsControl.controlNew(driver,"formulario de actividad","");
        listForm.get(2).click();
        listForm.get(2).sendKeys(nameAf);
        listForm.get(3).click();
        listForm.get(3).sendKeys(nameAf);
        listForm.get(4).click();
        listForm.get(4).sendKeys(nameAf);
        driver.findElement(By.id(save)).click();
    }

    public static void panelActivityForm(WebDriver driver, Actions actions , int tamModel,JavascriptExecutor js ) throws InterruptedException, AWTException {
        wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        driver.findElement(By.xpath("//span[contains(@id,'--TreeDMFB-rows-row0-treeicon')]")).click();
        Thread.sleep(2000);
        WebElement to = driver.findElement(By.xpath("//div[contains(@id,'idGridBuilder') and @aria-roledescription='Lista de elementos']"));

        for(int i=tamModel; i > 0 ; i -- ){
            WebElement from = driver.findElement(By.xpath("//td[contains(@id,'--TreeDMFB-rows-row"+i+"-col')]"));
            moveBox(from,to,js);
        }
        Thread.sleep(1000);
        driver.findElement(By.id("__xmlview4--btnSaveFB-img")).click();
        WebElement popupCarga = driver.findElement(By.cssSelector("#sapUiBusyIndicator.sapUiUserSelectable"));
        wait.until(ExpectedConditions.visibilityOf(popupCarga));
        wait.until(ExpectedConditions.invisibilityOf(popupCarga));

    }

    public static  void moveBox(WebElement from , WebElement to,JavascriptExecutor js) throws AWTException, InterruptedException {
        List<Integer> desplazamiento = calculoPixeles(from, to, js);
        int supBrowser = calculoPantalla(js);
        int x = from.getLocation().getX();
        int y = from.getLocation().getY() + supBrowser;
        System.out.println(x + " " + y);
        System.out.println(desplazamiento.get(0));
        System.out.println(desplazamiento.get(1));
        System.out.println(supBrowser);

        Robot robot = new Robot();
        robot.mouseMove(x+3,y+3);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        Thread.sleep(1000);
        robot.mouseMove(x+desplazamiento.get(0)+10,y+3);
        Thread.sleep(1000);
        robot.mouseMove(x+desplazamiento.get(0)+10,y-desplazamiento.get(1));
        Thread.sleep(1000);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }

    public static List<Integer> calculoPixeles(WebElement from , WebElement to,JavascriptExecutor js){
        List<Integer> posiciones = new ArrayList<>();
        //Calculo Pantalla
        int aumentY = calculoPantalla(js);

        //Posicion de Panel
        int x1 = to.getLocation().getX();
        int y1 = to.getLocation().getY()+aumentY;

        //Posicion de la caja
        int x2 = from.getLocation().getX();
        int y2 = from.getLocation().getY()+aumentY;

        //Desplazamiento
        int desX = x1-x2;
        int desy = y2-y1;


        posiciones.add(desX);
        posiciones.add(desy);

        return posiciones;
    }

    public static int calculoPantalla(JavascriptExecutor js){
        int fijo;
        int tamWindow = js.executeScript("return(window.innerHeight);").hashCode();
        int tamScreen = js.executeScript("return(screen.height);").hashCode();

        fijo = tamScreen -tamWindow;

        return fijo -40;
    }

}
