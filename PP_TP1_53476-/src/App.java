import actividades.Actividad;
import actividades.Charla;
import actividades.Curso;
import actividades.Taller;
import excepciones.CupoExcedidoException;
import excepciones.DatosInvalidosException;
import modelo.Estudiante;
import modelo.EventoUniversitario;
import modelo.Inscripcion;
import modelo.Sala;
import hilos.EnvioTicketsThread;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.StreamCorruptedException;
import java.nio.file.Path;
import java.util.List;

public class App {
    public static void main(String[] args) {

        
        Estudiante estudiante1;
        Estudiante estudiante2;
        Estudiante estudiante3;
        Estudiante estudiante4;
        EventoUniversitario evento1;
        EventoUniversitario evento2;

        try {
            
            System.out.println("Estudiantes:");
            estudiante1 = new Estudiante("53421", "Marcos");
            estudiante2 = new Estudiante("53234", "Luana");
            estudiante3 = new Estudiante("53345", "Leonel");
            estudiante4 = new Estudiante("53111", "Sofía");

            estudiante1.datosestudiante();
            estudiante2.datosestudiante();
            estudiante3.datosestudiante();
            estudiante4.datosestudiante();

            System.out.println("\nEventos:");
            evento1 = new EventoUniversitario(
                    "EV-01", "Jornadas de Programación", 10000, false);
            evento2 = new EventoUniversitario(
                    "EV-02", "Jornadas de Tecnología", 8000, false);
            System.out.println("Eventos creados: " + EventoUniversitario.getCantidadEventos());

            System.out.println("\nSalas:");
            evento1.asignarSala(new Sala(1, "Aula Magna"));
            evento2.asignarSala(new Sala(2, "Laboratorio 2"));
            System.out.println("Sala de " + evento1.getTitulo() + ": " + evento1.getSala());
            System.out.println("Sala de " + evento2.getTitulo() + ": " + evento2.getSala());

            System.out.println("\nActividades:");
            evento1.crearActividad(1, "Java Orientado a Objetos", 30, "curso", 2);
            evento1.crearActividad(2, "Patrones de Diseño", 30, "taller", true);
            evento1.crearActividad(3, "El futuro de la programación", 50, "charla", "Ing. Ana Pérez");

            evento2.crearActividad(4, "Bases de Datos", 30, "curso", 1);
            evento2.crearActividad(5, "Git y GitHub", 30, "taller", false);
            evento2.crearActividad(6, "Innovación tecnológica", 50, "charla", "Lic. Juan Gómez");

            System.out.println("Actividades creadas para cada evento:");
            evento1.getActividades().forEach(Actividad::datosAct);
            evento2.getActividades().forEach(Actividad::datosAct);

            System.out.println("\nInscripciones:");

            inscribirYMostrarResultado(evento1.getActividad(0), estudiante1);
            inscribirYMostrarResultado(evento1.getActividad(0), estudiante2);
            inscribirYMostrarResultado(evento1.getActividad(1), estudiante1);
            inscribirYMostrarResultado(evento1.getActividad(1), estudiante3);
            inscribirYMostrarResultado(evento1.getActividad(2), estudiante4);

            inscribirYMostrarResultado(evento2.getActividad(0), estudiante2);
            inscribirYMostrarResultado(evento2.getActividad(0), estudiante3);
            inscribirYMostrarResultado(evento2.getActividad(1), estudiante3);
            inscribirYMostrarResultado(evento2.getActividad(1), estudiante4);
            inscribirYMostrarResultado(evento2.getActividad(2), estudiante1);

            System.out.println("\nDetalle de inscripciones del evento 1:");
            for (Actividad actividad : evento1.getActividades()) {
                System.out.println("-- " + actividad.getTipo() + ": " + actividad.getTitulo());
                actividad.mostrarInscripciones();
            }

            System.out.println("\nCertificados:");
            System.out.println("Se emiten certificados solamente para Cursos y Talleres.");
            List<String> certificados1 = evento1.emitirCertificados();
            List<String> certificados2 = evento2.emitirCertificados();
            System.out.println("Certificados emitidos en evento 1: " + certificados1.size());
            System.out.println("Certificados emitidos en evento 2: " + certificados2.size());

            System.out.println("\nCertificados emitidos:");
            evento1.mostrarCertificados();
            evento2.mostrarCertificados();

            System.out.println("\nDatos de los eventos:");
            evento1.mostrarDatos();
            evento2.mostrarDatos();

            
            
            System.out.println("\nExcepciones y persistencia:");

            
            EventoUniversitario eventoPersistencia = new EventoUniversitario(
                    "EV-03", "Evento de Persistencia", 5000, false);
            eventoPersistencia.asignarSala(new Sala(3, "Aula 3"));
            eventoPersistencia.crearActividad(99, "Actividad de prueba de cupo", 1, "taller", false);
            Actividad actividadConCupo = eventoPersistencia.getActividad(0);

            System.out.println("\nControl de cupos:");
            inscribirYMostrarResultado(actividadConCupo, estudiante1); 
            inscribirYMostrarResultado(actividadConCupo, estudiante2); 

            
            
            System.out.println("\nPersistencia:");
            Path archivo = Path.of("evento_serializado.dat");
            EventoUniversitario eventoLeido = null;

            try {
                
                try {
                    
                    inscribir(actividadConCupo, estudiante3);
                    System.out.println("Inscripción realizada correctamente.");
                } catch (CupoExcedidoException e) {
                    System.out.println("Inscripción rechazada: " + e.getMessage());
                }

                try {
                    System.out.println("Persistiendo evento en: " + archivo.toAbsolutePath());
                    eventoPersistencia.guardar(archivo.toString());
                    System.out.println("Evento persistido correctamente.");
                } catch (FileNotFoundException e) {
                    System.out.println("No se pudo abrir el archivo para persistir: " + e.getMessage());
                } catch (SecurityException e) {
                    System.out.println("No hay permisos para persistir el evento: " + e.getMessage());
                } catch (IOException e) {
                    System.out.println("Error de entrada/salida al persistir el evento: " + e.getMessage());
                }

                try {
                    System.out.println("Leyendo evento persistido...");
                    eventoLeido = EventoUniversitario.leer(archivo.toString());
                    System.out.println("Evento leído correctamente.");
                    System.out.println("Datos del evento recuperado:");
                    eventoLeido.mostrarDatos();
                } catch (FileNotFoundException e) {
                    System.out.println("No existe el archivo que se quiere leer: " + e.getMessage());
                } catch (InvalidClassException e) {
                    System.out.println("La versión de una clase serializada no es compatible: " + e.getMessage());
                } catch (StreamCorruptedException e) {
                    System.out.println("El archivo no contiene un stream de objetos válido: " + e.getMessage());
                } catch (EOFException e) {
                    System.out.println("El archivo terminó antes de completar la lectura: " + e.getMessage());
                } catch (ClassNotFoundException e) {
                    System.out.println("No se encontró la clase necesaria para reconstruir el objeto: " + e.getMessage());
                } catch (SecurityException e) {
                    System.out.println("No hay permisos para leer el evento: " + e.getMessage());
                } catch (IOException e) {
                    System.out.println("Error de entrada/salida al leer el evento: " + e.getMessage());
                }
            } finally {
                
                
                
            }

            if (eventoLeido != null) {
                System.out.println("El evento recuperado conserva sus actividades e inscripciones.");
            }

            

            
            
            System.out.println("\nFiltrado y costo de materiales:");

            
            
            List<Charla> charlasEvento1 = evento1.filtrarActividadesPorTipo(Charla.class);
            List<Taller> talleresEvento1 = evento1.filtrarActividadesPorTipo(Taller.class);
            List<Curso> cursosEvento1 = evento1.filtrarActividadesPorTipo(Curso.class);

            List<Charla> charlasEvento2 = evento2.filtrarActividadesPorTipo(Charla.class);
            List<Taller> talleresEvento2 = evento2.filtrarActividadesPorTipo(Taller.class);
            List<Curso> cursosEvento2 = evento2.filtrarActividadesPorTipo(Curso.class);

            System.out.println("\nCantidad de actividades por tipo:");
            mostrarCantidadesPorTipo(evento1, charlasEvento1, talleresEvento1, cursosEvento1);
            mostrarCantidadesPorTipo(evento2, charlasEvento2, talleresEvento2, cursosEvento2);

            
            System.out.println("\nActividades filtradas:");
            mostrarActividadesFiltradas("Charla", charlasEvento1);
            mostrarActividadesFiltradas("Taller", talleresEvento1);
            mostrarActividadesFiltradas("Curso", cursosEvento1);
            mostrarActividadesFiltradas("Charla", charlasEvento2);
            mostrarActividadesFiltradas("Taller", talleresEvento2);
            mostrarActividadesFiltradas("Curso", cursosEvento2);

            
            System.out.println("\nCosto de materiales por tipo:");
            mostrarCostosPorTipo(evento1, charlasEvento1, talleresEvento1, cursosEvento1);
            mostrarCostosPorTipo(evento2, charlasEvento2, talleresEvento2, cursosEvento2);

            
            List<Actividad> todasLasActividadesEvento1 = evento1.getActividades();
            double costoTotalEvento1 = evento1.calcularCostoMateriales(todasLasActividadesEvento1);
            System.out.println("Costo total de materiales de " + evento1.getTitulo() +
                    " usando List<Actividad>: " + costoTotalEvento1);

            

            
            
            System.out.println("\nTickets y concurrencia:");

            
            System.out.println("\nEstudiantes:");
            estudiante1.datosestudiante();
            estudiante2.datosestudiante();
            estudiante3.datosestudiante();
            estudiante4.datosestudiante();

            EventoUniversitario eventoTickets1 = new EventoUniversitario(
                    "EV-04", "Congreso de Software", 12000, false);
            EventoUniversitario eventoTickets2 = new EventoUniversitario(
                    "EV-05", "Jornada de Tecnología", 9000, false);

            eventoTickets1.asignarSala(new Sala(4, "Auditorio Principal"));
            eventoTickets2.asignarSala(new Sala(5, "Aula 5"));

            eventoTickets1.crearActividad(7, "Java Avanzado", 20, "curso", 3);
            eventoTickets1.crearActividad(8, "Testing Automatizado", 20, "taller", true);
            eventoTickets2.crearActividad(9, "Bases de Datos II", 20, "curso", 2);
            eventoTickets2.crearActividad(10, "Novedades de IA", 30, "charla", "Dra. Laura Gómez");

            System.out.println("\nEventos, salas y actividades:");
            eventoTickets1.mostrarDatos();
            eventoTickets2.mostrarDatos();

            
            
            System.out.println("\nInscripciones:");
            Inscripcion inscripcion1 = inscribirPendienteYMostrarResultado(
                    eventoTickets1.getActividad(0), estudiante1);
            Inscripcion inscripcion2 = inscribirPendienteYMostrarResultado(
                    eventoTickets1.getActividad(0), estudiante2);
            Inscripcion inscripcion3 = inscribirPendienteYMostrarResultado(
                    eventoTickets1.getActividad(1), estudiante3);
            Inscripcion inscripcion4 = inscribirPendienteYMostrarResultado(
                    eventoTickets2.getActividad(0), estudiante4);
            Inscripcion inscripcion5 = inscribirPendienteYMostrarResultado(
                    eventoTickets2.getActividad(0), estudiante1);

            System.out.println("\nConfirmación de inscripciones:");
            inscripcion1.confirmar();
            inscripcion3.confirmar();
            inscripcion4.confirmar();
            System.out.println("Confirmadas: " + inscripcion1.getEstudiante() +
                    ", " + inscripcion3.getEstudiante() +
                    " y " + inscripcion4.getEstudiante());
            System.out.println("Pendientes: " + inscripcion2.getEstudiante() +
                    " y " + inscripcion5.getEstudiante());

            System.out.println("\nGeneración de tickets:");
            generarTicketYMostrar(inscripcion1);
            generarTicketYMostrar(inscripcion3);
            generarTicketYMostrar(inscripcion4);

            try {
                inscripcion2.generarTicket();
            } catch (DatosInvalidosException e) {
                System.out.println("No se generó ticket para " +
                        inscripcion2.getEstudiante() + ": " + e.getMessage());
            }

            
            System.out.println("\nEnvío de tickets:");
            EnvioTicketsThread hiloTickets1 = new EnvioTicketsThread(eventoTickets1);
            EnvioTicketsThread hiloTickets2 = new EnvioTicketsThread(eventoTickets2);
            hiloTickets1.start();
            hiloTickets2.start();

            
            System.out.println("\nEl hilo principal continúa mostrando datos:");
            for (int i = 1; i <= 3; i++) {
                System.out.println("Consulta " + i + " desde el hilo principal");
                eventoTickets1.mostrarDatos();
                eventoTickets2.mostrarDatos();
                try {
                    Thread.sleep(120);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("El hilo principal fue interrumpido mientras mostraba datos.");
                    break;
                }
            }

            
            try {
                hiloTickets1.join();
                hiloTickets2.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("El hilo principal fue interrumpido al esperar los hilos de tickets.");
            }

            System.out.println("\nEstado final de los tickets:");
            for (Inscripcion.TicketDeAcceso ticket : eventoTickets1.getTicketsGenerados()) {
                System.out.println(ticket + " | enviado: " + ticket.isEnviado());
            }
            for (Inscripcion.TicketDeAcceso ticket : eventoTickets2.getTicketsGenerados()) {
                System.out.println(ticket + " | enviado: " + ticket.isEnviado());
            }

            

        } catch (DatosInvalidosException e) {
            System.out.println("Datos inválidos: " + e.getMessage());
        }
    }

