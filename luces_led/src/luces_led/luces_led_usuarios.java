package luces_led;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class luces_led_usuarios {

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
            Logger.getLogger(luces_led_usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            cnx = DriverManager.getConnection(url, usuario, password);
            st = cnx.createStatement();

            // INSERTAR USUARIO
            String insertar = "INSERT INTO usuario (nombre_usuario, apellido_usuario, correo_usuario, telefono_usuario, contraseña) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement psInsert = cnx.prepareStatement(insertar);
            psInsert.setString(1, "Carlos");
            psInsert.setString(2, "Gómez");
            psInsert.setString(3, "carlos@email.com");
            psInsert.setString(4, "3124567890");
            psInsert.setString(5, "contrasena123");
            psInsert.executeUpdate();
            System.out.println("Usuario insertado correctamente");

            // MOSTRAR USUARIOS
            String consulta = "SELECT * FROM usuario";
            rs = st.executeQuery(consulta);
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id_usuario") +
                        " | Nombre: " + rs.getString("nombre_usuario") +
                        " | Apellido: " + rs.getString("apellido_usuario") +
                        " | Correo: " + rs.getString("correo_usuario") +
                        " | Teléfono: " + rs.getString("telefono_usuario"));
            }

            // ACTUALIZAR USUARIO (por ID)
            String actualizar = "UPDATE usuario SET telefono_usuario = ? WHERE id_usuario = ?";
            PreparedStatement psUpdate = cnx.prepareStatement(actualizar);
            psUpdate.setString(1, "3009876543"); // nuevo número
            psUpdate.setInt(2, 1); // ID del usuario a actualizar
            psUpdate.executeUpdate();
            System.out.println("Usuario actualizado");

            // ELIMINAR USUARIO (por ID)
            String eliminar = "DELETE FROM usuario WHERE id_usuario = ?";
            PreparedStatement psDelete = cnx.prepareStatement(eliminar);
            psDelete.setInt(1, 2); // ID del usuario a eliminar
            psDelete.executeUpdate();
            System.out.println("Usuario eliminado");

        } catch (SQLException ex) {
            Logger.getLogger(luces_led_usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
