package test.java.buscarHotel;

import static org.junit.Assert.*;

import java.util.Calendar;

import main.java.model.db.habitacion.Habitacion;
import main.java.model.db.hotel.Hotel;
import main.java.model.service.habitacion.HabitacionService;
import main.java.model.service.habitacion.HabitacionServiceImpl;
import main.java.model.service.hotel.HotelService;
import main.java.model.service.hotel.HotelServiceImpl;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

public class BuscarOrdenado extends JerseyTest {

	private HotelService hs = new HotelServiceImpl();
	private HabitacionService has = new HabitacionServiceImpl();

	@Test
	public void testBuscarOrdeadoPorPrezo()  {
		//Inicializacion dos datos
		
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
		
	    Float prezoHab = 300f;
	    int numCamasHab = 2;
		
		final Habitacion newHabitacion = has.addHabitacion(prezoHab, numCamasHab, newHotel.getId());
		
	    String nome2 = "Hotel Soneira";
	    String localizacion2 = "Vimianzo";
	    String descricion2 = "";
	    int categoria2 = 5;
	    Calendar temporadaInicio2 = Calendar.getInstance();
	    Calendar temporadaFin2 = Calendar.getInstance();
	    temporadaFin2.add(Calendar.MONTH, 3);
	    String servizos2 = "wifi,piscina,cocktail";
	    String telefono2 = "618382641";
		
		final Hotel newHotel2 = hs.addHotel(nome2, localizacion2, descricion2, categoria2, 
				temporadaInicio2, temporadaFin2, servizos2, telefono2);
	    
	    Float prezoHab2 = 400f;
	    int numCamasHab2 = 2;
		
		final Habitacion newHabitacion2 = has.addHabitacion(prezoHab2, numCamasHab2, newHotel2.getId());
		
		//Parametros comuns aos tests
		String testExpected = "<?xml version=\"1.0\"?>" + 
		"<hoteis>" +
			"<hotel>" + 
				"<id>" + newHotel2.getId() + "</id>" + 
				"<nome>" + nome2 + "</nome>" +
				"<categoria>" + categoria2 + "</categoria>" +
				"<habitacion>" +
					"<prezo>" + prezoHab2 + "</prezo>" +
				"</habitacion>" +
			"</hotel>" + // O que esperamos que devolva o test.
			"<hotel>" + 
				"<id>" + newHotel.getId() + "</id>" + 
				"<nome>" + nome + "</nome>" +
				"<categoria>" + categoria + "</categoria>" +
				"<habitacion>" +
					"<prezo>" + prezoHab + "</prezo>" +
				"</habitacion>" +
			"</hotel>" + // O que esperamos que devolva o test.
		"</hoteis>";
		//Parametros individuais
		String loc = "Vimianzo"; 	//Localizacion do hotel
		Integer pPH = 2;			//Persoas por habitacion

		
		//Test
		final String testResult = target("busqueda").
				queryParam("destino",loc).queryParam("numPersoas",pPH).queryParam("opcion",1).
				request().get(String.class);
		
		//Limpar Datos
		has.delHabitacion(newHabitacion.getId());
		has.delHabitacion(newHabitacion2.getId());
		hs.delHotel(newHotel.getId());
		hs.delHotel(newHotel2.getId());

		//Resultado
		assertEquals(testResult,testExpected);
	}

}
