package com.view;

/**
 *
 * @author jblom
 */
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Data;
import model.dao.AccountDAO;
import model.entity.Account;
import model.entity.UserBean;

@Data
@Named
@ViewScoped
public class AccountBackingBean implements Serializable {

    @EJB
    private AccountDAO accountDAO;

    @Inject
    UserBean userBean;

    private List<Account> users;

    private String userNameInput;

    private String passwordInput;

    public String validateAccount() {
        if (userNameInput.isEmpty() || passwordInput.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(
                    "studentForm:loginButton",
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Incorrect Username and Password",
                            "Please enter a username and Password"));
            return "login";
        } else {
            Account account = accountDAO.findAccountMatchingUserName(userNameInput);

            if (account != null) {
                if (account.getPassword().equals(passwordInput)) {
                    userBean.setAccount(account);
                    return "index";
                } else {
                    FacesContext.getCurrentInstance().addMessage(
                            "studentForm:loginButton",
                            new FacesMessage(FacesMessage.SEVERITY_WARN,
                                    "Incorrect Username and Password",
                                    "Please enter a correct username and Password"));
                    return "login";
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(
                        "studentForm:loginButton",
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Incorrect Username and Password",
                                "Please enter a correct username and password"));
                return "login";
            }
        }
    }
}
