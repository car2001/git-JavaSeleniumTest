package Helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class DynamicScroll {
    private WebDriver driver;
    private JavascriptExecutor js ;


    public DynamicScroll(WebDriver driver){
        this.driver = driver;
        js= (JavascriptExecutor) driver;
    }

    public int elementSearch(String element) {
        int positionFound = -1;
        Boolean existScroll;
        String xpathCompany = "//span[@class='sapMText sapUiSelectable sapMTextMaxWidth {Tree>class}' or @class='sapMText sapUiSelectable sapMTextBreakWord sapMTextMaxWidth {Tree>class}']";

        //Obtenemos la lista de Objetos
        List<WebElement>  elementTable = driver.findElements(By.xpath(xpathCompany));
        while(elementTable.contains("Loading...") ){
            elementTable = driver.findElements(By.xpath(xpathCompany));
        }

        //Creamos nuevo arreglo
        List<String> nameElement = new ArrayList<>();
        //Pasamos los nombres de los Elementos al nuevo array
        for(int i = 0; i<=elementTable.size()-1;i=i+1){
            if(elementTable.get(i).getText().equals("Loading...") == false){
                nameElement.add(elementTable.get(i).getText());
            }
        }

        existScroll = driver.findElement(By.cssSelector(".sapUiTableVSb")).isDisplayed();

        if(existScroll){
            try {
                WebElement scrollBar = driver.findElement(By.xpath("//div[contains(@id,'--mainTree-vsb') and @class='sapUiTableVSb']"));
                int scrollHeight,clientHeight;

                scrollHeight = js.executeScript("let int = arguments[0].scrollHeight; return(int)",scrollBar).hashCode();
                clientHeight = js.executeScript("let int = arguments[0].clientHeight; return(int)",scrollBar).hashCode();

                int numVeces,iterator;

                numVeces = scrollHeight/clientHeight; // Numero de veces para repetir el bucle
                iterator = 0;
                // Verificamos
                while (iterator<=numVeces+1){
                    if(nameElement.lastIndexOf(element) != -1){
                        positionFound = nameElement.lastIndexOf(element);
                        break;
                    }else{
                        iterator = iterator+1;
                        int multiplo = clientHeight*iterator ;
                        js.executeScript("arguments[0].scroll(0,'"+multiplo+"')",scrollBar);
                        elementTable = driver.findElements(By.xpath(xpathCompany));
                        nameElement.clear();
                        for(int i = 0; i<=elementTable.size()-1;i=i+1){
                            if(elementTable.get(i).getText().equals("Loading...") == false){
                                nameElement.add(elementTable.get(i).getText());
                            }
                        }
                    }
                }

            }catch (ArithmeticException e){
                System.out.println(e);
            }

        }else{
            if(nameElement.lastIndexOf(element)!= -1){
                positionFound = nameElement.lastIndexOf(element);
            }else {
                positionFound = -1;
            }
        }
        return positionFound;
    }
}
