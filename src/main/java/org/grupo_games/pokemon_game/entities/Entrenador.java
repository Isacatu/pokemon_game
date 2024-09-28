package org.grupo_games.pokemon_game.entities;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.ArrayList;
import java.util.Scanner;

public class Entrenador {
    private int id;
    private String nombre;
    private String pueblo_origen;
    private User user;
    private ArrayList<Pokemon> pokemones = new ArrayList();

    public Entrenador(String nombre, String pueblo_origen, User user) {
        this.nombre = nombre;
        this.pueblo_origen = pueblo_origen;
        this.user = user;
    }

    public String getPuebloOrigen() {
        return pueblo_origen;
    }

    public void setPuebloOrigen(String pueblo_origen) {
        this.pueblo_origen = pueblo_origen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getUsuarioId(){
        return user.getId();
    }

    public Pokemon getPokemonId(int id) {
        return this.pokemones.get(id);
    }

    public void agregarNuevoPokemon(Pokemon pokemon) {
        pokemones.add(pokemon);

        System.out.println("Se ha agregado un nuevo pokemon " + pokemon.getApodo());
    }

    public void entrenarPokemon() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresa el ID del pokemon que quieres entrenar");
        int pokemon_id = sc.nextInt();

        Pokemon pokemon_a_entrenar = pokemones.get(pokemon_id);

        boolean seguir_entrenando = true;
        while(seguir_entrenando) {
            System.out.println("Entrenando Pokemon");
            int nievel_actual = pokemon_a_entrenar.getNivel();

            pokemon_a_entrenar.setNivel(nievel_actual + 1);
        }

    }
}
