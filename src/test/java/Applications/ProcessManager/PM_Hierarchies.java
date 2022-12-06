package Applications.ProcessManager;

import Forms.FormsPM;
import Forms.ProcessManager.FormsHierarchie;
import Helpers.*;
import HomePage.Login;
import HomePage.LoginApplications;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PM_Hierarchies {

    private WebDriver driver;
    private Actions action;
    private DynamicScroll searchScrollElement;
    private Asserts asserts;
    private String component = "Process Hierarchies";


    public PM_Hierarchies(WebDriver driver){
        this.driver = driver;
        this.action = new Actions(driver);
        this.asserts = new Asserts(driver);
        this.searchScrollElement = new DynamicScroll(driver);
    }


    @Test
    public void crearHierarchies(String nameLevel) throws InterruptedException {
        WebElement hierarchies = driver.findElement(By.xpath("//span[text()='"+component+"']"));
        action.contextClick(hierarchies).perform();
        driver.findElement(By.xpath("//div[normalize-space()='New Level']")).click();
        Thread.sleep(1000);
        FormsHierarchie.createNewHierarchie(driver,nameLevel);
        asserts.assertSave();
    }

    @Test
    public void eliminarHierarchies(String nameLevel){
        int xpos = searchScrollElement.elementSearch(nameLevel);
        if(xpos != -1){
            WebElement hierarchie = driver.findElement(By.xpath("//span[text()='"+nameLevel+"']"));
            FormsControl.controlDelete(driver,action,hierarchie,"Level","Nivel");
        }else{
            Assert.assertEquals("No se encontro la jerarquia","NO");
        }
    }


}
