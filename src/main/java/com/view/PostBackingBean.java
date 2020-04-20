package com.view;

/**
 *
 * @author Team J
 */
import model.UserBean;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Data;
import model.dao.PostDAO;
import model.dao.ThreadDAO;
import model.entity.Account;
import model.entity.Post;
import model.entity.Thread;
import org.omnifaces.cdi.Param;

@Data
@Named
@ViewScoped
public class PostBackingBean implements Serializable {

    private String enteredMessage;
    
    private String editedMessage;
    
    private boolean badEditCheck;

    @EJB
    private PostDAO postDAO;

    @EJB
    private ThreadDAO threadDAO;

    @Inject
    UserBean userBean;

    @Inject
    @Param
    private long id;

    private Thread thread;

    @PostConstruct
    private void init() {
        thread = getThread();
        threadDAO.incrementViewCount(id);
        editedMessage = "";
    }

    public List<Post> getMatchingPosts() {
        return postDAO.findPostsMatchingThread(id);
    }

    public Thread getThread() {
        return threadDAO.find(id);
    }

    public void createPost() {
        if (userBean.isLoggedIn() && !enteredMessage.isEmpty()) {
            postDAO.create(new Post(enteredMessage, new Date(), userBean.getAccount(), thread, "0"));
        }
    }

    public void removePost(Post post) {
        postDAO.remove(post);
    }
    
    public void editPost(Post post) {
        post.setText(editedMessage);
        post.setEditTimestamp("last edited by " + userBean.getAccount().getUserName() + ", " +  new Date());
        postDAO.update(post);
    }
    
    public List<Post> findPostsMatchingUserName(String userName) {
        return postDAO.findPostsMatchingUserName(userName);
    }
    
    public String viewPostText(Account account, Post post) {
        if(account.getRole().equals("deleted")) return "deleted user";
        else return post.getText();
    }
}
