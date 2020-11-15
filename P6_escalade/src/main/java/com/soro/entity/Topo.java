package com.soro.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/*Represente les topos qui peuvent etre reserver */

@Entity @Table(name = "topo")
public class Topo implements  Serializable, Comparable<Reservation> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue
    @Column(name="id")
    private Long id;
    @NotNull
    @Size(min = 3, message = "caractère min 3")
    @Size(max = 20,message = "caractère max 20")
    @Column(name="nom")
    private String topoName;
    
	@Transient
	private MultipartFile url;
	
	private String imageUrl;
	
	 private boolean reserver;

	@NotNull
    @Column(name="description")
    private String description;
    
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "escalade_topo")
	private SiteEscalade escaladeTopo;
    
   

	@NotNull
    @Size(min = 3, message = "caractère min 3")
    @Size(max = 20,message = "caractère max 20")
    @Column(name="lieux")
    private String topoLocation;

  


	@Column(name="date_parution")
    private Date dateParution = new Date();;
    
    @Column(name="nombres_disponibles")
    private int nbreDispo ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userId;
    
    
    @OneToMany (fetch = FetchType.EAGER, mappedBy = "topoId")
    private List<Reservation> reservations ;
    
    
    @OneToMany (mappedBy = "topoId")
    private List<Commentaire> commentaires ;
    public SiteEscalade getEscaladeTopo() {
  		return escaladeTopo;
  	}

  	public void setEscaladeTopo(SiteEscalade escaladeTopo) {
  		this.escaladeTopo = escaladeTopo;
  	}

	public List<Commentaire> getCommentaires() {
		return commentaires;
	}

	public void setCommentaires(List<Commentaire> commentaires) {
		this.commentaires = commentaires;
	}
	

	
	
	 public MultipartFile getUrl() {
			return url;
		}

	 public void setUrl(MultipartFile url) {
			this.url = url;
			this.imageUrl = url.getOriginalFilename();
		}

		public String getImageUrl() {
			return imageUrl;
		}

		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}

		
		   
		public boolean isReserver() {
			return reserver;
		}

		public void setReserver(boolean reserver) {
			this.reserver = reserver;
		}


    public Topo() {
    }

    public Topo(String topoName, String topoLocation, Date dateParution, int nbreDispo, User userId, List<Reservation> reservations) {
        this.topoName = topoName;
        this.topoLocation = topoLocation;
        this.dateParution = dateParution;
        this.nbreDispo = nbreDispo;
        this.userId = userId;
        this.reservations = reservations;
    }
    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopoName() {
        return topoName;
    }

    public void setTopoName(String topoName) {
        this.topoName = topoName;
    }

    public String getTopoLocation() {
        return topoLocation;
    }

    public void setTopoLocation(String topoLocation) {
        this.topoLocation = topoLocation;
    }

    public Date getDateParution() {
        return dateParution;
    }

    public void setDateParution(Date dateParution) {
        this.dateParution = dateParution;
    }

    public int getNbreDispo() {
        return nbreDispo;
    }

    public void setNbreDispo(int nbreDispo) {
        this.nbreDispo = nbreDispo;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
    
    
    @Override
	public int compareTo(Reservation reserve) {
		if(reserve.getDisponible().equals("accepter")) {
			return 1;
		}
		return -1;
	}
	
	public boolean equals() {
		
		for(Reservation reserve: this.getReservations()) {
			if( this.compareTo(reserve) == 1) {
				return true;
			}
				
		}
		
		return false;
	}
	
	
    
}
