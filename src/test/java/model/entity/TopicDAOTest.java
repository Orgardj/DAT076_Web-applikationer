package model.entity;

import java.util.ArrayList;
import java.util.Date;
import javax.ejb.EJB;
import org.junit.Assert;
import model.dao.PostDAO;
import model.dao.SectionDAO;
import model.dao.TopicDAO;
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
public class TopicDAOTest {

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addClasses(TopicDAO.class, Topic.class, Post.class,Account.class,PostDAO.class,Section.class,SectionDAO.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    @EJB
    private TopicDAO topicDAO;
    @EJB
    private SectionDAO sectionDAO;
    @Before
    public void init() {
        
        Section section = new Section(new ArrayList<>(), "Douche","Im a douche");
        sectionDAO.create(section);
        
        topicDAO.create(new Topic(section,new ArrayList<>(),"Data","sten",Long.valueOf(5),new Date()));
    
    }

    @Test
    public void checkThatFindTopicMatchingTitleMatchesCorrectly() {
        Assert.assertEquals("Data", new ArrayList<>(topicDAO.findTopicMatchingTitle("Data")).get(0).getTitle());
    }
    
    @After
    public void clear() {
        topicDAO.findAll().forEach((topic) -> {
            topicDAO.remove(topic);
        });
        
        sectionDAO.findAll().forEach((section) -> {
            sectionDAO.remove(section);
        });
    }

}
