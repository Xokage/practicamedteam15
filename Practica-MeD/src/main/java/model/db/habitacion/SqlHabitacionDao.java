package main.java.model.db.habitacion;

import java.sql.Connection;
import java.util.List;

public interface SqlHabitacionDao {
	// ONLY FOR TESTING
	public Habitacion getHabitacion(Connection connection, Long id);

	public Habitacion addHabitacion(Connection connection, Long idHotel,
			Float prezo, int numCamas);

	public void delHabitacion(Connection connection, Long id);

	// END TESTING

	/**
	 * Busca habitacions dun hotel determinado.
	 * 
	 * @param connection
	 *            Conexión á base de datos.
	 * @param idHotel
	 *            Id do hotel sobre o que se van a buscar as habitacións.
	 * @return
	 */
	public List<Habitacion> findHabitacion(Connection connection, Long idHotel);

}
