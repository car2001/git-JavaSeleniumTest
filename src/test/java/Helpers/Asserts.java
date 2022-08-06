package Helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Asserts {
    WebDriver driver ;

    public Asserts(WebDriver driver) { this.driver = driver; }

    public void assertSave(){
        String message = driver.findElement(By.className("sapMMsgStripMessage")).getAttribute("textContent");
        Assert.assertEquals(message, "La operación se ha completado con éxito."+ "\n" );
    }

    public void assertSaveDiagram(){
        String message = driver.findElement(By.className("sapMMsgStripMessage")).getAttribute("textContent");
        Assert.assertEquals(message, "El diagrama se guardó con éxito.");
    }

    public void assertSaveModelData(){
        String message = driver.findElement(By.className("sapMMsgStripMessage")).getAttribute("textContent");
        Assert.assertEquals(message, "Los atributos del modelo de datos se guardaron con éxito");
    }

    public void assertDependecies(int num){
        String message = driver.findElement(By.id("__xmlview"+num+"--dependenciesTableTitle-inner")).getText();
        Assert.assertEquals(message,"Dependencies List");
    }

    public void assertDelete(String xpathMessage ){
        String message = driver.findElement(By.xpath(xpathMessage)).getText();
        Assert.assertEquals(message,"La operación se ha completado con éxito.");
    }

    public void assertDoubleCheck(String expected){
        String message = driver.findElement(By.className("sapMMsgStripMessage")).getAttribute("textContent");
        Assert.assertEquals(message,expected);
    }

}