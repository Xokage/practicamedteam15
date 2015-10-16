package main.java.model.service.reserva;

import static main.java.util.ModelConstants.BUSQUEDA_DATA_SOURCE;

import java.util.Calendar;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import main.java.model.db.reserva.Reserva;
import main.java.model.db.reserva.SqlReservaDao;
import main.java.model.db.reserva.SqlReservaDaoFactory;
import main.java.util.DataSourceLocator;

public class ReservaServiceImpl implements ReservaService {

	private DataSource dataSource;
	SqlReservaDao reservaDao = null;

	public ReservaServiceImpl() { 
		dataSource = DataSourceLocator.getDataSource(BUSQUEDA_DATA_SOURCE);
		reservaDao = SqlReservaDaoFactory.getDao();
	}

	public Reserva reservar(String nomeCliente, String DniCliente,
			Calendar dataEntrada, Calendar dataSaida, Long idHotel,
			Long idHabitacion) {
		try (Connection connection = dataSource.getConnection()) {

			try {

				/* Prepare connection. */
				connection
						.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
				connection.setAutoCommit(false);

				/* Do work. */
				Calendar dataReserva = Calendar.getInstance();
				Reserva reserva = reservaDao.addReserva(connection,
						dataReserva, nomeCliente, DniCliente, dataEntrada,
						dataSaida, idHotel, idHabitacion);

				/* Commit. */
				connection.commit();

				return reserva;

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
	
	public Reserva findReservaByParameters(String nomeCliente, String DniCliente, 
    		Calendar dataEntrada, Calendar dataSaida, Long idHotel, Long idHabitacion) {
		try (Connection connection = dataSource.getConnection()) {

			try {

				/* Prepare connection. */
				connection
						.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
				connection.setAutoCommit(false);

				return reservaDao.findReservaByParameters(connection, nomeCliente, DniCliente, dataEntrada, dataSaida, idHotel, idHabitacion);

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