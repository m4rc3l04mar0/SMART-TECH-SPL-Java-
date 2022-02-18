package manejadores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import objetos.Guachera;

public class DAOGuacheras {

	private static final String GUACHERAS_ID = "SELECT * FROM GUACHERAS WHERE ID_Guachera=?";


	//Buscar por id
	public static Guachera obtenerGuachera(long idGuachera){

		try{
			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(GUACHERAS_ID);

			statement.setLong(1, idGuachera);

			ResultSet resultado = statement.executeQuery();

			Guachera guachera = null;
			while(resultado.next()){
				guachera = getGuacheraFromResultSet(resultado);
			}
			return guachera;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}


	//Resultado
	private static Guachera getGuacheraFromResultSet(ResultSet resultado) throws SQLException{
		long idGuachera = resultado.getLong("ID_Guachera");
		String nombre = resultado.getString("Nombre");
		long idUsuario = resultado.getLong("ID_Usuario");

		Guachera guachera = new Guachera(idGuachera, nombre, idUsuario);

		return guachera;
	}
}