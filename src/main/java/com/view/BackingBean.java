package com.view;

/**
 *
 * @author jblom
 */
import java.io.IOException;
import model.UserBean;
import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Data;
import model.dao.AccountDAO;
import model.entity.Account;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.ExternalContext;
import javax.servlet.http.Cookie;
import model.dao.AccountAuthDAO;
import model.dao.PostDAO;
import model.entity.AccountAuth;
import model.entity.Post;
import org.apache.commons.lang3.RandomStringUtils;
import org.omnifaces.util.Messages;

@Data
@Named
@ViewScoped
public class BackingBean implements Serializable {

    @EJB
    private AccountDAO accountDAO;
    
    @EJB
    private PostDAO postDAO;

    @EJB
    private AccountAuthDAO accountAuthDAO;

    @Inject
    UserBean userBean;
    
    private String searchText;

    private boolean showSearchResult;

    private String searchResult;


    public void toggleSearchResult() {
        showSearchResult = !showSearchResult;
    }

    public void Search() throws IOException {
        
        if(searchText.equals("settings")) FacesContext.getCurrentInstance().getExternalContext().redirect("http://localhost:8080/DAT076_Web-applikationer/settings");
        else if(searchText.equals("settings")) FacesContext.getCurrentInstance().getExternalContext().redirect("http://localhost:8080/DAT076_Web-applikationer/settings");
        else if(accountDAO.findAccountMatchingUserName(searchText).getUserName().equals(searchText)) FacesContext.getCurrentInstance().getExternalContext().redirect("http://localhost:8080/DAT076_Web-applikationer/account_page?userName=" + searchText);
        else FacesContext.getCurrentInstance().getExternalContext().redirect("http://localhost:8080/DAT076_Web-applikationer/index");
    }
}
