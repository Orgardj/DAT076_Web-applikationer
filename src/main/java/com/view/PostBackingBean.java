package com.view;

/**
 *
 * @author Team J
 */
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
import model.entity.Post;
import model.entity.Thread;
import model.entity.UserBean;
import org.omnifaces.cdi.Param;

@Data
@Named
@ViewScoped
public class PostBackingBean implements Serializable {

    private String enteredMessage;

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

    private List<Post> posts;

    @PostConstruct
    private void init() {
        thread = getThread();
        posts = postDAO.findPostMatchingTId(id);
    }

    public Thread getThread() {
        return threadDAO.find(id);
    }

    public void createComment() {
        if (!enteredMessage.isEmpty()) {
            postDAO.create(new Post(enteredMessage, new Date(), userBean.getAccount(), thread));
            posts = postDAO.findPostsMatchingUser(id);
        }
    }
}
