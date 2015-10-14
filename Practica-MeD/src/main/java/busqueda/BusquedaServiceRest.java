package main.java.busqueda;

import static main.java.util.ModelConstants.BUSQUEDA_DATA_SOURCE;
import java.sql.Connection;
import java.util.Calendar;

import javax.sql.DataSource;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("busqueda")
public class BusquedaServiceRest implements BusquedaService {
	
	private DataSource dataSource;
	SqlBusquedaDao busquedaDao = null;
	
	
	public BusquedaServiceRest() {
        dataSource = DataSourceLocator.getDataSource(BUSQUEDA_DATA_SOURCE);
        busquedaDao = SqlBusquedaDaoFactory.getDao();
    }
	
	@GET
	@Produces(MediaType.TEXT_XML)
	public String buscarXML(@QueryParam("localizacion") String localizacion,
	@QueryParam("dataInicio") Calendar dataInicio,
	@QueryParam("dataFin") Calendar dataFin,
	@DefaultValue("1") @QueryParam("numPersoas") int numPersoas,@DefaultValue("0") @QueryParam("opcion") int opcion) {
		
		Busqueda busqueda = busquedaDao.realizarBusqueda(connection, localizacion, dataInicio, dataFin, numPersoas, opcion)
		return 
		
	}
	
	
	@Override
	public Busqueda realizarBusqueda(Connection connection,
			String localizacion, Calendar dataInicio, Calendar dataFin,
			int numPersoas, int opcion) {
		// TODO Auto-generated method stub
		return null;
	}
}
