package clases;

import lombok.Data;

@Data

public class Persona {
    private String nombre;
    private String apellido;
    private Integer año;
public Persona(String nombre,String apellido,Integer año) throws Error {
    this.nombre=nombre;
    this.año=año;
    this.setApellido(apellido);
}
    public void setApellido(String apellido) throws Error {
        if(apellido.length()!=3){
            throw new Error("Esta muy largo");
        }else{
            this.apellido = apellido;
        }

    }


}
