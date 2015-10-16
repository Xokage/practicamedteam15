package main.java.model.db.reserva;

import java.sql.Connection;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public interface SqlReservaDao {
	

	public Reserva getReserva(Connection connection, Long idReserva);
	
	public List<Reserva> findReservaByClient(Connection connection, String nomeCliente);
	
	public Reserva findReservaByDates(Connection connection, Calendar dataEntrada, Calendar dataSaida, Long idHabitacion);

	public Reserva addReserva(Connection connection, Calendar dataReserva, String nomeCliente, String DniCliente, 
    		Calendar dataEntrada, Calendar dataSaida, Long idHotel, Long idHabitacion);

	public void delReserva(Connection connection, Long idReserva);

}
