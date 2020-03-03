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
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import lombok.Data;
import model.dao.SectionDAO;
import model.entity.Section;

@Data
@Named
@ViewScoped
public class SectionBackingBean implements Serializable {

    @EJB    
    private SectionDAO sectionDAO;
    
    private List<Section> sections;

    @PostConstruct
    private void init() {
        sections = new ArrayList<>(sectionDAO.findAll());
    }
}
