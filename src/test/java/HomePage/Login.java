package HomePage;

import Helpers.BasicControl;
import Helpers.ChargePopPup;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;



public class Login {

    private WebDriver driver;
    private String url;
    private String user;
    private String password;
    private BasicControl basicControl;

    public Login(WebDriver driver) {
        this.driver = driver;
        //this.url = "https://cloud.buplat.com/IDO_SANDBOX/";
        //this.url = "http://wedox.sytes.net/buplat_config/";
        //this.url = "https://cloudbuplat.com/WEDOX_CONFIG/";
        this.url = "http://wedox.sytes.net/BHF_CFG/";
        
        this.user = "jjuarez";
        this.password = "1234";
        this.basicControl = new BasicControl(driver);
    }


    public void loginPage(){
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//input[contains(@id,'--inputUserName-inner')]")).sendKeys(user);
        driver.findElement(By.xpath("//input[contains(@id,'--inputPassword-inner')]")).sendKeys(password);
        driver.findElement(By.xpath("//button[contains(@id,'--btnSubmit')]")).click();
        ChargePopPup.PopPupGeneral(driver,new WebDriverWait(driver,Duration.ofSeconds(10)));
        basicControl.logo();
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


}