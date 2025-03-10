package controlventas;
/*Se crea clase de actualizar datos.*/
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class actualizarProductos {
    public static void actualizarProductos(Connection conn, Scanner scanner) {
        try {
            // Solicitar los valores al usuario
            System.out.print("Ingrese el id del producto a actualizar: ");
            int id = scanner.nextInt();
            System.out.print("Ingrese el nuevo nombre del producto: ");
            String nombre = scanner.next();
            System.out.print("Ingrese la nueva descripcion del producto: ");
            String descripcion = scanner.next();
            System.out.print("Ingrese la nueva cantidad del producto: ");
            int cantidad = scanner.nextInt();
            System.out.print("Ingrese el nuevo precio del producto: ");
            double precio = scanner.nextDouble();

            // Crear la sentencia SQL de actualización
            String sql = "UPDATE productos SET nombre = ?, descripcion = ?, cantidad = ?, precio = ? WHERE id_producto = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, descripcion);
            ps.setInt(3, cantidad);
            ps.setDouble(4, precio);
            ps.setInt(5, id);

            // Ejecutar la sentencia SQL
            ps.executeUpdate();
            System.out.println("Producto actualizado correctamente.");
            conn.commit(); // Confirmar la transacción
        } catch (SQLException e) {
            System.out.println("Error al actualizar el producto: " + e.getMessage());
        }
    }
}