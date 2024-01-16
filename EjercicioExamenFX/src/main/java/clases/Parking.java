package clases;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Parking {
    private String matricula;
    private String modeloCoche;
    private Cliente cliente;
    private String tarifa;
    private LocalDate entrada;
    private LocalDate salida;
    private Integer coste;


}
