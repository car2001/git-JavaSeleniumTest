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

public class MyProfile {

    private WebDriver driver;
    private WebDriverWait wait;
    private BasicControl basicControl;
    private JavascriptExecutor js;

    private By busyProfile   = By.xpath("//div[contains(@id,'--objPageLayoutUserProfile-busyIndicator')]");
    private By layoutProfile = By.xpath("//section[contains(@id,'objPageLayoutUserProfile-sectionsContainer')]");
    private By btnClose = By.xpath("//bdi[text()='Cerrar' or text()='Close']");
    private By btnSave = By.xpath("//bdi[text()='Guardar' or text()='Save']");


    public MyProfile(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        this.basicControl = new BasicControl(driver);
        this.js = (JavascriptExecutor) driver;
    }

    public void checkMyProfile() throws InterruptedException {
        WebElement profileBusy = driver.findElement(busyProfile);
        wait.until(ExpectedConditions.visibilityOf(profileBusy));
        wait.until(ExpectedConditions.invisibilityOf(profileBusy));
        Thread.sleep(1000);
        String xmlview = basicControl.getXmlview();
        driver.findElement(btnClose).click();
        basicControl.waitXmlview(xmlview);
        int tam = js.executeScript("let tam = document.getElementById('sap-ui-blocklayer-popup'); return(tam.clientHeight)").hashCode();
        if(tam != 0){
            ChargePopPup.PopPupGeneral(driver,wait);
        }
    }


}
