package HomepageFunctions;


import Helpers.AccessBranches;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Login_Applications {
    public static WebDriverWait wait;
    public static AccessBranches accessBranch;

    public static void loginOSM(WebDriver driver){
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        accessBranch = new AccessBranches(driver);
        String routeOSM = "//span[@class='sapMTextMaxLine sapMTextLineClamp' and normalize-space()='Organizational Structure Manager']";
        driver.findElement(By.xpath(routeOSM)).click();
        accessBranch.clickBranches(0);
        accessBranch.clickBranches(1);
//        try{
//            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("__xmlview4--mainTree-vsb")));
//        }catch (Exception e){
//            System.out.println("Se espero el Scroll pero no se encontro");
//        }<
    }

    public static void loginCM(WebDriver driver,String componente){
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String routeCM = "//span[@class='sapMTextMaxLine sapMTextLineClamp' and normalize-space()='Configuration Manager']";
        driver.findElement(By.xpath(routeCM)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("navListItem-navList-0-a")));
        driver.findElement(By.xpath("//div[@title='Reusable Component']")).click();
        driver.findElement(By.xpath("//div[@title='Setting']")).click();
        driver.findElement(By.xpath("//span[text()='"+componente+"']")).click();
        String progress;
        String mas = "//span[text()='Más' and @class='sapMSLITitle']";
        progress = "//div[@class='sapUiLocalBusyIndicator sapUiLocalBusyIndicatorSizeBig sapUiLocalBusyIndicatorFade' and @role='progressbar']";
        try {
            WebElement barra = driver.findElement(By.xpath(progress));
            wait.until(ExpectedConditions.invisibilityOf(barra));
            WebElement more = driver.findElement(By.xpath(mas));
            if(more.isDisplayed()){
                more.click();
            }
        }catch (Exception error){
            System.out.println("No hay elemento más");
        }
    }

    public static void loginRM(WebDriver driver, String componente){
        wait = new WebDriverWait(driver,Duration.ofSeconds(50));
        accessBranch = new AccessBranches(driver);
        String routeRM = "//span[@class='sapMTextMaxLine sapMTextLineClamp' and normalize-space()='Release Manager']";
        driver.findElement(By.xpath(routeRM)).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("__xmlview4--mainTree-rows-row0-treeicon")));
        if(componente.equals("Project")){
            accessBranch.clickBranches(0);
            //WebElement popupCarga = driver.findElement(By.xpath("//div[@id='__xmlview4--mainTree-busyIndicator']"));
            //wait.until(ExpectedConditions.visibilityOf(popupCarga));
            //wait.until(ExpectedConditions.invisibilityOf(popupCarga));
        }else if(componente.equals("Change Container")){
            accessBranch.clickBranches(1);
        }else if (componente.equals("Deployment Package")){
            accessBranch.clickBranches(2);
        }else{
            accessBranch.clickBranches(4);
        }
    }

    public static void main(String[] args) {
        String division = "20";
        int entero = Integer.parseInt(division);
        System.out.println(entero);
    }
}
