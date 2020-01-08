
package dto;
import entities.CityInfo;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "CityInfo")
public class CityInfoOutDTO {
    private int cityInfoID;
    
    @Schema(required = true, example = "1234")
    private int zipCode;
    @Schema(required = true, example = "KBH")
    private String city;

    public CityInfoOutDTO(CityInfo cityInfo) {
        this.zipCode = cityInfo.getZipCode();
        this.city = cityInfo.getCity();
    }

    public CityInfoOutDTO() {
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
    
    
}
