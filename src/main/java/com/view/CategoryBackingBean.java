package com.view;

/**
 *
 * @author Team J
 */
import model.UserBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Data;
import model.dao.CategoryDAO;
import model.entity.Category;
import model.entity.Thread;
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

    private String enteredTitle;
    private String enteredDescription;

    public List<Category> getAllCategories() {
        return categoryDAO.findAll();
    }

    public void createCategory() {
        if (!userBean.getAccount().getRole().equals("administrator")) {
            return;
        }
        categoryDAO.create(new Category(enteredTitle, enteredDescription, new ArrayList<>()));
    }

    public Category findMatchingCategory() {
        return categoryDAO.find(id);
    }

    public void removeCategory(Category category) {
        categoryDAO.remove(category);
    }

    public Thread latestThread(Category category) {
        return categoryDAO.latestThread(category);
    }
}
