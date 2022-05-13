package HomepageFunctions;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Login_Applications {
    public static WebDriverWait wait;

    public static void loginOSM(WebDriver driver){
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        String routeOSM = "//span[@class='sapMTextMaxLine sapMTextLineClamp' and normalize-space()='Organizational Structure Manager']";
        driver.findElement(By.xpath(routeOSM)).click();
        driver.findElement(By.id("__xmlview4--mainTree-rows-row0-treeicon")).click();
        driver.findElement(By.id("__xmlview4--mainTree-rows-row1-treeicon")).click();
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("__xmlview4--mainTree-vsb")));
        }catch (Exception e){
            System.out.println("Se espero el Scroll pero no se encontro");
        }
    }

    public static void loginCM(WebDriver driver,String componente){
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String routeCM = "//span[@class='sapMTextMaxLine sapMTextLineClamp' and normalize-space()='Configuration Manager']";
        driver.findElement(By.xpath(routeCM)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("navListItem-navList-0-a")));
        driver.findElement(By.xpath("//div[@title='Reusable Component']")).click();
        driver.findElement(By.xpath("//div[@title='Setting']")).click();
        driver.findElement(By.xpath("//span[text()='"+componente+"']")).click();
        String progress;
        String more = "//span[text()='Más' and @class='sapMSLITitle']";
        if(componente.equals("Form UI Configuration") || componente.equals("Global Settings")){
            progress = "//div[@class='sapUiLocalBusyIndicator sapUiLocalBusyIndicatorSizeBig sapUiLocalBusyIndicatorFade' and @role='progressbar']";
            try {
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(progress)));
                driver.findElement(By.xpath(more)).click();
            }catch (Exception error){
                System.out.println("No hay elemento más");
            }
        }else{
            progress = "//div[@id='__xmlview5--searchText-search']";
            try {
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath(progress)));
                driver.findElement(By.xpath(more)).click();
            }catch (Exception er){
                System.out.println("No hay elemento más");

            }
        }
    }

    public static void loginRM(WebDriver driver, String componente){
        String routeRM = "//span[@class='sapMTextMaxLine sapMTextLineClamp' and normalize-space()='Release Manager']";
        driver.findElement(By.xpath(routeRM)).click();
        if(componente.equals("Project")){
            driver.findElement(By.id("__xmlview4--mainTree-rows-row0-treeicon")).click();
        }else if(componente.equals("Change Container")){
            driver.findElement(By.id("__xmlview4--mainTree-rows-row1-treeicon")).click();
        }else if (componente.equals("Deployment Package")){
            driver.findElement(By.id("__xmlview4--mainTree-rows-row2-treeicon")).click();
        }else{
            driver.findElement(By.id("__xmlview4--mainTree-rows-row3-treeicon")).click();
        }
    }


}
