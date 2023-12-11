package org.example;

import lombok.Data;

import java.io.Serializable;

@Data
public class Usuario implements Serializable {
    private String nombre;
    private String apellido;
    private Integer edad;

}
