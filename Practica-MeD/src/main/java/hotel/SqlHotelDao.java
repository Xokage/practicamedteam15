package main.java.hotel;

import java.sql.Connection;
import java.util.Calendar;
import java.util.List;

public interface SqlHotelDao {
	
	//ONLY FOR TESTING
	public Hotel getHotel(Connection connection, Long id);
	public Hotel addHotel(Connection connection, String nome, String Localizacion, String descricion,
			int categoria, Calendar temporadaInicio, Calendar temporadaFin,
			String servizos, String telefono);
	public void delHotel(Connection connection, Long id);
	
	//END TESTING

	/** 
	 * Busca un hotel por localizacion ou por nome
	 * @param connection
	 * @param localizacion
	 * @return
	 */
	public List<Hotel> findHotel(Connection connection, String localizacion);
	
}
