package Applications.ConfigurationManager;


import Forms.ConfigurationManager.FormsINS;
import Helpers.Asserts;
import Helpers.BasicControl;
import Helpers.FormsControl;
import Helpers.SelectBrowser;
import HomePage.Login;
import HomePage.LoginApplications;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class CM_INS {

    private WebDriver driver;

    SelectBrowser browser;
    Asserts asserts;
    BasicControl basicControl;
    FormsINS formsINS;

    final String componente = "Instance Numbering Schemas";
    final String newINS = "INS Selenium";
    final String separator = "-";
    final String fixedValue = "SELENIUM";
    final String Counter = "Counter Selenium";

    public CM_INS(WebDriver driver){
        this.driver = driver;
        this.asserts = new Asserts(driver);
        this.basicControl = new BasicControl(driver);
        this.formsINS = new FormsINS(driver);
    }


    @Test
    public void crearINS(String INS,String separador,String valorFijo,String counter) throws InterruptedException {
        basicControl.btn_More(componente);
        formsINS.formCreateINS(INS,separador,valorFijo,counter);
        asserts.assertSave();
    }

    @Test
    public void editarINS(String INS,String INS_edit,String separador) throws InterruptedException {
        basicControl.btn_More(componente);
        String xmlview = basicControl.getXmlview();
        driver.findElement(By.xpath("//div[@id='"+xmlview+"--listObject']//div[text()='"+INS+"']")).click();
        formsINS.formEditINS(INS_edit,separador);
        asserts.assertSave();
    }


    @Test
    public void eliminarINS(@Optional(newINS) String INS){
        basicControl.btn_More(componente);
        FormsControl.controlDelete(driver,INS);
        String xpathMessage = "//span[@class='sapMText sapUiSelectable sapMTextMaxWidth sapMMsgBoxText']";
        asserts.assertDelete(xpathMessage);
    }


}
