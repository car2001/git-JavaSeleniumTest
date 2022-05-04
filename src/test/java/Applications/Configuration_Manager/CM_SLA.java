package Applications.Configuration_Manager;

import Forms.FormsCM;
import Helpers.SelectBrowser;
import HomepageFunctions.Home_Page;
import HomepageFunctions.Login_Applications;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class CM_SLA {
    private WebDriver driver;
    private String chosen_browser = "Chrome";

    SelectBrowser browser = new SelectBrowser(driver);
    Home_Page login;

    String componente = "SLA Definition";
    String newSLA = "SLA SELENIUM";
    String editSLA = "SLA EDIT SELENIUM";
    String versionMayor_Sla = "Sla Version Mayor Seleneium";
    String versionMenor_Sla = "Sla Version Menor Seleneium";
    String restoreVersion = "SLA Restaurado Selenium";


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
        driver.findElement(By.xpath("//span[text()='"+componente+"']")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//div[@class='sapMBarRight sapMBarContainer']/button[@title='Añadir']")).click();
        FormsCM.formCreateSLA(driver,newSLA);
        String message = driver.findElement(By.className("sapMMsgStripMessage")).getAttribute("textContent");
        Assert.assertEquals(message,"The Operation has been Completed Successfully."+ "\n");
    }

    @Test
    public void viewDependecies_SLA() throws InterruptedException{
        login.loginPage("cpingo","1234");
        Login_Applications.loginCM(driver);
        driver.findElement(By.xpath("//span[text()='"+componente+"']")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//span[text()='Más' and @class='sapMSLITitle']")).click();
        driver.findElement(By.xpath("//div[text()='"+newSLA+"']")).click();
        driver.findElement(By.id("__xmlview5--viewDependencies-img")).click();
        Thread.sleep(1200);
        String message = driver.findElement(By.id("__xmlview5--dependenciesTableTitle-inner")).getText();
        Assert.assertEquals(message,"Dependencies List");
    }

    @Test(priority = 1)
    public void editar_SLA() throws InterruptedException {
        login.loginPage("cpingo","1234");
        Login_Applications.loginCM(driver);
        driver.findElement(By.xpath("//span[text()='"+componente+"']")).click();
        Thread.sleep(1500);
        driver.findElement(By.xpath("//span[text()='Más' and @class='sapMSLITitle']")).click();
        driver.findElement(By.xpath("//div[text()='"+newSLA+"']")).click();
        FormsCM.formEditSLA(driver,editSLA);
        String message = driver.findElement(By.className("sapMMsgStripMessage")).getAttribute("textContent");
        Assert.assertEquals(message,"The Operation has been Completed Successfully."+ "\n");
    }

    @Test
    public void versionMayor_SLA()  throws InterruptedException{
        login.loginPage("cpingo","1234");
        Login_Applications.loginCM(driver);
        driver.findElement(By.xpath("//span[text()='"+componente+"']")).click();
        Thread.sleep(1800);
        driver.findElement(By.xpath("//span[text()='Más' and @class='sapMSLITitle']")).click();
        driver.findElement(By.xpath("//div[text()='"+editSLA+"']")).click();
        FormsCM.MayorVersionSLA(driver,versionMayor_Sla);
        String message = driver.findElement(By.className("sapMMsgStripMessage")).getAttribute("textContent");
        Assert.assertEquals(message,"The Operation has been Completed Successfully."+ "\n");
    }

    @Test
    public void versionMenor_SLA()  throws InterruptedException{
        login.loginPage("cpingo","1234");
        Login_Applications.loginCM(driver);
        driver.findElement(By.xpath("//span[text()='"+componente+"']")).click();
        Thread.sleep(1800);
        driver.findElement(By.xpath("//span[text()='Más' and @class='sapMSLITitle']")).click();
        driver.findElement(By.xpath("//div[text()='"+versionMayor_Sla+"']")).click();
        FormsCM.MenorVersionSLA(driver,versionMenor_Sla);
        String message = driver.findElement(By.className("sapMMsgStripMessage")).getAttribute("textContent");
        Assert.assertEquals(message,"The Operation has been Completed Successfully."+ "\n");
    }

    @Test
    public void restoreVersion_SLA()  throws InterruptedException{
        login.loginPage("cpingo","1234");
        Login_Applications.loginCM(driver);
        driver.findElement(By.xpath("//span[text()='"+componente+"']")).click();
        Thread.sleep(1800);
        driver.findElement(By.xpath("//span[text()='Más' and @class='sapMSLITitle']")).click();
        driver.findElement(By.xpath("//div[text()='"+versionMenor_Sla+"']")).click();
        FormsCM.restoreVersion_SLA(driver,restoreVersion);
        String message = driver.findElement(By.className("sapMMsgStripMessage")).getAttribute("textContent");
        Assert.assertEquals(message,"The Operation has been Completed Successfully."+ "\n");
    }

    @Test
    public void eliminar_SLA() throws InterruptedException {
        login.loginPage("cpingo","1234");
        Login_Applications.loginCM(driver);
        driver.findElement(By.xpath("//span[text()='"+componente+"']")).click();
        Thread.sleep(1800);
        driver.findElement(By.xpath("//span[text()='Más' and @class='sapMSLITitle']")).click();
        driver.findElement(By.xpath("//div[text()='"+restoreVersion+"']/parent::div/parent::div/following-sibling::button")).click();
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
        //if (driver != null){
            //driver.quit();
        //}
    }

}
