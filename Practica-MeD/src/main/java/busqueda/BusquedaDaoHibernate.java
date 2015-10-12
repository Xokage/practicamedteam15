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
		hoteis = (List<Hotel>) getSession().createQuery("SELECT h FROM Hotel h WHERE b.localizacion = :localizacion").
	        	setParameter("localizacion",localizacion).list();
		busqueda.setHoteis(hoteis);
		return busqueda;
	}

	@Override
	public Busqueda ordear(Busqueda busqueda, int opcion) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
