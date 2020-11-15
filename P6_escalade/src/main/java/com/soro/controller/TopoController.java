package com.soro.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.soro.entity.Commentaire;
import com.soro.entity.Reservation;
import com.soro.entity.Topo;
import com.soro.entity.User;
import com.soro.repository.CommentaireRepository;
import com.soro.repository.ReservationRepository;
import com.soro.repository.SiteRepository;
import com.soro.repository.TopoRepository;
import com.soro.storage.StorageService;


/*Gere l'ajout, l affichage et les commentaires d'un topo*/

@Controller
public class TopoController {

	@Autowired
	TopoRepository topoRepository;
	@Autowired
	SiteRepository siteRepository;
	@Autowired
	CommentaireRepository commentaireRepository;
	@Autowired
	ReservationRepository reservationRepository;
	@Autowired
	StorageService storageService;

	@GetMapping(value = "/displayTopos")
	public String displayTopos(Model model) {
		boolean activeAlert = false;
		model.addAttribute("activeAlert", activeAlert);
		List<Topo> listTopos = this.topoRepository.findAll();
		model.addAttribute("listTopos", listTopos);
		for (Topo top : listTopos) {
			System.err.println(top.equals());
		}
		return "listOfToposView";
	}

	@GetMapping(value = "/myTopos")
	public String displayMyTopos(Model model) {
		return "myTopoView";
	}

	@GetMapping(value = "/alert")
	public String alert(Model model) {
		boolean activeAlert = true;
		model.addAttribute("activeAlert", activeAlert);
		List<Topo> listTopos = this.topoRepository.findAll();
		model.addAttribute("listTopos", listTopos);
		return "listOfToposView";
	}

	// Method qui renvoie le details d un Site
	@RequestMapping(value = { "/displayTopos/{id}" }, method = RequestMethod.GET)
	public String getTopoDetails(@PathVariable(value = "id") Long id, Model model) {
		Topo topo = this.topoRepository.getTopoById(id);
		model.addAttribute("topoById", topo);
		model.addAttribute("topo", topo);
		return "topoDetailsView";
	}

	@GetMapping(value = "/postTopo")
	public String postTopo(@ModelAttribute("topoModel") Topo topoModel, HttpSession session) {
		User user = (User) session.getAttribute("userSession");
		if (user == null) {
			return "redirect:/register";
		} else {
			return "postToposView";
		}

	}

	@PostMapping(value = "/postTopo")
	public String postTopo(@Valid @ModelAttribute("topoModel") Topo topoModel, BindingResult result,
			HttpSession session, Model model, @RequestParam("url") MultipartFile url) {
		model.addAttribute("result", result);
		User user = (User) session.getAttribute("userSession");
		topoModel.setUserId(user);
		if (result.hasErrors()) {
			return "postToposView";
		} else if (user == null) {
			return "redirect:/register";
		} else {
			String name = storageService.store(url);
			topoModel.setImageUrl(name);

			String uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/image/").path(name).toUriString();

			Topo topo = this.topoRepository.save(topoModel);
			return "redirect:/displayTopos/" + topo.getId();
		}

	}

	@GetMapping(value = "/postCommentaireTopo/{id}")
	public String getCommentView(@ModelAttribute("commentModel") Commentaire commentModel,
			@PathVariable(value = "id") Long id, Model model) {
		Topo topo = this.topoRepository.getTopoById(id);
		model.addAttribute("topo", topo);
		commentModel.setTopoId(topo);
		return "postCommentView";
	}

	@PostMapping(value = "/postCommentaireTopo/{id}")
	public String postCommentView(@Valid @ModelAttribute("commentModel") Commentaire commentModel, BindingResult result,
			HttpSession session, @PathVariable(value = "id") Long id) {

		User userId = (User) session.getAttribute("userSession");
		Topo topo = this.topoRepository.getTopoById(id);
		commentModel.setUserId(userId);
		commentModel.setTopoId(topo);

		if (result.hasErrors()) {
			return "postCommentView";
		} else {
			this.commentaireRepository.save(commentModel);
			// model.setViewName("siteDetailsView");
			return "redirect:/displayTopos/" + topo.getId();
		}

	}

	@GetMapping(value = "/displayReservation/{id}")
	public String getDisplayReservation(@PathVariable("id") Long id, Model model) {
		Reservation reservation = this.reservationRepository.getOne(id);
		model.addAttribute("reservation", reservation);
		return "reservationDetailView";

	}

}
