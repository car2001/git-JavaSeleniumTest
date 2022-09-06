package Helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SelectListItem {
    public WebDriver driver;

    public SelectListItem(WebDriver driver){
        this.driver = driver;
    }

    public void SelectItemDiv(String item){
        String path = "//div[contains(@id,'-popup')][contains(@style,'visibility: visible;')]//div[text()='"+item+"']";
        WebElement element = driver.findElement(By.xpath(path));
        element.click();
    }

    public void SelectItemLi(String item){
        String path = "//div[contains(@id,'__popover')][contains(@style,'visibility: visible;')]//li[text()='"+item+"']";
        WebElement element = driver.findElement(By.xpath(path));
        element.click();
    }

}