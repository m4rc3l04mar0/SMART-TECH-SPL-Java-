package objetos;

import java.io.Serializable;
import java.util.Date;

public class Medicamento implements Serializable{

	private static final long serialVersionUID = 1L;

	private long idMedicamento;
	private String nombre;
	private String dosis;
	private Date fecha;

	public Medicamento(long idMedicamento, String nombre, String dosis, Date fecha) {
		this.idMedicamento = idMedicamento;
		this.nombre = nombre;
		this.dosis = dosis;
		this.fecha = fecha;
	}

	public long getIdMedicamento() {
		return idMedicamento;
	}
	public void setIdMedicamento(long idMedicamento) {
		this.idMedicamento = idMedicamento;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDosis() {
		return dosis;
	}
	public void setDosis(String dosis) {
		this.dosis = dosis;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}