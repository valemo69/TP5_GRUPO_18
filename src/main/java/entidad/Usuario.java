package entidad;

public class Usuario {

	private int idUsuario; 
	
	private String Usuario;  

	private String Contraseña; 
	
	
	
	public Usuario()
	{
		
	}
	
	
	public Usuario(int idUsuario, String Usuario, String Contraseña) {
		super(); 
		this.idUsuario=idUsuario; 
		this.Usuario=Usuario;  
		this.Contraseña=Contraseña;
	}


	public int getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}


	public String getUsuario() {
		return Usuario;
	}


	public void setUsuario(String usuario) {
		Usuario = usuario;
	}


	public String getContraseña() {
		return Contraseña;
	}


	public void setContraseña(String contraseña) {
		Contraseña = contraseña;
	}
	
	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", Usuario=" + Usuario + ", Contraseña=" + Contraseña + "]";
	}
	
	
	
}
