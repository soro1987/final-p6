package com.soro.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.soro.entity.Secteur;
import com.soro.entity.Voies;
import com.soro.repository.SecteurRepository;
import com.soro.repository.VoiesRepository;

/*Gere l'ajout, l affichage des voies*/

@Controller
public class VoieController {
	@Autowired
	SecteurRepository secteurRepository;
	@Autowired
	VoiesRepository voiesRepository;

	@GetMapping(value = "/postVoie/{id}")
	public String getFormVoie(@PathVariable(value = "id") int id, @ModelAttribute("voieModel") Voies voieModel,
			Model model) {
		List<String> listCotation = new ArrayList<String>();
		listCotation.addAll(Arrays.asList("1", "2", "3"));
		listCotation.addAll(Arrays.asList("4a", "4b", "4c"));
		listCotation.addAll(Arrays.asList("5a", "5b", "5c"));
		listCotation.addAll(Arrays.asList("6a", "6b", "6c"));
		listCotation.addAll(Arrays.asList("7a", "7b", "7c"));
		listCotation.addAll(Arrays.asList("8a", "8b", "8c"));
		listCotation.addAll(Arrays.asList("9a", "9a+", "9b", "9b+"));

		model.addAttribute("listCotation", listCotation);
		Secteur secteur = this.secteurRepository.getSecteurById(id);
		model.addAttribute("secteur", secteur);
		voieModel.setSecteur(secteur);
		return "postVoieView";

	}

	@PostMapping(value = "/postVoie/{id}")
	public String ajouterVoie(@Valid @ModelAttribute("voieModel") Voies voieModel, BindingResult result, Model model,
			@PathVariable(value = "id") int id) {
		Secteur secteur = this.secteurRepository.getSecteurById(id);
		model.addAttribute("secteur", secteur);
		voieModel.setSecteur(secteur);

		if (result.hasErrors()) {
			return "postVoieView";
		} else {
			Voies voie = this.voiesRepository.save(voieModel);
			secteur.getVoies().add(voie);
			this.secteurRepository.save(secteur);
			return "redirect:/displayVoie/" + voie.getId();
		}
	}

	@GetMapping(value = "/displayVoie/{id}")
	public ModelAndView displayVoie(@PathVariable(value = "id") int id) {
		Voies voie = this.voiesRepository.getVoiesById(id);
		ModelAndView model = new ModelAndView();
		model.addObject("voie", voie);
		model.setViewName("voieDetailsView");
		return model;

	}
}
