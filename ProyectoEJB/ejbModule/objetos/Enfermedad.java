package objetos;

import java.io.Serializable;

import enums.NombreEnfermedad;

public class Enfermedad implements Serializable{

	private static final long serialVersionUID = 1L;

	private long idEnfermedad;
	private NombreEnfermedad nombre;
	private int gravedad;

	public Enfermedad(long idEnfermedad, NombreEnfermedad nombre, int gravedad) {
		this.idEnfermedad = idEnfermedad;
		this.nombre = nombre;
		this.gravedad = gravedad;
	}

	public long getIdEnfermedad() {
		return idEnfermedad;
	}
	public void setIdEnfermedad(long idEnfermedad) {
		this.idEnfermedad = idEnfermedad;
	}
	public NombreEnfermedad getNombre() {
		return nombre;
	}
	public void setNombre(NombreEnfermedad nombre) {
		this.nombre = nombre;
	}
	public int getGravedad() {
		return gravedad;
	}
	public void setGravedad(int gravedad) {
		this.gravedad = gravedad;
	}
}