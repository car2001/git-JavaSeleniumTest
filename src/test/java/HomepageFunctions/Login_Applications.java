package HomepageFunctions;


import Helpers.AccessBranches;
import Helpers.CargaPopPup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class Login_Applications {
    public static WebDriverWait wait;
    public static AccessBranches accessBranch;

    public static void loginOSM(WebDriver driver){
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        accessBranch = new AccessBranches(driver);
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
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("navListItem-navList-0-a")));
        CargaPopPup.PopPupGeneral(driver,wait);
        driver.findElement(By.xpath("//div[@title='Reusable Component']")).click();
        driver.findElement(By.xpath("//div[@title='Setting']")).click();
        driver.findElement(By.xpath("//span[text()='"+componente+"']")).click();
        CargaPopPup.PopPupGeneral(driver,wait);
        String mas = "//span[text()='Más' and @class='sapMSLITitle']";
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
        accessBranch = new AccessBranches(driver);
        String routeRM = "//span[@class='sapMTextMaxLine sapMTextLineClamp' and normalize-space()='Release Manager']";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(routeRM)));
        driver.findElement(By.xpath(routeRM)).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("__xmlview4--mainTree-rows-row0-treeicon")));
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
        accessBranch = new AccessBranches(driver);
        String routePM = "//span[@class='sapMTextMaxLine sapMTextLineClamp' and normalize-space()='Process Manager']";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(routePM)));
        driver.findElement(By.xpath(routePM)).click();
        //Esperamos las cargas
        CargaPopPup.PopPupGeneral(driver,wait);
        CargaPopPup.PopPupGeneral(driver,wait);
        accessBranch.clickBranches(1);
    }

    public static void loginColl(WebDriver driver, String proceso){
        wait = new WebDriverWait(driver,Duration.ofSeconds(100));
        String routePM = "//span[@class='sapMTextMaxLine sapMTextLineClamp' and normalize-space()='Collaboration Workspace']";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(routePM)));
        driver.findElement(By.xpath(routePM)).click();
        CargaPopPup.PopPupGeneral(driver,wait);
        driver.findElement(By.id("navListItem-navList-2")).click();
        CargaPopPup.PopPupGeneral(driver,wait);
        driver.findElement(By.xpath("//div[contains(@aria-label,'"+proceso+"')]")).click();
        driver.findElement(By.xpath("//bdi[text()='Sí']")).click();
        CargaPopPup.PopPupGeneral(driver,wait);
        CargaPopPup.PopPupGeneral(driver,wait);
    }

}
