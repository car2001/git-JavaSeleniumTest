package Forms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class FormsCM {
    private static List<WebElement> listForm;
    private static String save = "__xmlview5--save-img";
    private static String edit= "__xmlview5--edit-img";
    private static String version = "__xmlview5--newVersion-inner";
    private static String versionHistory = "__xmlview5--versionHistory-img";

    //SLA
    public static void formCreateSLA(WebDriver driver, String SLA){
        driver.findElement(By.xpath("//div[@class='sapMBarRight sapMBarContainer']/button[@title='Añadir']")).click();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.id(save)));
        listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(2).sendKeys(SLA);
        listForm.get(3).sendKeys(SLA);
        listForm.get(4).sendKeys("Es un "+ SLA);
        driver.findElement(By.id("__xmlview5--slaType-arrow")).click();
        driver.findElement(By.xpath("//div[@class='sapMSLITitleOnly'][normalize-space()='Fixed Value']")).click();
        driver.findElement(By.id("__xmlview5--useNumberDays-switch")).click();
        driver.findElement(By.id(save)).click();
    }

    public static void formEditSLA(WebDriver driver, String SLA){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.id(edit)));
        driver.findElement(By.id(edit)).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id(save)));
        listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(2).clear();
        listForm.get(2).sendKeys(SLA);
        listForm.get(3).clear();
        listForm.get(3).sendKeys(SLA);
        listForm.get(4).clear();
        listForm.get(4).sendKeys("Descripción "+ SLA);
        driver.findElement(By.id("__xmlview5--slaType-arrow")).click();
        driver.findElement(By.xpath("//div[@class='sapMSLITitleOnly'][normalize-space()='Depends on a Condition']")).click();
        driver.findElement(By.id(save)).click();
    }

    public static void MayorVersionSLA(WebDriver driver,String mayor){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.id(version)));
        driver.findElement(By.id(version)).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//bdi[text()='Major Version']")));
        driver.findElement(By.xpath("//bdi[text()='Major Version']")).click();
        driver.findElement(By.xpath("//bdi[text()='Create Version']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id(save)));
        listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(2).clear();
        listForm.get(2).sendKeys(mayor);
        listForm.get(3).clear();
        listForm.get(3).sendKeys(mayor);
        listForm.get(4).clear();
        listForm.get(4).sendKeys("Descripción "+ mayor);
        listForm.get(6).sendKeys("Version Mayor");
        driver.findElement(By.id("__xmlview5--slaType-arrow")).click();
        driver.findElement(By.xpath("//div[@class='sapMSLITitleOnly'][normalize-space()='Fixed Value']")).click();
        driver.findElement(By.id(save)).click();
    }

    public static void MenorVersionSLA(WebDriver driver,String menor){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.id(version)));
        driver.findElement(By.id(version)).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//bdi[text()='Minor Version']")));
        driver.findElement(By.xpath("//bdi[text()='Minor Version']")).click();
        driver.findElement(By.xpath("//bdi[text()='Create Version']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id(save)));
        listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(2).clear();
        listForm.get(2).sendKeys(menor);
        listForm.get(3).clear();
        listForm.get(3).sendKeys(menor);
        listForm.get(4).clear();
        listForm.get(4).sendKeys("Descripción "+ menor);
        listForm.get(6).sendKeys("Version Menor");
        driver.findElement(By.id("__xmlview5--slaType-arrow")).click();
        driver.findElement(By.xpath("//div[@class='sapMSLITitleOnly'][normalize-space()='Fixed Value']")).click();
        driver.findElement(By.id(save)).click();
    }

    public static void restoreVersion_SLA(WebDriver driver,String restore){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.id(versionHistory)));
        driver.findElement(By.id(versionHistory)).click();
        String btn_xpath = "//span[@class='sapMBtnInner sapMBtnHoverable sapMFocusable sapMBtnIconFirst sapMBtnDefault']/parent::button[@class='sapMBtnBase sapMBtn']";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(btn_xpath)));
        List<WebElement> btn_restore = driver.findElements(By.xpath(btn_xpath));
        btn_restore.get(1).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//bdi[text()='Major Version']")));
        driver.findElement(By.xpath("//bdi[text()='Minor Version']")).click();
        driver.findElement(By.xpath("//bdi[text()='Create Version']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id(save)));
        listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(2).clear();
        listForm.get(2).sendKeys(restore);
        listForm.get(3).clear();
        listForm.get(3).sendKeys(restore);
        listForm.get(4).clear();
        listForm.get(4).sendKeys("Descripción "+ restore);
        listForm.get(6).sendKeys("Version restore");
        driver.findElement(By.id(save)).click();
    }

    // Form UI

    public static void formCreateFormUI(WebDriver driver, String UI){
        driver.findElement(By.xpath("//div[@class='sapMBarRight sapMBarContainer']/button[@title='Añadir']")).click();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.id(save)));
        listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(2).sendKeys(UI);
        listForm.get(3).sendKeys(UI);
        listForm.get(4).sendKeys(UI);
        driver.findElement(By.id("__xmlview5--comments-switch")).click();
        driver.findElement(By.id("__xmlview5--attachments-switch")).click();
        driver.findElement(By.id("__xmlview5--instructions-switch")).click();
        driver.findElement(By.id(save)).click();
    }

    public static void formEditFormUI(WebDriver driver, String UI){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.id(edit)));
        driver.findElement(By.id(edit)).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id(save)));
        listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(2).clear();
        listForm.get(2).sendKeys(UI);
        listForm.get(3).clear();
        listForm.get(3).sendKeys(UI);
        listForm.get(4).clear();
        listForm.get(4).sendKeys(UI);
        driver.findElement(By.id(save)).click();
    }

    public static void MayorVersionFormUI(WebDriver driver,String mayor){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.id(version)));
        driver.findElement(By.id(version)).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//bdi[text()='Major Version']")));
        driver.findElement(By.xpath("//bdi[text()='Major Version']")).click();
        driver.findElement(By.xpath("//bdi[text()='Create Version']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id(save)));
        listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(2).clear();
        listForm.get(2).sendKeys(mayor);
        listForm.get(3).clear();
        listForm.get(3).sendKeys(mayor);
        listForm.get(4).clear();
        listForm.get(4).sendKeys("Descripción "+ mayor);
        listForm.get(6).sendKeys("Version Mayor");
        driver.findElement(By.id(save)).click();
    }

    public static void MenorVersionFormUI(WebDriver driver,String menor){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.id(version)));
        driver.findElement(By.id(version)).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//bdi[text()='Minor Version']")));
        driver.findElement(By.xpath("//bdi[text()='Minor Version']")).click();
        driver.findElement(By.xpath("//bdi[text()='Create Version']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id(save)));
        listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(2).clear();
        listForm.get(2).sendKeys(menor);
        listForm.get(3).clear();
        listForm.get(3).sendKeys(menor);
        listForm.get(4).clear();
        listForm.get(4).sendKeys("Descripción "+ menor);
        listForm.get(6).sendKeys("Version Menor");
        driver.findElement(By.id(save)).click();
    }

    public static void restoreVersion_FormUI(WebDriver driver,String restore){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.id(versionHistory)));
        driver.findElement(By.id(versionHistory)).click();
        String btn_xpath = "//span[@class='sapMBtnInner sapMBtnHoverable sapMFocusable sapMBtnIconFirst sapMBtnDefault']/parent::button[@class='sapMBtnBase sapMBtn']";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(btn_xpath)));
        List<WebElement> btn_restore = driver.findElements(By.xpath(btn_xpath));
        btn_restore.get(1).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//bdi[text()='Minor Version']")));
        driver.findElement(By.xpath("//bdi[text()='Minor Version']")).click();
        driver.findElement(By.xpath("//bdi[text()='Create Version']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id(save)));
        listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(2).clear();
        listForm.get(2).sendKeys(restore);
        listForm.get(3).clear();
        listForm.get(3).sendKeys(restore);
        listForm.get(4).clear();
        listForm.get(4).sendKeys("Descripción "+ restore);
        listForm.get(6).sendKeys("Version restore");
        driver.findElement(By.id(save)).click();
    }

    // Performer Profile

    public static void formCreatePerformer(WebDriver driver,String performer){
        driver.findElement(By.xpath("//div[@class='sapMBarRight sapMBarContainer']/button[@title='Añadir']")).click();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.id(save)));
        listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(2).sendKeys(performer);
        listForm.get(3).sendKeys(performer);
        listForm.get(4).sendKeys("Descripción " + performer);
        driver.findElement(By.id("__xmlview5--reusePerformer-handle")).click();
        driver.findElement(By.id("__xmlview5--assignmentMethod-arrow")).click();
        driver.findElement(By.xpath("//div[@class='sapMSLITitleOnly'][normalize-space()='By Group']")).click();
        driver.findElement(By.id(save)).click();
    }

    public static void formEditPerformer(WebDriver driver,String performer){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.id(edit)));
        driver.findElement(By.id(edit)).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id(save)));
        listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(2).clear();
        listForm.get(2).sendKeys(performer);
        listForm.get(3).clear();
        listForm.get(3).sendKeys(performer);
        listForm.get(4).clear();
        listForm.get(4).sendKeys("Descripción " + performer);
        driver.findElement(By.id("__xmlview5--assignmentMethod-arrow")).click();
        driver.findElement(By.xpath("//div[@class='sapMSLITitleOnly'][normalize-space()='By Experience']")).click();
        driver.findElement(By.id(save)).click();
    }

    public static void MayorVersionPerformer(WebDriver driver, String mayor){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.id(version)));
        driver.findElement(By.id(version)).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//bdi[text()='Major Version']")));
        driver.findElement(By.xpath("//bdi[text()='Major Version']")).click();
        driver.findElement(By.xpath("//bdi[text()='Create Version']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id(save)));
        listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(2).clear();
        listForm.get(2).sendKeys(mayor);
        listForm.get(3).clear();
        listForm.get(3).sendKeys(mayor);
        listForm.get(4).clear();
        listForm.get(4).sendKeys("Descripción " + mayor);
        listForm.get(6).sendKeys("Version Mayor");
        driver.findElement(By.id("__xmlview5--assignmentMethod-arrow")).click();
        driver.findElement(By.xpath("//div[@class='sapMSLITitleOnly'][normalize-space()='By Group']")).click();
        driver.findElement(By.id(save)).click();
    }

    public static void MenorVersionPerformer(WebDriver driver, String menor){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.id(version)));
        driver.findElement(By.id(version)).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//bdi[text()='Minor Version']")));
        driver.findElement(By.xpath("//bdi[text()='Minor Version']")).click();
        driver.findElement(By.xpath("//bdi[text()='Create Version']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id(save)));
        listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(2).clear();
        listForm.get(2).sendKeys(menor);
        listForm.get(3).clear();
        listForm.get(3).sendKeys(menor);
        listForm.get(4).clear();
        listForm.get(4).sendKeys("Descripción " + menor);
        listForm.get(6).sendKeys("Version Menor");
        driver.findElement(By.id(save)).click();
    }

    public static void restoreVersion_PP(WebDriver driver,String restore){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.id(versionHistory)));
        driver.findElement(By.id(versionHistory)).click();
        String btn_xpath = "//span[@class='sapMBtnInner sapMBtnHoverable sapMFocusable sapMBtnIconFirst sapMBtnDefault']/parent::button[@class='sapMBtnBase sapMBtn']";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(btn_xpath)));
        List<WebElement> btn_restore = driver.findElements(By.xpath(btn_xpath));
        btn_restore.get(1).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//bdi[text()='Minor Version']")));
        driver.findElement(By.xpath("//bdi[text()='Minor Version']")).click();
        driver.findElement(By.xpath("//bdi[text()='Create Version']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id(save)));
        listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(2).clear();
        listForm.get(2).sendKeys(restore);
        listForm.get(3).clear();
        listForm.get(3).sendKeys(restore);
        listForm.get(4).clear();
        listForm.get(4).sendKeys("Descripción "+ restore);
        listForm.get(6).sendKeys("Version restore");
        driver.findElement(By.id(save)).click();
    }

    // Notification Profile

    public static void formCreateNotification(WebDriver driver,String notification){
        driver.findElement(By.xpath("//div[@class='sapMBarRight sapMBarContainer']/button[@title='Añadir']")).click();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.id(save)));
        listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(2).sendKeys(notification);
        listForm.get(3).sendKeys(notification);
        listForm.get(4).sendKeys("Descripción " + notification);
        driver.findElement(By.id("__xmlview5--addItem-img")).click();
        driver.findElement(By.className("sapMSltArrow")).click();
        driver.findElement(By.xpath("//li[text()='Allocate' and @class ='sapMSelectListItemBase sapMSelectListItem sapMSelectListItemBaseHoverable sapMSelectListItemBaseSelected']")).click();
        driver.findElement(By.id(save)).click();
    }

    public static void formEditNotification(WebDriver driver,String notification){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.id(edit)));
        driver.findElement(By.id(edit)).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id(save)));
        listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(2).clear();
        listForm.get(2).sendKeys(notification);
        listForm.get(3).clear();
        listForm.get(3).sendKeys(notification);
        listForm.get(4).clear();
        listForm.get(4).sendKeys("Descripción " + notification);
        driver.findElement(By.id(save)).click();
    }

    public static void MayorVersionNotification(WebDriver driver, String mayor){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.id(version)));
        driver.findElement(By.id(version)).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//bdi[text()='Major Version']")));
        driver.findElement(By.xpath("//bdi[text()='Major Version']")).click();
        driver.findElement(By.xpath("//bdi[text()='Create Version']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id(save)));
        listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(2).clear();
        listForm.get(2).sendKeys(mayor);
        listForm.get(3).clear();
        listForm.get(3).sendKeys(mayor);
        listForm.get(4).clear();
        listForm.get(4).sendKeys("Descripción " + mayor);
        listForm.get(6).sendKeys("Version Mayor");
        driver.findElement(By.className("sapMSltArrow")).click();
        driver.findElement(By.xpath("//li[text()='Abort' and @class ='sapMSelectListItemBase sapMSelectListItem sapMSelectListItemBaseHoverable']")).click();
        driver.findElement(By.id(save)).click();
    }

    public static void MenorVersionNotification(WebDriver driver, String menor){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.id(version)));
        driver.findElement(By.id(version)).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//bdi[text()='Minor Version']")));
        driver.findElement(By.xpath("//bdi[text()='Minor Version']")).click();
        driver.findElement(By.xpath("//bdi[text()='Create Version']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id(save)));
        listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(2).clear();
        listForm.get(2).sendKeys(menor);
        listForm.get(3).clear();
        listForm.get(3).sendKeys(menor);
        listForm.get(4).clear();
        listForm.get(4).sendKeys("Descripción " + menor);
        listForm.get(6).sendKeys("Version Menor");
        driver.findElement(By.className("sapMSltArrow")).click();
        driver.findElement(By.xpath("//li[text()='Allocate' and @class ='sapMSelectListItemBase sapMSelectListItem sapMSelectListItemBaseHoverable']")).click();
        driver.findElement(By.id(save)).click();
    }

    public static void restoreVersion_NP(WebDriver driver,String restore){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.id(versionHistory)));
        driver.findElement(By.id(versionHistory)).click();
        String btn_xpath = "//span[@class='sapMBtnInner sapMBtnHoverable sapMFocusable sapMBtnIconFirst sapMBtnDefault']/parent::button[@class='sapMBtnBase sapMBtn']";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(btn_xpath)));
        List<WebElement> btn_restore = driver.findElements(By.xpath(btn_xpath));
        btn_restore.get(1).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//bdi[text()='Minor Version']")));
        driver.findElement(By.xpath("//bdi[text()='Minor Version']")).click();
        driver.findElement(By.xpath("//bdi[text()='Create Version']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id(save)));
        listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(2).clear();
        listForm.get(2).sendKeys(restore);
        listForm.get(3).clear();
        listForm.get(3).sendKeys(restore);
        listForm.get(4).clear();
        listForm.get(4).sendKeys("Descripción " + restore);
        listForm.get(6).sendKeys("Version restore");
        driver.findElement(By.id(save)).click();
    }

    // Risk Profile

    public static void formCreateRisk(WebDriver driver,String risk){
        driver.findElement(By.xpath("//div[@class='sapMBarRight sapMBarContainer']/button[@title='Añadir']")).click();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.id(save)));
        listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(2).sendKeys(risk);
        listForm.get(3).sendKeys(risk);
        listForm.get(4).sendKeys("Descripción " + risk);
        driver.findElement(By.id("__xmlview5--typeRisk-arrow")).click();
        driver.findElement(By.id("__item4-__xmlview5--typeRisk-0")).click();
        driver.findElement(By.id("__xmlview5--numberHoursRisk-inner")).sendKeys("4");
        driver.findElement(By.id("__xmlview5--numberMinutesRisk-inner")).sendKeys("60");
        driver.findElement(By.id(save)).click();
    }

    public static void formEditRisk(WebDriver driver,String risk){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.id(edit)));
        driver.findElement(By.id(edit)).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id(save)));
        listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(2).clear();
        listForm.get(2).sendKeys(risk);
        listForm.get(3).clear();
        listForm.get(3).sendKeys(risk);
        listForm.get(4).clear();
        listForm.get(4).sendKeys("Descripción " + risk);
        driver.findElement(By.id("__xmlview5--typeRisk-arrow")).click();
        driver.findElement(By.id("__item4-__xmlview5--typeRisk-1")).click();
        driver.findElement(By.id("__xmlview5--RateRisk-inner")).sendKeys("5");
        driver.findElement(By.id(save)).click();
    }

    public static void MayorVersionRisk(WebDriver driver,String risk){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.id(version)));
        driver.findElement(By.id(version)).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//bdi[text()='Major Version']")));
        driver.findElement(By.xpath("//bdi[text()='Major Version']")).click();
        driver.findElement(By.xpath("//bdi[text()='Create Version']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id(save)));
        listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(2).clear();
        listForm.get(2).sendKeys(risk);
        listForm.get(3).clear();
        listForm.get(3).sendKeys(risk);
        listForm.get(4).clear();
        listForm.get(4).sendKeys("Descripción " + risk);
        listForm.get(6).sendKeys("Version Mayor");
        driver.findElement(By.id("__xmlview5--typeRisk-arrow")).click();
        driver.findElement(By.id("__item4-__xmlview5--typeRisk-0")).click();
        driver.findElement(By.id(save)).click();
    }

    public static void MenorVersionRisk(WebDriver driver,String risk){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.id(version)));
        driver.findElement(By.id(version)).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//bdi[text()='Minor Version']")));
        driver.findElement(By.xpath("//bdi[text()='Minor Version']")).click();
        driver.findElement(By.xpath("//bdi[text()='Create Version']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id(save)));
        listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(2).clear();
        listForm.get(2).sendKeys(risk);
        listForm.get(3).clear();
        listForm.get(3).sendKeys(risk);
        listForm.get(4).clear();
        listForm.get(4).sendKeys("Descripción " + risk);
        listForm.get(6).sendKeys("Version Menor");
        driver.findElement(By.id(save)).click();
    }

    public static void restoreVersionRisk(WebDriver driver,String risk){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.id(versionHistory)));
        driver.findElement(By.id(versionHistory)).click();
        String btn_xpath = "//span[@class='sapMBtnInner sapMBtnHoverable sapMFocusable sapMBtnIconFirst sapMBtnDefault']/parent::button[@class='sapMBtnBase sapMBtn']";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(btn_xpath)));
        List<WebElement> btn_restore = driver.findElements(By.xpath(btn_xpath));
        btn_restore.get(1).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//bdi[text()='Minor Version']")));
        driver.findElement(By.xpath("//bdi[text()='Minor Version']")).click();
        driver.findElement(By.xpath("//bdi[text()='Create Version']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id(save)));
        listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(2).clear();
        listForm.get(2).sendKeys(risk);
        listForm.get(3).clear();
        listForm.get(3).sendKeys(risk);
        listForm.get(4).clear();
        listForm.get(4).sendKeys("Descripción " + risk);
        listForm.get(6).sendKeys("Version restore");
        driver.findElement(By.id(save)).click();
    }

}