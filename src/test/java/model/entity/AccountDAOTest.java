package model.entity;

import java.util.ArrayList;
import java.util.Date;
import javax.ejb.EJB;
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
public class AccountDAOTest {

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addClasses(AccountDAO.class, Account.class, Post.class, Topic.class,Section.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    
    @EJB
    private AccountDAO accountDAO;

    @Before
    public void init() {
        accountDAO.create(new Account("john23", "douche@hotmail.com","administrator","John","Douche","kakao20",new Date()));
       
    }

    @Test
    public void checkThatFindAccountMatchingNameMatchesCorrectly() {
        Assert.assertEquals("john23", accountDAO.findAccountMatchingUserName("john23").getUserName());
    }
    
    @Test
    public void checkThatFindAccountMatchingEmailMatchesCorrectly() {
        Assert.assertEquals("douche@hotmail.com", accountDAO.findAccountMatchingEmail("douche@hotmail.com").getEmail());
    }
            
    @Test
    public void checkThatFindAccountsMatchingRoleMatchesCorrectly() {
        Assert.assertEquals("administrator", new ArrayList<>(
                accountDAO.findAccountsMatchingRole("administrator")).get(0).getRole());
    }
    
    @After
    public void clear() {
        accountDAO.remove(accountDAO.find("john23"));
    }
}
