package Helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasicControl {
    private WebDriver driver;
    private JavascriptExecutor js;
    private WebDriverWait wait;

    public BasicControl(WebDriver driver){
        this.driver = driver;
        this.js = (JavascriptExecutor)driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));
    }

    public void btnAdd(){
        driver.findElement(By.xpath("//span[contains(@id,'--add-img')]")).click();
    }

    public void btnEdit() throws InterruptedException {
        driver.findElement(By.xpath("//span[contains(@id,'--edit-img')]")).click();
        FormsControl.controlLook(driver,"//span[contains(@id,'--edit-img')]",js);
    }

    public void btnEdit(String edit) throws InterruptedException {
        driver.findElement(By.xpath("//span[contains(@id,'"+edit+"')]")).click();
        FormsControl.controlLook(driver,"//span[contains(@id,'"+edit+"')]",js);
    }

    public void btnSave(){
        driver.findElement(By.xpath("//span[contains(@id,'--save-img')]")).click();
    }

    public void btnSave(String save){
        driver.findElement(By.xpath("//span[contains(@id,'"+save+"')]")).click();
    }

    public void btnCancel() {
        driver.findElement(By.xpath("//span[contains(@id,'--cancel-img')]")).click();
    }

    public void btnDependecies(){
        driver.findElement(By.xpath("//span[contains(@id,'--viewDependencies-img')]")).click();
    }

    public void btNewVersion(){
        driver.findElement(By.xpath("//span[contains(@id,'--newVersion-img')]")).click();

    }

    public void btnVersionHistory(){
        driver.findElement(By.xpath("//span[contains(@id,'--versionHistory-img')]")).click();
    }

    public void logo(){
        driver.findElement(By.xpath("//img[@src='./public/images/buplat_logo_blanco.png']")).click();
        String routePM = "//span[@class='sapMTextMaxLine sapMTextLineClamp' and normalize-space()='Process Manager']";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(routePM)));
    }

    public void claim(){
        driver.findElement(By.xpath("//span[contains(@id,'--btnClain-img')]")).click();
        ChargePopPup.PopPupGeneral(driver,wait);
    }

    public void submit(){
        driver.findElement(By.xpath("//bdi[text() = 'Enviar' and contains(@id,'--btnSubmit-BDI-content')]")).click();
        ChargePopPup.PopPupGeneral(driver,wait);
    }


}
