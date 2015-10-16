package main.java.transaccions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import main.java.model.db.hotel.AbstractSqlHotelDao;
import main.java.model.db.hotel.Hotel;

public class Jdbc3CcSqlReservaDao extends AbstractSqlReservaDao{

	@Override
	public Reserva addReserva(Connection connection, Calendar dataReserva, String nomeCliente, String DniCliente, 
    		Calendar dataEntrada, Calendar dataSaida, Long idHotel, Long idHabitacion) {

		/* Create "queryString". */
		String queryString = "INSERT INTO Reserva"
				+ " (dataReserva, nomeCliente, DniCliente, dataEntrada, dataSaida, "
				+ "idHotel, idHabitacion)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?)";

		try (PreparedStatement preparedStatement = connection.prepareStatement(
				queryString, Statement.RETURN_GENERATED_KEYS)) {

			/* Fill "preparedStatement". */
			int i = 1;
			
			preparedStatement.setTimestamp(i++,
					dataReserva != null ? new Timestamp(dataReserva
							.getTime().getTime()) : null);
			preparedStatement.setString(i++, nomeCliente);
			preparedStatement.setString(i++, DniCliente);
			preparedStatement.setTimestamp(i++,
					dataEntrada != null ? new Timestamp(dataEntrada
							.getTime().getTime()) : null);
			preparedStatement.setTimestamp(i++,
					dataSaida != null ? new Timestamp(dataSaida.getTime()
							.getTime()) : null);

			preparedStatement.setLong(i++, idHotel);
			preparedStatement.setLong(i++, idHabitacion);

			/* Execute query. */
			preparedStatement.executeUpdate();

			/* Get generated identifier. */
			ResultSet resultSet = preparedStatement.getGeneratedKeys();

			if (!resultSet.next()) {
				throw new SQLException(
						"JDBC driver did not return generated key.");
			}

			// Obtención do ID do hotel
			Long id = resultSet.getLong(1);

			/* Return event. */
			return new Reserva(id, dataReserva, nomeCliente, DniCliente,
					dataEntrada, dataSaida, idHotel, idHabitacion);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
