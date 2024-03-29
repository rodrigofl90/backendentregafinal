package com.dh.OdontologoMVC.service.implementacion;

import com.dh.OdontologoMVC.dto.request.OdontologoRequestDTO;
import com.dh.OdontologoMVC.dto.request.PacienteRequestDTO;
import com.dh.OdontologoMVC.dto.request.TurnoRequestDTO;
import com.dh.OdontologoMVC.dto.response.TurnoResponseDTO;
import com.dh.OdontologoMVC.service.IOdontologoService;
import com.dh.OdontologoMVC.service.IPacienteService;
import com.dh.OdontologoMVC.service.ITurnoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TurnoServiceTest {

    @Autowired
    private ITurnoService turnoService;
    @Autowired
    private IPacienteService pacienteService;
    @Autowired
    private IOdontologoService odontologoService;

    @Test
    public void guardarTurno(){
        PacienteRequestDTO pacienteRequestDTO = new PacienteRequestDTO();
        pacienteRequestDTO.setNombre("Juan");
        pacienteRequestDTO.setApellido("Pino");
        pacienteRequestDTO.setDni("7897987");
        pacienteRequestDTO.setFechaIngreso("2022-05-23");
        pacienteService.guardar(pacienteRequestDTO);

        OdontologoRequestDTO odontologoRequestDTO = new OdontologoRequestDTO();
        odontologoRequestDTO.setNombre("Juan");
        odontologoRequestDTO.setApellido("Pino");
        odontologoRequestDTO.setMatricula("JP7896");
        odontologoService.guardar(odontologoRequestDTO);

        TurnoRequestDTO turnoRequestDTO = new TurnoRequestDTO();
        turnoRequestDTO.setPaciente_id(1L);
        turnoRequestDTO.setOdontologo_id(1L);
        turnoRequestDTO.setFecha("2022-02-23");
        turnoService.guardar(turnoRequestDTO);
        Optional<TurnoResponseDTO> turno = turnoService.buscarPorId(1L);
        assertTrue(turno != null);
    }
    @Test
    public void listarTurnos(){
        PacienteRequestDTO pacienteRequestDTO = new PacienteRequestDTO();
        pacienteRequestDTO.setNombre("Juan");
        pacienteRequestDTO.setApellido("Pino");
        pacienteRequestDTO.setDni("7897987");
        pacienteRequestDTO.setFechaIngreso("2022-05-23");
        pacienteService.guardar(pacienteRequestDTO);

        OdontologoRequestDTO odontologoRequestDTO = new OdontologoRequestDTO();
        odontologoRequestDTO.setNombre("Juan");
        odontologoRequestDTO.setApellido("Pino");
        odontologoRequestDTO.setMatricula("JP7896");
        odontologoService.guardar(odontologoRequestDTO);

        PacienteRequestDTO pacienteRequestDTO1 = new PacienteRequestDTO();
        pacienteRequestDTO1.setNombre("Juan");
        pacienteRequestDTO1.setApellido("Pino");
        pacienteRequestDTO1.setDni("7897987");
        pacienteRequestDTO1.setFechaIngreso("2022-05-23");
        pacienteService.guardar(pacienteRequestDTO1);

        OdontologoRequestDTO odontologoRequestDTO1 = new OdontologoRequestDTO();
        odontologoRequestDTO1.setNombre("Juan");
        odontologoRequestDTO1.setApellido("Pino");
        odontologoRequestDTO1.setMatricula("JP7896");
        odontologoService.guardar(odontologoRequestDTO1);

        TurnoRequestDTO turnoRequestDTO = new TurnoRequestDTO();
        turnoRequestDTO.setPaciente_id(1L);
        turnoRequestDTO.setOdontologo_id(1L);
        turnoRequestDTO.setFecha("2022-02-23");
        turnoService.guardar(turnoRequestDTO);

        TurnoRequestDTO turnoRequestDTO1 = new TurnoRequestDTO();
        turnoRequestDTO1.setPaciente_id(2L);
        turnoRequestDTO1.setOdontologo_id(2L);
        turnoRequestDTO1.setFecha("2022-03-23");
        turnoService.guardar(turnoRequestDTO1);

        Set<TurnoResponseDTO> turnos = turnoService.listarTodos();
        assertTrue(turnos !=null);
    }
    @Test
    public void buscarPorId(){
        PacienteRequestDTO pacienteRequestDTO = new PacienteRequestDTO();
        pacienteRequestDTO.setNombre("Juan");
        pacienteRequestDTO.setApellido("Pino");
        pacienteRequestDTO.setDni("7897987");
        pacienteRequestDTO.setFechaIngreso("2022-05-23");
        pacienteService.guardar(pacienteRequestDTO);

        OdontologoRequestDTO odontologoRequestDTO = new OdontologoRequestDTO();
        odontologoRequestDTO.setNombre("Juan");
        odontologoRequestDTO.setApellido("Pino");
        odontologoRequestDTO.setMatricula("JP7896");
        odontologoService.guardar(odontologoRequestDTO);

        TurnoRequestDTO turnoRequestDTO = new TurnoRequestDTO();
        turnoRequestDTO.setPaciente_id(1L);
        turnoRequestDTO.setOdontologo_id(1L);
        turnoRequestDTO.setFecha("2022-02-23");
        turnoService.guardar(turnoRequestDTO);
        Optional<TurnoResponseDTO> turno = turnoService.buscarPorId(1L);
        assertTrue(turno != null);
        assertNotNull(turno);

    }

    @Test
    public void eliminarTurno(){
        PacienteRequestDTO pacienteRequestDTO = new PacienteRequestDTO();
        pacienteRequestDTO.setNombre("Juan");
        pacienteRequestDTO.setApellido("Pino");
        pacienteRequestDTO.setDni("7897987");
        pacienteRequestDTO.setFechaIngreso("2022-05-23");
        pacienteService.guardar(pacienteRequestDTO);

        OdontologoRequestDTO odontologoRequestDTO = new OdontologoRequestDTO();
        odontologoRequestDTO.setNombre("Juan");
        odontologoRequestDTO.setApellido("Pino");
        odontologoRequestDTO.setMatricula("JP7896");
        odontologoService.guardar(odontologoRequestDTO);

        TurnoRequestDTO turnoRequestDTO = new TurnoRequestDTO();
        turnoRequestDTO.setPaciente_id(1L);
        turnoRequestDTO.setOdontologo_id(1L);
        turnoRequestDTO.setFecha("2022-02-23");
        turnoService.guardar(turnoRequestDTO);
        Optional<TurnoResponseDTO> turno = turnoService.buscarPorId(1L);
        turnoService.eliminar(1L);
        assertNotNull(turno);
    }



}