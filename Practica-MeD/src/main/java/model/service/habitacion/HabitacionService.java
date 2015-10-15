package main.java.model.service.habitacion;

import main.java.model.db.habitacion.Habitacion;

public interface HabitacionService {

	/**
	 * ONLY FOR TESTING
	 * 
	 * @param prezo
	 * @param numCamas
	 * @param idHotel
	 * @return
	 */
	public Habitacion addHabitacion(Float prezo, int numCamas, Long idHotel);

	/**
	 * ONLY FOR TESTING
	 * 
	 * @param id
	 */
	public void delHabitacion(Long id);

}
