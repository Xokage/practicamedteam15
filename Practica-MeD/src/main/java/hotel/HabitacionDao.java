package main.java.hotel;

import java.util.Calendar;

public interface HabitacionDao {
	//ONLY FOR TESTING
	public Habitacion getHabitacion(Long id);
	public Habitacion addHabitacion(Hotel hotel, Float prezo, int numCamas,
			String servizos);
	public void delHabitacion(Long id);

	//END TESTING
}
