package main.java.busqueda;

import java.sql.Connection;
import java.util.Calendar;

public interface SqlBusquedaDao {
	
	public Busqueda realizarBusqueda(Connection connection, String localizacion, 
			Calendar dataInicio, Calendar dataFin, int numPersoas);
	
	
	/** Opcion 0 = Ordear por nome
	/* Opcion 1 = Ordear por prezo
	/* Opcion 2 = Ordear por categoria
	**/
	public Busqueda ordear(Connection connection,Busqueda busqueda, int opcion);

}
