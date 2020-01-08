package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author frede
 */
@Entity
@NamedQuery(name = "Address.deleteAllRows", query = "DELETE from Address")
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer addressID;
    
    private String street;
    private String additionalInfo;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cityInfoID")
    private CityInfo cityInfo;
    
    @OneToMany(
            mappedBy = "address",
            cascade = CascadeType.PERSIST
    )
    private List<Person> persons = new ArrayList();

    public Address() {
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void addPerson(Person person) {
        this.persons.add(person);
    }
    
    public Address(String street, String additionalInfo, CityInfo cityInfo) {
        this.street = street;
        this.additionalInfo = additionalInfo;
        this.cityInfo = cityInfo;
        cityInfo.addAddress(this);
    }

    public CityInfo getCityInfo() {
        return cityInfo;
    }

    public void setCityInfo(CityInfo cityInfo) {
        this.cityInfo = cityInfo;
        if(!cityInfo.getAddresses().contains(this))
            cityInfo.addAddress(this);
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }    

    public Integer getAddressID() {
        return addressID;
    }

    public void setAddressID(Integer addressID) {
        this.addressID = addressID;
    }

    @Override
    public String toString() {
        return "Address{" + "addressID=" + addressID + ", street=" + street + ", additionalInfo=" + additionalInfo + ", cityInfo=" + cityInfo + ", persons=" + persons + '}';
    }

    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.addressID);
        hash = 97 * hash + Objects.hashCode(this.street);
        //hash = 97 * hash + Objects.hashCode(this.additionalInfo);
        hash = 97 * hash + Objects.hashCode(this.cityInfo);
        hash = 97 * hash + Objects.hashCode(this.persons);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Address other = (Address) obj;
        if (!Objects.equals(this.street, other.street)) {
            return false;
        }
        if (!Objects.equals(this.additionalInfo, other.additionalInfo)) {
            return false;
        }
        if (!Objects.equals(this.addressID, other.addressID)) {
            return false;
        }
        if (!Objects.equals(this.cityInfo, other.cityInfo)) {
            return false;
        }
        return true;
    }

    
}
