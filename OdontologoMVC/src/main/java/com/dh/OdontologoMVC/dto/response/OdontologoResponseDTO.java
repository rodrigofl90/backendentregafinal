package com.dh.OdontologoMVC.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OdontologoResponseDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String matricula;
}
