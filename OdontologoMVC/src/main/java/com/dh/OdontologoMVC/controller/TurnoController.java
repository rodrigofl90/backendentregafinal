package com.dh.OdontologoMVC.controller;

import com.dh.OdontologoMVC.entity.Turno;
import com.dh.OdontologoMVC.service.IOdontologoService;
import com.dh.OdontologoMVC.service.IPacienteService;
import com.dh.OdontologoMVC.service.ITurnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.log4j.Logger;


@RestController
@RequestMapping("/turnos")
public class TurnoController {
private static final Logger LOGGER = Logger.getLogger(TurnoController.class);
private ITurnoService turnoService;
private IOdontologoService odontologoService;
private IPacienteService pacienteService;

    public TurnoController(ITurnoService turnoService, IOdontologoService odontologoService, IPacienteService pacienteService) {
        this.turnoService = turnoService;
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<Turno> guardar(@RequestBody Turno turno) {
ResponseEntity<Turno> response;
 LOGGER.info("esto trae un turno: " + turno);
        //        vamos a chequear que exista el odontologo y la paciente
        if (odontologoService.buscarPorId(turno.getOdontologo().getId()) != null &&
                pacienteService.buscarPorId(turno.getPaciente().getId()) != null) {

            //setear una respuesta en 200 y vamos a hacer que devuelva el turno
            response = ResponseEntity.ok(turnoService.guardar(turno));

        } else {
            //si no existe el odontologo o el paciente
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }
        return response;
    }
    @DeleteMapping
    public void eliminar(@PathVariable Long id){
        if(turnoService.buscarPorId(id) != null){
            turnoService.eliminar(id);
        }
    }


}
