package com.dh.OdontologoMVC.controller;

import org.springframework.ui.Model;

import com.dh.OdontologoMVC.entity.Odontologo;
import com.dh.OdontologoMVC.service.IOdontologoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/odontologo")
public class IndexController {
    private IOdontologoService odontologoService;

    public IndexController(IOdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }


    @GetMapping("/{id}")
    public String buscarOdontodologoPorIdVariable(Model model, @PathVariable Long id){
        Odontologo odontologo = odontologoService.buscarPorId(id);
        model.addAttribute("nombre", odontologo.getNombre());
        model.addAttribute("apellido", odontologo.getApellido());
        return "buscarOdontologo";
    }

}
