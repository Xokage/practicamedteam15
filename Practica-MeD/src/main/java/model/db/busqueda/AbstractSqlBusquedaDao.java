package main.java.model.db.busqueda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import main.java.model.db.habitacion.Habitacion;
import main.java.model.db.hotel.Hotel;
import main.java.util.Pair;
import main.java.util.SqlInjectionValidator;
import main.java.model.db.filtro.Filtro;

public abstract class AbstractSqlBusquedaDao implements SqlBusquedaDao {

	@Override
	public Busqueda realizarBusqueda(Connection connection,
			String localizacion, Calendar dataInicio, Calendar dataFin,
			int numPersoas, int opcion, boolean desc, List<Filtro> filtros) {

		String queryString = "SELECT a.id, a.nome, a.localizacion, a.descricion, a.categoria, "
				+ "a.temporadaInicio, a.temporadaFin, a.servizos, a.telefono, b.id, "
				+ "MIN(b.prezo), b.numCamas FROM Hotel a JOIN Habitacion b ON a.id = b.idHotel "
				+ "WHERE (b.numCamas = ? AND (LOWER(a.localizacion) LIKE LOWER(?)) OR (LOWER(a.nome) LIKE LOWER(?))) ";
		if (filtros != null) {
			for (Filtro f : filtros) {
				String tmpstring = f.getExpresion();
				if (SqlInjectionValidator.validateString(tmpstring))
					queryString += "AND " + f.getExpresion() + " ";
			}
		}
		queryString += "GROUP BY a.id";

		// ordear por nome
		if (opcion == 0) {
			queryString += " ORDER BY nome";
		}

		if (opcion == 1) {
			queryString += " ORDER BY MIN(b.prezo)";
		}

		// ordear por Categoria
		if (opcion == 2) {
			queryString += " ORDER BY categoria";
		}

		if (desc) {
			queryString += " ASC";
		} else
			queryString += " DESC";

		try (PreparedStatement preparedStatement = connection
				.prepareStatement(queryString)) {

			/* Fill "preparedStatement". */
			int i = 1;
			preparedStatement.setInt(i++, numPersoas);
			preparedStatement.setString(i++, "%" + localizacion + "%");
			preparedStatement.setString(i++, "%" + localizacion + "%");

			/* Execute query. */
			ResultSet resultSet = preparedStatement.executeQuery();

			/* Get hotels */

			List<Pair<Hotel, Habitacion>> hoteis = new ArrayList<Pair<Hotel, Habitacion>>();

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

				Long newHabId = resultSet.getLong(i++);
				Float newHabPrezo = resultSet.getFloat(i++);
				int newHabNumCamas = resultSet.getInt(i++);

				hoteis.add(new Pair<Hotel, Habitacion>(new Hotel(newId,
						newNome, newLocalizacion, newDescricion, newCategoria,
						newTemporadaInicio, newTemporadaFin, newServizos,
						newTelefono), new Habitacion(newHabId, newHabPrezo,
						newHabNumCamas, newId)));

			}

			return new Busqueda(localizacion, dataInicio, dataFin, numPersoas,
					hoteis);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
	
	@Override
	public Busqueda realizarBusqueda(Connection connection,
			String localizacion, Float prezoMax, Float prezoMin, Calendar dataInicio, Calendar dataFin,
			int numPersoas, int opcion, boolean desc, List<Filtro> filtros) {

		String queryString = "SELECT a.id, a.nome, a.localizacion, a.descricion, a.categoria, "
				+ "a.temporadaInicio, a.temporadaFin, a.servizos, a.telefono, b.id, "
				+ "MIN(b.prezo), b.numCamas FROM Hotel a JOIN Habitacion b ON a.id = b.idHotel "
				+ "WHERE (b.numCamas = ? AND (LOWER(a.localizacion) LIKE LOWER(?)) OR (LOWER(a.nome) LIKE LOWER(?)) AND (prezo  BETWEEN ? AND ?)) ";
		if (filtros != null) {
			for (Filtro f : filtros) {
				String tmpstring = f.getExpresion();
				if (SqlInjectionValidator.validateString(tmpstring))
					queryString += "AND " + f.getExpresion() + " ";
			}
		}
		queryString += "GROUP BY a.id";

		// ordear por nome
		if (opcion == 0) {
			queryString += " ORDER BY nome";
		}

		if (opcion == 1) {
			queryString += " ORDER BY MIN(b.prezo)";
		}

		// ordear por Categoria
		if (opcion == 2) {
			queryString += " ORDER BY categoria";
		}

		if (desc) {
			queryString += " ASC";
		} else
			queryString += " DESC";

		try (PreparedStatement preparedStatement = connection
				.prepareStatement(queryString)) {

			/* Fill "preparedStatement". */
			int i = 1;
			preparedStatement.setInt(i++, numPersoas);
			preparedStatement.setString(i++, "%" + localizacion + "%");
			preparedStatement.setString(i++, "%" + localizacion + "%");
			preparedStatement.setFloat(i++,prezoMax);
			preparedStatement.setFloat(i++,prezoMin);


			/* Execute query. */
			ResultSet resultSet = preparedStatement.executeQuery();

			/* Get hotels */

			List<Pair<Hotel, Habitacion>> hoteis = new ArrayList<Pair<Hotel, Habitacion>>();

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

				Long newHabId = resultSet.getLong(i++);
				Float newHabPrezo = resultSet.getFloat(i++);
				int newHabNumCamas = resultSet.getInt(i++);

				hoteis.add(new Pair<Hotel, Habitacion>(new Hotel(newId,
						newNome, newLocalizacion, newDescricion, newCategoria,
						newTemporadaInicio, newTemporadaFin, newServizos,
						newTelefono), new Habitacion(newHabId, newHabPrezo,
						newHabNumCamas, newId)));

			}

			return new Busqueda(localizacion, prezoMax, prezoMin, dataInicio, dataFin, numPersoas,
					hoteis);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
}
