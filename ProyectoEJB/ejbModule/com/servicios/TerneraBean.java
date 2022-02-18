package com.servicios;

import java.util.LinkedList;
import javax.ejb.Stateless;
import manejadores.DAOTerneras;
import objetos.Ternera;

/**
 * Session Bean implementation class TerneraBean
 */
@Stateless
public class TerneraBean implements TerneraBeanRemote {

	/**
	 * Default constructor. 
	 */
	public TerneraBean() {
	}

	@Override
	public Ternera obtenerPorCaravana(int caravana) {
		return DAOTerneras.obtenerPorCaravana(caravana);
	}

	@Override
	public Ternera obtenerPorId(long idTernera) {
		return DAOTerneras.obtenerPorId(idTernera);
	}

	@Override
	public LinkedList<Ternera> obtenerTodasTerneras() {
		return DAOTerneras.obtenerTodasTerneras();
	}
}