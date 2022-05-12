package HomepageFunctions;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import java.time.Duration;



public class Home_Page {

    private WebDriver driver;
    private String url;

    public Home_Page(WebDriver driver) {
        this.driver = driver;
        this.url = "http://wedox.sytes.net/buplat_config/";
    }

    public void loginPage(String user,String password){
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.findElement(By.id("__xmlview0--inputUserName-inner")).sendKeys(user);
        driver.findElement(By.id("__xmlview0--inputPassword-inner")).sendKeys(password);
        driver.findElement(By.id("__xmlview0--btnSubmit")).click();
    }

}