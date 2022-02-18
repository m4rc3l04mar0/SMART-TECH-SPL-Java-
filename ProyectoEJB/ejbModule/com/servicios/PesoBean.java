package com.servicios;

import java.util.LinkedList;
import javax.ejb.Stateless;
import manejadores.DAOPesos;
import objetos.Peso;

/**
 * Session Bean implementation class PesoBean
 */
@Stateless
public class PesoBean implements PesoBeanRemote {

	/**
	 * Default constructor. 
	 */
	public PesoBean() {
	}

	@Override
	public boolean ingresarPeso(Peso peso) {
		return DAOPesos.ingresarPeso(peso);
	}

	@Override
	public LinkedList<Peso> obtenerPorTernera(long idTernera) {
		return DAOPesos.obtenerPorIdTernera(idTernera);
	}
}