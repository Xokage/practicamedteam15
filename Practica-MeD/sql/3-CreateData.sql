-- DATA INSERTION --

-- HOTEL --
INSERT INTO `hotel` (`id`, `nome`, `localizacion`, `descricion`, `categoria`, `temporadaInicio`, `temporadaFin`, `servizos`, `telefono`) VALUES (1, 'Hotel Soneira', 'Vimianzo', NULL, 5, '2015-10-15 09:48:22', '2015-12-15 09:48:34', 'wifi,piscina,billar', '918231495');
INSERT INTO `hotel` (`id`, `nome`, `localizacion`, `descricion`, `categoria`, `temporadaInicio`, `temporadaFin`, `servizos`, `telefono`) VALUES (2, 'Hotel Pastoriza', 'Vimianzo', NULL, 4, '2015-09-15 09:49:17', '2015-10-15 09:49:24', 'jacuzzi,bar,tenis', '918324951');
INSERT INTO `hotel` (`id`, `nome`, `localizacion`, `descricion`, `categoria`, `temporadaInicio`, `temporadaFin`, `servizos`, `telefono`) VALUES (3, 'El Condor Pasa', 'Camariñas', NULL, 3, '2015-05-15 09:50:02', '2015-07-15 09:50:08', 'lavanderia,despertador,paddle', '918811881');


-- HABITACION --
INSERT INTO `habitacion` (`id`, `prezo`, `numCamas`, `idHotel`) VALUES (1, 300, 1, 1);
INSERT INTO `habitacion` (`id`, `prezo`, `numCamas`, `idHotel`) VALUES (2, 320, 2, 1);
INSERT INTO `habitacion` (`id`, `prezo`, `numCamas`, `idHotel`) VALUES (3, 180, 1, 1);
INSERT INTO `habitacion` (`id`, `prezo`, `numCamas`, `idHotel`) VALUES (4, 500, 3, 2);
INSERT INTO `habitacion` (`id`, `prezo`, `numCamas`, `idHotel`) VALUES (5, 450, 2, 2);
INSERT INTO `habitacion` (`id`, `prezo`, `numCamas`, `idHotel`) VALUES (6, 320, 1, 3);
INSERT INTO `habitacion` (`id`, `prezo`, `numCamas`, `idHotel`) VALUES (7, 100, 2, 3);
INSERT INTO `habitacion` (`id`, `prezo`, `numCamas`, `idHotel`) VALUES (8, 44, 1, 3);
