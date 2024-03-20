package com.dh.OdontologoMVC.controller;

import com.dh.OdontologoMVC.entity.Paciente;
import com.dh.OdontologoMVC.service.IPacienteService;
import com.dh.OdontologoMVC.service.implementacion.PacienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private IPacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public Paciente guardar(@RequestBody Paciente paciente) {
        return pacienteService.guardar(paciente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(pacienteService.buscarPorId(id));
    }


    @DeleteMapping
    public void eliminar(@PathVariable Long id){
        if(pacienteService.buscarPorId(id) != null)
        pacienteService.eliminar(id);

    }
    @PatchMapping
    public ResponseEntity<String> actualizar(@RequestBody Paciente paciente){
        ResponseEntity<String> response;
        Paciente pacienteBuscado = pacienteService.buscarPorId(paciente.getId());
        if(pacienteBuscado != null){
            pacienteService.actualizar(paciente);
            response = ResponseEntity.ok("Se actulizó paciente con id :" + paciente.getId());
        }else{
        response = ResponseEntity.ok().body("No se puede actualizar paciente");
    }
   return response;
}
}

