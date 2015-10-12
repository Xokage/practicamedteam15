package main.java.busqueda;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import main.java.hotel.Hotel;
//import org.springframework.stereotype.Repository;

//@Repository("BusquedaDao")
public class BusquedaDaoHibernate implements BusquedaDao {
	
	@SuppressWarnings("unchecked")
	@Override
	public Busqueda buscarHabitacion(String localizacion, Calendar dataInicio,
			Calendar dataFin, int numPersoas) {
		
		Busqueda busqueda = new Busqueda(localizacion,dataInicio,dataFin,numPersoas);
		List<Hotel> hoteis = new ArrayList<Hotel>();
		hoteis = (List<Hotel>) getSession().createQuery("SELECT h FROM Habitacion h WHERE h.idhotel.localizacion = :localizacion").
	        	setParameter("localizacion",localizacion).list();
		busqueda.setHoteis(hoteis);
		return busqueda;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Busqueda ordear(Busqueda busqueda, int opcion) {
		
		String localizacion = busqueda.getLocalizacion();
		Calendar dataInicio = busqueda.getDataInicio();
		Calendar dataFin = busqueda.getDataFin();
		int numPersoas = busqueda.getNumPerPorHab();
		
		Busqueda novaBusqueda = new Busqueda(localizacion,dataInicio,dataFin,numPersoas);
		
		//ordear por nome
		if (opcion==0) {
			List<Hotel> hoteis = new ArrayList<Hotel>();
			hoteis = (List<Hotel>) getSession().createQuery("SELECT h FROM Habitacion h WHERE h.idhotel.localizacion = :localizacion" +
		        	" order by h.idhotel.nome").
		        	setParameter("localizacion",localizacion).list();
			busqueda.setHoteis(hoteis);
			return novaBusqueda;
		}
		
		//ordear por prezo
		if (opcion==1) {
			List<Hotel> hoteis = new ArrayList<Hotel>();
			hoteis = (List<Hotel>) getSession().createQuery("SELECT h FROM Habitacion h WHERE h.idhotel.localizacion = :localizacion" +
		        	" order by h.prezo").
		        	setParameter("localizacion",localizacion).list();
			busqueda.setHoteis(hoteis);
			return novaBusqueda;
		}
		
		//ordear por Categoria
		if (opcion==2) {
			List<Hotel> hoteis = new ArrayList<Hotel>();
			hoteis = (List<Hotel>) getSession().createQuery("SELECT h FROM Habitacion h WHERE h.idhotel.localizacion = :localizacion" +
	        	" order by h.idhotel.categoria").
		        	setParameter("localizacion",localizacion).list();
			busqueda.setHoteis(hoteis);
			return novaBusqueda;
		}
	}
	
	

}
