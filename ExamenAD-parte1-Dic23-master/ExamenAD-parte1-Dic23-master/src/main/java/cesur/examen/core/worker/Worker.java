package cesur.examen.core.worker;

import lombok.Data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * EXAMEN DE ACCESO A DATOS
 * Diciembre 2023
 *
 * Nombre del alumno:
 * Fecha:
 */
@Data
public class Worker implements Serializable {

    private Long id;
    private String name;
    private String dni;
    private Date from;
  /*  public Integer comparable(Worker worker){
        SimpleDateFormat formto=new SimpleDateFormat("dd/MM/yyyy");

    }*/



}
