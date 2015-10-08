/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.hotel;

/**
 *
 * @author Urist
 */
public class Habitacion {

    private Long id;
    private Float prezo;
    private int numCamas;
    private String servizos;
    private boolean estado;

    private Long idHotel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getPrezo() {
        return prezo;
    }

    public void setPrezo(Float prezo) {
        this.prezo = prezo;
    }

    public int getNumCamas() {
        return numCamas;
    }

    public void setNumCamas(int numCamas) {
        this.numCamas = numCamas;
    }

    public String getServizos() {
        return servizos;
    }

    public void setServizos(String servizos) {
        this.servizos = servizos;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Long getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(Long idHotel) {
        this.idHotel = idHotel;
    }

}
