package model.entity;

import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import model.dao.AccountDAO;

@Named
@SessionScoped
public class UserBean implements Serializable {

    @EJB
    private AccountDAO accountDAO;

    private Account account;

    @PostConstruct
    private void init() {
        //Since we dont have any login system atm its hard coded to use the user john23
        account = accountDAO.find("john23");
    }

    public Account getAccount() {
        return account;
    }

}
