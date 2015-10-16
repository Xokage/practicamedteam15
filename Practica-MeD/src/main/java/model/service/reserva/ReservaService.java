package main.java.model.service.reserva;

import java.util.Calendar;

import main.java.model.db.reserva.Reserva;

public interface ReservaService {

		public Reserva reservar(Calendar dataReserva, String nomeCliente, String DniCliente, 
    		Calendar dataEntrada, Calendar dataSaida, Long idHotel, Long idHabitacion);

}
