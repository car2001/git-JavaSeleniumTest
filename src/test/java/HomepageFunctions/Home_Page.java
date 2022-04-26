package HomepageFunctions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Home_Page {

    private WebDriver driver;

    public Home_Page(){
        this.driver = new ChromeDriver();
    }

    public Home_Page(WebDriver driver) {
        this.driver = driver;
    }

    public void loginPage(String user,String password){
        driver.findElement(By.id("__xmlview0--inputUserName-inner")).sendKeys(user);
        driver.findElement(By.id("__xmlview0--inputPassword-inner")).sendKeys(password);
        driver.findElement(By.id("__xmlview0--btnSubmit")).click();
    }

}