package main.java.model.service.filtro;

import static main.java.util.ModelConstants.BUSQUEDA_DATA_SOURCE;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import main.java.model.db.filtro.Filtro;
import main.java.model.db.filtro.SqlFiltroDao;
import main.java.model.db.filtro.SqlFiltroDaoFactory;
import main.java.util.DataSourceLocator;

public class FiltroServiceImpl implements FiltroService {

	private DataSource dataSource;
	SqlFiltroDao filtroDao = null;

	public FiltroServiceImpl() {
		dataSource = DataSourceLocator.getDataSource(BUSQUEDA_DATA_SOURCE);
		filtroDao = SqlFiltroDaoFactory.getDao();
	}

	public Filtro findFiltro(String nome) {
		try (Connection connection = dataSource.getConnection()) {

			try {
				Filtro f;

				/* Prepare connection. */
				connection
						.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
				connection.setAutoCommit(false);

				/* Do work. */
				f = filtroDao.findFiltro(connection, nome);

				/* Commit. */
				connection.commit();

				return f;

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
