package clases;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pedido")
public class Pedido implements Serializable,Comparable<Pedido> {
    /** Identificador del pedido**/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /** Código del pedido**/
    private String codigo;
    /** Fecha del pedido**/
    private String fecha;

    /** Total del pedido en euros**/
    private Integer total;
    /**Objeto Usuario asociado al pedido**/
    @ManyToOne
    @JoinColumn(name = "usuarioId")
    private Usuario usuario;
    /** Lista de elementos de pedido**/
    @OneToMany(mappedBy = "pedido",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    private List<Itempedido>items;

    /** Método toString para obtener una representación en cadena del objeto**/
    @Override
    public String toString() {
        return codigo+"-----"+fecha+"-----"+usuario.getNombre()+"-----"+total+"€";
    }

    @Override
    public int compareTo(Pedido o) {
        if(!this.codigo.equals(o.codigo)){
            return this.codigo.compareTo(o.codigo);
        }else{
            return 0;
        }

    }

    public int compareTotal(Pedido o) {
        if(!this.total.equals(o.total)){
            return this.total.compareTo(o.total);
        }else{
            return 0;
        }

    }
    public int compareFecha(Pedido o) {
        if(!this.fecha.equals(o.fecha)){
            return this.fecha.compareTo(o.fecha);
        }else{
            return 0;
        }

    }

}
