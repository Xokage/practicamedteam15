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

	public Busqueda realizarBusqueda(Connection connection,
			String localizacion, Calendar dataInicio, Calendar dataFin,
			int numPersoas, int opcion) {

		String queryString = "SELECT id, nome, localizacion, descricion, "
				+ "categoria, temporadaInicio, temporadaFin, servizos, "
				+ "telefono FROM Hotel WHERE (LOWER(localizacion) LIKE LOWER(?))";

		// ordear por nome
		if (opcion == 0) {
			queryString += " ORDER BY nome";
		}

		// ordear por prezo
		if (opcion == 1) {

		}

		// ordear por Categoria
		if (opcion == 2) {
			queryString += " ORDER BY categoria";
		}
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

			return new Busqueda(localizacion, dataInicio, dataFin, numPersoas,
					hoteis);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

}
