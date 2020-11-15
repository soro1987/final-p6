package com.soro.controller;

import java.nio.file.Path;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.soro.entity.Commentaire;
import com.soro.entity.Site;
import com.soro.entity.User;
import com.soro.repository.CommentaireRepository;
import com.soro.repository.SecteurRepository;
import com.soro.repository.SiteRepository;
import com.soro.repository.UserRepository;
import com.soro.storage.StorageService;

/*Gere les operation relative au Site ajout,affichage*/
@Controller
@CrossOrigin("*")
public class SiteController {

	@Autowired
	SiteRepository siteRepository;
	@Autowired
	SecteurRepository secteurRepository;
	@Autowired
	CommentaireRepository commentaireRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	StorageService storageService;

	// Method qui renvoie la liste de tous les Site
	@RequestMapping(value = { "/displaySite" }, method = RequestMethod.GET)
	public ModelAndView returnAllSites() {
		List<Site> listSites = this.siteRepository.findAll();
		ModelAndView model = new ModelAndView();
		model.addObject("listSitesmodel", listSites);
		model.setViewName("listOfSitesView");
		return model;
	}

	@RequestMapping(value = { "/displayMySite" }, method = RequestMethod.GET)
	public String displayMySite() {
		return "mySiteView";
	}

	// Method qui renvoie le details d un Site
	@RequestMapping(value = { "/displaySite/{id}" }, method = RequestMethod.GET)
	public ModelAndView getAllSites(@PathVariable(value = "id") int id) {
		Site site = this.siteRepository.getSiteById(id);
		ModelAndView model = new ModelAndView();
		model.addObject("site", site);
		model.setViewName("siteDetailsView");
		return model;
	}

	// Method qui charge l'image d un Site
	@RequestMapping(value = { "/loadImage/{id}" }, method = RequestMethod.GET)
	public Path loadImage(@PathVariable(value = "id") int id) {
		Site site = this.siteRepository.getOne(id);
		return this.storageService.load(site.getImageUrl());
	}

	// Method qui permet d'acceder au formulaire pour poster un Site
	@RequestMapping(value = "/postSite", method = { RequestMethod.GET })
	public String postSite(@ModelAttribute("siteModel") Site site, HttpSession session, Model model) {
		User user = (User) session.getAttribute("userSession");
		site.setUserSiteRelation(user);
		if (user == null) {
			return "redirect:/register";
		} else {
			return "postSiteView";
		}

	}

	// Method qui permet de poster un Site en post
	@RequestMapping(value = "/postSite", method = { RequestMethod.POST })
	public String postSite(@Valid @ModelAttribute("siteModel") Site site, BindingResult result, HttpSession session,
			Model model, @RequestParam("url") MultipartFile url) {
		User user = (User) session.getAttribute("userSession");
		site.setUserSiteRelation(user);

		if (user == null) {
			return "redirect:/register";
		} else if (result.hasErrors() == false) {
			if (url.getSize() > 0) {
				String name = storageService.store(url);
				site.setNbrSecteurs("0");
				site.setImageUrl(name);
			} else {
				site.setImageUrl("unnamed.png");
			}
			this.siteRepository.save(site);
			user.getSites().add(site);
			this.userRepository.save(user);
			session.setAttribute("userSession", user);
			return "redirect:/displaySite";
		} else {
			return "postSiteView";
		}
	}

	// Method qui permet d acceder au formulaire d'un commentaire
	@GetMapping(value = "/postCommentaire/{id}")
	public ModelAndView getCommentView(@ModelAttribute("commentModel") Commentaire commentModel, HttpSession session,
			@PathVariable(value = "id") int id) {
		ModelAndView model = new ModelAndView();
		Site site = this.siteRepository.getSiteById(id);
		User userId = (User) session.getAttribute("userSession");
		commentModel.setUserId(userId);
		commentModel.setSiteId(site);
		model.addObject("site", site);
		model.setViewName("postCommentView");
		return model;
	}

	// Method qui permet de poster le commentaire d'un Site en post
	@PostMapping(value = "/postCommentaire/{id}")
	public String postCommentView(@Valid @ModelAttribute("commentModel") Commentaire commentModel, BindingResult result,
			HttpSession session, @PathVariable(value = "id") int id, Model model) {

		User userId = (User) session.getAttribute("userSession");
		Site site = this.siteRepository.getSiteById(id);
		commentModel.setSiteId(site);
		commentModel.setUserId(userId);
		model.addAttribute("site", site);
		if (result.hasErrors()) {
			return "postCommentView";
		} else {
			this.commentaireRepository.save(commentModel);
			return "redirect:/displaySite/" + site.getId();
		}

	}

	// Method qui permet de taguer un site
	@GetMapping(value = "/taguer/{id}")
	public String taguer(@PathVariable("id") int id) {
		Site site = this.siteRepository.getSiteById(id);
		site.setEstTaguer(true);
		this.siteRepository.save(site);
		return "redirect:/displaySite";
	}

	// Method qui permet de detaguer un site
	@GetMapping(value = "/deTaguer/{id}")
	public String deTaguer(@PathVariable("id") int id) {
		Site site = this.siteRepository.getSiteById(id);
		site.setEstTaguer(false);
		this.siteRepository.save(site);
		return "redirect:/displaySite";
	}

}
