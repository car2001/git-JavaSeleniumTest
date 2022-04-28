package Helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Dynamic_Scroll_Search {
    private WebDriver driver;
    private JavascriptExecutor js ;

    public Dynamic_Scroll_Search(WebDriver driver){
        this.driver = driver;
        js= (JavascriptExecutor) driver;
    }

    public int elementSearch(String element){
        int scrollHeight,clientHeight,numVeces,iterator;
        int positionFound = -1;
        boolean exist;
        List<WebElement> elementTable;
        List<String> nameElement;
        WebElement scrollBar;

        //Obtenemos la lista de Objetos
        elementTable = driver.findElements(By.xpath("//span[@class='sapMText sapUiSelectable sapMTextMaxWidth {Tree>class}' or @class='sapMText sapUiSelectable sapMTextBreakWord sapMTextMaxWidth {Tree>class}']"));
        //Creamos nuevo arreglo
        nameElement = new ArrayList<>();
        // Definimos el Scroll
        scrollBar = driver.findElement(By.id("__xmlview4--mainTree-vsb"));

        scrollHeight = js.executeScript("let barra = document.getElementById('__xmlview4--mainTree-vsb');return(barra.scrollHeight)").hashCode();
        clientHeight = js.executeScript("let barra = document.getElementById('__xmlview4--mainTree-vsb');return(barra.clientHeight)").hashCode();

        try {
           numVeces = scrollHeight/clientHeight; // Numero de veces para repetir el bucle

            //Pasamos los nombres de los Elementos
            for(int i = 0; i<=elementTable.size()-1;i=i+1){
                nameElement.add(i,elementTable.get(i).getText());
            }

            iterator = 0;
            // Verificamos
            while (iterator<=numVeces){
                if(nameElement.indexOf(element) != -1){
                    positionFound = nameElement.indexOf(element);
                    break;
                }else{
                    iterator = iterator+1;
                    js.executeScript("arguments[0].scroll(0,'"+(clientHeight*iterator)+"')",scrollBar);
                    elementTable = driver.findElements(By.xpath("//span[@class='sapMText sapUiSelectable sapMTextMaxWidth {Tree>class}' or @class='sapMText sapUiSelectable sapMTextBreakWord sapMTextMaxWidth {Tree>class}']"));
                    for(int i = 0; i<=elementTable.size()-1;i=i+1){
                        nameElement.add(i,elementTable.get(i).getText());
                    }

                }
            }

        }catch (ArithmeticException e){
            e.printStackTrace();
        }

        return positionFound;
    }

}
