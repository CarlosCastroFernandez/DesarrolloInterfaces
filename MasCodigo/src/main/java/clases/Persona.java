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
    String numero="0123456789";
        for(int i=0;i<apellido.length();i++){
            if(numero.contains(apellido.charAt(i)+"")){
                throw new Error("Contiene numeros");
            }
        }
        this.apellido=apellido;
    }


}
