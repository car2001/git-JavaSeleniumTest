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

    public static void loginRM(WebDriver driver){
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
        WebElement title = driver.findElement(By.xpath("//span[contains(@id,'--txtTitleOfProject')][text()='Process Manager']"));
        wait.until(ExpectedConditions.visibilityOf(title));
        ChargePopPup.PopPupGeneral(driver,wait);
        accessBranch.clickBranches(1);
    }

    public static void loginColl(WebDriver driver){
        wait = new WebDriverWait(driver,Duration.ofSeconds(100));
        basicControl = new BasicControl(driver);
        basicControl.btnApplication("Collaboration Workspace");
        ChargePopPup.PopPupGeneral(driver,wait);
        driver.findElement(By.id("navListItem-navList-2")).click();
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

    public static void loginDEM(WebDriver driver){
        wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        js = (JavascriptExecutor) driver;
        basicControl = new BasicControl(driver);
        basicControl.btnApplication("Data Entity Manager");
        WebElement title = driver.findElement(By.xpath("//span[contains(@id,'--txtTitleOfProject')][text()='Data Entity Manager']"));
        wait.until(ExpectedConditions.visibilityOf(title));
        ChargePopPup.PopPupGeneral(driver,wait);

    }

    public static void loginDRM(WebDriver driver){
        wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        js = (JavascriptExecutor) driver;
        basicControl = new BasicControl(driver);
        basicControl.btnApplication("Data Record Manager");
        WebElement title = driver.findElement(By.xpath("//span[contains(@id,'--txtTitleOfProject')][text()='Data Record Manager']"));
        wait.until(ExpectedConditions.visibilityOf(title));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@id,'--searchText-search')]")));
        WebElement blockLayer = driver.findElement(By.id("sap-ui-blocklayer-popup"));
        while (blockLayer.getAttribute("style").contains("visible;")){
            blockLayer = driver.findElement(By.id("sap-ui-blocklayer-popup"));
        }

    }

    public static void loginIS(WebDriver driver){
        wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        js = (JavascriptExecutor) driver;
        basicControl = new BasicControl(driver);
        basicControl.btnApplication("Integration Studio");
        WebElement blockLayer = driver.findElement(By.id("sap-ui-blocklayer-popup"));
        while (blockLayer.getAttribute("style").contains("visible;")){
            blockLayer = driver.findElement(By.id("sap-ui-blocklayer-popup"));
        }
    }

    public static void loginTA(WebDriver driver){
        wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        js = (JavascriptExecutor) driver;
        basicControl = new BasicControl(driver);
        basicControl.btnApplication("Technical Administrator");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("navListItem-navList-0-a")));

        driver.findElement(By.xpath("//div[@title='Lock Management']")).click();
    }

    public static void loginANL(WebDriver driver){
        wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        js = (JavascriptExecutor) driver;
        basicControl = new BasicControl(driver);
        basicControl.btnApplication("Analytics");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("navListItem-navList-0-a")));
        driver.findElement(By.xpath("//div[@title='Query']")).click();
    }

    public static void loginInBuplat(WebDriver driver){
        wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        js = (JavascriptExecutor) driver;
        basicControl = new BasicControl(driver);
        basicControl.btnApplication("InBUPLAT");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("navListItem-navList-0-a")));
    }

    public static void loginPRJM(WebDriver driver){
        wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        js = (JavascriptExecutor) driver;
        basicControl = new BasicControl(driver);
        basicControl.btnApplication("Project Manager");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("navListItem-navList-0-a")));

        driver.findElement(By.xpath("//div[@title='Checklist Management']")).click();
    }

}
