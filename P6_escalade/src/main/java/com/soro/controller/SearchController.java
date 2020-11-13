package com.soro.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.soro.entity.Site;
import com.soro.entity.Topo;
import com.soro.entity.Voies;
import com.soro.repository.SiteRepository;
import com.soro.repository.TopoRepository;
import com.soro.repository.VoiesRepository;

//Controlleur qui gére la page de recherche

@Controller
public class SearchController {
	
	@Autowired
	private TopoRepository topoRepository;
	@Autowired
	private SiteRepository siteRepository;
	@Autowired
	private VoiesRepository voiesRepository;
	
	@RequestMapping(value="/searchTopo", method = {RequestMethod.GET})
	public String searchTopo(@RequestParam("mc") String mc)
	{
		List<Topo> topos = this.topoRepository.chercher("%"+mc+"%");		
		topos.forEach(t -> System.out.println(t.getDescription()));		
		return "listOfToposView";
	}
	
	@RequestMapping(value="/search", method = {RequestMethod.GET})
	public String search(@RequestParam(value = "nomSite", defaultValue = "n") String nomSite, @RequestParam(value = "lieu", defaultValue = "l") String lieu,
			@RequestParam(value = "nbrSecteurs", defaultValue = "nb") String nbrSecteurs, Model model,
			HttpSession session
			)
	{		
		session.setAttribute("nomCritere", true);
		session.setAttribute("lieuCritere", false);
		session.setAttribute("nbrSecteurCriteurs", false);
		List<Site> sites = new ArrayList<Site>();
				
		if(!nomSite.equals("n") && lieu.equals("l") && nbrSecteurs.equals("nb")) {
			sites = this.siteRepository.findByNomSite(nomSite);
		} else if(!lieu.equals("l") && nomSite.equals("n") && nbrSecteurs.equals("nb")) {
			sites = this.siteRepository.findByLieu(lieu);
		} else if(!nbrSecteurs.equals("nb") && nomSite.equals("n") && lieu.equals("l")) {
			sites = this.siteRepository.findByNbrSecteurs(nbrSecteurs);
		} else if(!nbrSecteurs.equals("nb")  && !nomSite.equals("n") && lieu.equals("l")) {
			sites = this.siteRepository.findByNomSiteAndNbrSecteurs(nomSite, nbrSecteurs);
		} else if(!lieu.equals("l") && !nomSite.equals("n") && nbrSecteurs.equals("nb")) {
			sites = this.siteRepository.findByNomSiteAndLieu(nomSite, lieu);
		}else if(!nbrSecteurs.equals("nb") && !lieu.equals("l") && nomSite.equals("n")) {
			sites = this.siteRepository.findByLieuAndNbrSecteurs(lieu, nbrSecteurs);
		} else {
			sites = this.siteRepository.findByNomSiteAndLieuAndNbrSecteurs(nomSite, lieu, nbrSecteurs);
		}
				
		model.addAttribute("sites", sites);
		sites.forEach(p -> System.out.println(p.getNomSite()));
		return "rechercheView";
	}
		
	@RequestMapping(value = "/searchSiteLieu", method = {RequestMethod.GET})
		public String searchSiteLieu(@RequestParam("lieu") String lieu,Model model) {
		List<Site> site = siteRepository.findByLieuContaining(lieu);
		model.addAttribute("sitesResult", site);
			return "resultatOfSitesView";
		}
	                        
	@RequestMapping(value = "/searchSiteNbSecteurs", method = {RequestMethod.GET})
	public String searchSiteNbSecteur(@RequestParam("nbSecteurs") String nbSecteurs,Model model) {
	List<Site> siteSecteurs = siteRepository.findByNbrSecteursContaining(nbSecteurs);
	model.addAttribute("sitesSecteurs", siteSecteurs);
		return "resultNbrSecteursView";
	}
	
	@RequestMapping(value = "/searchVoieCotation", method = {RequestMethod.GET})
	public String searchVoieCotation(@RequestParam("cotation") String cotation, Model model) {
	List<Voies> voies = voiesRepository.findByCotationsIsContaining(cotation);
	model.addAttribute("voiesResult", voies);
		return "resultatOfCotationView";
	}
	
	public String rateHandler(HttpServletRequest request) {
	    //your controller code
	    String referer = request.getHeader("Referer");
	    return "redirect:"+ referer;
	}
	//changerCritere
	@GetMapping(value = {"/selectionNom", })
	public String changerNomCritere(@RequestParam(value = "nomCritere") Boolean nomCritere, Model model, HttpSession session,  HttpServletResponse httpResp, HttpServletRequest httpReq) throws IOException {
		nomCritere = !nomCritere;
		session.setAttribute("nomCritere", nomCritere);
		return "rechercheView";
	}
	
	@GetMapping(value = {"/selectionLieu", })
	public String changerLieuCritere(@RequestParam(value = "lieuCritere") Boolean lieuCritere, Model model, HttpSession session,  HttpServletResponse httpResp, HttpServletRequest httpReq) throws IOException {
		lieuCritere = !lieuCritere;
		session.setAttribute("lieuCritere", lieuCritere);
		return "rechercheView";
	}
	
		@GetMapping(value = {"/selectionnbrSecteur", })
		public String changerCritere(@RequestParam(value = "nbrSecteurCriteurs") Boolean nbrSecteurCriteurs, Model model, HttpSession session){
			nbrSecteurCriteurs = !nbrSecteurCriteurs;
			session.setAttribute("nbrSecteurCriteurs", nbrSecteurCriteurs);
			return "rechercheView";
		}
		
	

}
