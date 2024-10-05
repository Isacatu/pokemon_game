package org.grupo_games.pokemon_game.daos;

import org.grupo_games.pokemon_game.db.BaseDAO;
import org.grupo_games.pokemon_game.entities.Pokemon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PokemonDAO extends BaseDAO {
    public void crearPokemon(Pokemon pokemon) throws SQLException {
        String sql = "INSERT INTO Pokemon (apodo, nivel, salud, entrenador_id, especie_id) VALUES (?, ?, ?)";
        try (Connection connection = obtenerConexion();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, pokemon.getApodo());
            statement.setInt(2, pokemon.getNivel());
            statement.setFloat(3, pokemon.getEntrenador().getId());
            statement.setInt(4, 0);
            statement.executeUpdate();
        }
    }

    public int obtenerEspecieId(String especie) throws SQLException {
        String sql = "SELECT id FROM Especie WHERE nombre = ?";
        int especieID = -1;

        try (Connection connection = obtenerConexion();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, especie);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    especieID = resultSet.getInt("id");
                } else {
                    System.out.println("No se encontró la especie para " + especie);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el ID de la especie: " + e.getMessage());
            throw e;
        }

        return especieID;
    }


    // Método para evolucionar un Pokémon
    public void evolucionarPokemon(Pokemon pokemon, String nuevaEspecie) throws SQLException {
        String sql = "UPDATE Pokemon SET especie_id = ? WHERE id = ?";
        try (Connection connection = obtenerConexion();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            int nuevaEspecieId = obtenerEspecieId(nuevaEspecie);
            statement.setInt(1, nuevaEspecieId);
            statement.setInt(2, pokemon.getId());
            statement.executeUpdate();
        }

        EvolucionDAO evolucionDAO = new EvolucionDAO();
        evolucionDAO.registrarEvolucion(pokemon, nuevaEspecie);
    }

//    public String obtenerSiguienteEvolucion(String nombrePokemon) throws SQLException {
//        String sql = "INSERT INTO Pokemon (apodo, nivel, salud, entrenador_id, especie_id) VALUES (?, ?, ?)";
//        try (Connection connection = obtenerConexion();
//             PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setString(1, pokemon.getApodo());
//            statement.setInt(2, pokemon.getNivel());
//            statement.setFloat(3, pokemon.getEntrenador().getId());
//            statement.setInt(4, 0);
//            statement.executeUpdate();
//        }
//    }
}
