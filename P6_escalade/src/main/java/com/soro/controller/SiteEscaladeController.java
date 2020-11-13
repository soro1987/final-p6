//package com.soro.controller;
//
//import java.util.List;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import com.soro.entity.Site;
//import com.soro.entity.SiteEscalade;
//import com.soro.entity.Topo;
//import com.soro.entity.User;
//import com.soro.repository.SiteEscaladeRepository;
//import com.soro.repository.SiteRepository;
//import com.soro.repository.TopoRepository;
//import com.soro.repository.UserRepository;
//
//@Controller
//public class SiteEscaladeController {
//
//	@Autowired
//	SiteRepository siteRepository;
//	@Autowired
//	TopoRepository topoRepository;
//	@Autowired
//	UserRepository userRepository;
//    @Autowired
//    SiteEscaladeRepository siteEscaladeRepository;
//    
//    @GetMapping(value="/init")
//    public String init() {
//	   List<Site> sites = siteRepository.findAll();
//	   List<Topo> topos = topoRepository.findAll();
//	   List<User> users = this.userRepository.findAll();
//	   
//	   SiteEscalade siteEscalade = new SiteEscalade();
//	   
//	   siteEscalade.setSites(sites);
//	   siteEscalade.setTopo(topos);
//	   siteEscalade.setUsers(users);
//	   siteEscalade = this.siteEscaladeRepository.save(siteEscalade);
//	   for(Site site: sites) {
//		   site.setEscaladeSite(siteEscalade);
//		   this.siteRepository.save(site);
//	   }
//	   
//	   for(Topo topo: topos) {
//		   topo.setEscaladeTopo(siteEscalade);
//		   this.topoRepository.save(topo);
//	   }
//	   
//	   for(User user: users) {
//		   user.setEscaladeUser(siteEscalade);
//		   this.userRepository.save(user);
//	   }
//	   return "homeView";
//    }
//}
