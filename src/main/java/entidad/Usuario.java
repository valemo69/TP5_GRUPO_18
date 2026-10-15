package entidad;

public class Usuario {

	private int idUsuario; 
	
	private String nombreUsuario;  

	private String contrasenia; 
	
	
	
	public Usuario()
	{
		
	}
	
	
	public Usuario(int idUsuario, String nombreUsuario, String contrasenia) {
		super(); 
		this.idUsuario=idUsuario; 
		this.nombreUsuario=nombreUsuario;  
		this.contrasenia=contrasenia;
	}


	public int getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}


	public String getNombreUsuario() {
		return nombreUsuario;
	}


	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}


	public String getContrasenia() {
		return contrasenia;
	}


	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	
	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", Usuario=" + nombreUsuario + ", Contraseña=" + contrasenia + "]";
	}
	
	
	
}
