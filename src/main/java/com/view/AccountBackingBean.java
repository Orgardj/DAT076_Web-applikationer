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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import lombok.Data;
import model.dao.AccountDAO;
import model.entity.Account;

@Data
@Named
@ViewScoped
public class AccountBackingBean implements Serializable {

    @EJB
    private AccountDAO accountDAO;

    private List<Account> users;

    private String userNameInput;

    private String passwordInput;

    @PostConstruct
    private void init() {
        users = new ArrayList<>(accountDAO.findAll());
    }

    public String validateAccount() {
        Account t = accountDAO.findMatchingUserCredentials(userNameInput,passwordInput);
//t.getPassword().equals(passwordInput)
        if (t != null) {
            /*HttpSession session = SessionUtils.getSession();
            session.setAttribute("username", userNameInput);
             */
            return "index";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    "studentForm:loginButton",
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Incorrect Username and Password",
                            "Please enter correct username and Password"));
            return "login";
        }
    }
}
