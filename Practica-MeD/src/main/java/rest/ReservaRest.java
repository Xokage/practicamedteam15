package main.java.rest;

import main.java.model.db.reserva.Reserva;
import main.java.model.service.reserva.ReservaService;
import main.java.model.service.reserva.ReservaServiceImpl;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.Calendar;


@Path("reserva")
public class ReservaRest {

	ReservaService reservaService = new ReservaServiceImpl();
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response reservaXML(String nomeCliente, String DniCliente, Calendar dataEntrada, Calendar dataSaida, Long idHotel, Long idHabitacion) {
		
		Reserva reserva = reservaService.reservar(nomeCliente, DniCliente, dataEntrada, dataSaida, idHotel, idHabitacion);
		String output = reserva.toString();
		return Response.status(200).entity(output).build();

	}
	
}
