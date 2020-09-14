package co.grandcircus.FinalProject;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.grandcircus.FinalProject.dao.FavoritesDao;
import co.grandcircus.FinalProject.dao.SavedSearchesDao;
import co.grandcircus.FinalProject.entity.Favorites;
import co.grandcircus.FinalProject.entity.GoogleResponse;
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

	@Autowired
	private FavoritesDao favsDao;

	@Autowired
	private SavedSearchesDao searchesDao;

	@Autowired
	HttpSession session;

	@RequestMapping("/")
	public String showHome(Model model) {
		List<Property> properties = apiServ.getAllProperties().getProperties();
		List<NearByPlaces> places = apiServ.getAllGoogleSearch();
		List<String> states = apiServ.getStates();
		model.addAttribute("states", states);
		model.addAttribute("properties", properties);
		model.addAttribute("places", places);
		return "homepage";
	}

	@RequestMapping("/submit-list")
	public String showList(Model model, @RequestParam(required = false) String state,
			@RequestParam(required = false) String city) {
		session.invalidate();

		List<Property> properties = apiServ.getProperiesByCityState(state, city).getProperties();
		model.addAttribute("properties", properties);

		model.addAttribute("city", city);
		model.addAttribute("state", state);

		// session.setAttribute("sumbitListPropertyResponse", propertyResponse);
		session.removeAttribute("searchUrl");

		String searchUrl = "submit-list?state=" + state + "&city=" + city;
		session.setAttribute("searchUrl", searchUrl);
		session.setAttribute("city", city);
		session.setAttribute("state", state);
		return "search-results";
	}

	@RequestMapping("/search-result")
	public String showList(Model model, @RequestParam(required = false) String state,
			@RequestParam(required = false) String city, @RequestParam(required = false) Integer minprice,
			@RequestParam(required = false) Integer maxprice, @RequestParam(required = false) Integer beds,
			@RequestParam(required = false) Double baths) {
		List<Property> properties = apiServ.getProperiesByCityState(state, city).getProperties();

		List<Property> filteredProperties = new ArrayList<>();
		boolean boo;
		for (int i = 0; i < properties.size(); i++) {
			boo = true;
			if (minprice > 0) {
				if (properties.get(i).getPrice() >= minprice) {
					boo = true;
				} else {
					boo = false;
				}
			}
			if (boo && maxprice > 0) {
				if (properties.get(i).getPrice() <= maxprice) {
					boo = true;
				} else {
					boo = false;
				}
			}
			if (boo && beds > 0) {
				if (properties.get(i).getBeds() != null && properties.get(i).getBeds() >= beds) {
					boo = true;
				} else {
					boo = false;
				}
			}
			if (boo && baths > 0) {
				if (properties.get(i).getBaths() != null && properties.get(i).getBaths() >= baths) {
					boo = true;
				} else {
					boo = false;
				}
			}
<<<<<<< HEAD
			session.setAttribute("minprice", minprice);
			session.setAttribute("maxprice", maxprice);
			session.setAttribute("beds", beds);
			session.setAttribute("baths", baths);
			session.setAttribute("city", city);
			session.setAttribute("state", state);
			model.addAttribute("properties", filteredProperties);
			model.addAttribute("city", (city.substring(0,1).toUpperCase()+city.substring(1).toLowerCase()));
			model.addAttribute("state", state);
=======
			if (boo) {
				filteredProperties.add(properties.get(i));
			}
		}

		session.removeAttribute("searchUrl");

		String searchUrl = "search-result?state=" + state + "&city=" + city + "&minprice=" + minprice + "&maxprice="
				+ maxprice + "&beds=" + beds + "&baths=" + baths;
		session.setAttribute("searchUrl", searchUrl);
		session.setAttribute("city", city);
		session.setAttribute("state", state);

		model.addAttribute("properties", filteredProperties);
		model.addAttribute("city", (city.substring(0, 1).toUpperCase() + city.substring(1).toLowerCase()));
		model.addAttribute("state", state);
>>>>>>> c7d2689bdf770421d0a6e5010aae6813136538b6

		return "search-results";
	}

	@RequestMapping("/submit-details")
	public String showDetails(Model model, @RequestParam(required = false) String propertyId) {
		PropertyResponse property = apiServ.getPropertyByPropertyId(propertyId);
		GoogleResponse supermarkets = apiServ.getAllGoogleSearchBySupermarket(propertyId);
		GoogleResponse gyms = apiServ.getAllGoogleSearchByGym(propertyId);
		model.addAttribute("property", property.getProperties());
		model.addAttribute("supermarkets", supermarkets.getResults());
		model.addAttribute("gyms", gyms.getResults());
		model.addAttribute("key", key);
		return "details";
	}

	@RequestMapping("/addFavorites")
	public String addToFavoriteList(Model model, @RequestParam(required = false) String weburl,
			@RequestParam(required = false) String propertyId, @RequestParam(required = false) String thumbnail) {
		Favorites newFav = new Favorites();
		Favorites existingFav = favsDao.findByPropertyId(propertyId);

		if (existingFav == null) {
			newFav.setPropertyId(propertyId);
			newFav.setThumbnail(thumbnail);
			newFav.setWeburl(weburl);
		} else {
			newFav.setId(existingFav.getId());
			newFav.setPropertyId(propertyId);
			newFav.setThumbnail(thumbnail);
			newFav.setWeburl(weburl);
		}

		favsDao.save(newFav);
<<<<<<< HEAD
		String state = (String) session.getAttribute("state");
		String city = (String) session.getAttribute("city");
		String url = "redirect:/submit-list?city=" + city +"&state="+ state;
		//return "redirect:/submit-list?city=" + city +"&state="+ state;

		Double minPrice =  (Double) session.getAttribute("minprice");
		Double maxPrice = (Double) session.getAttribute("maxprice");
		Integer beds= (Integer) session.getAttribute("beds");
		Double baths= (Double) session.getAttribute("baths");
		if (minPrice!=null){
			url += "&minprice="+minPrice+"&maxprice="+maxPrice+"&beds="+beds+"&baths="+baths;
		}		
		return url;
=======

		String searchUrl = (String) session.getAttribute("searchUrl");
		System.out.println("searchUrlExisting" + searchUrl);
		if (searchUrl != null) {

			return "redirect:/" + searchUrl;
		} else {
			return "/";
		}

		// return "redirect:/ExistingList";
>>>>>>> c7d2689bdf770421d0a6e5010aae6813136538b6
	}
		
	

}
