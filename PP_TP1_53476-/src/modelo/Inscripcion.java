package modelo;

import actividades.Actividad;
import excepciones.DatosInvalidosException;

import java.io.Serializable;
import java.time.LocalDate;

public class Inscripcion implements Serializable {
    private static final long serialVersionUID = 1L;

    private final Estudiante estudiante;
    private final Actividad actividad;
    private final LocalDate fecha;
    private String estado;
    private TicketDeAcceso ticket;

    public Inscripcion(String estado, Actividad actividad, Estudiante estudiante, LocalDate fecha) {
        if (estado == null || estado.isBlank()) {
            throw new DatosInvalidosException("El estado de la inscripción no puede estar vacío.");
        }
        if (actividad == null || estudiante == null || fecha == null) {
            throw new DatosInvalidosException("Los datos de la inscripción son inválidos.");
        }
        this.fecha = fecha;
        this.estudiante = estudiante;
        this.actividad = actividad;
        this.estado = estado;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public String getEstado() {
        return estado;
    }

    public boolean estaConfirmada() {
        return "confirmada".equalsIgnoreCase(estado);
    }

    public void confirmar() {
        this.estado = "confirmada";
    }

    public TicketDeAcceso generarTicket() {
        if (!estaConfirmada()) {
            throw new DatosInvalidosException(
                    "No se puede generar un ticket: la inscripción de " + estudiante + " no está confirmada.");
        }

        if (ticket == null) {
            ticket = new TicketDeAcceso();
        }
        return ticket;
    }

    public TicketDeAcceso getTicket() {
        return ticket;
    }

    public void dts() {
        System.out.println("Estudiante: " + estudiante);
        System.out.println("Fecha: " + fecha);
        System.out.println("Estado: " + estado);
        if (ticket != null) {
            System.out.println("Ticket: " + ticket.getIdTicket());
        }
    }

    public class TicketDeAcceso implements Serializable {
        private static final long serialVersionUID = 1L;

        private final String idTicket;
        private final LocalDate fechaEmision;
        private boolean enviado;

        private TicketDeAcceso() {
            this.idTicket = "TKT-" + estudiante.getLegajo() + "-" + actividad.getId();
            this.fechaEmision = LocalDate.now();
            this.enviado = false;
        }

        public String getIdTicket() {
            return idTicket;
        }

        public LocalDate getFechaEmision() {
            return fechaEmision;
        }

        public boolean isEnviado() {
            return enviado;
        }

        public void enviar() {
            if (!estaConfirmada()) {
                throw new DatosInvalidosException(
                        "No se puede enviar el ticket porque la inscripción no está confirmada.");
            }
            enviado = true;
            System.out.println("Enviando " + idTicket +
                    " -> " + estudiante + " | Actividad: " + actividad.getTitulo());
        }

        @Override
        public String toString() {
            return idTicket + " | Emisión: " + fechaEmision +
                    " | Estudiante: " + estudiante +
                    " | Actividad: " + actividad.getTitulo();
        }
    }
}
