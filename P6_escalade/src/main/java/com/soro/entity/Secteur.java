package com.soro.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/*Represente les secteur contenu dans les sites */

@Entity @Table(name = "secteur")
public class Secteur {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;
    
    @NotNull
    @Size(min=3, max=50)
    @Column(name="nom")
    private String nom;

    @NotNull
    @Size(min=3, max=50)
    @Column(name="description")
    private String description;

    @NotNull
    private String location;
    
    @NotNull
    @Column(name="nombres_voies")
    private int nombresVoies;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "site_relation")//Cette ligne permet de crée une colone site_id ds la base de donnée qui a pr roule de recupérer la valeurs des id qu on vas ajouter ds ce secteur
    private Site site;

    @OneToMany (fetch = FetchType.LAZY, mappedBy = "secteur")
    private List<Voies> voies ;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getNombresVoies() {
		return nombresVoies;
	}

	public void setNombresVoies(int nombresVoies) {
		this.nombresVoies = nombresVoies;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public List<Voies> getVoies() {
		return voies;
	}

	public void setVoies(List<Voies> voies) {
		this.voies = voies;
	}

	public Secteur(int id, String nom, String description, String location, int nombresVoies, Site site,
			List<Voies> voies) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.location = location;
		this.nombresVoies = nombresVoies;
		this.site = site;
		this.voies = voies;
	}

	public Secteur() {
		super();
		// TODO Auto-generated constructor stub
	}



	
    
    
}
