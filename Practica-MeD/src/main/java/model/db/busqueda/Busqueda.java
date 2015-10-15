/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.model.db.busqueda;

import java.util.Calendar;
import java.util.List;

import main.java.model.db.filtro.Filtro;
import main.java.model.db.habitacion.Habitacion;
import main.java.model.db.hotel.Hotel;
import main.java.util.Pair;

/**
 *
 * @author Urist
 */

public class Busqueda {

	private Long id;
	private List<Filtro> filtros;
	private String localizacion;
	private Calendar dataInicio;
	private Calendar dataFin;
	private int numPerPorHab;
	private int categoria;
	private List<Pair<Hotel,Habitacion>> hoteis; // Lista de hoteis coas habitacions dispoñibles que cumplen o criterio de busqueda.

	public Busqueda() {

	}

	public Busqueda(String localizacion, Calendar dataInicio, Calendar dataFin,
			int numPerPorHab, List<Pair<Hotel,Habitacion>> hoteis) {

		this.localizacion = localizacion;
		if (dataInicio != null)
			dataInicio.set(Calendar.MILLISECOND, 0); // Workaround for rounding
														// errors
		this.dataInicio = dataInicio;

		if (dataFin != null)
			dataFin.set(Calendar.MILLISECOND, 0); // Workaround for rounding
													// errors
		this.dataFin = dataFin;
		this.numPerPorHab = numPerPorHab;
		this.hoteis = hoteis;
	}

	public Busqueda(Long id, String localizacion, Calendar dataInicio,
			Calendar dataFin, int numPerPorHab, List<Pair<Hotel,Habitacion>> hoteis) {
		this(localizacion, dataInicio, dataFin, numPerPorHab, hoteis);
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	public Calendar getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Calendar dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Calendar getDataFin() {
		return dataFin;
	}

	public void setDataFin(Calendar dataFin) {
		this.dataFin = dataFin;
	}

	public int getNumPerPorHab() {
		return numPerPorHab;
	}

	public void setNumPerPorHab(int numPerPorHab) {
		this.numPerPorHab = numPerPorHab;
	}

	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}

	public List<Filtro> getFiltros() {
		return filtros;
	}

	public void setFiltros(List<Filtro> filtros) {
		this.filtros = filtros;
	}

	public List<Pair<Hotel,Habitacion>> getHoteis() {
		return hoteis;
	}

	public void setHoteis(List<Pair<Hotel,Habitacion>> hoteis) {
		this.hoteis = hoteis;
	}
}
