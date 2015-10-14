package main.java.model.db.habitacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Jdbc3CcSqlHabitacionDao extends AbstractSqlHabitacionDao {

	@Override
	public Habitacion addHabitacion(Connection connection, Long idHotel,
			Float prezo, int numCamas) {

		/* Create "queryString". */
		String queryString = "INSERT INTO Habitacion"
				+ " (prezo, numCamas, idHotel)" + " VALUES (?, ?, ?)";

		try (PreparedStatement preparedStatement = connection.prepareStatement(
				queryString, Statement.RETURN_GENERATED_KEYS)) {

			/* Fill "preparedStatement". */
			int i = 1;
			preparedStatement.setFloat(i++, prezo);
			preparedStatement.setInt(i++, numCamas);
			preparedStatement.setLong(i++, idHotel);

			/* Execute query. */
			preparedStatement.executeUpdate();

			/* Get generated identifier. */
			ResultSet resultSet = preparedStatement.getGeneratedKeys();

			if (!resultSet.next()) {
				throw new SQLException(
						"JDBC driver did not return generated key.");
			}

			// Obtención del ID del Evento
			Long habitacionId = resultSet.getLong(1);

			/* Return event. */
			return new Habitacion(habitacionId, prezo, numCamas, idHotel);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
