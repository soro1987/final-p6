package com.soro.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.soro.entity.Secteur;
import com.soro.entity.Site;
import com.soro.entity.User;
import com.soro.repository.SecteurRepository;
import com.soro.repository.SiteRepository;

/*Gere les operation relative au secteur ajout,affichage*/

@RestController
public class SecteurController {

	@Autowired
	SiteRepository siteRepository;
	@Autowired
	SecteurRepository secteurRepository;

	@GetMapping(value = "/secteurs")
	public ModelAndView displaySecteurs() {
		List<Secteur> listOfSecteurs = this.secteurRepository.findAll();
		ModelAndView model = new ModelAndView();
		model.addObject("listSecteursModels", listOfSecteurs);
		model.setViewName("listOfSecteursView");
		return model;
	}
	
	
	@RequestMapping(value = "/postSecteur/{id}", method = { RequestMethod.GET})
	public ModelAndView getFormSecteur(@PathVariable(value = "id") int id,
			@ModelAttribute("secteurModel") Secteur SecteurModel, HttpSession session) {
		Site site = this.siteRepository.getSiteById(id);
		ModelAndView model = new ModelAndView("redirect:/displaySite/" + site.getId());
		model.addObject("site", site);
		SecteurModel.setSite(site);
		User user = (User)session.getAttribute("userSession");		
		if(user == null) {
			model = new ModelAndView("redirect:/login");
		}
		else {
			model.setViewName("postSecteurView");
		}
	return model;
	}
	
	@RequestMapping(value = "/postSecteur/{id}", method = { RequestMethod.POST })
	public ModelAndView ajouterSecteur(@PathVariable(value = "id") int id,
			@Valid @ModelAttribute("secteurModel") Secteur SecteurModel, BindingResult result) {
		Site site = this.siteRepository.getSiteById(id);
		ModelAndView model = new ModelAndView("redirect:/displaySite/" + site.getId());
		model.addObject("site", site);
		SecteurModel.setSite(site);
		if(result.hasErrors()) {
			model.setViewName("postSecteurView");
		} else {
			Secteur secteur = this.secteurRepository.save(SecteurModel);
			site.getSecteurs().add(secteur);
			site.setNbrSecteurs(String.valueOf(site.getSecteurs().size()-1).toString());
			this.siteRepository.save(site);
		}
		
		return model;
	}

	@GetMapping(value = "/displaySecteur/{id}")
	public  ModelAndView displaySecteur(@PathVariable(value="id") int id) {
		Secteur secteur = this.secteurRepository.getSecteurById(id);
		ModelAndView model = new ModelAndView();
		model.addObject("secteur", secteur);
		model.setViewName("secteurDetailsView");
		return model;
		
	}

}
