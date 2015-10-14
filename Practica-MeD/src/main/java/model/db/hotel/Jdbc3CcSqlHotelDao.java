package main.java.model.db.hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;

public class Jdbc3CcSqlHotelDao extends AbstractSqlHotelDao {

	@Override
	public Hotel addHotel(Connection connection, String nome,
			String localizacion, String descricion, int categoria,
			Calendar temporadaInicio, Calendar temporadaFin, String servizos,
			String telefono) {

		/* Create "queryString". */
		String queryString = "INSERT INTO Hotel"
				+ " (nome, localizacion, descricion, categoria, temporadaInicio, "
				+ "temporadaFin, servizos, telefono)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try (PreparedStatement preparedStatement = connection.prepareStatement(
				queryString, Statement.RETURN_GENERATED_KEYS)) {

			/* Fill "preparedStatement". */
			int i = 1;
			preparedStatement.setString(i++, nome);
			preparedStatement.setString(i++, localizacion);
			preparedStatement.setString(i++, descricion);
			preparedStatement.setInt(i++, categoria);
			preparedStatement.setTimestamp(i++,
					temporadaInicio != null ? new Timestamp(temporadaInicio
							.getTime().getTime()) : null);
			preparedStatement.setTimestamp(i++,
					temporadaFin != null ? new Timestamp(temporadaFin.getTime()
							.getTime()) : null);

			preparedStatement.setString(i++, servizos);
			preparedStatement.setString(i++, telefono);

			/* Execute query. */
			preparedStatement.executeUpdate();

			/* Get generated identifier. */
			ResultSet resultSet = preparedStatement.getGeneratedKeys();

			if (!resultSet.next()) {
				throw new SQLException(
						"JDBC driver did not return generated key.");
			}

			// Obtención do ID do hotel
			Long hotelId = resultSet.getLong(1);

			/* Return event. */
			return new Hotel(hotelId, nome, localizacion, descricion,
					categoria, temporadaInicio, temporadaFin, servizos,
					telefono);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
