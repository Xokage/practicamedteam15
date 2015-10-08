/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.busqueda;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Urist
 */

@Entity
public class Busqueda {

    private Long id;
    private List<FiltroXeral> filtros;
    private String localizacion;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataInicio;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar dataFin;
    private int numPerPorHab;
    private int categoria;
    

    private String getLocalizacion() {
		return localizacion;
	}

	private void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	private Calendar getDataInicio() {
		return dataInicio;
	}

	private void setDataInicio(Calendar dataInicio) {
		this.dataInicio = dataInicio;
	}

	private Calendar getDataFin() {
		return dataFin;
	}

	private void setDataFin(Calendar dataFin) {
		this.dataFin = dataFin;
	}

	private int getNumPerPorHab() {
		return numPerPorHab;
	}

	private void setNumPerPorHab(int numPerPorHab) {
		this.numPerPorHab = numPerPorHab;
	}

	private int getCategoria() {
		return categoria;
	}

	private void setCategoria(int categoria) {
		this.categoria = categoria;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<FiltroXeral> getFiltros() {
        return filtros;
    }

    public void setFiltros(List<FiltroXeral> filtros) {
        this.filtros = filtros;
    }

}
