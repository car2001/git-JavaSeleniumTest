import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ReleaseManager {

    @Test
    public void crearProyecto() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        Actions action = new Actions(driver);
        driver.get("http://wedox.sytes.net/buplat_dev/");
        driver.manage().window().maximize();
        Thread.sleep(5000); // Tiempo de Espera

        driver.findElement(By.id("__xmlview0--inputUserName-inner")).sendKeys("cpingo");
        driver.findElement(By.id("__xmlview0--inputPassword-inner")).sendKeys("1234");
        driver.findElement(By.id("__xmlview0--btnSubmit")).click();
        Thread.sleep(3000);

        //driver.findElement(By.xpath("//div[@id='__tile0-__xmlview2--container-9']")).click();
        driver.findElement(By.xpath("//span[normalize-space()='Release Manager']")).click();
        Thread.sleep(8000);

        WebElement element = driver.findElement(By.xpath("//span[normalize-space()='Project']"));
        action.contextClick(element).perform();
        driver.findElement(By.xpath("//div[@id='__item1-unifiedmenu-txt']")).click();

        Thread.sleep(3000);
        //Llenar Formulario
        List<WebElement> listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(2).sendKeys("Proyecto Selenium");
        listForm.get(3).sendKeys("Proyecto Selenium");
        listForm.get(4).sendKeys("Proyecto Creado en Selenium");
        driver.findElement(By.id("__xmlview4--ProjectStartDate-icon")).click();
        driver.findElement(By.xpath("//span[normalize-space()='14']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[@id='__xmlview4--ProjectEndDate-icon']")).click();
        driver.findElement(By.xpath("//div[@id='__xmlview4--ProjectEndDate-cal--Month0-20220429']//span[@class='sapUiCalItemText'][normalize-space()='29']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[@id='__xmlview4--selectProjectState-label']")).click();
        // Open(O),In Progress(I),Closed(C)
        char combo = 'I';
        if(combo == 'O'){
            driver.findElement(By.xpath("//li[@id='__item15-__xmlview4--selectProjectState-0']")).click();
        }else if(combo == 'I'){
            driver.findElement(By.xpath("//li[@id='__item15-__xmlview4--selectProjectState-1']")).click();
        }else{
            driver.findElement(By.xpath("//li[@id='__item15-__xmlview4--selectProjectState-2']")).click();
        }

        driver.findElement(By.id("__xmlview4--useInProcess-handle")).click();
        driver.findElement(By.id("__xmlview4--useInReleases-handle")).click();

        //Guardamos(G) o cancelamos(C)
        char decision = 'G';

        if (decision == 'G'){
            driver.findElement(By.id("__xmlview4--save-img")).click();
            Thread.sleep(1000);
            WebElement message = driver.findElement(By.xpath("//span[@id='__text46']"));
            //Assert.assertEquals(message.getText(),"The Operation has been Completed Successfully.");
            System.out.println(message.getText());
        }else {
            driver.findElement(By.id("__xmlview4--cancel-img")).click();
        }
    }

    @Test
    public void editarProyecto() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        Actions action = new Actions(driver);
        driver.get("http://wedox.sytes.net/buplat_dev/");
        driver.manage().window().maximize();
        Thread.sleep(5000); // Tiempo de Espera

        driver.findElement(By.id("__xmlview0--inputUserName-inner")).sendKeys("cpingo");
        driver.findElement(By.id("__xmlview0--inputPassword-inner")).sendKeys("1234");
        driver.findElement(By.id("__xmlview0--btnSubmit")).click();
        Thread.sleep(3000);

        driver.findElement(By.xpath("//span[normalize-space()='Release Manager']")).click();
        Thread.sleep(5000);

        driver.findElement(By.id("__xmlview4--mainTree-rows-row0-treeicon")).click(); // Para ver más Proyectos
        Thread.sleep(10000);
        driver.findElement(By.xpath("//span[normalize-space()='Proyect1']")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("__xmlview4--edit-img")).click(); // Editar Proyecto

        Thread.sleep(2000);
        //Llenar Formulario
        List<WebElement> listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(2).clear();
        listForm.get(2).sendKeys("PSelenium");
        listForm.get(3).clear();
        listForm.get(3).sendKeys("PSelenium");

        listForm.get(4).sendKeys("Creado en Selenium");
        driver.findElement(By.id("__xmlview4--ProjectStartDate-icon")).click();
        driver.findElement(By.xpath("//span[normalize-space()='11']")).click();
        Thread.sleep(1000);
        //driver.findElement(By.xpath("//span[@id='__xmlview4--ProjectEndDate-icon']")).click();
        //driver.findElement(By.xpath("//div[@id='__xmlview4--ProjectEndDate-cal--Month0-20220429']//span[@class='sapUiCalItemText'][normalize-space()='30']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[@id='__xmlview4--selectProjectState-label']")).click();
        // Open(O),In Progress(I),Closed(C)
        char combo = 'I';
        if(combo == 'O'){
            driver.findElement(By.xpath("//li[@id='__item15-__xmlview4--selectProjectState-0']")).click();
        }else if(combo == 'I'){
            driver.findElement(By.xpath("//li[@id='__item15-__xmlview4--selectProjectState-1']")).click();
        }else{
            driver.findElement(By.xpath("//li[@id='__item15-__xmlview4--selectProjectState-2']")).click();
        }

        driver.findElement(By.id("__xmlview4--useInProcess-handle")).click();
        driver.findElement(By.id("__xmlview4--useInReleases-handle")).click();

        //Guardamos(G) o cancelamos(C)
        char decision = 'G';

        if (decision == 'G'){
            driver.findElement(By.id("__xmlview4--save-img")).click();
            Thread.sleep(1000);
            WebElement message = driver.findElement(By.xpath("//span[@id='__text46']"));
            //Assert.assertEquals(message.getText(),"The Operation has been Completed Successfully.");
            System.out.println(message.getText());
        }else {
            driver.findElement(By.id("__xmlview4--cancel-img")).click();
        }


    }

}
