package clases;



import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity

public class Usuario implements Serializable {

    /** Identificador único del usuario **/
    @Id
    @GeneratedValue()
    private Integer id;

    /** Nombre del usuario **/
    private String nombre;

    /** Correo electrónico del usuario **/
    private String email;

    /** Contraseña del usuario **/
    private String contraseña;

    /** Lista de pedidos asociados al usuario **/
    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
    private List<Pedido> pedidos;
    public Usuario(){

    }

    public Usuario( Integer id,String nombre, String email, String contraseña, List<Pedido> pedidos) {
        this.id=id;
        this.nombre = nombre;
        this.email = email;
        this.contraseña = contraseña;
        this.pedidos = pedidos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", contraseña='" + contraseña + '\'' +
                ", pedidos=" + pedidos +
                '}';
    }
}