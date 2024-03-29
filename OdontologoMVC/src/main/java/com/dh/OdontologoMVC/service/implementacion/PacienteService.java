package com.dh.OdontologoMVC.service.implementacion;
import com.dh.OdontologoMVC.entity.Paciente;
import com.dh.OdontologoMVC.repository.IPacienteRepository;
import com.dh.OdontologoMVC.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PacienteService implements IPacienteService {

    private IPacienteRepository pacienteRepository;
    @Autowired
    public PacienteService(IPacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public Paciente guardar(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    @Override
    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll() ;
    }

    @Override
    public Paciente buscarPorId(Long id) {
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(id);
        if(pacienteOptional.isPresent()){
            return pacienteOptional.get();
        }else{
            return null;
        }
    }

    @Override
    public void eliminar(Long id) {
       pacienteRepository.deleteById(id);
    }

    @Override
    public void actualizar(Paciente paciente) {
          pacienteRepository.save(paciente);
    }
}
