package controller;

import java.util.Map;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import model.UserBean;
import model.dao.AccountAuthDAO;
import model.dao.AccountDAO;
import model.entity.AccountAuth;
import javax.servlet.http.Cookie;

/**
 *
 * @author orgardj
 */
public class SessionListener implements HttpSessionListener {

    @Inject
    UserBean userBean;

    @EJB
    private AccountAuthDAO accountAuthDAO;

    @EJB
    private AccountDAO accountDAO;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> cookies = ec.getRequestCookieMap();
        if (cookies.containsKey("selector") && cookies.containsKey("validator")) {
            Cookie selector = (Cookie) cookies.get("selector");
            Cookie validator = (Cookie) cookies.get("validator"); 
            AccountAuth accountAuth = accountAuthDAO.findMatchingAccount(selector.getValue(), validator.getValue());
            if (accountAuth != null) {
                userBean.setAccount(accountAuth.getAccount());
            }
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
    }
}
