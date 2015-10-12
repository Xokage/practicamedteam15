package main.java.busqueda;

import java.util.Calendar;

public interface BusquedaService {
	
	public String buscarHabitacion(String localizacion, 
			Calendar dataInicio, Calendar dataFin, int numPersoas);
	
	
	// Opcion 0 = Ordear por nome
	// Opcion 1 = Ordear por prezo
	// Opcion 3 = Ordear por categoria
	
	public String ordear(int opcion);

}
