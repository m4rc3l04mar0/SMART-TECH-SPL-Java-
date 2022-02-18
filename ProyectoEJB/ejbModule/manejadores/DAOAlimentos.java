package manejadores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import objetos.Alimento;
import objetos.Unidad;

public class DAOAlimentos {

	private static final String ALL_ALIMENTOS = "SELECT * FROM ALIMENTOS ORDER BY ID_Alimento";	
	private static final String INSERT_ALIMENTO = "INSERT INTO ALIMENTOS (Nombre, Costo_Unidad, Cantidad, ID_Unidad) values (?,?,?,?)";
	private static final String UPDATE_ALIMENTO = "UPDATE ALIMENTOS SET Costo_Unidad=?, Cantidad=? WHERE ID_Alimento=?";
	private static final String DELETE_ALIMENTO = "DELETE FROM ALIMENTOS WHERE ID_Alimento=?";
	private static final String ALIMENTOS_ID = "SELECT * FROM ALIMENTOS WHERE ID_Alimento=?";
	private static final String ALIMENTOS_NOMBRE = "SELECT * FROM ALIMENTOS WHERE Nombre=?";


	//Insertar
	public static boolean ingresarAlimento(Alimento alimento){
		try{
			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(INSERT_ALIMENTO);

			statement.setString(1, alimento.getNombre());
			statement.setDouble(2, alimento.getCostoUnidad());
			statement.setDouble(3, alimento.getCantidad());
			statement.setLong(4, alimento.getUnidad().getIdUnidad());

			int retorno = statement.executeUpdate();

			return retorno>0;
		}
		catch(SQLException e){
			e.printStackTrace();
			return false;
		}
	}


	//Editar
	public static boolean editarAlimento(Alimento alimento){
		try{
			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(UPDATE_ALIMENTO);

			statement.setDouble(1, alimento.getCostoUnidad());
			statement.setDouble(2, alimento.getCantidad());
			statement.setLong(3, alimento.getIdAlimento());

			int retorno = statement.executeUpdate();

			return retorno>0;
		}
		catch(SQLException e){
			e.printStackTrace();
			return false;
		}
	}


	//Eliminar
	public static boolean eliminarAlimento(Alimento alimento){
		try{
			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(DELETE_ALIMENTO);

			statement.setLong(1, alimento.getIdAlimento());

			int retorno = statement.executeUpdate();

			return retorno>0;
		}
		catch(SQLException e){
			e.printStackTrace();
			return false;
		}
	}


	//Todos los alimentos
	public static LinkedList<Alimento> obtenerTodosAlimentos(){
		LinkedList<Alimento> alimentos = new LinkedList<>();

		try{
			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(ALL_ALIMENTOS);

			ResultSet resultado = statement.executeQuery();

			while (resultado.next()){

				Alimento alimento = getAlimentoFromResultSet(resultado);				
				alimentos.add(alimento);
			}
			return alimentos;
		}
		catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}


	//Buscar por id
	public static Alimento obtenerPorId(long idAlimento){

		try{
			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(ALIMENTOS_ID);

			statement.setLong(1, idAlimento);

			ResultSet resultado = statement.executeQuery();

			Alimento alimento = null;
			while(resultado.next()){
				alimento = getAlimentoFromResultSet(resultado);
			}
			return alimento;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}


	//Buscar por nombre
	public static Alimento obtenerPorNombre(String nombre){

		try{
			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(ALIMENTOS_NOMBRE);
			statement.setString(1, nombre);

			ResultSet resultado = statement.executeQuery();

			Alimento alimento = null;
			while(resultado.next()){
				alimento = getAlimentoFromResultSet(resultado);
			}
			return alimento;
		}
		catch(SQLException e){
			e.printStackTrace();
			return null;
		}	
	}


	//Resultado
	private static Alimento getAlimentoFromResultSet(ResultSet resultado) throws SQLException{
		long idAlimento = resultado.getLong(1);
		String nombre = resultado.getString(2);
		double costoUnidad = resultado.getDouble(3);
		double cantidad = resultado.getDouble(4);
		long idUnidad = resultado.getLong(5);

		Unidad unidad = DAOUnidades.obtenerUnidad(idUnidad);

		Alimento alimento = new Alimento(idAlimento, nombre, costoUnidad, cantidad, unidad);

		return alimento;
	}
}