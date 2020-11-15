package com.soro.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/*Represente les voies contenu dans les secteurs */


@Entity @Table(name = "voies")
public class Voies {
    @Id
    @GeneratedValue
    @Column(name="id")
    private int id;

    @NotNull
   
    private String cotations;

    @NotNull
    @Size(min=3, max=50)
    @Column(name="nom_voies")
    private String nomVoie;

    @NotNull
    @Size(min=3, max=50)
    @Column(name="description")
    private String descriptionVoie;

   
    @Column(name="nombres_relais")
    private int nbreRelais;

    
    @Column(name="points")
    private int points;
    

    @ManyToOne
    @JoinColumn(name = "voies_id")
    private Secteur secteur;
    
    @NotNull
	@Column(name="longeur_id")
    private int longeurId;

    @OneToMany (fetch = FetchType.LAZY, mappedBy = "voies")
    private List<Longeur> longeurs ;

	public Voies() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Voies(int id, String cotations, String nomVoie, String descriptionVoie, int nbreRelais, int points,
			Secteur secteur, int longeurId, List<Longeur> longeurs) {
		super();
		this.id = id;
		this.cotations = cotations;
		this.nomVoie = nomVoie;
		this.descriptionVoie = descriptionVoie;
		this.nbreRelais = nbreRelais;
		this.points = points;
		this.secteur = secteur;
		this.longeurId = longeurId;
		this.longeurs = longeurs;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCotations() {
		return cotations;
	}

	public void setCotations(String cotations) {
		this.cotations = cotations;
	}

	public String getNomVoie() {
		return nomVoie;
	}

	public void setNomVoie(String nomVoie) {
		this.nomVoie = nomVoie;
	}

	public String getDescriptionVoie() {
		return descriptionVoie;
	}

	public void setDescriptionVoie(String descriptionVoie) {
		this.descriptionVoie = descriptionVoie;
	}

	public int getNbreRelais() {
		return nbreRelais;
	}

	public void setNbreRelais(int nbreRelais) {
		this.nbreRelais = nbreRelais;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public Secteur getSecteur() {
		return secteur;
	}

	public void setSecteur(Secteur secteur) {
		this.secteur = secteur;
	}

	public int getLongeurId() {
		return longeurId;
	}

	public void setLongeurId(int longeurId) {
		this.longeurId = longeurId;
	}

	public List<Longeur> getLongeurs() {
		return longeurs;
	}

	public void setLongeurs(List<Longeur> longeurs) {
		this.longeurs = longeurs;
	}

	
	
}