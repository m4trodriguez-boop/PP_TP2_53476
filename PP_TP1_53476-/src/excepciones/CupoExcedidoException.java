package excepciones;

public class CupoExcedidoException extends Exception {
    private static final long serialVersionUID = 1L;

    public CupoExcedidoException(String mensaje) {
        super(mensaje);
    }
}
