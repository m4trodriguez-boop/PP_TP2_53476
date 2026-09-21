package actividades;

import modelo.Estudiante;

public interface Certificable {
    String ENTIDAD_INSCRIPCION = "UTN-FRM";

    String generarCertificadoAsistencia(Estudiante estudiante);
}
