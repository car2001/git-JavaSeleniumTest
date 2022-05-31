package Forms;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

public class FormsRM {
    private static List<WebElement> listForm;
    private static String save = "__xmlview4--save-img";
    private static String edit= "__xmlview4--edit-img";
    private static String version = "__xmlview4--newVersion-inner";
    private static String versionHistory = "__xmlview4--versionHistory-img";

    //Project

    public static void formCreateProject(WebDriver driver,String proyecto){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.id(save)));
        List<WebElement> listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(2).sendKeys(proyecto);
        listForm.get(3).sendKeys(proyecto);
        listForm.get(4).sendKeys("Proyecto Creado en Selenium");
        driver.findElement(By.id("__xmlview4--ProjectStartDate-icon")).click();
        LocalDate date = LocalDate.now();
        driver.findElement(By.xpath("//span[normalize-space()='"+date.getDayOfMonth()+"']")).click();
        driver.findElement(By.id("__xmlview4--ProjectEndDate-icon")).click();
        driver.findElement(By.id("__xmlview4--ProjectEndDate-cal--Head-B2")).click();
        driver.findElement(By.xpath("//div[text()='"+(date.getYear()+5)+"']")).click();
        List<WebElement> Day = driver.findElements(By.xpath("//span[normalize-space()='"+date.getDayOfMonth()+"']"));
        Day.get(1).click();
        driver.findElement(By.id("__xmlview4--selectProjectState-label")).click();
        driver.findElement(By.xpath("//li[text()='Open']")).click();
        driver.findElement(By.id("__xmlview4--useInProcess-handle")).click();
        driver.findElement(By.id("__xmlview4--useInReleases-handle")).click();
        driver.findElement(By.id(save)).click();
    }

    public static void formEditProject(WebDriver driver,String proyecto){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.id(edit)));
        driver.findElement(By.id(edit)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(save)));
        List<WebElement> listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(2).clear();
        listForm.get(2).sendKeys(proyecto);
        listForm.get(3).clear();
        listForm.get(3).sendKeys(proyecto);
        listForm.get(4).clear();
        listForm.get(4).sendKeys("Creado en Selenium");
        driver.findElement(By.id("__xmlview4--selectProjectState-label")).click();
        driver.findElement(By.xpath("//li[text()='Closed']")).click();
        driver.findElement(By.id(save)).click();

    }

    public static void formCreateProjectWithoutRelease(WebDriver driver,String proyecto){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.id(save)));
        List<WebElement> listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(2).sendKeys(proyecto);
        listForm.get(3).sendKeys(proyecto);
        listForm.get(4).sendKeys(" Proyecto sin Release Creado en Selenium");
        driver.findElement(By.id("__xmlview4--ProjectStartDate-icon")).click();
        LocalDate date = LocalDate.now();
        driver.findElement(By.xpath("//span[normalize-space()='"+date.getDayOfMonth()+"']")).click();
        driver.findElement(By.id("__xmlview4--ProjectEndDate-icon")).click();
        driver.findElement(By.id("__xmlview4--ProjectEndDate-cal--Head-B2")).click();
        driver.findElement(By.xpath("//div[text()='"+(date.getYear()+5)+"']")).click();
        List<WebElement> endDay = driver.findElements(By.xpath("//span[normalize-space()='"+date.getDayOfMonth()+"']"));
        endDay.get(1).click();
        driver.findElement(By.id("__xmlview4--selectProjectState-label")).click();
        driver.findElement(By.xpath("//li[text()='Open']")).click();
        driver.findElement(By.id(save)).click();
    }

    //Release

    public static void formCreateRelease(WebDriver driver,String release) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(save)));
        List<WebElement> listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(2).sendKeys(release);
        listForm.get(3).sendKeys(release);
        listForm.get(4).sendKeys("Release Creado en Selenium");
        Thread.sleep(500);
        driver.findElement(By.id("__xmlview4--ReleaseStartDate-icon")).click();
        LocalDate date = LocalDate.now();
        List<WebElement> Day = driver.findElements(By.xpath("//span[normalize-space()='"+date.getDayOfMonth()+"']"));
        Day.get(2).click();
        driver.findElement(By.id("__xmlview4--ReleaseEndDate-icon")).click();
        driver.findElement(By.id("__xmlview4--ReleaseEndDate-cal--Head-B2")).click();
        driver.findElement(By.xpath("//div[text()='"+(date.getYear()+5)+"']")).click();
        Day = driver.findElements(By.xpath("//span[normalize-space()='"+date.getDayOfMonth()+"']"));
        Day.get(3).click();
        listForm.get(8).sendKeys("Release Selenium");
        driver.findElement(By.id("__xmlview4--selectReleaseState-label")).click();
        List<WebElement> stateRelease =  driver.findElements(By.xpath("//li[text()='Open']"));
        stateRelease.get(1).click();
        driver.findElement(By.id(save)).click();
    }

    public static void formEditRelease(WebDriver driver,String release){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.id(edit)));
        driver.findElement(By.id(edit)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(save)));
        wait.until(ExpectedConditions.elementToBeClickable(By.id(save)));
        List<WebElement> listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(2).clear();
        listForm.get(2).sendKeys(release);
        listForm.get(3).clear();
        listForm.get(3).sendKeys(release);
        driver.findElement(By.id("__xmlview4--ReleaseStartDate-icon")).click();
        LocalDate date = LocalDate.now();
        List<WebElement> Day = driver.findElements(By.xpath("//span[normalize-space()='"+date.getDayOfMonth()+"']"));
        Day.get(0).click();
        driver.findElement(By.id("__xmlview4--ReleaseEndDate-icon")).click();
        driver.findElement(By.id("__xmlview4--ReleaseEndDate-cal--Head-B2")).click();
        driver.findElement(By.xpath("//div[text()='"+(date.getYear()+5)+"']")).click();
        Day = driver.findElements(By.xpath("//span[normalize-space()='"+date.getDayOfMonth()+"']"));
        Day.get(1).click();
        listForm.get(8).clear();
        listForm.get(8).sendKeys("Release Selenium");
        driver.findElement(By.id(save)).click();
    }

    // Change Container

    public static void formCreateChangeContainer(WebDriver driver,String ChangeContainer){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(save)));
        List<WebElement> listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(2).sendKeys(ChangeContainer);
        listForm.get(3).sendKeys(ChangeContainer);
        listForm.get(4).sendKeys( ChangeContainer);
        driver.findElement(By.id("__xmlview4--selectCCProject-arrow")).click();
        driver.findElement(By.xpath("//li[text()='Proyecto Release Selenium']")).click();
        driver.findElement(By.id("__xmlview4--selectCCRelease-arrow")).click();
        driver.findElement(By.xpath("//li[text()='Release Selenium Editado']")).click();
    }

}
