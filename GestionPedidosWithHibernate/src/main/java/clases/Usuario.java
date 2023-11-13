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
@Table(name = "usuario")
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;         // Identificador único del usuario
    private String nombre;      // Nombre del usuario
    private String email;       // Correo electrónico del usuario
    private String contraseña;  // Contraseña del usuario
    @OneToMany(mappedBy = "usuario",fetch = FetchType.EAGER)
    private List<Pedido> pedidos;  // Lista de pedidos asociados al usuario

    /**
     * Constructor de la clase Usuario.
     *
     * @param id          Identificador único del usuario.
     * @param nombre      Nombre del usuario.
     * @param email       Correo electrónico del usuario.
     * @param contraseña  Contraseña del usuario.
     */
    public Usuario(Integer id, String nombre, String email, String contraseña) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.contraseña = contraseña;
        this.pedidos = new ArrayList<Pedido>();
    }
}
