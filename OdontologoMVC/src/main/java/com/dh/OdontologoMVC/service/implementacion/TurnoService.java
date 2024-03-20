package com.dh.OdontologoMVC.service.implementacion;


import com.dh.OdontologoMVC.entity.Turno;
import com.dh.OdontologoMVC.repository.ITurnoRepository;
import com.dh.OdontologoMVC.service.ITurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements ITurnoService {

    private ITurnoRepository turnoRepository;
@Autowired
    public TurnoService(ITurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    @Override
    public Turno guardar(Turno turno) {
        return turnoRepository.save(turno);
    }

    @Override
    public List<Turno> listarTodos() {
    return turnoRepository.findAll();
    }
    @Override
    public Turno buscarPorId (Long id) {
        Optional<Turno> turnoOptional = turnoRepository.findById(id);
        if(turnoOptional.isPresent()){
            return turnoOptional.get();
        }else{
            return null;
        }
    }
    @Override
    public void eliminar(Long id) {
        turnoRepository.deleteById(id);
    }
    @Override
    public void actualizar(Turno turno) {
    turnoRepository.save(turno);

    }



    }
