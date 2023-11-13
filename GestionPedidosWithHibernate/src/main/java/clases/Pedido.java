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
public class Pedido implements Serializable {
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
}
