package Repository;

import Domain.Entity;

public interface Repository<ID, E extends Entity<ID>> {
    int size();
    void save(E entity);
    void delete(ID id);
    void update(ID id, E entity);
    E findOne(ID id);
    Iterable<E> findAll();
}