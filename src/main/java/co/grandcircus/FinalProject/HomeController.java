package co.grandcircus.FinalProject;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.grandcircus.FinalProject.dao.BuyerInformationDao;
import co.grandcircus.FinalProject.dao.FavoritesDao;
import co.grandcircus.FinalProject.dao.SavedSearchesDao;
import co.grandcircus.FinalProject.entity.BuyerInformation;
import co.grandcircus.FinalProject.entity.Favorites;
import co.grandcircus.FinalProject.entity.GoogleResponse;
import co.grandcircus.FinalProject.entity.NearByPlaces;
import co.grandcircus.FinalProject.entity.Property;
import co.grandcircus.FinalProject.entity.PropertyResponse;
import co.grandcircus.FinalProject.entity.SavedSearches;


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
	private BuyerInformationDao buyerInfoDao;

	@Autowired
	HttpSession session;

	@RequestMapping("/")
	public String showHome(Model model) {
		List<Property> properties = apiServ.getAllProperties().getProperties();
		List<NearByPlaces> places = apiServ.getAllGoogleSearch();
		List<String> states = apiServ.getStates();
		List<SavedSearches> searches = searchesDao.findAll();
		model.addAttribute("searches", searches);
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

		//model.addAttribute("city", city);
		model.addAttribute("city", (city.substring(0, 1).toUpperCase() + city.substring(1).toLowerCase()));
		model.addAttribute("state", state);

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

			session.setAttribute("minprice", minprice);
			session.setAttribute("maxprice", maxprice);
			session.setAttribute("beds", beds);
			session.setAttribute("baths", baths);
			session.setAttribute("city", city);
			session.setAttribute("state", state);
			model.addAttribute("properties", filteredProperties);
			model.addAttribute("city", (city.substring(0,1).toUpperCase()+city.substring(1).toLowerCase()));
			model.addAttribute("state", state);

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


		return "search-results";
	}

	@RequestMapping("/submit-details")
	public String showDetails(Model model, @RequestParam(required = false) String propertyId) {
		PropertyResponse property = apiServ.getPropertyByPropertyId(propertyId);
		GoogleResponse supermarkets = apiServ.getAllGoogleSearchBySupermarket(propertyId);
		GoogleResponse gyms = apiServ.getAllGoogleSearchByGym(propertyId);
		
		// Getting the list of Map Markers as string and passing to the page.
		// In the page, we will pass these markers in the map URL.
		// Maximum of 15 Map Markers allowed. We will show 1 property, 7 super markets and 7 gyms.
		// Getting the Map Markers for Super Markets:
		String sMarkers="markers=color:blue%7Clabel:S";
		for(int i=0;i<supermarkets.getResults().size();i++) {
			if (i==7) {break;}
			sMarkers+="%7C"+supermarkets.getResults().get(i).getGeometry().getLocation().getLat()+","+
					supermarkets.getResults().get(i).getGeometry().getLocation().getLng();
		}
		// Getting the Map Markers for Gyms:
		String gMarkers="markers=color:yellow%7Clabel:G";
		for(int i=0;i<gyms.getResults().size();i++) {
			if (i==7) {break;}
			gMarkers+="%7C"+gyms.getResults().get(i).getGeometry().getLocation().getLat()+","+
					gyms.getResults().get(i).getGeometry().getLocation().getLng();
		}
		model.addAttribute("property", property.getProperties());
		model.addAttribute("supermarkets", supermarkets.getResults());
		model.addAttribute("gyms", gyms.getResults());
		model.addAttribute("key", key);

		model.addAttribute("lat", property.getProperties().get(0).getAddress().getLat());
		model.addAttribute("lon", property.getProperties().get(0).getAddress().getLon());
		model.addAttribute("sMarkers",sMarkers);// passing the super market markers to the page
		model.addAttribute("gMarkers",gMarkers);// passing the gym markers to the pag
		
		session.removeAttribute("searchUrl");
		String searchUrl = "submit-details?propertyId=" + propertyId;
		session.setAttribute("searchUrl", searchUrl);
		

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

		
		String searchUrl = (String) session.getAttribute("searchUrl");
		//System.out.println("searchUrlExisting" + searchUrl);
		if (searchUrl != null) {

			return "redirect:/" + searchUrl;
		} else {
			return "/";
		}


		}
	
	
	@RequestMapping("/favorite-list")
	public String showfavoriteList(Model model) {
		// Recipe recipe[];
		List<Favorites> favs = new ArrayList<Favorites>();  
		favs = favsDao.findAll(); 
		model.addAttribute("properties", favs);
		return "favoriteList";

	}
	

	@RequestMapping("/removeFavorites")
	public String removeFavorite(@RequestParam String propertyId, Model model) {
		favsDao.deleteByPropertyId(propertyId);  
		return "redirect:/favorite-list";
	}
	
	@RequestMapping("/addSearch")
	public String addSavedSearch(Model model, @RequestParam String name) {
		SavedSearches newSearch = new SavedSearches();
		String searchUrl = (String) session.getAttribute("searchUrl");
		newSearch.setName(name);
		newSearch.setSearchUrl(searchUrl);
		searchesDao.save(newSearch);
		
		return "redirect:/" + searchUrl;
		}

	@RequestMapping("/removeSearch")
	public String removeSavedSearch(@RequestParam Long id, Model model) {
		searchesDao.deleteById(id);  
		return "redirect:/";
	}
	@RequestMapping("/contact-submit")
	public String showContactFrom(Model model, @RequestParam(required=false) String propertyId) {
		model.addAttribute("propertyId",propertyId);		
		return "contact-agent";
	}
	
	@PostMapping("/contact-submit")
	public String addUserDetails(BuyerInformation buyerInfo) {			
		String searchUrl = (String) session.getAttribute("searchUrl");		
		buyerInfoDao.save(buyerInfo);
		return "redirect:/" + searchUrl;
	}

}
