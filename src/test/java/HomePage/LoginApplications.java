package HomePage;


import Helpers.AccessBranch;
import Helpers.BasicControl;
import Helpers.ChargePopPup;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginApplications {
    public static WebDriverWait wait;
    public static AccessBranch accessBranch;
    public static BasicControl basicControl;
    public static JavascriptExecutor js;

    public static void loginOSM(WebDriver driver){
        wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        accessBranch = new AccessBranch(driver);
        basicControl = new BasicControl(driver);
        basicControl.btnApplication("Organizational Structure Manager");
        accessBranch.clickBranches(0);
        accessBranch.clickBranches(1);
    }

    public static void loginCM(WebDriver driver){
        wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        js = (JavascriptExecutor) driver;
        basicControl = new BasicControl(driver);
        basicControl.btnApplication("Configuration Manager");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("navListItem-navList-0-a")));
        int tam = js.executeScript("let tam = document.getElementById('sap-ui-blocklayer-popup'); return(tam.clientHeight)").hashCode();
        if(tam != 0){
            ChargePopPup.PopPupGeneral(driver,wait);
        }
        driver.findElement(By.xpath("//div[@title='Reusable Component']")).click();
        driver.findElement(By.xpath("//div[@title='Setting']")).click();
    }

    public static void loginRM(WebDriver driver,String componente){
        wait = new WebDriverWait(driver,Duration.ofSeconds(100));
        accessBranch = new AccessBranch(driver);
        basicControl = new BasicControl(driver);
        basicControl.btnApplication("Release Manager");
        ChargePopPup.PopPupGeneral(driver,wait);
    }

    public static void loginPM(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        accessBranch = new AccessBranch(driver);
        basicControl = new BasicControl(driver);
        basicControl.btnApplication("Process Manager");
        //Esperamos las cargas
        ChargePopPup.PopPupGeneral(driver,wait);
        ChargePopPup.PopPupGeneral(driver,wait);
        accessBranch.clickBranches(1);
    }

    public static void loginColl(WebDriver driver, String proceso){
        wait = new WebDriverWait(driver,Duration.ofSeconds(100));
        basicControl = new BasicControl(driver);
        basicControl.btnApplication("Collaboration Workspace");
        ChargePopPup.PopPupGeneral(driver,wait);
        driver.findElement(By.id("navListItem-navList-2")).click();
        ChargePopPup.PopPupGeneral(driver,wait);
        driver.findElement(By.xpath("//div[contains(@aria-label,'"+proceso+"')]")).click();
        driver.findElement(By.xpath("//bdi[text()='Sí']")).click();
        ChargePopPup.PopPupGeneral(driver,wait);
        ChargePopPup.PopPupGeneral(driver,wait);
    }

    public static void loginSM(WebDriver driver){
        wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        js = (JavascriptExecutor) driver;
        basicControl = new BasicControl(driver);
        basicControl.btnApplication("Security Manager");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("navListItem-navList-0-a")));
        int tam = js.executeScript("let tam = document.getElementById('sap-ui-blocklayer-popup'); return(tam.clientHeight)").hashCode();
        if(tam != 0){
            ChargePopPup.PopPupGeneral(driver,wait);
        }
        driver.findElement(By.xpath("//div[@title='Object Management']")).click();
        driver.findElement(By.xpath("//div[@title='User & Role Management']")).click();
    }

}
