package main.java.model.service.hotel;

import static main.java.util.ModelConstants.BUSQUEDA_DATA_SOURCE;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;

import javax.sql.DataSource;

import main.java.model.db.hotel.Hotel;
import main.java.model.db.hotel.SqlHotelDao;
import main.java.model.db.hotel.SqlHotelDaoFactory;
import main.java.util.DataSourceLocator;

public class HotelServiceImpl implements HotelService {

	private DataSource dataSource;
	SqlHotelDao hotelDao = null;

	public HotelServiceImpl() {
		dataSource = DataSourceLocator.getDataSource(BUSQUEDA_DATA_SOURCE);
		hotelDao = SqlHotelDaoFactory.getDao();
	}

	public void delHotel(Long id) {
		try (Connection connection = dataSource.getConnection()) {

			try {

				/* Prepare connection. */
				connection
						.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
				connection.setAutoCommit(false);

				/* Do work. */
				hotelDao.delHotel(connection, id);

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

	public Hotel addHotel(String nome, String localizacion, String descricion,
			int categoria, Calendar temporadaInicio, Calendar temporadaFin,
			String servizos, String telefono) {
		try (Connection connection = dataSource.getConnection()) {

			try {

				/* Prepare connection. */
				connection
						.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
				connection.setAutoCommit(false);

				/* Do work. */
				Hotel hotel = hotelDao.addHotel(connection, nome, localizacion,
						descricion, categoria, temporadaInicio, temporadaFin,
						servizos, telefono);

				/* Commit. */
				connection.commit();

				return hotel;

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
