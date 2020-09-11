package co.grandcircus.FinalProject;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import co.grandcircus.FinalProject.entity.GoogleResponse;
import co.grandcircus.FinalProject.entity.NearByPlaces;
import co.grandcircus.FinalProject.entity.PropertyResponse;

@Service
public class ApiService {
	
	RestTemplate rt=new RestTemplate();
	
	@Value("${api-Key}")
	public String apiKey;
	@Value("${key}")
	public String key;
	
	public PropertyResponse getAllProperties() {
		
		String url="https://realtor.p.rapidapi.com/properties/v2/list-for-sale?city=New%20York%20City&limit=200&offset=0&state_code=NY&price_min&rapidapi-key={apiKey}";
		PropertyResponse propertyResponse= rt.getForObject(url, PropertyResponse.class,apiKey);
		return propertyResponse;
	}
	
	public List<NearByPlaces> getAllGoogleSearch() {
		String url="https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=42.4606,-83.1346&key={key}&radius=1000";
	//	String url="https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=42.4606,-83.1346&key=AIzaSyDfO7vC2hX5xSfjZ5RBgC3M95vLuw8nHj8&radius=1000";
		List<NearByPlaces> places=rt.getForObject(url,GoogleResponse.class,key).getResults();
		return places;
	}

}
