package co.grandcircus.FinalProject;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import co.grandcircus.FinalProject.entity.GoogleResponse;
import co.grandcircus.FinalProject.entity.NearByPlaces;
import co.grandcircus.FinalProject.entity.Property;
import co.grandcircus.FinalProject.entity.PropertyResponse;
import co.grandcircus.FinalProject.entity.State;

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
		List<NearByPlaces> places=rt.getForObject(url,GoogleResponse.class,key).getResults();
		return places;
	}
	public PropertyResponse getProperiesByCityState(String state_code,String city) {		
		String url="https://realtor.p.rapidapi.com/properties/v2/list-for-sale?city={city}&limit=200&offset=0&state_code={state_code}&price_min&rapidapi-key={apiKey}";
		PropertyResponse propertyResponse= rt.getForObject(url, PropertyResponse.class,city, state_code,apiKey);
		return propertyResponse;
	}
	public List<String> getStates() {
		String url="https://worldpopulationreview.com/static/states/abbr-list.json";
		String[] states=rt.getForObject(url, String[].class);
		return Arrays.asList(states);
		
	}
	public PropertyResponse getPropertyByPropertyId(String property_id) {
		String url="https://realtor.p.rapidapi.com/properties/v2/detail?property_id={property_id}&rapidapi-key={apiKey}";
		PropertyResponse property=rt.getForObject(url, PropertyResponse.class,property_id,apiKey);
		System.out.println(property);
		return property;
	}

}