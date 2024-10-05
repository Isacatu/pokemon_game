package org.grupo_games.pokemon_game.entities;

public abstract class Objeto {
    private String nombre;
    private String descripcion;
    private int cantidad = 0;

    public Objeto(String nombre, String descripcion, int cantidad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
    }

    public Objeto(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Método abstracto para describir el uso del objeto, debe ser implementado en las subclases
    public abstract void usar();

    public String toJSON() {
        return "Objeto{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
