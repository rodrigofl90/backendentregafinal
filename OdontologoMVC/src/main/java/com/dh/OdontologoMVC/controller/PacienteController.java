package com.dh.OdontologoMVC.controller;

import com.dh.OdontologoMVC.dto.request.PacienteRequestDTO;
import com.dh.OdontologoMVC.dto.response.PacienteResponseDTO;
import com.dh.OdontologoMVC.service.IPacienteService;
import com.dh.OdontologoMVC.exception.ResourceNotFoundException;
import com.dh.OdontologoMVC.service.implementacion.PacienteService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private static final Logger LOGGER = Logger.getLogger(PacienteController.class);
    private IPacienteService pacienteService;
    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<PacienteResponseDTO> guardar(@RequestBody PacienteRequestDTO pacienteRequestDTO) {
        ResponseEntity<PacienteResponseDTO> response;
        LOGGER.info("Creando paciente" + pacienteRequestDTO);
        response = ResponseEntity.ok(pacienteService.guardar(pacienteRequestDTO));

        return response;
    }
    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> findById(@PathVariable Long id){
        LOGGER.info("Buscando paciente id " + id);
        PacienteResponseDTO paciente = pacienteService.buscarPorId(id).orElseThrow(() -> new ResourceNotFoundException("No se encontró el paciente con id: " + id));
        return ResponseEntity.ok(paciente);
    }
    @GetMapping
    public ResponseEntity<Set<PacienteResponseDTO>> listarTodos(){
        return ResponseEntity.ok(pacienteService.listarTodos());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id){
        ResponseEntity<String> response;
        try {
            pacienteService.eliminar(id);
            response = ResponseEntity.ok("Se eliminó el paciente con id: " + id);
        } catch (ResourceNotFoundException e) {
            response = ResponseEntity.ok().body("No se encontró el paciente con id: " + id);
        }
        return response;

    }
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizar(@PathVariable Long id, @RequestBody PacienteRequestDTO pacienteRequestDTO){
        ResponseEntity<String> response;

        try {
            pacienteService.actualizar(id, pacienteRequestDTO);
            response = ResponseEntity.ok("Se actualizo paciente con id: " + id);
        } catch (ResourceNotFoundException e) {
            response = ResponseEntity.ok().body("No se encontro el paciente con id: " + id);
        }
   return response;
}

}

