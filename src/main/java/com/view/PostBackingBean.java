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
    @Param
    private long id;

    private Post post;

    private Thread thread;

    private List<Post> posts;

    @PostConstruct
    private void init() {
        thread = getThread();
        posts = postDAO.findPostsMatchingUser(id);
        post = posts.get(0);
    }

    public Thread getThread() {
        return threadDAO.find(id);
    }

    public void createComment() {
        if(!enteredMessage.isEmpty()){
        postDAO.create(new Post(enteredMessage, new Date(), post.getUser(), post.getThread()));
        posts = postDAO.findPostsMatchingUser(id);
        }
    }

    public Thread getMatchingPost(Long pId) {
        return threadDAO.findThreadMatchingTId(pId);
    }

}
