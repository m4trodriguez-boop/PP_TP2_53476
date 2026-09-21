package actividades;

import excepciones.DatosInvalidosException;
import modelo.Estudiante;

public class Taller extends Actividad implements Certificable {
    private static final long serialVersionUID = 1L;
    private final boolean requiereNotebook;

    public Taller(boolean requiereNotebook, int id, String titulo, int cupoMaximo) {
        super(id, titulo, cupoMaximo);
        this.requiereNotebook = requiereNotebook;
    }

    @Override
    public double calcularCostoMateriales() {
        return requiereNotebook ? 5000 : 2000;
    }

    @Override
    public String getTipo() {
        return "Taller";
    }

    public boolean isRequiereNotebook() {
        return requiereNotebook;
    }

    @Override
    public String generarCertificadoAsistencia(Estudiante estudiante) {
        if (estudiante == null) {
            throw new DatosInvalidosException("No se puede emitir un certificado para un estudiante nulo.");
        }
        if (!estaInscripto(estudiante)) {
            throw new DatosInvalidosException(
                    "No se puede emitir el certificado porque el estudiante no está inscripto en el taller.");
        }
        return "CERTIFICADO DE ASISTENCIA | " + ENTIDAD_INSCRIPCION +
                " | Estudiante: " + estudiante.getNombre() +
                " | Legajo: " + estudiante.getLegajo() +
                " | Actividad: " + getTitulo() +
                " | Tipo: " + getTipo();
    }
}
