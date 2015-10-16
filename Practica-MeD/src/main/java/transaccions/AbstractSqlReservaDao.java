package main.java.transaccions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public abstract class AbstractSqlReservaDao implements SqlReservaDao {

	public Reserva findReserva(Connection connection, Long idReserva) {

		/* Create "queryString". */
		String queryString = "SELECT id, dataReserva, nomeCliente, DniCliente, dataEntrada, dataSaida, idHotel, idHabitacion, FROM Reserva "
				+ "WHERE id = ?";

		try (PreparedStatement preparedStatement = connection
				.prepareStatement(queryString)) {

			/* Fill "preparedStatement". */
			int i = 1;
			preparedStatement.setLong(i++, idReserva);

			/* Execute query. */
			ResultSet resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				// If you find nothing return null
				return null;
			}

			/* Get results. */
			i = 1;
			Long newId = resultSet.getLong(i++);
			Calendar newDataReserva = Calendar.getInstance();
			newDataReserva.setTime(resultSet.getTimestamp(i++));
			String newNomeCliente = resultSet.getString(i++);
			String newDniCliente = resultSet.getString(i++);
			Calendar newDataEntrada = Calendar.getInstance();
			newDataEntrada.setTime(resultSet.getTimestamp(i++));
			Calendar newDataSaida = Calendar.getInstance();
			newDataSaida.setTime(resultSet.getTimestamp(i++));
			Long newIdHotel = resultSet.getLong(i++);
			Long newIdHabitacion = resultSet.getLong(i++);

			return new Reserva(newId, newDataReserva, newNomeCliente,
					newDniCliente, newDataEntrada, newDataSaida, newIdHotel,
					newIdHabitacion);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void delReserva(Connection connection, Long id) {

		/* Create "queryString". */
		String queryString = "DELETE FROM Reserva WHERE id = ?";

		try (PreparedStatement preparedStatement = connection
				.prepareStatement(queryString)) {

			/* Fill "preparedStatement". */
			int i = 1;
			preparedStatement.setLong(i++, id);

			/* Execute query. */
			int removedRows = preparedStatement.executeUpdate();

			if (removedRows == 0) {
				System.err.println("Reserva id:" + id + " non atopada.");
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

}
