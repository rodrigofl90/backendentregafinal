package com.dh.OdontologoMVC.service.implementacion;
import com.dh.OdontologoMVC.dto.request.PacienteRequestDTO;
import com.dh.OdontologoMVC.dto.response.PacienteResponseDTO;
import com.dh.OdontologoMVC.entity.Paciente;
import com.dh.OdontologoMVC.exception.ResourceNotFoundException;
import com.dh.OdontologoMVC.repository.IPacienteRepository;
import com.dh.OdontologoMVC.service.IPacienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PacienteService implements IPacienteService {

    private IPacienteRepository pacienteRepository;
    @Autowired
    ObjectMapper mapper;
    @Autowired
    public PacienteService(IPacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public PacienteResponseDTO guardar(PacienteRequestDTO pacienteRequestDTO) {
        //creo un paciente en la base de datos
        Paciente pacienteEntity = new Paciente();
        pacienteEntity.setNombre(pacienteRequestDTO.getNombre());
        pacienteEntity.setApellido(pacienteRequestDTO.getApellido());
        pacienteEntity.setDni(pacienteRequestDTO.getDni());
        pacienteEntity.setFechaIngreso(LocalDate.parse(pacienteRequestDTO.getFechaIngreso()));
        pacienteEntity = pacienteRepository.save(pacienteEntity);

        //asigno los valores del paciente guardado
        PacienteResponseDTO pacienteResponseDTO = new PacienteResponseDTO();
        pacienteResponseDTO.setId(pacienteEntity.getId());
        pacienteResponseDTO.setNombre(pacienteRequestDTO.getNombre());
        pacienteResponseDTO.setApellido(pacienteRequestDTO.getApellido());
        pacienteResponseDTO.setDni(pacienteRequestDTO.getDni());
        pacienteResponseDTO.setFechaIngreso(pacienteRequestDTO.getFechaIngreso());

        return pacienteResponseDTO;

    }
    @Override
    public Set<PacienteResponseDTO> listarTodos() {
        //llamo a todos los turnos
        List<Paciente> pacientes = pacienteRepository.findAll();
        Set<PacienteResponseDTO> pacientesDTO = new HashSet<>();
        //utilizo un foreach para recorrerlo y transformar con el mapper de turno a turnodto
        for(Paciente paciente: pacientes){
            pacientesDTO.add(mapper.convertValue(paciente, PacienteResponseDTO.class));
        }
        return pacientesDTO;
    }

    @Override
    public Optional<PacienteResponseDTO> buscarPorId(Long id) {
        Optional<Paciente> pacienteBuscar = pacienteRepository.findById(id);
        Optional<PacienteResponseDTO> pacienteOptional;

        if(pacienteBuscar.isPresent()){
            Paciente paciente = pacienteBuscar.get();

            PacienteResponseDTO pacienteResponseDTO = new PacienteResponseDTO();
            pacienteResponseDTO.setId(paciente.getId());
            pacienteResponseDTO.setNombre(paciente.getNombre());
            pacienteResponseDTO.setApellido(paciente.getApellido());
            pacienteResponseDTO.setDni(paciente.getDni());
            if (paciente.getFechaIngreso() != null) {
                pacienteResponseDTO.setFechaIngreso(paciente.getFechaIngreso().toString());
            }
            pacienteResponseDTO.setDomicilio(paciente.getDomicilio());

            pacienteOptional = Optional.of(pacienteResponseDTO);

            return pacienteOptional;
        }else{
            throw new ResourceNotFoundException("No se encontró el paciente con id: " + id);
        }
    }

    @Override
    public void eliminar(Long id) {
       pacienteRepository.deleteById(id);
    }

    @Override
    public void actualizar(Long id,PacienteRequestDTO pacienteRequestDTO) {
        if (id != null) {
            Optional<Paciente> pacienteOptional = pacienteRepository.findById(id);
            if (pacienteOptional.isPresent()) {
                Paciente paciente = pacienteOptional.get();
                // Actualizar los campos del paciente con los datos del DTO
                paciente.setNombre(pacienteRequestDTO.getNombre());
                paciente.setApellido(pacienteRequestDTO.getApellido());
                paciente.setDni(pacienteRequestDTO.getDni());
                paciente.setFechaIngreso(LocalDate.parse(pacienteRequestDTO.getFechaIngreso()));
                // Guardar los cambios en la base de datos
                pacienteRepository.save(paciente);
            } else {
                throw new ResourceNotFoundException("No se encontró el paciente con id: " + id);
            }
        } else {
            throw new IllegalArgumentException("El ID proporcionado no puede ser nulo");
        }
    }
}
