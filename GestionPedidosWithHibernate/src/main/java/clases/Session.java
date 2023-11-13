package clases;

public class Session {
    private static Usuario usuario; // Usuario actual en la sesión
    private static Pedido pedido;   // Pedido actual en la sesión
    private static Itempedido item;

    public static Itempedido getItem() {
        return item;
    }

    public static void setItem(Itempedido item) {
        Session.item = item;
    }

    /**
     * Obtiene el objeto Usuario asociado a la sesión.
     *
     * @return El usuario actual en la sesión.
     */
    public static Usuario getUsuario() {
        return usuario;
    }

    /**
     * Obtiene el objeto Pedido asociado a la sesión.
     *
     * @return El pedido actual en la sesión.
     */
    public static Pedido getPedido() {
        return pedido;
    }

    /**
     * Establece el objeto Pedido en la sesión.
     *
     * @param pedido El nuevo pedido que se asociará a la sesión.
     */
    public static void setPedido(Pedido pedido) {
        Session.pedido = pedido;
    }

    /**
     * Establece el objeto Usuario en la sesión.
     *
     * @param usuario El nuevo usuario que se asociará a la sesión.
     */
    public static void setUsuario(Usuario usuario) {
        Session.usuario = usuario;
    }
}