package com.soro.repository;

import com.soro.entity.Longeur;
import com.soro.entity.Secteur;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SecteurRepository extends JpaRepository<Secteur, Integer> {

	Secteur getSecteurById(int id);
}
