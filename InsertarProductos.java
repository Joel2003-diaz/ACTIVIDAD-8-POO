package controlventas;
/**Se crea clase para insertar productos.*/
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
public class InsertarProductos {
    public static void insertarProductos(Connection conn, Scanner scanner) {
        try {
            // Solicitar los valores al usuario
            System.out.print("Ingrese el nombre del producto: ");
            String nombre = scanner.next();
            System.out.print("Ingrese la descripcion del producto: ");
            String descripcion = scanner.next();
            int cantidad = 0;
            boolean cantidadValida = false;
            while (!cantidadValida) {
                try {
                    System.out.print("Ingrese la cantidad del producto: ");
                    cantidad = scanner.nextInt();
                    cantidadValida = true; // Si se lee correctamente, salimos del bucle
                } catch (InputMismatchException e) {
                    System.out.println("Por favor, ingrese un número válido para la cantidad.");
                    scanner.next(); // Limpiar el buffer
                }}
            double precio = 0.0;
            boolean precioValido = false;
            while (!precioValido) {
                try {
                    System.out.print("Ingrese el precio del producto: ");
                    precio = scanner.nextDouble();
                    precioValido = true; // Si se lee correctamente, salimos del bucle
                } catch (InputMismatchException e) {
                    System.out.println("Por favor, ingrese un número válido para el precio.");
                    scanner.next(); // Limpiar el buffer
                }}
            // Crear la sentencia SQL de inserción
            String sql = "INSERT INTO productos (nombre, descripcion, cantidad, precio) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, descripcion);
            ps.setInt(3, cantidad);
            ps.setDouble(4, precio);
            // Ejecutar la sentencia SQL
            ps.executeUpdate();
            System.out.println("Producto insertado correctamente.");
            conn.commit(); // Confirmar la transacción
        } catch (SQLException e) {
            System.out.println("Error al insertar el producto: " + e.getMessage());
        }}
}
