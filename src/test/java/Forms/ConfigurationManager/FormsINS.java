package Forms.ConfigurationManager;

import Helpers.BasicControl;
import Helpers.FormsControl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FormsINS {
    private static List<WebElement> listForm;
    private static BasicControl basicControl;
    private static String addOptions = "//span[contains(@id,'--addItem-img')]";
    private static String selectOptions = "//span[@class='sapUiIcon sapUiIconMirrorInRTL sapUiIconPointer sapMInputBaseIcon' and (@aria-label ='Opciones de selección' or @aria-label='Select Options')]";

    public static void formCreateINS(WebDriver driver, String INS , String separador , String fixedValue, String counter){
        basicControl = new BasicControl(driver);
        basicControl.btnAdd();
        listForm = FormsControl.controlNew(driver,"Nuevo esquema de numeración de instancias","Instance Numbering Schema");
        listForm.get(2).click();
        listForm.get(2).sendKeys(INS);
        listForm.get(3).click();
        listForm.get(3).sendKeys(INS);
        listForm.get(4).click();
        listForm.get(4).sendKeys(INS);
        listForm.get(6).click();
        listForm.get(6).sendKeys(separador);
        //Agregamos Component List
        driver.findElement(By.xpath(addOptions)).click();
        driver.findElement(By.xpath(addOptions)).click();
        //Seleccionamos Opciones
        List<WebElement> cboComponentes =  driver.findElements(By.xpath(selectOptions));
        cboComponentes.get(0).click();
        driver.findElement(By.xpath("//div[text()='Fixed Value' and @class ='sapMSLITitleOnly']")).click();
        cboComponentes.get(1).click();
        driver.findElements(By.xpath("//div[text()='Counter' and @class ='sapMSLITitleOnly']")).get(1).click();
        listForm= driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(8).click();
        listForm.get(8).sendKeys(fixedValue);
        driver.findElements(By.xpath(selectOptions)).get(2).click();
        driver.findElement(By.xpath("//div[text()='"+counter+"']")).click();
        basicControl.btnSave();
    }
}
