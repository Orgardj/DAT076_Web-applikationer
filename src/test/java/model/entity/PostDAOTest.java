package model.entity;

import java.util.ArrayList;
import java.util.Date;
import javax.ejb.EJB;
import org.junit.Assert;
import model.dao.PostDAO;
import model.dao.AccountDAO;
import model.dao.CategoryDAO;
import model.dao.ThreadDAO;
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
public class PostDAOTest {

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addClasses(PostDAO.class, AccountDAO.class, ThreadDAO.class,
                        Post.class, Account.class, Thread.class, Category.class, CategoryDAO.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    @EJB
    private PostDAO postDAO;
    @EJB
    private AccountDAO accountDAO;
    @EJB
    private ThreadDAO threadDAO;
    @EJB
    private CategoryDAO categoryDAO;

    private String text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
    Thread thread;

    @Before
    public void init() {
        Account user = new Account("john23", "kakao20", "douche@hotmail.com", "administrator", "John", "Douche", new Date(), 1);
        accountDAO.create(user);

        Category category = new Category("Douche", "Im a douche", new ArrayList<>());
        categoryDAO.create(category);

        thread = new Thread("Data", Long.valueOf(5), new Date(), category, new ArrayList<>());
        threadDAO.create(thread);

        postDAO.create(new Post(text, new Date(), user, thread, "0"));
    }

    @Test
    public void checkThatFindPostMatchingUserNameMatchesCorrectly() {
        Assert.assertEquals(text, postDAO.findPostsMatchingUserName("john23").get(0).getText());
    }

    @Test
    public void checkThatFindPostMatchingTIdMatchesCorrectly() {
        Assert.assertEquals(text, postDAO.findPostsMatchingTId(thread.getTId()).get(0).getText());
    }

    @After
    public void clear() {
        postDAO.findAll().forEach((post) -> {
            postDAO.remove(post);
        });

        threadDAO.findAll().forEach((thread) -> {
            threadDAO.remove(thread);
        });

        categoryDAO.findAll().forEach((category) -> {
            categoryDAO.remove(category);
        });

        accountDAO.remove(accountDAO.find("john23"));
    }
}
