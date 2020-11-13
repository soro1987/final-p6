package com.soro.repository;

import com.soro.entity.Longeur;
import com.soro.entity.Topo;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public interface TopoRepository extends JpaRepository<Topo, Long> {


	public Topo getTopoById(Long id);
    @Query("select t from Topo t where t.description like :x")
	public List<Topo> chercher(@Param("x") String mc);
}
