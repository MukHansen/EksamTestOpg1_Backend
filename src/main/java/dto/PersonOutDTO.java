package dto;

import entities.Hobby;
import entities.Person;
import entities.Phone;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;

@Schema(name = "PersonInfo")
public class PersonOutDTO {
    
    private int personID;
    
    @Schema(required = true, example = "info@simonskodebiks.dk")
    private String email;
    
    @Schema(required = true, example = "Gũnther")
    private String firstName;
    
    @Schema(required = true, example = "Steiner")
    private String lastName;
    
    @Schema(required = true, example = "[{\"name\":\"football\",\"description\":\"Every tuesday\"}, {\"name\":\"programming\",\"description\":\"all the time\"}]")
    private ArrayList<HobbyDTO> hobbies;
    
    @Schema(required = true, example = "[{\"phone\":\"12345\",\"description\":\"mobile\"}, {\"phone\":\"4444\",\"description\":\"work\"}]")
    private ArrayList<PhoneOutDTO> phones;
    
    @Schema(required = true, example = "{\"street\":\"Jacobsvej\","
            + "\"addiontinalInfo\":\"Første sal\","
            + " \"cityInfo\":{\"zipcode\":1234,\"city\":\"KBH\"}}")
    private AddressOutDTO address;
    
    public ArrayList<HobbyDTO> getHobbies() {
        return hobbies;
    }
    
    public void setHobbies(ArrayList<HobbyDTO> hobbies) {
        this.hobbies = hobbies;
    }
    
    public PersonOutDTO(Person person) {
        this.email = person.getEmail();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        List<Phone> phones = person.getPhones();
        for (Phone phone : phones) {
            this.phones.add(new PhoneOutDTO(phone));            
        }
        List<Hobby> hobbies = person.getHobbies();
        for (Hobby hobby : hobbies) {
            this.hobbies.add(new HobbyDTO(hobby));
        }
        this.address = new AddressOutDTO(person.getAddress());
    }
    
    public PersonOutDTO(String email, String firstName, String lastName, ArrayList<HobbyDTO> hobbies) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hobbies = hobbies;
    }

    public ArrayList<PhoneOutDTO> getPhones() {
        return phones;
    }

    public void setPhones(ArrayList<PhoneOutDTO> phones) {
        this.phones = phones;
    }

    public AddressOutDTO getAddress() {
        return address;
    }

    public void setAddress(AddressOutDTO address) {
        this.address = address;
    }
    
    public PersonOutDTO() {
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
    
}
