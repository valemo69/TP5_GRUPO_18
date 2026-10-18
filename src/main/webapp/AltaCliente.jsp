<%@page import="entidad.*"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Alta Cliente</title>
</head>
<style>
  <style>
     {
        box-sizing: border-box;
    }
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f9;
        margin: 0;
        display: flex;
        min-height: 100vh;
    }
    
    /* Panel lateral */
    .sidebar {
        width: 250px;
        background-color: #2b6cb0; 
        color: white;
        display: flex;
        flex-direction: column;
        position: fixed;
        height: 100%;
        top: 0;
        left: 0;
    }
    .sidebar-title {
        padding: 25px 20px;
        font-size: 20px;
        font-weight: bold;
        text-align: center;
        border-bottom: 1px solid rgba(255, 255, 255, 0.15);
    }
    .sidebar a {
        padding: 15px 20px;
        color: white;
        text-decoration: none;
        display: block;
        font-size: 15px;
        transition: background 0.2s;
    }
    .sidebar a:hover, .sidebar a.active {
        background-color: #2c5282;
    }

    /* Área principal */
    .main-content {
        margin-left: 250px;
        flex-grow: 1;
        padding: 40px;
    }
    .main-content h2 {
        margin-top: 0;
        font-size: 24px;
        color: #000;
        margin-bottom: 5px;
    }
    .user-info {
        color: #555;
        margin-bottom: 25px;
        font-size: 14px;
    }

    /* Tarjeta del formulario */
    .card-form {
        background-color: white;
        padding: 30px;
        border-radius: 8px;
        box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        max-width: 900px;
    }


    .form-grid {
        display: grid;
        grid-template-columns: 1fr 1fr;
        gap: 20px;
    }

    .form-group {
        margin-bottom: 15px;
    }
    .form-group label {
        display: block;
        margin-bottom: 5px;
        font-size: 14px;
        color: #333;
    }
    .form-group input, .form-group select {
        width: 100%;
        max-width: 350px;
        padding: 10px;
        border: 1px solid #ccc;
        border-radius: 4px;
        font-size: 14px;
        font-family: Arial, sans-serif;
    }
    .form-group input:focus, .form-group select:focus {
        outline: none;
        border-color: #2b6cb0;
    }
    .form-text {
        font-size: 12px;
        color: #777;
        margin-top: 4px;
    }

   
    .btn-guardar {
        background-color: #2b6cb0;
        color: white;
        border: none;
        padding: 12px 25px;
        font-size: 15px;
        border-radius: 4px;
        cursor: pointer;
        margin-top: 10px;
        transition: background-color 0.2s;
    }
    .btn-guardar:hover {
        background-color: #2c5282;
    }
</style>

</head>
<body>

    <%
    if (session.getAttribute("usuarioLogueado") == null) {
        
        response.sendRedirect(request.getContextPath() + "/Login.jsp");
        return; 
    }    
    
    
   
        String NombreUsuario = "Invitado";
        if (session.getAttribute("usuarioLogueado") != null) {
           
        	Usuario usuarioLogueado =(Usuario) session.getAttribute("usuarioLogueado"); 
            NombreUsuario=usuarioLogueado.getNombreUsuario();
        }
    
    %>

    <!-- Menú Lateral -->
    <div class="sidebar">
        <div class="sidebar-title">Menú</div>
        <a href="AltaCliente.jsp">Alta de Cliente</a>
      
    </div>

    <!-- Contenido -->
    <div class="main-content">
        <h2>Formulario de Cliente</h2>
        <div class="user-info">
            Usuario: <strong><%= NombreUsuario %></strong>
        </div>

		<% 
            String msjExito = (String) request.getAttribute("mensajeExito");
            String msjError = (String) request.getAttribute("mensajeError");
            
            if (msjExito != null) { 
        %>
            <div style="background-color: #c6f6d5; color: #2f855a; padding: 10px; margin-bottom: 15px; border-radius: 4px;">
                <strong>Exito:</strong> <%= msjExito %>
            </div>
        <% } else if (msjError != null) { %>
            <div style="background-color: #fed7d7; color: #c53030; padding: 10px; margin-bottom: 15px; border-radius: 4px;">
                <strong>Error:</strong> <%= msjError %>
            </div>
        <% } %>

        <div class="form-card">
            <form action="ServletCliente" method="POST">
                <div class="form-grid">
                    
                    <div class="form-group">
                        <label>DNI</label>
                        <input type="text" name="txtDni">
                        <div class="form-text">Solo números. 6 a 8 dígitos (ej: 12345678).</div>
                    </div>
                    
                    <div class="form-group">
                        <label>CUIL</label>
                        <input type="text" name="txtCuil">
                        <div class="form-text">Formato: 11 dígitos (ej: 20333444555).</div>
                    </div>

                    <div class="form-group">
                        <label>Nombre</label>
                        <input type="text" name="txtNombre" required>
                    </div>
                    
                    <div class="form-group">
                        <label>Apellido</label>
                        <input type="text" name="txtApellido" required>
                    </div>

                    <div class="form-group">
                        <label>Sexo</label>
                        <select name="Sexo" required>
                            <option value="" disabled selected>-- Seleccione --</option>
                            <option value="M">Masculino</option>
                            <option value="F">Femenino</option>
                            <option value="O">Otro</option>
                        </select>
                    </div>
                    
                    <div class="form-group">
                        <label>Nacionalidad</label>
                        <input type="text" name="txtNacionalidad"required>
                    </div>

                    <div class="form-group">
                        <label>Fecha de nacimiento</label>
                        <input type="date" name="txtFechaNac" required>
                    </div>
                    
                    <div class="form-group">
                        <label>Dirección</label>
                        <input type="text" name="txtDireccion"required>
                    </div>

                    <div class="form-group">
                        <label>Localidad</label>
                        <input type="text" name="txtLocalidad" required>
                    </div>
                    
                    <div class="form-group">
                        <label>Provincia</label>
                        <input type="text" name="txtProvincia" required>
                    </div>

                    <div class="form-group">
                        <label>Correo electrónico</label>
                        <input type="email" name="txtCorreo" placeholder="ej: nombre@dominio.com" required>
                    </div>
                    
                    <div class="form-group">
                        <label>Teléfono</label>
                        <input type="text" name="txtTelefono">
                    </div>

                </div>

                <button type="submit" class="btn-guardar">Guardar cliente</button>
            </form>
        </div>
    </div>

</body>
</html>