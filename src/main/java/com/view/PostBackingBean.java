/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.view;

/**
 *
 * @author jblom
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
import model.dao.TopicDAO;
import model.entity.Post;
import model.entity.Topic;
import org.omnifaces.cdi.Param;

@Data
@Named
@ViewScoped
public class PostBackingBean implements Serializable {

    private String enteredMessage;

    @EJB
    private PostDAO postDAO;

    @EJB
    private TopicDAO topicDAO;

    @Inject
    @Param
    private long id;

    private Post post;

    private List<Post> posts;

    /*public Post getActivePost() {
        return postDAO.find(id);
    }*/
    @PostConstruct
    private void init() {
        posts = postDAO.findPostsMatchingPId(id);
        post = posts.get(0);
    }

    public Topic getTopic() {
        return topicDAO.find(id);
    }

    public void createComment() {
        postDAO.create(new Post(post.getUser(), post.getTopic(), "Scrum is not best", enteredMessage, new Date()));
        posts = postDAO.findPostsMatchingPId(id);
    }

    public Topic getMatchingTopic(Long tId) {
        return topicDAO.findTopicMatchingId(tId);
    }

}
