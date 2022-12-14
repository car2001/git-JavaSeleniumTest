package Applications.UserOptions;

import Helpers.BasicControl;
import Helpers.ChargePopPup;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Preferences {

    private WebDriver driver;
    private WebDriverWait wait;
    private BasicControl basicControl;
    private JavascriptExecutor js;

    private By headerData = By.xpath("//div[contains(@id,'--userData-title')][text()='Preferencias' or text()='Preferences']");
    private By btnClose = By.xpath("//div[contains(@id,'xmlview') and contains(@class,'sapUiView sapUiXMLView sapMNavItem') and not(contains(@class,'sapMNavItemHidden'))]//bdi[text()='Cerrar' or text()='Close']");
    private By btnSave = By.xpath("//div[contains(@id,'xmlview') and contains(@class,'sapUiView sapUiXMLView sapMNavItem') and not(contains(@class,'sapMNavItemHidden'))]//bdi[text()='Cerrar' or text()='Close']");

    public Preferences(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        this.basicControl = new BasicControl(driver);
        this.js = (JavascriptExecutor) driver;
    }

    public void checkMyPreferences() throws InterruptedException {
        Thread.sleep(1000);
        WebElement header = driver.findElement(headerData);
        wait.until(ExpectedConditions.visibilityOf(header));
        Thread.sleep(1000);
        String xmlview = basicControl.getXmlview();
        driver.findElement(btnClose).click();
        basicControl.waitXmlview(xmlview);

    }
}
