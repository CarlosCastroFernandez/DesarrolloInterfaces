package domain.pedido;

import domain.item.Item;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Data
public class Pedido implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String codigo;
    private Date fecha;

    @OneToMany(mappedBy = "pedido", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<>(0);

    public Pedido() {
    }

    public void addItem(Item item) {
        item.setPedido(this);
        items.add(item);
    }

    public double getCalculoTotal() {
        var salida = 0.0;
        for (Item i : this.getItems()) salida += i.getProducto().getPrecio() * i.getCantidad();
        return salida;
    }

    public Pedido(String codigo, Date fecha) {
        this.codigo = codigo;
        this.fecha = fecha;
    }

    public String toString() {
        return "Pedido(id=" + this.getId() + ", codigo=" + this.getCodigo() + ", fecha=" + this.getFecha() + ", items=" + this.getItems() + ")";
    }

    /*
    En las relaciones OneToMany, puede ocurrir que JavaFX genere un stackOverflow por un mal uso de
    el equals autogenerado por lombok.
    En ese caso, es recomendable sobreescribirlo y no incluir el campo de la relación en la comprobación.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return Objects.equals(id, pedido.id) && Objects.equals(codigo, pedido.codigo) && Objects.equals(fecha, pedido.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, codigo, fecha);
    }
}
