package manejadores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import enums.TipoUnidad;
import objetos.Unidad;

public class DAOUnidades {

	private static final String ALL_UNIDADES = "SELECT * FROM UNIDADES";
	private static final String UNIDADES_ID = "SELECT * FROM UNIDADES WHERE ID_Unidad=?";


	//Todas las unidades
	public static LinkedList<Unidad> obtenerTodasUnidades(){
		LinkedList<Unidad> unidades = new LinkedList<>();

		try{
			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(ALL_UNIDADES);

			ResultSet resultado = statement.executeQuery();

			while (resultado.next()){
				Unidad unidad = getUnidadFromResultSet(resultado);				
				unidades.add(unidad);				
			}
			return unidades;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}


	//Buscar por id
	public static Unidad obtenerUnidad(long idUnidad){

		try{
			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(UNIDADES_ID);

			statement.setLong(1, idUnidad);

			ResultSet resultado = statement.executeQuery();

			Unidad unidad = null;
			while(resultado.next()){
				unidad = getUnidadFromResultSet(resultado);
			}
			return unidad;
		}
		catch(SQLException e){
			e.printStackTrace();
			return null;
		}	
	}


	//Resultado
	private static Unidad getUnidadFromResultSet(ResultSet resultado) throws SQLException{
		long idUnidad = resultado.getLong(1);
		String unidad = resultado.getString(2);

		Unidad uni = new Unidad(idUnidad, TipoUnidad.valueOf(unidad));

		return uni;
	}
}