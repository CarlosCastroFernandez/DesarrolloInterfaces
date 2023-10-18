package clases;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    private Integer id;
    private String codigo;
    private String fecha;
    private Integer usuarioId;
    private Integer total;
}
