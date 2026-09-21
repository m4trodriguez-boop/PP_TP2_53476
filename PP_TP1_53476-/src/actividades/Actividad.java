package actividades;

import excepciones.CupoExcedidoException;
import excepciones.CupoMinimoException;
import excepciones.DatosInvalidosException;
import modelo.Estudiante;
import modelo.Inscripcion;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Actividad implements Serializable {
    private static final long serialVersionUID = 1L;

    private final int id;
    private final String titulo;
    private final int cupoMaximo;
    public static final int CUPO_MINIMO = 10;
    private final ArrayList<Inscripcion> inscripciones;

    public Actividad(int id, String titulo, int cupoMaximo) {
        if (id < 0) {
            throw new DatosInvalidosException("El id de la actividad no puede ser negativo.");
        }
        if (titulo == null || titulo.isBlank()) {
            throw new DatosInvalidosException("El título de la actividad no puede estar vacío.");
        }
        if (cupoMaximo <= 0) {
            throw new DatosInvalidosException("El cupo máximo debe ser mayor que cero.");
        }

        this.id = id;
        this.titulo = titulo;
        this.cupoMaximo = cupoMaximo;
        this.inscripciones = new ArrayList<>();
    }

    public Inscripcion inscribir(Estudiante estudiante) throws CupoExcedidoException {
        if (estudiante == null) {
            throw new DatosInvalidosException("No se puede inscribir un estudiante nulo.");
        }

        if (inscripciones.size() >= cupoMaximo) {
            throw new CupoExcedidoException(
                    "No se puede inscribir a " + estudiante +
                    ": se alcanzó el cupo máximo de " + cupoMaximo + ".");
        }

        for (Inscripcion inscripcion : inscripciones) {
            if (inscripcion.getEstudiante().equals(estudiante)) {
                throw new DatosInvalidosException("El estudiante ya está inscripto en esta actividad.");
            }
        }

        Inscripcion inscripcion = new Inscripcion("confirmada", this, estudiante, LocalDate.now());
        inscripciones.add(inscripcion);
        return inscripcion;
    }

    public Inscripcion inscribirPendiente(Estudiante estudiante) throws CupoExcedidoException {
        return crearInscripcion(estudiante, "pendiente");
    }

    private Inscripcion crearInscripcion(Estudiante estudiante, String estado)
            throws CupoExcedidoException {
        if (estudiante == null) {
            throw new DatosInvalidosException("No se puede inscribir un estudiante nulo.");
        }

        if (inscripciones.size() >= cupoMaximo) {
            throw new CupoExcedidoException(
                    "No se puede inscribir a " + estudiante +
                    ": se alcanzó el cupo máximo de " + cupoMaximo + ".");
        }

        for (Inscripcion inscripcion : inscripciones) {
            if (inscripcion.getEstudiante().equals(estudiante)) {
                throw new DatosInvalidosException("El estudiante ya está inscripto en esta actividad.");
            }
        }

        Inscripcion inscripcion = new Inscripcion(estado, this, estudiante, LocalDate.now());
        inscripciones.add(inscripcion);
        return inscripcion;
    }

    public Inscripcion inscirbir(Estudiante estudiante) throws CupoExcedidoException {
        return inscribir(estudiante);
    }

    public void validarCupoMinimo() throws CupoMinimoException {
        if (inscripciones.size() < CUPO_MINIMO) {
            throw new CupoMinimoException(
                    "La actividad tiene " + inscripciones.size() +
                    " inscriptos y necesita al menos " + CUPO_MINIMO + ".");
        }
    }

    public void mostrarInscripciones() {
        System.out.println("La cantidad de inscriptos es: " + inscripciones.size());
        System.out.println("Los inscriptos son:");
        for (Inscripcion ins : inscripciones) {
            ins.dts();
        }
    }

    public boolean estaInscripto(Estudiante estudiante) {
        if (estudiante == null) {
            return false;
        }
        return inscripciones.stream()
                .anyMatch(inscripcion -> inscripcion.getEstudiante().equals(estudiante));
    }

    public void datosAct() {
        System.out.println("El id es " + id);
        System.out.println("El título es " + titulo);
        System.out.println("El cupo máximo es " + cupoMaximo);
        System.out.println("Es de tipo " + this.getTipo());
        System.out.println("Cantidad de inscriptos: " + inscripciones.size());
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public List<Inscripcion> getInscripciones() {
        return new ArrayList<>(inscripciones);
    }

    public abstract double calcularCostoMateriales();
    public abstract String getTipo();
}
