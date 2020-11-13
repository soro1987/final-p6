package com.soro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soro.entity.Site;
import com.soro.entity.Voies;

public interface VoiesRepository extends JpaRepository<Voies, Integer> {

	Voies getVoiesById(int id);
	public List<Voies> findByCotationsIsContaining(String cotation);

}
