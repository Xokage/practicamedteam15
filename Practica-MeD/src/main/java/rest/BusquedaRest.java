package main.java.rest;

import static main.java.util.ModelConstants.BUSQUEDA_DATA_SOURCE;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.sql.DataSource;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import main.java.busqueda.Busqueda;
import main.java.busqueda.BusquedaService;
import main.java.busqueda.BusquedaServiceImpl;
import main.java.busqueda.SqlBusquedaDao;
import main.java.busqueda.SqlBusquedaDaoFactory;
import main.java.hotel.Hotel;

@Path("busqueda")
public class BusquedaRest {

	BusquedaService busquedaService = new BusquedaServiceImpl();
	SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String buscarXML(@Context UriInfo ui) {
		MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
		String localizacion = queryParams.getFirst("localizacion");
		Calendar dataInicio = Calendar.getInstance();
		if (queryParams.getFirst("dataInicio") != null) {
			try {
				dataInicio
						.setTime(sdf.parse(queryParams.getFirst("dataInicio")));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else dataInicio = null;
		Calendar dataFin = Calendar.getInstance();

		if (queryParams.getFirst("dataFin") != null) {
			try {
				dataFin.setTime(sdf.parse(queryParams.getFirst("dataFin")));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else dataFin = null;
		
		int numPersoas = 1;
		if(queryParams.getFirst("numPersoas")!=null){
			numPersoas = Integer.parseInt(queryParams.getFirst("numPersoas"));
		}
		int opcion = 0; 
		if(queryParams.getFirst("opcion")!=null){
			numPersoas = Integer.parseInt(queryParams.getFirst("opcion"));
		}

		Busqueda busqueda = busquedaService.realizarBusqueda(localizacion,
				dataInicio, dataFin, numPersoas, opcion);
		String result = "<?xml version=\"1.0\"?>" + "<hoteis>";
		for (Hotel h : busqueda.getHoteis()) {
			result = result + "<hotel>" + "<id>" + h.getId() + "</id>"
					+ "<nome>" + h.getNome() + "</nome>" + "</hotel>"; // O que
																		// esperamos
																		// que
																		// devolva
																		// o
																		// test.

		}
		result += "</hoteis>";

		return result;

	}

}
