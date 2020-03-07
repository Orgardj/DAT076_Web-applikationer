package com.view;

/**
 *
 * @author Team J
 */
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Data;
import model.dao.ThreadDAO;
import model.entity.Thread;
import org.omnifaces.cdi.Param;

@Data
@Named
@ViewScoped
public class ThreadBackingBean implements Serializable {

    @EJB
    private ThreadDAO threadDAO;

    @Inject
    @Param
    private long id;

    private List<Thread> threads;

    public List<Thread> getMatchingThreads() {
        return threadDAO.findThreadsMatchingCategory(id);
    }
}
