package com.servicios;

import javax.ejb.Remote;
import objetos.Consumo;

@Remote
public interface ConsumoBeanRemote {
	public boolean ingresarConsumo(Consumo consumo);
}