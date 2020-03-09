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
import model.dao.AccountDAO;
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
    
    @EJB
    private AccountDAO accountDAO;
    
    @Inject
    @Param
    private long id;

    @Inject
    @Param
    private long id;

    private List<Thread> threads;

    public List<Thread> getMatchingThreads() {
        return threadDAO.findThreadsMatchingCategory(id);
    }
    
    public void createThread() {
        Thread thread = new Thread(enteredTitle, Long.valueOf(5), new Date(), categoryDAO.find(id), new ArrayList<>());
        threadDAO.create(thread);
        postDAO.create(new Post(enteredMessage, new Date(), accountDAO.findAccountMatchingUserName("john23"), thread));
    }
}
