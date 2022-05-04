package Forms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FormsOSM {

    private static List<WebElement> listForm;
    private static String save = "__xmlview4--save-img";
    private static String edit= "__xmlview4--edit-img";

    //Organizational Unit
    public static void formCreateOrganization(WebDriver driver, String organización) throws InterruptedException{
        listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(2).sendKeys(organización);
        listForm.get(3).sendKeys(organización);
        listForm.get(4).sendKeys("Creado por" + organización);
        driver.findElement(By.id(save)).click();
        Thread.sleep(500);
    }

    public static void formEditOrganization(WebDriver driver, String organización) throws InterruptedException{
        listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(2).clear();
        listForm.get(2).sendKeys(organización);
        listForm.get(3).clear();
        listForm.get(3).sendKeys(organización);
        listForm.get(4).clear();
        listForm.get(4).sendKeys(organización);
        driver.findElement(By.id(save)).click();
        Thread.sleep(500);
    }


    // LOCATION
    public static void formCreateLocation(WebDriver driver, String location) throws InterruptedException {
        listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(2).sendKeys(location);
        listForm.get(3).sendKeys(location);
        listForm.get(4).sendKeys(location);
        driver.findElement(By.id(save)).click();
        Thread.sleep(500);
    }

    public static void formEditLocation(WebDriver driver, String location) throws InterruptedException {
        //Editando Formulario
        driver.findElement(By.id(edit)).click();
        Thread.sleep(300);
        listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(2).clear();
        listForm.get(2).sendKeys(location);
        listForm.get(3).clear();
        listForm.get(3).sendKeys(location);
        listForm.get(4).clear();
        listForm.get(4).sendKeys(location);
        driver.findElement(By.id(save)).click();
        Thread.sleep(500);
    }


    //POSITION
    public static void formCreatePosition(WebDriver driver, String position) throws InterruptedException {
        //Llenando Formulario
        listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(2).sendKeys(position);
        listForm.get(3).sendKeys(position);
        listForm.get(4).sendKeys(position);
        driver.findElement(By.id(save)).click();
        Thread.sleep(500);
    }
    public static void formEditPosition(WebDriver driver, String position) throws InterruptedException {
        driver.findElement(By.id(edit)).click();
        Thread.sleep(500);
        listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(2).clear();
        listForm.get(2).sendKeys(position);
        listForm.get(3).clear();
        listForm.get(3).sendKeys(position);
        listForm.get(4).clear();
        listForm.get(4).sendKeys(position);
        driver.findElement(By.id(save)).click();
        Thread.sleep(500);
    }

}
