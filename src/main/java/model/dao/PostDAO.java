package model.dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.Getter;
import model.entity.Post;
import model.entity.QPost;
import model.entity.QTopic;
import model.entity.Topic;

/**
 *
 * @author Team J
 */
@Stateless
public class PostDAO extends AbstractDAO<Post, Long> {

    @Getter
    @PersistenceContext(unitName = "Forum")
    private EntityManager entityManager;

    public PostDAO() {
        super(Post.class);
    }

    public List<Post> findPostMatchingTitle(String title) {
        return (List<Post>) entityManager.createQuery("SELECT t FROM Post t WHERE t.title = :title")
                .setParameter("title", title).getResultList();
    }
    
    public Post findPostMatchingPId(Long tId) {
        //return (List<Post>) entityManager.createQuery("SELECT t FROM Post t WHERE t.tId = :tId")
          //      .setParameter("tId", tId).getResultList();
          JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QPost post = QPost.post;
        
        return queryFactory.selectFrom(post)
               .where(post.topic.tId.eq(tId))
               .fetchFirst();
    }
    
    public List<Post> findPostsMatchingPId(Long tId) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QPost post = QPost.post;
        
        List<Post> l = queryFactory.selectFrom(post)
                        .where(post.topic.tId.eq(tId))
                        .fetch();
        
        return l;
    }
}

