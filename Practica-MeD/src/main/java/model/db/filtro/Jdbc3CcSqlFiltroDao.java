package main.java.model.db.filtro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Jdbc3CcSqlFiltroDao extends AbstractSqlFiltroDao {

	@Override
	public Filtro addFiltro(Connection connection, String nome, String expresion) {

		/* Create "queryString". */
		String queryString = "INSERT INTO Filtro (nome, expresion) VALUES (?, ?)";

		try (PreparedStatement preparedStatement = connection.prepareStatement(
				queryString, Statement.RETURN_GENERATED_KEYS)) {

			/* Fill "preparedStatement". */
			int i = 1;
			preparedStatement.setString(i++, nome);
			preparedStatement.setString(i++, expresion);

			/* Execute query. */
			preparedStatement.executeUpdate();

			/* Get generated identifier. */
			ResultSet resultSet = preparedStatement.getGeneratedKeys();

			if (!resultSet.next()) {
				throw new SQLException(
						"JDBC driver did not return generated key.");
			}

			// Obtención do ID do filtro
			Long id = resultSet.getLong(1);

			/* Return filtro. */
			return new Filtro(id, nome, expresion);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
