/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.model.db.habitacion;

/**
 *
 * @author Urist
 */

public class Habitacion {

	private Long id;
	private Float prezo;
	private int numCamas;

	private Long idHotel;

	public Habitacion() {

	}

	public Habitacion(Float prezo, int numCamas, Long idHotel) {

		this.prezo = prezo;
		this.numCamas = numCamas;
	}

	public Habitacion(Long id, Float prezo, int numCamas, Long idHotel) {
		this(prezo, numCamas, idHotel);
		this.id = id;
	}

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

	public Long getIdHotel() {
		return idHotel;
	}

	public void setIdHotel(Long idHotel) {
		this.idHotel = idHotel;
	}

}
