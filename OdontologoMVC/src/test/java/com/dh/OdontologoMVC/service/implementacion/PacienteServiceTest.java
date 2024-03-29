package com.dh.OdontologoMVC.service.implementacion;


import com.dh.OdontologoMVC.dto.request.PacienteRequestDTO;
import com.dh.OdontologoMVC.dto.response.PacienteResponseDTO;
import com.dh.OdontologoMVC.service.IPacienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PacienteServiceTest {
    @Autowired
    private IPacienteService pacienteService;

    @Test
    public void testCrearPaciente(){
        PacienteRequestDTO pacienteRequestDTO = new PacienteRequestDTO();
        pacienteRequestDTO.setNombre("Juan");
        pacienteRequestDTO.setApellido("Pino");
        pacienteRequestDTO.setDni("7897987");
        pacienteRequestDTO.setFechaIngreso("2022-05-23");
        pacienteService.guardar(pacienteRequestDTO);
        Optional<PacienteResponseDTO> paciente = pacienteService.buscarPorId(1L);
        assertTrue(paciente != null);


    }

    @Test
    public void testListarPaciente(){
        PacienteRequestDTO pacienteRequestDTO = new PacienteRequestDTO();
        pacienteRequestDTO.setNombre("Juan");
        pacienteRequestDTO.setApellido("Pino");
        pacienteRequestDTO.setDni("7897987");
        pacienteRequestDTO.setFechaIngreso("2022-05-23");
        pacienteService.guardar(pacienteRequestDTO);

        PacienteRequestDTO pacienteRequestDTO1 = new PacienteRequestDTO();
        pacienteRequestDTO1.setNombre("Pedro");
        pacienteRequestDTO1.setApellido("Roble");
        pacienteRequestDTO1.setDni("78975412");
        pacienteRequestDTO1.setFechaIngreso("2022-07-25");
        pacienteService.guardar(pacienteRequestDTO1);

        Set<PacienteResponseDTO> odontologos = pacienteService.listarTodos();
        assertTrue(odontologos != null);

    }



    @Test
    public void testEliminar(){
        PacienteRequestDTO pacienteRequestDTO1 = new PacienteRequestDTO();
        pacienteRequestDTO1.setNombre("Juan");
        pacienteRequestDTO1.setApellido("Pino");
        pacienteRequestDTO1.setDni("7897987");
        pacienteRequestDTO1.setFechaIngreso("2022-05-23");
        pacienteService.guardar(pacienteRequestDTO1);
        Optional<PacienteResponseDTO> paciente = pacienteService.buscarPorId(1L);
        pacienteService.eliminar(1L);
        assertNotNull(paciente);

    }

    @Test
    public void actualizarPaciente(){
        PacienteRequestDTO pacienteRequestDTO = new PacienteRequestDTO();
        pacienteRequestDTO.setNombre("Juan");
        pacienteRequestDTO.setApellido("Pino");
        pacienteRequestDTO.setDni("7897987");
        pacienteRequestDTO.setFechaIngreso("2022-05-23");
        pacienteService.guardar(pacienteRequestDTO);
        Optional<PacienteResponseDTO> pacienteOptional = pacienteService.buscarPorId(1L);
        assertNotNull(pacienteOptional);
        assertTrue(pacienteOptional.isPresent());

        pacienteRequestDTO.setNombre("Pedro");
        pacienteService.actualizar(1L, pacienteRequestDTO);

        Optional<PacienteResponseDTO> actualizarPaciente = pacienteService.buscarPorId(1L);
        assertTrue(actualizarPaciente.isPresent());
        assertEquals("Pedro", actualizarPaciente.get().getNombre());

    }
    //Profe cuando ejecuto el test buscarPorId, sin ejecutar con actualizar y eliminar, el test
    //corre normalmente, pero cuando los habilito buscarPorId se rompe, no pudimos encontrar una soluciòn
    @Test
    public void buscarPorId(){
        PacienteRequestDTO pacienteRequestDTO2 = new PacienteRequestDTO();
        pacienteRequestDTO2.setNombre("Juan");
        pacienteRequestDTO2.setApellido("Pino");
        pacienteRequestDTO2.setDni("7897987");
        pacienteRequestDTO2.setFechaIngreso("2022-05-23");
        pacienteService.guardar(pacienteRequestDTO2);
        Optional<PacienteResponseDTO> paciente = pacienteService.buscarPorId(1L);
        assertTrue(paciente != null);
        assertNotNull(paciente);
    }
}