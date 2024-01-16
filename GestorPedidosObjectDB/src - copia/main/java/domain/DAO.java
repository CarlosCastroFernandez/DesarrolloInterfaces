package domain;

import java.util.List;

public interface DAO<T> {
    /* Basic CRUD operations */
    /* All write methods return the updated object */
    T save( T t);
    T update( T t);
    boolean remove(T t);
    T get( Long id);
    List<T> getAll();
}
