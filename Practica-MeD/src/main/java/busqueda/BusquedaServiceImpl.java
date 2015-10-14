package main.java.busqueda;

import static main.java.util.ModelConstants.BUSQUEDA_DATA_SOURCE;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;

import javax.annotation.Resource;
import javax.sql.DataSource;

import main.java.util.DataSourceLocator;

public class BusquedaServiceImpl implements BusquedaService {
	
	
	private DataSource dataSource;
	SqlBusquedaDao busquedaDao = null;
	
	public BusquedaServiceImpl() {
        dataSource = DataSourceLocator.getDataSource(BUSQUEDA_DATA_SOURCE);
        busquedaDao = SqlBusquedaDaoFactory.getDao();
    }
	
	@Override
	public Busqueda realizarBusqueda(String localizacion, Calendar dataInicio, Calendar dataFin,
			int numPersoas, int opcion) {
		
		try (Connection connection = dataSource.getConnection()) {

            try {

                /* Prepare connection. */
                connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
                connection.setAutoCommit(false);

                /* Do work. */
                Busqueda busqueda =  busquedaDao.realizarBusqueda(connection, localizacion, dataInicio, dataFin, numPersoas, opcion);

                /* Commit. */
                connection.commit();

                return busqueda;

            } catch (SQLException e) {
                connection.rollback();
                throw new RuntimeException(e);
            } catch (RuntimeException | Error e) {
                connection.rollback();
                throw e;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
		
	}

	
}
