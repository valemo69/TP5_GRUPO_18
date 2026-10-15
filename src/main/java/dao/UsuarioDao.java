package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import datoslmpl.Conexion;
import entidad.Usuario;

public class UsuarioDao {

	private static final String LOGIN = "SELECT id_usuario, usuario, contrasena "
    		+ "FROM usuarios WHERE usuario = ? "
    		+ "AND contrasena = ?";

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
                usuario.setIdUsuario(resultSet.getInt("id_usuario"));
                usuario.setNombreUsuario(resultSet.getString("usuario"));
                usuario.setContrasenia(resultSet.getString("contrasena"));
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