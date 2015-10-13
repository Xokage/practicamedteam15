package main.java.busqueda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import main.java.hotel.Hotel;

public abstract class AbstractSqlBusquedaDao implements SqlBusquedaDao {

	protected AbstractSqlBusquedaDao() {
	}

	@Override
	public Busqueda buscarHabitacion(Connection connection,
			String localizacion, Calendar dataInicio, Calendar dataFin,
			int numPersoas) {

		String queryString = "SELECT idHotel FROM Hotel WHERE localizacion = ?";
		try (PreparedStatement preparedStatement = connection
				.prepareStatement(queryString)) {

			List<Long> hoteis = new ArrayList<Long>();
			/* Fill "preparedStatement". */
			int i = 1;
			preparedStatement.setString(i++, localizacion);

			/* Execute query. */
			ResultSet resultSet = preparedStatement.executeQuery();

			/*
			 * if (!resultSet.next()) { throw new
			 * InstanceNotFoundException(eventId, Event.class.getName()); }
			 */

			while (resultSet.next()) {

				int j = 1;

				Long k = resultSet.getLong(i++);
				hoteis.add(k);
			}

			String queryString2 = "SELECT prezo, numCamas, estado FROM Habitacion WHERE idhotel IN ?";

			if (dataInicio != null) {
				queryString2 += " AND dataInicio BETWEEN ? AND ?";
			}
			if (dataFin != null) {
				queryString2 += " AND dataFin BETWEEN ? AND ?";
			}
			
			
			
			return null;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public Busqueda ordear(Connection connection, Busqueda busqueda, int opcion) {
		// TODO Auto-generated method stub
		return null;
	}

}
