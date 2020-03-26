
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Application;
import model.dao.AccountDAO;
import model.dao.PostDAO;
import model.entity.Account;
import model.entity.Post;
import model.entity.Topic;

/*
 * material-ui, react-scroll, react-router-dom, react-recaptcha. jquery
 */
/**
 *
 * @author davherm
 */
@Path("/ws")
@ApplicationPath("resources")
public class Resources extends Application{
    
   //kraschar om man har med
    @EJB
    private AccountDAO accountDAO;
    @EJB
    private PostDAO postDAO;
    //account.create(new Account("john23", "douche@hotmail.com","administrator","John","Douche","kakao20",new Date()));


    //http://localhost:8080/lab3/resources/ws/sayhello
    @GET
    @Path("/sayhello")
    public String GetHelloMSG() {
        return "dh worhhhhhhhld";
    }
    
    
    @GET
    @Path("/users")
    public List<Account> names() {
        return accountDAO.findAll();
        //return account.count();
//        long i = account.count();
//        System.out.println("" + i);
//        return new ArrayList<>();
    }
    
    //http://localhost:8080/lab3/resources/ws/getallposts
    @GET
    @Path("/getallposts")
    public List<Post> GetAllPosts() {
        return postDAO.findAll();
    }
    
    //http://localhost:8080/lab3/resources/ws/createpost
    /*@GET
    @Path("/sayhello")
    public void CreatePost(Account acc, Topic topic, String text, String title) {
        postDAO.create(new Post(acc,topic,title,text,new Date()));
    }*/
    
    //http://localhost:8080/lab3/resources/ws/editpost
    /*@GET
    @Path("/sayhello")
    public void EditPost(String editedString, Post post) {
        post.setText(editedString);
        postDAO.update(post);
    }*/
    
    

}
