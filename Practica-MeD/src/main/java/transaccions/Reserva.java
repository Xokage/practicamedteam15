/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transaccions;

import hotel.Habitacion;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;

import org.hibernate.annotations.Immutable;

/**
 *
 * @author Urist
 */

@Entity
@Immutable
public class Reserva {

    private Long id;
    private Date dataReserva;
    private String nomeCliente;
    private String DniCliente;
    private Date dataEntrada;
    private Date dataSaida;

    private Long idHotel;
    private List<Habitacion> reservadas;

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
