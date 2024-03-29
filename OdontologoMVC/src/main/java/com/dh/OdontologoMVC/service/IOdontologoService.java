package com.dh.OdontologoMVC.service;

import com.dh.OdontologoMVC.dto.request.OdontologoRequestDTO;
import com.dh.OdontologoMVC.dto.response.OdontologoResponseDTO;
import java.util.Set;

public interface IOdontologoService {
    void actualizar(Long id, OdontologoRequestDTO odontologoRequestDTO);

    OdontologoResponseDTO guardar (OdontologoRequestDTO odontologoRequestDTO);

    Set<OdontologoResponseDTO> listarTodos();

    OdontologoResponseDTO buscarPorId(Long id);

    void eliminar(Long id);
}
