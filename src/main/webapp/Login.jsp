<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Iniciar sesion</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .login-card {
            background-color: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            width: 100%;
            max-width: 350px;
        }
        .login-card h2 {
            margin-top: 0;
            font-size: 20px;
            color: black;
        }
        .form-group {
            margin-bottom: 20px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
            font-size: 14px;
            color: #555;
        }
        .form-group input {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .btn-ingresar {
            width: 100%;
            padding: 10px;
            background-color: #2b6cb0;
            color: white;
            border: none;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
        }
        .btn-ingresar:hover {
            background-color: #2c5282;
        }
        .error-message {
            color: #e53e3e;
            font-size: 14px;
            margin-bottom: 15px;
            text-align: center;
        }
    </style>
</head>
<body>

    <div class="login-card">
        <h2>Iniciar sesión</h2>
        
<!--         Bloque para mostrar errores enviados desde ServletLogin -->
        <% 
            String error = (String) request.getAttribute("mensajeError");
            if (error != null) { 
        %>
            <div class="error-message">
                <%= error %>
            </div>
        <% } %>

        <form action="ServletLogin" method="POST">
            <div class="form-group">
                <label for="usuario">Usuario</label>
                <input type="text" id="usuario" name="usuario" required placeholder="Ingrese su usuario">
            </div>
            
            <div class="form-group">
                <label for="contrasenia">Contraseña</label>
                <input type="password" id="contrasenia" name="contrasenia" required placeholder="Ingrese su contraseña">
            </div>
            
            <button type="submit" class="btn-ingresar">Ingresar</button>
        </form>
    </div>

</body>
</html>