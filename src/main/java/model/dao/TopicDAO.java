package model.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.Getter;
import model.entity.Topic;

/**
 *
 * @author Team J
 */
@Stateless
public class TopicDAO extends AbstractDAO<Topic, Long> {

    @Getter
    @PersistenceContext(unitName = "Forum")
    private EntityManager entityManager;

    public TopicDAO() {
        super(Topic.class);
    }

    public List<Topic> findTopicMatchingTitle(String title) {
        return (List<Topic>) entityManager.createQuery("SELECT t FROM Topic t WHERE t.title = :title")
                .setParameter("title", title).getResultList();
    }
}

