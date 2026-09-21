package actividades;

import excepciones.DatosInvalidosException;
import modelo.Estudiante;

public class Curso extends Actividad implements Certificable {
    private static final long serialVersionUID = 1L;
    private final int nivel;

    public Curso(int nivel, int id, String titulo, int cupoMaximo) {
        super(id, titulo, cupoMaximo);
        if (nivel <= 0) {
            throw new DatosInvalidosException("El nivel del curso debe ser mayor que cero.");
        }
        this.nivel = nivel;
    }

    @Override
    public double calcularCostoMateriales() {
        return 3000.0 + (nivel * 1000.0);
    }

    @Override
    public String getTipo() {
        return "Curso";
    }

    public int getNivel() {
        return nivel;
    }

    @Override
    public String generarCertificadoAsistencia(Estudiante estudiante) {
        if (estudiante == null) {
            throw new DatosInvalidosException("No se puede emitir un certificado para un estudiante nulo.");
        }
        if (!estaInscripto(estudiante)) {
            throw new DatosInvalidosException(
                    "No se puede emitir el certificado porque el estudiante no está inscripto en el curso.");
        }
        return "CERTIFICADO DE ASISTENCIA | " + ENTIDAD_INSCRIPCION +
                " | Estudiante: " + estudiante.getNombre() +
                " | Legajo: " + estudiante.getLegajo() +
                " | Actividad: " + getTitulo() +
                " | Tipo: " + getTipo() +
                " | Nivel: " + nivel;
    }
}
