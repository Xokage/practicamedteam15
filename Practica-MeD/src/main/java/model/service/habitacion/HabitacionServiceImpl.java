package main.java.model.service.habitacion;

import static main.java.util.ModelConstants.BUSQUEDA_DATA_SOURCE;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import main.java.model.db.habitacion.Habitacion;
import main.java.model.db.habitacion.SqlHabitacionDao;
import main.java.model.db.habitacion.SqlHabitacionDaoFactory;
import main.java.util.DataSourceLocator;

public class HabitacionServiceImpl implements HabitacionService {

	private DataSource dataSource;
	SqlHabitacionDao habitacionDao = null;

	public HabitacionServiceImpl() {
		dataSource = DataSourceLocator.getDataSource(BUSQUEDA_DATA_SOURCE);
		habitacionDao = SqlHabitacionDaoFactory.getDao();
	}

	public void delHabitacion(Long id) {
		try (Connection connection = dataSource.getConnection()) {

			try {

				/* Prepare connection. */
				connection
						.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
				connection.setAutoCommit(false);

				/* Do work. */
				habitacionDao.delHabitacion(connection, id);

				/* Commit. */
				connection.commit();

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

	public Habitacion addHabitacion(Float prezo, int numCamas, Long idHotel) {
		try (Connection connection = dataSource.getConnection()) {

			try {

				/* Prepare connection. */
				connection
						.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
				connection.setAutoCommit(false);

				/* Do work. */
				Habitacion habitacion = habitacionDao.addHabitacion(connection,
						idHotel, prezo, numCamas);

				/* Commit. */
				connection.commit();

				return habitacion;

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
