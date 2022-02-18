package com.servicios;

import java.util.LinkedList;
import javax.ejb.Stateless;
import manejadores.DAOUsuarios;
import objetos.Usuario;

/**
 * Session Bean implementation class AltaUsuarioBean
 */
@Stateless
public class UsuarioBean implements UsuarioBeanRemote {

	/**
	 * Default constructor. 
	 */
	public UsuarioBean() {
	}

	@Override
	public boolean ingresarUsuario(Usuario usuario) {
		return DAOUsuarios.ingresarUsuario(usuario);
	}

	@Override
	public boolean eliminarUsuario(Usuario usuario) {
		return DAOUsuarios.eliminarUsuario(usuario);
	}

	@Override
	public LinkedList<Usuario> obtenerTodosUsuarios() {
		return DAOUsuarios.obtenerTodosUsuarios();
	}

	@Override
	public boolean editarUsuario(Usuario usuario) {
		return DAOUsuarios.editarUsuario(usuario);
	}
}