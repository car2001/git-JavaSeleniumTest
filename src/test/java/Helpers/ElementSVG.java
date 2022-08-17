package Helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ElementSVG {

    public static void clickSVGElements(String cssSelectorSVG, JavascriptExecutor js, Actions action , WebDriver driver , String rect, int x){
        js.executeScript("let g = document.querySelector('"+rect+"'); g.scrollIntoView();");

        WebElement task1 =  driver.findElement(By.cssSelector(cssSelectorSVG));
        action.moveToElement(task1,x,0).click().build().perform();
        js.executeScript("let y = document.getElementById('__xmlview4--detail-cont');y.scroll(0,0)");
    }


}
