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
    private Asserts asserts;
    private BasicControl basicControl;
    private FormsRiskProfile formsRiskProfile;

    private final String componente = "Risk Profiles";

    public CM_Risk_Profile(WebDriver driver){
        this.driver = driver;
        this.asserts = new Asserts(driver);
        this.basicControl = new BasicControl(driver);
        this.formsRiskProfile = new FormsRiskProfile(driver);
    }

    @Test
    public void crearRiskProfile(String riskProfile){
        basicControl.btn_More(componente);
        formsRiskProfile.formCreateRisk(riskProfile);
        asserts.assertSave();
    }

    @Test
    public void editRiskProfile(String riskProfile,String edit_RiskProfile) throws InterruptedException {
        basicControl.btn_More(componente);
        String xmlview = basicControl.getXmlview();
        driver.findElement(By.xpath("//div[@id='"+xmlview+"--listObject']//div[text()='"+riskProfile+"']")).click();
        formsRiskProfile.formEditRisk(edit_RiskProfile);
        asserts.assertSave();
    }

    @Test
    public void versionMayor_RP(String riskProfile,String vMayorRiskProfile) throws InterruptedException {
        basicControl.btn_More(componente);
        String xmlview = basicControl.getXmlview();
        driver.findElement(By.xpath("//div[text()='" + riskProfile + "']")).click();
        formsRiskProfile.MayorVersionRisk(vMayorRiskProfile);
        asserts.assertSave();
    }

    @Test
    public void versionMenor_RP(String riskProfile,String vMenorRiskProfile) throws InterruptedException {
        basicControl.btn_More(componente);
        String xmlview = basicControl.getXmlview();
        driver.findElement(By.xpath("//div[text()='" + riskProfile + "']")).click();;
        formsRiskProfile.MenorVersionRisk(vMenorRiskProfile);
        asserts.assertSave();
    }

    @Test
    public void restoreVersion_RP(String riskProfile,String vRestoreRiskProfile) throws InterruptedException {
        basicControl.btn_More(componente);
        String xmlview = basicControl.getXmlview();
        driver.findElement(By.xpath("//div[text()='" + riskProfile + "']")).click();
        String versionActual = driver.findElement(By.xpath("//input[contains(@id,'--txtVersion-inner')]")).getAttribute("value");
        formsRiskProfile.restoreVersionRisk(vRestoreRiskProfile,versionActual);
        asserts.assertSave();
    }

    @Test
    public void eliminar_RP(String delete_RP){
        basicControl.btn_More(componente);
        FormsControl.controlDelete(driver,delete_RP);
        String xpathMessage = "//span[@class='sapMText sapUiSelectable sapMTextMaxWidth sapMMsgBoxText']";
        asserts.assertDelete(xpathMessage);
    }

}