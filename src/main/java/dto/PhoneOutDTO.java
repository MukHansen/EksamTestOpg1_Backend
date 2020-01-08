
package dto;

import entities.Phone;
import io.swagger.v3.oas.annotations.media.Schema;

public class PhoneOutDTO {
    private int phoneID;
    
    @Schema(required = true, example = "12345678")
    private String phoneNumber;
    
    @Schema(required = true, example = "work phone")
    private String description;

    public PhoneOutDTO(Phone phone) {
        this.phoneNumber = phone.getPhoneNumber();
        this.description = phone.getDescription();
    }

    public PhoneOutDTO() {
    }
       
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
