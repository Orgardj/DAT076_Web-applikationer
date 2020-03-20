package controller;

import com.view.AccountBackingBean;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import model.entity.Account;
import org.omnifaces.cdi.Push;
import org.omnifaces.cdi.PushContext;

@Named
@RequestScoped
public class AccountController implements Serializable {

    @Inject
    private AccountBackingBean accountBackingBean;

    @Inject
    @Push(channel = "main")
    private PushContext push;

    public void banAccount(Account account) {
        accountBackingBean.banAccount(account);
        push.send("update_posts");
        push.send("update_users");
    }
}
