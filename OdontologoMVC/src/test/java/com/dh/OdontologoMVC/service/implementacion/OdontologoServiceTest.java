package com.dh.OdontologoMVC.service.implementacion;

import com.dh.OdontologoMVC.dto.request.OdontologoRequestDTO;
import com.dh.OdontologoMVC.dto.response.OdontologoResponseDTO;
import com.dh.OdontologoMVC.service.IOdontologoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OdontologoServiceTest {

    @Autowired
    private IOdontologoService odontologoService;

    @Test
    public void testCrearOdontolgo(){
        OdontologoRequestDTO odontologoRequestDTO = new OdontologoRequestDTO();
        odontologoRequestDTO.setNombre("Juan");
        odontologoRequestDTO.setApellido("Pino");
        odontologoRequestDTO.setMatricula("JP7896");
        odontologoService.guardar(odontologoRequestDTO);
        OdontologoResponseDTO odontologo = odontologoService.buscarPorId(1L);
        assertTrue(odontologo != null);


    }

    @Test
    public void testListarOdontologo(){
        Set<OdontologoResponseDTO> odontologos = odontologoService.listarTodos();
        assertTrue(odontologos != null);
    }

    @Test
    public void buscarPorID(){
        OdontologoRequestDTO odontologoRequestDTO = new OdontologoRequestDTO();
        odontologoRequestDTO.setNombre("Juan");
        odontologoRequestDTO.setApellido("Pino");
        odontologoRequestDTO.setMatricula("JP7896");
        odontologoService.guardar(odontologoRequestDTO);
        odontologoService.buscarPorId(1L);
        assertTrue(odontologoRequestDTO != null);
    }

    @Test
    public void testEliminarOdontologo(){
        OdontologoRequestDTO odontologoRequestDTO = new OdontologoRequestDTO();
        odontologoRequestDTO.setNombre("Juan");
        odontologoRequestDTO.setApellido("Pino");
        odontologoRequestDTO.setMatricula("JP7896");
        odontologoService.guardar(odontologoRequestDTO);
        OdontologoResponseDTO odontologo = odontologoService.buscarPorId(1L);
        odontologoService.eliminar(1L);
        assertNotNull(odontologo);

        OdontologoResponseDTO eliminarOdontologo = odontologoService.buscarPorId(1L);
        assertNull(eliminarOdontologo);
    }

    @Test
    public void actualizarOdontologo(){
        OdontologoRequestDTO odontologoRequestDTO = new OdontologoRequestDTO();
        odontologoRequestDTO.setNombre("Juan");
        odontologoRequestDTO.setApellido("Pino");
        odontologoRequestDTO.setMatricula("JP7896");
        odontologoService.guardar(odontologoRequestDTO);
        OdontologoResponseDTO odontologo1 = odontologoService.buscarPorId(1L);
        assertNotNull(odontologo1);

        odontologoRequestDTO.setNombre("Pedro");
        odontologoService.actualizar(1L, odontologoRequestDTO);

        OdontologoResponseDTO actualizarOdontologo = odontologoService.buscarPorId(1L);
        assertNotNull(actualizarOdontologo);
        assertEquals("Pedro", actualizarOdontologo.getNombre());

    }

}