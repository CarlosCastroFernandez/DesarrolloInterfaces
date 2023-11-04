package clases;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    /** Identificador del pedido**/
    private Integer id;
    /** Código del pedido**/
    private String codigo;
    /** Fecha del pedido**/
    private String fecha;
    /** Identificador del usuario que realizó el pedido**/
    private Integer usuarioId;
    /** Total del pedido en euros**/
    private Integer total;
    /**Objeto Usuario asociado al pedido**/
    private Usuario usuario;
    /** Lista de elementos de pedido**/
    private ArrayList<Itempedido> items = new ArrayList<>();

    /** Método toString para obtener una representación en cadena del objeto**/
    @Override
    public String toString() {
        return codigo+"-----"+fecha+"-----"+usuario.getNombre()+"-----"+total+"€";
    }
}
