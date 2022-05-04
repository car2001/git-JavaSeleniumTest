package Forms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class FormsCM {
    private static List<WebElement> listForm;

    public static void formCreateSLA(WebDriver driver, String SLA) throws InterruptedException {
        listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(2).sendKeys(SLA);
        listForm.get(3).sendKeys(SLA);
        listForm.get(4).sendKeys("Es un "+ SLA);
        driver.findElement(By.id("__xmlview5--slaType-arrow")).click();
        driver.findElement(By.xpath("//div[@class='sapMSLITitleOnly'][normalize-space()='Fixed Value']")).click();
        driver.findElement(By.id("__xmlview5--useNumberDays-switch")).click();
        driver.findElement(By.id("__xmlview5--save-img")).click();
        Thread.sleep(500);
    }

    public static void formEditSLA(WebDriver driver, String SLA) throws InterruptedException {
        driver.findElement(By.id("__xmlview5--edit-img")).click();
        Thread.sleep(200);
        listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(2).clear();
        listForm.get(2).sendKeys(SLA);
        listForm.get(3).clear();
        listForm.get(3).sendKeys(SLA);
        listForm.get(4).clear();
        listForm.get(4).sendKeys("Descripción "+ SLA);
        driver.findElement(By.id("__xmlview5--slaType-arrow")).click();
        driver.findElement(By.xpath("//div[@class='sapMSLITitleOnly'][normalize-space()='Depends on a Condition']")).click();
        driver.findElement(By.id("__xmlview5--save-img")).click();
        Thread.sleep(500);
    }

}