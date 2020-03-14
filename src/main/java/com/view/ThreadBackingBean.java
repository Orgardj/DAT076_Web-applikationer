package com.view;

/**
 *
 * @author Team J
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Data;
import model.dao.CategoryDAO;
import model.dao.PostDAO;
import model.dao.ThreadDAO;
import model.entity.Post;
import model.entity.Thread;
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
    private PostDAO postDAO;
    
    @Inject
    UserBean userBean;
    
    @Inject
    @Param
    private long id;

    private List<Thread> threads;

    public List<Thread> getMatchingThreads() {
        return threadDAO.findThreadsMatchingCategory(id);
    }
    
    public void createThread() {
        if (userBean.isLoggedIn() && !enteredTitle.isEmpty() && !enteredMessage.isEmpty()) {
            Thread thread = new Thread(enteredTitle, Long.valueOf(0), new Date(), categoryDAO.find(id), new ArrayList<>());
            threadDAO.create(thread);
            //Post should probably not be created here.
            postDAO.create(new Post(enteredMessage, new Date(), userBean.getAccount(), thread));
        }
    }
    
    public void removeThread(Long tId) {
        threadDAO.remove(threadDAO.find(tId));
    }
}
