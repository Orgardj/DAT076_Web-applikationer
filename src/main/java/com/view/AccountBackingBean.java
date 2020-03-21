package com.view;

/**
 *
 * @author jblom
 */
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
import model.entity.AccountAuth;
import org.apache.commons.lang3.RandomStringUtils;
import org.omnifaces.util.Messages;

@Data
@Named
@ViewScoped
public class AccountBackingBean implements Serializable {

    @EJB
    private AccountDAO accountDAO;

    @EJB
    private AccountAuthDAO accountAuthDAO;

    @Inject
    UserBean userBean;

    private String userNameInput;

    private String passwordInput;

    private String firstName;

    private String lastName;

    private String email;

    private String confirmPassword;

    private String passwordInput2;

    private int chooseProfilePicture;

    private Boolean passwordValid = false;

    private Boolean emailDontExist = false;

    private Boolean accountDontExist = false;

    private Boolean passwordIsCurrent = false;

    private String hashedPassword;

    private Boolean showPassword = false;

    private Boolean showEmail = false;

    private Boolean rememberMe = true;

    Boolean loginSuccessfully = false;

    public static final String SALT = "saltSecurityText";

    public void checkAccountInfo() throws NoSuchAlgorithmException {
        hashPassword(passwordInput);
        checkIfAccountExists();
        checkIfEmailExists();
        checkIfPasswordsMatch(passwordInput);
        if (passwordValid && emailDontExist && accountDontExist) {
            addAccount();
        }
    }

