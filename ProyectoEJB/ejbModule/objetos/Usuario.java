package objetos;

import java.io.Serializable;

import enums.Perfil;

public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;

	private long idUsuario;
	private String usuario;
	private String nombre;
	private String apellido;
	private Perfil perfil;
	private String contraseña;

	public Usuario(long idUsuario, String usuario, String nombre, String apellido, Perfil perfil, String contraseña) {
		this.idUsuario = idUsuario;
		this.usuario = usuario;
		this.nombre = nombre;
		this.apellido = apellido;
		this.perfil = perfil;
		this.contraseña = contraseña;
	}

	public Usuario() {
	}

	public Usuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Usuario(String contraseña) {
		this.contraseña = contraseña;
	}

	public long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Perfil getPerfil() {
		return perfil;
	}
	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
}