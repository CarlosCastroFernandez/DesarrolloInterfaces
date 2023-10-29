package clases;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    private Integer id;
    private String nombre;
    private String email;
    private String contraseña;
    private ArrayList<Pedido>pedidos;
    public Usuario(Integer id,String nombre,String email,String contraseña){
        this.id=id;
        this.nombre=nombre;
        this.email=email;
        this.contraseña=contraseña;
        this.pedidos=new ArrayList<Pedido>();
    }

}
