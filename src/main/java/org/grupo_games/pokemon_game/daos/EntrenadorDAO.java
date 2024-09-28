package org.grupo_games.pokemon_game.daos;

import org.grupo_games.pokemon_game.db.BaseDAO;
import org.grupo_games.pokemon_game.db.DatabaseConnection;
import org.grupo_games.pokemon_game.entities.Entrenador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EntrenadorDAO extends  BaseDAO{
    public void crearEntrenador(Entrenador entrenador) throws SQLException {
        String sql = "INSERT INTO Entrenador (nombre, pueblo_origen, usuario_id) VALUES (?, ?, ?)";
        try (Connection connection = obtenerConexion();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, entrenador.getNombre());
            statement.setString(2, entrenador.getPuebloOrigen());
            statement.setInt(3, entrenador.getUsuarioId());
            statement.executeUpdate();
        }
    }
}