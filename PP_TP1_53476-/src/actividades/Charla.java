package actividades;

import excepciones.DatosInvalidosException;

public class Charla extends Actividad {
    private static final long serialVersionUID = 1L;
    private final String disertante;

    public Charla(String disertante, int id, String titulo, int cupoMaximo) {
        super(id, titulo, cupoMaximo);
        if (disertante == null || disertante.isBlank()) {
            throw new DatosInvalidosException("El disertante no puede estar vacío.");
        }
        this.disertante = disertante;
    }

    @Override
    public double calcularCostoMateriales() {
        return 0;
    }

    @Override
    public String getTipo() {
        return "Charla";
    }

    public String getDisertante() {
        return disertante;
    }
}
