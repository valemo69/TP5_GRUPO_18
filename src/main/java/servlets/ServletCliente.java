package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import entidad.Cliente;
import negocio.ClienteNegocio;


@WebServlet("/ServletCliente")
public class ServletCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public ServletCliente() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/AltaCliente.jsp");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        
        String dni = request.getParameter("txtDni");
        String cuil = request.getParameter("txtCuil");
        String nombre = request.getParameter("txtNombre");
        String apellido = request.getParameter("txtApellido");
        String sexo = request.getParameter("Sexo");
        String nacionalidad = request.getParameter("txtNacionalidad");
        String fechaNac = request.getParameter("txtFechaNac");
        String direccion = request.getParameter("txtDireccion");
        String localidad = request.getParameter("txtLocalidad");
        String provincia = request.getParameter("txtProvincia");
        String correo = request.getParameter("txtCorreo");
        String telefono = request.getParameter("txtTelefono");
              
        Cliente nuevoCliente = new Cliente(0, dni, cuil, nombre, apellido, sexo, fechaNac, direccion, nacionalidad, localidad, provincia, correo, telefono);
        
        ClienteNegocio negocio = new ClienteNegocio();
        String mensajeValidacion = negocio.registrarCliente(nuevoCliente);
        
        if (mensajeValidacion == null) {
            request.setAttribute("mensajeExito", "¡El cliente " + nombre + " " + apellido + " se registro correctamente!");
        } else {
            request.setAttribute("mensajeError", mensajeValidacion);
            
            request.setAttribute("clienteConError", nuevoCliente);
        }
        
        request.getRequestDispatcher("/AltaCliente.jsp").forward(request, response);
	}

}
