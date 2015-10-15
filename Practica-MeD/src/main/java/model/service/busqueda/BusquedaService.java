package main.java.model.service.busqueda;

import java.util.Calendar;

import main.java.model.db.busqueda.Busqueda;

public interface BusquedaService {
	
	public Busqueda realizarBusqueda(String localizacion, 
			Calendar dataInicio, Calendar dataFin, int numPersoas, int opcion, boolean desc);
	
	
	// Opcion 0 = Ordear por nome
	// Opcion 1 = Ordear por prezo
	// Opcion 3 = Ordear por categoria
	
	

}
