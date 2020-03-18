package model.dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.Getter;
import model.entity.AccountAuth;
import model.entity.QAccountAuth;

/**
 *
 * @author orgardj
 */
@Stateless
public class AccountAuthDAO extends AbstractDAO<AccountAuth, Long> {

    @Getter
    @PersistenceContext(unitName = "Forum")
    private EntityManager entityManager;

    public AccountAuthDAO() {
        super(AccountAuth.class);
    }
    
    public AccountAuth findMatchingAccountAuth(String selector, String validator) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QAccountAuth accountAuth = QAccountAuth.accountAuth;

        AccountAuth l = queryFactory.selectFrom(accountAuth)
                .where(accountAuth.selector.eq(selector)).where(accountAuth.validator.eq(validator))
                .fetchFirst();
        return l;

    }
}
