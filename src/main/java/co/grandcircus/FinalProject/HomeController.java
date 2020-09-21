package co.grandcircus.FinalProject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
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
import co.grandcircus.FinalProject.entity.User;
import co.grandcircus.FinalProject.dao.UserDao;

@Controller
public class HomeController {

	@Value("${key}")
	public String key;

	@Autowired
	private ApiService apiServ;

	@Autowired
	private FavoritesDao favsDao;
	@Autowired
	UserDao userDao;

	@Autowired
	private SavedSearchesDao searchesDao;

	@Autowired
	private BuyerInformationDao buyerInfoDao;

	@Autowired
	HttpSession session;

	@RequestMapping("/")
	public String showProperty(Model model) { 
		
		model.addAttribute("pets", (String) session.getAttribute("pets"));
		model.addAttribute("kids", (String) session.getAttribute("kids"));
		model.addAttribute("active", (String) session.getAttribute("active"));
		model.addAttribute("nightLife", (String) session.getAttribute("nightLife"));
		model.addAttribute("publicTransit", (String) session.getAttribute("publicTransit"));

 
		return "homepage";
	}

	@RequestMapping("/search")
	public String showPropertyList(@RequestParam String search, Model model) {
		
		try {
			if(search!=null && !search.isBlank())
			{
			String[] ctST=search.split("-");
			
		String state="";
		String city= ctST[0];
		state=ctST[1];
		if( ctST.length>1) {
		
		 state=ctST[1];
		} 
		 
			
		return "redirect:/submit-list?city="+city+"&state="+state;
			
			}else {
				model.addAttribute("errorMsg","city or state cannot be empty or null");
				return "error";
			}
			
		}
		catch(Exception e) {
			model.addAttribute("errorMsg",e.getMessage());
			return "error";
		}
	}
	
	@RequestMapping("/submit-list")
	public String showList(Model model, @RequestParam(required = false) String state,
			@RequestParam(required = false) String city) {
				
		List<Property> properties = apiServ.getProperiesByCityState(state, city).getProperties();
		model.addAttribute("properties", properties);
		model.addAttribute("city", (city.substring(0, 1).toUpperCase() + city.substring(1).toLowerCase()));
		model.addAttribute("state", state);
   //if(session.getAttribute("searchUrl")!=null)
   //{
		session.removeAttribute("searchUrl");
  // }

		String searchUrl = "submit-list?state=" + state + "&city=" + city;
		session.setAttribute("searchUrl", searchUrl);
		session.setAttribute("city", city);
		session.setAttribute("state", state);

		// setting the session variable ContactCalledFrom as this page.
		// because, after submitting the details in the contact agent form, 
		// we have to come back to this page:
		session.removeAttribute("ContactCalledFrom");
		session.setAttribute("ContactCalledFrom", searchUrl);
		
		return "search-results";
	}

	@RequestMapping("/search-result")
	public String showList(Model model, @RequestParam(required = false) String state,
			@RequestParam(required = false) String city, @RequestParam(required = false) Integer minprice,
			@RequestParam(required = false) Integer maxprice, @RequestParam(required = false) Integer beds,
			@RequestParam(required = false) Double baths) {
		try {
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

		// setting the session variable ContactCalledFrom as this page.
		// because, after submitting the details in the contact agent form, 
		// we have to come back to this page:
		session.removeAttribute("ContactCalledFrom");
		session.setAttribute("ContactCalledFrom", searchUrl);
		
		model.addAttribute("properties", filteredProperties);
		model.addAttribute("city", (city.substring(0, 1).toUpperCase() + city.substring(1).toLowerCase()));
		model.addAttribute("state", state);
		return "search-results";
		}catch(Exception e) {
			model.addAttribute("error message", e.getMessage());
			return "error";
		}	
	}

