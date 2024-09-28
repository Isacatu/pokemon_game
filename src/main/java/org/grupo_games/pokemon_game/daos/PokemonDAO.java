package org.grupo_games.pokemon_game.daos;

import org.grupo_games.pokemon_game.db.BaseDAO;
import org.grupo_games.pokemon_game.entities.Entrenador;
import org.grupo_games.pokemon_game.entities.Pokemon;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
}
