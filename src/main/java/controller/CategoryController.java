package controller;

import com.view.CategoryBackingBean;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.entity.Category;
import org.omnifaces.cdi.Push;
import org.omnifaces.cdi.PushContext;

@Named
@RequestScoped
public class CategoryController implements Serializable {

    @Inject
    private CategoryBackingBean categoryBackingBean;

    @Inject
    @Push(channel = "main")
    private PushContext push;

    public void createCategory() {
        categoryBackingBean.createCategory();
        push.send("update_categories");
    }

    public void removeCategory(Category category) {
        categoryBackingBean.removeCategory(category);
        push.send("update_categories");
    }
}
