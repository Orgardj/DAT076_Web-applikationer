package model.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractDAO<T, K> {

    private final Class<T> entityType;

    protected abstract EntityManager getEntityManager();

    public long count() {
        final CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        final CriteriaQuery cq = builder.createQuery();
        final Root<T> rt = cq.from(entityType);
        cq.select(builder.count(rt));
        final Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult());
    }

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public T find(K key) {
        return getEntityManager().find(entityType, key);
    }

    public void update(T entity) {
        getEntityManager().merge(entity);
    }

    public List<T> findAll() {
        final CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityType));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public void remove(T entity) {
        /* 
            If we create an entity and add a child entity and then add another child entity to
            that one we are unable to remove the the "grand" parent entity until the application 
            had been restarted and the entity was refreshed. So we found a work around to do this
            before every delete instead. Not sure if this is a bug in hibernate or if it's something
            we have missed.
        */
        getEntityManager().refresh(getEntityManager().merge(entity));
        getEntityManager().remove(getEntityManager().merge(entity));
    }
}
