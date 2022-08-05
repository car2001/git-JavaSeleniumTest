package Helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;


import java.time.Duration;

public class AccessBranches {
    private WebDriver driver;
    private JavascriptExecutor js ;


    public AccessBranches(WebDriver driver) {
        this.driver = driver;
        js= (JavascriptExecutor) driver;
    }

    public void clickBranches(Integer exist){
        String branch = "__xmlview4--mainTree-rows-row"+exist+"-treeicon";
        driver.findElement(By.id(branch)).click();
        try {
            String script = "let next=document.getElementById('__xmlview4--mainTree-rows-row"+(exist+1)+"');return next.textContent";
            String next = js.executeScript(script).toString();
            while(next.contains("Loading...")){
                next = js.executeScript(script).toString();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Esta al final");
        }

    }
}
