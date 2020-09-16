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
	public String showHome(Model model) {
		//List<Property> properties = apiServ.getAllProperties().getProperties();
		List<NearByPlaces> places = apiServ.getAllGoogleSearch();
		List<String> states = apiServ.getStates();
		List<SavedSearches> searches = searchesDao.findAll();
		model.addAttribute("searches", searches);
		model.addAttribute("states", states);
		//model.addAttribute("properties", properties);
		model.addAttribute("places", places);
		return "homepage";
	}

	@RequestMapping("/submit-list")
	public String showList(Model model, @RequestParam(required = false) String state,
			@RequestParam(required = false) String city) {
		//session.invalidate();

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

		User user = (User) session.getAttribute("user");

		if (user != null) {

			model.addAttribute("userId", user.getId());

			Favorites newFav = new Favorites();
			// Favorites existingFav = favsDao.findByPropertyId(propertyId);
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
			// System.out.println("searchUrlExisting" + searchUrl);
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
		// favs = favsDao.findAll();
		favs = favsDao.findByUserId(user.getId());
		model.addAttribute("properties", favs);
		return "favoriteList";


	}
	

	@RequestMapping("/removeFavorites")
	public String removeFavorite( Model model,@RequestParam Long id) {

		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "login";
		}

		// favsDao.deleteByPropertyId(propertyId);
		//favsDao.deleteByPropertyIdAndUserId(propertyId, user.getId());
		favsDao.deleteById(id);
		return "redirect:/favorite-list";
	}
	
	@RequestMapping("/addSearch")
	public String addSavedSearch(Model model, @RequestParam String name) {
		User user = (User) session.getAttribute("user");
		if (user != null) {
			SavedSearches newSearch = new SavedSearches();
			// Favorites existingFav = favsDao.findByPropertyIdAndUserId(propertyId,
			// user.getId());
			String searchUrl = (String) session.getAttribute("searchUrl");
			SavedSearches existingSearch = searchesDao.findByNameAndUserIdAndSearchUrl(name, user.getId(),searchUrl);
			
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
		return "redirect:/";
	}


	@RequestMapping("/contact-submit")
	public String showContactFrom(Model model, @RequestParam(required=false) String propertyId) {
		User user = (User) session.getAttribute("user");
		
		// searchUrl will be changed if the login page is called.
		// so, saving the calling page URL in a session attribute searchUrlMain:
		String searchUrlMain = (String) session.getAttribute("searchUrlMain");		
		if (searchUrlMain == null ) {
			session.setAttribute("searchUrlMain",session.getAttribute("searchUrl"));
		}
		
		if (user != null) {
			
			BuyerInformation existingBI = buyerInfoDao.findByUserIdAndPropertyId(user.getId(),propertyId);
			if (existingBI != null) {
				model.addAttribute("existingBI", existingBI);
			}
			model.addAttribute("userId", user.getId());
			System.out.println(user.getId());
			model.addAttribute("propertyId", propertyId);
			return "contact-agent";
		} else {
			//session.setAttribute("searchUrl", searchUrl);
			// Changing the searchUrl to contact form. because, after login, it has to come back to contact form.
			session.setAttribute("searchUrl", "contact-submit?propertyId=" + propertyId);	
			return "login";
		}
	}
	
	@PostMapping("/contact-submit")
	public String addUserDetails(BuyerInformation buyerInfo ) {			
		
		User user = (User) session.getAttribute("user");

		BuyerInformation newBI = new BuyerInformation();
		BuyerInformation existingBI = buyerInfoDao.findByUserIdAndPropertyId(user.getId(),buyerInfo.getPropertyId());
		
		if (existingBI == null) {
			newBI.setComments(buyerInfo.getComments());
			newBI.setQuote(buyerInfo.getQuote());
			newBI.setPropertyId(buyerInfo.getPropertyId());
			newBI.setUser(user);
		} else {
			newBI.setId(existingBI.getId());;
			newBI.setComments(buyerInfo.getComments());
			newBI.setQuote(buyerInfo.getQuote());
			newBI.setPropertyId(buyerInfo.getPropertyId());
			newBI.setUser(user);
		}
				
		buyerInfoDao.save(newBI);

		// we need to go back to the calling page. the Url was saved in the session attribute searchUrlMain
		// after taking the url into a variable, session attribute is removed.
		String searchUrlMain = (String) session.getAttribute("searchUrlMain");
		session.removeAttribute("searchUrlMain");
		return "redirect:/" + searchUrlMain;
	}

}
