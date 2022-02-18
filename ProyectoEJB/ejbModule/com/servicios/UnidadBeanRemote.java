package com.servicios;

import javax.ejb.Remote;
import objetos.Unidad;

@Remote
public interface UnidadBeanRemote {
	public Unidad obtenerUnidad(long idUnidad);
}