/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.hotel;

import main.java.transaccions.Reserva;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;

import org.hibernate.annotations.BatchSize;

/**
 *
 * @author Urist
 */




public class Hotel {

    private Long id;
    private String nome;
    private String localizacion;
    private String descricion;
    private Integer categoria;
    private Calendar temporadaInicio;
    private Calendar temporadaFin;
    private String servizos;
    private String telefono;

    public Hotel() {
    	
    }
    
    public Hotel(String nome, String localizacion, String descricion, Integer categoria, 
    		Calendar temporadaInicio, Calendar temporadaFin, String servizos, String telefono) {
    		
    	this.nome = nome;
    	this.localizacion = localizacion;
    	this.descricion = descricion;
    	this.categoria = categoria;
    	this.temporadaInicio = temporadaInicio;
    	this.temporadaFin = temporadaFin;
    	this.servizos = servizos;
    	this.telefono = telefono;
    	
    }

    public Hotel(Long id, String nome, String localizacion, String descricion, Integer categoria, 
    		Calendar temporadaInicio, Calendar temporadaFin, String servizos, String telefono) {
    	this(nome,localizacion,descricion,categoria,temporadaInicio,temporadaFin,servizos,telefono);
    	this.id = id;
    }
    
    private Calendar getTemporadaInicio() {
		return temporadaInicio;
	}
	private void setTemporadaInicio(Calendar temporadaInicio) {
		this.temporadaInicio = temporadaInicio;
	}
	private Calendar getTemporadaFin() {
		return temporadaFin;
	}
	private void setTemporadaFin(Calendar temporadaFin) {
		this.temporadaFin = temporadaFin;
	}
	public Hotel(Long id, String nome){
    	this.id = id;
    	this.nome = nome;
    }
    public void engadirServizos(String s) {

    }

    public void eliminarServizos(String s) {

    }

    public void cambiarTempada() {

    }

    public void cambiarEstadoHabitacion(Habitacion h) {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public String getDescricion() {
        return descricion;
    }

    public void setDescricion(String descricion) {
        this.descricion = descricion;
    }

    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public String getServizos() {
        return servizos;
    }

    public void setServizos(String servizos) {
        this.servizos = servizos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}