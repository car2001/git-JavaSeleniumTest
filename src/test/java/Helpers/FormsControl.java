package Helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class FormsControl {

    private static WebDriverWait wait;
    private static List<WebElement> listForm;

    public static void controlSave( WebDriver driver){
        wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        WebElement titulo = driver.findElement(By.xpath("//span[@id='__xmlview4--objFormTitle-inner']"));
        wait.until(ExpectedConditions.visibilityOf(titulo));
    }
    public static List<WebElement> controlEdit(WebDriver driver,String edit,String componente){
        wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        WebElement titleDetail = driver.findElement(By.xpath("//span[@id='__xmlview4--objFormTitle-inner']"));
        wait.until(ExpectedConditions.visibilityOf(titleDetail));
        driver.findElement(By.id(edit)).click();
        WebElement titleEdit = driver.findElement(By.xpath("//span[text()='Editar "+componente+"']"));
        wait.until(ExpectedConditions.visibilityOf(titleEdit));
        listForm = driver.findElements(By.className("sapMInputBaseInner"));
        WebElement disabled = listForm.get(2);
        while(disabled.isEnabled() == false){
            listForm = driver.findElements(By.className("sapMInputBaseInner"));
            disabled = listForm.get(2);
        }
        return listForm;
    }

}
