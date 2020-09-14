package co.grandcircus.FinalProject;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.grandcircus.FinalProject.entity.NearByPlaces;
import co.grandcircus.FinalProject.entity.Property;
import co.grandcircus.FinalProject.entity.PropertyResponse;
import co.grandcircus.FinalProject.entity.State;

@Controller
public class HomeController {
	
	@Value("${key}")
	public String key;
	
	@Autowired
	private ApiService apiServ;
	
	@RequestMapping("/")
	public String showHome(Model model) {
		List<Property> properties=apiServ.getAllProperties().getProperties();
		List<NearByPlaces> places=apiServ.getAllGoogleSearch();
		List<String> states=apiServ.getStates();
		model.addAttribute("states",states);
		model.addAttribute("properties",properties);
		model.addAttribute("places",places);
		return "homepage";
	}
	@RequestMapping("/submit-list")
	public String showList(Model model, @RequestParam(required=false) String state, 
										@RequestParam(required=false) String city) {
		List<Property> properties=apiServ.getProperiesByCityState(state, city).getProperties();
				model.addAttribute("properties", properties);
				model.addAttribute("city", city);
				model.addAttribute("state", state);
		return "search-results";	
	} 
	
	@RequestMapping("/search-result")
	public String showList(Model model, @RequestParam(required=false) String state, 
										@RequestParam(required=false) String city,
										@RequestParam(required=false) Double minprice,
										@RequestParam(required=false) Double maxprice,
										@RequestParam(required=false) Integer beds,
										@RequestParam(required=false) Double baths) {
		List<Property> properties=apiServ.getProperiesByCityState(state, city).getProperties();
		List<Property> filteredProperties=new ArrayList<>();
		boolean boo;
			for (int i=0; i<properties.size(); i++) {
				boo=true;
				if ( minprice > 0){
					if (properties.get(i).getPrice()>= minprice) {
						boo = true;
					}else{
						boo = false;
					}
				}
				if ( boo && maxprice > 0){
					if (properties.get(i).getPrice()<= maxprice) {
						boo = true;
					}else{
						boo = false;
					}
				}
				if ( boo && beds > 0 ){
					if (properties.get(i).getBeds() != null && properties.get(i).getBeds()>= beds) {
						boo = true;
					}else{
						boo = false;
					}
				}
				if ( boo && baths > 0){
					if ( properties.get(i).getBaths() != null && properties.get(i).getBaths()>= baths) {
						boo = true;
					}else{
						boo = false;
					}
				}
				if (boo) {
					filteredProperties.add(properties.get(i));
				}
			}
			model.addAttribute("properties", filteredProperties);
			model.addAttribute("city", city);
			model.addAttribute("state", state);

		return "search-results";
	} 

	@RequestMapping("/submit-details")
	public String showDetails(Model model, @RequestParam(required=false) String propertyId) {
		PropertyResponse property=apiServ.getPropertyByPropertyId(propertyId);
		
		System.out.println(property);
		model.addAttribute("property",property.getProperties());
		model.addAttribute("key", key);
		return "details";
	}
	

}
