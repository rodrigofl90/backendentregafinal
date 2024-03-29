package com.dh.OdontologoMVC.service.implementacion;


import com.dh.OdontologoMVC.dto.request.OdontologoRequestDTO;
import com.dh.OdontologoMVC.dto.response.OdontologoResponseDTO;
import com.dh.OdontologoMVC.entity.Odontologo;
import com.dh.OdontologoMVC.exception.ResourceNotFoundException;
import com.dh.OdontologoMVC.repository.IOdontologoRepository;
import com.dh.OdontologoMVC.service.IOdontologoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service

public class OdontologoService implements IOdontologoService {

    private IOdontologoRepository odontologoRepository;
    @Autowired

    public OdontologoService(IOdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }
    @Autowired
    ObjectMapper mapper;



    @Override
    public OdontologoResponseDTO guardar(OdontologoRequestDTO odontologoRequestDTO) {
        Odontologo odontologoEntity = new Odontologo();
        odontologoEntity.setNombre(odontologoRequestDTO.getNombre());
        odontologoEntity.setApellido(odontologoRequestDTO.getApellido());
        odontologoEntity.setMatricula(odontologoRequestDTO.getMatricula());
        odontologoEntity = odontologoRepository.save(odontologoEntity);

        odontologoRepository.save(odontologoEntity);

        OdontologoResponseDTO odontologoResponseDTO = new OdontologoResponseDTO();
        odontologoResponseDTO.setId(odontologoEntity.getId());
        odontologoResponseDTO.setNombre(odontologoRequestDTO.getNombre());
        odontologoResponseDTO.setApellido(odontologoRequestDTO.getApellido());
        odontologoResponseDTO.setMatricula(odontologoRequestDTO.getMatricula());

        return odontologoResponseDTO;
    }


    @Override
    public Set<OdontologoResponseDTO> listarTodos() {
        //llamo a todos los odontologs
        List<Odontologo> odontologos = odontologoRepository.findAll();
        Set<OdontologoResponseDTO> odontologosDTO = new HashSet<>();
        //utilizo un foreach para recorrerlo y transformar con el mapper de odontologo a odontologo
        for(Odontologo odontologo: odontologos){
            odontologosDTO.add(mapper.convertValue((Object) odontologo, OdontologoResponseDTO.class));
        }
        return odontologosDTO;
    }

    @Override
    public OdontologoResponseDTO buscarPorId(Long id) {
        Optional<Odontologo> odontologoOptional = odontologoRepository.findById(id);
        OdontologoResponseDTO odontologoResponseDTO = null;
        if(odontologoOptional.isPresent())
            odontologoResponseDTO = mapper.convertValue(odontologoOptional, OdontologoResponseDTO.class);
            return odontologoResponseDTO;
    }

    @Override
    public void actualizar(Long id, OdontologoRequestDTO odontologoRequestDTO) {
        if (id != null) {
            Optional<Odontologo> odontologoOptional = odontologoRepository.findById(id);
            if (odontologoOptional.isPresent()) {
                Odontologo odontologo = odontologoOptional.get();
                // Actualizar los campos del paciente con los datos del DTO
                odontologo.setNombre(odontologoRequestDTO.getNombre());
                odontologo.setApellido(odontologoRequestDTO.getApellido());
                odontologo.setMatricula(odontologoRequestDTO.getMatricula());
                // Guardar los cambios en la base de datos
                odontologoRepository.save(odontologo);
            } else {
                throw new ResourceNotFoundException("No se encontró el odontologo con id: " + id);
            }
        } else {
            throw new IllegalArgumentException("El ID proporcionado no puede ser nulo");
        }
    }


    @Override
    public void eliminar(Long id) {
      odontologoRepository.deleteById(id);
    }
}
