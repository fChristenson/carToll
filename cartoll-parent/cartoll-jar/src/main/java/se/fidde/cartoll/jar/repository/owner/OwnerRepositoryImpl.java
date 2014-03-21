package se.fidde.cartoll.jar.repository.owner;

import java.io.IOException;
import java.util.Collection;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import se.fidde.cartoll.jar.domain.owner.Owner;
import se.fidde.cartoll.jar.repository.AbstractRepository;

public class OwnerRepositoryImpl extends AbstractRepository<Owner> implements
        OwnerRepository {

    private Logger log = Logger.getLogger(OwnerRepositoryImpl.class);

    @Transactional
    @Override
    public Owner getOwner(long id) throws IOException {
        return get(id);
    }

    @Transactional
    @Override
    public long addOwner(Owner owner) {
        log.debug("add: " + owner);
        Owner result = add(owner);

        return result.getId();
    }

    @Transactional
    @Override
    public boolean removeOwner(long id) throws IOException {
        Owner owner = get(id);

        return remove(owner);
    }

    @Transactional
    @Override
    public long updateOwner(Owner owner) {
        log.debug("add: " + owner);
        Owner result = update(owner);

        return result.getId();
    }

    @Transactional
    @Override
    public Collection<Owner> getAllOwners() {
        return getAll();
    }

    @Transactional
    @Override
    protected Owner get(long id) throws IOException {
        log.debug("get owner: " + id);
        Owner owner = entityManager.find(Owner.class, id);

        log.debug("owner found was: " + owner);
        if (owner == null)
            return Owner.getEmptyOwner();

        return owner;
    }

    @Transactional
    @SuppressWarnings("unchecked")
    @Override
    protected Collection<Owner> getAll() {
        Query query = entityManager.createNamedQuery("getAllOwners");

        return query.getResultList();
    }

    @Transactional
    @Override
    public boolean removeAllOwners() {
        Query namedQuery = entityManager.createNamedQuery("removeAllOwners");
        namedQuery.executeUpdate();
        return true;
    }

}
