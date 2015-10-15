package main.java.model.db.filtro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractSqlFiltroDao implements SqlFiltroDao {

	public void delFiltro(Connection connection, Long id) {

		/* Create "queryString". */
		String queryString = "DELETE FROM Filtro WHERE id = ?";

		try (PreparedStatement preparedStatement = connection
				.prepareStatement(queryString)) {

			/* Fill "preparedStatement". */
			int i = 1;
			preparedStatement.setLong(i++, id);

			/* Execute query. */
			int removedRows = preparedStatement.executeUpdate();

			if (removedRows == 0) {
				System.err.println("Filtro id:" + id + " non atopado.");
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public Filtro findFiltro(Connection connection, String nome) {

		/* Create "queryString". */
		String queryString = "SELECT id, nome, expresion, FROM Filtro "
				+ "WHERE (LOWER(nome) LIKE LOWER(?))";

		try (PreparedStatement preparedStatement = connection
				.prepareStatement(queryString)) {

			/* Fill "preparedStatement". */
			int i = 1;
			preparedStatement.setString(i++, nome);

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
			String newExpresion = resultSet.getString(i++);

			return new Filtro(newId, newNome, newExpresion);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
