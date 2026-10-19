package utu.dao;

import utu.modelo.Perfil;
import utu.modelo.Usuario;
import utu.util.PasswordUtil;
import utu.conexion.Conexion;
import java.sql.*;

public class LoginDAO {

    private final Connection conexion;

    public LoginDAO(){
        conexion = Conexion.conectar();
    }

     public Usuario iniciarSesion(String usuario, String contrasena) {

        String sql = """
                SELECT
                    u.id_usuario,
                    u.nombre,
                    u.apellido,
                    u.cedula,
                    u.telefono,
                    u.correo,
                    u.usuario,
                    u.contrasena,
                    u.activo,
                    p.id_perfil,
                    p.nombre AS nombre_perfil,
                    p.descripcion,
                    p.activo AS activo_perfil
                FROM usuario u
                INNER JOIN perfil p
                    ON u.id_perfil = p.id_perfil
                WHERE u.usuario = ?
                AND u.activo = true
                """;

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, usuario);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                // Obtener el hash almacenado
                String hashGuardado = rs.getString("contrasena");

                // Verificar la contraseña ingresada
                if (!PasswordUtil.verificar(contrasena, hashGuardado)) {
                    return null;
                }

                Perfil perfil = new Perfil();
                perfil.setIdPerfil(rs.getInt("id_perfil"));
                perfil.setNombre(rs.getString("nombre_perfil"));
                perfil.setDescripcion(rs.getString("descripcion"));
                perfil.setActivo(rs.getBoolean("activo_perfil"));

                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("id_usuario"));
                u.setNombre(rs.getString("nombre"));
                u.setApellido(rs.getString("apellido"));
                u.setCedula(rs.getString("cedula"));
                u.setTelefono(rs.getString("telefono"));
                u.setCorreo(rs.getString("correo"));
                u.setUsuario(rs.getString("usuario"));
                u.setActivo(rs.getBoolean("activo"));
                u.setPerfil(perfil);

                return u;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
