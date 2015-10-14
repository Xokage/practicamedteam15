-- Creamos as taboas para a base de datos hoteis_med

USE hoteis_med;

-- Borrar claves, pode fallar --

ALTER TABLE Habitacion DROP FOREIGN KEY Habitacion_idHotel_FK;
ALTER TABLE Reserva DROP FOREIGN KEY Reserva_idHabitacion_FK;

-- Borrar taboas --


DROP TABLE IF EXISTS Hotel;
DROP TABLE IF EXISTS Habitacion;
DROP TABLE IF EXISTS Reserva;
DROP TABLE IF EXISTS Filtro;

-- Crear taboas --

-- HOTEL --

CREATE TABLE Hotel (
	id BIGINT NOT NULL AUTO_INCREMENT,
	nome VARCHAR(40) NOT NULL,
	localizacion VARCHAR(40) NOT NULL,
	descricion VARCHAR(80),
	categoria SMALLINT NOT NULL DEFAULT 1,
	temporadaInicio TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	temporadaFin TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	servizos VARCHAR(100),
	telefono VARCHAR(32) NOT NULL,
	
	CONSTRAINT Hotel_PK PRIMARY KEY (id)
	);
	
CREATE INDEX HotelIndexByNome ON Hotel (nome);
CREATE INDEX HotelIndexByCategoria ON Hotel (categoria);


-- HABITACION --

CREATE TABLE Habitacion (
	id BIGINT NOT NULL AUTO_INCREMENT,
	prezo FLOAT NOT NULL DEFAULT 1,
	numCamas SMALLINT NOT NULL DEFAULT 1,
	idHotel BIGINT NOT NULL,
	
	CONSTRAINT Habitacion_PK PRIMARY KEY (id),
	CONSTRAINT Habitacion_idHotel_FK FOREIGN KEY (idHotel) REFERENCES Hotel(id)
	);
	
CREATE INDEX HabitacionIndexByPrezo ON Habitacion (prezo);
	
-- RESERVA --

CREATE TABLE Reserva (
	id BIGINT NOT NULL AUTO_INCREMENT,
	dataReserva TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	nomeCliente VARCHAR(20) NOT NULL,
	dniCliente VARCHAR(20) NOT NULL,
	dataEntrada TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	dataSaida TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	idHabitacion BIGINT NOT NULL,
	
	CONSTRAINT Reserva_PK PRIMARY KEY (id),
	CONSTRAINT Reserva_idHabitacion_FK FOREIGN KEY (idHabitacion) REFERENCES Habitacion(id)
	);
	
	CREATE INDEX ReservaIndexByDataEntrada ON Reserva (dataEntrada);
	
-- FILTRO --

CREATE TABLE Filtro (
	id BIGINT NOT NULL AUTO_INCREMENT,
	nome VARCHAR(20) NOT NULL,
	datos VARCHAR(100),
	
	CONSTRAINT Filtro_PK PRIMARY KEY (id)
	);
	
