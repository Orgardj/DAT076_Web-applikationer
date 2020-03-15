package model.dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.Getter;
import model.entity.Post;
import model.entity.QPost;

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

    public List<Post> findPostsMatchingUserName(String userName) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QPost post = QPost.post;

        List<Post> l = queryFactory.selectFrom(post)
                .where(post.user.userName.eq(userName))
                .fetch();

        return l;
    }

    public List<Post> findPostsMatchingTId(Long tId) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QPost post = QPost.post;

        return queryFactory.selectFrom(post)
                .where(post.post.thread.tId.eq(tId))
                .fetch();
    }

    public List<Post> findPostsMatchingThread(Long tId) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QPost post = QPost.post;

        List<Post> l = queryFactory.selectFrom(post)
                .where(post.thread.tId.eq(tId))
                .fetch();

        return l;
    }
}
