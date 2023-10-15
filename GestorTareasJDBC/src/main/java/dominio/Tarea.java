package dominio;

import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
public class Tarea {
    private Long id;
    private String titulo;
    private String prioridad;
    private Long usuario_id;
    private String categoria;
    private String descripcion;

public Tarea(String titulo,String prioridad,Long usuario,String categoria,String descripcion){
    this.categoria=categoria;
    this.titulo=titulo;
    this.usuario_id=usuario;
    this.prioridad=prioridad;
    this.descripcion=descripcion;

}
public Tarea(){

}
}
