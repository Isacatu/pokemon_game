package org.grupo_games.pokemon_game.daos;

import org.grupo_games.pokemon_game.db.BaseDAO;
import org.grupo_games.pokemon_game.entities.Entrenador;
import org.grupo_games.pokemon_game.entities.User;

import java.sql.*;

public class UserDAO extends BaseDAO {

    // Método para agregar un nuevo User
    public int agregarUser(User user) throws SQLException {
        String sql = "INSERT INTO Usuario (username, password) VALUES (?, ?)";
        try (Connection connection = obtenerConexion();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.executeUpdate();

            // Obtener el ID generado
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // Retorna el ID del usuario creado
                } else {
                    throw new SQLException("No se pudo obtener el ID del usuario creado.");
                }
            }
        }
    }


    // Método para obtener un User por su ID
    public User obtenerUserPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Usuario WHERE id = ?";
        try (Connection connection = obtenerConexion();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                return new User(username, password);
            }
        }
        return null; // Retorna null si no se encuentra el User
    }

    // Método para actualizar un User
    public void actualizarUser(User user) throws SQLException {
        String sql = "UPDATE Usuario SET username = ?, password = ?, entrenador_id = ? WHERE id = ?";
        try (Connection connection = obtenerConexion();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setInt(4, user.getId());
            statement.executeUpdate();
        }
    }

    // Método para eliminar un User
    public void eliminarUser(int id) throws SQLException {
        String sql = "DELETE FROM Usuario WHERE id = ?";
        try (Connection connection = obtenerConexion();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    // Método para iniciar sesión
    public User iniciarSesion(String username, String password) throws SQLException {
        String sql = "SELECT * FROM Usuario WHERE username = ? AND password = ?";
        try (Connection connection = obtenerConexion();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                return new User(username, password);
            }
        }
        return null;
    }


}
