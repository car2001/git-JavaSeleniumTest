package Forms.ProcessManager;

import Helpers.BasicControl;
import Helpers.FormsControl;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class FormsProcess {

    private static List<WebElement> listForm;
    private static BasicControl basicControl;
    private static String isAutomated = "//div[contains(@id,'--isAutomated-handle')]";
    private static String addSecurityProfile = "//span[contains(@id,'--addSecurityProfile-inner')]";
    private static String groupPermisos = "//span[ contains(@id,'__select') and @class='sapMSltArrow']";
    private static String selectType  = "//span[contains(@id,'--typeSelect-') and @class='sapMSltArrow' ]";
    private static String valueA = "//span[contains(@id,'--valueSelect-') and @class='sapMSltArrow']";
    private static String inputINS = "//input[contains(@id,'--instanceNumbering-inner')]";
    private static String inputSLA = "//input[contains(@id,'--selectSLAPROProperty-inner')]";
    private static String slaDays = "//input[contains(@id,'--numberOfDaysPRO-inner')]";

    public static void createProcess(WebDriver driver, String process , Actions action, String INS, String SLA ) throws InterruptedException {
        basicControl = new BasicControl(driver);
        listForm = FormsControl.controlNew(driver,"proceso","");
        listForm.get(2).click();
        listForm.get(2).sendKeys(process);
        listForm.get(3).click();
        listForm.get(3).sendKeys(process);
        listForm.get(4).click();
        listForm.get(4).sendKeys(process);
        Thread.sleep(1000);
        driver.findElement(By.xpath(isAutomated)).click();
        //SECURITY PROFILE
        securityProfile(driver,action);
        Thread.sleep(1000);
        // INS
        clickProcessConfigurationIcons(driver,"Instance Creation");
        driver.findElement(By.xpath(inputINS)).click();
        driver.findElement(By.xpath(inputINS)).sendKeys(INS);
        //SLA
        clickProcessConfigurationIcons(driver,"SLA");
        driver.findElement(By.xpath(inputSLA)).click();
        driver.findElement(By.xpath(inputSLA)).sendKeys(SLA);
        driver.findElement(By.xpath(inputSLA)).sendKeys(Keys.TAB);
        driver.findElement(By.xpath(slaDays)).click();
        driver.findElement(By.xpath(slaDays)).sendKeys("5");
        basicControl.btnSave();
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

    public static void clickProcessConfigurationIcons(WebDriver driver, String processConfiguration){
        int iconProcess;
        String moreIcons = "//div[contains(@id,'--itbAutomationProcess--header-overflow-text')]";

        switch (processConfiguration)
        {
            case "Security Profile":
                iconProcess = driver.findElement(By.xpath("//span[contains(@id,'--itfSecurityProfile-icon')]")).getRect().getHeight();
                if(iconProcess == 0){
                    driver.findElement(By.xpath(moreIcons)).click();
                    driver.findElement(By.xpath("//li[contains(@id,'--itfSecurityProfile-')]")).click();
                }else{
                    driver.findElement(By.xpath("//span[contains(@id,'--itfSecurityProfile-icon')]")).click();
                }
                break;
            case "Automation Features":
                iconProcess = driver.findElement(By.xpath("//span[contains(@id,'--itfAutomation-icon')]")).getRect().getHeight();
                if(iconProcess == 0){
                    driver.findElement(By.xpath(moreIcons)).click();
                    driver.findElement(By.xpath("//li[contains(@id,'--itfAutomation-')]")).click();
                }else{
                    driver.findElement(By.xpath("//span[contains(@id,'--itfAutomation-icon')]")).click();
                }
                break;
            case "Form UI":
                iconProcess = driver.findElement(By.xpath("//span[contains(@id,'--itfFormOptions-icon')]")).getRect().getHeight();
                if(iconProcess == 0){
                    driver.findElement(By.xpath(moreIcons)).click();
                    driver.findElement(By.xpath("//li[contains(@id,'--itfFormOptions-')]")).click();
                }else{
                    driver.findElement(By.xpath("//span[contains(@id,'--itfFormOptions-icon')]")).click();
                }
                break;
            case "Instance Creation":
                iconProcess = driver.findElement(By.xpath("//span[contains(@id,'--itfInstanceNumbering-icon')]")).getRect().getHeight();
                if(iconProcess == 0){
                    driver.findElement(By.xpath(moreIcons)).click();
                    driver.findElement(By.xpath("//li[contains(@id,'--itfInstanceNumbering-')]")).click();
                }else{
                    driver.findElement(By.xpath("//span[contains(@id,'--itfInstanceNumbering-icon')]")).click();
                }
                break;
            case "SLA":
                iconProcess = driver.findElement(By.xpath("//span[contains(@id,'--itfSLAConfiguration-icon')]")).getRect().getHeight();
                if(iconProcess == 0){
                    driver.findElement(By.xpath(moreIcons)).click();
                    driver.findElement(By.xpath("//li[contains(@id,'--itfSLAConfiguration-')]")).click();
                }else{
                    driver.findElement(By.xpath("//span[contains(@id,'--itfSLAConfiguration-icon')]")).click();
                }
                break;
            case "Notification":
                iconProcess = driver.findElement(By.xpath("//span[contains(@id,'--itNotificationProfile-icon')]")).getRect().getHeight();
                if(iconProcess == 0){
                    driver.findElement(By.xpath(moreIcons)).click();
                    driver.findElement(By.xpath("//li[contains(@id,'--itNotificationProfile-')]")).click();
                }else{
                    driver.findElement(By.xpath("//span[contains(@id,'--itNotificationProfile-icon')]")).click();
                }
                break;
            case "Risk Management":
                iconProcess = driver.findElement(By.xpath("//span[contains(@id,'--idRiskProfileProfile-icon')]")).getRect().getHeight();
                if(iconProcess == 0){
                    driver.findElement(By.xpath(moreIcons)).click();
                    driver.findElement(By.xpath("//li[contains(@id,'--idRiskProfileProfile-')]")).click();
                }else{
                    driver.findElement(By.xpath("//span[contains(@id,'--idRiskProfileProfile-icon')]")).click();
                }
                break;
            case "Project Management":
                iconProcess = driver.findElement(By.xpath("//span[contains(@id,'--idProjectManagement-icon')]")).getRect().getHeight();
                if(iconProcess == 0){
                    driver.findElement(By.xpath(moreIcons)).click();
                    driver.findElement(By.xpath("//li[contains(@id,'--idProjectManagement-')]")).click();
                }else{
                    driver.findElement(By.xpath("//span[contains(@id,'--idProjectManagement-icon')]")).click();
                }
                break;
            case "Indexed Fields":
                iconProcess = driver.findElement(By.xpath("//span[contains(@id,'--idIndexedManagement-icon')]")).getRect().getHeight();
                if(iconProcess == 0){
                    driver.findElement(By.xpath(moreIcons)).click();
                    driver.findElement(By.xpath("//li[contains(@id,'--idIndexedManagement-')]")).click();
                }else{
                    driver.findElement(By.xpath("//span[contains(@id,'--idIndexedManagement-icon')]")).click();
                }
                break;
        }
    }
}
