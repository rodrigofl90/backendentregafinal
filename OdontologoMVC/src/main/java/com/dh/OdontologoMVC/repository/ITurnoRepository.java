package com.dh.OdontologoMVC.repository;

import com.dh.OdontologoMVC.entity.Turno;
import com.dh.OdontologoMVC.entity.Domicilio;
import com.dh.OdontologoMVC.entity.Paciente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITurnoRepository extends JpaRepository<Turno, Long> {
}
