package domain.item;

import domain.pedido.Pedido;
import domain.producto.Producto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private Integer cantidad;

    @ManyToOne
    private Producto producto;

    @ManyToOne
    private Pedido pedido;
}
