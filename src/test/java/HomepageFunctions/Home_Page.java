package HomepageFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;


public class Home_Page {

    private WebDriver driver;
    private String url;

    public Home_Page(WebDriver driver) {
        this.driver = driver;
        this.url = "http://wedox.sytes.net/buplat_config/";
    }

    public void homeSettings() throws InterruptedException {
        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(2500);
    }

    public void loginPage(String user,String password) throws InterruptedException {
        driver.findElement(By.id("__xmlview0--inputUserName-inner")).sendKeys(user);
        driver.findElement(By.id("__xmlview0--inputPassword-inner")).sendKeys(password);
        driver.findElement(By.id("__xmlview0--btnSubmit")).click();
        Thread.sleep(2000);
    }

}