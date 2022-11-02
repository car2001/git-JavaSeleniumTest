package Applications.ConfigurationManager;


import Forms.ConfigurationManager.FormsRiskProfile;
import Helpers.Asserts;
import Helpers.BasicControl;
import Helpers.FormsControl;
import Helpers.SelectBrowser;
import HomePage.Login;
import HomePage.LoginApplications;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class CM_Risk_Profile {
    private WebDriver driver;
    private String chosen_browser = "Chrome";

    SelectBrowser browser = new SelectBrowser(driver);
    Login login;
    Asserts asserts;
    BasicControl basicControl;
    FormsRiskProfile formsRiskProfile;

    final String componente = "Risk Profiles";
    final String newRiskProfile = "Risk Profile Selenium";
    final String editRiskProfile = "Risk Profile Selenium Editado";

    public CM_Risk_Profile(WebDriver driver){
        this.driver = driver;
        this.browser = new SelectBrowser(driver);
        this.asserts = new Asserts(driver);
        this.basicControl = new BasicControl(driver);
        this.formsRiskProfile = new FormsRiskProfile(driver);
    }

    @Parameters("riskProfile")
    @Test
    public void crearRiskProfile(@Optional(newRiskProfile) String riskProfile){
        basicControl.btn_More(componente);
        formsRiskProfile.formCreateRisk(driver,riskProfile);
        asserts.assertSave();
    }

    @Parameters({"riskProfile","edit_RiskProfile"})
    @Test
    public void editRiskProfile(String riskProfile,String edit_RiskProfile) throws InterruptedException {
        basicControl.btn_More(componente);
        String xmlview = basicControl.getXmlview();
        driver.findElement(By.xpath("//div[@id='"+xmlview+"--listObject']//div[text()='"+riskProfile+"']")).click();
        formsRiskProfile.formEditRisk(driver,edit_RiskProfile);
        asserts.assertSave();
    }

    @Parameters("delete_RP")
    @Test
    public void eliminar_RP(@Optional(editRiskProfile) String delete_RP){
        basicControl.btn_More(componente);
        FormsControl.controlDelete(driver,delete_RP);
        String xpathMessage = "//span[@class='sapMText sapUiSelectable sapMTextMaxWidth sapMMsgBoxText']";
        asserts.assertDelete(xpathMessage);
    }

}