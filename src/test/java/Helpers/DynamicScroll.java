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


    public  String searchElementTable(String project, String user ,String state ,String release,String name){
        WebElement scrollTable;
        Boolean displayedScroll = false;
        String xpos = "";

        List<WebElement> webElementList = driver.findElements(By.xpath("//tr[contains(@id,'--tblComponentList-rows-row')]"));
        List<String> textContentList = new ArrayList<>();

        //Pasamos los nombres de los Elementos al nuevo array
        for(int i = 0; i<= webElementList.size()-1;i=i+1){
            String textContent = js.executeScript("let text = arguments[0].textContent; return(text)",webElementList.get(i)).toString();
            textContentList.add(textContent);
        }



        try{
            scrollTable = driver.findElement(By.xpath("//div[contains(@id,'--tblComponentList-vsb')]"));
            displayedScroll = scrollTable.isDisplayed();
        }catch (Exception e){
            System.out.println("No se encontro scroll");
        }

        if(displayedScroll == true){
            scrollTable = driver.findElement(By.xpath("//div[contains(@id,'--tblComponentList-vsb')]"));
            int scrollHeight= js.executeScript("let scrollHeight = arguments[0].scrollHeight; return(scrollHeight)",scrollTable).hashCode();
            int clientHeight = js.executeScript("let clientHeight = arguments[0].clientHeight; return(clientHeight)",scrollTable).hashCode();

            int numVeces = scrollHeight/clientHeight;
            int iterator = 0;


            while (iterator<=numVeces+1){
                xpos = busqueda(textContentList,project,user,state,release,name);
                if(xpos != " "){
                    break;
                }else{
                    iterator = iterator+1;
                    js.executeScript("arguments[0].scroll(0,'"+clientHeight+"')",scrollTable);
                    webElementList = driver.findElements(By.xpath("//tr[contains(@id,'--tblComponentList-rows-row')]"));
                    textContentList.clear();
                    for(int i = 0; i<= webElementList.size()-1;i=i+1){
                        String textContent = js.executeScript("let text = arguments[0].textContent; return(text)",webElementList.get(i)).toString();
                        textContentList.add(textContent);
                    }
                }
            }
        } else{
            xpos = busqueda(textContentList,project,user,state,release,name);
        }

        return xpos;
    }

    public  String busqueda (List<String> contentList,String project, String user ,String state ,String release,String name){
        String xpos = " ";
        String content = " ";
        for(int i = 0; i<= contentList.size()-1;i=i+1){
            content = contentList.get(i);
            if(content.contains(state) && content.contains(user) && content.contains(project) && content.contains(release)){
                xpos = content.substring(0,content.indexOf(name));
                break;
            }else{
                xpos = " ";
            }
        }
        return  xpos;
    }



}
