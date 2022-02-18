package objetos;

import java.io.Serializable;
import java.util.Date;

import enums.TipoRegistro;

public class Peso implements Serializable{

	private static final long serialVersionUID = 1L;

	private long idPeso;
	private TipoRegistro tipoRegistro;
	private Date fechaPeso;
	private double peso;
	private Ternera ternera;

	public Peso() {
	}

	public Peso(long idPeso, TipoRegistro tipoRegistro, Date fechaPeso, double peso, Ternera ternera) {
		this.idPeso = idPeso;
		this.tipoRegistro = tipoRegistro;
		this.fechaPeso = fechaPeso;
		this.peso = peso;
		this.ternera = ternera;
	}

	public Peso(TipoRegistro tipoRegistro, Date fechaPeso, double peso, Ternera ternera) {
		this.tipoRegistro = tipoRegistro;
		this.fechaPeso = fechaPeso;
		this.peso = peso;
		this.ternera = ternera;
	}

	public long getIdPeso() {
		return idPeso;
	}
	public void setIdPeso(long idPeso) {
		this.idPeso = idPeso;
	}
	public TipoRegistro getTipoRegistro() {
		return tipoRegistro;
	}
	public void setTipoRegistro(TipoRegistro tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}
	public Date getFechaPeso() {
		return fechaPeso;
	}
	public void setFechaPeso(Date fechaPeso) {
		this.fechaPeso = fechaPeso;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	public Ternera getTernera() {
		return ternera;
	}
	public void setTernera(Ternera ternera) {
		this.ternera = ternera;
	}
}