package HomepageFunctions;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Login_Applications {
    public static WebDriverWait wait;

    public static void loginOSM(WebDriver driver){
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //Ingresamos al OSM
        String routeOSM = "//span[@class='sapMTextMaxLine sapMTextLineClamp' and normalize-space()='Organizational Structure Manager']";
        driver.findElement(By.xpath(routeOSM)).click();
        //Desplegamos Client
        driver.findElement(By.id("__xmlview4--mainTree-rows-row0-treeicon")).click();
        //Desplegamos Company
        driver.findElement(By.id("__xmlview4--mainTree-rows-row1-treeicon")).click();
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("__xmlview4--mainTree-vsb")));
        }catch (Exception e){
            System.out.println("No hay más elementos");
        }
    }

    public static void loginCM(WebDriver driver,String componente){
        //Ingresamos al Configuration Manager
        wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        String routeCM = "//span[@class='sapMTextMaxLine sapMTextLineClamp' and normalize-space()='Configuration Manager']";
        driver.findElement(By.xpath(routeCM)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("navListItem-navList-0-a")));
        driver.findElement(By.xpath("//div[@title='Reusable Component']")).click();
        driver.findElement(By.xpath("//div[@title='Setting']")).click();
        driver.findElement(By.xpath("//span[text()='"+componente+"']")).click();
        try {
            String progress = "//div[@id='__xmlview5--searchText-search']";
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(progress)));
            driver.findElement(By.xpath("//span[text()='Más' and @class='sapMSLITitle']")).click();
        }catch (Exception e){
           System.out.println("No hay elemento más");
        }

    }




}
