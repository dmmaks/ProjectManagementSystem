package repositories;

import java.util.List;

public interface BasicRepository<E> {
    public List<E> getAll();
    public E getById(int id);
    public boolean delete(int id);
    public boolean create(E item);
    public boolean update(E item);
}
