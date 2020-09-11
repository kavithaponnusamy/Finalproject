package co.grandcircus.FinalProject;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.grandcircus.FinalProject.entity.NearByPlaces;
import co.grandcircus.FinalProject.entity.Property;
import co.grandcircus.FinalProject.entity.State;

@Controller
public class HomeController {
	
	
	@Autowired
	private ApiService apiServ;
	
	@RequestMapping("/")
	public String showHome(Model model) {
		List<Property> properties=apiServ.getAllProperties().getProperties();
		List<NearByPlaces> places=apiServ.getAllGoogleSearch();
		List<String> states=apiServ.getStates();
		System.out.println(states);
		model.addAttribute("states",states);
		System.out.println(places);
		model.addAttribute("properties",properties);
		model.addAttribute("places",places);
		return "homepage";
	}
	@RequestMapping("/details")
	public String showDetails(Model model, @RequestParam(required=false) String state_code, @RequestParam(required=false) String city) {
		List<Property> properties=apiServ.getProperiesByCityState(state_code, city).getProperties();
		model.addAttribute("properties", properties);
		return "details";
		
	}
	

}
