package negocio;

import dao.ClienteDao;
import entidad.Cliente;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class ClienteNegocio {

    private ClienteDao clienteDao;

    public ClienteNegocio() {
        this.clienteDao = new ClienteDao();
    }

    public String registrarCliente(Cliente cliente) {
        
        // Validacion de campos vacios
        if (esNuloOVacio(cliente.getDni()) || esNuloOVacio(cliente.getCuil()) ||
            esNuloOVacio(cliente.getNombre()) || esNuloOVacio(cliente.getApellido()) ||
            esNuloOVacio(cliente.getSexo()) || esNuloOVacio(cliente.getFecha_nacimiento()) ||
            esNuloOVacio(cliente.getDireccion()) || esNuloOVacio(cliente.getNacionalidad()) ||
            esNuloOVacio(cliente.getLocalidad()) || esNuloOVacio(cliente.getProvincia()) ||
            esNuloOVacio(cliente.getCorreo_electronico()) || esNuloOVacio(cliente.getTelefono())) {
            return "Debe ingresar todos los campos para poder continuar.";
        }

        // Validaciones de formato y longitud
        if (!cliente.getDni().matches("\\d{6,8}")) {
            return "El DNI debe ser numerico y tener entre 6 y 8 digitos.";
        }
        
        if (!cliente.getCuil().matches("\\d{11}")) {
            return "El CUIL debe ser numerico y tener exactamente 11 digitos.";
        }
        
        if (!cliente.getTelefono().matches("\\d{10}")) {
            return "El telefono debe ser numerico y tener exactamente 10 digitos.";
        }

        // Validacion solo letras
        String regexLetras = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$";
        if (!cliente.getNombre().matches(regexLetras)) return "El nombre solo debe contener letras.";
        if (!cliente.getApellido().matches(regexLetras)) return "El apellido solo debe contener letras.";
        if (!cliente.getNacionalidad().matches(regexLetras)) return "La nacionalidad solo debe contener letras.";
        if (!cliente.getLocalidad().matches(regexLetras)) return "La localidad solo debe contener letras.";
        if (!cliente.getProvincia().matches(regexLetras)) return "La provincia solo debe contener letras.";

        // Validacion Sexo
        String sexo = cliente.getSexo().toUpperCase();
        if (!sexo.equals("M") && !sexo.equals("F") && !sexo.equals("O")) {
            return "El sexo seleccionado no es valido.";
        }

        // Validacion correo electrónico
        String regexCorreo = "^[A-Za-z0-9+_.-]+@(.+)$";
        if (!Pattern.matches(regexCorreo, cliente.getCorreo_electronico())) {
            return "El formato del correo electronico no es valido.";
        }

        // Validacion fecha de nacimiento
        try {
            LocalDate fechaNac = LocalDate.parse(cliente.getFecha_nacimiento());
            if (fechaNac.isAfter(LocalDate.now())) {
                return "La fecha de nacimiento no puede ser futura.";
            }
        } catch (DateTimeParseException e) {
            return "El formato de la fecha de nacimiento es incorrecto.";
        }

        // Validaciones contra la base de datos
        if (clienteDao.existeDni(cliente.getDni())) {
            return "El DNI ingresado ya se encuentra registrado.";
        }
        if (clienteDao.existeCuil(cliente.getCuil())) {
            return "El CUIL ingresado ya se encuentra registrado.";
        }
        if (clienteDao.existeCorreo(cliente.getCorreo_electronico())) {
            return "El correo electronico ya se encuentra registrado en el sistema.";
        }

        boolean guardado = clienteDao.agregar(cliente);
        if (!guardado) {
            return "Ocurrio un error interno al intentar guardar el cliente.";
        }

        return null; 
    }

    private boolean esNuloOVacio(String str) {
        return str == null || str.trim().isEmpty();
    }
}