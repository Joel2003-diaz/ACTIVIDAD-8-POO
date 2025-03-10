package controlventas;
/**Se va programar para insertar, actualizar y eliminar
información en la base de datos, así como para recuperar información
de esta.*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Scanner;
public class ControlVentas {
    public static void main(String[] args) {
        Connection conn = null;
        Scanner scanner = new Scanner(System.in);
        try {
            // Conexión a la base de datos
            String url = "jdbc:mysql://localhost:3306/VENTAS";
            String usuario = "root"; 
            String contraseña = "123456"; 

            conn = DriverManager.getConnection(url, usuario, contraseña);
            conn.setAutoCommit(false); 

            System.out.println("Conexión exitosa a la base de datos.");
            // Menú de operaciones
            while (true) {
                System.out.println("\nMenú de opciones:");
                System.out.println("1. Actualizar Base de datos.");
                System.out.println("2. Insertar productos.");
                System.out.println("3. Eliminar Productos.");
                System.out.println("4. Consultar Productos.");
                System.out.println("0. Salir.");
                System.out.print("Seleccione una opción: ");
                int opcion = scanner.nextInt();

                switch (opcion) {
                    case 1 -> actualizarProductos.actualizarProductos(conn, scanner);
                    case 2 -> InsertarProductos.insertarProductos(conn, scanner);
                    case 3 -> eliminarProductos.eliminarProductos(conn, scanner);
                    case 4 -> ConsultarProductos.consultarProductos(conn, scanner);
                    case 0 -> {
                        // Salir del sistema
                        System.out.println("Gracias por usar nuestro sistema.");
                        return;
                    }
                    default -> System.out.println("Opción no válida.");
                }
            }           
        } catch (SQLException e) {
            // Revertir la transacción en caso de error
            if (conn != null) {
                try {
                    conn.rollback();
                    System.out.println("Transacción revertida debido a un error.");
                } catch (SQLException ex) {
                    System.err.println("Error al revertir la transacción: " + ex.getMessage());
                }}
            System.err.println("Error de SQL: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Cerrar la conexión
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("Conexión cerrada.");
                } catch (SQLException e) {
                    System.err.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
    }
}