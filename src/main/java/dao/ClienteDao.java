package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import datoslmpl.Conexion;
import entidad.Cliente;

public class ClienteDao {

	private static final String INSERTAR = "INSERT INTO clientes (dni, cuil, nombre, apellido, sexo, "
			+ "fecha_nacimiento, direccion, nacionalidad, localidad, provincia, "
			+ "correo_electronico, telefono) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String EXISTE_CORREO = "SELECT 1 FROM clientes WHERE correo_electronico = ?";
	private static final String EXISTE_DNI = "SELECT 1 FROM clientes WHERE dni = ?";
	private static final String EXISTE_CUIL = "SELECT 1 FROM clientes WHERE cuil = ?";

	
	public boolean agregar(Cliente cliente) {
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement statement = null;
		boolean guardado = false;

		try {
			cn = conexion.Open();
			
			if (cn == null) return false;

			statement = cn.prepareStatement(INSERTAR);
			statement.setString(1, cliente.getDni());
			statement.setString(2, cliente.getCuil());
			statement.setString(3, cliente.getNombre());
			statement.setString(4, cliente.getApellido());
			statement.setString(5, cliente.getSexo());
			statement.setString(6, cliente.getFecha_nacimiento());
			statement.setString(7, cliente.getDireccion());
			statement.setString(8, cliente.getNacionalidad());
			statement.setString(9, cliente.getLocalidad());
			statement.setString(10, cliente.getProvincia());
			statement.setString(11, cliente.getCorreo_electronico());
			statement.setString(12, cliente.getTelefono());

			guardado = statement.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (statement != null) {
			    try {
			    	statement.close();
			    }
			    catch (SQLException e) {
			    	e.printStackTrace();
			    }
			}
			if (cn != null) {
			    conexion.close();
			}
		}

		return guardado;
	}


	public boolean existeCorreo(String correo) {
		return existe(EXISTE_CORREO, correo);
	}

	public boolean existeDni(String dni) {
		return existe(EXISTE_DNI, dni);
	}

	
	public boolean existeCuil(String cuil) {
		return existe(EXISTE_CUIL, cuil);
	}

	
	private boolean existe(String sql, String valor) {
		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		boolean existe = false;

		try {
			cn = conexion.Open();

			statement = cn.prepareStatement(sql);
			statement.setString(1, valor);

			resultSet = statement.executeQuery();
			existe = resultSet.next();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (resultSet != null) {
			    try {
			    	resultSet.close();
			    	}
			    catch (SQLException e)
			    {
			    	e.printStackTrace();
			    }
			}
			if (statement != null) {
			    try {
			    	statement.close();
			    	}
			    catch (SQLException e)
			    {
			    	e.printStackTrace();
			    }
			}
			if (cn != null) {
			    conexion.close();
			}
		}

		return existe;
	}
}