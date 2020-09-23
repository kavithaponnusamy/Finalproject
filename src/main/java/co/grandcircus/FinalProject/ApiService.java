package co.grandcircus.FinalProject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import co.grandcircus.FinalProject.entity.AutoCompleteResponse;
import co.grandcircus.FinalProject.entity.GoogleResponse;
import co.grandcircus.FinalProject.entity.NearByPlaces;

import co.grandcircus.FinalProject.entity.PropertyResponse;
import co.grandcircus.FinalProject.entity.AutoCompleteResponse;


@Service
public class ApiService {
	
	RestTemplate rt=new RestTemplate();
	
	@Value("${api-Key}")
	public String apiKey;
	@Value("${key}")
	public String key;
 
	public AutoCompleteResponse getCityStateResponse(String citytext ) {		 
		String url="https://realtor.p.rapidapi.com/locations/auto-complete?input={citytext}&rapidapi-key={apiKey}";
		AutoCompleteResponse response = rt.getForObject(url, AutoCompleteResponse.class,citytext,apiKey);
		return response;

	}	
		
	public PropertyResponse getProperiesByCityState(String state_code,String city) {		
		String url="https://realtor.p.rapidapi.com/properties/v2/list-for-sale?city={city}&limit=15&offset=0&state_code={state_code}&price_min&rapidapi-key={apiKey}";
		PropertyResponse propertyResponse= rt.getForObject(url, PropertyResponse.class,city, state_code,apiKey);
		return propertyResponse;
	}	
 
	public PropertyResponse getPropertyByPropertyId(String property_id) {
		String url="https://realtor.p.rapidapi.com/properties/v2/detail?property_id={property_id}&rapidapi-key={apiKey}";
		PropertyResponse property=rt.getForObject(url, PropertyResponse.class,property_id,apiKey);
		return property;
	}
	

 
	public GoogleResponse getAllGoogleSearchBySupermarket(Double lat, Double lon) { 
		
		String url="https://maps.googleapis.com/maps/api/place/nearbysearch/json?location={lat},{lon}&key={key}&radius=10000&type=supermarket";
		GoogleResponse supermarkets=rt.getForObject(url,GoogleResponse.class,lat,lon,key);
		return supermarkets;
	}
	
 
	public GoogleResponse getAllGoogleSearchByGym(Double lat, Double lon) {
		 
		String url="https://maps.googleapis.com/maps/api/place/nearbysearch/json?location={lat},{lon}&key={key}&radius=10000&type=gym";
		GoogleResponse gyms=rt.getForObject(url,GoogleResponse.class,lat,lon,key);
		return gyms;
	}
  
	public GoogleResponse getAllGoogleSearchByRestaurants(Double lat, Double lon) { 
		String url="https://maps.googleapis.com/maps/api/place/nearbysearch/json?location={lat},{lon}&key={key}&radius=10000&type=restaurant";
		GoogleResponse restaurants=rt.getForObject(url,GoogleResponse.class,lat,lon,key);
		return restaurants;
	}
 
	 
	public GoogleResponse getAllGoogleSearchByPetStore(Double lat, Double lon) {
		 
		String url="https://maps.googleapis.com/maps/api/place/nearbysearch/json?location={lat},{lon}&key={key}&radius=10000&type=pet_store";
		GoogleResponse petStore=rt.getForObject(url,GoogleResponse.class,lat,lon,key);
		return petStore;
	}
	
 
	public GoogleResponse getAllGoogleSearchByTransitStation(Double lat, Double lon) { 
		String url="https://maps.googleapis.com/maps/api/place/nearbysearch/json?location={lat},{lon}&key={key}&radius=10000&type=transit_station";
		GoogleResponse transitStation=rt.getForObject(url,GoogleResponse.class,lat,lon,key);
		return transitStation;
	}
	 
	public GoogleResponse getAllGoogleSearchByPrimarySchool(Double lat, Double lon) { 
		String url="https://maps.googleapis.com/maps/api/place/nearbysearch/json?location={lat},{lon}&key={key}&radius=10000&type=primary_school";
		GoogleResponse primarySchool=rt.getForObject(url,GoogleResponse.class,lat,lon,key);
		return primarySchool;
	}

 
	public GoogleResponse getAllGoogleSearchByPark(Double lat, Double lon) { 
		String url="https://maps.googleapis.com/maps/api/place/nearbysearch/json?location={lat},{lon}&key={key}&radius=10000&type=park";
		GoogleResponse park=rt.getForObject(url,GoogleResponse.class,lat,lon,key);
		return park;

	}
	 
	public GoogleResponse getAllGoogleSearchByBar(Double lat, Double lon) { 
		String url="https://maps.googleapis.com/maps/api/place/nearbysearch/json?location={lat},{lon}&key={key}&radius=10000&type=bar";
		GoogleResponse bar=rt.getForObject(url,GoogleResponse.class,lat,lon,key);
		return bar;
	}
	
 
	public GoogleResponse getAllGoogleSearchByNightClub(Double lat, Double lon) { 
		String url="https://maps.googleapis.com/maps/api/place/nearbysearch/json?location={lat},{lon}&key={key}&radius=10000&type=night_club";
		GoogleResponse nightClub=rt.getForObject(url,GoogleResponse.class,lat,lon,key);
		return nightClub;
	}
	
 
	public GoogleResponse getAllGoogleSearchByVet(Double lat, Double lon) { 
		String url="https://maps.googleapis.com/maps/api/place/nearbysearch/json?location={lat},{lon}&key={key}&radius=10000&type=veterinary_care";
		GoogleResponse vet=rt.getForObject(url,GoogleResponse.class,lat,lon,key);
		return vet;
	}
	
	public GoogleResponse getCityLatLong(String city, String state_code) {
		 
		String url="https://maps.googleapis.com/maps/api/geocode/json?address={city},{state_code}&key={key}";
		GoogleResponse cityLatLong=rt.getForObject(url,GoogleResponse.class,city,state_code,key);
		return cityLatLong;
	}


}
