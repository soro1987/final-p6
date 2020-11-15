package com.soro.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import java.util.List;

/*Utilisateur de l'app peut etre Admin ou User selon le boolean admin */

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull( message =  "Le username ne doit pas être vide" )
    @Size(min = 3, message = "Username doit contenir au moins trois caractères")
    @Size(max = 20, message = "Username ne doit pas depasser 20 caractères")
    @Column(name = "username")
    private String username;

    @NotNull( message =  "Le nom ne doit pas être vide" )
    @Size(min = 3, message = "Le nom doit contenir au moins trois caractères")
    @Size(max = 20, message = "Le nom ne doit pas depasser 20 caractères")
    @Column(name = "lastname")
    private String lastname;

    @NotNull( message =  "Le prénom ne doit pas être vide" )
    @Size(min = 3, message = "Le prénom doit contenir au moins trois caractères")
    @Size(max = 20, message = "Le prénom ne doit pas depasser 20 caractères")
    @Column(name = "firstname")
    private String firstname;

    @NotNull( message =  "L'é-mail ne doit pas être vide" )
    @Column(name = "mail")
    private String mail;

    @NotNull( message =  "Le telephone ne doit pas être vide" )
    @Column(name = "telephone")
    private int telephone;
    
    

//    @NotNull( message =  "Le mot de passe ne doit pas être vide" )
//    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]).{7,}", message = "Le mot de passe doit contenir 8 caractères, dont une majuscules, une minuscule et un chiffre")
    @Column(name = "password")
    private String password;

    @Column(name = "active")
    private boolean active;
    
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "escalade_user")
	private SiteEscalade escaladeUser;
    
    public SiteEscalade getEscaladeUser() {
		return escaladeUser;
	}


	public void setEscaladeUser(SiteEscalade escaladeUser) {
		this.escaladeUser = escaladeUser;
	}


	@LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany( mappedBy = "user")
    private List<Reservation> reservations;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany( mappedBy = "userId")
    private List<Topo> topos;
    
    //@LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany( mappedBy = "userSiteRelation", fetch = FetchType.EAGER)
    private List<Site> sites;

   

	@OneToMany(mappedBy = "userId")
    private List<Commentaire> commentaires;
    
   
    private boolean admin;
    
    
    public List<Site> getSites() {
		return sites;
	}


	public void setSites(List<Site> sites) {
		this.sites = sites;
	}



	public boolean isAdmin() {
		return admin;
	}


	public void setAdmin(boolean admin) {
		this.admin = admin;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}


	public int getTelephone() {
		return telephone;
	}


	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	public List<Reservation> getReservations() {
		return reservations;
	}


	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}


	public List<Topo> getTopos() {
		return topos;
	}


	public void setTopos(List<Topo> topos) {
		this.topos = topos;
	}


	public List<Commentaire> getCommentaires() {
		return commentaires;
	}


	public void setCommentaires(List<Commentaire> commentaires) {
		this.commentaires = commentaires;
	}

   
	

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", lastname=" + lastname + ", firstname=" + firstname
				+ ", mail=" + mail + ", telephone=" + telephone + ", password=" + password + ", active=" + active
				+ ", admin="
				+ admin + "]";
	}


	public User(Long id,
			@NotNull(message = "Le username ne doit pas être vide") @Size(min = 3, message = "Username doit contenir au moins trois caractères") @Size(max = 20, message = "Username ne doit pas depasser 20 caractères") String username,
			@NotNull(message = "Le nom ne doit pas être vide") @Size(min = 3, message = "Le nom doit contenir au moins trois caractères") @Size(max = 20, message = "Le nom ne doit pas depasser 20 caractères") String lastname,
			@NotNull(message = "Le prénom ne doit pas être vide") @Size(min = 3, message = "Le prénom doit contenir au moins trois caractères") @Size(max = 20, message = "Le prénom ne doit pas depasser 20 caractères") String firstname,
			@NotNull(message = "L'é-mail ne doit pas être vide") String mail, int telephone,
			@NotNull(message = "Le mot de passe ne doit pas être vide") @Size(min = 3, message = "Le mot de passe doit contenir au moins trois caractères") @Size(max = 20, message = "Le mot de passe ne doit pas depasser 20 caractères") String password,
			boolean active, List<Reservation> reservations, List<Topo> topos, List<Commentaire> commentaires,
			List<String> roles) {
		super();
		this.id = id;
		this.username = username;
		this.lastname = lastname;
		this.firstname = firstname;
		this.mail = mail;
		this.telephone = telephone;
		this.password = password;
		this.active = active;
		this.reservations = reservations;
		this.topos = topos;
		this.commentaires = commentaires;
		
	}


	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

    

}
