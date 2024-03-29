package com.dh.OdontologoMVC.controller;

import com.dh.OdontologoMVC.dto.response.OdontologoResponseDTO;
import org.springframework.ui.Model;

import com.dh.OdontologoMVC.service.IOdontologoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/odontologo")
public class IndexController {
    private IOdontologoService odontologoService;

    public IndexController(IOdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }


    @GetMapping("/{id}")
    public String buscarOdontodologoPorIdVariable(Model model, @PathVariable Long id){
        OdontologoResponseDTO odontologo = odontologoService.buscarPorId(id);
        model.addAttribute("nombre", odontologo.getNombre());
        model.addAttribute("apellido", odontologo.getApellido());
        return "buscarOdontologo";
    }
    @GetMapping
    public String buscarOdontologoPorId(Model model, @RequestParam Long id, @RequestParam String nombre) {
        OdontologoResponseDTO odontologo = odontologoService.buscarPorId(id);
        model.addAttribute("nombre", odontologo.getNombre());
        model.addAttribute("apellido", odontologo.getApellido());
        return "buscarOdontologo";
    }
}
