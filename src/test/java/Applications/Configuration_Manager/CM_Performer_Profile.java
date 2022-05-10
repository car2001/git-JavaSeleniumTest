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

public class CM_Performer_Profile {

    private WebDriver driver;
    private String chosen_browser = "Chrome";

    SelectBrowser browser = new SelectBrowser(driver);
    Home_Page login;

    String componente = "Performer Profile";
    String newPerformer = "Performer Selenium";
    String editPerfomer = "Performer Edit Selenium";
    String versionMayor_PP = "Performer Selenium Version Mayor";
    String versionMenor_PP = "Performer Selenium Version Menor";
    String restoreVersion_PP = "Performer Selenium Restaurado";

    @BeforeMethod
    public void SetUp(){
        browser.chooseBrowser(chosen_browser);
        driver = browser.getDriver();
        login = new Home_Page(driver);
        login.loginPage("cpingo","1234");
        Login_Applications.loginCM(driver,componente);
    }

    @Test
    public void crearPerformerProfile(){
        FormsCM.formCreatePerformer(driver,newPerformer);
        String message = driver.findElement(By.className("sapMMsgStripMessage")).getAttribute("textContent");
        Assert.assertEquals(message,"The Operation has been Completed Successfully."+ "\n");
    }

    @Test(priority = 1)
    public void viewDependecies_PP(){
        driver.findElement(By.xpath("//div[text()='"+newPerformer+"']")).click();
        driver.findElement(By.id("__xmlview5--viewDependencies-img")).click();
        String message = driver.findElement(By.id("__xmlview5--dependenciesTableTitle-inner")).getText();
        Assert.assertEquals(message,"Dependencies List");
    }

    @Test(priority = 2)
    public void editarPerformerProfile(){
        driver.findElement(By.xpath("//div[text()='"+newPerformer+"']")).click();
        FormsCM.formEditPerformer(driver,editPerfomer);
        String message = driver.findElement(By.className("sapMMsgStripMessage")).getAttribute("textContent");
        Assert.assertEquals(message,"The Operation has been Completed Successfully."+ "\n");
    }

    @Test(priority = 3)
    public void versionMayor_PP(){
        driver.findElement(By.xpath("//div[text()='"+editPerfomer+"']")).click();
        FormsCM.MayorVersionPerformer(driver,versionMayor_PP);
        String message = driver.findElement(By.className("sapMMsgStripMessage")).getAttribute("textContent");
        Assert.assertEquals(message,"The Operation has been Completed Successfully."+ "\n");
    }

    @Test(priority = 4)
    public void versionMenor_PP(){
        driver.findElement(By.xpath("//div[text()='"+versionMayor_PP+"']")).click();
        FormsCM.MenorVersionPerformer(driver,versionMenor_PP);
        String message = driver.findElement(By.className("sapMMsgStripMessage")).getAttribute("textContent");
        Assert.assertEquals(message,"The Operation has been Completed Successfully."+ "\n");
    }

    @Test(priority = 5)
    public void restoreVersion_PP(){
        driver.findElement(By.xpath("//div[text()='"+versionMenor_PP+"']")).click();
        FormsCM.restoreVersion_PP(driver,restoreVersion_PP);
        String message = driver.findElement(By.className("sapMMsgStripMessage")).getAttribute("textContent");
        Assert.assertEquals(message,"The Operation has been Completed Successfully."+ "\n");
    }

    @Test(priority = 6)
    public void eliminarPerformerProfile(){
        driver.findElement(By.xpath("//div[text()='"+restoreVersion_PP+"']/parent::div/parent::div/following-sibling::button")).click();
        driver.findElement(By.xpath("//bdi[normalize-space()='Si']")).click();
        String xpathMessage = "//span[@class='sapMText sapUiSelectable sapMTextMaxWidth sapMMsgBoxText']";
        driver.findElement(By.xpath("//bdi[normalize-space()='OK']")).click();
        String message = driver.findElement(By.xpath(xpathMessage)).getText();
        Assert.assertEquals(message,"The Operation has been Completed Successfully.");
    }

    @AfterMethod
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }

}
