/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author RuizX
 */


public class ConexionBD {

    // Datos de conexión
    private static final String URL = "jdbc:mysql://localhost:3306/kd_electronics";
    private static final String USER = "root";
    private static final String PASSWORD = "MyNewPass1"; // Contraseña configurada

    // Método para obtener la conexión
    public static Connection getConnection() {
        try {
            // Cargar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecer conexión
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Conexión exitosa a la base de datos.");
            return connection;

        } catch (ClassNotFoundException e) {
            System.err.println("❌ Driver de MySQL no encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("❌ Error al conectar a la base de datos: " + e.getMessage());
        }
        return null;
    }

    // Método principal para probar la conexión
    public static void main(String[] args) {
        Connection conn = getConnection();
        if (conn != null) {
            try {
                System.out.println("Conexión establecida correctamente.");
                conn.close();
            } catch (SQLException e) {
                System.err.println("❌ Error al cerrar la conexión: " + e.getMessage());
            }
        } else {
            System.err.println("No se pudo establecer la conexión.");
        }
    }
}