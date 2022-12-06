package Applications.ConfigurationManager;

import Forms.ConfigurationManager.FormsCounter;
import Helpers.Asserts;
import Helpers.BasicControl;
import Helpers.FormsControl;
import Helpers.SelectBrowser;
import HomePage.Login;
import HomePage.LoginApplications;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class CM_Counter {

    private WebDriver driver;

    private Asserts asserts;
    private BasicControl basicControl;
    private FormsCounter formsCounter;

    private final String componente = "Counters";

    public CM_Counter(WebDriver driver){
        this.driver = driver;
        this.asserts = new Asserts(driver);
        this.basicControl = new BasicControl(driver);
        this.formsCounter = new FormsCounter(driver);
    }


    public void crearCounter (String counter,String inicio,String aumento){
        basicControl.btn_More(componente);
        formsCounter.formCreateCounter(counter,inicio,aumento);
        asserts.assertSave();
    }

    public void editarCounter (String counter, String counterEdit,String inicio,String aumento) throws InterruptedException {
        basicControl.btn_More(componente);
        String xmlview = basicControl.getXmlview();
        driver.findElement(By.xpath("//div[@id='"+xmlview+"--listObject']//div[text()='"+counter+"']")).click();
        formsCounter.formEditCounter(counterEdit,inicio,aumento);
        asserts.assertSave();
    }


    public void eliminarCounter(String counter){
        basicControl.btn_More(componente);
        FormsControl.controlDelete(driver,counter);
        String xpathMessage = "//span[@class='sapMText sapUiSelectable sapMTextMaxWidth sapMMsgBoxText']";
        asserts.assertDelete(xpathMessage);
    }

}
