package Forms.SecurityManager;

import Helpers.BasicControl;
import Helpers.FormsControl;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FormsRole {
    private WebDriver driver;
    private List<WebElement> listForm;
    private JavascriptExecutor js;
    private BasicControl basicControl;
    private String useAttributes = "//div[contains(@id,'--useAttributesRole-handle')]";
    private String isComposite = "//div[contains(@id,'--isCompositeRole-handle')]";
    private String app = "";


    public FormsRole(WebDriver driver){
        this.driver = driver;
        basicControl = new BasicControl(driver);
        this.js = (JavascriptExecutor) driver;
    }

    public void formCreateRole(String role) throws InterruptedException {
        basicControl.btnAdd();
        listForm = FormsControl.controlNew(driver,"Rol","Role");
        listForm.get(0).click();
        listForm.get(0).sendKeys(role);
        listForm.get(1).click();
        listForm.get(1).sendKeys(role);
        listForm.get(2).click();
        listForm.get(2).sendKeys(role);
        app = "InBUPLAT";
        String id = clickArrowApp(app);
        id = addNextRow(id);
        Thread.sleep(1000);
        driver.findElement(By.id(id)).click();
        WebElement checkBox = driver.findElements(By.xpath("//div[@class='sapMCbBg sapMCbHoverable sapMCbMark']")).get(0);
        js.executeScript("arguments[0].scrollIntoView();", checkBox);
        Thread.sleep(1000);
        checkBox.click();
        basicControl.btnSave();
    }

    public void formEditRole(String role) throws InterruptedException {
        listForm = FormsControl.controlEdit(driver,"Rol","Role");
        listForm.get(0).click();
        listForm.get(0).clear();
        listForm.get(0).sendKeys(role);
        listForm.get(1).click();
        listForm.get(1).clear();
        listForm.get(1).sendKeys(role);
        listForm.get(2).click();
        listForm.get(2).clear();
        listForm.get(2).sendKeys(role);
        basicControl.btnSave();
    }


    private String clickArrowApp(String app){
        String arrowApp = "//span[contains(text(),'Application: "+app+"')]/../..";
        WebElement arrow = driver.findElement(By.xpath(arrowApp));
        String idArrow = arrow.getAttribute("id");
        idArrow = idArrow.substring(0,idArrow.lastIndexOf("-")) + "-treeicon";
        driver.findElement(By.id(idArrow)).click();
        return  idArrow;
    }

    public String addNextRow(String id){
        int numId = Integer.parseInt(id.substring(id.lastIndexOf('w')+1,id.lastIndexOf("-"))) + 1;
        String newId = id.substring(0,id.lastIndexOf("w")+1) + numId + "-treeicon";
        return newId;
    }





}
