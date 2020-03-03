package model.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.Getter;
import model.entity.Account;

/**
 *
 * @author Team J
 */
@Stateless
public class AccountDAO extends AbstractDAO<Account, String> {

    @Getter
    @PersistenceContext(unitName = "Forum")
    private EntityManager entityManager;

    public AccountDAO() {
        super(Account.class);
    }

    public Account findAccountMatchingUserName(String userName) {
        return (Account) entityManager.createQuery("SELECT a FROM Account a WHERE a.userName = :userName")
                .setParameter("userName", userName).getSingleResult();
    }
    
    public Account findAccountMatchingEmail(String email) {
        return (Account) entityManager.createQuery("SELECT a FROM Account a WHERE a.email = :email")
                .setParameter("email", email).getSingleResult();
    }
    
    public List<Account> findAccountsMatchingRole(String role) {
        return (List<Account>) entityManager.createQuery("SELECT a FROM Account a WHERE a.role = :role")
                .setParameter("role", role).getResultList();
    }
    
}
