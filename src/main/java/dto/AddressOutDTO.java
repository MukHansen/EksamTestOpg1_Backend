package dto;

import entities.Address;
import entities.CityInfo;
import io.swagger.v3.oas.annotations.media.Schema;

public class AddressOutDTO {
    private Integer addressID;
    @Schema(required = true, example = "some street")
    private String street;
    @Schema(required = true, example = "second floor")
    private String additionalInfo;
    @Schema(required = true, example = "[{\"zipcode\":\"1234\",\"city\":\"KBH\"}]")
    private CityInfoOutDTO cityInfo;

    public AddressOutDTO(Address address) {
        this.street = address.getStreet();
        this.additionalInfo = address.getAdditionalInfo();
        CityInfo ci = address.getCityInfo();
        this.cityInfo = new CityInfoOutDTO(ci);
    }

    public AddressOutDTO() {
    }

    public CityInfoOutDTO getCityInfo() {
        return cityInfo;
    }

    public void setCityInfo(CityInfoOutDTO cityInfo) {
        this.cityInfo = cityInfo;
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
}
