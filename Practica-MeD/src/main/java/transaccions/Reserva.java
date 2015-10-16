/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.transaccions;

import main.java.model.db.habitacion.Habitacion;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Urist
 */

public class Reserva {

    private Long id;
    private Calendar dataReserva;
    private String nomeCliente;
    private String DniCliente;
    private Calendar dataEntrada;
    private Calendar dataSaida;

    private Long idHotel;
    private Long idHabitacion;
    
    public Reserva() {
    	
    }
    
    public Reserva(Calendar dataReserva, String nomeCliente, String DniCliente, 
    		Calendar dataEntrada, Calendar dataSaida, Long idHotel, Long idHabitacion){
    	
    	this.dataReserva = dataReserva;
    	this.nomeCliente = nomeCliente;
    	this.DniCliente = DniCliente;
    	if (dataEntrada != null)
			dataEntrada.set(Calendar.MILLISECOND, 0); // Workaround for rounding
														// errors
		this.dataEntrada = dataEntrada;

		if (dataSaida != null)
			dataSaida.set(Calendar.MILLISECOND, 0);

		this.dataSaida = dataSaida;
    	this.idHotel = idHotel;
    	this.idHabitacion = idHabitacion;
    }
    
    public Reserva(Long id, Calendar dataReserva, String nomeCliente, String DniCliente, 
    		Calendar dataEntrada, Calendar dataSaida, Long idHotel, Long idHabitacion){
    	
    	this(dataReserva,nomeCliente,DniCliente, dataEntrada,dataSaida,idHotel,idHabitacion);
    	this.id = id;
    }
    
    public Long getIdHabitacion() {
		return idHabitacion;
	}

	public void setIdHabitacion(Long idHabitacion) {
		this.idHabitacion = idHabitacion;
	}

	public Long getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(Long idHotel) {
        this.idHotel = idHotel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(Date dataReserva) {
        this.dataReserva = dataReserva;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getDniCliente() {
        return DniCliente;
    }

    public void setDniCliente(String DniCliente) {
        this.DniCliente = DniCliente;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
    }

}
