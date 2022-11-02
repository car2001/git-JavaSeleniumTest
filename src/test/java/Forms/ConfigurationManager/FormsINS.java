package Forms.ConfigurationManager;

import Helpers.BasicControl;
import Helpers.FormsControl;
import Helpers.SelectListItem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FormsINS {
    private WebDriver driver;
    private List<WebElement> listForm;
    private BasicControl basicControl;
    private SelectListItem selectListItem;
    private String addOptions;
    private String selectOptions;
    private String inputComponents = "//div[contains(@id,'__xmlview') and @class='sapUiView sapUiXMLView sapMNavItem']//div[contains(@id,'--operationsTable')]//*[@class='sapMInputBaseInner' or @class='sapMInputBaseInner sapMTextAreaInner sapMTextAreaGrow']";


    public FormsINS(WebDriver driver){
        this.driver = driver;
        this.basicControl = new BasicControl(driver);
        this.selectListItem = new SelectListItem(driver);
        this.addOptions = "//span[contains(@id,'--addItem-img')]";
        this.selectOptions = "//span[@class='sapUiIcon sapUiIconMirrorInRTL sapUiIconPointer sapMInputBaseIcon' and (@aria-label ='Opciones de selección' or @aria-label='Select Options')]";
    }

    public void formCreateINS(String INS , String separador , String fixedValue, String counter) throws InterruptedException {
        basicControl.btnAdd();
        listForm = FormsControl.controlNew(driver,"Esquema de Numeración de Instancias","Instance Numbering Schema");
        listForm.get(0).click();
        listForm.get(0).sendKeys(INS);
        listForm.get(1).click();
        listForm.get(1).sendKeys(INS);
        listForm.get(2).click();
        listForm.get(2).sendKeys(INS);
        listForm.get(3).click();
        listForm.get(3).sendKeys(separador);
        //Agregamos Component List
        driver.findElement(By.xpath(addOptions)).click();
        driver.findElement(By.xpath(addOptions)).click();
        //Seleccionamos Opciones
        List<WebElement> cboComponentes =  driver.findElements(By.xpath(selectOptions));
        cboComponentes.get(0).click();
        selectListItem.SelectItemDiv("Fixed Value");
        cboComponentes.get(1).click();
        selectListItem.SelectItemDiv("Counter");
        listForm = driver.findElements(By.xpath(inputComponents));
        listForm.get(0).click();
        listForm.get(0).sendKeys(fixedValue);
        driver.findElements(By.xpath(selectOptions)).get(2).click();
        selectListItem.SelectItemDiv(counter);
        basicControl.btnSave();
    }

    public void formEditINS(String INS , String separador) throws InterruptedException {
        listForm = FormsControl.controlEdit(driver,"Esquema de Numeración de Instancias","Instance Numbering Schema");
        listForm.get(0).click();
        listForm.get(0).clear();
        listForm.get(0).sendKeys(INS);
        listForm.get(1).click();
        listForm.get(1).clear();
        listForm.get(1).sendKeys(INS);
        listForm.get(2).click();
        listForm.get(2).clear();
        listForm.get(2).sendKeys(INS);
        listForm.get(3).click();
        listForm.get(3).clear();
        listForm.get(3).sendKeys(separador);
        //Agregamos Component List
        basicControl.btnSave();
    }


}
