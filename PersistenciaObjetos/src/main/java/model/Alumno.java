package model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
public class Alumno implements Serializable {
    private String nombre;
    private String apellido;
}
