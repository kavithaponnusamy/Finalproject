package co.grandcircus.FinalProject.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AutoCompleteResponse {

	@JsonProperty("autocomplete")
	private List<CityAndStateInfo> CityAndStateInfo; 
	
	
	public List<CityAndStateInfo> getCityAndStateInfo() {
		return CityAndStateInfo;
	} 


	public void setCityAndStateInfo(List<CityAndStateInfo> cityAndStateInfo) {
		CityAndStateInfo = cityAndStateInfo;
	}

}


