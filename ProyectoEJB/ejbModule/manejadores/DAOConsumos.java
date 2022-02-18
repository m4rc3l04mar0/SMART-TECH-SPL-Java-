package manejadores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;

import objetos.Alimento;
import objetos.Consumo;
import objetos.Ternera;

public class DAOConsumos {

	private static final String ALL_CONSUMOS = "SELECT * FROM CONSUMOS ORDER BY ID_Ternera";
	private static final String INSERT_CONSUMO = "INSERT INTO CONSUMOS (Fecha, Cantidad, ID_Ternera, ID_Alimento) values (?,?,?,?)";


	//Insertar
	public static boolean ingresarConsumo(Consumo consumo){
		try{
			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(INSERT_CONSUMO);

			java.sql.Date fechaSQL = new java.sql.Date(consumo.getFechaConsumo().getTime());

			statement.setDate(1, fechaSQL);
			statement.setDouble(2, consumo.getCantidad());
			statement.setLong(3, consumo.getTernera().getIdTernera());
			statement.setLong(4, consumo.getAlimento().getIdAlimento());

			int retorno = statement.executeUpdate();

			return retorno>0;
		}
		catch(SQLException e){
			e.printStackTrace();
			return false;
		}
	}


	//Todos los consumos
	public static LinkedList<Consumo> obtenerTodosConsumos(){
		LinkedList<Consumo> consumos = new LinkedList<>();

		try{
			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(ALL_CONSUMOS);

			ResultSet resultado = statement.executeQuery();

			while (resultado.next()){

				Consumo consumo = getConsumoFromResultSet(resultado);				
				consumos.add(consumo);
			}
			return consumos;
		}
		catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}


	//Resultado
	private static Consumo getConsumoFromResultSet(ResultSet resultado) throws SQLException{
		java.sql.Date  fechaSQL = resultado.getDate(1);
		double cantidad = resultado.getDouble(2);
		long idAlimento = resultado.getLong(3);
		long idTernera = resultado.getLong(4);
		long idConsumo = resultado.getLong(5);

		Alimento alimento = DAOAlimentos.obtenerPorId(idAlimento);

		Ternera ternera = DAOTerneras.obtenerPorId(idTernera);

		Date fechaConsumo = new Date(fechaSQL.getTime());

		Consumo consumo = new Consumo(idConsumo, fechaConsumo, cantidad, alimento, ternera);

		return consumo;
	}
}