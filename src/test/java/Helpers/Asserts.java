package Helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class Asserts {
    WebDriver driver ;
    JavascriptExecutor js;

    public Asserts(WebDriver driver) {
        this.driver = driver;
        js= (JavascriptExecutor) driver;
    }

    public void assertSave(){
        String message = driver.findElement(By.className("sapMMsgStripMessage")).getAttribute("textContent");
        if(message.contains("The")){
            Assert.assertEquals(message, "The Operation has been Completed Successfully."+ "\n" );
        }else{
            Assert.assertEquals(message, "La operación se ha completado con éxito."+ "\n" );
        }
        driver.findElement(By.xpath("//button[@title='Cerrar' or @title='Close']")).click();
    }



    public void assertSaveDiagram(){
        String message = driver.findElement(By.className("sapMMsgStripMessage")).getAttribute("textContent");
        Assert.assertEquals(message, "El diagrama se guardó con éxito.");
        driver.findElement(By.xpath("//button[@title='Cerrar' or @title='Close']")).click();
    }

    public void assertSaveDP(){
        String message = driver.findElement(By.className("sapMMsgStripMessage")).getAttribute("textContent");
        Assert.assertEquals(message.substring(0,54), "El paquete de instalación ha sido creado con el nombre");
        driver.findElement(By.xpath("//button[@title='Cerrar' or @title='Close']")).click();
    }

    public void assertSaveDR(){
        String message = driver.findElement(By.className("sapMMsgStripMessage")).getAttribute("textContent");
        Assert.assertEquals(message.substring(0,56), "La solicitud de instalación ha sido creado con el nombre");
        driver.findElement(By.xpath("//button[@title='Cerrar' or @title='Close']")).click();
    }

    public void assertSaveModelData(){
        String message = driver.findElement(By.className("sapMMsgStripMessage")).getAttribute("textContent");
        Assert.assertEquals(message, "Los atributos del modelo de datos se guardaron con éxito");
        driver.findElement(By.xpath("//button[@title='Cerrar' or @title='Close']")).click();
    }

    public void assertDependecies(){
        String message = driver.findElement(By.xpath("//span[contains(@id,'--dependenciesTableTitle-inner')]")).getText();
        if(message.contains("List")){
            Assert.assertEquals(message,"Dependencies List");
        }else {
            Assert.assertEquals(message,"Lista de dependencias");
        }
    }

    public void assertDelete(String xpathMessage ){
        String message = driver.findElement(By.xpath(xpathMessage)).getText();
        Assert.assertEquals(message,"La operación se ha completado con éxito.");
    }

    public void assertDoubleCheck(String expected){
        String message = driver.findElement(By.className("sapMMsgStripMessage")).getAttribute("textContent");
        Assert.assertEquals(message,expected);
        driver.findElement(By.xpath("//button[@title='Cerrar' or @title='Close']")).click();
        driver.findElement(By.xpath("//span[contains(@id,'--cancel-img')]")).click();
    }

}