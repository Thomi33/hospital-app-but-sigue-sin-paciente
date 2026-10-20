package utu.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/hospital";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static Connection conexion;

    /** Devuelve la conexión a la base de datos (una única para toda la aplicación). */
    public static Connection conectar() {
        try {
            if (conexion == null || conexion.isClosed()) {
                conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            }
            return conexion;
        } catch (SQLException e) {
            throw new RuntimeException("No se pudo conectar a la base de datos (" + URL + "): " + e.getMessage(), e);
        }
    }
}
