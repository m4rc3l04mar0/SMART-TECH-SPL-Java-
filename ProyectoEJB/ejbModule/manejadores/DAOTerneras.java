package manejadores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;

import enums.Raza;
import enums.TipoParto;
import objetos.Guachera;
import objetos.Ternera;

public class DAOTerneras {

	private static final String ALL_TERNERAS = "SELECT * FROM TERNERAS ORDER BY ID_Ternera";
	private static final String TERNERAS_ID = "SELECT * FROM TERNERAS WHERE ID_Ternera=?";
	private static final String TERNERAS_CARAVANA = "SELECT * FROM TERNERAS WHERE Nro_Caravana=?";


	//Todas las terneras
	public static LinkedList<Ternera> obtenerTodasTerneras(){
		LinkedList<Ternera> terneras = new LinkedList<>();

		try{
			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(ALL_TERNERAS);

			ResultSet resultado = statement.executeQuery();

			while (resultado.next()){

				Ternera ternera = getTerneraFromResultSet(resultado);
				terneras.add(ternera);
			}
			return terneras;
		}
		catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}


	//Buscar por id
	public static Ternera obtenerPorId(long idTernera){

		try{
			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(TERNERAS_ID);

			statement.setLong(1, idTernera);

			ResultSet resultado = statement.executeQuery();

			Ternera ternera = null;
			while(resultado.next()){
				ternera = getTerneraFromResultSet(resultado);
			}
			return ternera;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}


	//Buscar por caravana
	public static Ternera obtenerPorCaravana(int caravana){

		try{
			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(TERNERAS_CARAVANA);

			statement.setInt(1, caravana);

			ResultSet resultado = statement.executeQuery();

			Ternera ternera = null;
			while(resultado.next()){
				ternera = getTerneraFromResultSet(resultado);
			}
			return ternera;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}


	//Resultado
	private static Ternera getTerneraFromResultSet(ResultSet resultado) throws SQLException{
		long idTernera = resultado.getLong(1);
		int numCaravana = resultado.getInt(2);
		long idGuachera = resultado.getLong(3);
		long idPadre = resultado.getLong(4);
		long idMadre = resultado.getLong(5);
		java.sql.Date fechaSQL = resultado.getDate(6);
		double pesoNacimiento = resultado.getDouble(7);
		String raza = resultado.getString(8);
		String parto = resultado.getString(9);
		java.sql.Date fechaSQL1 = resultado.getDate(10);
		String causaMuerte = resultado.getString(11);
		java.sql.Date fechaSQL2 = resultado.getDate(12);
		String motivoBaja = resultado.getString(13);

		Date fechaNacimiento = new Date(fechaSQL.getTime());
		Date fechaMuerte = new Date(fechaSQL1.getTime());
		Date fechaBaja = new Date(fechaSQL2.getTime());

		Guachera guachera = DAOGuacheras.obtenerGuachera(idGuachera);

		Ternera ternera = new Ternera(idTernera, numCaravana, guachera, idPadre, idMadre, fechaNacimiento, pesoNacimiento, Raza.valueOf(raza), TipoParto.valueOf(parto), fechaMuerte, causaMuerte, fechaBaja, motivoBaja, null);

		return ternera;
	}
}