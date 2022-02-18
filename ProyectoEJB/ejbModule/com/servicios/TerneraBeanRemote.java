package com.servicios;

import java.util.LinkedList;
import javax.ejb.Remote;
import objetos.Ternera;

@Remote
public interface TerneraBeanRemote {
	public Ternera obtenerPorCaravana(int caravana);
	public Ternera obtenerPorId(long idTernera);
	public LinkedList<Ternera> obtenerTodasTerneras();
}