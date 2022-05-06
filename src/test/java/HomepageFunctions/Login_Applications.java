package HomepageFunctions;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Login_Applications {

    public static WebDriverWait wait;

    public static void loginOSM(WebDriver driver){
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        //Ingresamos al OSM
        String routeOSM = "//span[@class='sapMTextMaxLine sapMTextLineClamp' and normalize-space()='Organizational Structure Manager']";
        driver.findElement(By.xpath(routeOSM)).click();
        //Desplegamos Client
        driver.findElement(By.id("__xmlview4--mainTree-rows-row0-treeicon")).click();
        //Desplegamos Company
        driver.findElement(By.id("__xmlview4--mainTree-rows-row1-treeicon")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("__xmlview4--mainTree-vsb")));
    }


    public static void loginCM(WebDriver driver) throws InterruptedException {
        //Ingresamos al Configuration Manager
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        String routeCM = "//span[@class='sapMTextMaxLine sapMTextLineClamp' and normalize-space()='Configuration Manager']";
        driver.findElement(By.xpath(routeCM)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("navListItem-navList-0-a")));
        driver.findElement(By.xpath("//div[@title='Reusable Component']")).click();
        driver.findElement(By.xpath("//div[@title='Setting']")).click();
    }

}
