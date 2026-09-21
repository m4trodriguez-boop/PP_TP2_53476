package hilos;

import modelo.EventoUniversitario;
import modelo.Inscripcion;

import java.util.List;

public class EnvioTicketsThread extends Thread {
    private final EventoUniversitario evento;

    public EnvioTicketsThread(EventoUniversitario evento) {
        if (evento == null) {
            throw new IllegalArgumentException("El evento no puede ser nulo.");
        }
        this.evento = evento;
    }

    @Override
    public void run() {
        System.out.println("Envío de tickets iniciado para: " + evento.getTitulo());
        List<Inscripcion.TicketDeAcceso> tickets = evento.getTicketsGenerados();

        if (tickets.isEmpty()) {
            System.out.println("No hay tickets para enviar en: " + evento.getTitulo());
            System.out.println("Envío finalizado para: " + evento.getTitulo());
            return;
        }

        for (Inscripcion.TicketDeAcceso ticket : tickets) {
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Envío de tickets interrumpido.");
                return;
            }

            ticket.enviar();
        }

        System.out.println("Tickets enviados para " + evento.getTitulo() + ": " + tickets.size());
    }
}
