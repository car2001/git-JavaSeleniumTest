package test.user_options;

import Applications.UserOptions.*;
import Helpers.BasicControl;
import Helpers.ChargePopPup;
import Helpers.SelectBrowser;
import HomePage.Login;
import HomePage.LoginApplications;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;


public class TestUserOptions {

    private WebDriver driver;
    private String chosen_browser = "Chrome";
    private Login login;
    private LoginApplications loginApplications;
    private SelectBrowser browser = new SelectBrowser(driver);
    private BasicControl basicControl;
    private About about;
    private MyProfile myProfile;
    private Preferences preferences;
    private ChangePassword changePassword;
    private MyLocks myLocks;
    private MyTimeSheet myTimeSheet;
    private WebDriverWait wait;

    @BeforeTest
    public void setup(){
        browser.chooseBrowser(chosen_browser);
        driver = browser.getDriver();
        login = new Login(driver);
        basicControl = new BasicControl(driver);
        about = new About(driver);
        myProfile = new MyProfile(driver);
        preferences = new Preferences(driver);
        changePassword = new ChangePassword(driver);
        myLocks = new MyLocks(driver);
        myTimeSheet = new MyTimeSheet(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        //Iniciamos Sesión
        login.loginPage();
    }

    @Test
    public void testUserOptions() throws InterruptedException {
        basicControl.waitApplication();
        //Pagina de todas las Apps
        userPageApps();

        String[] apps = {"Security Manager","Organizational Structure Manager","Collaboration Workspace",
                "Process Manager","Configuration Manager","Data Entity Manager","Data Record Manager","Integration Studio",
                "Technical Administrator","Release Manager","Analytics","InBUPLAT","Project Manager"};


        for (String app : apps) {
            login.loginPage();
            loginApps(app);
            userPage(app);
        }

    }


    public void userPageApps() throws InterruptedException {
        basicControl.btnUserApps("About","Acerca de");
        about.checkAbout();
        basicControl.btnUserApps("My Profile","Mi Perfil");
        myProfile.checkMyProfile();
        basicControl.waitApplication();
        basicControl.btnUserApps("Preferences","Preferencias");
        preferences.checkMyPreferences();
        basicControl.waitApplication();
        basicControl.btnUserApps("Change Password","Cambiar Contraseña");
        changePassword.checkChangePassword();
        basicControl.waitApplication();
        basicControl.btnUserApps("My Locks","Mis Bloqueos");
        myLocks.checkMyLocks();
        basicControl.waitApplication();
        basicControl.btnUserApps("My TimeSheet","Mi Hoja de Tiempos");
        myTimeSheet.checkMyTimeSheet();
        basicControl.btnUserApps("Disconnect","Desconectar");
    }

    public void userPage(String app) throws InterruptedException {
        basicControl.btnUser("About","Acerca de");
        about.checkAbout();
        basicControl.btnUser("My Profile","Mi Perfil");
        myProfile.checkMyProfile();
        applicaciones(app);
        basicControl.btnUser("Preferences","Preferencias");
        preferences.checkMyPreferences();
        applicaciones(app);
        basicControl.btnUser("Change Password","Cambiar Contraseña");
        changePassword.checkChangePassword();
        applicaciones(app);
        basicControl.btnUser("My Locks","Mis Bloqueos");
        myLocks.checkMyLocks();
        basicControl.btnUser("My TimeSheet","Mi Hoja de Tiempos");
        myTimeSheet.checkMyTimeSheet();
        applicaciones(app);
        basicControl.btnUser("Disconnect","Desconectar");
    }

    public void applicaciones(String app){
        WebElement blockLayer = driver.findElement(By.id("sap-ui-blocklayer-popup"));
        switch (app){
            case "Security Manager":
                blockLayer = driver.findElement(By.id("sap-ui-blocklayer-popup"));
                while (blockLayer.getAttribute("style").contains("visible;")){
                    blockLayer = driver.findElement(By.id("sap-ui-blocklayer-popup"));
                }
                break;
            case "Organizational Structure Manager":
                blockLayer = driver.findElement(By.id("sap-ui-blocklayer-popup"));
                while (blockLayer.getAttribute("style").contains("visible;")){
                    blockLayer = driver.findElement(By.id("sap-ui-blocklayer-popup"));
                }
                break;
            case "Collaboration Workspace":
                blockLayer = driver.findElement(By.id("sap-ui-blocklayer-popup"));
                while (blockLayer.getAttribute("style").contains("visible;")){
                    blockLayer = driver.findElement(By.id("sap-ui-blocklayer-popup"));
                }
                break;

            case "Process Manager":
                blockLayer = driver.findElement(By.id("sap-ui-blocklayer-popup"));
                while (blockLayer.getAttribute("style").contains("visible;")){
                    blockLayer = driver.findElement(By.id("sap-ui-blocklayer-popup"));
                }
                break;
            case "Configuration Manager":
                wait.until(ExpectedConditions.elementToBeClickable(By.id("navListItem-navList-0-a")));
                blockLayer = driver.findElement(By.id("sap-ui-blocklayer-popup"));
                while (blockLayer.getAttribute("style").contains("visible;")){
                    blockLayer = driver.findElement(By.id("sap-ui-blocklayer-popup"));
                }
                break;

            case "Data Entity Manager":
                blockLayer = driver.findElement(By.id("sap-ui-blocklayer-popup"));
                while (blockLayer.getAttribute("style").contains("visible;")){
                    blockLayer = driver.findElement(By.id("sap-ui-blocklayer-popup"));
                }
                break;
            case "Data Record Manager":
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@id,'--searchText-search')]")));
                break;
            case  "Integration Studio":
                blockLayer = driver.findElement(By.id("sap-ui-blocklayer-popup"));
                while (blockLayer.getAttribute("style").contains("visible;")){
                    blockLayer = driver.findElement(By.id("sap-ui-blocklayer-popup"));
                }
                break;
            case  "Technical Administrator":
                wait.until(ExpectedConditions.elementToBeClickable(By.id("navListItem-navList-0-a")));
                blockLayer = driver.findElement(By.id("sap-ui-blocklayer-popup"));
                while (blockLayer.getAttribute("style").contains("visible;")){
                    blockLayer = driver.findElement(By.id("sap-ui-blocklayer-popup"));
                }
                break;
            case  "Release Manager":
                blockLayer = driver.findElement(By.id("sap-ui-blocklayer-popup"));
                while (blockLayer.getAttribute("style").contains("visible;")){
                    blockLayer = driver.findElement(By.id("sap-ui-blocklayer-popup"));
                }
                break;
            case  "Analytics":
                wait.until(ExpectedConditions.elementToBeClickable(By.id("navListItem-navList-0-a")));
                blockLayer = driver.findElement(By.id("sap-ui-blocklayer-popup"));
                while (blockLayer.getAttribute("style").contains("visible;")){
                    blockLayer = driver.findElement(By.id("sap-ui-blocklayer-popup"));
                }
                break;
            case  "InBUPLAT":
                wait.until(ExpectedConditions.elementToBeClickable(By.id("navListItem-navList-0-a")));
                blockLayer = driver.findElement(By.id("sap-ui-blocklayer-popup"));
                while (blockLayer.getAttribute("style").contains("visible;")){
                    blockLayer = driver.findElement(By.id("sap-ui-blocklayer-popup"));
                }
                break;
            case  "Project Manager":
                wait.until(ExpectedConditions.elementToBeClickable(By.id("navListItem-navList-0-a")));
                blockLayer = driver.findElement(By.id("sap-ui-blocklayer-popup"));
                while (blockLayer.getAttribute("style").contains("visible;")){
                    blockLayer = driver.findElement(By.id("sap-ui-blocklayer-popup"));
                }
                break;
        }
    }


    public void loginApps(String app){

        switch (app){
            case "Security Manager":
                loginApplications.loginSM(driver);
                break;
            case "Organizational Structure Manager":
                loginApplications.loginOSM(driver);
                break;
            case "Collaboration Workspace":
                loginApplications.loginColl(driver);
                break;

            case "Process Manager":
                loginApplications.loginPM(driver);
                break;
            case "Configuration Manager":
                loginApplications.loginCM(driver);
                break;

            case "Data Entity Manager":
                loginApplications.loginDEM(driver);
                break;
            case "Data Record Manager":
                loginApplications.loginDRM(driver);
                break;
            case "Integration Studio":
                loginApplications.loginIS(driver);
                break;
            case  "Technical Administrator":
                loginApplications.loginTA(driver);
                break;
            case  "Release Manager":
                loginApplications.loginRM(driver);
                break;
            case  "Analytics":
                loginApplications.loginANL(driver);
                break;
            case  "InBUPLAT":
                loginApplications.loginInBuplat(driver);
                break;
            case  "Project Manager":
                loginApplications.loginPRJM(driver);
                break;
        }
    }

}
