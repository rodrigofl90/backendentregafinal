package com.dh.OdontologoMVC.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PacienteRequestDTO {
    private String nombre;
    private String apellido;
    private String dni;
    private String fechaIngreso;

}
