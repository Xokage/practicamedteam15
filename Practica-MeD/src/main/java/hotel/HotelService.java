package main.java.hotel;

import java.util.Calendar;

public interface HotelService {

	public String buscarHotel(String localizacion, Calendar dataChegada, 
			Calendar dataFin, Integer categoria, Integer persoasPorHab);
}