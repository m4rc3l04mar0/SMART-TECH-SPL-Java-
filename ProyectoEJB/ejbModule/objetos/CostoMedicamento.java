package objetos;

import java.io.Serializable;
import java.util.Date;

public class CostoMedicamento implements Serializable{

	private static final long serialVersionUID = 1L;

	private Date fechaDesde;
	private Date fechaHasta;
	private double costo;
	private Medicamento medicamento;

	public CostoMedicamento(Date fechaDesde, Date fechaHasta, double costo, Medicamento medicamento) {
		this.fechaDesde = fechaDesde;
		this.fechaHasta = fechaHasta;
		this.costo = costo;
		this.medicamento = medicamento;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public Date getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	public double getCosto() {
		return costo;
	}
	public void setCosto(double costo) {
		this.costo = costo;
	}
	public Medicamento getMedicamento() {
		return medicamento;
	}
	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}
}