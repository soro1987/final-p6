package com.soro.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

/*Represente les sites d'escalade */

@Entity
@Table(name = "site")
public class Site {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	@NotNull
	@Size(min = 3, message = "-                                           Caractère min 3")
	@Size(max = 20, message = "-                                         Caractère max 20")
	@Column(name = "nom")
	private String nomSite;
	@NotNull
	@Size(min = 3, message = "-                                         Caractère min 3")
	@Size(max = 200, message = "-                                         Caractère max 20")
	@Column(name = "description")
	private String descriptionSite;
	@NotNull
	@Size(min = 3, message = "-                                         Caractère min 3")
	@Size(max = 20, message = "-                                         Caractère max 20")
	private String lieu;
	
	
	@Transient
	private MultipartFile url;
	
	private String imageUrl;

	

	@Column(name = "est_taguer")
	private boolean estTaguer;

	@Column(name = "sitescol")
	private String siteCol;

	@OneToMany(mappedBy = "siteId")
	private List<Commentaire> commentaires;

	@OneToMany(mappedBy = "site", fetch = FetchType.LAZY)
	private List<Secteur> secteurs;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "escalade_site")
	private SiteEscalade escaladeSite;

	public SiteEscalade getEscaladeSite() {
		return escaladeSite;
	}

	public void setEscaladeSite(SiteEscalade escaladeSite) {
		this.escaladeSite = escaladeSite;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_site_relation")
	private User userSiteRelation;

	@Column(name = "nbr_Secteurs")
	private String nbrSecteurs ;
	
	@Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] sitePicture;
	
	
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Site() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Site(int id, String nomSite, String descriptionSite, String lieu, boolean estTaguer, String siteCol,
			List<Commentaire> commentaires, List<Secteur> secteurs, String nbrSecteurs) {
		super();
		this.id = id;
		this.nomSite = nomSite;
		this.descriptionSite = descriptionSite;
		this.lieu = lieu;
		this.estTaguer = estTaguer;
		this.siteCol = siteCol;
		this.commentaires = commentaires;
		this.secteurs = secteurs;
		this.nbrSecteurs = nbrSecteurs;
		nbrSecteurs = String.valueOf(secteurs.size()).toString();
	}

	public String getNbrSecteurs() {

		return nbrSecteurs;
	}

	public void setNbrSecteurs(String nbrSecteurs) {
		this.nbrSecteurs = nbrSecteurs;
	}

	public String getLieu() {
		return lieu;
	}

	public void setLieu(String lieu) {
		this.lieu = lieu;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomSite() {
		return nomSite;
	}

	public void setNomSite(String nomSite) {
		this.nomSite = nomSite;
	}

	public String getDescriptionSite() {
		return descriptionSite;
	}

	public void setDescriptionSite(String descriptionSite) {
		this.descriptionSite = descriptionSite;
	}

	public boolean isEstTaguer() {
		return estTaguer;
	}

	public void setEstTaguer(boolean estTaguer) {
		this.estTaguer = estTaguer;
	}

	public String getSiteCol() {
		return siteCol;
	}

	public void setSiteCol(String siteCol) {
		this.siteCol = siteCol;
	}

	public List<Commentaire> getCommentaires() {
		return commentaires;
	}

	public void setCommentaires(List<Commentaire> commentaires) {
		this.commentaires = commentaires;
	}

	public List<Secteur> getSecteurs() {
		return secteurs;
	}

	public void setSecteurs(List<Secteur> secteurs) {
		this.secteurs = secteurs;
		this.nbrSecteurs = String.valueOf(this.secteurs.size()).toString();
	}

	public User getUserSiteRelation() {
		return userSiteRelation;
	}

	public void setUserSiteRelation(User userSiteRelation) {
		this.userSiteRelation = userSiteRelation;
	}

	public MultipartFile getUrl() {
		return url;
	}

	public void setUrl(MultipartFile url) {
		this.url = url;
		this.imageUrl = url.getOriginalFilename();
	}
	
	

}
