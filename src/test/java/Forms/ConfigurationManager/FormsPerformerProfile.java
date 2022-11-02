package Forms.ConfigurationManager;

import Helpers.BasicControl;
import Helpers.FormsControl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



import java.util.List;

public class FormsPerformerProfile {
    private WebDriver driver;
    private List<WebElement> listForm;
    private BasicControl basicControl;
    private String reusePP;
    private String currentUser;
    private String assignMethod;

    public FormsPerformerProfile(WebDriver driver){
        this.driver = driver;
        this.basicControl = new BasicControl(driver);
        this.reusePP = "//div[contains(@id,'--reusePerformer-handle')]";
        this.currentUser = "//div[contains(@id,'--assignCurrentUser-switch')]";
        this.assignMethod = "//span[contains(@id,'--assignmentMethod-arrow')]";
    }

    public void formCreatePerformer(String performer){
        basicControl.btnAdd();
        listForm = FormsControl.controlNew(driver,"Perfil de Ejecutor","Performer Profile");
        listForm.get(0).click();
        listForm.get(0).sendKeys(performer);
        listForm.get(1).click();
        listForm.get(1).sendKeys(performer);
        listForm.get(2).click();
        listForm.get(2).sendKeys("Descripción " + performer);
        driver.findElement(By.xpath(assignMethod)).click();
        driver.findElement(By.xpath("//div[@class='sapMSLITitleOnly'][normalize-space()='By Group']")).click();
        basicControl.btnSave();
    }

    public void formEditPerformer(String performer) throws InterruptedException {
        listForm = FormsControl.controlEdit(driver,"Perfil de Ejecutor","Performer Profile");
        listForm.get(0).clear();
        listForm.get(0).sendKeys(performer);
        listForm.get(1).clear();
        listForm.get(1).sendKeys(performer);
        listForm.get(2).clear();
        listForm.get(2).sendKeys("Descripción " + performer);
        driver.findElement(By.xpath(assignMethod)).click();
        driver.findElement(By.xpath("//div[@class='sapMSLITitleOnly'][normalize-space()='By Experience']")).click();
        basicControl.btnSave();
    }

}
