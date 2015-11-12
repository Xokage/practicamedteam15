package main.java.model.service.busqueda;

import java.util.Calendar;
import java.util.List;

import main.java.model.db.busqueda.Busqueda;
import main.java.model.db.filtro.Filtro;

public interface BusquedaService {

	public Busqueda realizarBusqueda(String localizacion, Calendar dataInicio,
			Calendar dataFin, int numPersoas, int opcion, boolean desc,
			List<Filtro> filtros);

	// Opcion 0 = Ordear por nome
	// Opcion 1 = Ordear por prezo
	// Opcion 3 = Ordear por categoria
	
	public Busqueda realizarBusqueda(String localizacion, Float prezoMax, Float prezoMin, Calendar dataInicio,
			Calendar dataFin, int numPersoas, int opcion, boolean desc,
			List<Filtro> filtros);
}
