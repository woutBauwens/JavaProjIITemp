package repository;

//import domein.gbGroepStatePattern.State;
import java.util.ArrayList;
import persistentie.SQLConnection;
import java.util.List;
import javax.persistence.EntityManager;

public class GenericDaoJpa<T> implements GenericDao<T> {

    private static final String PU_NAME = "GoedBezig";
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
    public void persist(T object) {
        em.getTransaction().begin();
        em.persist(object);
        em.getTransaction().commit();
    }

    @Override
    public List<T> findAll() {
        List<T> values = new ArrayList<>();
        int id = 1;
        while (true) {
            T value = get(id);
            if (value != null) {
                values.add(value);
                id++;
            } else {
                break;
            }
        }
        return values;
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
        em.getTransaction().begin();
        em.persist(object);
        em.getTransaction().commit();
    }

    @Override
    public boolean exists(Long id) {
        T entity = em.find(type, id);
        return entity != null;
    }
}
