package com.soro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.soro.entity.Site;
import com.soro.repository.SiteRepository;

//Controlleur qui gére la page d'acceuil
@Controller
public class homeController {
	
	@Autowired
	SiteRepository siteRepository;
	
	//Gere les image afficher sur le carrousel en fonction des derniers sites poster
	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String displayLastSites(Model model) {
		List<Site> listSites = this.siteRepository.findAll();
        if (listSites.size()>3) {
			Site carrou1 = listSites.get(0);
			Site carrou2 = listSites.get(1);
			Site carrou3 = listSites.get(2);
			model.addAttribute("carrou1", carrou1);
			model.addAttribute("carrou2", carrou2);
			model.addAttribute("carrou3", carrou3);
	
        }else {
	        Site carrou1 = listSites.get(0);
			Site carrou2 = listSites.get(0);
			Site carrou3 = listSites.get(0);
			model.addAttribute("carrou1", carrou1);
			model.addAttribute("carrou2", carrou2);
			model.addAttribute("carrou3", carrou3);        	
        }
		
		return "homeView";
	}

}
