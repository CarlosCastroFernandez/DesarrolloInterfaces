package domain.pedido;

import domain.item.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Pedido implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String codigo;
    private Date fecha;

    @OneToMany(mappedBy = "pedido", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Item> items;

    public void addItem(Item item){
        item.setPedido(this);
        items.add( item);
    }
}
