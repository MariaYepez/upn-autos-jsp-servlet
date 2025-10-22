package com.upn.autos.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Auto {
    private int id;
    private String marca;
    private String modelo;
    private int anio;
    private double precio;
}
