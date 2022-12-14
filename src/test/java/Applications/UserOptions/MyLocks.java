package Applications.UserOptions;

import Helpers.BasicControl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MyLocks {

    private WebDriver driver;
    private WebDriverWait wait;
    private BasicControl basicControl;

    private By title = By.xpath("//span[text()='Mis Bloqueos' or text()='My Locks']");
    private By btnClose =  By.xpath("//div[contains(@id,'--dMyLocks-footer')]//bdi[text()='Cerrar' or text()='Close']");


    public MyLocks(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        this.basicControl = new BasicControl(driver);
    }

    public void checkMyLocks() throws InterruptedException {
        WebElement titleLocks = driver.findElement(title);
        wait.until(ExpectedConditions.visibilityOf(titleLocks));
        Thread.sleep(1000);
        driver.findElement(btnClose).click();

    }

}
