package com.dh.OdontologoMVC.repository;

import com.dh.OdontologoMVC.entity.Turno;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITurnoRepository extends JpaRepository<Turno, Long> {
}
