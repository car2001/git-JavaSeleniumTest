package Forms;

import Helpers.FormsControl;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class FormsPM {
    private static List<WebElement> listForm;
    private static String save = "__xmlview4--save-img";
    private static String edit= "__xmlview4--edit-img";
    private static String version = "__xmlview4--newVersion-inner";
    private static String versionHistory = "__xmlview4--versionHistory-img";
    private static String num = "4";


    //HIERARCHIE
    public static void createNewHierarchie(WebDriver driver,String hierarchie){
        listForm = FormsControl.controlNewWithoutFocus(driver,"nivel",num);
        listForm.get(2).click();
        listForm.get(2).sendKeys(hierarchie);
        listForm.get(3).click();
        listForm.get(3).sendKeys(hierarchie);
        listForm.get(4).click();
        listForm.get(4).sendKeys(hierarchie);
        driver.findElement(By.id(save)).click();
    }

    //PROCESS
    public static void creteNewProcess(WebDriver driver, String process , Actions action, String INS, String SLA , JavascriptExecutor js){
        listForm = FormsControl.controlNew(driver,"proceso",num);
        listForm.get(2).click();
        listForm.get(2).sendKeys(process);
        listForm.get(3).click();
        listForm.get(3).sendKeys(process);
        listForm.get(4).click();
        listForm.get(4).sendKeys(process);
        driver.findElement(By.id("__xmlview4--isAutomated-handle")).click();
        //SECURITY PROFILE
        WebElement btnSecurity =  driver.findElement(By.id("__xmlview4--addSecurityProfile-inner"));
        action.doubleClick(btnSecurity).perform();
        driver.findElements(By.cssSelector(".sapMSltArrow")).get(0).click();
        String options = ".sapMSelectListItemBase.sapMSelectListItem.sapMSelectListItemBaseHoverable";
        driver.findElements(By.cssSelector(options)).get(0).click();
        driver.findElements(By.cssSelector(".sapMSltArrow")).get(1).click();
        driver.findElements(By.cssSelector(options)).get(9).click();
        driver.findElements(By.cssSelector(".sapMSltArrow")).get(2).click();
        List<WebElement> superadmin = driver.findElements(By.xpath("//li[text()='Superadmin']"));
        superadmin.get(0).click();
        driver.findElements(By.cssSelector(".sapMSltArrow")).get(3).click();
        driver.findElements(By.xpath("//li[text()='Administrator' and @class= 'sapMSelectListItemBase sapMSelectListItem sapMSelectListItemBaseHoverable']")).get(1).click();
        driver.findElements(By.cssSelector(".sapMSltArrow")).get(4).click();
        driver.findElements(By.xpath("//li[text()='Role' and @class= 'sapMSelectListItemBase sapMSelectListItem sapMSelectListItemBaseHoverable']")).get(0).click();
        driver.findElements(By.cssSelector(".sapMSltArrow")).get(5).click();
        superadmin = driver.findElements(By.xpath("//li[text()='Superadmin']"));
        superadmin.get(1).click();
        // INS
        if(js.executeScript("let INS = document.getElementById('__xmlview4--itfInstanceNumbering-icon');return INS.clientHeight").hashCode() == 0){
            driver.findElement(By.id("__xmlview4--itbAutomationProcess--header-overflow-text")).click();
            driver.findElement(By.xpath("//li[@title = 'Numeración de instancia']")).click();
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


}
