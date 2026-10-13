
package negocio;

import dao.UsuarioDao;
import entidad.Usuario;

public class UsuarioNegocio {

    private UsuarioDao usuarioDao;

    public UsuarioNegocio() {
        this.usuarioDao = new UsuarioDao();
    }

    public Usuario validarLogin(String nombreUsuario, String password) {

        
        if (nombreUsuario == null || nombreUsuario.trim().isEmpty()) {
            return null;
        }

        if (password == null || password.isEmpty()) {
            return null;
        }

       
        Usuario usuario = usuarioDao.obtenerUsuario(
            nombreUsuario.trim(),
            password
        );

        
        if (usuario != null) {
            return usuario;
        }

       
        return null;
    }
}
