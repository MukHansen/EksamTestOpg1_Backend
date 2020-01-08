package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author frede
 */
@Entity
@NamedQuery(name = "CityInfo.deleteAllRows", query = "DELETE from CityInfo")
public class CityInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cityInfoID;
    
    private int zipCode;
    private String city;
    
    @OneToMany(
            mappedBy = "cityInfo", 
            cascade = CascadeType.PERSIST
    )
    private List<Address> addresses = new ArrayList();

    public CityInfo() {
    }

    public CityInfo(int zipCode, String city) {
        this.zipCode = zipCode;
        this.city = city;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void addAddress(Address a) {
        this.addresses.add(a);
    }

    public Integer getCityInfoID() {
        return cityInfoID;
    }

    public void setCityInfoID(Integer cityInfoID) {
        this.cityInfoID = cityInfoID;
    }

    @Override
    public String toString() {
        return "CityInfo{" + "cityInfoID=" + cityInfoID + ", zipCode=" + zipCode + ", city=" + city + ", addresses=" + addresses + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.cityInfoID);
        hash = 67 * hash + this.zipCode;
        //hash = 67 * hash + Objects.hashCode(this.city);
        //hash = 67 * hash + Objects.hashCode(this.addresses);
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
        final CityInfo other = (CityInfo) obj;
        if (this.zipCode != other.zipCode) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.cityInfoID, other.cityInfoID)) {
            return false;
        }
        if (!Objects.equals(this.addresses, other.addresses)) {
            return false;
        }
        return true;
    }
    
    
    
}
