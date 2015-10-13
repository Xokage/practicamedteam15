package main.java.hotel;

import java.sql.Connection;
import java.util.Calendar;
import java.util.List;

public interface SqlHabitacionDao {
	//ONLY FOR TESTING
	public Habitacion getHabitacion(Connection connection, Long id);
	public Habitacion addHabitacion(Connection connection, Hotel hotel, Float prezo, int numCamas,
			String servizos);
	public void delHabitacion(Connection connection, Long id);

	//END TESTING
	
	/**
	 * Busca habitacions dun hotel determinado.
	 * @param connection
	 * @param idHotel
	 * @return
	 */
	public List<Habitacion> findHabitacion(Connection connection, Long idHotel);
	
}
