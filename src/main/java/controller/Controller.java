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

    public void Search () throws IOException {
        /*String text = backingBean.Search();
        if(text.equals("index") || text.equals("settings"))FacesContext.getCurrentInstance().getExternalContext().redirect("http://localhost:8080/DAT076_Web-applikationer/" + backingBean.Search());
        else */
        
        FacesContext.getCurrentInstance().getExternalContext().redirect("http://localhost:8080/DAT076_Web-applikationer/index");
        
        
    }
    
        public void toggleSearchResult() {
        backingBean.toggleSearchResult();
    }
}
