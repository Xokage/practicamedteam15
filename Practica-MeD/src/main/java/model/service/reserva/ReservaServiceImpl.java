package main.java.model.service.reserva;

import static main.java.util.ModelConstants.BUSQUEDA_DATA_SOURCE;
import main.java.model.db.hotel.Hotel;
import main.java.model.db.reserva.Reserva;
import main.java.model.db.reserva.SqlReservaDao;
import main.java.model.db.reserva.SqlReservaDaoFactory;
import main.java.model.service.hotel.Calendar;
import main.java.model.service.hotel.Connection;
import main.java.model.service.hotel.Error;
import main.java.model.service.hotel.RuntimeException;
import main.java.model.service.hotel.SQLException;
import main.java.model.service.hotel.String;
import main.java.util.DataSourceLocator;

public class ReservaServiceImpl implements ReservaService{

	private DataSource dataSource;
	SqlReservaDao reservaDao = null;

	public ReservaServiceImpl() {
		dataSource = DataSourceLocator.getDataSource(BUSQUEDA_DATA_SOURCE);
		reservaDao = SqlReservaDaoFactory.getDao();
	}
	
	public Reserva reservar(Calendar dataReserva, String nomeCliente, String DniCliente, 
    		Calendar dataEntrada, Calendar dataSaida, Long idHotel, Long idHabitacion) {
		try (Connection connection = dataSource.getConnection()) {

			try {

				/* Prepare connection. */
				connection
						.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
				connection.setAutoCommit(false);

				/* Do work. */
				Reserva reserva = reservaDao.addReserva(connection, dataReserva, nomeCliente,
						DniCliente, dataEntrada, dataSaida,idHotel,idHabitacion);

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
