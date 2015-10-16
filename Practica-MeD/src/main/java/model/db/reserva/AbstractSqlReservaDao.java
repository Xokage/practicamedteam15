package main.java.model.db.reserva;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import main.java.model.db.reserva.Reserva;

public abstract class AbstractSqlReservaDao implements SqlReservaDao {

	public Reserva getReserva(Connection connection, Long idReserva) {

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

	public Reserva findReservaByParameters(Connection connection,
			String nomeCliente, String DniCliente, Calendar dataEntrada,
			Calendar dataSaida, Long idHotel, Long idHabitacion) {

		/* Create "queryString". */
		String queryString = "SELECT id, dataReserva, nomeCliente, DniCliente, dataEntrada, dataSaida, idHotel, idHabitacion FROM Reserva "
				+ "WHERE nomeCliente = ? AND DniCliente = ? AND idHotel = ? AND idHabitacion = ?";

		if (dataEntrada != null) {
			queryString += " AND dataEntrada = ?";

		}

		if (dataSaida != null) {
			queryString += " AND dataSaida = ?";
		}

		try (PreparedStatement preparedStatement = connection
				.prepareStatement(queryString)) {

			/* Fill "preparedStatement". */
			int i = 1;
			preparedStatement.setString(i++, nomeCliente);
			preparedStatement.setString(i++, DniCliente);
			preparedStatement.setLong(i++, idHotel);
			preparedStatement.setLong(i++, idHabitacion);

			if (dataEntrada != null) {
				preparedStatement.setTimestamp(i++,
						dataEntrada != null ? new Timestamp(dataEntrada
								.getTime().getTime()) : null);

			}

			if (dataSaida != null) {
				preparedStatement.setTimestamp(i++,
						dataSaida != null ? new Timestamp(dataSaida.getTime()
								.getTime()) : null);
			}
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

	public List<Reserva> findReservaByClient(Connection connection,
			String nomeCliente) {

		/* Create "queryString". */
		String queryString = "SELECT id, dataReserva, nomeCliente, DniCliente, dataEntrada, dataSaida, idHotel, idHabitacion, FROM Reserva "
				+ "WHERE nomeCliente = ?";

		try (PreparedStatement preparedStatement = connection
				.prepareStatement(queryString)) {

			/* Fill "preparedStatement". */
			int i = 1;
			preparedStatement.setString(i++, nomeCliente);

			/* Execute query. */
			ResultSet resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				// If you find nothing return null
				return null;
			}

			/* Get results. */

			List<Reserva> reservas = new ArrayList<Reserva>();

			while (resultSet.next()) {

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

				reservas.add(new Reserva(newId, newDataReserva, newNomeCliente,
						newDniCliente, newDataEntrada, newDataSaida,
						newIdHotel, newIdHabitacion));
			}

			return reservas;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Reserva findReservaByDates(Connection connection, 
			Calendar dataEntrada, Calendar dataSaida,Long idHabitacion) {
		/* Create "queryString". */
		String queryString = "SELECT id, dataReserva, nomeCliente, DniCliente, dataEntrada, dataSaida, idHotel, idHabitacion FROM Reserva WHERE idHabitacion = ?";

		if (dataEntrada != null) {
			queryString += " AND dataEntrada = ?";

		}

		if (dataSaida != null) {
			queryString += " AND dataSaida = ?";
		}

		try (PreparedStatement preparedStatement = connection
				.prepareStatement(queryString)) {

			/* Fill "preparedStatement". */
			int i = 1;

			preparedStatement.setLong(i++, idHabitacion);

			if (dataEntrada != null) {
				preparedStatement.setTimestamp(i++,
						dataEntrada != null ? new Timestamp(dataEntrada
								.getTime().getTime()) : null);

			}

			if (dataSaida != null) {
				preparedStatement.setTimestamp(i++,
						dataSaida != null ? new Timestamp(dataSaida.getTime()
								.getTime()) : null);
			}

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
