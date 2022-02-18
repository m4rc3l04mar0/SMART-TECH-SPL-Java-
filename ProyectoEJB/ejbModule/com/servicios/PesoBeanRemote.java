package com.servicios;

import java.util.LinkedList;
import javax.ejb.Remote;
import objetos.Peso;

@Remote
public interface PesoBeanRemote {
	public boolean ingresarPeso(Peso peso);
	public LinkedList<Peso> obtenerPorTernera(long idTernera);
}