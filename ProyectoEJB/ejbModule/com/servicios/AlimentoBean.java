package com.servicios;

import java.util.LinkedList;
import javax.ejb.Stateless;

import controladores.ControladorAlimentos;
import manejadores.DAOAlimentos;
import objetos.Alimento;

/**
 * Session Bean implementation class AltaAlimentoBean
 */
@Stateless
public class AlimentoBean implements AlimentoBeanRemote {

	/**
	 * Default constructor. 
	 */
	public AlimentoBean() {

	}

	@Override
	public boolean ingresarAlimento(Alimento alimento) {
		return DAOAlimentos.ingresarAlimento(alimento);
	}

	@Override
	public LinkedList<Alimento> obtenerTodosAlimentos() {
		return DAOAlimentos.obtenerTodosAlimentos();
	}

	@Override
	public boolean editarAlimento(Alimento alimento) {
		return DAOAlimentos.editarAlimento(alimento);
	}

	@Override
	public Alimento obtenerPorNombre(String nombre) {
		return DAOAlimentos.obtenerPorNombre(nombre);
	}

	@Override
	public LinkedList<String> obtenerNombreAlimentos() {
		return ControladorAlimentos.obtenerNombreAlimentos();
	}
}