package Helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ChargePopPup {

    public static void PopPupGeneral(WebDriver driver, WebDriverWait wait) {
        try {
            WebElement popupCarga = driver.findElement(By.cssSelector("#sapUiBusyIndicator.sapUiUserSelectable"));
            wait.until(ExpectedConditions.visibilityOf(popupCarga));
            wait.until(ExpectedConditions.invisibilityOf(popupCarga));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static void PopPupMain(WebDriver driver, WebDriverWait wait){

        try{
            WebElement popupCarga = driver.findElement(By.xpath("//div[@class='sapUiBlockLayer  sapUiLocalBusyIndicator sapUiLocalBusyIndicatorSizeMedium sapUiLocalBusyIndicatorFade' and contains(@id,'--resSplitMain-busyIndicator')]"));
            wait.until(ExpectedConditions.visibilityOf(popupCarga));
            wait.until(ExpectedConditions.invisibilityOf(popupCarga));
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }

    public static void PopPupDetail(WebDriver driver, WebDriverWait wait){
        try{
            WebElement popupCarga = driver.findElement(By.xpath("//div[contains(@id,'--detail-busyIndicator')]"));
            wait.until(ExpectedConditions.visibilityOf(popupCarga));
            wait.until(ExpectedConditions.invisibilityOf(popupCarga));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void PopPupTree(WebDriver driver, WebDriverWait wait){
        try{
            WebElement popupCarga = driver.findElement(By.xpath("//div[contains(@id,'--mainTree-busyIndicator')]"));
            wait.until(ExpectedConditions.visibilityOf(popupCarga));
            wait.until(ExpectedConditions.invisibilityOf(popupCarga));
        }catch (Exception e){

        }
    }

}
