package com.dh.OdontologoMVC.controller;


import com.dh.OdontologoMVC.dto.request.TurnoRequestDTO;
import com.dh.OdontologoMVC.dto.response.TurnoResponseDTO;
import com.dh.OdontologoMVC.exception.ResourceNotFoundException;
import com.dh.OdontologoMVC.service.IOdontologoService;
import com.dh.OdontologoMVC.service.IPacienteService;
import com.dh.OdontologoMVC.service.ITurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/turnos")
public class TurnoController {
private static final Logger LOGGER = Logger.getLogger(TurnoController.class);
private ITurnoService turnoService;
private IOdontologoService odontologoService;
private IPacienteService pacienteService;
    @Autowired
    public TurnoController(ITurnoService turnoService, IOdontologoService odontologoService, IPacienteService pacienteService) {
        this.turnoService = turnoService;
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<TurnoResponseDTO> guardar(@RequestBody TurnoRequestDTO turnoRequestDTO) {
        ResponseEntity<TurnoResponseDTO> response;
 LOGGER.info("esto trae un turno: " + turnoRequestDTO);
        //        vamos a chequear que exista el odontologo y la paciente
        if (odontologoService.buscarPorId(turnoRequestDTO.getOdontologo_id()) != null &&
                pacienteService.buscarPorId(turnoRequestDTO.getPaciente_id()) != null) {

            //setear una respuesta en 200 y vamos a hacer que devuelva el turno
            response = ResponseEntity.ok(turnoService.guardar(turnoRequestDTO));

        } else {
            //si no existe el odontologo o el paciente
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }
        return response;
    }
    @GetMapping
    public ResponseEntity<Set<TurnoResponseDTO>> findAll(){
        return ResponseEntity.ok(turnoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoResponseDTO> findById(@PathVariable Long id) {
        LOGGER.info("Buscamos turno por id" + id);
        //vamos simplemente a llamar al servicio que busca por id
        //porque si no lo encuentra, el servicio va a manejar la excepción
        turnoService.buscarPorId(id);
        return ResponseEntity.ok().build();

    }



    @DeleteMapping("/id")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        LOGGER.info("Eliminamos un turno por id " + id);
        ResponseEntity<String> response;
        try {
            turnoService.eliminar(id);
            response = ResponseEntity.ok("Se eliminó el turno con id: " + id);
        } catch (ResourceNotFoundException e) {
            response = ResponseEntity.ok().body("No se encontrò el turno con id: " + id);
        }
        return response;
    }


}
