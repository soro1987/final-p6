package com.soro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.soro.entity.Site;
import com.soro.entity.Topo;
import com.soro.entity.User;

public interface SiteRepository extends JpaRepository <Site, Integer> {
	public  Site getSiteById(int id);
	public List<Site> findByLieuContaining(String lieu);
	public List<Site> findByNbrSecteursContaining(String nbSecteurs);
	
	public List<Site> findByNomSite(String nomSite);
	public List<Site> findByLieu(String lieu);
	public List<Site> findByNbrSecteurs(String nbSecteurs);
	
	public List<Site> findByNomSiteAndLieu(String nomSite, String lieu);
	public List<Site> findByNomSiteAndNbrSecteurs(String nomSite, String nbrSecteurs);
	public List<Site> findByLieuAndNbrSecteurs(String lieu, String nbrSecteurs);
	public List<Site> findByNomSiteAndLieuAndNbrSecteurs(String nomSite, String lieu, String nbrSecteurs);


//	public List<Site> findByLieuContains(String lieu);
//	public List<Site> findByLieuIsContaining(String lieu);

}
