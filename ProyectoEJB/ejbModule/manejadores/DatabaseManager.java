package manejadores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DatabaseManager {

	private static Connection dbConn;

	private static String CONNECTION_STRING = "jdbc:oracle:thin:@localhost:1521:xe";
	private static String USUARIO = "PROYECTO";
	private static String CLAVE = "PROYECTO";

	static {
		try {
			Locale.setDefault(new Locale("es", "ES"));
			dbConn = DriverManager.getConnection(CONNECTION_STRING, USUARIO, CLAVE);
		} catch (Exception e) {
			System.out.println("Error creando la conexión a la base de datos");
		}
	}

	public static Connection getConnection() {
		if (dbConn == null) {
			try {
				DataSource ds = (DataSource) InitialContext.doLookup("java:/ProyectoDS");
				dbConn = ds.getConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		return dbConn;
	}
}