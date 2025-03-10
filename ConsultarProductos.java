
package controlventas;

/**Clase para consultar mis productos.*/
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ConsultarProductos {
    public static void consultarProductos(Connection conn, Scanner scanner) {
        System.out.println("Seleccione el criterio de consulta:");
        System.out.println("1. Consultar por ID");
        System.out.println("2. Consultar todos los productos");
        System.out.print("Seleccione una opción: ");
        int criterio = scanner.nextInt();

        try (Statement stmt = conn.createStatement()) {
            if (criterio == 1) {
                System.out.print("Ingrese el ID del producto que desea consultar: ");
                int id_producto = scanner.nextInt();
                String query = "SELECT * FROM productos WHERE id_producto = " + id_producto; // Asegúrate de que la tabla y columna existan
                
                try (ResultSet rs = stmt.executeQuery(query)) { // Usar try-with-resources para ResultSet
                    if (rs.next()) {
                        // Usar la variable ya declarada
                        int id = rs.getInt("id_producto");
                        String Nombre = rs.getString("Nombre");
                        String Descripcion = rs.getString("Descripcion");
                        int cantidad = rs.getInt("cantidad");
                        double precio = rs.getDouble("precio");
                        System.out.println("ID: " + id + ", Nombre: " + Nombre + ", Descripción: " + Descripcion + ", Cantidad: " + cantidad + ", Precio: " + precio);
                    } else {
                        System.out.println("No se encontró el producto con ID: " + id_producto);
                    }
                }
            } else if (criterio == 2) {
                String query = "SELECT * FROM productos"; // Consulta para obtener todos los productos
                
                try (ResultSet rs = stmt.executeQuery(query)) { // Usar try-with-resources para ResultSet
                    System.out.println("Lista de productos:");
                    while (rs.next()) {
                        int id_producto = rs.getInt("id_producto");
                        String Nombre = rs.getString("Nombre");
                        String Descripcion = rs.getString("Descripcion");
                        int cantidad = rs.getInt("cantidad");
                        double precio = rs.getDouble("precio");
                        
                        System.out.println("ID: " + id_producto + ", Nombre: " + Nombre + ", Descripción: " + Descripcion + ", Cantidad: " + cantidad + ", Precio: " + precio);
                    }
                }
            } else {
                System.out.println("Opción no válida.");
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar los productos: " + e.getMessage());
        }
    }
}