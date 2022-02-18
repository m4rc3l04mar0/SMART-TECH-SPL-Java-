package com.servicios;

import java.util.LinkedList;
import javax.ejb.Remote;
import objetos.Usuario;

@Remote
public interface UsuarioBeanRemote {
	public boolean ingresarUsuario(Usuario usuario);
	public boolean eliminarUsuario(Usuario usuario);
	public LinkedList<Usuario> obtenerTodosUsuarios();
	public boolean editarUsuario(Usuario usuario);
}