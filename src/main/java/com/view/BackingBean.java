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
import model.dao.ThreadDAO;
import model.dao.CategoryDAO;
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
    private ThreadDAO threadDAO;
    
    @EJB
    private CategoryDAO categoryDAO;

    @EJB
    private AccountAuthDAO accountAuthDAO;

    @Inject
    UserBean userBean;
    
    private String searchText;

    private boolean showSearchResult;
    
    private String searchResult;

    public void search(){
       
        if(searchText.equals("index")) searchResult = "index";
        else if(searchText.equals("register")) searchResult = "register";
        else if(searchText.equals("login")) searchResult = "login";
        else if(accountDAO.findAccountMatchingUserName(searchText) != null) {
            searchResult = "account_page?userName=" + searchText; 
        }
        else if(categoryDAO.findCategoryMatchingName(searchText) != null) {
            searchResult = "category?id=" + categoryDAO.findCategoryMatchingName(searchText).getCId(); 
        }
        else if(threadDAO.findThreadMatchingTitle(searchText) != null) {
            searchResult = "thread?id=" + threadDAO.findThreadMatchingTitle(searchText).getTId(); 
        }
        else {
            searchResult = "index";
            searchText = "nothing found for: " + searchText;
        }
        
    }
    
    public void toggleSearchResult() {
        showSearchResult = true;
    }
}
