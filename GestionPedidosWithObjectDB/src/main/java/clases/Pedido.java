package clases;



import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity

public class Pedido implements Serializable,Comparable<Pedido> {
    /** Identificador del pedido**/
    @Id
    @GeneratedValue()
    private Integer id;
    /** Código del pedido**/
    private String codigo;
    /** Fecha del pedido**/
    private String fecha;

    /** Total del pedido en euros**/
    private Integer total;
    /**Objeto Usuario asociado al pedido**/
    @ManyToOne

    private Usuario usuario;
    /** Lista de elementos de pedido**/
    @OneToMany(mappedBy = "pedido",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    private List<Itempedido>items;

    /** Método toString para obtener una representación en cadena del objeto**/
    @Override
    public String toString() {
        return codigo+"-----"+fecha+"-----"+usuario.getNombre()+"-----"+total+"€";
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Itempedido> getItems() {
        return items;
    }

    public void setItems(List<Itempedido> items) {
        this.items = items;
    }

    /**
     * Método compareTo para la interfaz Comparable. Compara dos objetos Pedido
     * basándose en el código del pedido.
     */
    @Override
    public int compareTo(Pedido o) {
        if (!this.codigo.equals(o.codigo)) {
            return this.codigo.compareTo(o.codigo);
        } else {
            return 0;
        }
    }

    /**
     * Método compareTotal para comparar dos objetos Pedido basándose en el total del
     * pedido.
     */
    public int compareTotal(Pedido o) {
        if (!this.total.equals(o.total)) {
            return this.total.compareTo(o.total);
        } else {
            return 0;
        }
    }

    /**
     * Método compareFecha para comparar dos objetos Pedido basándose en la fecha del
     * pedido.
     */
    public int compareFecha(Pedido o) {
        if (!this.fecha.equals(o.fecha)) {
            return this.fecha.compareTo(o.fecha);
        } else {
            return 0;
        }
    }

    public Pedido(Integer id, String codigo, String fecha, Integer total, Usuario usuario, List<Itempedido> items) {
        this.id = id;
        this.codigo = codigo;
        this.fecha = fecha;
        this.total = total;
        this.usuario = usuario;
        this.items = items;
    }
    public Pedido(){

    }


}