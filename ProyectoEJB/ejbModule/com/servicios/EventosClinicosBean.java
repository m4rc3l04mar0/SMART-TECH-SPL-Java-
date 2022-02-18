package com.servicios;

import java.util.LinkedList;
import javax.ejb.Stateless;
import manejadores.DAOEventosClinicos;
import objetos.EventoClinico;

/**
 * Session Bean implementation class EventosClinicosBean
 */
@Stateless
public class EventosClinicosBean implements EventosClinicosBeanRemote {

	/**
	 * Default constructor. 
	 */
	public EventosClinicosBean() {
	}

	@Override
	public LinkedList<EventoClinico> obtenerTodosEventos() {
		return DAOEventosClinicos.obtenerTodosEventos();
	}
}