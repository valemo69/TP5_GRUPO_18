<%@page import="entidad.Usuario"%>
<%@page import="entidad.Cliente"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Listado de Clientes</title>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.min.css">
<style>
    body { font-family: Arial, sans-serif; background-color: #f4f4f9; margin: 0; display: flex; min-height: 100vh; }
    .sidebar { width: 250px; background-color: #2b6cb0; color: white; display: flex; flex-direction: column; position: fixed; height: 100%; }
    .sidebar-title { padding: 25px 20px; font-size: 20px; font-weight: bold; text-align: center; border-bottom: 1px solid rgba(255, 255, 255, 0.15); }
    .sidebar a { padding: 15px 20px; color: white; text-decoration: none; display: block; font-size: 15px; }
    .sidebar a:hover { background-color: #2c5282; }
    .main-content { margin-left: 250px; flex-grow: 1; padding: 40px; box-sizing: border-box;}
    .user-info { color: #555; margin-bottom: 25px; font-size: 14px; }
    .table-container { background-color: white; padding: 20px; border-radius: 8px; box-shadow: 0 4px 8px rgba(0,0,0,0.1); }
    table.dataTable { width: 100% !important; font-size: 14px; }
</style>
</head>
<body>
    <%
    if (session.getAttribute("usuarioLogueado") == null) {
        response.sendRedirect(request.getContextPath() + "/Login.jsp");
        return; 
    }    
    String NombreUsuario = "Invitado";
    Usuario usuarioLogueado = (Usuario) session.getAttribute("usuarioLogueado"); 
    if (usuarioLogueado != null) {
        NombreUsuario = usuarioLogueado.getNombreUsuario();
    }
    
    ArrayList<Cliente> lista = (ArrayList<Cliente>) request.getAttribute("listaClientes");
    if (lista == null) {
        lista = new ArrayList<>();
    }
    %>

    <div class="sidebar">
        <div class="sidebar-title">Menú</div>
        <a href="AltaCliente.jsp">Alta de Cliente</a>
        <a href="ServletListadoClientes">Listado de Clientes</a>
    </div>

    <div class="main-content">
        <h2>Listado de Clientes</h2>
        <div class="user-info">
            Usuario: <strong><%= NombreUsuario %></strong>
        </div>

        <div class="table-container">
            <table id="tablaClientes" class="display">
                <thead>
                    <tr>
                        <th>DNI</th>
                        <th>CUIL</th>
                        <th>Nombre y Apellido</th>
                        <th>Sexo</th>
                        <th>Fecha de Nacimiento</th>
                        <th>Dirección</th>
                        <th>Nacionalidad</th>
                        <th>Localidad</th>
                        <th>Provincia</th>
                    </tr>
                </thead>
                <tbody>
                    <% for(Cliente c : lista) { %>
                    <tr>
                        <td><%= c.getDni() %></td>
                        <td><%= c.getCuil() %></td>
                        <td><%= c.getNombre() + " " + c.getApellido() %></td>
                        <td><%= c.getSexo() %></td>
                        <td><%= c.getFecha_nacimiento() %></td>
                        <td><%= c.getDireccion() %></td>
                        <td><%= c.getNacionalidad() %></td>
                        <td><%= c.getLocalidad() %></td>
                        <td><%= c.getProvincia() %></td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
    <script>
        $(document).ready(function() {
            $('#tablaClientes').DataTable({
                "language": {
                    "url": "//cdn.datatables.net/plug-ins/1.13.6/i18n/es-ES.json"
                },
                "pageLength": 5,
                "lengthMenu": [5, 10, 15, 20]
            });
        });
    </script>
</body>
</html>