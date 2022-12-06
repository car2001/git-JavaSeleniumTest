package test;

import Applications.ConfigurationManager.*;
import Helpers.Asserts;
import Helpers.BasicControl;
import Helpers.DynamicScroll;
import Helpers.SelectBrowser;
import HomePage.Login;
import HomePage.LoginApplications;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;

public class TestCM {

    private WebDriver driver;
    private String chosen_browser = "Chrome";
    private Login login;
    private SelectBrowser browser = new SelectBrowser(driver);
    private CM_Counter counter;
    private CM_INS ins;
    private CM_SLA sla;
    private CM_Form_UI formUI;
    private CM_Performer_Profile performerProfile;
    private CM_Notification_Profile notificationProfile;
    private CM_Risk_Profile riskProfile;


    @BeforeTest
    public void setup(){
        browser.chooseBrowser(chosen_browser);
        driver = browser.getDriver();
        login = new Login(driver);
        counter = new CM_Counter(driver);
        ins = new CM_INS(driver);
        sla = new CM_SLA(driver);
        formUI = new CM_Form_UI(driver);
        performerProfile = new CM_Performer_Profile(driver);
        notificationProfile = new CM_Notification_Profile(driver);
        riskProfile = new CM_Risk_Profile(driver);
        //Iniciamos Sesión
        login.loginPage();
        LoginApplications.loginCM(driver);
    }



    @Test
    public void testConfigurationManager() throws InterruptedException {
        crearComponetesCM();
        editarComponentesCM();
        versionMayorComponentesCM();
        versionMenorComponentesCM();
        restoreVersionComponentesCM();
        eliminarComponentesCM();
    }

    public void crearComponetesCM() throws InterruptedException {
        counter.crearCounter("CM-1","1","5");
        ins.crearINS("INS-1","-","INS","CM-1");
        sla.crear_SLA("SLA-1");
        formUI.crear_FormUI("FormUI-1");
        performerProfile.crearPerformerProfile("Performer-1");
        notificationProfile.crearNotification("Notification-1");
        riskProfile.crearRiskProfile("Risk-1");
    }

    public void editarComponentesCM() throws InterruptedException {
        counter.editarCounter("CM-1","CM-20","1", "14");
        ins.editarINS("INS-1","INS-20","-");
        sla.editar_SLA("SLA-1","SLA-20");
        formUI.editar_FormUI("FormUI-1","FormUI-20");
        performerProfile.editarPerformerProfile("Performer-1","Performer-20");
        notificationProfile.editarNotification("Notification-1","Notification-20");
        riskProfile.editRiskProfile("Risk-1","Risk-20");
    }

    public void versionMayorComponentesCM() throws InterruptedException {
        sla.versionMayor_SLA("SLA-20","SLA-20 VMa");
        formUI.versionMayor_FormUI("FormUI-20","FormUI-20 VMa");
        performerProfile.versionMayor_PP("Performer-20","Performer-20 VMa");
        notificationProfile.versionMayor_NP("Notification-20","Notification-20 VMa");
        riskProfile.versionMayor_RP("Risk-20","Risk-20 VMa");
    }

    public void versionMenorComponentesCM() throws InterruptedException {
        sla.versionMenor_SLA("SLA-20 VMa","SLA-20 VMe");
        formUI.versionMenor_FormUI("FormUI-20 VMa","FormUI-20 VMe");
        performerProfile.versionMenor_PP("Performer-20 VMa","Performer-20 VMe");
        notificationProfile.versionMenor_NP("Notification-20 VMa","Notification-20 VMe");
        riskProfile.versionMenor_RP("Risk-20 VMa","Risk-20 VMe");
    }

    public void restoreVersionComponentesCM() throws InterruptedException {
        sla.restoreVersion_SLA("SLA-20 VMe","SLA-20 vR");
        formUI.versionRestore_FormUI("FormUI-20 VMe","FormUI-20 vR");
        performerProfile.restoreVersion_PP("Performer-20 VMe","Performer-20 vR");
        notificationProfile.restoreVersion_NP("Notification-20 VMe","Notification-20 vR");
        riskProfile.restoreVersion_RP("Risk-20 VMe","Risk-20 vR");
    }

    public void eliminarComponentesCM(){
        ins.eliminarINS("INS-20");
        counter.eliminarCounter("CM-20");
        sla.eliminar_SLA("SLA-20 vR");
        formUI.eliminar_FormUI("FormUI-20 vR");
        performerProfile.eliminarPerformerProfile("Performer-20 vR");
        notificationProfile.eliminarNotification("Notification-20 vR");
        riskProfile.eliminar_RP("Risk-20 vR");
    }






}
