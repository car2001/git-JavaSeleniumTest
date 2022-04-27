import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;


public class Challenge {

    public String concatenarCadena(String cadena,String conc, char caracter){
        int ultimo = cadena.lastIndexOf(caracter);
        String newCadena = cadena.substring(0,ultimo+1);
        newCadena = newCadena+conc;
        return newCadena;
    }


    @Test
    public void crearCounter() throws InterruptedException {
        //Iniciar propiedades
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
        //Iniciamos chrome
        WebDriver driver = new ChromeDriver();
        //Navegamos a la pagina deseada Buplat Dev
        driver.get("http://wedox.sytes.net/buplat_dev/");
        driver.manage().window().maximize();
        //tIEMPO DE ESPERA
        Thread.sleep(5000);
        //NOS LOGEAMOS EN BUPLAT
        //driver.findElement(By.xpath("//input[@id='__xmlview0--inputUserName-inner']")).sendKeys("cpingo");
        driver.findElement(By.id("__xmlview0--inputUserName-inner")).sendKeys("cpingo");
        driver.findElement(By.id("__xmlview0--inputPassword-inner")).sendKeys("1234");
        driver.findElement(By.id("__xmlview0--btnSubmit")).click();

        Thread.sleep(4000);
        //Ingresamos al CM
        //driver.findElement(By.xpath("//div[@id='__content0-__xmlview2--container-4']")).click();
        driver.findElement(By.xpath("//span[normalize-space()='Configuration Manager']")).click();
        Thread.sleep(4000);

        //Creamos un Counter
        driver.findElement(By.xpath("//span[@class='sapMText sapTntNavLIText sapMTextNoWrap' and normalize-space()='Reusable Component']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[normalize-space()='Counter']")).click();
        Thread.sleep(4000);
        driver.findElement(By.xpath("//span[@id='__xmlview5--add-img']")).click();
        //Editamos Formulario de Counter
        driver.findElement(By.id("__xmlview5--txtName-inner")).sendKeys("CounterSelenium1");
        driver.findElement(By.id("__xmlview5--txtDisplayName-inner")).sendKeys("Counter Selenium1");
        driver.findElement(By.id("__xmlview5--txtDescription-inner")).sendKeys("Descripcion del Counter");
        driver.findElement(By.id("__xmlview5--txtCounterStartAt-inner")).sendKeys("1900");
        driver.findElement(By.id("__xmlview5--txtCounterIncrement-inner")).sendKeys("1");
        //driver.findElement(By.id("__xmlview5--save-img")).click(); // Guardar Counter
        driver.findElement(By.id("__xmlview5--cancel-img")).click(); // Cancelar Counter
    }


    @Test
    public void selecionarCounter() throws InterruptedException {
        //Iniciar propiedades
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
        //Iniciamos chrome
        WebDriver driver = new ChromeDriver();
        //Navegamos a la pagina deseada Buplat Dev
        driver.get("http://wedox.sytes.net/buplat_dev/");
        driver.manage().window().maximize();
        //tIEMPO DE ESPERA
        Thread.sleep(5000);
        //NOS LOGEAMOS EN BUPLAT
        //driver.findElement(By.xpath("//input[@id='__xmlview0--inputUserName-inner']")).sendKeys("cpingo");
        driver.findElement(By.id("__xmlview0--inputUserName-inner")).sendKeys("cpingo");
        driver.findElement(By.id("__xmlview0--inputPassword-inner")).sendKeys("1234");
        driver.findElement(By.id("__xmlview0--btnSubmit")).click();
        Thread.sleep(4000);
        //Ingresamos al CM
        driver.findElement(By.xpath("//div[@id='__content0-__xmlview2--container-4']")).click();
        Thread.sleep(4000);
        driver.findElement(By.xpath("//span[normalize-space()='Reusable Component']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[normalize-space()='Counter']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//div[normalize-space()='Counter Selenium']")).click();
        //driver.findElement(By.id("__item3-__clone10-content")).click();
        driver.findElement(By.id("__xmlview5--edit-img")).click();
    }

}


