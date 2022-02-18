package com.servicios;

import java.util.LinkedList;
import javax.ejb.Remote;
import objetos.EventoClinico;

@Remote
public interface EventosClinicosBeanRemote {
	public LinkedList<EventoClinico> obtenerTodosEventos();
}