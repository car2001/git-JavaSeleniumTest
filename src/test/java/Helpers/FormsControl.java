package Helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.MalformedParametersException;
import java.time.Duration;
import java.util.List;

public class FormsControl {

    private static WebDriverWait wait;
    private static List<WebElement> listForm;
    private static BasicControl basicControl;

    public static List<WebElement> controlNewWithoutFocus( WebDriver driver,String componente){
        wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        WebElement titulo = driver.findElement(By.xpath("//span[contains(@id,'--objFormTitle-inner') and contains(text(),'"+componente+"')]"));
        wait.until(ExpectedConditions.visibilityOf(titulo));
        listForm = driver.findElements(By.className("sapMInputBaseInner"));
        boolean focus = false;
        while(focus == false){
            listForm.get(3).click();
            if(listForm.get(3).equals(driver.switchTo().activeElement())){
                System.out.println("Element is focused");
                focus = true;
            }
            else {
                listForm = driver.findElements(By.className("sapMInputBaseInner"));
                System.out.println("Element is no focused");
                focus = false;
            }

        }
        return listForm;
    }

    public static List<WebElement> controlNew( WebDriver driver,String componente){
        wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        WebElement titulo = driver.findElement(By.xpath("//span[contains(@id,'--objFormTitle-inner') and contains(text(),'"+componente+"')]"));
        wait.until(ExpectedConditions.visibilityOf(titulo));
        listForm = driver.findElements(By.className("sapMInputBaseInner"));
        boolean focus = false;
        while(focus == false){
            if(listForm.get(2).equals(driver.switchTo().activeElement())){
                System.out.println("Element is focused");
                focus = true;
            }
            else {
                listForm = driver.findElements(By.className("sapMInputBaseInner"));
                System.out.println("Element is no focused");
                focus = false;
            }

        }
        return listForm;
    }

    public static List<WebElement> controlEdit(WebDriver driver,String componente) throws InterruptedException{
        wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        WebElement titleDetail = driver.findElement(By.xpath("//span[contains(@id,'--objFormTitle-inner') and contains(text(),'"+componente+"')]"));
        wait.until(ExpectedConditions.visibilityOf(titleDetail));
        basicControl = new BasicControl(driver);
        basicControl.btnEdit();
        WebElement titleEdit = driver.findElement(By.xpath("//span[text()='Editar "+componente+"']"));
        wait.until(ExpectedConditions.visibilityOf(titleEdit));
        listForm = driver.findElements(By.className("sapMInputBaseInner"));
        WebElement disabled = listForm.get(2);
        while(disabled.isEnabled() == false){
            listForm = driver.findElements(By.className("sapMInputBaseInner"));
            disabled = listForm.get(2);
        }
        boolean focus = false;
        while(focus == false){
            if(listForm.get(2).equals(driver.switchTo().activeElement())){
                System.out.println("Element is focused");
                focus = true;
            }
            else {
                System.out.println("Element is not focused");
                listForm = driver.findElements(By.className("sapMInputBaseInner"));
                focus = false;
            }
        }
        return listForm;
    }

    public static void controlTitle(WebDriver driver,String componente ){
        wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        WebElement titleDetail = driver.findElement(By.xpath("//span[contains(@id,'--objFormTitle-inner') and contains(text(),'"+componente+"')]"));
        wait.until(ExpectedConditions.visibilityOf(titleDetail));
    }

    public static void controlDelete(WebDriver driver, Actions action , WebElement elemento,String componente){
        action.contextClick(elemento).perform();
        driver.findElement(By.xpath("//div[normalize-space()='Delete "+componente+"']")).click();
        driver.findElement(By.xpath("//bdi[normalize-space()='Sí']")).click();
        driver.findElement(By.xpath("//bdi[normalize-space()='OK']")).click();
    }

    public static void controlDelete(WebDriver driver, String nameComponent){
        driver.findElement(By.xpath("//div[text()='"+nameComponent+"']/parent::div/parent::div/following-sibling::button")).click();
        driver.findElement(By.xpath("//bdi[normalize-space()='Sí']")).click();
        driver.findElement(By.xpath("//bdi[normalize-space()='OK']")).click();
    }

    public static void controlLook(WebDriver driver, String edit, JavascriptExecutor js) throws InterruptedException {
        wait = new WebDriverWait(driver, Duration.ofMillis(1000));
        Thread.sleep(500);
        js.executeScript("async function prueba(){let x =  document.querySelector('.sapMMsgStripMessage');x.textContent }");
        try {
            if(js.executeScript("let msg = document.querySelector('.sapMMsgStripMessage'); return(msg.textContent);").toString() != null){
                String message = driver.findElement(By.className("sapMMsgStripMessage")).getAttribute("textContent");
                if(message.contains("Este objeto está bloqueado por")){
                    driver.findElement(By.xpath("//button[@title='Cerrar']")).click();
                    driver.findElement(By.xpath("//span[@title='Opciones de usuario']")).click();
                    driver.findElement(By.xpath("//bdi[text()='Mis bloqueos']")).click();
                    driver.findElement(By.xpath("//div[@title='Seleccionar todo']")).click();
                    driver.findElement(By.xpath("//button[@title='Borrar']")).click();
                    driver.findElement(By.xpath("//bdi[normalize-space()='Sí']")).click();
                    driver.findElement(By.xpath("//bdi[normalize-space()='OK']")).click();
                    WebElement popLokk  = driver.findElement(By.xpath("//span[text()='Mis bloqueos']"));
                    driver.findElement(By.xpath("//bdi[text()='Cerrar']")).click();
                    wait.until(ExpectedConditions.invisibilityOf(popLokk));
                    driver.findElement(By.xpath(edit)).click();
                    Thread.sleep(1000);
                }
            }else{
                System.out.println("Sigue nada más" + "false");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
