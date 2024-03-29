package com.dh.OdontologoMVC.dto.response;

import com.dh.OdontologoMVC.entity.Domicilio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PacienteResponseDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String dni;
    private String fechaIngreso;
    private Domicilio domicilio;
}
