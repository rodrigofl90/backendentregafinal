package com.dh.OdontologoMVC.service;

import com.dh.OdontologoMVC.entity.Odontologo;

import java.util.List;

public interface IOdontologoService {
    void actualizar(Odontologo odontologo);

    Odontologo guardar (Odontologo odontologo);

    List<Odontologo> listarTodos();

    Odontologo buscarPorId(Long id);

    void eliminar(Long id);
}
