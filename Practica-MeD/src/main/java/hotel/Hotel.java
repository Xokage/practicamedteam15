/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel;

import transaccions.Reserva;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;

import org.hibernate.annotations.BatchSize;

/**
 *
 * @author Urist
 */
@Entity
@BatchSize(size=10)
public class Hotel {

    private Long id;
    private String nome;
    private String localizacion;
    private String descricion;
    private int Categoria;
    private Calendar temporadaInicio;
    private Calendar temporadaFin;
    private String servizos;
    private String telefono;
    private int numHabitacions;

    private List<Reserva> reservas;

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

    public void realizarReserva(String nome, String dni,
            Date dataEntrada, Date dataSaida, int numHabitacions,
            int persoasPorHabitacion) {

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

    public int getCategoria() {
        return Categoria;
    }

    public void setCategoria(int Categoria) {
        this.Categoria = Categoria;
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

    public int getNumHabitacions() {
        return numHabitacions;
    }

    public void setNumHabitacions(int numHabitacions) {
        this.numHabitacions = numHabitacions;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }
    
    
}
