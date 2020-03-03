package model.dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.Getter;
import model.entity.QPost;
import model.entity.QTopic;
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
    
    public List<Topic> findTopicsMatchingSection(Long sId) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QTopic topic = QTopic.topic;

        
        List<Topic> l = queryFactory.selectFrom(topic)
                .where(topic.section.sId.eq(sId))
                .fetch();
        
        return l;
        //return (List<Topic>) entityManager.createQuery("SELECT t FROM Topic t WHERE t.sId = :sId")
        //        .setParameter("sId", sId).getResultList();
    }
    
    public Topic findTopicMatchingId(Long tId) {
          JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QTopic topic = QTopic.topic;
        
        return queryFactory.selectFrom(topic)
               .where(topic.topic.tId.eq(tId))
               .fetchFirst();
    }
}

