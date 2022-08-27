package HomePage;


import Helpers.AccessBranch;
import Helpers.ChargePopPup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginApplications {
    public static WebDriverWait wait;
    public static AccessBranch accessBranch;

    public static void loginOSM(WebDriver driver){
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        accessBranch = new AccessBranch(driver);
        String routeOSM = "//span[@class='sapMTextMaxLine sapMTextLineClamp' and normalize-space()='Organizational Structure Manager']";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(routeOSM)));
        driver.findElement(By.xpath(routeOSM)).click();
        accessBranch.clickBranches(0);
        accessBranch.clickBranches(1);
    }

    public static void loginCM(WebDriver driver,String componente){
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String routeCM = "//span[@class='sapMTextMaxLine sapMTextLineClamp' and normalize-space()='Configuration Manager']";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(routeCM)));
        driver.findElement(By.xpath(routeCM)).click();
        ChargePopPup.PopPupGeneral(driver,wait);
        driver.findElement(By.xpath("//div[@title='Reusable Component']")).click();
        driver.findElement(By.xpath("//div[@title='Setting']")).click();
        driver.findElement(By.xpath("//span[text()='"+componente+"']")).click();
        ChargePopPup.PopPupGeneral(driver,wait);
        String mas = "//span[(text()='Más' or text()='More') and @class='sapMSLITitle']";
        try {
            WebElement more = driver.findElement(By.xpath(mas));
            Boolean moreDisplayed = more.isDisplayed();
            while (moreDisplayed){
                more.click();
                moreDisplayed  = driver.findElement(By.xpath(mas)).isDisplayed();
            }
        }catch (Exception error){
            System.out.println("No hay elemento más");
        }
    }

    public static void loginRM(WebDriver driver, String componente){
        wait = new WebDriverWait(driver,Duration.ofSeconds(50));
        accessBranch = new AccessBranch(driver);
        String routeRM = "//span[@class='sapMTextMaxLine sapMTextLineClamp' and normalize-space()='Release Manager']";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(routeRM)));
        driver.findElement(By.xpath(routeRM)).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@id,'--mainTree-rows-row0-treeicon')]")));
        if(componente.equals("Project")){
            accessBranch.clickBranches(0);
        }else if(componente.equals("Change Container")){
            accessBranch.clickBranches(1);
        }else if (componente.equals("Deployment Package")){
            accessBranch.clickBranches(2);
        }else if(componente.equals("Deployment Request")){
            accessBranch.clickBranches(3);
        }
    }

    public static void loginPM(WebDriver driver){
        wait = new WebDriverWait(driver,Duration.ofSeconds(100));
        accessBranch = new AccessBranch(driver);
        String routePM = "//span[@class='sapMTextMaxLine sapMTextLineClamp' and normalize-space()='Process Manager']";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(routePM)));
        driver.findElement(By.xpath(routePM)).click();
        //Esperamos las cargas
        ChargePopPup.PopPupGeneral(driver,wait);
        ChargePopPup.PopPupGeneral(driver,wait);
        accessBranch.clickBranches(1);
    }

    public static void loginColl(WebDriver driver, String proceso){
        wait = new WebDriverWait(driver,Duration.ofSeconds(100));
        String routePM = "//span[@class='sapMTextMaxLine sapMTextLineClamp' and normalize-space()='Collaboration Workspace']";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(routePM)));
        driver.findElement(By.xpath(routePM)).click();
        ChargePopPup.PopPupGeneral(driver,wait);
        driver.findElement(By.id("navListItem-navList-2")).click();
        ChargePopPup.PopPupGeneral(driver,wait);
        driver.findElement(By.xpath("//div[contains(@aria-label,'"+proceso+"')]")).click();
        driver.findElement(By.xpath("//bdi[text()='Sí']")).click();
        ChargePopPup.PopPupGeneral(driver,wait);
        ChargePopPup.PopPupGeneral(driver,wait);
    }

}
