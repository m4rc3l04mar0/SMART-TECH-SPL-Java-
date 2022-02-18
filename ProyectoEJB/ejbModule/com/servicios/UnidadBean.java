package com.servicios;

import javax.ejb.Stateless;
import manejadores.DAOUnidades;
import objetos.Unidad;

/**
 * Session Bean implementation class UnidadBean
 */
@Stateless
public class UnidadBean implements UnidadBeanRemote {

	/**
	 * Default constructor. 
	 */
	public UnidadBean() {
	}

	@Override
	public Unidad obtenerUnidad(long idUnidad) {
		return DAOUnidades.obtenerUnidad(idUnidad);
	}
}