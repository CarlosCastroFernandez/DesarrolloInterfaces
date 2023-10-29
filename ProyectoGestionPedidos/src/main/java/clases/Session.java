package clases;

public class Session {
    private static Usuario usuario;
    private static Pedido pedido;

    public static Usuario getUsuario() {
        return usuario;
    }

    public static Pedido getPedido() {
        return pedido;
    }

    public static void setPedido(Pedido pedido) {
        Session.pedido = pedido;
    }

    public static void setUsuario(Usuario usuario) {
        Session.usuario = usuario;
    }
}
