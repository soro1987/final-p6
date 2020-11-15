package com.soro.controller;

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
import com.soro.entity.Commentaire;
import com.soro.entity.Site;
import com.soro.entity.Topo;
import com.soro.entity.User;
import com.soro.repository.CommentaireRepository;
import com.soro.repository.SiteRepository;
import com.soro.repository.TopoRepository;

//Controlleur qui gere les commentaires
@Controller
public class CommentaireController {
	@Autowired
	CommentaireRepository commentaireRepository;
	@Autowired
	SiteRepository siteRepository;
	@Autowired
	TopoRepository topoRepository;

	@GetMapping(value = { "/deleteComment/{id}" })
	public String deleteCommentSite(@PathVariable("id") Long commentaireId) {
		Commentaire delComment = this.commentaireRepository.getOne(commentaireId);
		this.commentaireRepository.delete(delComment);
		return "redirect:/displaySite/" + delComment.getSiteId().getId();
	}

	@GetMapping(value = { "/deleteCommentTopo/{id}" })
	public String deleteCommentTopo(@PathVariable("id") Long commentaireId) {
		Commentaire delComment = this.commentaireRepository.getOne(commentaireId);
		this.commentaireRepository.delete(delComment);
		return "redirect:/displayTopos/" + delComment.getTopoId().getId();
	}

	@GetMapping(value = "/editComment")
	public String getComment(@ModelAttribute("commentModel") Commentaire commentModel,
			@RequestParam(value = "id") Long id, @RequestParam(value = "idSite") int idSite, Model model) {
		Commentaire comment = this.commentaireRepository.getOne(id);
		Site site = this.siteRepository.getOne(idSite);
		model.addAttribute("comment", comment);
		model.addAttribute("site", site);
		commentModel.setSiteId(comment.getSiteId());
		// commentModel.setMessage(comment.getMessage());
		return "editCommentView";
	}

	@PostMapping(value = "/editComment")
	public String editComment(@Valid @ModelAttribute(value = "commentModel") Commentaire commentModel,
			BindingResult result, @RequestParam(value = "id") Long id, @RequestParam(value = "idSite") int idSite,
			Model model, HttpSession session) {

		User user = (User) session.getAttribute("userSession");
		Commentaire comment = this.commentaireRepository.getOne(id);
		Site site = this.siteRepository.getOne(idSite);
		model.addAttribute("comment", comment);
		commentModel.setUserId(user);
		commentModel.setSiteId(site);
		if (result.hasErrors())
			return "editCommentView";
		else {
			this.commentaireRepository.save(commentModel);
			return "redirect:/displaySite/" + comment.getSiteId().getId();
		}

	}

	@GetMapping(value = "/editCommentTopo")
	public String getCommentTopo(@ModelAttribute("commentModel") Commentaire commentModel,
			@RequestParam(value = "id") Long id, @RequestParam(value = "idTopo") Long idTopo, Model model) {
		Commentaire comment = this.commentaireRepository.getOne(id);
		Topo topo = this.topoRepository.getTopoById(idTopo);
		model.addAttribute("comment", comment);
		model.addAttribute("topo", topo);
		commentModel.setTopoId(comment.getTopoId());

		// commentModel.setMessage(comment.getMessage());
		return "editCommentView";
	}

	@PostMapping(value = "/editCommentTopo")
	public String editCommentTopo(@Valid @ModelAttribute(value = "commentModel") Commentaire commentModel,
			BindingResult result, @RequestParam(value = "id") Long id, @RequestParam(value = "idTopo") Long idTopo,
			Model model, HttpSession session) {

		User user = (User) session.getAttribute("userSession");
		Commentaire comment = this.commentaireRepository.getOne(id);
		Topo topo = this.topoRepository.getTopoById(idTopo);
		model.addAttribute("comment", comment);

		model.addAttribute("topo", topo);
		commentModel.setUserId(user);
		commentModel.setTopoId(topo);
		if (result.hasErrors())
			return "editCommentView";
		else {
			this.commentaireRepository.save(commentModel);
			return "redirect:/displayTopos/" + comment.getTopoId().getId();
		}

	}

	@RequestMapping(value = "/login2", method = RequestMethod.GET)
	public String displayLogin(Model model) {
		model.addAttribute("UserLogin", new User());
		return "siteDetailsView";
	}

}
