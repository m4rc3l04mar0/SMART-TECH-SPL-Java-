package manejadores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import enums.Perfil;
import objetos.Usuario;

public class DAOUsuarios {

	private static final String ALL_USUARIOS = "SELECT * FROM USUARIOS";
	private static final String INSERT_USUARIO = "INSERT INTO USUARIOS (Usuario, Nombre, Apellido, Perfil, Contraseña) VALUES (?,?,?,?,?)";
	private static final String UPDATE_USUARIO = "UPDATE USUARIOS SET Contraseña=? WHERE ID_Usuario=?";
	private static final String DELETE_USUARIO = "DELETE FROM USUARIOS WHERE ID_Usuario=?";
	private static final String USUARIOS_ID = "SELECT * FROM USUARIOS WHERE ID_Usuario=?";

	//Insertar
	public static boolean ingresarUsuario(Usuario usuario){

		try{
			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(INSERT_USUARIO);

			statement.setString(1, usuario.getUsuario());
			statement.setString(2, usuario.getNombre());
			statement.setString(3, usuario.getApellido());
			statement.setString(4, usuario.getPerfil().name());
			statement.setString(5, usuario.getContraseña());

			int retorno = statement.executeUpdate();

			return retorno>0;
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
	}


	//Editar
	public static boolean editarUsuario(Usuario usuario){

		try{
			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(UPDATE_USUARIO);

			statement.setString(1, usuario.getContraseña());
			statement.setLong(2, usuario.getIdUsuario());

			int retorno = statement.executeUpdate();

			return retorno>0;
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
	}


	//Eliminar
	public static boolean eliminarUsuario(Usuario usuario){
		try{
			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(DELETE_USUARIO);
			statement.setLong(1, usuario.getIdUsuario());

			int retorno = statement.executeUpdate();

			return retorno>0;
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
	}


	//Todos los usuarios
	public static LinkedList<Usuario> obtenerTodosUsuarios(){
		LinkedList<Usuario> usuarios = new LinkedList<>();

		try{
			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(ALL_USUARIOS);

			ResultSet resultado = statement.executeQuery();

			while (resultado.next()){

				Usuario usuario = getUsuarioFromResultSet(resultado);				
				usuarios.add(usuario);
			}
			return usuarios;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}


	//Buscar por id
	public static Usuario obtenerUsuario(long idUsuario){

		try{
			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(USUARIOS_ID);
			statement.setLong(1, idUsuario);

			ResultSet resultado = statement.executeQuery();

			Usuario usuario = null;
			while(resultado.next()){
				usuario = getUsuarioFromResultSet(resultado);
			}
			return usuario;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}	
	}


	//Resultado
	private static Usuario getUsuarioFromResultSet(ResultSet resultado) throws SQLException{
		long idUsuario = resultado.getLong("ID_Usuario");
		String usuario = resultado.getString("Usuario");
		String nombre = resultado.getString("Nombre");
		String apellido = resultado.getString("Apellido");
		String perfil = resultado.getString("Perfil");
		String contraseña = resultado.getString("Contraseña");

		Usuario usu = new Usuario(idUsuario, usuario, nombre, apellido, Perfil.valueOf(perfil), contraseña);

		return usu;
	}
}