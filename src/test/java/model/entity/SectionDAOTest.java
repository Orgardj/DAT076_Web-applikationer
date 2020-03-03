package model.entity;

import java.util.ArrayList;
import javax.ejb.EJB;
import org.junit.Assert;
import model.dao.SectionDAO;
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
public class SectionDAOTest {

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addClasses(SectionDAO.class, Section.class,Topic.class,Account.class,Post.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    @EJB
    private SectionDAO sectionDAO;

    @Before
    public void init() {
        
        sectionDAO.create(new Section(new ArrayList<>(), "Douche","Im a douche"));
       
    }

    @Test
    public void checkThatFindSectionMatchingNameMatchesCorrectly() {
        Assert.assertEquals("Douche", sectionDAO.findSectionMatchingName("Douche").getName());
    }
    
    @Test
    public void checkThatUpdateSectionUpdatesCorrectly() {
        sectionDAO.update(new Section(new ArrayList<>(), "Not douche","Im not a douche"));
        Assert.assertEquals("Not douche", sectionDAO.findSectionMatchingName("Not douche").getName());
    }
    
    @After
    public void clear() {
        sectionDAO.findAll().forEach((section) -> {
            sectionDAO.remove(section);
        });
    }
}
