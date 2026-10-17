package servlets;

import java.io.IOException;
// IMPORTANTE: el proyecto corre en Tomcat 11 (Jakarta EE 11), por lo que las
// clases del paquete servlet son "jakarta.servlet" y NO "javax.servlet".
// Con "javax.servlet" la clase no compila ni se despliega y el servidor
// responde 404 al invocar el servlet.
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import negocio.UsuarioNegocio;
import entidad.Usuario;

@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletLogin() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // 1. Capturamos usando los name exactos del Login.jsp
        String usuarioIngresado = request.getParameter("usuario");
        String passwordIngresada = request.getParameter("contrasenia");
        
        // 2. Instanciamos la capa de negocio
        UsuarioNegocio negocio = new UsuarioNegocio();
        
        // 3. Validamos: nos devuelve un objeto Usuario (o null si falló)
        Usuario usuarioLogueado = negocio.validarLogin(usuarioIngresado, passwordIngresada);
        
        if (usuarioLogueado != null) {
            // 4. Si el usuario existe, creamos la Sesión y guardamos el objeto o su nombre
            HttpSession session = request.getSession();
            session.setAttribute("usuarioLogueado", usuarioLogueado);
            
            // 5. Redirigimos a la vista principal.
            // El redirect debe incluir el context path: "/Login.jsp" solo
            // apuntaria a la raiz del servidor y devolveria 404.
            response.sendRedirect(request.getContextPath() + "/AltaCliente.jsp");
        } else {
            // 6. Si falla, mandamos el mensaje de error de vuelta al Login.jsp
            request.setAttribute("mensajeError", "Usuario o contraseña incorrectos.");
            request.getRequestDispatcher("/Login.jsp").forward(request, response);
        }
    }
}