	@RequestMapping("/submit-details")
	public String showDetails(Model model, @RequestParam(required = false) String propertyId) {
		
		String pets = (String) session.getAttribute("pets");
		String kids = (String) session.getAttribute("kids");
		String active = (String) session.getAttribute("active");
		String nightLife = (String) session.getAttribute("nightLife");
		String publicTransit = (String) session.getAttribute("publicTransit");
		
		
		PropertyResponse property = apiServ.getPropertyByPropertyId(propertyId);
 
		
		Double lat=property.getProperties().get(0).getAddress().getLat();
		Double lon=property.getProperties().get(0).getAddress().getLon();
		


		GoogleResponse supermarkets = apiServ.getAllGoogleSearchBySupermarket(lat,lon);
        List<NearByPlaces> smSorted = supermarkets.getResults();
		Collections.sort(smSorted, Comparator.comparingDouble(NearByPlaces::getRating)); 
		Collections.reverse(smSorted);// sort by Descending Order
		
		GoogleResponse restaurants = apiServ.getAllGoogleSearchByRestaurants(lat,lon);
        List<NearByPlaces> restSorted = restaurants.getResults();  
		Collections.sort(restSorted, Comparator.comparingDouble(NearByPlaces::getRating));
		Collections.reverse(restSorted);//sort by Descending Order
		 
		if (pets != null) { 
			GoogleResponse petStores = apiServ.getAllGoogleSearchByPetStore(lat,lon);
			List<NearByPlaces> petSorted = petStores.getResults();
			Collections.sort(petSorted, Comparator.comparingDouble(NearByPlaces::getRating));
			Collections.reverse(petSorted);
			model.addAttribute("petstores", petSorted);
			}
		if (kids != null) {
			GoogleResponse schools = apiServ.getAllGoogleSearchByPrimarySchool(lat,lon);
			List<NearByPlaces> schoolsSorted = schools.getResults();
			Collections.sort(schoolsSorted, Comparator.comparingDouble(NearByPlaces::getRating));
			Collections.reverse(schoolsSorted);
			model.addAttribute("schools", schoolsSorted);
			}
		if (active != null) {
			GoogleResponse gyms = apiServ.getAllGoogleSearchByGym(lat,lon);
			List<NearByPlaces> gymSorted = gyms.getResults();
			Collections.sort(gymSorted, Comparator.comparingDouble(NearByPlaces::getRating));
			Collections.reverse(gymSorted);
			model.addAttribute("gyms", gymSorted);			
			}
		if (nightLife != null) {
			GoogleResponse bars = apiServ.getAllGoogleSearchByBar(lat,lon);
			List<NearByPlaces> barsSorted = bars.getResults();
			Collections.sort(barsSorted, Comparator.comparingDouble(NearByPlaces::getRating));
			Collections.reverse(barsSorted);
			model.addAttribute("bars", barsSorted);
			
			}
		if (publicTransit != null) {
			GoogleResponse transit = apiServ.getAllGoogleSearchByTransitStation(lat,lon);
			List<NearByPlaces> transitSorted = transit.getResults();
			Collections.sort(transitSorted, Comparator.comparingDouble(NearByPlaces::getRating));
			Collections.reverse(transitSorted);
			model.addAttribute("transits", transitSorted);
  
			}			 

		model.addAttribute("property", property.getProperties());
		model.addAttribute("supermarkets", smSorted);
		model.addAttribute("restaurants", restSorted);
		model.addAttribute("key", key);
		
		model.addAttribute("lat", lat);
		model.addAttribute("lon", lon);


		String searchUrl = (String) session.getAttribute("searchUrl"); 
		model.addAttribute("searchUrl", searchUrl);
		
		// setting the session attribute ContactCalledFrom as this page.
		// because, after submitting the details in the contact agent form, 
		// it has to be come back to this page:
		session.removeAttribute("ContactCalledFrom");
		session.setAttribute("ContactCalledFrom", "submit-details?propertyId=" + propertyId);
				 
		//adding this session variable for favorites as we are not saving the detail page url 
		// in searchurl session variable as we want to use searchurl for back to list.
		session.removeAttribute("favoriteCalledFrom");
		session.setAttribute("favoriteCalledFrom", "submit-details?propertyId=" + propertyId);
		
		return "details";
	}

	@RequestMapping("/addFavorites")
	public String addToFavoriteList(Model model, @RequestParam(required = false) String weburl,
			@RequestParam(required = false) String propertyId, @RequestParam(required = false) String thumbnail) {

		User user = (User) session.getAttribute("user");

		if (user != null) {

			model.addAttribute("userId", user.getId());

			Favorites newFav = new Favorites();
			Favorites existingFav = favsDao.findByPropertyIdAndUserId(propertyId, user.getId());

			if (existingFav == null) {
				newFav.setPropertyId(propertyId);
				newFav.setThumbnail(thumbnail);
				newFav.setWeburl(weburl);
				newFav.setUser(user);
			} else {
				newFav.setId(existingFav.getId());
				newFav.setPropertyId(propertyId);
				newFav.setThumbnail(thumbnail);
				newFav.setWeburl(weburl);
				newFav.setUser(user);
			}

			favsDao.save(newFav);

			String searchUrl = (String) session.getAttribute("searchUrl");
 
			if (searchUrl != null) {

				return "redirect:/" + searchUrl;
			} else {
				return "redirect:/";
			}

		} else {
			return "login";
		}

	}

	@RequestMapping("/favorite-list")
	public String showfavoriteList(Model model) {
		List<Favorites> favs = new ArrayList<Favorites>();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "login";
		}
		favs = favsDao.findByUserId(user.getId());
		model.addAttribute("properties", favs);

		// setting the session attribute ContactCalledFrom as this page.
		// because, after submitting the details in the contact agent form, 
		// it has to be come back to this page:
		session.removeAttribute("ContactCalledFrom");
		session.setAttribute("ContactCalledFrom", "favorite-list");
		
