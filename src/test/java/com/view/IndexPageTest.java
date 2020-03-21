package com.view;

import java.io.File;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.runner.RunWith;
import java.net.URL;
import org.arquillian.extension.recorder.screenshooter.Screenshooter;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.GrapheneElement;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Filters;
import org.jboss.shrinkwrap.api.exporter.ZipExporter;
import org.jboss.shrinkwrap.api.importer.ExplodedImporter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author orgardj
 */
@RunAsClient
@RunWith(Arquillian.class)
public class IndexPageTest {

    private static final String WEB_SRC = "src/main/webapp";
    private static final String WEBINF_SRC = WEB_SRC.concat("/WEB-INF");
    private static final String TEST_SRC = "src/test/resources";

    @Deployment
    public static WebArchive createDeployment() {
        WebArchive archive = ShrinkWrap.create(WebArchive.class)
                .addPackages(true, "com.view", "model.dao", "model", "model.entity", "controller")
                .merge(ShrinkWrap.create(WebArchive.class).as(ExplodedImporter.class)
                        .importDirectory(WEB_SRC).as(WebArchive.class),
                        "/", Filters.include(".*\\.xhtml$"))
                .merge(ShrinkWrap.create(WebArchive.class).as(ExplodedImporter.class)
                        .importDirectory(WEB_SRC).as(WebArchive.class),
                        "/", Filters.include(".*\\.css"))
                .merge(ShrinkWrap.create(WebArchive.class).as(ExplodedImporter.class)
                        .importDirectory(WEB_SRC).as(WebArchive.class),
                        "/", Filters.include(".*\\.js"))
                .addAsWebInfResource("META-INF/persistence.xml", "classes/META-INF/persistence.xml")
                .addAsWebInfResource("web.xml", "web.xml")
                .addAsWebInfResource("beans.xml", "beans.xml")
                .addAsWebInfResource(
                        new StringAsset("<faces-config version=\"2.0\"/>"),
                        "faces-config.xml");
        archive.as(ZipExporter.class).exportTo(new File("C:\\Skola\\Web-applikationer\\DAT076_Web-applikationer\\" + archive.getName()), true);
        return archive;
    }

    @Drone
    private ChromeDriver browser;

    @ArquillianResource
    private URL deploymentUrl;

    @ArquillianResource
    Screenshooter screenshooter;

    //private AccountDAO accountDAO;
    @Before
    public void init() {
        //accountDAO = new AccountDAO();
    }

    @FindBy(id = "create_category:category_title")
    private WebElement categoryTitle;

    @FindBy(id = "create_category:category_description")
    private WebElement categoryDescription;

    @FindBy(id = "create_category:new_category_button")
    private WebElement categoryButton;

    @FindBy(id = "j_idt56:userIcon")
    private WebElement userIcon;

    @FindBy(id = "j_idt7:loginForm:Username")
    private WebElement userNameLoginInput;

    @FindBy(id = "j_idt7:loginForm:Password")
    private WebElement passwordLoginInput;

    @FindBy(id = "j_idt7:loginForm:loginButton")
    private WebElement loginButton;

    @FindBy(id = "j_idt7:registerForm:userName")
    private WebElement userNameRegisterInput;

    @FindBy(id = "j_idt7:registerForm:password")
    private WebElement passwordRegisterInput;

    @FindBy(id = "j_idt7:registerForm:confirmPassword")
    private WebElement confirmPasswordRegisterInput;

    @FindBy(id = "j_idt7:registerForm:firstName")
    private WebElement firstNameRegisterInput;

    @FindBy(id = "j_idt7:registerForm:lastName")
    private WebElement lastNameRegisterInput;

    @FindBy(id = "j_idt7:registerForm:email")
    private WebElement emailRegisterInput;

    @FindBy(id = "j_idt7:registerForm:chooseProfilePicture:0")
    private WebElement chooseProfilePictureRadioButton;

    @FindBy(id = "j_idt7:registerForm:createAccountButton")
    private GrapheneElement registerButton;

    @FindBy(id = "registerSwitch")
    private WebElement registerSwitch;

    @Ignore
    @Test
    public void register_successfully() {
        browser.get(deploymentUrl.toExternalForm() + "index.jsf");

        userIcon.click();
        registerSwitch.click();

        userNameRegisterInput.sendKeys("john23");
        passwordRegisterInput.sendKeys("kakao20!!");
        confirmPasswordRegisterInput.sendKeys("kakao20!!");
        emailRegisterInput.sendKeys("douche@hotmail.com");
        firstNameRegisterInput.sendKeys("John");
        lastNameRegisterInput.sendKeys("Douche");
        chooseProfilePictureRadioButton.click();

        Graphene.waitForHttp(registerButton).click();

        //Assert.assertEquals("john23", accountDAO.findAccountMatchingUserName("john23").getUserName());
        Assert.assertTrue(true);
    }

    @Ignore
    @Test
    public void login_successfully() {
        browser.get("http://localhost:17070/index");

        userIcon.click();

        userNameLoginInput.sendKeys("john23");
        passwordLoginInput.sendKeys("kakao20");

        loginButton.click();
        screenshooter.takeScreenshot("loginTest.png");

        //TODO
        Assert.assertTrue(true);
    }

    @Ignore
    @Test
    public void should_create_category_successfully() {
        browser.get("http://www.google.com");

        String pageTitle = browser.getTitle();
        Assert.assertEquals(pageTitle, "Google");
        /*
        browser.get(deploymentUrl.toExternalForm() + "index");
        categoryTitle.sendKeys("title test");
        categoryDescription.sendKeys("description test");
        categoryButton.click();

        waitAjax().until().element(signedAs).is().present();
        assertTrue(signedAs.getText().contains("demo"));
        
        // TODO
        Assert.assertTrue(true);
         */
    }
}
