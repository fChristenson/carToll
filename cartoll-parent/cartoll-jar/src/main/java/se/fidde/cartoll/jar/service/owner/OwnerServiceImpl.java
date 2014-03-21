package se.fidde.cartoll.jar.service.owner;

import java.io.IOException;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.fidde.cartoll.jar.domain.owner.Owner;
import se.fidde.cartoll.jar.repository.owner.OwnerRepository;
import se.fidde.cartoll.jar.util.validation.ValidationTools;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;
    private Logger log = Logger.getLogger(OwnerServiceImpl.class);

    public OwnerServiceImpl() {
    }

    @Override
    public Owner getOwner(long id) throws IOException {
        log.debug("getting owner with id: " + id);
        return ownerRepository.getOwner(id);
    }

    @Override
    public long addOwner(Owner owner) {
        ValidationTools.isNull(owner);

        log.debug("adding owner: " + owner);
        return ownerRepository.addOwner(owner);
    }

    @Override
    public boolean removeOwner(long id) throws IOException {
        log.debug("removing owner with id " + id);
        return ownerRepository.removeOwner(id);
    }

    public void setRepository(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public long updateOwner(Owner owner) {
        return ownerRepository.updateOwner(owner);
    }

    @Override
    public Collection<Owner> getAllOwners() {
        return ownerRepository.getAllOwners();
    }

    @Override
    public boolean removeAllOwners() {
        return ownerRepository.removeAllOwners();
    }
}
