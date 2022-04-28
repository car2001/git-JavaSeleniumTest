package Helpers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SelectBrowser {
    private WebDriver driver;


    public SelectBrowser(WebDriver driver){
        this.driver = driver;
    }

    public void chooseBrowser(String browser){
        if(browser.equals("Chrome")){
            WebDriverManager.chromedriver().setup();
            this.driver = new ChromeDriver();
        }else if(browser.equals("Edge")){
            WebDriverManager.edgedriver().setup();
            this.driver = new EdgeDriver();
        }else if (browser.equals("Firefox")) {
            WebDriverManager.firefoxdriver().setup();
            this.driver = new FirefoxDriver();
        }

    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
}
