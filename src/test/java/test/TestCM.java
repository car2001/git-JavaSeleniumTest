package test;

import Applications.ConfigurationManager.*;
import Forms.ConfigurationManager.FormsCounter;
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
    private DynamicScroll searchScrollElement;
    private SelectBrowser browser = new SelectBrowser(driver);
    private Actions action;
    private JavascriptExecutor js;
    private Asserts asserts;
    private BasicControl basicControl;
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
        asserts = new Asserts(driver);
        js = (JavascriptExecutor) driver;
        basicControl = new BasicControl(driver);
        searchScrollElement = new DynamicScroll(driver);
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
        crearComponetesCounter();
        editarComponentesCounter();
        eliminarComponentesCounter();
    }

    public void crearComponetesCounter() throws InterruptedException {
        counter.crearCounter("CM-1","1","5");
        ins.crearINS("INS-1","-","INS","CM-1");
        sla.crear_SLA("SLA-1");
        formUI.crear_FormUI("FormUI-1");
        performerProfile.crearPerformerProfile("Performer-1");
        notificationProfile.crearNotification("Notification-1");
        riskProfile.crearRiskProfile("Risk-1");
    }

    public void editarComponentesCounter() throws InterruptedException {
        counter.editarCounter("CM-1","CM-20","1", "14");
        ins.editarINS("INS-1","INS-20","-");
        sla.editar_SLA("SLA-1","SLA-20");
        formUI.editar_FormUI("FormUI-1","FormUI-20");
        performerProfile.editarPerformerProfile("Performer-1","Performer-20");
        notificationProfile.editarNotification("Notification-1","Notification-20");
        riskProfile.editRiskProfile("Risk-1","Risk-20");
    }

    public void eliminarComponentesCounter(){
        ins.eliminarINS("INS-20");
        counter.eliminarCounter("CM-20");
        sla.eliminar_SLA("SLA-20");
        formUI.eliminar_FormUI("FormUI-20");
        performerProfile.eliminarPerformerProfile("Performer-20");
        notificationProfile.eliminarNotification("Notification-20");
        riskProfile.eliminar_RP("Risk-20");
    }






}
