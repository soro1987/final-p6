package com.soro.repository;

import com.soro.entity.Longeur;
import com.soro.entity.Reservation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
