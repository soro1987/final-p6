package com.soro.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class SiteEscalade implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private Long id;
	
	@OneToMany(mappedBy = "escaladeSite", fetch = FetchType.LAZY)
	private List<Site> sites;
	
	@OneToMany(mappedBy = "escaladeTopo", fetch = FetchType.LAZY)
	private List<Topo> topo;
	
	@OneToMany(mappedBy = "escaladeUser", fetch = FetchType.LAZY)
	private List<User> users;
	
	
	public SiteEscalade() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public List<Site> getSites() {
		return sites;
	}


	public void setSites(List<Site> sites) {
		this.sites = sites;
	}


	public List<Topo> getTopo() {
		return topo;
	}


	public void setTopo(List<Topo> topo) {
		this.topo = topo;
	}


	public List<User> getUsers() {
		return users;
	}


	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	
}
