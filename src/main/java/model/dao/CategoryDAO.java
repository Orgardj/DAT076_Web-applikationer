package model.dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.Getter;
import model.entity.Category;
import model.entity.QCategory;
import model.entity.Thread;

/**
 *
 * @author jblom
 */
@Stateless
public class CategoryDAO extends AbstractDAO<Category, Long> {

    @Getter
    @PersistenceContext(unitName = "Forum")
    private EntityManager entityManager;

    public CategoryDAO() {
        super(Category.class);
    }

    public Category findCategoryMatchingName(String title) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QCategory category = QCategory.category;

        Category l = queryFactory.selectFrom(category)
                .where(category.name.eq(title))
                .fetchFirst();
        return l;
    }

    public Thread latestThread(Category category) {
        if (category.getThreads().isEmpty()) {
            return null;
        }
        return category.getThreads().get(category.getThreads().size() - 1);
    }
}
