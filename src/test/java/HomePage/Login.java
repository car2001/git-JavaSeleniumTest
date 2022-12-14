package HomePage;

import Helpers.BasicControl;
import Helpers.ChargePopPup;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;



public class Login {

    private WebDriver driver;
    private String url;
    private String user;
    private String password;
    private BasicControl basicControl;
    private WebDriverWait wait;

    public Login(WebDriver driver) {
        this.driver = driver;
        //this.url = "https://cloud.buplat.com/IDO_SANDBOX/";
        //this.url = "http://wedox.sytes.net/buplat_config/";
        //this.url = "https://cloudbuplat.com/WEDOX_CONFIG/";
        //this.url = "http://wedox.sytes.net/BHF_CFG/";
        this.url = "http://wedox.sytes.net/buplat_rt_config/";

        //this.user = "jjuarez";
        this.user = "cpingo";
        this.password = "1234";
        this.basicControl = new BasicControl(driver);
        this.wait = new WebDriverWait(driver,Duration.ofSeconds(100));
    }


    public void loginPage(){
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//input[contains(@id,'--inputUserName-inner')]")).sendKeys(user);
        driver.findElement(By.xpath("//input[contains(@id,'--inputPassword-inner')]")).sendKeys(password);
        driver.findElement(By.xpath("//button[contains(@id,'--btnSubmit')]")).click();
        try {
            Thread.sleep(1800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.get(geturl()+"#/Applications");
    }

    public void loginPage(String url){
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//input[contains(@id,'--inputUserName-inner')]")).sendKeys(user);
        driver.findElement(By.xpath("//input[contains(@id,'--inputPassword-inner')]")).sendKeys(password);
        driver.findElement(By.xpath("//button[contains(@id,'--btnSubmit')]")).click();
    }



    public void loginPage(String user,String password){
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//input[contains(@id,'--inputUserName-inner')]")).sendKeys(user);
        driver.findElement(By.xpath("//input[contains(@id,'--inputPassword-inner')]")).sendKeys(password);
        driver.findElement(By.xpath("//button[contains(@id,'--btnSubmit')]")).click();
    }

    public String getUser() {
        return user;
    }

    public String geturl(){return url;}
}