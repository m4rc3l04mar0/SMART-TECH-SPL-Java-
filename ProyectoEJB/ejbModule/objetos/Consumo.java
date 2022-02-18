package objetos;

import java.io.Serializable;
import java.util.Date;

public class Consumo implements Serializable{

	private static final long serialVersionUID = 1L;

	private long idConsumo;
	private Date fechaConsumo;
	private double cantidad;
	private Alimento alimento;
	private Ternera ternera;

	public Consumo(long idConsumo, Date fechaConsumo, double cantidad, Alimento alimento, Ternera ternera) {
		this.idConsumo = idConsumo;
		this.fechaConsumo = fechaConsumo;
		this.cantidad = cantidad;
		this.alimento = alimento;
		this.ternera = ternera;
	}

	public Consumo(Date fechaConsumo, double cantidad, Alimento alimento, Ternera ternera) {
		this.fechaConsumo = fechaConsumo;
		this.cantidad = cantidad;
		this.alimento = alimento;
		this.ternera = ternera;
	}

	public long getIdConsumo() {
		return idConsumo;
	}
	public void setIdConsumo(long idConsumo) {
		this.idConsumo = idConsumo;
	}
	public Date getFechaConsumo() {
		return fechaConsumo;
	}
	public void setFechaConsumo(Date fechaConsumo) {
		this.fechaConsumo = fechaConsumo;
	}
	public double getCantidad() {
		return cantidad;
	}
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}
	public Alimento getAlimento() {
		return alimento;
	}
	public void setAlimento(Alimento alimento) {
		this.alimento = alimento;
	}
	public Ternera getTernera() {
		return ternera;
	}
	public void setTernera(Ternera ternera) {
		this.ternera = ternera;
	}
}