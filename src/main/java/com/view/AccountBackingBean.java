package com.view;

/**
 *
 * @author jblom
 */
import java.io.Serializable;
import java.util.Date;
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

    private String firstName;

    private String lastName;

    private String email;

    private String confirmPassword;

    private Boolean passwordValid = false;

    private Boolean emailDontExist = false;

    private Boolean accountDontExist = false;

    public String checkAccountInfo() {

        checkIfAccountExists();
        checkIfEmailExists();
        checkIfPasswordsMatch();
        if (passwordValid && emailDontExist && accountDontExist) {
            addAccount();
            return "login";
        } else {
            return "";
        }

        
    }

    public void checkIfPasswordsMatch() {
        passwordValid = false;
        if (!passwordInput.equals(confirmPassword)) {
            FacesContext.getCurrentInstance().addMessage(
                    "registerForm:confirmPassword",
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Password must match",
                            "Password must match"));

        } else {
            passwordValid = true;
        }

    }

    public void checkIfAccountExists() {
        accountDontExist = false;
        if (userNameInput.isEmpty()) {

        } else {
            Account account = accountDAO.findAccountMatchingUserName(userNameInput);

            if (account != null) {
                FacesContext.getCurrentInstance().addMessage(
                        "registerForm:userName",
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Username already taken",
                                "Username already taken"));

            } else {

                accountDontExist = true;
            }
        }
    }

    public void checkIfEmailExists() {
        emailDontExist = false;
        if (email.isEmpty()) {

        } else {
            Account account = accountDAO.findAccountMatchingEmail(email);

            if (account != null) {
                FacesContext.getCurrentInstance().addMessage(
                        "registerForm:email",
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Email already used",
                                "Email already used"));

            } else {

                emailDontExist = true;
            }
        }
    }

    public void addAccount() {
        accountDAO.create(new Account(userNameInput, passwordInput, email, "member", firstName, lastName, new Date())); // hardcoded as member for now

    }

    public String validateAccount() {
        if (userNameInput.isEmpty() || passwordInput.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(
                    "studentForm:loginButton",
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Incorrect Username and Password",
                            "Please enter a username and Password"));
            return "";
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
                    return "";
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(
                        "studentForm:loginButton",
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Incorrect Username and Password",
                                "Please enter a correct username and password"));
                return "";
            }
        }
    }

}
