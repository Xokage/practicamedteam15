package main.java.hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import es.udc.ws.app.model.answer.Answer;
import es.udc.ws.app.model.event.Event;

public abstract class AbstractSqlHotelDao implements SqlHotelDao {
	
	public Hotel getHotel(Connection connection, Long id) {
		
        /* Create "queryString". */
        String queryString = "SELECT id, nome, localizacion, descricion, "
        		+ "categoria, temporadaInicio, temporadaFin, servizos, "
        		+ "telefono FROM Hotel WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(queryString)) {

        	/* Fill "preparedStatement". */
            int i = 1;
            preparedStatement.setLong(i++, id);

            /* Execute query. */
            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
            	//If you find nothing return null
                return null;
            }
            
            /* Get results. */
            i = 1;
            Long newId = resultSet.getLong(i++); 
            String newNome= resultSet.getString(i++);
            String newLocalizacion = resultSet.getString(i++);
            String newDescricion = resultSet.getString(i++);
            Integer newCategoria = resultSet.getInt(i++);
            Calendar newTemporadaInicio = Calendar.getInstance();
            newTemporadaInicio.setTime(resultSet.getTimestamp(i++));
            Calendar newTemporadaFin = Calendar.getInstance();
            newTemporadaFin.setTime(resultSet.getTimestamp(i++));
            String newServizos = resultSet.getString(i++);
            String newTelefono = resultSet.getString(i++);
            
            return new Hotel(newId, newNome, newLocalizacion, newDescricion, newCategoria, newTemporadaInicio,
            		newTemporadaFin, newServizos, newTelefono);
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}
	

	/** 
	 * Busca un hotel por localizacion ou por nome
	 * @param connection
	 * @param localizacion
	 * @return
	 */
	public List<Hotel> findHotel(Connection connection, String localizacion) {
		
        /* Create "queryString". */
        String queryString = "SELECT id, nome, localizacion, descricion, "
        		+ "categoria, temporadaInicio, temporadaFin, servizos, "
        		+ "telefono FROM Hotel WHERE (LOWER(localizacion) LIKE LOWER(?))";

        try (PreparedStatement preparedStatement = connection.prepareStatement(queryString)) {

        	/* Fill "preparedStatement". */
            int i = 1;
            preparedStatement.setString(i++, "%"+localizacion+"%");

            /* Execute query. */
            ResultSet resultSet = preparedStatement.executeQuery();

            /* Get hotels */
            
            List<Hotel> hoteis = new ArrayList<Hotel>();

            while (resultSet.next()) {

                int i = 1;
                
                Long tmpEventId = resultSet.getLong(i++);
                String name = resultSet.getString(i++);
                String description = resultSet.getString(i++);
                Calendar tmpStartDate = Calendar.getInstance();
                tmpStartDate.setTime(resultSet.getTimestamp(i++));
                Calendar tmpFinishDate = Calendar.getInstance();
                tmpFinishDate.setTime(resultSet.getTimestamp(i++));

                int capacity = resultSet.getInt(i++);
                boolean isInternal = resultSet.getBoolean(i++);
                int attendantNum = resultSet.getInt(i++);
                String facebookId = resultSet.getString(i++);
                
                
                events.add(new Event(tmpEventId, name, description, tmpStartDate, tmpFinishDate,capacity,isInternal, attendantNum,facebookId));

            }
            
            /* Get results. */
            i = 1;
            Long newId = resultSet.getLong(i++); 
            String newNome= resultSet.getString(i++);
            String newLocalizacion = resultSet.getString(i++);
            String newDescricion = resultSet.getString(i++);
            Integer newCategoria = resultSet.getInt(i++);
            Calendar newTemporadaInicio = Calendar.getInstance();
            newTemporadaInicio.setTime(resultSet.getTimestamp(i++));
            Calendar newTemporadaFin = Calendar.getInstance();
            newTemporadaFin.setTime(resultSet.getTimestamp(i++));
            String newServizos = resultSet.getString(i++);
            String newTelefono = resultSet.getString(i++);
            
            return new Hotel(newId, newNome, newLocalizacion, newDescricion, newCategoria, newTemporadaInicio,
            		newTemporadaFin, newServizos, newTelefono);
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

		return null;
	}
}
