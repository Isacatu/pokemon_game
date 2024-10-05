package org.grupo_games.pokemon_game.entities;

import java.util.ArrayList;

public class EquipoBatalla {
    private ArrayList<Pokemon> equipo;

    // Constructor
    public EquipoBatalla() {
        equipo = new ArrayList<>();
    }

    // Método para agregar un Pokémon al equipo
    public void agregarPokemon(Pokemon pokemon) {
        equipo.add(pokemon);
    }

    // Método para obtener el equipo completo
    public ArrayList<Pokemon> obtenerEquipo() {
        return equipo;
    }
}