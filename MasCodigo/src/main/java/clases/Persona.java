package clases;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Persona {
    private String nombre;
    private String apellido;
    private Integer año;

}
