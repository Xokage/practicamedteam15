package main.java.model.service.busqueda;

import static main.java.util.ModelConstants.BUSQUEDA_DATA_SOURCE;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import javax.sql.DataSource;

import main.java.model.db.busqueda.Busqueda;
import main.java.model.db.busqueda.SqlBusquedaDao;
import main.java.model.db.busqueda.SqlBusquedaDaoFactory;
import main.java.model.db.filtro.Filtro;
import main.java.util.DataSourceLocator;

public class BusquedaServiceImpl implements BusquedaService {

	private DataSource dataSource;
	SqlBusquedaDao busquedaDao = null;

	public BusquedaServiceImpl() {
		dataSource = DataSourceLocator.getDataSource(BUSQUEDA_DATA_SOURCE);
		busquedaDao = SqlBusquedaDaoFactory.getDao();
	}

	@Override
	public Busqueda realizarBusqueda(String localizacion, Calendar dataInicio,
			Calendar dataFin, int numPersoas, int opcion, boolean desc,
			List<Filtro> filtros) {

		try (Connection connection = dataSource.getConnection()) {

			try {

				/* Prepare connection. */
				connection
						.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
				connection.setAutoCommit(false);

				/* Do work. */
				Busqueda busqueda = busquedaDao.realizarBusqueda(connection,
						localizacion, dataInicio, dataFin, numPersoas, opcion,
						desc, filtros);

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
	
	@Override
	public Busqueda realizarBusqueda(String localizacion, Float prezoMax, Float prezoMin, Calendar dataInicio,
			Calendar dataFin, int numPersoas, int opcion, boolean desc,
			List<Filtro> filtros) {

		try (Connection connection = dataSource.getConnection()) {

			try {

				/* Prepare connection. */
				connection
						.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
				connection.setAutoCommit(false);

				/* Do work. */
				Busqueda busqueda = busquedaDao.realizarBusqueda(connection,
						localizacion, prezoMax, prezoMin, dataInicio, dataFin, numPersoas, opcion,
						desc, filtros);

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
