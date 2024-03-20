package com.dh.OdontologoMVC.service.implementacion;


import com.dh.OdontologoMVC.entity.Odontologo;
import com.dh.OdontologoMVC.repository.IOdontologoRepository;
import com.dh.OdontologoMVC.service.IOdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class OdontologoService implements IOdontologoService {

    private IOdontologoRepository odontologoRepository;
    @Autowired

    public OdontologoService(IOdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    @Override
    public void actualizar(Odontologo odontologo) {
         odontologoRepository.save(odontologo);
    }

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        return odontologoRepository.save(odontologo);
    }

    @Override
    public List<Odontologo> listarTodos() {
        return odontologoRepository.findAll();
    }

    @Override
    public Odontologo buscarPorId(Long id) {
        Optional<Odontologo> odontologoOptional = odontologoRepository.findById(id);
        if(odontologoOptional.isPresent()){
            return odontologoOptional.get();
        }else{
            return null;
        }

    }

    @Override
    public void eliminar(Long id) {
      odontologoRepository.deleteById(id);
    }
}
