package main.java.model.service.hotel;

import java.util.Calendar;

import main.java.model.db.hotel.Hotel;

public interface HotelService {

	/**
	 * ONLY FOR TESTING
	 * 
	 * @param nome
	 * @param Localizacion
	 * @param descricion
	 * @param categoria
	 * @param temporadaInicio
	 * @param temporadaFin
	 * @param servizos
	 * @param telefono
	 * @return
	 */
	public Hotel addHotel(String nome, String Localizacion, String descricion,
			int categoria, Calendar temporadaInicio, Calendar temporadaFin,
			String servizos, String telefono);

	/**
	 * ONLY FOR TESTING
	 * 
	 * @param id
	 */
	public void delHotel(Long id);

}
