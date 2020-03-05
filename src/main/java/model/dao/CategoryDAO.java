package model.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.Getter;
import model.entity.Category;

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

    public Category findCategoryMatchingName(String name) {
        return (Category) entityManager.createQuery("SELECT c FROM Category c WHERE c.name = :name")
                .setParameter("name", name).getSingleResult();
    }
}
