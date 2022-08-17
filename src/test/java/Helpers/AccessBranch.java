package Helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AccessBranch {
    private WebDriver driver;
    private JavascriptExecutor js ;


    public AccessBranch(WebDriver driver) {
        this.driver = driver;
        js= (JavascriptExecutor) driver;
    }

    public void clickBranches(Integer exist){
        WebElement branch = driver.findElement(By.xpath("//span[contains(@id,'-mainTree-rows-row"+exist+"-treeicon')]"));
        String id = branch.getAttribute("id").substring(9,10);
        branch.click();
        try {
            String script = "let next=document.getElementById('__xmlview"+id+"--mainTree-rows-row"+(exist+1)+"');return next.textContent";
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
