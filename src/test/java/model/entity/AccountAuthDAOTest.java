package model.entity;

import java.util.Date;
import javax.ejb.EJB;
import model.dao.AccountAuthDAO;
import org.junit.Assert;
import model.dao.AccountDAO;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class AccountAuthDAOTest {

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addClasses(AccountAuthDAO.class, AccountDAO.class, Account.class, Post.class, Thread.class, Category.class, AccountAuth.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @EJB
    private AccountAuthDAO accountAuthDAO;

    @EJB
    private AccountDAO accountDAO;

    @Before
    public void init() {
        Account account = new Account("john23", "kakao20", "douche@hotmail.com", "administrator", "John", "Douche", new Date(), 1);
        accountDAO.create(account);
        accountAuthDAO.create(new AccountAuth("selector", "validator", account));
    }

    @Test
    public void checkThatFindMatchingAccountAuthMatchesCorrectly() {
        Assert.assertEquals("john23", accountAuthDAO.findMatchingAccountAuth("selector", "validator").getAccount().getUserName());
    }

    @After
    public void clear() {
        accountAuthDAO.findAll().forEach(accountAuthDAO::remove);
        
        accountDAO.remove(accountDAO.find("john23"));
    }
}
