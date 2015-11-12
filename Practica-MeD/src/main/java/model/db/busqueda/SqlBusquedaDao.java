package main.java.model.db.busqueda;

import java.sql.Connection;
import java.util.Calendar;
import java.util.List;

import main.java.model.db.filtro.Filtro;

public interface SqlBusquedaDao {

	/**
	 * Opcion 0 = Ordear por nome /* Opcion 1 = Ordear por prezo /* Opcion 2 =
	 * Ordear por categoria
	 **/

	public Busqueda realizarBusqueda(Connection connection,
			String localizacion, Calendar dataInicio, Calendar dataFin,
			int numPersoas, int opcion, boolean desc, List<Filtro> filtros);
	
	public Busqueda realizarBusqueda(Connection connection,
			String localizacion, Float precioMax, Float precioMin, Calendar dataInicio, Calendar dataFin,
			int numPersoas, int opcion, boolean desc, List<Filtro> filtros);

}
