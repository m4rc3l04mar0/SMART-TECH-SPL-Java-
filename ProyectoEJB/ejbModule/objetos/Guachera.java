package objetos;

import java.io.Serializable;

public class Guachera implements Serializable{

	private static final long serialVersionUID = 1L;

	private long idGuachera;
	private String nombre;
	private long idUsuario;

	public Guachera(long idGuachera, String nombre, long idUsuario) {
		this.idGuachera = idGuachera;
		this.nombre = nombre;
		this.idUsuario = idUsuario;
	}

	public long getIdGuachera() {
		return idGuachera;
	}
	public void setIdGuachera(long idGuachera) {
		this.idGuachera = idGuachera;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
}