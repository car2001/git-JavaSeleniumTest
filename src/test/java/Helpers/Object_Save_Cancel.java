package Helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Object_Save_Cancel {
    private WebDriver driver;

    public Object_Save_Cancel(WebDriver driver){
        this.driver = driver;
    }

    public void save_Cancel(char decision) throws InterruptedException {
        if (decision == 'G'){
            driver.findElement(By.id("__xmlview4--save-img")).click();
            Thread.sleep(1000);
        }else {
            driver.findElement(By.id("__xmlview4--cancel-img")).click();
        }
    }
}

