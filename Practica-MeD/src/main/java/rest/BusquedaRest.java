package main.java.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import main.java.model.db.busqueda.Busqueda;
import main.java.model.db.hotel.Hotel;
import main.java.model.service.busqueda.BusquedaService;
import main.java.model.service.busqueda.BusquedaServiceImpl;

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
		} else
			dataInicio = null;
		Calendar dataFin = Calendar.getInstance();

		if (queryParams.getFirst("dataFin") != null) {
			try {
				dataFin.setTime(sdf.parse(queryParams.getFirst("dataFin")));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else
			dataFin = null;

		int numPersoas = 1;
		if (queryParams.getFirst("numPersoas") != null) {
			numPersoas = Integer.parseInt(queryParams.getFirst("numPersoas"));
		}
		int opcion = 0;
		if (queryParams.getFirst("opcion") != null) {
			opcion = Integer.parseInt(queryParams.getFirst("opcion"));
		}
		boolean desc = false;
		if (queryParams.getFirst("desc") != null) {
			desc = Boolean.parseBoolean((queryParams.getFirst("desc")));
		}

		Busqueda busqueda = busquedaService.realizarBusqueda(localizacion,
				dataInicio, dataFin, numPersoas, opcion, desc);
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
