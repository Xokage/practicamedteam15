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

public class BuscarOrdenado extends JerseyTest {

	private HotelService hs;
	private SqlHotelDao	hd;
	private SqlHabitacionDao habd;

	@Test
	public void testBuscarOrdeadoPorPrezo()  {
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
		
	    Long idHab;
	    Float prezoHab = 300f;
	    int numCamasHab = 2;
	    String servizosHab = "jacuzzi";
	    boolean estadoHab = false;
		
		final Habitacion newHabitacion = habd.addHabitacion(newHotel, prezoHab, numCamasHab, servizosHab);
		
	    Long id2 = null;
	    String nome2 = "Hotel Soneira";
	    String localizacion2 = "Vimianzo";
	    String descricion2 = "";
	    int categoria2 = 5;
	    Calendar temporadaInicio2 = Calendar.getInstance();
	    Calendar temporadaFin2 = Calendar.getInstance();
	    temporadaFin2.add(Calendar.MONTH, 3);
	    String servizos2 = "wifi,piscina,cocktail";
	    String telefono2 = "618382641";
		
		final Hotel newHotel2 = hd.addHotel(nome2, localizacion2, descricion2, categoria2, 
				temporadaInicio2, temporadaFin2, servizos2, telefono2);
	    
		Long idHab2;
	    Float prezoHab2 = 400f;
	    int numCamasHab2 = 2;
	    String servizosHab2 = "jacuzzi";
	    boolean estadoHab2 = false;
		
		final Habitacion newHabitacion2 = habd.addHabitacion(newHotel2, prezoHab2, numCamasHab2, servizosHab2);
		
		//Parametros comuns aos tests
		String testExpected = "<?xml version=\"1.0\"?>" + 
		"<hoteis>" +
			"<hotel>" + 
				"<id>" + newHotel2.getId() + "</id>" + 
				"<nome>" + nome2 + "</nome>" +
				"<prezo>" + prezoHab2 + "</prezo>" +
			"</hotel>" + // O que esperamos que devolva o test.
			"<hotel>" + 
				"<id>" + newHotel.getId() + "</id>" + 
				"<nome>" + nome + "</nome>" +
				"<prezo>" + prezoHab + "</prezo>" +
			"</hotel>" + // O que esperamos que devolva o test.
		"</hoteis>";
		//Parametros individuais
		String loc = "Vimianzo"; 	//Localizacion do hotel
		Integer pPH = 2;			//Persoas por habitacion

		
		//Test
		final String testResult = target("busqueda").
				queryParam("localizacion",loc).queryParam("numPersoas",pPH).queryParam("opcion",1).
				request().get(String.class);
		
		//Limpar Datos
		habd.delHabitacion(newHabitacion.getId());
		habd.delHabitacion(newHabitacion2.getId());
		hd.delHotel(newHotel.getId());
		hd.delHotel(newHotel2.getId());

		//Resultado
		assertEquals(testResult,testExpected);
	}

}
