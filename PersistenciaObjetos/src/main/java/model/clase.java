package model;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;

@Data
public class clase implements Serializable {
    private ArrayList<Alumno>alumnos;
}
