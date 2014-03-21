package se.fidde.cartoll.jar.repository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

public abstract class AbstractRepository<E> {
    @PersistenceContext
    protected EntityManager entityManager;

    @Transactional
    protected abstract E get(long id) throws IOException, SQLException;

    @Transactional
    protected abstract Collection<E> getAll();

    @Transactional
    protected final E update(E obj) {
        return entityManager.merge(obj);
    }

    @Transactional
    protected final boolean remove(E obj) throws IOException {
        entityManager.remove(obj);
        return true;
    }

    @Transactional
    protected E add(E obj) {
        entityManager.persist(obj);
        return obj;
    }
}