package manejadores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;

import enums.TipoRegistro;
import objetos.Peso;
import objetos.Ternera;

public class DAOPesos {

	private static final String INSERT_PESO = "INSERT INTO PESOS (Tipo_Registro, Fecha, Peso, ID_Ternera) values (?,?,?,?)";
	private static final String PESOS_TERNERAS = "SELECT * FROM PESOS WHERE ID_Ternera=? ORDER BY 3";


	//Insertar
	public static boolean ingresarPeso(Peso peso){
		try{
			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(INSERT_PESO);

			java.sql.Date fechaSQL = new java.sql.Date(peso.getFechaPeso().getTime());

			statement.setString(1, peso.getTipoRegistro().name());
			statement.setDate(2, fechaSQL);
			statement.setDouble(3, peso.getPeso());
			statement.setLong(4, peso.getTernera().getIdTernera());

			int retorno = statement.executeUpdate();

			return retorno>0;
		}
		catch(SQLException e){
			e.printStackTrace();
			return false;
		}
	}


	//Buscar por idTernera
	public static LinkedList<Peso> obtenerPorIdTernera(long idTernera){
		LinkedList<Peso> pesos = new LinkedList<>();

		try{
			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(PESOS_TERNERAS);

			statement.setLong(1, idTernera);

			ResultSet resultado = statement.executeQuery();

			while(resultado.next()){
				Peso peso = getPesoFromResultSet(resultado);
				pesos.add(peso);
			}
			return pesos;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}


	//Resultado
	private static Peso getPesoFromResultSet(ResultSet resultado) throws SQLException{
		long idPeso = resultado.getLong(1);
		String tipoRegistro = resultado.getString(2);
		java.sql.Date  fechaSQL = resultado.getDate(3);
		double peso = resultado.getDouble(4);
		long idTernera = resultado.getLong(5);

		Ternera ternera = DAOTerneras.obtenerPorId(idTernera);

		Date fechaPeso = new Date(fechaSQL.getTime());

		Peso pesoo = new Peso(idPeso, TipoRegistro.valueOf(tipoRegistro), fechaPeso, peso, ternera);

		return pesoo;
	}
}