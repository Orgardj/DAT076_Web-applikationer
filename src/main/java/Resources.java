
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.dao.AccountDAO;
import model.dao.PostDAO;
import model.dao.TopicDAO;
import model.entity.Account;
import model.entity.Post;
import model.entity.Section;
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
    @EJB
    private TopicDAO topicDAO;
    


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
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Post> GetAllPosts() {
        return postDAO.findAll();
    }
    
    //http://localhost:8080/lab3/resources/ws/createpost
    @POST
    @Path("/createpost/{topicId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response CreatePost(@PathParam("topicId") long topicId, Post post) {
        final Topic topic = topicDAO.find(topicId);
        final Account acc = accountDAO.find("john23");

        if (topic == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        post.setTimestamp(new Date());
        post.setUser(acc);
        post.setTopic(topic);

        postDAO.create(post);
        return Response.ok().build();
    }

    //@Consumes(MediaType.APPLICATION_JSON)
    //public void CreatePost(Post post) {

    //http://localhost:8080/lab3/resources/ws/editpost
    @POST
    @Path("/editpost/{postId}")
    public Response EditPost(@PathParam("postId") long postId, String editedString) {
        
        Post post = postDAO.find(postId);
        
        if (post == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        post.setText(editedString);
        
        postDAO.update(post);
        return Response.ok().build();
    }
    
    

}
