package manejadores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import enums.NombreEnfermedad;
import objetos.Enfermedad;

public class DAOEnfermedades {

	private static final String ALL_ENFERMEDADES = "SELECT * FROM ENFERMEDADES";
	private static final String ENFERMEDADES_ID = "SELECT * FROM ENFERMEDADES WHERE ID_Enfermedad=?";


	//Todas las enfermedades
	public static LinkedList<Enfermedad> obtenerTodasEnfermedades(){
		LinkedList<Enfermedad> enfermedades = new LinkedList<>();

		try{
			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(ALL_ENFERMEDADES);

			ResultSet resultado = statement.executeQuery();

			while (resultado.next()){

				Enfermedad enfermedad = getEnfermedadFromResultSet(resultado);
				enfermedades.add(enfermedad);
			}
			return enfermedades;
		}
		catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}


	//Buscar por id
	public static Enfermedad obtenerEnfermedad(long idEnfermedad){

		try{
			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(ENFERMEDADES_ID);
			statement.setLong(1, idEnfermedad);

			ResultSet resultado = statement.executeQuery();

			Enfermedad enfermedad = null;
			while(resultado.next()){
				enfermedad = getEnfermedadFromResultSet(resultado);
			}
			return enfermedad;
		}
		catch(SQLException e){
			e.printStackTrace();
			return null;
		}	
	}


	//Resultado
	private static Enfermedad getEnfermedadFromResultSet(ResultSet resultado) throws SQLException{
		long idEnfermedad = resultado.getLong(1);
		String nombre = resultado.getString(2);
		int gravedad = resultado.getInt(3);

		Enfermedad enfermedad = new Enfermedad(idEnfermedad, NombreEnfermedad.valueOf(nombre), gravedad);

		return enfermedad;
	}
}