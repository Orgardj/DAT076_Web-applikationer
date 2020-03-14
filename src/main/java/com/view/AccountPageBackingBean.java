package com.view;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Data;
import model.dao.AccountDAO;
import model.entity.Account;
import org.omnifaces.cdi.Param;

/**
 *
 * @author orgardj
 */
@Data
@Named
@ViewScoped
public class AccountPageBackingBean implements Serializable {

    @EJB
    private AccountDAO accountDAO;

    private Account account;

    @Inject
    @Param
    private String userName;

    @PostConstruct
    private void init() {
        account = accountDAO.find(userName);
    }
}
