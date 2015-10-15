package test.java.buscarHotel;

import static org.junit.Assert.*;

import java.util.Calendar;

import main.java.model.db.habitacion.Habitacion;
import main.java.model.db.habitacion.SqlHabitacionDao;
import main.java.model.db.hotel.Hotel;
import main.java.model.db.hotel.SqlHotelDao;
import main.java.model.service.hotel.HotelService;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

public class BuscarHotel extends JerseyTest {

	private HotelService hs;
	private SqlHotelDao	hd;
	private SqlHabitacionDao habd;
	
	
	@Test
	public void testHabitacionDobleVimianzo()  {
		//Inicializacion dos datos
		
	    Long id = null;
	    String nome = "Hotel Soneira";
	    String localizacion = "Vimianzo";
	    String descricion = "";
	    int categoria = 5;
	    Calendar temporadaInicio = Calendar.getInstance();
	    Calendar temporadaFin = Calendar.getInstance();
	    temporadaFin.add(Calendar.MONTH, 3);
	    String servizos = "wifi,piscina,cocktail";
	    String telefono = "618382641";
		
		final Hotel newHotel = hs.addHotel(nome, localizacion, descricion, categoria, 
				temporadaInicio, temporadaFin, servizos, telefono);
		
	    Long idHab;
	    Float prezoHab = 300f;
	    int numCamasHab = 2;
	    String servizosHab = "jacuzzi";
	    boolean estadoHab = false;
		
		final Habitacion newHabitacion = habd.addHabitacion(newHotel, prezoHab, numCamasHab, servizosHab);
		
		//Parametros comuns aos tests
		String testExpected = "<?xml version=\"1.0\"?>" + 
		"<hoteis>" +
			"<hotel>" + 
				"<id>" + newHotel.getId() + "</id>" + 
				"<nome>" + nome + "</nome>" +
			"</hotel>" + // O que esperamos que devolva o test.
	    "</hoteis>";
		//Parametros individuais
		String loc = "Vimianzo"; 	//Localizacion do hotel
		Integer pPH = 2;			//Persoas por habitacion

		
		//Test
		final String testResult = target("busqueda").
				queryParam("localizacion",loc).queryParam("numPersoas",pPH).
				request().get(String.class);
		
		//Limpar Datos
		habd.delHabitacion(newHabitacion.getId());
		hd.delHotel(newHotel.getId());

		//Resultado
		assertEquals(testResult,testExpected);
	}

}
