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
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Data;
import model.dao.CategoryDAO;
import model.entity.Category;
import model.entity.UserBean;
import org.omnifaces.cdi.Param;

@Data
@Named
@ViewScoped
public class CategoryBackingBean implements Serializable {

    @Inject
    UserBean userBean;

    @EJB
    private CategoryDAO categoryDAO;

    @Inject
    @Param
    private long id;

    private List<Category> categories;

    private String enteredTitle;
    private String enteredDescription;

    @PostConstruct
    private void init() {
        categories = new ArrayList<>(categoryDAO.findAll());
    }

    public void createCategory() {
        if (!userBean.getAccount().getRole().equals("administrator")) {
            return;
        }
        categoryDAO.create(new Category(enteredTitle, enteredDescription, new ArrayList<>()));
        categories = categoryDAO.findAll();
    }
    
    public Category findMatchingCategory() {
        return categoryDAO.find(id);
    }
}
