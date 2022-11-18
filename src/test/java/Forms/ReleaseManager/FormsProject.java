package Forms.ReleaseManager;

import Helpers.BasicControl;
import Helpers.FormsControl;
import Helpers.SelectListItem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.util.List;

public class FormsProject {

    private WebDriver driver;
    private List<WebElement> listForm;
    private BasicControl basicControl;
    private SelectListItem selectListItem;
    private String arrowStartDate = "//span[contains(@id,'--ProjectStartDate-icon')]";
    private String arrowEndDate = "//span[contains(@id,'--ProjectEndDate-icon')]";
    private LocalDate date = LocalDate.now();
    private String startDate = "//div[contains(@id,'ProjectStartDate-cal--Month')]//span[text()='" + date.getDayOfMonth() + "']";
    private String btnYear = "//button[contains(@id,'--ProjectEndDate-cal--Head-B2')]";
    private String selectYear = "//div[contains(@id,'--ProjectEndDate-cal') and text()='" + (date.getYear() + 5) + "']";
    private String endDate = "//div[contains(@id,'ProjectEndDate-cal--Month')]//span[text()='" + date.getDayOfMonth() + "']";
    private String stateProject = "//span[contains(@id,'--selectProjectState-arrow')]";
    private String useProcess = "//div[contains(@id,'--useInProcess-handle')]";
    private String useReleases = "//div[contains(@id,'--useInReleases-handle')]";

    public FormsProject(WebDriver driver) {
        this.driver = driver;
        this.basicControl = new BasicControl(driver);
        this.selectListItem = new SelectListItem(driver);
    }

    public void createProject(String proyecto) throws InterruptedException {
        listForm = FormsControl.controlNew(driver, "Proyecto", "Project");
        Thread.sleep(500);
        listForm.get(0).click();
        listForm.get(0).sendKeys(proyecto);
        listForm.get(1).click();
        listForm.get(1).sendKeys(proyecto);
        listForm.get(2).click();
        listForm.get(2).sendKeys(proyecto);
        Thread.sleep(500);

        driver.findElement(By.xpath(arrowStartDate)).click(); //  click en fecha de inico
        driver.findElement(By.xpath(startDate)).click();
        driver.findElement(By.xpath(arrowEndDate)).click();   //  click en arrow fecha final
        driver.findElement(By.xpath(btnYear)).click(); // click en el año actual
        driver.findElement(By.xpath(selectYear)).click(); // seleccionamos +5 años
        driver.findElement(By.xpath(endDate)).click();  //Seleccionamos la fecha final

        driver.findElement(By.xpath(stateProject)).click();
        selectListItem.SelectItemLi("Open");

        driver.findElement(By.xpath(useProcess)).click();
        driver.findElement(By.xpath(useReleases)).click();
        basicControl.btnSave();
    }


    public void editProject(String proyecto) throws InterruptedException {
        listForm = FormsControl.controlEdit(driver, "Proyecto", "Project");
        Thread.sleep(500);
        listForm.get(0).click();
        listForm.get(0).clear();
        listForm.get(0).sendKeys(proyecto);
        listForm.get(1).click();
        listForm.get(1).clear();
        listForm.get(1).sendKeys(proyecto);
        listForm.get(2).click();
        listForm.get(2).clear();
        listForm.get(2).sendKeys(proyecto);

        driver.findElement(By.xpath(stateProject)).click();
        selectListItem.SelectItemLi("Closed");

        basicControl.btnSave();

    }


    public void createProjectWithoutRelease(String proyecto) throws InterruptedException {
        listForm = FormsControl.controlNew(driver, "Proyecto", "Project");
        listForm.get(0).click();
        listForm.get(0).sendKeys(proyecto);
        listForm.get(1).click();
        listForm.get(1).sendKeys(proyecto);
        listForm.get(2).click();
        listForm.get(2).sendKeys(proyecto);
        Thread.sleep(500);

        driver.findElement(By.xpath(arrowStartDate)).click(); //  click en fecha de inico
        driver.findElement(By.xpath(startDate)).click();
        driver.findElement(By.xpath(arrowEndDate)).click();   //  click en arrow fecha final
        driver.findElement(By.xpath(btnYear)).click(); // click en el año actual
        driver.findElement(By.xpath(selectYear)).click(); // seleccionamos +5 años
        driver.findElement(By.xpath(endDate)).click();  //Seleccionamos la fecha final

        driver.findElement(By.xpath(stateProject)).click();
        selectListItem.SelectItemLi("Open");

        basicControl.btnSave();
    }

}
