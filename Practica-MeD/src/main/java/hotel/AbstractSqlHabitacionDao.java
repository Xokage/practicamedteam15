package main.java.hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public abstract class AbstractSqlHabitacionDao implements SqlHabitacionDao {

	public Habitacion getHabitacion(Connection connection, Long id) {

		/* Create "queryString". */
		String queryString = "SELECT id, prezo, numCamas, servizos, "
				+ "estado FROM Habitacion WHERE id = ?";

		try (PreparedStatement preparedStatement = connection
				.prepareStatement(queryString)) {

			/* Fill "preparedStatement". */
			int i = 1;
			preparedStatement.setLong(i++, id);

			/* Execute query. */
			ResultSet resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				// If you find nothing return null
				return null;
			}

			/* Get results. */
			i = 1;
			Long newId = resultSet.getLong(i++);
			Float newPrezo = resultSet.getFloat(i++);
			Integer newNumCamas = resultSet.getInt(i++);
			String newServizos = resultSet.getString(i++);
			boolean newEstado = resultSet.getBoolean(i++);
			// Integer newIdHotel = resultSet.getInt(i++);

			return new Habitacion(newId, newPrezo, newNumCamas, newServizos,
					newEstado);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Habitacion> findHabitacion(Connection connection, Long idHotel) {

		/* Create "queryString". */
		String queryString = "SELECT id, prezo, numCamas, servizos, "
				+ "estado FROM Habitacion WHERE idHotel = ?";

		try (PreparedStatement preparedStatement = connection
				.prepareStatement(queryString)) {

			/* Fill "preparedStatement". */
			int i = 1;
			preparedStatement.setLong(i++, idHotel.longValue());

			/* Execute query. */
			ResultSet resultSet = preparedStatement.executeQuery();

			/* Get hotels */

			List<Habitacion> habitacions = new ArrayList<Habitacion>();

			while (resultSet.next()) {

				i = 1;

				Long newId = resultSet.getLong(i++);
				Float newPrezo = resultSet.getFloat(i++);
				Integer newNumCamas = resultSet.getInt(i++);
				String newServizos = resultSet.getString(i++);
				boolean newEstado = resultSet.getBoolean(i++);
				// Integer newIdHotel = resultSet.getInt(i++);

				habitacions.add(new Habitacion(newId, newPrezo, newNumCamas,
						newServizos, newEstado));

			}

			/* Return hoteis. */
			return habitacions;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
	
	public void delHabitacion(Connection connection, Long id) {
    	
    	/* Create "queryString". */
        String queryString = "DELETE FROM Habitacion WHERE" + " id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(queryString)) {

            /* Fill "preparedStatement". */
            int i = 1;
            preparedStatement.setLong(i++, id);

            /* Execute query. */
            int removedRows = preparedStatement.executeUpdate();

            if (removedRows == 0) {
            	System.err.println("Habitacion id:" + id + " non atopado.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
