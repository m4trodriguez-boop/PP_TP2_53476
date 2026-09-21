package modelo;

import excepciones.DatosInvalidosException;

import java.io.Serializable;

public class Estudiante implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String legajo;
    private final String nombre;

    public Estudiante(String legajo, String nombre) {
        if (legajo == null || legajo.isBlank()) {
            throw new DatosInvalidosException("El legajo no puede estar vacío.");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new DatosInvalidosException("El nombre no puede estar vacío.");
        }
        this.legajo = legajo;
        this.nombre = nombre;
    }

    public void datosestudiante() {
        System.out.println("El legajo es " + legajo);
        System.out.println("El nombre es " + nombre);
    }

    public String getLegajo() {
        return legajo;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return this.nombre + " (Legajo: " + this.legajo + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Estudiante otro)) return false;
        return legajo.equals(otro.legajo);
    }

    @Override
    public int hashCode() {
        return legajo.hashCode();
    }
}