    private static void mostrarCantidadesPorTipo(
            EventoUniversitario evento,
            List<Charla> charlas,
            List<Taller> talleres,
            List<Curso> cursos) {
        System.out.println("Evento: " + evento.getTitulo());
        System.out.println("  Charlas: " + charlas.size());
        System.out.println("  Talleres: " + talleres.size());
        System.out.println("  Cursos: " + cursos.size());
    }

    private static void mostrarActividadesFiltradas(String tipo, List<? extends Actividad> actividades) {
        System.out.println(tipo + " -> cantidad: " + actividades.size());
        for (Actividad actividad : actividades) {
            System.out.println("  - " + actividad.getTipo() + ": " + actividad.getTitulo());
        }
    }

    private static void mostrarCostosPorTipo(
            EventoUniversitario evento,
            List<Charla> charlas,
            List<Taller> talleres,
            List<Curso> cursos) {
        System.out.println("Evento: " + evento.getTitulo());
        System.out.println("  Costo de Charlas: " + evento.calcularCostoMateriales(charlas));
        System.out.println("  Costo de Talleres: " + evento.calcularCostoMateriales(talleres));
        System.out.println("  Costo de Cursos: " + evento.calcularCostoMateriales(cursos));
    }

    private static Inscripcion inscribirPendienteYMostrarResultado(
            Actividad actividad, Estudiante estudiante) {
        try {
            Inscripcion inscripcion = actividad.inscribirPendiente(estudiante);
            System.out.println("Inscripción pendiente: " + estudiante +
                    " -> " + actividad.getTipo() + " '" + actividad.getTitulo() + "'.");
            return inscripcion;
        } catch (CupoExcedidoException e) {
            System.out.println("Inscripción rechazada: " + e.getMessage());
            return null;
        }
    }

    private static void generarTicketYMostrar(Inscripcion inscripcion) {
        Inscripcion.TicketDeAcceso ticket = inscripcion.generarTicket();
        System.out.println("Ticket generado: " + ticket);
    }

    private static void inscribir(Actividad actividad, Estudiante estudiante)
            throws CupoExcedidoException {
        actividad.inscribir(estudiante);
    }

    private static void inscribirYMostrarResultado(Actividad actividad, Estudiante estudiante) {
        try {
            inscribir(actividad, estudiante);
            System.out.println("Inscripción realizada: " + estudiante +
                    " -> " + actividad.getTipo() + " '" + actividad.getTitulo() + "'.");
        } catch (CupoExcedidoException e) {
            System.out.println("Inscripción rechazada: " + e.getMessage());
        }
    }

    private static Actividad crearActividadConCupoUno(EventoUniversitario evento) {
        evento.crearActividad(99, "Actividad de prueba de cupo", 1, "taller", false);
        return evento.getActividad(evento.getActividades().size() - 1);
    }
}
