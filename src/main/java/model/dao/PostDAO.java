package model.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import lombok.Getter;
import model.entity.Post;

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
}

