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
import org.springframework.web.bind.annotation.RequestParam;

import com.soro.entity.Reservation;
import com.soro.entity.Topo;
import com.soro.entity.User;
import com.soro.repository.ReservationRepository;
import com.soro.repository.TopoRepository;
import com.soro.repository.UserRepository;

//Controlleur qui gére les reservations de topos
@Controller
public class ReservationController {
	@Autowired
	ReservationRepository reservationRepository;
	@Autowired
	TopoRepository topoRepository;
	@Autowired
	UserRepository userRepository;
	
	//Formulaire de reservation
	@GetMapping(value = "/placeReservation/{id}")
	public String placeReservation(@PathVariable("id") Long id, Model model, HttpSession session, @ModelAttribute(value = "reservationModel") Reservation reservationModel) {
		Topo topo = this.topoRepository.getTopoById(id);
		User user = (User) session.getAttribute("userSession");
		model.addAttribute("topo", topo);
		return "placeReservationView";
	}
	
	//Formulaire de reservation en post
	@PostMapping(value = "/placeReservation/{id}")
	public String ajouterReservation(
			@PathVariable("id") Long id, 
			Model model, HttpSession session, 
			@Valid @ModelAttribute(value = "reservationModel") Reservation reservationModel,
			BindingResult result
			) {
	    String msgErr = null;
		Topo topo = this.topoRepository.getTopoById(id);
		User user = (User) session.getAttribute("userSession");
		reservationModel.setUser(user);
		reservationModel.setTopoId(topo);
		model.addAttribute("topo", topo);
		 List<Reservation> reservations = null;		 
		if (user != null) 
			reservations = user.getReservations();		
		if(result.hasErrors()) {
			return "placeReservationView";
		} else if(user != null && reservationModel.equals(reservations)) {// On test si l utilisateur est connecter apres on verifie si la reservation existe ds la liste des demandes de reservations de l utilisateur courrant
			msgErr = "Vous avez déjà réservé ce Topo";
			model.addAttribute("msgErr", msgErr);
			return  "placeReservationView";
		}
		else {
		    reservationModel.setDisponible("demander");
		    this.reservationRepository.save(reservationModel);
			this.topoRepository.save(topo);
			return "redirect:/displayTopos/" + topo.getId();
		}	
	}
	
	
	
	/* Gestion des status de reservation : refuser; accepter; supprimer; disponible */
	
	
	
	@GetMapping(value = "accepter")
    public String accepterReservation(HttpSession session,Model model, @RequestParam("idTopo") Long idTopo, @RequestParam("idReservation") Long idReservation) {
		User user = (User)session.getAttribute("userSession");
		model.addAttribute("user", user);
		Topo topo = this.topoRepository.getTopoById(idTopo);
		Reservation reservation = this.reservationRepository.getOne(idReservation);
		reservation.setDisponible("accepter");
		topo.setReserver(true);
        this.reservationRepository.save(reservation);
		return "redirect:/displayReservation/" + reservation.getId();	
	}
		
	@GetMapping(value = "/refuser")
    public String refuserReservation(HttpSession session,Model model, @RequestParam("idTopo") Long idTopo, @RequestParam("idReservation") Long idReservation) {
		Topo topo = this.topoRepository.getTopoById(idTopo);
		User user = (User)session.getAttribute("userSession");
        this.topoRepository.save(topo);
        Reservation reservation = this.reservationRepository.getOne(idReservation);
        reservation.setDisponible("refuser");
        topo.setReserver(false);
        this.reservationRepository.save(reservation);
        this.topoRepository.save(topo);
        this.userRepository.save(user);
        session.setAttribute("userSession", this.userRepository.getOne(user.getId()));
		return "redirect:/myTopos";	
	}

	@GetMapping(value = "/supprimer")
    public String supprimer(HttpSession session,Model model, @RequestParam("idTopo") Long idTopo, @RequestParam("idReservation") Long idReservation) {
		Topo topo = this.topoRepository.getTopoById(idTopo);
		User user = (User)session.getAttribute("userSession");
        this.topoRepository.save(topo);
        Reservation reservation = this.reservationRepository.getOne(idReservation);
        reservation.setDisponible("refuser");
        topo.getReservations().remove(reservation);
        topo.setReserver(false);
        this.topoRepository.save(topo);
        user.getReservations().remove(reservation);
        this.userRepository.save(user);
        session.setAttribute("reservationSession", reservation);
        this.reservationRepository.delete(reservation);
        session.setAttribute("userSession", this.userRepository.getOne(user.getId()));
		return "redirect:/myTopos";		
	}
	
	@GetMapping(value = "disponible")
    public String rendreRedisponibleReservation(HttpSession session,Model model, @RequestParam("idTopo") Long idTopo, @RequestParam("idReservation") Long idReservation) {
		User user = (User)session.getAttribute("userSession");
		model.addAttribute("user", user);
		Topo topo = this.topoRepository.getTopoById(idTopo);
		topo.setReserver(false);
		Reservation reservation = this.reservationRepository.getOne(idReservation);
		reservation.setDisponible("disponible");
		this.topoRepository.save(topo);
        this.reservationRepository. delete(reservation);      //save(reservation);  displayReservation/" + reservation.getId();
		return "redirect:/displayTopos";             	
	}
}