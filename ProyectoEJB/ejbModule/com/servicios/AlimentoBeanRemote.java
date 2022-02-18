package com.servicios;

import java.util.LinkedList;
import javax.ejb.Remote;
import objetos.Alimento;

@Remote
public interface AlimentoBeanRemote {
	public boolean ingresarAlimento(Alimento alimento);
	public LinkedList<Alimento> obtenerTodosAlimentos();
	public boolean editarAlimento(Alimento alimento);
	public Alimento obtenerPorNombre(String nombre);
	public LinkedList<String> obtenerNombreAlimentos();
}