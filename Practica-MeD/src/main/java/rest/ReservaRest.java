package main.java.rest;

import main.java.model.db.reserva.Reserva;
import main.java.model.service.reserva.ReservaService;
import main.java.model.service.reserva.ReservaServiceImpl;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.Calendar;

import static main.java.util.ModelConstants.BUSQUEDA_DATA_SOURCE;
import static org.junit.Assert.*;

import java.util.Calendar;

import javax.sql.DataSource;
import javax.ws.rs.core.Application;

import main.java.model.db.habitacion.Habitacion;
import main.java.model.db.hotel.Hotel;
import main.java.model.service.habitacion.HabitacionService;
import main.java.model.service.habitacion.HabitacionServiceImpl;
import main.java.model.service.hotel.HotelService;
import main.java.model.service.hotel.HotelServiceImpl;
import main.java.rest.BusquedaRest;
import main.java.util.DataSourceLocator;
import main.java.util.SimpleDataSource;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import main.java.model.db.busqueda.Busqueda;
import main.java.model.db.filtro.Filtro;
import main.java.model.db.habitacion.Habitacion;
import main.java.model.db.hotel.Hotel;
import main.java.model.service.busqueda.BusquedaService;
import main.java.model.service.busqueda.BusquedaServiceImpl;
import main.java.model.service.filtro.FiltroService;
import main.java.model.service.filtro.FiltroServiceImpl;
import main.java.util.Pair;

@Path("reserva")
public class ReservaRest {

	ReservaService reservaService = new ReservaServiceImpl();
	SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("add")
	public String reservaXML(@Context UriInfo ui) {

		MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
		String nomeCliente = queryParams.getFirst("nomeCliente");
		String dniCliente = queryParams.getFirst("dniCliente");
		Calendar dataEntrada = Calendar.getInstance();
		Calendar dataSaida = Calendar.getInstance();
		Long idHotel = Long.parseLong(queryParams.getFirst("idHotel"));
		Long idHabitacion = Long
				.parseLong(queryParams.getFirst("idHabitacion"));

		if (queryParams.getFirst("dataEntrada") != null) {
			try {
				dataEntrada.setTime(sdf.parse(queryParams
						.getFirst("dataEntrada")));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else
			dataEntrada = null;

		if (queryParams.getFirst("dataSaida") != null) {
			try {
				dataSaida.setTime(sdf.parse(queryParams.getFirst("dataSaida")));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else
			dataSaida = null;

		Reserva reserva = reservaService.reservar(nomeCliente, dniCliente,
				dataEntrada, dataSaida, idHotel, idHabitacion);

		String result = "<?xml version=\"1.0\"?>" 
				+ "<reserva>"
					+ "<id>" + reserva.getId() + "</id>" 
					+ "<nomeCliente>" + reserva.getNomeCliente() + "</nomeCliente>"
					+ "<dniCliente>" + reserva.getDniCliente() + "</dniCliente>" 
					+ "<dataEntrada>" + sdf.format(reserva.getDataEntrada().getTime()) + "</dataEntrada>"
					+ "<dataSaida>" + sdf.format(reserva.getDataSaida().getTime()) + "</dataSaida>"
					+ "<idHotel>" + reserva.getIdHotel() + "</idHotel>"
					+ "<idHabitacion>" + reserva.getIdHabitacion() + "</idHabitacion>"
				+ "</reserva>"; 

		return result;
		
	}
}
