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
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import lombok.Data;
import model.dao.TopicDAO;
import model.entity.Topic;

@Data
@Named
@ViewScoped
public class TopicBackingBean implements Serializable {

    @EJB    
    private TopicDAO topicDAO;
    
    private List<Topic> topics;

    public List<Topic> getMatchingTopics(Long sId){
        return topicDAO.findTopicsMatchingSection(sId);
    }
}
