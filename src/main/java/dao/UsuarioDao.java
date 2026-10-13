package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import datoslmpl.Conexion;
import entidad.Usuario;

public class UsuarioDao {

    private static final String LOGIN = "SELECT id, nombre_usuario, contrasenia "
    		+ "FROM usuarios WHERE nombre_usuario = ? "
    		+ "AND contrasenia = ?";

    public Usuario obtenerUsuario(String nombreUsuario, String password) {
        Conexion conexion = new Conexion(); // Instanciamos 
        Connection cn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Usuario usuario = null;

        try {
            // Abrimos la conexión 
            cn = conexion.Open();
            
            statement = cn.prepareStatement(LOGIN);
            statement.setString(1, nombreUsuario);
            statement.setString(2, password);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                usuario = new Usuario();
                usuario.setIdUsuario(resultSet.getInt("id"));
                usuario.setUsuario(resultSet.getString("nombre_usuario"));
                usuario.setContraseña(resultSet.getString("contrasenia"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerramos recursos y conexión 
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                conexion.close(); 
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return usuario;
    }
}