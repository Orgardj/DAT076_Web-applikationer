package controller;

import com.view.AccountBackingBean;
import com.view.BackingBean;
import java.io.IOException;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import model.entity.Account;
import org.omnifaces.cdi.Push;
import org.omnifaces.cdi.PushContext;

@Named
@RequestScoped
public class Controller implements Serializable {

    @Inject
    private BackingBean backingBean;

    public void search() {
        backingBean.search();
    }

    public void toggleSearchResult() {
        if (!backingBean.getSearchText().equals("")) {
            search();

        }
        if (backingBean.getSearchResult() != null) {
            backingBean.toggleSearchResult();
        }

    }
}
