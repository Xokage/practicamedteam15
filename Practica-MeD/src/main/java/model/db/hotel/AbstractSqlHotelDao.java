package main.java.model.db.hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public abstract class AbstractSqlHotelDao implements SqlHotelDao {

	public Hotel getHotel(Connection connection, Long id) {

		/* Create "queryString". */
		String queryString = "SELECT id, nome, localizacion, descricion, "
				+ "categoria, temporadaInicio, temporadaFin, servizos, "
				+ "telefono FROM Hotel WHERE id = ?";

		try (PreparedStatement preparedStatement = connection
				.prepareStatement(queryString)) {

			/* Fill "preparedStatement". */
			int i = 1;
			preparedStatement.setLong(i++, id);

			/* Execute query. */
			ResultSet resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				// If you find nothing return null
				return null;
			}

			/* Get results. */
			i = 1;
			Long newId = resultSet.getLong(i++);
			String newNome = resultSet.getString(i++);
			String newLocalizacion = resultSet.getString(i++);
			String newDescricion = resultSet.getString(i++);
			Integer newCategoria = resultSet.getInt(i++);
			Calendar newTemporadaInicio = Calendar.getInstance();
			newTemporadaInicio.setTime(resultSet.getTimestamp(i++));
			Calendar newTemporadaFin = Calendar.getInstance();
			newTemporadaFin.setTime(resultSet.getTimestamp(i++));
			String newServizos = resultSet.getString(i++);
			String newTelefono = resultSet.getString(i++);

			return new Hotel(newId, newNome, newLocalizacion, newDescricion,
					newCategoria, newTemporadaInicio, newTemporadaFin,
					newServizos, newTelefono);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Busca un hotel por localizacion ou por nome
	 * 
	 * @param connection
	 * @param localizacion
	 * @return
	 */
	public List<Hotel> findHotel(Connection connection, String localizacion) {

		/* Create "queryString". */
		String queryString = "SELECT id, nome, localizacion, descricion, "
				+ "categoria, temporadaInicio, temporadaFin, servizos, "
				+ "telefono FROM Hotel WHERE (LOWER(localizacion) LIKE LOWER(?))";

		try (PreparedStatement preparedStatement = connection
				.prepareStatement(queryString)) {

			/* Fill "preparedStatement". */
			int i = 1;
			preparedStatement.setString(i++, "%" + localizacion + "%");

			/* Execute query. */
			ResultSet resultSet = preparedStatement.executeQuery();

			/* Get hotels */

			List<Hotel> hoteis = new ArrayList<Hotel>();

			while (resultSet.next()) {

				i = 1;

				Long newId = resultSet.getLong(i++);
				String newNome = resultSet.getString(i++);
				String newLocalizacion = resultSet.getString(i++);
				String newDescricion = resultSet.getString(i++);
				Integer newCategoria = resultSet.getInt(i++);
				Calendar newTemporadaInicio = Calendar.getInstance();
				newTemporadaInicio.setTime(resultSet.getTimestamp(i++));
				Calendar newTemporadaFin = Calendar.getInstance();
				newTemporadaFin.setTime(resultSet.getTimestamp(i++));
				String newServizos = resultSet.getString(i++);
				String newTelefono = resultSet.getString(i++);

				hoteis.add(new Hotel(newId, newNome, newLocalizacion,
						newDescricion, newCategoria, newTemporadaInicio,
						newTemporadaFin, newServizos, newTelefono));

			}

			/* Return hoteis. */
			return hoteis;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void delHotel(Connection connection, Long hotelId) {

		/* Create "queryString". */
		String queryString = "DELETE FROM Hotel WHERE" + " id = ?";

		try (PreparedStatement preparedStatement = connection
				.prepareStatement(queryString)) {

			/* Fill "preparedStatement". */
			int i = 1;
			preparedStatement.setLong(i++, hotelId);

			/* Execute query. */
			int removedRows = preparedStatement.executeUpdate();

			if (removedRows == 0) {
				System.err.println("Hotel id:" + hotelId + " non atopado.");
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

}
