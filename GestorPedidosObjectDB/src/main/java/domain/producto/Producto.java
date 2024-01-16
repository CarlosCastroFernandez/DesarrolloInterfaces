package domain.producto;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
    private Double precio;


}
