/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package KD_Electronics;
import DAO.ProductoDAO;
import Modelo.Producto;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author RuizX
 */

public class Main {
    public static void main(String[] args) {
        ProductoDAO dao = new ProductoDAO();
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- MENÚ CRUD KD_Electronics ---");
            System.out.println("1. Listar productos");
            System.out.println("2. Insertar producto");
            System.out.println("3. Actualizar producto");
            System.out.println("4. Eliminar producto (lógico)");
            System.out.println("5. Buscar producto por código");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    List<Producto> lista = dao.listarProductos();
                    if (!lista.isEmpty()) {
                        System.out.println("\nLista de productos:");
                        lista.forEach(System.out::println);
                    } else {
                        System.out.println("\nNo hay productos registrados.");
                    }
                    break;

                case 2:
                    Producto nuevo = new Producto();
                    System.out.print("Código: ");
                    nuevo.setCodigoProducto(sc.nextLine());
                    System.out.print("Nombre: ");
                    nuevo.setNombre(sc.nextLine());
                    System.out.print("Descripción: ");
                    nuevo.setDescripcion(sc.nextLine());
                    System.out.print("Precio base: ");
                    nuevo.setPrecioBase(sc.nextDouble());
                    System.out.print("Precio venta: ");
                    nuevo.setPrecioVenta(sc.nextDouble());
                    sc.nextLine(); // Limpiar buffer
                    System.out.print("Categoría: ");
                    nuevo.setCategoria(sc.nextLine());
                    System.out.print("Cantidad: ");
                    nuevo.setCantidad(sc.nextInt());
                    nuevo.setFechaIngreso(new Date(System.currentTimeMillis()));
                    dao.insertarProducto(nuevo);
                    System.out.println("Producto insertado correctamente.");
                    break;

                case 3:
                    Producto editado = new Producto();
                    System.out.print("Código del producto a editar: ");
                    editado.setCodigoProducto(sc.nextLine());
                    System.out.print("Nuevo nombre: ");
                    editado.setNombre(sc.nextLine());
                    System.out.print("Nueva descripción: ");
                    editado.setDescripcion(sc.nextLine());
                    System.out.print("Nuevo precio base: ");
                    editado.setPrecioBase(sc.nextDouble());
                    System.out.print("Nuevo precio venta: ");
                    editado.setPrecioVenta(sc.nextDouble());
                    sc.nextLine(); // Limpiar buffer
                    System.out.print("Nueva categoría: ");
                    editado.setCategoria(sc.nextLine());
                    System.out.print("Nueva cantidad: ");
                    editado.setCantidad(sc.nextInt());
                    editado.setFechaIngreso(new Date(System.currentTimeMillis()));
                    boolean actualizado = dao.actualizarProducto(editado);
                    if (actualizado) {
                        System.out.println("Producto actualizado correctamente.");
                    } else {
                        System.out.println("No se encontró el producto.");
                    }
                    break;

                case 4:
                    System.out.print("Código del producto a eliminar: ");
                    String codigoEliminar = sc.nextLine();
                    boolean eliminado = dao.eliminarProductoLogico(codigoEliminar);
                    if (eliminado) {
                        System.out.println("Producto eliminado correctamente.");
                    } else {
                        System.out.println("No se encontró el producto.");
                    }
                    break;

                case 5:
                    System.out.print("Código del producto a buscar: ");
                    String codigoBuscar = sc.nextLine();
                    Producto encontrado = dao.buscarPorCodigo(codigoBuscar);
                    if (encontrado != null) {
                        System.out.println("Producto encontrado: " + encontrado);
                    } else {
                        System.out.println("No se encontró el producto.");
                    }
                    break;

                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 0);

        sc.close();
    }
}