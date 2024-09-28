package org.grupo_games.pokemon_game.entities;

import org.grupo_games.pokemon_game.entities.Entrenador;

public class Pokemon {
    private int id;
    private String apodo;
    private int nivel;
    private float salud;
    private Entrenador entrenador;

    public Pokemon(int id, String apodo, int nivel, float salud) {
        this.id = id;
        this.apodo = apodo;
        this.nivel = nivel;
        this.salud = salud;
    }

    public Pokemon(String apodo, int nivel, float salud, Entrenador entrenador) {
        this.apodo = apodo;
        this.nivel = nivel;
        this.salud = salud;
        this.entrenador = entrenador;
    }

    public Pokemon(String apodo, int nivel, float salud) {
        this.apodo = apodo;
        this.nivel = nivel;
        this.salud = salud;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    public float getSalud() {
        return salud;
    }

    public void setSalud(float salud) {
        this.salud = salud;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public void evoluacionarPokemon(Pokemon pokemon) {

    }
}
