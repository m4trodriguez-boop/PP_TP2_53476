package excepciones;

public class CupoMinimoException extends Exception {
    private static final long serialVersionUID = 1L;

    public CupoMinimoException(String mensaje) {
        super(mensaje);
    }
}
