package org.grupo_games.pokemon_game.daos;

import org.grupo_games.pokemon_game.db.BaseDAO;
import org.grupo_games.pokemon_game.entities.Pokemon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EvolucionDAO extends BaseDAO {
    // Método para registrar la evolución en la tabla RegistroEvolucion
    public void registrarEvolucion(Pokemon pokemon, String nuevaEspecie) {
        String sqlSelect = "SELECT " +
                "    E.id AS 'Especie Origen ID', " +
                "    ED.id AS 'Especie Destino ID' " +
                "FROM Pokemon pk " +
                "         INNER JOIN Especie E ON pk.especie_id = E.id " +
                "         INNER JOIN Evolucion EV ON EV.especie_origen_id = E.id " +
                "         INNER JOIN Especie ED ON EV.especie_destino_id = ED.id " +
                "WHERE E.nombre = ?;";

        String sqlInsert = "INSERT INTO RegistroEvolucion (pokemon_id, especie_origen_id, especie_destino_id, fecha_evolucion) VALUES (?, ?, ?, CURRENT_DATE);";

        try (Connection connection = obtenerConexion();
             PreparedStatement selectStatement = connection.prepareStatement(sqlSelect);
             PreparedStatement insertStatement = connection.prepareStatement(sqlInsert)) {

            selectStatement.setString(1, pokemon.getEspecie());

            try (ResultSet resultSet = selectStatement.executeQuery()) {
                if (resultSet.next()) {
                    int especieOrigenId = resultSet.getInt("Especie Origen ID");
                    int especieDestinoId = resultSet.getInt("Especie Destino ID");

                    int pokemonId = pokemon.getId();

                    insertStatement.setInt(1, pokemonId);
                    insertStatement.setInt(2, especieOrigenId);
                    insertStatement.setInt(3, especieDestinoId);

                    insertStatement.executeUpdate();
                    System.out.println("Evolución registrada exitosamente.");
                } else {
                    System.out.println("No se encontró la evolución para " + pokemon.getEspecie());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> obtenerSiguientesEvoluciones(String especie) {
        String sql = "SELECT " +
                "    E.nombre AS 'Especie Origen', " +
                "    ED.nombre AS 'Especie Destino' " +
                "FROM Pokemon pk " +
                "         INNER JOIN Especie E ON pk.especie_id = E.id " +
                "         INNER JOIN Evolucion EV ON EV.especie_origen_id = E.id " +
                "         INNER JOIN Especie ED ON EV.especie_destino_id = ED.id " +
                "WHERE E.nombre = ?;";

        ArrayList<String> evoluciones = new ArrayList<>();

        try (Connection connection = obtenerConexion();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, especie);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String especieOrigen = resultSet.getString("Especie Origen");
                    String especieDestino = resultSet.getString("Especie Destino");
                    evoluciones.add(especieDestino);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return evoluciones;
    }
}
