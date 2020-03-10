package model.entity;

import java.util.ArrayList;
import java.util.Date;
import javax.ejb.EJB;
import org.junit.Assert;
import model.dao.PostDAO;
import model.dao.AccountDAO;
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
public class PostDAOTest {

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                .addClasses(PostDAO.class, AccountDAO.class, TopicDAO.class, 
                        Post.class, Account.class, Topic.class,Section.class,SectionDAO.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    @EJB
    private PostDAO postDAO;
    @EJB
    private AccountDAO accountDAO;
    @EJB
    private TopicDAO topicDAO;
    @EJB
    private SectionDAO sectionDAO;
    @Before
    public void init() {
        Account user = new Account("john23", "douche@hotmail.com","administrator","John","Douche","kakao20",new Date());
        accountDAO.create(user);
        
        Section section = new Section(new ArrayList<>(), "Douche","Im a douche");
        sectionDAO.create(section);
        
        Topic topic = new Topic(section, new ArrayList<>(), "Data","sten",Long.valueOf(5),new Date());
        topicDAO.create(topic);
        
        postDAO.create(new Post(user, topic, "Scrum is best","yes it is",new Date()));
       
    }

    @Test
    public void checkThatFindPostMatchingNameMatchesCorrectly() {
        Assert.assertEquals("Scrum is best", new ArrayList<>(postDAO.findPostMatchingTitle("Scrum is best")).get(0).getTitle());
    }
    
    @After
    public void clear() {
        postDAO.findAll().forEach((post) -> {
            postDAO.remove(post);
        });
        
        topicDAO.findAll().forEach((topic) -> {
            topicDAO.remove(topic);
        });
        
        sectionDAO.findAll().forEach((section) -> {
            sectionDAO.remove(section);
        });
        
        accountDAO.remove(accountDAO.find("john23"));
    }
}
