package objetos;

import java.io.Serializable;
import java.util.Date;

public class DosisSuministrada implements Serializable{

	private static final long serialVersionUID = 1L;

	private long idDosis;
	private Date fecha;
	private double cantidad;
	private Medicamento medicamento;
	private Ternera ternera;

	public DosisSuministrada(long idDosis, Date fecha, double cantidad, Medicamento medicamento, Ternera ternera) {
		this.idDosis = idDosis;
		this.fecha = fecha;
		this.cantidad = cantidad;
		this.medicamento = medicamento;
		this.ternera = ternera;
	}

	public long getIdDosis() {
		return idDosis;
	}
	public void setIdDosis(long idDosis) {
		this.idDosis = idDosis;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public double getCantidad() {
		return cantidad;
	}
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}
	public Medicamento getMedicamento() {
		return medicamento;
	}
	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}
	public Ternera getTernera() {
		return ternera;
	}
	public void setTernera(Ternera ternera) {
		this.ternera = ternera;
	}
}