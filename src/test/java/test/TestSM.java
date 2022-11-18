package test;

import Applications.ConfigurationManager.*;
import Applications.SecurityManager.SM_Role;
import Applications.SecurityManager.SM_User;
import Applications.SecurityManager.SM_UserGroup;
import Helpers.Asserts;
import Helpers.BasicControl;
import Helpers.DynamicScroll;
import Helpers.SelectBrowser;
import HomePage.Login;
import HomePage.LoginApplications;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestSM {

    private WebDriver driver;
    private String chosen_browser = "Chrome";

    private Login login;
    private DynamicScroll searchScrollElement;
    private SelectBrowser browser = new SelectBrowser(driver);
    private Actions action;
    private JavascriptExecutor js;
    private Asserts asserts;
    private BasicControl basicControl;
    private SM_Role role;
    private SM_User user;
    private SM_UserGroup userGroup;

    @BeforeTest
    public void setup() {
        browser.chooseBrowser(chosen_browser);
        driver = browser.getDriver();
        login = new Login(driver);
        asserts = new Asserts(driver);
        js = (JavascriptExecutor) driver;
        basicControl = new BasicControl(driver);
        searchScrollElement = new DynamicScroll(driver);
        role = new SM_Role(driver);
        user = new SM_User(driver);
        userGroup = new SM_UserGroup(driver);
        //Iniciamos Sesión
        login.loginPage();
        LoginApplications.loginSM(driver);
    }

    @Test
    public void testSecurityManager() throws InterruptedException {
        role.crearRole("Automated");
        role.editarRole("Automated","Auto");
        //user.crearUser("testAutomated");
        //userGroup.crearUserGroup("QA-Automated");
        //userGroup.editarUserGroup("QA-Automated","Automated");
    }

}
