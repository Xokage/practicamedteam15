package test.java.rest;

import static main.java.util.ModelConstants.BUSQUEDA_DATA_SOURCE;
import static org.junit.Assert.*;

import java.util.Calendar;

import javax.sql.DataSource;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import junit.framework.Assert;
import main.java.model.db.habitacion.Habitacion;
import main.java.model.db.hotel.Hotel;
import main.java.model.db.reserva.Reserva;
import main.java.model.service.habitacion.HabitacionService;
import main.java.model.service.habitacion.HabitacionServiceImpl;
import main.java.model.service.hotel.HotelService;
import main.java.model.service.hotel.HotelServiceImpl;
import main.java.model.service.reserva.ReservaService;
import main.java.model.service.reserva.ReservaServiceImpl;
import main.java.rest.BusquedaRest;
import main.java.util.DataSourceLocator;
import main.java.util.SimpleDataSource;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.BeforeClass;
import org.junit.Test;


public class ReservarJerseyTest extends JerseyTest {

	private HotelService hs = new HotelServiceImpl();
	private HabitacionService has = new HabitacionServiceImpl();
	private ReservaService rs = new ReservaServiceImpl();
	
	@BeforeClass
	public static void init() {

		/*
		 * Create a simple data source and add it to "DataSourceLocator" (this
		 * is needed to test "es.udc.ws.app.model.eventservice.EventService"
		 */
		DataSource dataSource = new SimpleDataSource();

		/* Add "dataSource" to "DataSourceLocator". */
		DataSourceLocator.addDataSource(BUSQUEDA_DATA_SOURCE, dataSource);

	}
	
	@Override
    protected Application configure() {
        // Find first available port.
        forceSet(TestProperties.CONTAINER_PORT, "0");
        
        return new ResourceConfig(BusquedaRest.class);
    }
	
	@Test
	public void testReservarHabitacion()  {
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
		
	    String nome2 = "Hotel California";
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
		
		
		//Parametros individuais
		Calendar dataEntrada = Calendar.getInstance();
		dataEntrada.add(Calendar.MONTH, 1);
		Calendar dataSaida = Calendar.getInstance();
		dataEntrada.add(Calendar.MONTH, 2);

	    Reserva reserva = new Reserva();
	    reserva.setDataEntrada(dataEntrada);
	    reserva.setDataSaida(dataSaida);
	    reserva.setDniCliente("12345678C");
	    reserva.setIdHabitacion(newHabitacion2.getId());
	    reserva.setIdHotel(newHotel2.getId());
	    reserva.setNomeCliente("Pepiño");
	    
	    Entity<Reserva> userEntity = Entity.entity(reserva, MediaType.APPLICATION_XML_TYPE);
	    target("reserva/add").request().post(userEntity); //Here we send POST request
	    Reserva r = rs.findReservaByParameters(reserva.getNomeCliente(),
	    		reserva.getDataEntrada(),reserva.getDataSaida(),reserva.getIdHotel(),
	    		reserva.getIdHabitacion());

	    //Limpar Datos
		has.delHabitacion(newHabitacion.getId());
		has.delHabitacion(newHabitacion2.getId());
		hs.delHotel(newHotel.getId());
		hs.delHotel(newHotel2.getId());
	    rs.delReserva(r.getId());

	    assertTrue(reserva.getDataEntrada()==r.getDataEntrada()&&
	    		reserva.getDataReserva()==r.getDataReserva()&&
	    		reserva.getDataSaida()==r.getDataSaida()&&
	    		reserva.getDniCliente()==r.getDniCliente()&&
	    		reserva.getIdHabitacion()==r.getIdHabitacion()&&
	    		reserva.getIdHotel()==r.getIdHotel()&&
	    		reserva.getNomeCliente()==r.getNomeCliente());
	}

}
