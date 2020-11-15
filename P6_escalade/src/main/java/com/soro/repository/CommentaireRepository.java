package com.soro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soro.entity.Commentaire;
import com.soro.entity.Reservation;
import com.soro.entity.Site;

public interface CommentaireRepository extends JpaRepository<Commentaire, Long> {
	public   List<Commentaire> findBySiteId(Site siteId );
}
