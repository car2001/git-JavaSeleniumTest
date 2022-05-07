package Applications.Configuration_Manager;

import Forms.FormsCM;
import Helpers.SelectBrowser;
import HomepageFunctions.Home_Page;
import HomepageFunctions.Login_Applications;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class CM_FORM_UI {

    private WebDriver driver;
    private String chosen_browser = "Chrome";
    public static WebDriverWait wait;

    SelectBrowser browser = new SelectBrowser(driver);
    Home_Page login;

    String componente = "Form UI Configuration";
    String newFormUI = "Form UI Selenium";

    @BeforeMethod
    public void setup() throws InterruptedException {
        browser.chooseBrowser(chosen_browser);
        driver = browser.getDriver();
        login = new Home_Page(driver);
        login.loginPage("cpingo","1234");
        Login_Applications.loginCM(driver,componente);
        decisionMore(driver);
    }

    @Test
    public void crear_FormUI() throws InterruptedException {
        driver.findElement(By.xpath("//div[@class='sapMBarRight sapMBarContainer']/button[@title='Añadir']")).click();
        FormsCM.formCreateFormUI(driver,newFormUI);
        String message = driver.findElement(By.className("sapMMsgStripMessage")).getAttribute("textContent");
        Assert.assertEquals(message,"The Operation has been Completed Successfully."+ "\n");
    }

    @Test
    public void editar_FormUI(){

    }

    public static void decisionMore(WebDriver driver){
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        String progress = "//div[@id='sap-ui-blocklayer-popup' and @class='sapUiBly sapUiBlyBusy']";
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(progress)));
        Boolean element_exist = driver.findElement(By.xpath("//span[text()='Más' and @class='sapMSLITitle']")).isDisplayed();
        if(element_exist == true){
            driver.findElement(By.xpath("//span[text()='Más' and @class='sapMSLITitle']")).click();
        }else{
            System.out.println("No hay  elemento Más");
        }
    }


}
