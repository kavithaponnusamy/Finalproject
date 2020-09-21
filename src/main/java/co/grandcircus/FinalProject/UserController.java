package co.grandcircus.FinalProject;

import javax.servlet.http.HttpSession;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.grandcircus.FinalProject.dao.UserDao;
import co.grandcircus.FinalProject.entity.User;

@Controller
public class UserController {

	@Autowired
	UserDao userDao;
	@Autowired
	HttpSession session;

	@RequestMapping("/login")
	public String showLoginForm() {
		return "login";
	}

	@PostMapping("/login")
	// get the username and password from the form when it's submitted.
	public String submitLoginForm(@RequestParam("username") String username, @RequestParam("password") String password,
			Model model, RedirectAttributes redir) {
		// Find the matching user.
		User user = userDao.findByUsername(username);

		// Password decoded

		byte[] decodedBytes = Base64.getDecoder().decode(user.getPassword());
		String decodedUserPasswordString = new String(decodedBytes);

		if (user == null || !password.equals(decodedUserPasswordString)) {
			// If the user or password don't match, display an error message.
			model.addAttribute("message", "Incorrect username or password.");
			return "login";
		}

		// On successful login, add the user to the session.
		session.setAttribute("user", user);

		// A flash message will only show on the very next page. Then it will go away.
		// It is useful with redirects since you can't add attributes to the mav.
		redir.addFlashAttribute("message", "Logged in.");

		String searchUrl = (String) session.getAttribute("searchUrl");
		System.out.println("searchUrlExisting" + searchUrl);

		// If the login page is called from the Contact Agent Form, it has to go back
		// to Contact Agent Form whose URL has been saved in the session attribute
		// ContactForm:
		// If the login page is called from any other form, the session attribute
		// searchUrl
		// is used to go back to the calling page:
		if (session.getAttribute("ContactForm") != null) {
			String contactFormUrl = (String) session.getAttribute("ContactForm");
			session.removeAttribute("ContactForm");
			return "redirect:/" + contactFormUrl;
		}

		// If the login page is called for Add Favorites, it has to go back to detail or
		// list form
		if (session.getAttribute("favoriteCalledFrom") != null) {
			String favoriteFormUrl = (String) session.getAttribute("favoriteCalledFrom");
			session.removeAttribute("favoriteCalledFrom");
			return "redirect:/" + favoriteFormUrl;
		}

		if (searchUrl != null) {
			return "redirect:/" + searchUrl;
		} else {
			return "redirect:/";
		}
	}

	@RequestMapping("/logout")
	public String logout(RedirectAttributes redir) {
		// invalidate clears the current user session and starts a new one.
		session.invalidate();

		// A flash message will only show on the very next page. Then it will go away.
		// It is useful with redirects since you can't add attributes to the mav.
		redir.addFlashAttribute("message", "Logged out.");
		return "redirect:/";
	}

	@RequestMapping("/signup")
	public String showSignupForm() {
		return "signup";
	}

	@PostMapping("/signup")
	public String submitSignupForm(User user, @RequestParam("confirm-password") String confirmPassword, Model model,
			RedirectAttributes redir) {
		// Find the matching user.
		User existingUser = userDao.findByUsername(user.getUsername());

		if (existingUser != null) {
			// If user already exists, display an error message.
			model.addAttribute("message", "A user with that username already exists.");
			return "signup";
		}

		if (!confirmPassword.equals(user.getPassword())) {
			// If the user or passwords don't match, display an error message.
			model.addAttribute("message", "Passwords do not match.");
			return "signup";
		}

		// password encoded
		String encodedPassword = Base64.getEncoder().encodeToString(user.getPassword().getBytes());
		user.setPassword(encodedPassword);
		userDao.save(user);

		// On successful sign-up, add the user to the session.
		session.setAttribute("user", user);

		// A flash message will only show on the very next page. Then it will go away.
		// It is useful with redirects since you can't add attributes to the mav.
		redir.addFlashAttribute("message", "Thanks for signing up!");
		String searchUrl = (String) session.getAttribute("searchUrl");
		// System.out.println("searchUrlExisting" + searchUrl);
		if (searchUrl != null) {

			return "redirect:/" + searchUrl;
		} else {
			return "redirect:/";
		}
	}

}
