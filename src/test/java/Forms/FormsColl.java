package Forms;

import Helpers.BasicControl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FormsColl {
    private static List<WebElement> listForm;
    private static BasicControl basicControl;

    //Primer Proceso
    public static void primerProcesoSi(WebDriver driver){
        basicControl = new BasicControl(driver);
        listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(2).click();
        listForm.get(2).sendKeys("Solicitud Selenium");
        driver.findElement(By.xpath("//div[contains(@id,'-handle') and @class='sapMSwtHandle' and @data-sap-ui-swt = 'No']")).click();
        basicControl.submit();

    }

    public static void primerProcesoNo(WebDriver driver){
        basicControl = new BasicControl(driver);
        listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(2).click();
        listForm.get(2).sendKeys("Solicitud Selenium");
        basicControl.submit();
        basicControl.claim();
        listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(3).click();
        listForm.get(3).sendKeys("cpingo");
        basicControl.submit();
    }


}
