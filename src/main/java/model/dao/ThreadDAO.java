package model.dao;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.Getter;
import model.entity.QThread;
import model.entity.Thread;
import model.entity.Post;

/**
 *
 * @author Team J
 */
@Stateless
public class ThreadDAO extends AbstractDAO<Thread, Long> {

    @Getter
    @PersistenceContext(unitName = "Forum")
    private EntityManager entityManager;

    public ThreadDAO() {
        super(Thread.class);
    }

    public Thread findThreadMatchingTitle(String title) {
        return (Thread) entityManager.createQuery("SELECT t FROM Thread t WHERE t.title = :title")
                .setParameter("title", title).getSingleResult();
    }

    public List<Thread> findThreadsMatchingCategory(Long cId) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QThread thread = QThread.thread;

        List<Thread> l = queryFactory.selectFrom(thread)
                .where(thread.category.cId.eq(cId))
                .fetch();

        return l;
    }

    public Thread findThreadMatchingTId(Long tId) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QThread thread = QThread.thread;

        return queryFactory.selectFrom(thread)
                .where(thread.tId.eq(tId))
                .fetchFirst();
    }

    public void incrementViewCount(Long tId) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QThread thread = QThread.thread;

        queryFactory.update(thread)
                .where(thread.tId.eq(tId))
                .set(thread.views, thread.views.add(1))
                .execute();
    }

    public Post firstPost(Thread thread) {
        if (thread.getPosts().isEmpty()) {
            return null;
        }
        return thread.getPosts().get(0);
    }

    public Post latestPost(Thread thread) {
        if (thread.getPosts().isEmpty()) {
            return null;
        }
        return thread.getPosts().get(thread.getPosts().size() - 1);
    }
    
}
