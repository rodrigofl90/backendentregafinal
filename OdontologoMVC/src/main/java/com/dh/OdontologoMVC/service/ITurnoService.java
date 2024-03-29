package com.dh.OdontologoMVC.service;

import com.dh.OdontologoMVC.dto.request.TurnoRequestDTO;
import com.dh.OdontologoMVC.dto.response.TurnoResponseDTO;
import com.dh.OdontologoMVC.exception.ResourceNotFoundException;

import java.util.Optional;
import java.util.Set;

public interface ITurnoService {
    TurnoResponseDTO guardar(TurnoRequestDTO turno);
    Set<TurnoResponseDTO> listarTodos();
    Optional<TurnoResponseDTO> buscarPorId(Long id) throws ResourceNotFoundException;;
    void eliminar(Long id);


}
