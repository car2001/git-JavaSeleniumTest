package Helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FormsOSM {

    private static List<WebElement> listForm;

    // LOCATION
    public static void formCreateLocation(WebDriver driver, String location){
        listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(2).sendKeys(location);
        listForm.get(3).sendKeys(location);
        listForm.get(4).sendKeys(location);
    }

    public static void formEditLocation(WebDriver driver, String location){
        //Editando Formulario
        listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(2).clear();
        listForm.get(2).sendKeys(location);
        listForm.get(3).clear();
        listForm.get(3).sendKeys(location);
        listForm.get(4).clear();
        listForm.get(4).sendKeys(location);
    }

    //POSITION

    public static void formCreatePosition(WebDriver driver, String position){
        //Llenando Formulario
        listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(2).sendKeys(position);
        listForm.get(3).sendKeys(position);
        listForm.get(4).sendKeys(position);
    }
    public static void formEditPosition(WebDriver driver, String position){
        listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(2).clear();
        listForm.get(2).sendKeys(position);
        listForm.get(3).clear();
        listForm.get(3).sendKeys(position);
        listForm.get(4).clear();
        listForm.get(4).sendKeys(position);
    }

}
