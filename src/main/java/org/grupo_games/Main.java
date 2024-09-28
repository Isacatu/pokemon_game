package org.grupo_games;

import java.sql.SQLException;
import java.util.Scanner;

import org.grupo_games.pokemon_game.daos.EntrenadorDAO;
import org.grupo_games.pokemon_game.daos.PokedexDAO;
import org.grupo_games.pokemon_game.daos.UserDAO;
import org.grupo_games.pokemon_game.entities.Pokemon;
import org.grupo_games.pokemon_game.entities.User;
import org.grupo_games.pokemon_game.entities.Entrenador;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        UserDAO userDAO = new UserDAO();
        EntrenadorDAO entrenadorDAO = new EntrenadorDAO();
        PokedexDAO pokedexDAO = new PokedexDAO();

        System.out.println("Bienvenido al juego Pokémon");
        System.out.println("1. Iniciar sesión");
        System.out.println("2. Crear usuario");

        int opcion = scanner.nextInt();
        scanner.nextLine();  // Consumir nueva línea

        if (opcion == 1) {
            System.out.println("Ingrese su nombre de usuario:");
            String username = scanner.nextLine();
            System.out.println("Ingrese su contraseña:");
            String password = scanner.nextLine();

            User user = userDAO.iniciarSesion(username, password);
            if (user == null) {
                System.out.println("Usuario o contraseña incorrectos.");
                return;
            }

            System.out.println("Bienvenido, " + user.getUsername());
        } else if (opcion == 2) {
            // Crear nuevo usuario
            System.out.println("Ingrese un nombre de usuario:");
            String username = scanner.nextLine();
            System.out.println("Ingrese una contraseña:");
            String password = scanner.nextLine();

            User user = new User(username, password);
            int user_id = userDAO.agregarUser(user);
            user.setId(user_id);

            // Crear nuevo entrenador
            System.out.println("Ingrese un nombre para el entrenador:");
            String entrenador_nombre = scanner.nextLine();
            System.out.println("Ingrese un pueblo origen para el entrenador:");
            String entrenador_pueblo_origen = scanner.nextLine();

            Entrenador nuevoEntrenador = new Entrenador(entrenador_nombre, entrenador_pueblo_origen, user);
            entrenadorDAO.crearEntrenador(nuevoEntrenador);

            Pokemon pikachu = new Pokemon("Pikachu", 0, 100, nuevoEntrenador);
            nuevoEntrenador.agregarNuevoPokemon(pikachu);
            System.out.println("Usuario creado exitosamente y entrenador creado automáticamente con un Pikachu.");

            System.out.println("¿Desea ver la Pokédex? (si/no)");
            String respuesta = scanner.nextLine();

            if (respuesta.equalsIgnoreCase("si")) {
                pokedexDAO.mostrarTodosLosPokemones();
            } else {
                System.out.println("¡Disfruta de tu aventura Pokémon!");
            }
        }

        scanner.close();
    }
}
