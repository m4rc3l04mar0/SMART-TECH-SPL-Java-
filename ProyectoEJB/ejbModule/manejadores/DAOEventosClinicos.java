package manejadores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;

import objetos.Enfermedad;
import objetos.EventoClinico;
import objetos.Ternera;

public class DAOEventosClinicos {

	private static final String ALL_EVENTOS = "SELECT * FROM TIENE_ENFERMEDADES";


	//Todos los eventos clinicos
	public static LinkedList<EventoClinico> obtenerTodosEventos(){
		LinkedList<EventoClinico> eventos = new LinkedList<>();

		try{
			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(ALL_EVENTOS);

			ResultSet resultado = statement.executeQuery();

			while (resultado.next()){

				EventoClinico evento = getEventoFromResultSet(resultado);
				eventos.add(evento);
			}
			return eventos;
		}
		catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}


	//Resultado
	private static EventoClinico getEventoFromResultSet(ResultSet resultado) throws SQLException{
		java.sql.Date  fechaSQL = resultado.getDate(1);
		java.sql.Date  fechaSQL1 = resultado.getDate(2);
		String observacion = resultado.getString(3);
		long idEnfermedad = resultado.getLong(4);
		long idTernera = resultado.getLong(5);
		long idTieneEnfermedad = resultado.getLong(6);

		Ternera ternera = DAOTerneras.obtenerPorId(idTernera);

		Enfermedad enfermedad = DAOEnfermedades.obtenerEnfermedad(idEnfermedad);

		Date fechaInicio = new Date(fechaSQL.getTime());

		Date fechaFin = new Date(fechaSQL1.getTime());

		EventoClinico evento = new EventoClinico(idTieneEnfermedad, fechaInicio, fechaFin, observacion, ternera, enfermedad);

		return evento;
	}
}