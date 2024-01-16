package domain.item;

import domain.pedido.Pedido;
import domain.producto.Producto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@Data
public class Item implements Serializable {

    public Item(Integer cantidad, Producto producto) {
        this.cantidad = cantidad;
        this.producto = producto;
    }

    @Id
    @GeneratedValue
    private Long id;

    private Integer cantidad;

    @ManyToOne
    private Producto producto = null;

    @ManyToOne
    private Pedido pedido = null;

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", cantidad=" + cantidad +
                ", producto=" + producto.getNombre() +
                ", pedido=" + pedido.getCodigo() +
                '}';
    }

}
