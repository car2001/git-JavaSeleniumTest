package Applications.Configuration_Manager;

import Forms.FormsCM;
import Helpers.SelectBrowser;
import HomepageFunctions.Home_Page;
import HomepageFunctions.Login_Applications;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CM_SLA {
    private WebDriver driver;
    private String chosen_browser = "Chrome";

    SelectBrowser browser = new SelectBrowser(driver);
    Home_Page login;

    @BeforeMethod
    public void setUp(){
        browser.chooseBrowser(chosen_browser);
        driver = browser.getDriver();
        login = new Home_Page(driver);
    }

    @Test
    public void crear_SLA() throws InterruptedException {
        login.loginPage("cpingo","1234");
        Login_Applications.loginCM(driver);
        driver.findElement(By.xpath("//span[text()='SLA Definition']")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//div[@class='sapMBarRight sapMBarContainer']/button[@title='Añadir']")).click();
        FormsCM.formCreateSLA(driver,"SLA Selenium");
        String message = driver.findElement(By.className("sapMMsgStripMessage")).getAttribute("textContent");
        Assert.assertEquals(message,"The Operation has been Completed Successfully."+ "\n");
    }

    @Test(priority = 1)
    public void editar_SLA() throws InterruptedException {
        login.loginPage("cpingo","1234");
        Login_Applications.loginCM(driver);
        driver.findElement(By.xpath("//span[text()='SLA Definition']")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//span[text()='Más' and @class='sapMSLITitle']")).click();
        driver.findElement(By.xpath("//div[text()='SLA Selenium']")).click();
        FormsCM.formEditSLA(driver,"SLA EDIT SELENIUM");
        String message = driver.findElement(By.className("sapMMsgStripMessage")).getAttribute("textContent");
        Assert.assertEquals(message,"The Operation has been Completed Successfully."+ "\n");
    }

    @Test(priority = 2)
    public void eliminar_SLA() throws InterruptedException {
        login.loginPage("cpingo","1234");
        Login_Applications.loginCM(driver);
        driver.findElement(By.xpath("//span[text()='SLA Definition']")).click();
        Thread.sleep(1800);
        driver.findElement(By.xpath("//span[text()='Más' and @class='sapMSLITitle']")).click();
        driver.findElement(By.xpath("//div[text()='SLA EDIT SELENIUM']/parent::div/parent::div/following-sibling::button")).click();
        driver.findElement(By.xpath("//bdi[normalize-space()='Si']")).click();
        Thread.sleep(1000);
        String message = driver.findElement(By.xpath("//span[@class='sapMText sapUiSelectable sapMTextMaxWidth sapMMsgBoxText']")).getText();
        Thread.sleep(1000);
        if(message.equals("The Operation has been Completed Successfully.")){
            driver.findElement(By.xpath("//bdi[normalize-space()='OK']")).click();
        }else{
            driver.findElement(By.xpath("//bdi[normalize-space()='Cerrar']")).click();
        }
        Assert.assertEquals(message,"The Operation has been Completed Successfully.");
    }

    @AfterMethod
    public void tearDown(){
        if (driver != null){
            driver.quit();
        }
    }

}
