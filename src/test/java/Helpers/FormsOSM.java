package Helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FormsOSM {

    public static void formCreateLocation(WebDriver driver, String location){
        //Llenando Formulario
        List<WebElement> listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(2).sendKeys(location);
        listForm.get(3).sendKeys(location);
        listForm.get(4).sendKeys(location);
    }

    public static void formEditLocation(WebDriver driver, String location){
        //Llenando Formulario
        List<WebElement> listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(2).clear();
        listForm.get(2).sendKeys(location);
        listForm.get(3).clear();
        listForm.get(3).sendKeys(location);
        listForm.get(4).clear();
        listForm.get(4).sendKeys(location);
    }
}
