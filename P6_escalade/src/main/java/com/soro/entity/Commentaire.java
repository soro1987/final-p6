package com.soro.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/*Represente les commentaire utiliser pour commenter site et topos*/


@Entity @Table(name = "commentaire")
public class Commentaire {
    @Id @GeneratedValue
    @Column(name="id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "site_id")
    private Site siteId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topo_id")
    private Topo topoId;

   
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userId;
    @NotNull
    @Size(min = 1, message = "Commentaire minimum un caractère")
    @Size(max = 300, message = "Le commentaire ne peut contenir que 300 caractère")
    @Column(columnDefinition = "TEXT")
    private String message;

    @Column(name="date")
    private Date date = new Date();
    
    

    public Commentaire() {
    }

    public Commentaire(Site siteId, User userId, String message, Date date) {
        this.siteId = siteId;
        this.userId = userId;
        this.message = message;
        this.date = date;
    }
    
    public Topo getTopoId() {
		return topoId;
	}

	public void setTopoId(Topo topoId) {
		this.topoId = topoId;
	}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Site getSiteId() {
        return siteId;
    }

    public void setSiteId(Site siteId) {
        this.siteId = siteId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
