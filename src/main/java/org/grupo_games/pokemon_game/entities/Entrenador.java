package org.grupo_games.pokemon_game.entities;

public class Entrenador {
    private String nombre;
    private String pueblo_origen;
    private User user;

    public Entrenador(String nombre, String pueblo_origen, User user) {
        this.nombre = nombre;
        this.pueblo_origen = pueblo_origen;
        this.user = user;
    }

    public String getPueblo_origen() {
        return pueblo_origen;
    }

    public void setPueblo_origen(String pueblo_origen) {
        this.pueblo_origen = pueblo_origen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPuebloOrigen() {
        return pueblo_origen;
    }

    public void setPuebloOrigen(String pueblo_origen) {
        this.pueblo_origen = pueblo_origen;
    }

    public int getUsuarioId(){
        return user.getId();
    }
}
