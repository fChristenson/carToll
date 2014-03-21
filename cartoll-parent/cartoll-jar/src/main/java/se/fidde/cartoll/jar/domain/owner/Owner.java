package se.fidde.cartoll.jar.domain.owner;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.apache.log4j.Logger;

import se.fidde.cartoll.jar.domain.vehicle.Vehicle;
import se.fidde.cartoll.jar.util.constants.JarStringConstants;
import se.fidde.cartoll.jar.util.validation.ValidationTools;

/**
 * @author fidde Owner represents a person who can own 0 or more vehicles
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "getAllOwners", query = "SELECT o FROM Owner o"),
        @NamedQuery(name = "removeAllOwners", query = "DELETE FROM Owner") })
public class Owner {
    @Id
    @GeneratedValue
    private long id;
    private String firstName;
    private String lastName;
    private String street;
    private String zipCode;
    private String city;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Vehicle> vehicles;
    @Transient
    private Logger log = Logger.getLogger(Owner.class);

    public Owner() {
    }

    public Owner(Name name, Address address) {
        ValidationTools.isNull(name, address);

        firstName = name.getFirstName();
        lastName = name.getLastName();
        street = address.getStreet();
        zipCode = address.getZipcode();
        city = address.getCity();
        vehicles = new HashSet<>();
    }

    public Set<Vehicle> getVehicles() {
        return vehicles;
    }

    @Override
    public String toString() {
        return String.format("%s %s", getFirstName(), getLastName());
    }

    public static Owner getEmptyOwner() {
        Address address = new Address(JarStringConstants.EMPTY_NAME.toString(),
                JarStringConstants.EMPTY_ZIPCODE.toString(),
                JarStringConstants.EMPTY_NAME.toString());
        Name name = new Name(JarStringConstants.EMPTY_NAME.toString(),
                JarStringConstants.EMPTY_NAME.toString());

        return new Owner(name, address);
    }

    public Address getFullAddress() {
        return new Address(getStreet(), getZipcode(), getCity());
    }

    public Name getFullName() {
        return new Name(firstName, lastName);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipCode;
    }

    public void setZipcode(String zipcode) {
        this.zipCode = zipcode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

}