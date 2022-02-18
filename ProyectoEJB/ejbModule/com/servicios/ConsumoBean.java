package com.servicios;

import javax.ejb.Stateless;
import manejadores.DAOConsumos;
import objetos.Consumo;

/**
 * Session Bean implementation class ConsumoBean
 */
@Stateless
public class ConsumoBean implements ConsumoBeanRemote {

	/**
	 * Default constructor. 
	 */
	public ConsumoBean() {
	}

	@Override
	public boolean ingresarConsumo(Consumo consumo) {
		return DAOConsumos.ingresarConsumo(consumo);
	}
}