package Forms.SecurityManager;

import Helpers.BasicControl;
import Helpers.FormsControl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.util.List;

public class FormsUser {
    private WebDriver driver;
    private List<WebElement> listForm;
    private BasicControl basicControl;
    private LocalDate date;
    private String startDay = "";
    private String startDateIcon = "//span[contains(@id,'--buserValidityStartDate-icon')]";
    private String startDateCal = "";
    private String endDateIcon = "//span[contains(@id,'--buserValidityEndDate-icon')]";
    private String endDateCal = "";
    private String btnYear = "//button[contains(@id,'-buserValidityEndDate-cal--Head-B2')]";
    private int year;
    private String yearCal = "";
    private String arrowCorporation = "//span[contains(@id,'--buserCorporation-arrow')]";
    private String arrowOrgUnit = "//span[contains(@id,'--buserOrgUnit-arrow')]";
    private String arrowPosition = "//span[contains(@id,'--buserPosition-arrow')]";

    public FormsUser(WebDriver driver){
        this.driver = driver;
        this.basicControl = new BasicControl(driver);
        this.date = LocalDate.now();
        this.startDay =  String.valueOf(date.getDayOfMonth());
        this.startDateCal = "//div[contains(@id,'--buserValidityStartDate-RP-popover')]//span[text()='"+startDay+"']";
        this.endDateCal = "//div[contains(@id,'-buserValidityEndDate-RP-popover-cont')]//span[text()='"+startDay+"']";
        this.year = date.getYear();
        this.yearCal = "//div[contains(@id,'--buserValidityEndDate-cal')]//div[text()='"+(year+5)+"']";
    }

    public void formCreateUser(String user,String name,String lastName){
        LocalDate date = LocalDate.now();
        basicControl.btnAdd();
        listForm = FormsControl.controlNew(driver,"Usuario","User");
        listForm.get(0).click();
        listForm.get(0).sendKeys(user);
        listForm.get(1).click();
        listForm.get(1).sendKeys("1234");
        listForm.get(3).click();
        listForm.get(3).sendKeys(user+"@gmail.com");

        driver.findElement(By.xpath(startDateIcon)).click();
        driver.findElement(By.xpath(startDateCal)).click();

        driver.findElement(By.xpath(endDateIcon)).click();
        driver.findElement(By.xpath(btnYear)).click();
        driver.findElement(By.xpath(yearCal)).click();
        driver.findElement(By.xpath(endDateCal)).click();

        listForm.get(6).click();
        listForm.get(6).sendKeys(name);
        listForm.get(8).click();
        listForm.get(8).sendKeys(lastName);

        driver.findElement(By.xpath(arrowCorporation)).click();
        driver.findElement(By.xpath("//li[contains(@id,'--buserCorporation-8')]")).click();

        driver.findElement(By.xpath(arrowOrgUnit)).click();
        driver.findElement(By.xpath("//li[contains(@id,'--buserOrgUnit-0')]")).click();

        driver.findElement(By.xpath(arrowPosition)).click();
        driver.findElement(By.xpath("//li[contains(@id,'--buserPosition-0')]")).click();
    }



}
