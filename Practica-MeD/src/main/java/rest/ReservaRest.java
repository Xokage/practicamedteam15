package main.java.rest;

import main.java.model.db.reserva.Reserva;
import main.java.model.service.reserva.ReservaService;
import main.java.model.service.reserva.ReservaServiceImpl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.Calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

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

		String result = "<?xml version=\"1.0\"?>" + "<reserva>" + "<id>"
				+ reserva.getId() + "</id>" + "<nomeCliente>"
				+ reserva.getNomeCliente() + "</nomeCliente>" + "<dniCliente>"
				+ reserva.getDniCliente() + "</dniCliente>" + "<dataEntrada>"
				+ sdf.format(reserva.getDataEntrada().getTime())
				+ "</dataEntrada>" + "<dataSaida>"
				+ sdf.format(reserva.getDataSaida().getTime()) + "</dataSaida>"
				+ "<idHotel>" + reserva.getIdHotel() + "</idHotel>"
				+ "<idHabitacion>" + reserva.getIdHabitacion()
				+ "</idHabitacion>" + "</reserva>";

		return result;

	}
}
