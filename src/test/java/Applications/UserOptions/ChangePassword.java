package Applications.UserOptions;

import Helpers.BasicControl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ChangePassword {

    private WebDriver driver;
    private WebDriverWait wait;
    private BasicControl basicControl;

    private By title = By.xpath("//div[contains(@id,'--changePassword-title')]");
    private By btnClose = By.xpath("//div[contains(@id,'xmlview') and contains(@class,'sapUiView sapUiXMLView sapMNavItem') and not(contains(@class,'sapMNavItemHidden'))]//bdi[text()='Cerrar' or text()='Close']");
    private By btnSave = By.xpath("//div[contains(@id,'xmlview') and contains(@class,'sapUiView sapUiXMLView sapMNavItem') and not(contains(@class,'sapMNavItemHidden'))]//bdi[text()='Cerrar' or text()='Close']");

    public ChangePassword(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        this.basicControl = new BasicControl(driver);
    }

    public void checkChangePassword() throws InterruptedException {
        Thread.sleep(2000);
        WebElement titlePassword = driver.findElement(title);
        wait.until(ExpectedConditions.visibilityOf(titlePassword));
        Thread.sleep(1000);
        String xmlview = basicControl.getXmlview();
        driver.findElement(btnClose).click();
        basicControl.waitXmlview(xmlview);
    }

}
