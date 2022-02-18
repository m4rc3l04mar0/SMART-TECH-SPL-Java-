package objetos;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;

import enums.Raza;
import enums.TipoParto;

public class Ternera implements Serializable{

	private static final long serialVersionUID = 1L;

	private long idTernera;
	private int numCaravana;
	private Guachera guachera;
	private long idMadre;
	private long idPadre;
	private Date fechaNacimiento;
	private double pesoNacimiento;
	private Raza raza;
	private TipoParto tipoParto;
	private Date fechaMuerte;
	private String causaMuerte;
	private Date fechaBaja;
	private String motivoBaja;
	private LinkedList<Peso> historicoPeso;

	public Ternera(long idTernera, int numCaravana, Guachera guachera, long idMadre, long idPadre, Date fechaNacimiento,
			double pesoNacimiento, Raza raza, TipoParto tipoParto, Date fechaMuerte, String causaMuerte, Date fechaBaja,
			String motivoBaja, LinkedList<Peso> historicoPeso) {
		this.idTernera = idTernera;
		this.numCaravana = numCaravana;
		this.guachera = guachera;
		this.idMadre = idMadre;
		this.idPadre = idPadre;
		this.fechaNacimiento = fechaNacimiento;
		this.pesoNacimiento = pesoNacimiento;
		this.raza = raza;
		this.tipoParto = tipoParto;
		this.fechaMuerte = fechaMuerte;
		this.causaMuerte = causaMuerte;
		this.fechaBaja = fechaBaja;
		this.motivoBaja = motivoBaja;
		this.historicoPeso = historicoPeso;
	}

	public long getIdTernera() {
		return idTernera;
	}
	public void setIdTernera(long idTernera) {
		this.idTernera = idTernera;
	}
	public int getNumCaravana() {
		return numCaravana;
	}
	public void setNumCaravana(int numCaravana) {
		this.numCaravana = numCaravana;
	}
	public Guachera getGuachera() {
		return guachera;
	}
	public void setGuachera(Guachera guachera) {
		this.guachera = guachera;
	}
	public long getIdMadre() {
		return idMadre;
	}
	public void setIdMadre(long idMadre) {
		this.idMadre = idMadre;
	}
	public long getIdPadre() {
		return idPadre;
	}
	public void setIdPadre(long idPadre) {
		this.idPadre = idPadre;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public double getPesoNacimiento() {
		return pesoNacimiento;
	}
	public void setPesoNacimiento(double pesoNacimiento) {
		this.pesoNacimiento = pesoNacimiento;
	}
	public Raza getRaza() {
		return raza;
	}
	public void setRaza(Raza raza) {
		this.raza = raza;
	}
	public TipoParto getTipoParto() {
		return tipoParto;
	}
	public void setTipoParto(TipoParto tipoParto) {
		this.tipoParto = tipoParto;
	}
	public Date getFechaMuerte() {
		return fechaMuerte;
	}
	public void setFechaMuerte(Date fechaMuerte) {
		this.fechaMuerte = fechaMuerte;
	}
	public String getCausaMuerte() {
		return causaMuerte;
	}
	public void setCausaMuerte(String causaMuerte) {
		this.causaMuerte = causaMuerte;
	}
	public Date getFechaBaja() {
		return fechaBaja;
	}
	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	public String getMotivoBaja() {
		return motivoBaja;
	}
	public void setMotivoBaja(String motivoBaja) {
		this.motivoBaja = motivoBaja;
	}
	public LinkedList<Peso> getHistoricoPeso() {
		return historicoPeso;
	}
	public void setHistoricoPeso(LinkedList<Peso> historicoPeso) {
		this.historicoPeso = historicoPeso;
	}
}