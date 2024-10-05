package org.grupo_games.pokemon_game.entities;

import org.grupo_games.pokemon_game.daos.EvolucionDAO;
import org.grupo_games.pokemon_game.daos.PiedraEvolutivaDAO;
import org.grupo_games.pokemon_game.daos.PokemonDAO;
import org.grupo_games.pokemon_game.entities.Entrenador;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Pokemon {
    private int id;
    private String apodo;
    private int nivel;
    private float salud;
    private String especie;
    private Entrenador entrenador;

    private int NIVEL_EVOLUCION = 0;

    public Pokemon(int id, String apodo, String especie, int nivel, float salud) {
        this.id = id;
        this.apodo = apodo;
        this.especie = especie;
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

    public String getEspecie(){
        return especie;
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

//    public void evolucionarPorEntrenamiento() {
//        if (this.nivel >= NIVEL_EVOLUCION) {
//            // Cambiamos la especie actual por la evolución correspondiente
//            System.out.println(this.apodo + " ha evolucionado por entrenamiento!");
//            this.especie = evolucionarEspeciePorNivel(); // Cambiamos la especie
//        } else {
//            System.out.println(this.apodo + " aún no ha alcanzado el nivel necesario para evolucionar.");
//        }
//    }

    public void evolucionarPorPiedra(PiedraEvolutiva piedra) throws SQLException {

        if (piedra == null) {
            System.out.println("No tienes una piedra, no podrás evolucionar a tu Pokémon.");
            return;
        }

        PiedraEvolutivaDAO piedraDAO = new PiedraEvolutivaDAO();
        String piedraEvolutiva = piedraDAO.obtenerPiedraSegunPokemon(this.apodo);

        EvolucionDAO evoDAO = new EvolucionDAO();
        ArrayList<String> especies = evoDAO.obtenerSiguientesEvoluciones(this.especie);

        String especieDestino = null;

        if (especies.size() == 1) {
            especieDestino = especies.get(0);
        } else if (especies.size() > 1) {
            System.out.println("Especies a las que puedes evolucionar:");
            for (int i = 0; i < especies.size(); i++) {
                System.out.println((i + 1) + ". " + especies.get(i));
            }

            Scanner scanner = new Scanner(System.in);
            System.out.print("Selecciona el número de la especie a la que deseas evolucionar: ");
            int seleccion = scanner.nextInt();

            if (seleccion > 0 && seleccion <= especies.size()) {
                especieDestino = especies.get(seleccion - 1);
            } else {
                System.out.println("Selección inválida, no se realizará la evolución.");
                return;
            }
        }

        if (piedraEvolutiva != null && especieDestino != null) {
            System.out.println(this.apodo + " ha evolucionado a " + especieDestino + " usando " + piedra.getNombre() + "!");

            PokemonDAO pokemonDAO = new PokemonDAO();
            pokemonDAO.evolucionarPokemon(this, especieDestino);

            this.especie = especieDestino;
        } else {
            System.out.println(this.apodo + " no puede evolucionar usando " + piedra.getNombre());
        }
    }
}
