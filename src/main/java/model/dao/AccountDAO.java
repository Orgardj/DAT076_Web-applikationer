package model.dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.Getter;
import model.entity.Account;
import model.entity.QAccount;

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
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QAccount account = QAccount.account;

        Account l = queryFactory.selectFrom(account)
                .where(account.userName.eq(userName))
                .fetchFirst();
        return l;
    }

    public Account findAccountMatchingEmail(String email) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QAccount account = QAccount.account;

        Account l = queryFactory.selectFrom(account)
                .where(account.email.eq(email))
                .fetchFirst();
        return l;
    }

    public List<Account> findAccountsMatchingRole(String role) {
        return (List<Account>) entityManager.createQuery("SELECT a FROM Account a WHERE a.role = :role")
                .setParameter("role", role).getResultList();
    }
}
