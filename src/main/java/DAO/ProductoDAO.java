/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import Modelo.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

 /**
 *
 * @author RuizX
 */


public class ProductoDAO {
    private final String url = "jdbc:mysql://localhost:3306/kd_inventory";
    private final String user = "root";
    private final String password = "MyNewPass1"; // Cambia esto si usas otra contraseña

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public void insertarProducto(Producto p) {
        String sql = "INSERT INTO productos (codigo_producto, nombre, descripcion, precio_base, precio_venta, categoria, cantidad, fecha_ingreso) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getCodigoProducto());
            stmt.setString(2, p.getNombre());
            stmt.setString(3, p.getDescripcion());
            stmt.setDouble(4, p.getPrecioBase());
            stmt.setDouble(5, p.getPrecioVenta());
            stmt.setString(6, p.getCategoria());
            stmt.setInt(7, p.getCantidad());
            stmt.setDate(8, p.getFechaIngreso());

            stmt.executeUpdate();
            System.out.println("✅ Producto insertado correctamente.");

        } catch (SQLException e) {
            System.err.println("❌ Error al insertar producto: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Producto> listarProductos() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM productos";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Producto p = new Producto(
                        rs.getString("codigo_producto"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getDouble("precio_base"),
                        rs.getDouble("precio_venta"),
                        rs.getString("categoria"),
                        rs.getInt("cantidad"),
                        rs.getDate("fecha_ingreso")
                );
                lista.add(p);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al listar productos: " + e.getMessage());
            e.printStackTrace();
        }

        return lista;
    }

    public boolean actualizarProducto(Producto p) {
        String sql = "UPDATE productos SET nombre=?, descripcion=?, precio_base=?, precio_venta=?, categoria=?, cantidad=?, fecha_ingreso=? WHERE codigo_producto=?";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getNombre());
            stmt.setString(2, p.getDescripcion());
            stmt.setDouble(3, p.getPrecioBase());
            stmt.setDouble(4, p.getPrecioVenta());
            stmt.setString(5, p.getCategoria());
            stmt.setInt(6, p.getCantidad());
            stmt.setDate(7, p.getFechaIngreso());
            stmt.setString(8, p.getCodigoProducto());

            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("✅ Producto actualizado correctamente.");
                return true;
            } else {
                System.out.println("⚠️ No se encontró el producto con ese código.");
                return false;
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar producto: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarProductoLogico(String codigo) {
        // Si no tienes 'estado', puedes usar eliminación física
        String sql = "DELETE FROM productos WHERE codigo_producto = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, codigo);

            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("🗑️ Producto eliminado físicamente.");
                return true;
            } else {
                System.out.println("⚠️ No se encontró el producto con ese código.");
                return false;
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar producto: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public Producto buscarPorCodigo(String codigo) {
        String sql = "SELECT * FROM productos WHERE codigo_producto = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, codigo);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Producto(
                            rs.getString("codigo_producto"),
                            rs.getString("nombre"),
                            rs.getString("descripcion"),
                            rs.getDouble("precio_base"),
                            rs.getDouble("precio_venta"),
                            rs.getString("categoria"),
                            rs.getInt("cantidad"),
                            rs.getDate("fecha_ingreso")
                    );
                }
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al buscar producto: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }
}