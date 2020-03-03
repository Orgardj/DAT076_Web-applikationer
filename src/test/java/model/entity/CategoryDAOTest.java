package model.entity;

import java.util.ArrayList;
import javax.ejb.EJB;
import org.junit.Assert;
import model.dao.CategoryDAO;
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
public class CategoryDAOTest {

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addClasses(CategoryDAO.class, Category.class, Thread.class, Account.class, Post.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    @EJB
    private CategoryDAO categoryDAO;

    @Before
    public void init() {
        categoryDAO.create(new Category("Douche", "Im a douche", new ArrayList<>()));
    }

    @Test
    public void checkThatFindCategoryMatchingNameMatchesCorrectly() {
        Assert.assertEquals("Douche", categoryDAO.findCategoryMatchingName("Douche").getName());
    }

    @Test
    public void checkThatUpdateCategoryUpdatesCorrectly() {
        categoryDAO.update(new Category("Not douche", "Im not a douche", new ArrayList<>()));
        Assert.assertEquals("Not douche", categoryDAO.findCategoryMatchingName("Not douche").getName());
    }

    @After
    public void clear() {
        categoryDAO.findAll().forEach((category) -> {
            categoryDAO.remove(category);
        });
    }
}
