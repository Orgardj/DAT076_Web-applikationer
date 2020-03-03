/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.view;

/**
 *
 * @author Team J
 */
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import lombok.Data;
import model.dao.ThreadDAO;
import model.entity.Thread;

@Data
@Named
@ViewScoped
public class ThreadBackingBean implements Serializable {

    @EJB
    private ThreadDAO threadDAO;

    private List<Thread> threads;

    public List<Thread> getMatchingThreads(Long tId) {
        return threadDAO.findThreadsMatchingCategory(tId);
    }
}
