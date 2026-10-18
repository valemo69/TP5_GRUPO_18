package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import negocio.ClienteNegocio;

import java.io.IOException;
import java.util.ArrayList;

import entidad.Cliente;


@WebServlet("/ServletListadoClientes")
public class ServletListadoClientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ServletListadoClientes() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ClienteNegocio negocio = new ClienteNegocio();
        ArrayList<Cliente> listaClientes = negocio.obtenerTodos();
        
        request.setAttribute("listaClientes", listaClientes);
        request.getRequestDispatcher("/ListadoClientes.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
