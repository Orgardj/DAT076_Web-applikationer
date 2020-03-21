package model.entity;

import java.util.ArrayList;
import java.util.Date;
import javax.ejb.EJB;
import model.dao.AccountDAO;
import org.junit.Assert;
import model.dao.PostDAO;
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
public class ThreadDAOTest {

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addClasses(PostDAO.class, AccountDAO.class, ThreadDAO.class,
                        Post.class, Account.class, Thread.class, Category.class, CategoryDAO.class, AccountAuth.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @EJB
    private ThreadDAO threadDAO;

    @EJB
    private CategoryDAO categoryDAO;

    @EJB
    private PostDAO postDAO;

    @EJB
    private AccountDAO accountDAO;

    private Category category;
    private Thread thread;
    private Post first_post;
    private Post last_post;

    @Before
    public void init() {
        Account user = new Account("john23", "kakao20", "douche@hotmail.com", "administrator", "John", "Douche", new Date(), 1);
        accountDAO.create(user);

        category = new Category("Douche", "Im a douche", new ArrayList<>());
        categoryDAO.create(category);

        thread = new Thread("Data", Long.valueOf(0), new Date(), category, new ArrayList<>());
        threadDAO.create(thread);

        postDAO.create(new Post("first post", new Date(), user, thread, "0"));
        postDAO.create(new Post("last post", new Date(), user, thread, "0"));
    }

    @Test
    public void checkThatFindThreadMatchingTitleMatchesCorrectly() {
        Assert.assertEquals("Data", threadDAO.findThreadMatchingTitle("Data").getTitle());
    }

    @Test
    public void checkThatFindThreadsMatchingCategoryMatchesCorrectly() {
        Assert.assertEquals("Douche", threadDAO.findThreadsMatchingCategory(category.getCId()).get(0).getCategory().getName());
    }

    @Test
    public void checkThatFindThreadMatchingTIdMatchesCorrectly() {
        Assert.assertEquals(thread.getTId(), threadDAO.findThreadMatchingTId(thread.getTId()).getTId());
    }

    @Test
    public void checkThatFirstPostMatchesCorrectly() {
        Assert.assertEquals(first_post, threadDAO.firstPost(thread));
    }

    @Test
    public void checkThatLastPostMatchesCorrectly() {
        Assert.assertEquals(last_post, threadDAO.latestPost(thread));
    }

    @After
    public void clear() {
        postDAO.findAll().forEach(postDAO::remove);

        threadDAO.findAll().forEach(threadDAO::remove);

        categoryDAO.findAll().forEach(categoryDAO::remove);

        accountDAO.remove(accountDAO.find("john23"));
    }
}
