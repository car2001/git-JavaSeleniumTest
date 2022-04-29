package Helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Options {
    private WebDriver driver;

    public Options(WebDriver driver){
        this.driver = driver;
    }

    public void save() throws InterruptedException {
        driver.findElement(By.id("__xmlview4--save-img")).click();
        Thread.sleep(500);
    }

    public void cancel() throws InterruptedException {
        driver.findElement(By.id("__xmlview4--cancel-img")).click();
        Thread.sleep(500);
    }

    public void edit() throws InterruptedException {
        driver.findElement(By.id("__xmlview4--edit-img")).click(); // Editar Proyecto
        Thread.sleep(1000);
    }

    public void viewDependecies() throws InterruptedException {
        driver.findElement(By.id("__xmlview4--viewDependencies-img")).click();
        Thread.sleep(2000);
    }

}

