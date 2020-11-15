package com.soro.repository;

import com.soro.entity.Longeur;
import com.soro.entity.Split;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SplitRepository extends JpaRepository<Split, Integer> {
	
}
