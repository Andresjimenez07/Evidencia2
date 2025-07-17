package luces_led;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class luces_led_1 {

    public static void main(String[] args) {

        String usuario = "root";
        String password = "admin";
        String url = "jdbc:mysql://localhost:3306/luces_led";
        Connection cnx;
        Statement st;
        ResultSet rs;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.getLogger(luces_led.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

        try {
            cnx = DriverManager.getConnection(url, usuario, password);
            st = cnx.createStatement();
            
            // INSERTAR PRODUCTO
String insertar = "INSERT INTO producto (nombre_producto, descripcion, precio) VALUES (?, ?, ?)";
PreparedStatement psInsert = cnx.prepareStatement(insertar);
psInsert.setString(1, "Kit LED Azul");
psInsert.setString(2, "Luces LED azules para interiores");
psInsert.setDouble(3, 28000);
psInsert.executeUpdate();
System.out.println("Producto insertado correctamente");

// MOSTRAR PRODUCTOS
String consulta = "SELECT * FROM producto";
rs = st.executeQuery(consulta);
while (rs.next()) {
    System.out.println("ID: " + rs.getInt("id_producto") +
        " | Nombre: " + rs.getString("nombre_producto") +
        " | Precio: " + rs.getDouble("precio"));
}

// ACTUALIZAR PRODUCTO (por ID)
String actualizar = "UPDATE producto SET precio = ? WHERE id_producto = ?";
PreparedStatement psUpdate = cnx.prepareStatement(actualizar);
psUpdate.setDouble(1, 29999);
psUpdate.setInt(2, 1); // ID del producto a actualizar
psUpdate.executeUpdate();
System.out.println("Producto actualizado");

// ELIMINAR PRODUCTO (por ID)
String eliminar = "DELETE FROM producto WHERE id_producto = ?";
PreparedStatement psDelete = cnx.prepareStatement(eliminar);
psDelete.setInt(1, 2); // ID del producto a eliminar
psDelete.executeUpdate();
System.out.println("Producto eliminado");

            rs = st.executeQuery("SELECT * FROM usuario");

            if (rs.next()) {
                do {
                    System.out.println(rs.getInt("id_usuario") + ":" + rs.getString("nombre_usuario"));
                } while (rs.next());
            } else {
                System.out.println("No hay datos en la tabla.");
            }

        } catch (SQLException ex) {
            System.getLogger(luces_led.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
}
