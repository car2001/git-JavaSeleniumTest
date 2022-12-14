package Applications.UserOptions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class About {

    private WebDriver driver;

    private By productVersion = By.xpath("//bdi[contains(@id,'--Version-bdi')]");
    private By patchNumber = By.xpath("//bdi[contains(@id,'--Patch-bdi')]");
    private By buildNumber = By.xpath("//bdi[contains(@id,'--Build-bdi')]");
    private By distribution = By.xpath("//bdi[contains(@id,'--Distribution-bdi')]");
    private By closeAbout = By.xpath("//section[contains(@id,'--about-cont')]//button[@title='Decline' or @title='Rechazar' ]");

    public About(WebDriver driver){
        this.driver = driver;
    }

    public void checkAbout() throws InterruptedException {
        driver.findElement(productVersion);
        driver.findElement(patchNumber);
        driver.findElement(buildNumber);
        driver.findElement(distribution);
        Thread.sleep(1000);
        driver.findElement(closeAbout).click();
    }




}
