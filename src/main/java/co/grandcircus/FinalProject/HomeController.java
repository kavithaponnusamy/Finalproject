package co.grandcircus.FinalProject;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import co.grandcircus.FinalProject.entity.NearByPlaces;
import co.grandcircus.FinalProject.entity.Property;

@Controller
public class HomeController {
	
	
	@Autowired
	private ApiService apiServ;
	
	@RequestMapping("/")
	public String showHome(Model model) {
		List<Property> properties=apiServ.getAllProperties().getProperties();
		List<NearByPlaces> places=apiServ.getAllGoogleSearch();
		System.out.println(places);
		model.addAttribute("properties",properties);
		model.addAttribute("places",places);
		return "homepage";
	}
	

}
