package com.soro.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.soro.entity.User;

import com.soro.repository.UserRepository;


//Controlleur qui gere l'authentification
@RestController
@CrossOrigin(value = "*")
public class AuthenticationController {

	@Autowired
	UserRepository userRepository;

	// Method qui permet d enregistrer un user
	@RequestMapping(value = "/register", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView register(@Valid @ModelAttribute("userLogin") User userLogin, BindingResult result) {
		ModelAndView model = new ModelAndView();
		if (this.findUserByMail(userLogin.getMail())) {

			if (result.hasErrors()) {
				model = new ModelAndView();
				model.setViewName("registerView");// Renvoie la page thymleaf
			} else {

				this.userRepository.save(userLogin); // Enregistre l user dans la DB
				model = new ModelAndView("redirect:/login");
			}

		} else {
			model = new ModelAndView();
			model.addObject("errormsg", "Mail déjà existant");
			model.setViewName("registerView");// Renvoie la page thymleaf
		}

		return model;

	}

	// Method qui permet d enregistrer un user
	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView login(@ModelAttribute("userLogin") User userLogin, HttpSession session) {
		User userConfirmed = this.userRepository.findByMailAndPassword(userLogin.getMail(), userLogin.getPassword());
		session.setAttribute("critere", "lieu");

		if (userConfirmed != null) {// On verifie si userConfirm est different de nul pr savoir si l utilisateur
									// existe
			session.setAttribute("userSession", userConfirmed);// On crée une variable de session a l aide de l objet
			ModelAndView model = new ModelAndView("redirect:/home"); // userConfirm qui sera dispo ds toute l appli
			return model;
		} else {
			ModelAndView model = new ModelAndView();
			model.addObject("errormsg", "Identifiant incorrect !");
			model.setViewName("loginView");// Renvoie la page thymleaf
			return model;
		}

	}

	// Method qui renvoie la liste de tous les users
	@RequestMapping(value = { "/users" }, method = RequestMethod.GET)
	public ModelAndView users() {
		List<User> users = this.userRepository.findAll();
		ModelAndView model = new ModelAndView();
		model.addObject("listUsers", users);
		model.setViewName("listOfUsersView");
		return model;
	}

	// Method qui affiche les details de l utilisateur
	@GetMapping(value = "/users/{id}")
	public ModelAndView userDetail(@PathVariable(value = "id") Long id) {
		User user = this.userRepository.getUserById(id);
		ModelAndView model = new ModelAndView();
		model.addObject("user", user);
		model.setViewName("userDetailView");
		return model;
	}

	// Method pour ce deconecter
	@GetMapping(value = "/logout")
	public ModelAndView logout(HttpSession session) {
		ModelAndView model = new ModelAndView();
		session.setAttribute("userSession", null);// Reatribue null a lauserSession pr logout
		model.setViewName("redirect:/home");
		return model;
	}

	private boolean findUserByMail(String mail) {
		User user = this.userRepository.findByMail(mail);

		// return (user == null) ? true: false;

		if (user == null)
			return true;
		else
			return false;
	}

	// Consulter ses infos persos
	@GetMapping(value = "/mesInfos")
	public ModelAndView mesInfos(HttpSession session) {
		ModelAndView model = new ModelAndView();
		if (session != null) {
			model.setViewName("mesInfosView");
		} else
			model.setViewName("loginView");
		return model;
	}

	// Modifier ses infos persos get
	@GetMapping(value = "/editInfos")
	public ModelAndView editComment(@ModelAttribute(value = "userInfos") User userInfos) {
		ModelAndView model = new ModelAndView();
		model.setViewName("editInfosView");
		return model;
	}

	// Modifier ses infos persos post
	@PostMapping(value = "/editInfos")
	public ModelAndView editComment(@Valid @ModelAttribute(value = "userInfos") User userInfos, BindingResult result,
			HttpSession session) {
		if (userInfos == null) {
			ModelAndView model = new ModelAndView("redirect:/register");
			return model;
		} else if (result.hasErrors() == false) {
			User userSession = (User) session.getAttribute("userSession");
			User user = this.userRepository.getOne(userSession.getId());
			user.setUsername(userInfos.getUsername());
			user.setLastname(userInfos.getLastname());
			user.setFirstname(userInfos.getFirstname());
			user.setTelephone(userInfos.getTelephone());
			user.setMail(userInfos.getMail());
			user.setPassword(userInfos.getPassword());
			this.userRepository.save(user);
			session.setAttribute("userSession", userInfos);
			ModelAndView model = new ModelAndView("redirect:/mesInfos");
			return model;
		} else {
			ModelAndView model = new ModelAndView("redirect:/editInfos");
			return model;
		}

	}

}
