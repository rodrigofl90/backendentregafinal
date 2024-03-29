package com.dh.OdontologoMVC.controller;

import com.dh.OdontologoMVC.dto.request.OdontologoRequestDTO;
import com.dh.OdontologoMVC.dto.response.OdontologoResponseDTO;
import com.dh.OdontologoMVC.exception.ResourceNotFoundException;
import com.dh.OdontologoMVC.service.IOdontologoService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@RequestMapping("/odontologos")
public class OdontologoController {
    private static final Logger LOGGER = Logger.getLogger(OdontologoController.class);
    private IOdontologoService odontologoService;
    @Autowired
    public OdontologoController(IOdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    // crear odontologo
    @PostMapping
    public ResponseEntity<OdontologoResponseDTO> guardar(@RequestBody OdontologoRequestDTO odontologoRequestDTO) {
        LOGGER.info("Estamos creando un odontologo: " + odontologoRequestDTO);
        ResponseEntity<OdontologoResponseDTO> response;
        response = ResponseEntity.ok(odontologoService.guardar(odontologoRequestDTO));
        return response;
    }
// listar todos los odontologos
    @GetMapping
    public ResponseEntity<Set<OdontologoResponseDTO>> listarTodos(){
        LOGGER.info("Estamos llamando a todos los odontologos");
        return ResponseEntity.ok(odontologoService.listarTodos());
    }
    //busca odontologo por id
    @GetMapping("/{id}")
    public ResponseEntity<OdontologoResponseDTO> buscarPorId(@PathVariable Long id) {
        LOGGER.info("Estamos buscando un odontologo por id :" + id);
        return ResponseEntity.ok(odontologoService.buscarPorId(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizar(@PathVariable Long id,@RequestBody OdontologoRequestDTO odontologoRequestDTO) {
        LOGGER.info("Estamos actualizando un odontologo: " + odontologoRequestDTO);
        ResponseEntity<String> response;
        try {
            odontologoService.actualizar(id, odontologoRequestDTO);
            response = ResponseEntity.ok("Se actualizo odontologo con id: " + id);
        } catch (ResourceNotFoundException e) {
            response = ResponseEntity.ok().body("No se encontro el paciente con id: " + id);
        }
        return response;

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id){
        ResponseEntity<String> response;
        try {
            odontologoService.eliminar(id);
            response = ResponseEntity.ok("Se eliminó el odontologo con id: " + id);
        } catch (ResourceNotFoundException e) {
            response = ResponseEntity.ok().body("No se encontró el odontologo con id: " + id);
        }
        return response;

    }
}
