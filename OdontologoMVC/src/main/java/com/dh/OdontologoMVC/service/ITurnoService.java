package com.dh.OdontologoMVC.service;

import com.dh.OdontologoMVC.entity.Turno;

import java.util.List;

public interface ITurnoService {
    Turno guardar(Turno turno);
    List<Turno> listarTodos();

    Turno buscarPorId(Long id);

    void eliminar(Long id);

    void actualizar(Turno turno);
}
