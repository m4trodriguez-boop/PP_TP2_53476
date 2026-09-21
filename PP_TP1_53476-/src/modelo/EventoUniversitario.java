package modelo;

import actividades.Actividad;
import actividades.Charla;
import actividades.Certificable;
import actividades.Curso;
import actividades.Taller;
import excepciones.DatosInvalidosException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EventoUniversitario implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String id;
    private String titulo;
    private double costoBase;
    private boolean gratuito;
    private static int cantidadEventos;
    private Sala sala;
    private final ArrayList<Actividad> actividades;

    public EventoUniversitario(String id, String titulo, double costoBase, boolean gratuito) {
        validarDatosEvento(id, titulo, costoBase);
        this.id = id;
        this.titulo = titulo;
        this.costoBase = costoBase;
        this.gratuito = gratuito;
        cantidadEventos++;
        this.actividades = new ArrayList<>();
    }

    public EventoUniversitario(EventoUniversitario otro) {
        if (otro == null) {
            throw new DatosInvalidosException("No se puede copiar un evento nulo.");
        }
        this.id = otro.id;
        this.titulo = otro.titulo;
        this.costoBase = otro.costoBase;
        this.gratuito = otro.gratuito;
        this.sala = otro.sala;
        this.actividades = new ArrayList<>(otro.actividades);
        cantidadEventos++;
    }

    private static void validarDatosEvento(String id, String titulo, double costoBase) {
        if (id == null || id.isBlank()) {
            throw new DatosInvalidosException("El id del evento no puede estar vacío.");
        }
        if (titulo == null || titulo.isBlank()) {
            throw new DatosInvalidosException("El título del evento no puede estar vacío.");
        }
        if (costoBase < 0) {
            throw new DatosInvalidosException("El costo base no puede ser negativo.");
        }
    }

    public double calcularCostoEstimado() {
        if (gratuito) {
            return 0;
        }

        double res = costoBase;
        for (Actividad act : actividades) {
            res += act.calcularCostoMateriales();
        }
        return res * 1.21;
    }

    public void mostrarDatos() {
        System.out.println("Evento: " + titulo + " (ID: " + id + ")");
        System.out.println("Costo estimado: " + calcularCostoEstimado());
        System.out.println("Sala asignada: " + (sala != null ? sala : "Sin sala"));
        System.out.println("Cantidad de actividades: " + actividades.size());
        for (Actividad actividad : actividades) {
            System.out.println("  - " + actividad.getTipo() +
                    ": " + actividad.getTitulo() +
                    " | inscriptos: " + actividad.getInscripciones().size());
        }
    }

    public List<String> emitirCertificados() {
        List<String> certificados = new ArrayList<>();
        for (Actividad actividad : actividades) {
            if (actividad instanceof Certificable certificable) {
                for (Inscripcion inscripcion : actividad.getInscripciones()) {
                    certificados.add(certificable.generarCertificadoAsistencia(inscripcion.getEstudiante()));
                }
            }
        }
        return certificados;
    }

    public void mostrarCertificados() {
        System.out.println("Certificados emitidos en el evento " + titulo + ":");
        List<String> certificados = emitirCertificados();
        if (certificados.isEmpty()) {
            System.out.println("  No hay certificados emitidos.");
            return;
        }
        for (String certificado : certificados) {
            System.out.println("  " + certificado);
        }
    }

    public static int getCantidadEventos() {
        return cantidadEventos;
    }

    public void asignarSala(Sala sala) {
        if (sala == null) {
            throw new DatosInvalidosException("No se puede asignar una sala nula.");
        }
        this.sala = sala;
    }

    public void crearActividad(int id, String titulo, int cupoMax, String tipo, String disertante) {
        if (!"charla".equalsIgnoreCase(tipo)) {
            throw new DatosInvalidosException("El tipo de actividad debe ser 'charla'.");
        }
        actividades.add(new Charla(disertante, id, titulo, cupoMax));
    }

    public void crearActividad(int id, String titulo, int cupoMax, String tipo, boolean requiereNotebook) {
        if (!"taller".equalsIgnoreCase(tipo)) {
            throw new DatosInvalidosException("El tipo de actividad debe ser 'taller'.");
        }
        actividades.add(new Taller(requiereNotebook, id, titulo, cupoMax));
    }

    public void crearActividad(int id, String titulo, int cupoMax, String tipo, int nivel) {
        if (!"curso".equalsIgnoreCase(tipo)) {
            throw new DatosInvalidosException("El tipo de actividad debe ser 'curso'.");
        }
        actividades.add(new Curso(nivel, id, titulo, cupoMax));
    }

    public Actividad getActividad(int indice) {
        if (indice < 0 || indice >= actividades.size()) {
            throw new DatosInvalidosException("No existe una actividad en el índice " + indice + ".");
        }
        return actividades.get(indice);
    }

    public List<Actividad> getActividades() {
        return new ArrayList<>(actividades);
    }

    public List<Inscripcion.TicketDeAcceso> getTicketsGenerados() {
        List<Inscripcion.TicketDeAcceso> tickets = new ArrayList<>();
        for (Actividad actividad : actividades) {
            for (Inscripcion inscripcion : actividad.getInscripciones()) {
                if (inscripcion.estaConfirmada() && inscripcion.getTicket() != null) {
                    tickets.add(inscripcion.getTicket());
                }
            }
        }
        return tickets;
    }

    public <T extends Actividad> List<T> filtrarActividadesPorTipo(Class<T> tipo) {
        if (tipo == null) {
            throw new DatosInvalidosException("El tipo de actividad no puede ser nulo.");
        }

        List<T> filtradas = new ArrayList<>();
        for (Actividad actividad : actividades) {
            if (tipo.isInstance(actividad)) {
                filtradas.add(tipo.cast(actividad));
            }
        }
        return filtradas;
    }

    public double calcularCostoMateriales(List<? extends Actividad> actividades) {
        if (actividades == null) {
            throw new DatosInvalidosException("La lista de actividades no puede ser nula.");
        }

        double costoTotal = 0;
        for (Actividad actividad : actividades) {
            if (actividad == null) {
                throw new DatosInvalidosException("La lista de actividades no puede contener actividades nulas.");
            }
            costoTotal += actividad.calcularCostoMateriales();
        }
        return costoTotal;
    }

    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Sala getSala() {
        return sala;
    }

    public void guardar(String rutaArchivo) throws IOException {
        if (rutaArchivo == null || rutaArchivo.isBlank()) {
            throw new IllegalArgumentException("La ruta del archivo no puede estar vacía.");
        }

        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {
            salida.writeObject(this);
        }
    }

    public static EventoUniversitario leer(String rutaArchivo) throws IOException, ClassNotFoundException {
        if (rutaArchivo == null || rutaArchivo.isBlank()) {
            throw new IllegalArgumentException("La ruta del archivo no puede estar vacía.");
        }

        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
            Object objeto = entrada.readObject();
            if (!(objeto instanceof EventoUniversitario evento)) {
                throw new IOException("El archivo no contiene un EventoUniversitario válido.");
            }
            return evento;
        }
    }
}
