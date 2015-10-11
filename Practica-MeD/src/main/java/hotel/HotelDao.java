package main.java.hotel;

import java.util.Calendar;

public interface HotelDao {
	
	//ONLY FOR TESTING
	public Hotel getHotel(Long id);
	public Hotel addHotel(String nome, String Localizacion, String descricion,
			int categoria, Calendar temporadaInicio, Calendar temporadaFin,
			String servizos, String telefono);
	public void delHotel(Long id);
	
}
