package org.example;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
public class Alumno {
    private String nombre;
    private String apellido;
    public Alumno(String nombre,String apellido){
        this.nombre=nombre;
        this.apellido=apellido;
    }
    public static ArrayList<Alumno>listaAlumno(){
        Alumno alumno1=new Alumno("Carlos","Castro");
        Alumno alumno2=new Alumno("Cayetano","Perez");
        Alumno alumno3=new Alumno("Lorena","Marin");

        ArrayList<Alumno>alumnos=new ArrayList<>();
        alumnos.add(alumno1);alumnos.add(alumno2);alumnos.add(alumno3);
return alumnos;

    }
}
