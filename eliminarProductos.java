package controlventas;
/**Clase de elimacion*/
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class eliminarProductos {
    public static void eliminarProductos(Connection conn, Scanner scanner) {
        try {
            // Solicitar los valores al usuario
            System.out.print("Ingrese el id del producto a eliminar: ");
            int id = scanner.nextInt();

            // Crear la sentencia SQL de eliminación
            String sql = "DELETE FROM productos WHERE id_producto = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            // Ejecutar la sentencia SQL
            ps.executeUpdate();
            System.out.println("Producto eliminado correctamente.");
            conn.commit(); // Confirmar la transacción
        } catch (SQLException e) {
            System.out.println("Error al eliminar el producto: " + e.getMessage());
        }
    }
}