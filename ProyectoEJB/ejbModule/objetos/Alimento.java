package objetos;

import java.io.Serializable;

public class Alimento implements Serializable{

	private static final long serialVersionUID = 1L;

	private long idAlimento;
	private String nombre;
	private double costoUnidad;
	private double cantidad;
	private Unidad unidad;

	public Alimento(long idAlimento, String nombre, double costoUnidad, double cantidad, Unidad unidad) {
		this.idAlimento = idAlimento;
		this.nombre = nombre;
		this.costoUnidad = costoUnidad;
		this.cantidad = cantidad;
		this.unidad = unidad;
	}

	public Alimento() {
	}

	public Alimento(long idAlimento, double costoUnidad, double cantidad) {
		this.idAlimento = idAlimento;
		this.costoUnidad = costoUnidad;
		this.cantidad = cantidad;
	}

	public long getIdAlimento() {
		return idAlimento;
	}
	public void setIdAlimento(long idAlimento) {
		this.idAlimento = idAlimento;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getCostoUnidad() {
		return costoUnidad;
	}
	public void setCostoUnidad(double costoUnidad) {
		this.costoUnidad = costoUnidad;
	}
	public double getCantidad() {
		return cantidad;
	}
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}
	public Unidad getUnidad() {
		return unidad;
	}
	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}
}