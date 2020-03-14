package com.view;

/**
 *
 * @author jblom
 */
import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Data;
import model.dao.AccountDAO;
import model.entity.Account;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Data
@Named
@ViewScoped
public class AccountBackingBean implements Serializable {

    @EJB
    private AccountDAO accountDAO;

    @Inject
    UserBean userBean;

    private String userNameInput;

    private String passwordInput;

    private String firstName;

    private String lastName;

    private String email;

    private String confirmPassword;

    private Boolean passwordValid = false;

    private Boolean emailDontExist = false;

    private Boolean accountDontExist = false;

    private String hashedPassword;

    public static final String salt = "saltSecurityText";

    public String checkAccountInfo() throws NoSuchAlgorithmException {
        hashPassword();
        checkIfAccountExists();
        checkIfEmailExists();
        checkIfPasswordsMatch();

        if (passwordValid && emailDontExist && accountDontExist) {
            addAccount();
            return "login";
        }
        return "";
    }

    public void hashPassword() throws NoSuchAlgorithmException {
        String passwordToHash = passwordInput;
        hashedPassword = get_SHA_512_hashedPassword(passwordToHash, salt);
    }

    private static String get_SHA_512_hashedPassword(String passwordToHash, String salt) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");

            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
        }
        return generatedPassword;
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
        if (!userNameInput.isEmpty()) {
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
        if (!email.isEmpty()) {
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
        accountDAO.create(new Account(userNameInput, hashedPassword, email, "member", firstName, lastName, new Date())); // hardcoded as member for now
    }

    public String validateAccount() throws NoSuchAlgorithmException {
        if (userNameInput.isEmpty() || passwordInput.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(
                    "studentForm:loginButton",
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Incorrect Username and Password",
                            "Please enter a username and Password"));
            return "";
        }

        String passwordToHash = passwordInput;
        Account account = accountDAO.findAccountMatchingUserName(userNameInput);
        hashedPassword = get_SHA_512_hashedPassword(passwordToHash, salt);

        if (account != null) {
            if (account.getPassword().equals(hashedPassword)) {
                if (account.getRole().equals("banned")) {
                    FacesContext.getCurrentInstance().addMessage(
                            "studentForm:loginButton",
                            new FacesMessage(FacesMessage.SEVERITY_WARN,
                                    "Contact an administrator if you want to appeal",
                                    "Banned user"));
                    return "";
                }
                userBean.setAccount(account);
                return "index";
            }
            FacesContext.getCurrentInstance().addMessage(
                    "studentForm:loginButton",
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Incorrect Username and Password",
                            "Please enter a correct username and Password"));
            return "";
        }
        FacesContext.getCurrentInstance().addMessage(
                "studentForm:loginButton",
                new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "Incorrect Username and Password",
                        "Please enter a correct username and password"));
        return "";
    }

public void banAccount(Account account) {
        account.setRole("banned");
        accountDAO.update(account);
    }
}
