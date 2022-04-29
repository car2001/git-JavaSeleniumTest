package Applications.OSM;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class OSM_Company {

    @Test(enabled = false)
    public void crearCompany() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        Actions action = new Actions(driver);
        driver.get("http://wedox.sytes.net/buplat_config/");
        driver.manage().window().maximize();
        Thread.sleep(5000); // Tiempo de Espera

        driver.findElement(By.id("__xmlview0--inputUserName-inner")).sendKeys("cpingo");
        driver.findElement(By.id("__xmlview0--inputPassword-inner")).sendKeys("1234");
        driver.findElement(By.id("__xmlview0--btnSubmit")).click();
        Thread.sleep(3000);

        //driver.findElement(By.xpath("//div[@id='__tile0-__xmlview2--container-9']")).click();
        driver.findElement(By.xpath("//span[normalize-space()='Organizational Structure Manager']")).click();
        Thread.sleep(8000);

        driver.findElement(By.id("__xmlview4--mainTree-rows-row0-treeicon")).click();
        //Crear Compañia y hacer RigthClick
        WebElement element = driver.findElement(By.xpath("//span[normalize-space()='Company']"));
        action.contextClick(element).perform();
        driver.findElement(By.xpath("//div[@id='__item1-unifiedmenu-txt']")).click();

        Thread.sleep(3000);
        //Llenar Formulario
        List<WebElement> listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(2).sendKeys("Company Selenium");
        listForm.get(3).sendKeys("Company Selenium");
        listForm.get(4).sendKeys("Compañia Creada en Selenium");
        listForm.get(5).sendKeys("123456");

        //Guardar Formulario
        driver.findElement(By.id("__xmlview4--save-inner")).click();
    }

    @Test
    public void doubleCheckCompany() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        Actions action = new Actions(driver);
        driver.get("http://wedox.sytes.net/buplat_config/");
        driver.manage().window().maximize();
        Thread.sleep(5000); // Tiempo de Espera

        driver.findElement(By.id("__xmlview0--inputUserName-inner")).sendKeys("cpingo");
        driver.findElement(By.id("__xmlview0--inputPassword-inner")).sendKeys("1234");
        driver.findElement(By.id("__xmlview0--btnSubmit")).click();
        Thread.sleep(3000);

        //driver.findElement(By.xpath("//div[@id='__tile0-__xmlview2--container-9']")).click();
        driver.findElement(By.xpath("//span[normalize-space()='Organizational Structure Manager']")).click();
        Thread.sleep(8000);

        driver.findElement(By.id("__xmlview4--mainTree-rows-row0-treeicon")).click();
        //Crear Compañia y hacer RigthClick
        WebElement element = driver.findElement(By.xpath("//span[normalize-space()='Company']"));
        action.contextClick(element).perform();
        driver.findElement(By.xpath("//div[@id='__item1-unifiedmenu-txt']")).click();

        Thread.sleep(3000);
        //Llenar Formulario
        List<WebElement> listForm = driver.findElements(By.className("sapMInputBaseInner"));
        listForm.get(2).sendKeys("Company Selenium");
        listForm.get(3).sendKeys("Company Selenium");
        listForm.get(4).sendKeys("Compañia Creada en Selenium");
        listForm.get(5).sendKeys("123456");

        //Guardar Formulario
        driver.findElement(By.id("__xmlview4--save-inner")).click();
        Thread.sleep(2000);
        String message = driver.findElement(By.className("sapMMsgStripMessage")).getAttribute("textContent");
        Assert.assertEquals(message,"Company Already Exist");
    }

    @Test
    public void viewCompanyDependencies() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        Actions action = new Actions(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.get("http://wedox.sytes.net/buplat_config/");
        driver.manage().window().maximize();
        Thread.sleep(5000); // Tiempo de Espera

        driver.findElement(By.id("__xmlview0--inputUserName-inner")).sendKeys("cpingo");
        driver.findElement(By.id("__xmlview0--inputPassword-inner")).sendKeys("1234");
        driver.findElement(By.id("__xmlview0--btnSubmit")).click();
        Thread.sleep(3000);

        //Ingresamos al OSM
        driver.findElement(By.xpath("//span[normalize-space()='Organizational Structure Manager']")).click();
        Thread.sleep(5000);
        //Desplegamos Client
        driver.findElement(By.id("__xmlview4--mainTree-rows-row0-treeicon")).click();
        //Desplegamos Company
        driver.findElement(By.id("__xmlview4--mainTree-rows-row1-treeicon")).click();
        Thread.sleep(5000);

        String company = "Company Selenium";
        //Obtenemos la lista de Objetos
        List<WebElement> elementTable = driver.findElements(By.xpath("//span[@class='sapMText sapUiSelectable sapMTextMaxWidth {Tree>class}' or @class='sapMText sapUiSelectable sapMTextBreakWord sapMTextMaxWidth {Tree>class}']"));
        //Creamos nuevo arreglo
        List<String> nameElement = new ArrayList<>();

        WebElement scrollBar = driver.findElement(By.id("__xmlview4--mainTree-vsb")); // Definimos el Scroll

        int scrollHeight = js.executeScript("let barra = document.getElementById('__xmlview4--mainTree-vsb');return(barra.scrollHeight)").hashCode();
        int clientHeight = js.executeScript("let barra = document.getElementById('__xmlview4--mainTree-vsb');return(barra.clientHeight)").hashCode();
        int numVeces =  scrollHeight/clientHeight; // Numero de veces para repetir el bucle
        boolean exist = false;

        //Pasamos los nombres de las CompaÃ±ias
        for(int i = 0; i<=elementTable.size()-1;i=i+1){
            nameElement.add(i,elementTable.get(i).getText());
        }

        int iterador = 0;
        // Verificamos
        while (iterador<=numVeces){
            if(nameElement.contains(company)){
                exist = true;
                break;
            }else{
                iterador = iterador+1;
                js.executeScript("arguments[0].scroll(0,'"+clientHeight*iterador+"')",scrollBar);
                elementTable = driver.findElements(By.xpath("//span[@class='sapMText sapUiSelectable sapMTextMaxWidth {Tree>class}' or @class='sapMText sapUiSelectable sapMTextBreakWord sapMTextMaxWidth {Tree>class}']"));
                for(int i = 0; i<=elementTable.size()-1;i=i+1){
                    nameElement.add(i,elementTable.get(i).getText());
                }

            }
        }

        if (exist = true){
            driver.findElement(By.xpath("//span[normalize-space()='"+company+"']")).click();
            Thread.sleep(1000);
            driver.findElement(By.id("__xmlview4--viewDependencies-img")).click();
            Thread.sleep(2000);
        }else{
            System.out.println("No hay la compañia" + company);
        }

    }


    @Test(enabled = false)
    public void editarCompany() throws InterruptedException {

        //Inicio de Propiedades
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        Actions action = new Actions(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.get("http://wedox.sytes.net/buplat_config/");
        driver.manage().window().maximize();
        Thread.sleep(5000); // Tiempo de Espera

        //Logeo
        driver.findElement(By.id("__xmlview0--inputUserName-inner")).sendKeys("cpingo");
        driver.findElement(By.id("__xmlview0--inputPassword-inner")).sendKeys("1234");
        driver.findElement(By.id("__xmlview0--btnSubmit")).click();
        Thread.sleep(3000);

        //Ingresamos al OSM
        driver.findElement(By.xpath("//span[normalize-space()='Organizational Structure Manager']")).click();
        Thread.sleep(5000);
        //Desplegamos Client
        driver.findElement(By.id("__xmlview4--mainTree-rows-row0-treeicon")).click();
        //Desplegamos Company
        driver.findElement(By.id("__xmlview4--mainTree-rows-row1-treeicon")).click();
        Thread.sleep(5000);

        String company = "Company Selenium";
        //Obtenemos la lista de Objetos
        List<WebElement> elementTable = driver.findElements(By.xpath("//span[@class='sapMText sapUiSelectable sapMTextMaxWidth {Tree>class}' or @class='sapMText sapUiSelectable sapMTextBreakWord sapMTextMaxWidth {Tree>class}']"));
        //Creamos nuevo arreglo
        List<String> nameElement = new ArrayList<>();

        WebElement scrollBar = driver.findElement(By.id("__xmlview4--mainTree-vsb")); // Definimos el Scroll

        int scrollHeight = js.executeScript("let barra = document.getElementById('__xmlview4--mainTree-vsb');return(barra.scrollHeight)").hashCode();
        int clientHeight = js.executeScript("let barra = document.getElementById('__xmlview4--mainTree-vsb');return(barra.clientHeight)").hashCode();
        int numVeces =  scrollHeight/clientHeight; // Numero de veces para repetir el bucle
        boolean exist = false;

        //Pasamos los nombres de las CompaÃ±ias
        for(int i = 0; i<=elementTable.size()-1;i=i+1){
            nameElement.add(i,elementTable.get(i).getText());
        }

        int iterador = 0;
        // Verificamos
        while (iterador<=numVeces){
            if(nameElement.contains(company)){
                exist = true;
                break;
            }else{
                iterador = iterador+1;
                js.executeScript("arguments[0].scroll(0,'"+clientHeight*iterador+"')",scrollBar);
                elementTable = driver.findElements(By.xpath("//span[@class='sapMText sapUiSelectable sapMTextMaxWidth {Tree>class}' or @class='sapMText sapUiSelectable sapMTextBreakWord sapMTextMaxWidth {Tree>class}']"));
                for(int i = 0; i<=elementTable.size()-1;i=i+1){
                    nameElement.add(i,elementTable.get(i).getText());
                }

            }
        }

        if(exist == true){
            driver.findElement(By.xpath("//span[normalize-space()='"+company+"']")).click();
            Thread.sleep(2000);
            //Editar Formulario
            driver.findElement(By.id("__xmlview4--edit-inner")).click();
            Thread.sleep(2000);
            //Llenar Formulario
            List<WebElement> listForm = driver.findElements(By.className("sapMInputBaseInner"));
            listForm.get(2).clear();
            listForm.get(2).sendKeys("Company Selenium1");
            listForm.get(3).clear();
            listForm.get(3).sendKeys("Company Selenium1");
            listForm.get(4).clear();
            listForm.get(4).sendKeys("Compañia Creada en Selenium1");
            listForm.get(5).clear();
            listForm.get(5).sendKeys("123456789");
            //Guardar Formulario
            driver.findElement(By.id("__xmlview4--save-inner")).click();


        }else{
            js.executeScript("alert('"+" No se encontro la compañia "+company+"')");
        }

    }

    @Test(enabled = false)
    public void eliminarCompany() throws InterruptedException {
        //Inicio de Propiedades
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        Actions action = new Actions(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.get("http://wedox.sytes.net/buplat_config/");
        driver.manage().window().maximize();
        Thread.sleep(5000); // Tiempo de Espera

        //Logeo
        driver.findElement(By.id("__xmlview0--inputUserName-inner")).sendKeys("cpingo");
        driver.findElement(By.id("__xmlview0--inputPassword-inner")).sendKeys("1234");
        driver.findElement(By.id("__xmlview0--btnSubmit")).click();
        Thread.sleep(3000);

        //Ingresamos al OSM
        driver.findElement(By.xpath("//span[normalize-space()='Organizational Structure Manager']")).click();
        Thread.sleep(5000);
        //Desplegamos Client
        driver.findElement(By.id("__xmlview4--mainTree-rows-row0-treeicon")).click();
        //Desplegamos Company
        driver.findElement(By.id("__xmlview4--mainTree-rows-row1-treeicon")).click();
        Thread.sleep(5000);

        String company = "Company Selenium1";
        //Obtenemos la lista de Objetos
        List<WebElement> elementTable = driver.findElements(By.xpath("//span[@class='sapMText sapUiSelectable sapMTextMaxWidth {Tree>class}' or @class='sapMText sapUiSelectable sapMTextBreakWord sapMTextMaxWidth {Tree>class}']"));
        //Creamos nuevo arreglo
        List<String> nameElement = new ArrayList<>();

        WebElement scrollBar = driver.findElement(By.id("__xmlview4--mainTree-vsb")); // Definimos el Scroll

        int scrollHeight = js.executeScript("let barra = document.getElementById('__xmlview4--mainTree-vsb');return(barra.scrollHeight)").hashCode();
        int clientHeight = js.executeScript("let barra = document.getElementById('__xmlview4--mainTree-vsb');return(barra.clientHeight)").hashCode();
        int numVeces =  scrollHeight/clientHeight; // Numero de veces para repetir el bucle
        boolean exist = false;

        //Pasamos los nombres de las CompaÃ±ias
        for(int i = 0; i<=elementTable.size()-1;i=i+1){
            nameElement.add(i,elementTable.get(i).getText());
        }

        int iterador = 0;
        // Verificamos
        while (iterador<=numVeces){
            if(nameElement.contains(company)){
                exist = true;
                break;
            }else{
                iterador = iterador+1;
                js.executeScript("arguments[0].scroll(0,'"+clientHeight*iterador+"')",scrollBar);
                elementTable = driver.findElements(By.xpath("//span[@class='sapMText sapUiSelectable sapMTextMaxWidth {Tree>class}' or @class='sapMText sapUiSelectable sapMTextBreakWord sapMTextMaxWidth {Tree>class}']"));
                for(int i = 0; i<=elementTable.size()-1;i=i+1){
                    nameElement.add(i,elementTable.get(i).getText());
                }

            }
        }

        if(exist == true){
            WebElement element = driver.findElement(By.xpath("//span[normalize-space()='"+company+"']"));
            action.contextClick(element).perform();
            driver.findElement(By.xpath("//div[@id='__item2-unifiedmenu-txt']")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//bdi[normalize-space()='Si']")).click();
            Thread.sleep(2000);

            String message = driver.findElement(By.xpath("//span[@class='sapMText sapUiSelectable sapMTextMaxWidth sapMMsgBoxText']")).getText();
            Thread.sleep(2000);
            if(message.equals("The Operation has been Completed Successfully.")){
                driver.findElement(By.xpath("//bdi[normalize-space()='OK']")).click();
            }else{
                driver.findElement(By.xpath("//bdi[normalize-space()='Cerrar']")).click();
            }

        }else{
            js.executeScript("alert('"+" No se encontro la compañia "+company+"')");
        }

    }


}
