package com.view;

/**
 *
 * @author Team J
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import lombok.Data;
import model.dao.CategoryDAO;
import model.entity.Category;

@Data
@Named
@ViewScoped
public class CategoryBackingBean implements Serializable {

    @EJB
    private CategoryDAO categoryDAO;

    private List<Category> categories;

    @PostConstruct
    private void init() {
        categories = new ArrayList<>(categoryDAO.findAll());
    }
}
