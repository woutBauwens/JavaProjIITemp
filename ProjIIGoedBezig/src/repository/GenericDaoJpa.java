package repository;

import Persistentie.SQLConnection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GenericDaoJpa<T> implements GenericDao<T> {
    private static final String PU_NAME = "bierWinkels";
    protected static final EntityManager em = SQLConnection.getManager();
    private final Class<T> type;
    
    public GenericDaoJpa(Class<T> type) {
        this.type = type;
    }
    public static void closePersistency() {
        em.close();
    }
    public static void startTransaction() {
        em.getTransaction().begin();
    }
    public static void commitTransaction() {
        em.getTransaction().commit();
    }
    public static void rollbackTransaction() {
        em.getTransaction().rollback();
    }

    @Override
    public List<T> findAll() {
        return em.createQuery("select entity from " + type.getName() + " entity", type).getResultList();
    }

    @Override
    public T get(int id) {
        T entity = em.find(type, id);
        return entity;
    }

    @Override
    public T update(T object) {
        return em.merge(object);
    }

    @Override
    public void delete(T object) {
        em.remove(em.merge(object));
    }

    @Override
    public void insert(T object) {
        em.persist(object);
    }

    @Override
    public boolean exists(Long id) {
        T entity = em.find(type, id);
        return entity != null;
    } 
    
}
