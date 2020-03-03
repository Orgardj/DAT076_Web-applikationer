package model.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.Getter;
import model.entity.Section;

/**
 *
 * @author jblom
 */
@Stateless
public class SectionDAO extends AbstractDAO<Section, Long> {

    @Getter
    @PersistenceContext(unitName = "Forum")
    private EntityManager entityManager;

    public SectionDAO() {
        super(Section.class);
    }

    public Section findSectionMatchingName(String name) {
        return (Section) entityManager.createQuery("SELECT s FROM Section s WHERE s.name = :name")
                .setParameter("name", name).getSingleResult();
    }
}
