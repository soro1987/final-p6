package com.soro.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/*Represente les reservation de topos */

@Entity
@Table(name = "reservation")
public class Reservation implements Serializable, Comparable<Reservation> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
    @Column(name="id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "topo_id")
    private Topo topoId;

    @NotNull
    @Size(min=3,max=300)
    @Column(columnDefinition = "TEXT")
    private String message;

    @Column(name="date")
    private Date messageDate = new Date();
    
    private String disponible = "disponible";
    
    

	public String getDisponible() {
		return disponible;
	}

	public void setDisponible(String disponible) {
		this.disponible = disponible;
	}


    public Reservation() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Topo getTopoId() {
        return topoId;
    }

    public void setTopoId(Topo topoId) {
        this.topoId = topoId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
    }


	@Override
	public int compareTo(Reservation reserve) {
		if(this.topoId.getId() == reserve.getTopoId().getId() && this.user.getId() == reserve.getUser().getId() ) {
			return 1;
		}
		return -1;
	}
	
	public boolean equals(List<Reservation> reserves) {
		
		for(Reservation reserve: reserves) {
			if( this.compareTo(reserve) == 1) {
				return true;
			}
				
		}
		
		return false;
	}
	
}
