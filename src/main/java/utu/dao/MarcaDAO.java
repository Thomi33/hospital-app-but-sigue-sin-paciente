package utu.dao;

import utu.interfaces.CrudDAO;
import utu.modelo.Marca;
import utu.conexion.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MarcaDAO implements CrudDAO<Marca> {

    private final Connection conexion;
   
    public MarcaDAO(){
        conexion = Conexion.conectar();
    }
    private Marca mapearMarca(ResultSet rs) throws SQLException {
        Marca marca = new Marca();
        marca.setIdMarca(rs.getInt("id_marca"));
        marca.setNombre(rs.getString("nombre"));
        marca.setDescripcion(rs.getString("descripcion"));
        marca.setActivo(rs.getBoolean("activo"));
        return marca;
    }
    @Override
    public boolean insertar(Marca marca) {
        String sql = "INSERT INTO marca (nombre, descripcion, activo) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, marca.getNombre());
            ps.setString(2, marca.getDescripcion());
            ps.setBoolean(3, marca.isActivo());
            
            return ps.executeUpdate() > 0;
        }catch (SQLException e) {
            e.printStackTrace();
                    }
        return false;
    }
    @Override 
    public boolean actualizar(Marca marca) {
        String sql = "UPDATE marca SET nombre = ?, descripcion = ?, activo = ? WHERE id_marca = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, marca.getNombre());
            ps.setString(2, marca.getDescripcion());
            ps.setBoolean(3, marca.isActivo());
            ps.setInt(4, marca.getIdMarca());

            int filas = ps.executeUpdate();
            
            return filas > 0;
        }catch (SQLException e) {
            e.printStackTrace();
                    }
        return false;
    }
    @Override
    public boolean eliminar(int id) {
        String sql = "UPDATE marca SET activo = false WHERE id_marca = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override 
    public Marca buscarPorId(int id) {
        String sql = "SELECT * FROM marca WHERE id_marca = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapearMarca(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public List<Marca> listarTodos() {
        String sql = "SELECT * FROM marca";
        List<Marca> marcas = new ArrayList<>();
        try(PreparedStatement ps = conexion.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                marcas.add(mapearMarca(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return marcas;
    }
    public List<Marca> listarActivas() {

        List<Marca> marcas = new ArrayList<>();
        String sql = "SELECT * FROM marca WHERE activo = true ORDER BY nombre ASC";
        try(PreparedStatement ps = conexion.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                marcas.add(mapearMarca(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return marcas;
    }
    public Marca buscarPorNombre(String nombre) {
        String sql = "SELECT * FROM marca WHERE UPPER(nombre) = UPPER(?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)){
            ps.setString(1, nombre);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearMarca(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean existe(String nombre) {
        String sql = "SELECT COUNT(*) FROM marca WHERE UPPER(nombre) = UPPER(?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nombre);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
