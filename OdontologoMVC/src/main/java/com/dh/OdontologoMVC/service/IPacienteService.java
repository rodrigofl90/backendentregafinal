package com.dh.OdontologoMVC.service;

import com.dh.OdontologoMVC.dto.request.PacienteRequestDTO;
import com.dh.OdontologoMVC.dto.response.PacienteResponseDTO;

import java.util.Optional;
import java.util.Set;

public interface IPacienteService {
    PacienteResponseDTO guardar(PacienteRequestDTO paciente);
    Set<PacienteResponseDTO> listarTodos();

    Optional<PacienteResponseDTO> buscarPorId(Long id);
    void eliminar(Long id);
    void actualizar(Long id, PacienteRequestDTO pacienteRequestDTO);
}

