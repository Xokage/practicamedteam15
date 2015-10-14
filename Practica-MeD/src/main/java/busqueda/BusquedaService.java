package main.java.busqueda;

import java.sql.Connection;
import java.util.Calendar;

public interface BusquedaService {
	
	public Busqueda realizarBusqueda(String localizacion, 
			Calendar dataInicio, Calendar dataFin, int numPersoas, int opcion);
	
	
	// Opcion 0 = Ordear por nome
	// Opcion 1 = Ordear por prezo
	// Opcion 3 = Ordear por categoria
	
	

}
