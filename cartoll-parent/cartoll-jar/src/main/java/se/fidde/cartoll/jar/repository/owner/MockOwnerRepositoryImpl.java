package se.fidde.cartoll.jar.repository.owner;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import se.fidde.cartoll.jar.domain.owner.Owner;
import se.fidde.cartoll.jar.util.constants.IntegerConstants;
import se.fidde.cartoll.jar.util.validation.ValidationTools;

public class MockOwnerRepositoryImpl implements OwnerRepository {

    private ConcurrentHashMap<Long, Owner> owners;

    public MockOwnerRepositoryImpl() {
        owners = new ConcurrentHashMap<>();
    }

    @Override
    public Owner getOwner(long id) {
        Owner owner = owners.get(id);
        if (owner == null)
            return Owner.getEmptyOwner();

        return owner;
    }

    @Override
    public long addOwner(Owner owner) {
        ValidationTools.isNull(owner);

        try {
            owner.setId(owners.size());
            owners.put(owner.getId(), owner);

            return owner.getId();

        } catch (Exception e) {
            return IntegerConstants.EMPTY_ID.getInt();
        }
    }

    @Override
    public boolean removeOwner(long id) {
        Owner owner = owners.remove(id);
        if (owner == null)
            return false;

        return true;
    }

    @Override
    public long updateOwner(Owner owner) {
        try {
            owners.put(owner.getId(), owner);

            return owner.getId();

        } catch (Exception e) {
            return IntegerConstants.EMPTY_ID.getInt();
        }
    }

    @Override
    public Collection<Owner> getAllOwners() {
        return owners.values();
    }

    @Override
    public boolean removeAllOwners() {
        owners.clear();
        return true;
    }

}
