package Helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Asserts {
    WebDriver driver ;

    public Asserts(WebDriver driver) { this.driver = driver; }

    public void assertSave(){
        String message = driver.findElement(By.className("sapMMsgStripMessage")).getAttribute("textContent");
        Assert.assertEquals(message,"The Operation has been Completed Successfully."+ "\n");
    }

    public void assertDependecies(int num){
        String message = driver.findElement(By.id("__xmlview"+num+"--dependenciesTableTitle-inner")).getText();
        Assert.assertEquals(message,"Dependencies List");
    }

    public void assertDelete(String xpathMessage ){
        String message = driver.findElement(By.xpath(xpathMessage)).getText();
        Assert.assertEquals(message,"The Operation has been Completed Successfully.");
    }

    public void assertDoubleCheck(String expected){
        String message = driver.findElement(By.className("sapMMsgStripMessage")).getAttribute("textContent");
        Assert.assertEquals(message,expected);
    }

}