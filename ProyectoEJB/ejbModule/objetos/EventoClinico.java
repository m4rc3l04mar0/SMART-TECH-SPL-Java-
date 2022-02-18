package objetos;

import java.io.Serializable;
import java.util.Date;

public class EventoClinico implements Serializable{

	private static final long serialVersionUID = 1L;

	private long idTieneEnfermedad;
	private Date fechaInicio;
	private Date fechaFin;
	private String observacion;
	private Ternera ternera;
	private Enfermedad enfermedad;

	public EventoClinico(long idTieneEnfermedad, Date fechaInicio, Date fechaFin, String observacion, Ternera ternera, Enfermedad enfermedad) {
		this.idTieneEnfermedad = idTieneEnfermedad;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.observacion = observacion;
		this.ternera = ternera;
		this.enfermedad = enfermedad;
	}

	public long getIdTieneEnfermedad() {
		return idTieneEnfermedad;
	}
	public void setIdTieneEnfermedad(long idTieneEnfermedad) {
		this.idTieneEnfermedad = idTieneEnfermedad;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public Ternera getTernera() {
		return ternera;
	}
	public void setTernera(Ternera ternera) {
		this.ternera = ternera;
	}
	public Enfermedad getEnfermedad() {
		return enfermedad;
	}
	public void setEnfermedad(Enfermedad enfermedad) {
		this.enfermedad = enfermedad;
	}
}