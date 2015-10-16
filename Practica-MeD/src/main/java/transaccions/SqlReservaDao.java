package main.java.transaccions;

import java.sql.Connection;
import java.util.Calendar;
import java.util.Date;
public interface SqlReservaDao {
	

	public Reserva findReserva(Connection connection, Long idReserva);

	public Reserva addReserva(Connection connection, Calendar dataReserva, String nomeCliente, String DniCliente, 
    		Calendar dataEntrada, Calendar dataSaida, Long idHotel, Long idHabitacion);

	public void delReserva(Connection connection, Long idReserva);

}
