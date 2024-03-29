package com.dh.OdontologoMVC.service.implementacion;


import com.dh.OdontologoMVC.dto.request.TurnoRequestDTO;
import com.dh.OdontologoMVC.dto.response.TurnoResponseDTO;
import com.dh.OdontologoMVC.entity.Odontologo;
import com.dh.OdontologoMVC.entity.Paciente;
import com.dh.OdontologoMVC.entity.Turno;
import com.dh.OdontologoMVC.exception.ResourceNotFoundException;
import com.dh.OdontologoMVC.repository.ITurnoRepository;
import com.dh.OdontologoMVC.service.ITurnoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TurnoService implements ITurnoService {

    private ITurnoRepository turnoRepository;
    @Autowired
    ObjectMapper mapper;
    @Autowired
    public TurnoService(ITurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }
    @Override
        public TurnoResponseDTO guardar(TurnoRequestDTO turnoRequestDTO) {
            //mapear el dto que recibimos a una entidad
            //instanciar turnoEntity -> para persitirlo en la BD
            Turno turnoEntity = new Turno(); //Turno(null, null, null, null)

            //instanciamos Paciente
            Paciente paciente = new Paciente();
            paciente.setId(turnoRequestDTO.getPaciente_id());

            //instanciamos Odontologo
            Odontologo odontologo = new Odontologo();
            odontologo.setId(turnoRequestDTO.getOdontologo_id());

            //seteamos Paciente y Odontologo a la entidad Turno
            turnoEntity.setOdontologo(odontologo);
            turnoEntity.setPaciente(paciente);

            //convertir el String del turnoRequestDTO a LocalDate
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            //creamos el LocalDate que vamos a tener que persistir en la BD
            LocalDate date = LocalDate.parse(turnoRequestDTO.getFecha(), formatter);

            //seteamos al turno la fecha
            turnoEntity.setFecha(date);

            //persistir el turno en la BD
            turnoRepository.save(turnoEntity);
            //acá ya tenemos la entidad con id

            //ESTE ES EL CAMINO DE VUELTA HACIA EL CONTROLADOR
            //PORQUE YA SE PERSISTIÓ LA ENTIDAD
            //mapear la entidad persistida en un dto
            TurnoResponseDTO turnoResponseDTO = new TurnoResponseDTO(); //TurnoDto(null, null, null, null)
            turnoResponseDTO.setId(turnoEntity.getId());
            turnoResponseDTO.setOdontologo_id(turnoEntity.getOdontologo().getId());
            turnoResponseDTO.setPaciente_id(turnoEntity.getPaciente().getId());
            turnoResponseDTO.setFecha(turnoEntity.getFecha().toString());

            return turnoResponseDTO;
    }

    @Override
    public Set<TurnoResponseDTO> listarTodos() {
        //llamo a todos los turnos
        List<Turno> turnos = turnoRepository.findAll();
        Set<TurnoResponseDTO> turnosDTO = new HashSet<>();
    //utilizo un foreach para recorrerlo y transformar con el mapper de turno a turnodto
        for(Turno turno: turnos){
            turnosDTO.add(mapper.convertValue(turno, TurnoResponseDTO.class));
        }
        return turnosDTO;
    }
    @Override
    public Optional<TurnoResponseDTO> buscarPorId(Long id) {
        //buscar por id
        Optional<Turno> turnoABuscar = turnoRepository.findById(id);

        //instanciamos el turnoDTO
        Optional<TurnoResponseDTO> turnoOptional = null;

        if (turnoABuscar.isPresent()) {
            //nos trajimos el turnoDTO optional a una entidad
            Turno turno = turnoABuscar.get();

            //mapear el turno al dto a devolver
            TurnoResponseDTO turnoResponseDTO = new TurnoResponseDTO();
            turnoResponseDTO.setId(turno.getId());
            turnoResponseDTO.setOdontologo_id(turno.getOdontologo().getId());
            turnoResponseDTO.setPaciente_id(turno.getPaciente().getId());
            turnoResponseDTO.setFecha(turno.getFecha().toString());

            //voy a convertir mi turnoDTO a Optional para devolverlo
            turnoOptional = Optional.of(turnoResponseDTO);

            return turnoOptional;
        } else {
            //agregan un log que diga que "No se encontró el turno con  id " + id
            throw new ResourceNotFoundException("No se encontró el turno con  id " + id);
        }
    }
    @Override
    public void eliminar(Long id) {
        turnoRepository.deleteById(id);
    }

}
