package com.dh.OdontologoMVC.repository;

import com.dh.OdontologoMVC.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//@Repository le sirve a springboot para realizar mejor la inyección de dependencia, para que sea más performante
public interface IOdontologoRepository extends JpaRepository<Odontologo, Long> {
}
