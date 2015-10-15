package test.java.buscarHotel;

import static org.junit.Assert.*;

import java.util.Calendar;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.Application;

import main.java.model.db.habitacion.Habitacion;
import main.java.model.db.hotel.Hotel;
import main.java.model.service.habitacion.HabitacionService;
import main.java.model.service.habitacion.HabitacionServiceImpl;
import main.java.model.service.hotel.HotelService;
import main.java.model.service.hotel.HotelServiceImpl;
import main.java.rest.BusquedaRest;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sun.jersey.api.core.ClassNamesResourceConfig;
import com.sun.jersey.spi.container.servlet.WebComponent;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;
import com.sun.jersey.test.framework.spi.container.TestContainerFactory;
import com.sun.jersey.test.framework.spi.container.grizzly2.web.GrizzlyWebTestContainerFactory;




public class BuscarHotel extends JerseyTest {

	private HotelService hs = new HotelServiceImpl();
	private HabitacionService has = new HabitacionServiceImpl();
	

	
    @Override
    public TestContainerFactory getTestContainerFactory() {
        return new GrizzlyWebTestContainerFactory();
    }
 
    @Override
    public WebAppDescriptor configure() {
        return new WebAppDescriptor.Builder()
            	.initParam(WebComponent.RESOURCE_CONFIG_CLASS,
                      ClassNamesResourceConfig.class.getName())
                .initParam(
                      ClassNamesResourceConfig.PROPERTY_CLASSNAMES,
                      BusquedaRest.class.getName() + ";").build();
    }
 
    
    
    
    
	@Test
	public void testHabitacionDobleVimianzo() throws Exception  {
		
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
		
		//Parametros comuns aos tests
		String testExpected = "<?xml version=\"1.0\"?>" + 
		"<hoteis>" +
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
				queryParam("destino",loc).queryParam("numPersoas",pPH).
				request().get(String.class);
		
		//Limpar Datos
		has.delHabitacion(newHabitacion.getId());
		hs.delHotel(newHotel.getId());

		//Resultado
		assertEquals(testResult,testExpected);
	}

}
