package com.view;

/**
 *
 * @author Team J
 */
import model.UserBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Data;
import model.dao.AccountDAO;
import model.dao.CategoryDAO;
import model.dao.PostDAO;
import model.dao.ThreadDAO;
import model.entity.Post;
import model.entity.Thread;
import model.entity.Account;
import org.omnifaces.cdi.Param;

@Data
@Named
@ViewScoped
public class ThreadBackingBean implements Serializable {

    private String enteredTitle;

    private String enteredMessage;

    @EJB
    private ThreadDAO threadDAO;

    @EJB
    private CategoryDAO categoryDAO;
    
    @EJB
    private AccountDAO accountDAO;

    @EJB
    private PostDAO postDAO;

    @Inject
    UserBean userBean;

    @Inject
    @Param
    private long id;

    public List<Thread> getMatchingThreads() {
        return threadDAO.findThreadsMatchingCategory(id);
    }

    public void createThread() {
        if (userBean.isLoggedIn() && !enteredTitle.isEmpty() && !enteredMessage.isEmpty()) {
            Thread thread = new Thread(enteredTitle, Long.valueOf(0), new Date(), categoryDAO.find(id), new ArrayList<>());
            threadDAO.create(thread);
            postDAO.create(new Post(enteredMessage, new Date(), userBean.getAccount(), thread, "0"));
            /* 
            If we create an entity and add a child entity and then add another child entity to
            that one we are unable to remove the the "grand" parent entity until the application 
            had been restarted and the entity was refreshed. So we found a work around to do this
            after every creation instead. Not sure if this is a bug in hibernate or if it's something
            we have missed.
             */
            categoryDAO.refresh(categoryDAO.find(id));
            threadDAO.refresh(thread);
        }
    }

    public void removeThread(Thread thread) {
        threadDAO.remove(thread);
    }

    public Post firstPost(Thread thread) {
        return threadDAO.firstPost(thread);
    }

    public Post latestPost(Thread thread) {
        return threadDAO.latestPost(thread);
    }

    public String truncateComment(String comment) {
        if (comment.length() > 50) {
            return comment.substring(0, 50) + "...";
        }
        return comment;
    }
    
    public List<Thread> getFollowingThreads(Account user) {
        return user.getFollowingThreads();
    }
}