		return "favoriteList";

	}

	@RequestMapping("/removeFavorites")
	public String removeFavorite(Model model, @RequestParam Long id) {

		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "login";
		}
		favsDao.deleteById(id);
		return "redirect:/favorite-list";
	}

	@RequestMapping("/addSearch")
	public String addSavedSearch(Model model, @RequestParam String name) {
		User user = (User) session.getAttribute("user");
		if (user != null) {
			SavedSearches newSearch = new SavedSearches();
			String searchUrl = (String) session.getAttribute("searchUrl");
			SavedSearches existingSearch = searchesDao.findByNameAndUserIdAndSearchUrl(name, user.getId(), searchUrl);

			if (existingSearch == null) {
				newSearch.setName(name);
				newSearch.setSearchUrl(searchUrl);
				newSearch.setUser(user);
			} else {
				newSearch.setId(existingSearch.getId());
				newSearch.setName(existingSearch.getName());
				newSearch.setSearchUrl(searchUrl);
				newSearch.setUser(user);
			}
			searchesDao.save(newSearch);
			return "redirect:/" + searchUrl;
		} else {
			return "login";
		}
	}

	@RequestMapping("/removeSearch")
	public String removeSavedSearch(@RequestParam Long id, Model model) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "login";
		}
		searchesDao.deleteById(id);
		return "redirect:/saved-searches";
	}

	@RequestMapping("/contact-submit")
	public String showContactFrom(Model model, @RequestParam(required = false) String propertyId) {
		User user = (User) session.getAttribute("user");

		if (user != null) {
			BuyerInformation existingBI = buyerInfoDao.findByUserIdAndPropertyId(user.getId(), propertyId);
			if (existingBI != null) {
				model.addAttribute("existingBI", existingBI);
			}
			model.addAttribute("userId", user.getId());
			model.addAttribute("name", user.getUsername());
			model.addAttribute("email", user.getEmail());
			model.addAttribute("phoneno", user.getPhone());
			model.addAttribute("propertyId", propertyId);
			return "contact-agent";
		} else {
			// Capturing the current form into the session variable ContactForm
			// because, after login, it has to come back to contact form.
			session.setAttribute("ContactForm", "contact-submit?propertyId=" + propertyId);
			return "login";
		}
	}

	@PostMapping("/contact-submit")
	public String addUserDetails(BuyerInformation buyerInfo) {

		User user = (User) session.getAttribute("user");

		BuyerInformation newBI = new BuyerInformation();
		BuyerInformation existingBI = buyerInfoDao.findByUserIdAndPropertyId(user.getId(), buyerInfo.getPropertyId());

		if (existingBI == null) {
			newBI.setName(user.getUsername());
			newBI.setEmail(user.getEmail());
			newBI.setPhone(user.getPhone());
			newBI.setComments(buyerInfo.getComments());
			newBI.setQuote(buyerInfo.getQuote());
			newBI.setPropertyId(buyerInfo.getPropertyId());
			newBI.setUser(user);
		} else {
			newBI.setId(existingBI.getId());
			newBI.setName(user.getUsername());
			newBI.setEmail(user.getEmail());
			newBI.setPhone(user.getPhone());
			newBI.setComments(buyerInfo.getComments());
			newBI.setQuote(buyerInfo.getQuote());
			newBI.setPropertyId(buyerInfo.getPropertyId());
			newBI.setUser(user);
		}

		buyerInfoDao.save(newBI);

		// we need to go back to the calling page. The Url was saved in the session
		// attribute ContactCalledFrom. After taking the url into a variable, 
		// session attribute is removed.
		String calledFrom = (String) session.getAttribute("ContactCalledFrom");
		session.removeAttribute("ContactCalledFrom");
		return "redirect:/" + calledFrom;
	}
	
	@RequestMapping("/saveLifestyle")
	public String addToLifestyle(Model model, @RequestParam(required = false) String pets,
			@RequestParam(required = false) String kids, @RequestParam(required = false) String active, @RequestParam(required = false) String nightLife, @RequestParam(required = false) String publicTransit) {

		session.setAttribute("pets", pets);
		session.setAttribute("kids", kids);
		session.setAttribute("active", active);
		session.setAttribute("nightLife", nightLife);
		session.setAttribute("publicTransit", publicTransit);
	
				return "redirect:/";
	}
	
	@RequestMapping("/saved-searches")
	public String showSearches(Model model) {	
		List<SavedSearches> searches = new ArrayList<SavedSearches>();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "login";
		}
		searches = searchesDao.findByUserId(user.getId());
		model.addAttribute("searches", searches);

		// setting the session attribute ContactCalledFrom as this page.
		// because, after submitting the details in the contact agent form, 
		// it has to be come back to this page:
		session.removeAttribute("ContactCalledFrom");
		session.setAttribute("ContactCalledFrom", "favorite-list");
		
		return "saved-searches";
	}
	
	@RequestMapping("/updateLifestyle")
	public String updateLifestyle(Model model,@RequestParam(required = false) String propertyId, @RequestParam(required = false) String pets,
			@RequestParam(required = false) String kids, @RequestParam(required = false) String active, @RequestParam(required = false) String nightLife, @RequestParam(required = false) String publicTransit) {

		session.setAttribute("pets", pets);
		session.setAttribute("kids", kids);
		session.setAttribute("active", active);
		session.setAttribute("nightLife", nightLife);
		session.setAttribute("publicTransit", publicTransit);
		session.setAttribute("propertyId", propertyId);
		
	
				return "redirect:/submit-details?propertyId=" + propertyId;
				
	}

}
