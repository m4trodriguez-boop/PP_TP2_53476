package modelo;

import excepciones.DatosInvalidosException;

import java.io.Serializable;

public class Sala implements Serializable {
    private static final long serialVersionUID = 1L;

    private final int id;
    private final String nombre;

    public Sala(int id, String nombre) {
        if (id < 0) {
            throw new DatosInvalidosException("El id de la sala no puede ser negativo.");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new DatosInvalidosException("El nombre de la sala no puede estar vacío.");
        }
        this.id = id;
        this.nombre = nombre;
    }

    public void mostrarS() {
        System.out.println("El id es " + id);
        System.out.println("El nombre es " + nombre);
    }

    @Override
    public String toString() {
        return id + " - " + nombre;
    }
}
