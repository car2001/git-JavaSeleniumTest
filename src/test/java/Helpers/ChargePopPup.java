package Helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ChargePopPup {

    public static void PopPupGeneral(WebDriver driver, WebDriverWait wait) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        try {
            WebElement popupCarga = driver.findElement(By.xpath("//div[@id='sapUiBusyIndicator' and @class='sapUiUserSelectable']"));
            wait.until(ExpectedConditions.visibilityOf(popupCarga));
            wait.until(ExpectedConditions.invisibilityOf(popupCarga));
            Thread.sleep(800);
            WebElement blockLayer = driver.findElement(By.id("sap-ui-blocklayer-popup"));
            while (blockLayer.getAttribute("style").contains("visible;")){
                blockLayer = driver.findElement(By.id("sap-ui-blocklayer-popup"));
            }
            Thread.sleep(500);

        } catch (Exception e) {
            //System.out.println(e.getMessage());
        }
    }


    public static void PopPupMain(WebDriver driver, WebDriverWait wait){

        try{
            WebElement popupCarga = driver.findElement(By.xpath("//div[@class='sapUiBlockLayer  sapUiLocalBusyIndicator sapUiLocalBusyIndicatorSizeMedium sapUiLocalBusyIndicatorFade' and contains(@id,'--resSplitMain-busyIndicator')]"));
            wait.until(ExpectedConditions.visibilityOf(popupCarga));
            wait.until(ExpectedConditions.invisibilityOf(popupCarga));
            Thread.sleep(1000);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }

    public static void PopPupDetail(WebDriver driver, WebDriverWait wait){
        try{
            WebElement popupCarga = driver.findElement(By.xpath("//div[contains(@id,'--detail-busyIndicator')]"));
            wait.until(ExpectedConditions.visibilityOf(popupCarga));
            wait.until(ExpectedConditions.invisibilityOf(popupCarga));
            Thread.sleep(1000);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void PopPupTree(WebDriver driver, WebDriverWait wait){
        try{
            WebElement popupCarga = driver.findElement(By.xpath("//div[contains(@id,'--mainTree-busyIndicator')]"));
            wait.until(ExpectedConditions.visibilityOf(popupCarga));
            wait.until(ExpectedConditions.invisibilityOf(popupCarga));
            Thread.sleep(1000);
        }catch (Exception e){

        }
    }

}