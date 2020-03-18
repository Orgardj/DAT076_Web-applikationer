package model.entity;

import java.util.ArrayList;
import java.util.Date;
import javax.ejb.EJB;
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
                .addClasses(ThreadDAO.class, Thread.class, Post.class, Account.class, PostDAO.class, Category.class, CategoryDAO.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @EJB
    private ThreadDAO threadDAO;
    @EJB
    private CategoryDAO categoryDAO;

    Category category;
    Thread thread;

    @Before
    public void init() {
        category = new Category("Douche", "Im a douche", new ArrayList<>());
        categoryDAO.create(category);

        thread = new Thread("Data", Long.valueOf(5), new Date(), category, new ArrayList<>());
        threadDAO.create(thread);
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

    @After
    public void clear() {
        threadDAO.findAll().forEach((thread) -> {
            threadDAO.remove(thread);
        });

        categoryDAO.findAll().forEach((category) -> {
            categoryDAO.remove(category);
        });
    }
}
