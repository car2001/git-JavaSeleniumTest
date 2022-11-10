package Forms.ReleaseManager;

import Helpers.BasicControl;
import Helpers.FormsControl;
import Helpers.SelectListItem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.util.List;

public class FormsRelease {

    private WebDriver driver;
    private List<WebElement> listForm;
    private BasicControl basicControl;
    private SelectListItem selectListItem;
    private String arrowStartDate = "//span[contains(@id,'--ReleaseStartDate-icon')]";
    private String arrowEndDate = "//span[contains(@id,'--ReleaseEndDate-icon')]";
    private LocalDate date = LocalDate.now();
    private String startDate = "//div[contains(@id,'ReleaseStartDate-cal--Month')]//span[text()='"+date.getDayOfMonth()+"']";
    private String btnYear = "//button[contains(@id,'ReleaseEndDate-cal--Head-B2')]";
    private String selectYear = "//div[contains(@id,'--ReleaseEndDate-cal--YP') and text()='"+(date.getYear()+5)+"']";
    private String endDate = "//div[contains(@id,'--ReleaseEndDate-cal--Month')]//span[text()='"+date.getDayOfMonth()+"']";
    private String stateRelease = "//span[contains(@id,'--selectReleaseState-arrow')]";

    public FormsRelease(WebDriver driver){
        this.driver = driver;
        this.basicControl = new BasicControl(driver);
        selectListItem = new SelectListItem(driver);
    }


    public void createRelease(String release) throws InterruptedException {
        listForm = FormsControl.controlNew(driver,"Liberación","Release");
        listForm.get(0).click();
        listForm.get(0).sendKeys(release);
        listForm.get(1).click();
        listForm.get(1).sendKeys(release);
        listForm.get(2).click();
        listForm.get(2).sendKeys(release);
        Thread.sleep(500);
        driver.findElement(By.xpath(arrowStartDate)).click();
        driver.findElement(By.xpath(startDate)).click();
        Thread.sleep(500);
        driver.findElement(By.xpath(arrowEndDate)).click();
        driver.findElement(By.xpath(btnYear)).click();
        driver.findElement(By.xpath(selectYear)).click();
        driver.findElement(By.xpath(endDate)).click();

        listForm.get(5).click();
        listForm.get(5).sendKeys("ID "+ release);

        driver.findElement(By.xpath(stateRelease)).click();
        selectListItem.SelectItemLi("Open");
        basicControl.btnSave();
    }

    public void editRelease(String release) throws InterruptedException {
        listForm.get(0).click();
        listForm.get(0).clear();
        listForm.get(0).sendKeys(release);
        listForm.get(1).click();
        listForm.get(1).clear();
        listForm.get(1).sendKeys(release);

        Thread.sleep(500);
        driver.findElement(By.xpath(arrowStartDate)).click();
        driver.findElement(By.xpath(startDate)).click();
        Thread.sleep(500);
        driver.findElement(By.xpath(arrowEndDate)).click();
        driver.findElement(By.xpath(btnYear)).click();
        driver.findElement(By.xpath(selectYear)).click();
        driver.findElement(By.xpath(endDate)).click();

        listForm.get(5).click();
        listForm.get(5).sendKeys("ID "+ release);

        basicControl.btnSave();
    }


}
