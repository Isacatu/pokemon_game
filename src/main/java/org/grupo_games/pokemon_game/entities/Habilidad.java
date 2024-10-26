public class HabilidadDAO {
    private Connection conexion;

    // Constructor que establece la conexión
    public HabilidadDAO(Connection conexion) {
        this.conexion = conexion;
    }

    // Método para insertar una nueva habilidad
    public void insertarHabilidad(Habilidad habilidad) throws SQLException {
        String query = "INSERT INTO habilidad (nombre, tipo, poder) VALUES (?, ?, ?)";
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, habilidad.getNombre());
            statement.setString(2, habilidad.getTipo());
            statement.setInt(3, habilidad.getPoder());
            statement.executeUpdate();
        }
    }

    // Método para obtener una habilidad por su ID
    public Habilidad obtenerHabilidadPorId(int id) throws SQLException {
        String query = "SELECT * FROM habilidad WHERE id = ?";
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                String tipo = resultSet.getString("tipo");
                int poder = resultSet.getInt("poder");

                return new Habilidad(nombre, tipo, poder);
            }
        }
        return null;  // Si no se encuentra la habilidad
    }

    // Método para obtener todas las habilidades
    public List<Habilidad> obtenerTodasLasHabilidades() throws SQLException {
        List<Habilidad> habilidades = new ArrayList<>();
        String query = "SELECT * FROM habilidad";
        try (Statement statement = conexion.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                String tipo = resultSet.getString("tipo");
                int poder = resultSet.getInt("poder");

                Habilidad habilidad = new Habilidad(nombre, tipo, poder);
                habilidades.add(habilidad);
            }
        }
        return habilidades;
    }

    // Método para actualizar una habilidad existente
    public void actualizarHabilidad(Habilidad habilidad, int id) throws SQLException {
        String query = "UPDATE habilidad SET nombre = ?, tipo = ?, poder = ? WHERE id = ?";
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, habilidad.getNombre());
            statement.setString(2, habilidad.getTipo());
            statement.setInt(3, habilidad.getPoder());
            statement.setInt(4, id);
            statement.executeUpdate();
        }
    }

    // Método para eliminar una habilidad por su ID
    public void eliminarHabilidad(int id) throws SQLException {
        String query = "DELETE FROM habilidad WHERE id = ?";
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
