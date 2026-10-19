package utu.conexion;
import java.sql.*;

public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/hospital";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection conectar() {
       try {
        return DriverManager.getConnection(URL, USER, PASSWORD);
       } catch (SQLException e) {
        System.out.println("Error al conectar a la base de datos: " );
        System.out.println(e.getMessage());
           
        return null;
       }
    }
}
