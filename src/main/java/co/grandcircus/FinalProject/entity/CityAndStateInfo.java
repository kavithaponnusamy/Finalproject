package co.grandcircus.FinalProject.entity;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CityAndStateInfo{
	public String city;
	@JsonProperty("state_code")
    public String state;
    
    
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	} 
}