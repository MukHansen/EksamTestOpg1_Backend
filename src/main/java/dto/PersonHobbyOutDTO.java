package dto;

import entities.Address;
import entities.Hobby;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;

@Schema(name = "PersonInfo")
public class PersonHobbyOutDTO {
    
    private int personID;
    
    @Schema(required = true, example = "info@simonskodebiks.dk")
    private String email;
    
    @Schema(required = true, example = "Gũnther")
    private String firstName;
    
    @Schema(required = true, example = "Steiner")
    private String lastName;
    
    @Schema(required = true, example = "Monzavej 1, 2800 Lyngby")
    private String address;
    
    @Schema(required = true, example = "[\"Football\",\"Programming\"]")
    private List<Hobby> hobbies;
    
    public PersonHobbyOutDTO(String email, String firstName, String lastName, String address, List<Hobby> hobbies ) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.lastName = lastName;
        this.address = address;
        this.hobbies = hobbies;
    }

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(List<Hobby> hobbies) {
        this.hobbies = hobbies;
    }

    @Override
    public String toString() {
        return "PersonHobbyOutDTO{" + "personID=" + personID + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + ", address=" + address + ", hobbies=" + hobbies + '}';
    }
    
    
    
}
