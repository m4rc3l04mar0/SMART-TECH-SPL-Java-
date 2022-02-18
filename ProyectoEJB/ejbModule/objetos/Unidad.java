package objetos;

import java.io.Serializable;

import enums.TipoUnidad;

public class Unidad implements Serializable{

	private static final long serialVersionUID = 1L;

	private long idUnidad;
	private TipoUnidad unidad;

	public Unidad(long idUnidad, TipoUnidad unidad) {
		this.idUnidad = idUnidad;
		this.unidad = unidad;
	}

	public long getIdUnidad() {
		return idUnidad;
	}
	public void setIdUnidad(long idUnidad) {
		this.idUnidad = idUnidad;
	}
	public TipoUnidad getUnidad() {
		return unidad;
	}
	public void setUnidad(TipoUnidad unidad) {
		this.unidad = unidad;
	}
}