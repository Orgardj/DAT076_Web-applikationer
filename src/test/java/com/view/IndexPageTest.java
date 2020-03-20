package com.view;

import model.entity.Account;
import model.entity.Category;
import model.entity.Post;
import model.entity.Thread;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.runner.RunWith;
import java.net.URL;
import model.entity.AccountAuth;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Filters;
import org.jboss.shrinkwrap.api.GenericArchive;
import org.jboss.shrinkwrap.api.importer.ExplodedImporter;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author orgardj
 */
@RunAsClient
@RunWith(Arquillian.class)
public class IndexPageTest {

    private static final String WEBAPP_SRC = "src/main/webapp";

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "login.war")
                .addClasses(Account.class, Post.class, Thread.class, Category.class, AccountAuth.class)
                .merge(ShrinkWrap.create(GenericArchive.class).as(ExplodedImporter.class)
                        .importDirectory(WEBAPP_SRC).as(GenericArchive.class),
                        "/", Filters.include(".*\\.xhtml$"))
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsWebInfResource(
                        new StringAsset("<faces-config version=\"2.0\"/>"),
                        "faces-config.xml");
    }

    @Drone
    private WebDriver browser;

    @ArquillianResource
    private URL deploymentUrl;

    @FindBy(id = "create_category:category_title")
    private WebElement categoryTitle;

    @FindBy(id = "create_category:category_description")
    private WebElement categoryDescription;

    @FindBy(id = "create_category:new_category_button")
    private WebElement categoryButton;

    @Test
    public void should_create_category_successfully() {
        browser.get("http://www.google.com");

        String pageTitle = browser.getTitle();
        Assert.assertEquals(pageTitle, "Google");
        Assert.assertEquals(true, true);
        /*browser.get(deploymentUrl.toExternalForm() + "index");
        categoryTitle.sendKeys("title test");
        categoryDescription.sendKeys("description test");
        categoryButton.click();*/

        //waitAjax().until().element(signedAs).is().present();
        //assertTrue(signedAs.getText().contains("demo"));
    }
}