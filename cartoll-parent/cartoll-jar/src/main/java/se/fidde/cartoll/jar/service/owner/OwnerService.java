package se.fidde.cartoll.jar.service.owner;

import java.io.IOException;
import java.util.Collection;

import se.fidde.cartoll.jar.domain.owner.Owner;

/**
 * @author fidde Service for owners
 */
public interface OwnerService {

    Owner getOwner(long id) throws IOException;

    long addOwner(Owner owner);

    boolean removeOwner(long id) throws IOException;

    long updateOwner(Owner owner);

    Collection<Owner> getAllOwners();

    boolean removeAllOwners();

}
