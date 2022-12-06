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
            }else{
                Assert.assertEquals("No se encontro la jerarquia","NO");

            }
        }
    }


}
