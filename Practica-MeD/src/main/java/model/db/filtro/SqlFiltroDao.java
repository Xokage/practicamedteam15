package main.java.model.db.filtro;

import java.sql.Connection;

public interface SqlFiltroDao {

	/**
	 * Opcion 0 = Ordear por nome /* Opcion 1 = Ordear por prezo /* Opcion 2 =
	 * Ordear por categoria
	 **/

	public Filtro findFiltro(Connection connection, String nome);

	public Filtro addFiltro(Connection connection, String nome, String expresion);

	public void delFiltro(Connection connection, Long id);
}
