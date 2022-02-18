package controladores;

import java.util.LinkedList;

import manejadores.DAOAlimentos;
import objetos.Alimento;

public class ControladorAlimentos {

	public static LinkedList<String> obtenerNombreAlimentos(){
		LinkedList<String> nombres = new LinkedList<>();
		for(Alimento a : DAOAlimentos.obtenerTodosAlimentos()){
			nombres.add(a.getNombre());
		}		
		return nombres;
	}
}