    public void hashPassword(String password) throws NoSuchAlgorithmException {
        String passwordToHash = password;
        hashedPassword = get_SHA_512_hashedPassword(passwordToHash, SALT);
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

    public void checkIfPasswordsMatch(String password) {
        passwordValid = false;
        if (!password.equals(confirmPassword)) {
            Messages.addError("changePasswordForm:confirmPassword", "Password must match");
            Messages.addError("registerForm:confirmPassword", "Password must match");
        } else {
            passwordValid = true;
        }
    }

    public void checkIfAccountExists() {
        accountDontExist = false;
        if (!userNameInput.isEmpty()) {
            Account account = accountDAO.findAccountMatchingUserName(userNameInput);
            if (account != null) {
                Messages.addError("registerForm:userName", "Username already taken");
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
                Messages.addError("changeEmailForm:email", "Email already used");
                Messages.addError("registerForm:email", "Email already used");
            } else {
                emailDontExist = true;
            }
        }
    }

    public void checkIfPasswordIsCurrent(Account account) throws NoSuchAlgorithmException {
        passwordIsCurrent = false;
        hashPassword(passwordInput);
        if (account.getPassword().equals(hashedPassword)) {
            passwordIsCurrent = true;
        } else {
            Messages.addError("changePasswordForm:oldPassword", "Not your current password");
            Messages.addError("changeEmailForm:currentPassword2", "Wrong password");
        }
    }

    public void togglePasswordWindow() {
        showEmail = false;
        showPassword = !showPassword;
    }

    public void toggleEmailWindow() {
        showEmail = !showEmail;
        showPassword = false;
    }

    public void changePassword(Account account) throws NoSuchAlgorithmException {
        checkIfPasswordsMatch(passwordInput2);
        checkIfPasswordIsCurrent(account);
        if (passwordValid && passwordIsCurrent) {
            hashPassword(passwordInput2);
            account.setPassword(hashedPassword);
            accountDAO.update(account);
            togglePasswordWindow();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            UIViewRoot uiViewRoot = facesContext.getViewRoot();
            HtmlInputText inputText = null;
            inputText = (HtmlInputText) uiViewRoot.findComponent("changePasswordForm:oldPassword");
            inputText.setValue("");
            inputText = (HtmlInputText) uiViewRoot.findComponent("changePasswordForm:password2");
            inputText.setValue("");
            inputText = (HtmlInputText) uiViewRoot.findComponent("changePasswordForm:confirmPassword");
            inputText.setValue("");
            inputText = (HtmlInputText) uiViewRoot.findComponent("changeEmailForm:currentPassword2");
            inputText.setValue("");
        }
    }

    public void changeEmail(Account account) throws NoSuchAlgorithmException {
        checkIfPasswordIsCurrent(account);
        checkIfEmailExists();
        if (passwordIsCurrent && emailDontExist) {
            account.setEmail(email);
            accountDAO.update(account);
            toggleEmailWindow();
            FacesContext facesContext = FacesContext.getCurrentInstance();
            UIViewRoot uiViewRoot = facesContext.getViewRoot();
            HtmlInputText inputText = null;
            inputText = (HtmlInputText) uiViewRoot.findComponent("changeEmailForm:email");
            inputText.setValue("");
            inputText = (HtmlInputText) uiViewRoot.findComponent("changeEmailForm:currentPassword2");
            inputText.setValue("");
            inputText = (HtmlInputText) uiViewRoot.findComponent("changePasswordForm:oldPassword");
            inputText.setValue("");
        }
    }

    public void addAccount() {
        accountDAO.create(new Account(userNameInput, hashedPassword, email, "member", firstName, lastName, new Date(), chooseProfilePicture)); // hardcoded as member for now
    }

    public void validateAccount() throws NoSuchAlgorithmException {
        System.out.println("TESTING");
        System.out.println(userNameInput);
        System.out.println(passwordInput);

        if (userNameInput.isEmpty() || passwordInput.isEmpty()) {
            Messages.addError("studentForm:loginButton", "Please enter a username and Password");
        }
        String passwordToHash = passwordInput;
        Account account = accountDAO.findAccountMatchingUserName(userNameInput);
        hashedPassword = get_SHA_512_hashedPassword(passwordToHash, SALT);

        if (account != null) {
            if (account.getPassword().equals(hashedPassword)) {
                if (account.getRole().equals("banned")) {
                    Messages.addError("studentForm:loginButton", "Banned user");
                }
                userBean.setAccount(account);
                if (rememberMe) {
                    AccountAuth newToken = new AccountAuth();

                    String selector = RandomStringUtils.randomAlphanumeric(12);
                    String rawValidator = RandomStringUtils.randomAlphanumeric(64);

                    String hashedValidator = get_SHA_512_hashedPassword(rawValidator, SALT);

                    newToken.setSelector(selector);
                    newToken.setValidator(hashedValidator);
                    newToken.setAccount(account);

                    accountAuthDAO.create(newToken);

                    ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
                    HashMap map = new HashMap();
                    map.put("maxAge", 604800);
                    ec.addResponseCookie("selector", selector, map);
                    ec.addResponseCookie("validator", hashedValidator, map);
                }
                loginSuccessfully = true;
            }
            Messages.addError("studentForm:loginButton", "Please enter a correct username and password");
        }
        Messages.addError("studentForm:loginButton", "Please enter a correct username and Password");
    }

    public void banAccount(Account account) {
        account.setRole("banned");
        accountDAO.update(account);
    }

    public String viewProfilePicture(Account account) {
        return "profile" + account.getProfilePicture() + ".png";
    }

    public void logout() {
        userBean.setAccount(null);

        //Remove cookies
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> cookies = ec.getRequestCookieMap();
        if (cookies.containsKey("selector") && cookies.containsKey("validator")) {
            Cookie selector = (Cookie) cookies.get("selector");
            Cookie validator = (Cookie) cookies.get("validator");
            accountAuthDAO.remove(accountAuthDAO.findMatchingAccountAuth(selector.getValue(), validator.getValue()));

            HashMap map = new HashMap();
            map.put("maxAge", 0);
            ec.addResponseCookie("selector", "", map);
            ec.addResponseCookie("validator", "", map);
        }
    }
}
