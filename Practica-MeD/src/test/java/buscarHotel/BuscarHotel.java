package test.java.buscarHotel;

import static org.junit.Assert.*;

import java.util.Calendar;

import main.java.hotel.Hotel;
import main.java.hotel.HotelDao;
import main.java.hotel.HotelService;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

public class BuscarHotel extends JerseyTest {

	private HotelService hs;
	private HotelDao	hd;
	
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
		
		final Hotel newHotel = hd.addHotel(nome, localizacion, descricion, categoria, 
				temporadaInicio, temporadaFin, servizos, telefono);
		
		//Parametros comuns aos tests
		String testExpected = "<?xml version=\"1.0\"?>" + 
			"<hotel>" + 
				"<id>" + newHotel.getId() + "</id>" + 
				"<nome>" + nome + "</nome>" +
			"</hotel>"; // O que esperamos que devolva o test.
	    
		//Parametros individuais
		String loc = "Vimianzo"; 	//Localizacion do hotel
		Integer pPH = 2;			//Persoas por habitacion

		
		//Test
		final String testResult = target("hotel").
				queryParam("loc",loc).queryParam("pPH",pPH).
				request().get(String.class);
		
		
		//Limpar Datos
		
		hd.delHotel(newHotel.getId());

		//Resultado
		assertEquals(testResult,testExpected);
	}

}
