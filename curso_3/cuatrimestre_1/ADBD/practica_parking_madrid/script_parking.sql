-- Author: Hernando Brecht, Rebeca
-- Author: Herruzo Herrero, Juan
-- Author: Jáñez Lagüéns, Guillermo
-- Author: Llorente Pérez, Mario
-- CREATE DATE: November 2019
-- Description: Creación de la base de datos del aparcamiento de Madrid

DROP TABLE IF EXISTS Gestor CASCADE ;
DROP TABLE IF EXISTS Aparcamiento CASCADE ;
DROP TABLE IF EXISTS Rotacional CASCADE;
DROP TABLE IF EXISTS Residencial CASCADE;
DROP TABLE IF EXISTS ReclamacionYSugerencia CASCADE;
DROP TABLE IF EXISTS Balance CASCADE;
DROP TABLE IF EXISTS DetalleBalance CASCADE;
DROP TABLE IF EXISTS Acceso CASCADE;
DROP TABLE IF EXISTS Vehiculo CASCADE;
DROP TABLE IF EXISTS VehiculoAbono CASCADE;
DROP TABLE IF EXISTS HistorialUso;
DROP TABLE IF EXISTS Titular CASCADE;
DROP TABLE IF EXISTS Particular CASCADE;
DROP TABLE IF EXISTS Solicitud CASCADE;
DROP TABLE IF EXISTS Servicio CASCADE;
DROP TABLE IF EXISTS Plaza CASCADE;
DROP TABLE IF EXISTS CesionDeTitularidad CASCADE;
DROP TABLE IF EXISTS Abono CASCADE;
DROP TABLE IF EXISTS VehiculoAsociado CASCADE;
DROP DOMAIN IF EXISTS tipoAcceso;
DROP DOMAIN IF EXISTS tipoPlaza;
DROP DOMAIN IF EXISTS tipoAbono;
DROP DOMAIN IF EXISTS motivoCesion;


CREATE DOMAIN tipoAcceso AS VARCHAR(8)
CHECK (VALUE IN ('peatonal','entrada','salida'));

CREATE DOMAIN tipoAbono AS VARCHAR(11)
CHECK (VALUE IN ('diurno','nocturno','residencial','sostenible','diario','cesion'));

CREATE DOMAIN tipoPlaza AS VARCHAR(13)
CHECK (VALUE IN ('residencial','rotacional','rotacionalDis'));

CREATE DOMAIN motivoCesion AS VARCHAR(22)
CHECK (VALUE IN ('fallecimiento', 'ventaVivienda', 'liquidacionGananciales', 'interesParticular'));

CREATE TABLE Gestor (
	nombre CHAR(50),
	cif CHAR(9),
	PRIMARY KEY (cif)
);
--Este aserto comprueba que haya plazas libres antes de dejar entrar a un coche a un aparcamiento residencial
--CREATE assertion capacidad
	--CHECK (
	--(SELECT count(*)
	--FROM Plaza P
	--WHERE P.disponibilidad = true AND P.tipo <> 'residencial') >=
	--(SELECT count(*)
	--FROM HistorialUso HU
	--WHERE HU.tSalida IS NULL)
--);
CREATE TABLE Aparcamiento (
	idApar CHAR(10),
	nombre CHAR(50),
	distrito CHAR(50),
	cifGes CHAR(9),
	PRIMARY KEY (idApar),
	FOREIGN KEY (cifGes) REFERENCES Gestor (cif)
);

CREATE TABLE Rotacional (
	idApar CHAR(10),
	abDiurno INT,
	abNocturno INT,
	tarifCoche INT,
	tarifMoto INT,
	tarifAutocaravana INT,
	tarifCocheDis INT,
	tarifMotoDis INT,
	tarifAutocaravanaDis INT,
	PRIMARY KEY (idApar),
	FOREIGN KEY (idApar) REFERENCES Aparcamiento (idApar)
);

CREATE TABLE Residencial (
	idApar CHAR(10),
	abDiario INT,
	abSostenible INT,
	abResidencial INT,
	abCesion INT,
	PRIMARY KEY (idApar),
	FOREIGN KEY (idApar) REFERENCES Aparcamiento (idApar)
);

CREATE TABLE ReclamacionYSugerencia (
	id CHAR(10),
	idApar CHAR(10),
	fechaEmision DATE,
	texto CHAR(500),
	PRIMARY KEY (idApar, id),
	FOREIGN KEY (idApar) REFERENCES Aparcamiento (idApar)
);
-- No puede haber dos balances en el mismo periodo de tiempo del mismo parking.
CREATE TABLE Balance (
	id CHAR(10),
	idApar CHAR(10),
	fechaIni DATE,
	fechaFin DATE,
	PRIMARY KEY (idApar, id),
	FOREIGN KEY (idApar) REFERENCES Aparcamiento (idApar),
	CHECK(fechaIni < fechaFin)
);
-- fecha debe estar comprendida entre la fechaIni y la fechaFin del balance al que pertenece.
CREATE TABLE DetalleBalance (
	id CHAR(10),
	idBal CHAR(10),
	idApar CHAR(10),
	concepto CHAR(50),
	valor INT,
	fecha DATE,
	PRIMARY KEY (id, idApar, idBal),
	FOREIGN KEY (idApar, idBal) REFERENCES Balance (idApar, id)
);

CREATE TABLE Acceso (
	id CHAR(10),
	idApar CHAR(10),
	abierto BOOLEAN,
	direcion CHAR(50),
	tipo tipoAcceso,
	PRIMARY KEY (id, idApar),
	FOREIGN KEY (idApar) REFERENCES Aparcamiento (idApar)
);
-- Si cif es NULL implica que un Particular tiene como id la id de este titular.
-- Si cif no es NULL implica que no existe un Particular tiene como id la id de este titular.
CREATE TABLE Titular (
	id CHAR(10),
	domicilio CHAR(50),
	nombre CHAR(50),
	cif CHAR(9),
	PRIMARY KEY (id)
);

CREATE TABLE Particular (
	id CHAR(10),
	apellidos CHAR(50),
	dni CHAR(9),
	pmr BOOLEAN,
	PRIMARY KEY (id),
	FOREIGN KEY (id) REFERENCES Titular (id)
);

CREATE TABLE Vehiculo (
	matricula CHAR(8),
	pmr BOOLEAN,
	PRIMARY KEY (matricula)
);

CREATE TABLE VehiculoAbono(
	matricula CHAR(8),
	modelo CHAR(20),
	bastidor CHAR(18),
	idT CHAR(10),
	PRIMARY KEY (matricula),
	FOREIGN KEY (matricula) REFERENCES Vehiculo (matricula),
	FOREIGN KEY (idT) REFERENCES Titular (id)
);
CREATE TABLE HistorialUso (
	id CHAR(10),
	idApar CHAR(10),
	matricula CHAR(8),
	tEntrada TIMESTAMP,
	tSalida TIMESTAMP,
	tPago TIMESTAMP,
	PRIMARY KEY (id, idApar),
	FOREIGN KEY (idApar) REFERENCES Aparcamiento (idApar),
	FOREIGN KEY (matricula) REFERENCES Vehiculo (matricula),
	CHECK (tEntrada<= tPago),
	CHECK (tPago <=tSalida)
	--CHECK (tSalida IS NULL OR (tSalida - tPago) < 900) -- 900 es 15 mins en timestamp
	--CHECK( 2 > all (SELECT count(*)
	--				FROM HistorialUso HU
	--				WHERE HU.tSalida IS NULL
	--				GROUP BY HU.matricula))
);
-- tCreacion > 'tiempo apertura plazo inscripción'
-- EL tipo de solicitud tiene que ser compatible con el tipo de parking al que se pide.
CREATE TABLE Solicitud (
	id CHAR(10),
	idApar CHAR(10),
	tCreacion TIMESTAMP,
	tipo tipoAbono,
	idT CHAR(10),
	matricula CHAR(10),
	PRIMARY KEY (id),
	FOREIGN KEY (idApar) REFERENCES Aparcamiento (idApar),
	FOREIGN KEY (idT) REFERENCES Titular (id),
	FOREIGN KEY (matricula) REFERENCES VehiculoAbono (matricula)
	--CHECK (NOT EXISTS(
	--	SELECT *
	--	FROM Solicitud S ,VehiculoAbono V
	--	WHERE S.idT <> V.idT
	--))
);

CREATE TABLE Servicio (
	id CHAR(10),
	nombre CHAR(50),
	cif CHAR(9),
	PRIMARY KEY (id)
	--CHECK(NOT EXISTS(
	--	SELECT *
	--	FROM Aparcamiento A NATURAL JOIN Residencial Re NATURAL JOIN Rotacional Ro NATURAL JOIN Plaza P
	--	WHERE P.tipo='residencial' AND idS IS NOT Null))
);

CREATE TABLE Plaza (
	id CHAR(10),
	idApar CHAR(10),
	idS CHAR(10),
	disponibilidad BOOLEAN,
	pmr BOOLEAN,
	tipo tipoPlaza,
	PRIMARY KEY (id, idApar),
	FOREIGN KEY (idApar) REFERENCES Aparcamiento (idApar),
	FOREIGN KEY (idS) REFERENCES Servicio (id),
	CHECK ( idS IS NULL or disponibilidad = false)
);

-- la fechaFin del abono que se cesiona es menor que la fechaIni del abono que se crea
-- Si hay fechaFin, ha de ser mayor que fechaIni
-- Si un abono ha caducado, no puede cederse.
-- Un abono cecido tiene la misma plaza que el abono del que se cede y la misma fechaCaducidad.
CREATE TABLE Abono (
	id CHAR(10),
	idP CHAR(10),
	idApar CHAR(10),
	fechaIni DATE,
	fechaFin DATE,
	fechaCaducidad DATE,
	fechaSiguientePago DATE,
	PRIMARY KEY (id),
	FOREIGN KEY (id) REFERENCES Solicitud (id),
	FOREIGN KEY (idP, idApar) REFERENCES Plaza (id, idApar),
	CHECK ( fechaIni< fechaCaducidad)
	--CHECK ( NOT EXISTS(
	--	SELECT *
	--	FROM Plaza P NATURAL JOIN Abono A
	--	WHERE P.tipo <> 'residencial'
	--))
);
CREATE TABLE CesionDeTitularidad (
	motivo motivoCesion,
	idBeneficiario CHAR(10),
	idInteresado CHAR(10),
	PRIMARY KEY (idInteresado),
	FOREIGN KEY (idBeneficiario) REFERENCES Abono (id),
	FOREIGN KEY (idInteresado) REFERENCES Abono (id),
	CHECK(idBeneficiario <> idInteresado)
);
CREATE TABLE VehiculoAsociado (
	idAb CHAR(10),
	matricula CHAR(8),
	PRIMARY KEY (idAb, matricula),
	FOREIGN KEY (idAb) REFERENCES Abono (id),
	FOREIGN KEY (matricula) REFERENCES VehiculoAbono (matricula)
);
/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
INSERT INTO Gestor VALUES ('Paco Jimenez', 'S2085915C');
INSERT INTO Gestor VALUES ('Luis Herrera', 'W1699718A');
/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
INSERT INTO Aparcamiento VALUES ('A1','AGUILAR DE CAMPOO', 'MONCLOA - ARAVACA', 'S2085915C');
INSERT INTO Aparcamiento VALUES ('A2','AGUILON', 'ARGANZUELA', 'W1699718A');
INSERT INTO Aparcamiento VALUES ('A3','AGUSTIN LARA', 'CENTRO', 'W1699718A');
/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
INSERT INTO Rotacional VALUES ('A1','500','750','50','25','110','30','12','70');
INSERT INTO Rotacional VALUES ('A3','800','1200','65','35','140','40','18','90');
/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
INSERT INTO Residencial VALUES ('A2','1100','600','800','650');
INSERT INTO Residencial VALUES ('A3','1200','700','900','750');
/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
INSERT INTO ReclamacionYSugerencia VALUES ('RyS1', 'A1','2019-06-12','Hacía mucho calor en el parking.');
INSERT INTO ReclamacionYSugerencia VALUES ('RyS1', 'A2','2019-04-01','Parking decente, tenia muchos accesos, pero estaría bien tener más accesos peatonales.');
INSERT INTO ReclamacionYSugerencia VALUES ('RyS2', 'A1','2019-11-11','No había extintores.');
/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
INSERT INTO Balance VALUES ('B1','A1','2019-01-10','2019-03-09');
INSERT INTO Balance VALUES ('B2','A1','2019-03-10','2019-05-09');
INSERT INTO Balance VALUES ('B1','A2','2019-01-20','2019-03-19');
/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
INSERT INTO DetalleBalance VALUES ('DB1', 'B1','A2','Salida vehiculo','75', '2019-02-10');
INSERT INTO DetalleBalance VALUES ('DB1', 'B1','A1','Compra Material','-150000', '2019-01-12');
/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
INSERT INTO Acceso VALUES ('AC1', 'A1', TRUE, 'CALLE ARTAJONA', 'peatonal');
INSERT INTO Acceso VALUES ('AC2', 'A1', TRUE, 'CALLE ARTAJONA', 'entrada');
INSERT INTO Acceso VALUES ('AC3', 'A1', TRUE, 'CALLE OCHAGAVIA', 'salida');
INSERT INTO Acceso VALUES ('AC1', 'A2', TRUE, 'CALLE AGUILON', 'peatonal');
INSERT INTO Acceso VALUES ('AC2', 'A2', TRUE, 'CALLE AGUILON', 'entrada');
INSERT INTO Acceso VALUES ('AC3', 'A2', TRUE, 'CALLE AGUILON', 'salida');
INSERT INTO Acceso VALUES ('AC1', 'A3', TRUE, 'CALLE ARTAJONA', 'peatonal');
INSERT INTO Acceso VALUES ('AC2', 'A3', TRUE, 'CALLE ARTAJONA', 'entrada');
INSERT INTO Acceso VALUES ('AC3', 'A3', TRUE, 'CALLE ARTAJONA', 'salida');
/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
INSERT INTO Titular VALUES ('T1','Hernando de Acuña 32','Hector',NULL);
INSERT INTO Titular VALUES ('T2','Universidad 2','Marcos',NULL);
INSERT INTO Titular VALUES ('T3','Campo Grande 55','Silvia',NULL);
INSERT INTO Titular VALUES ('T4','Centro 4','Empresas SA','F1694318A');
/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
INSERT INTO Particular VALUES ('T1','Hernando Hernando','09345425X',FALSE);
INSERT INTO Particular VALUES ('T2','Perez Martín','62795425B',TRUE);
INSERT INTO Particular VALUES ('T3','Alvarez Fernandez','71646823K',FALSE);
/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
INSERT INTO Vehiculo VALUES ('9368BBB',FALSE);
INSERT INTO Vehiculo VALUES ('2345FRD',TRUE);
INSERT INTO Vehiculo VALUES ('1234BCD',FALSE);
INSERT INTO Vehiculo VALUES ('0000BBB',FALSE);
INSERT INTO Vehiculo VALUES ('2104JHH',FALSE);
INSERT INTO Vehiculo VALUES ('3347LKH',TRUE);
INSERT INTO Vehiculo VALUES ('3405TXM',FALSE);
INSERT INTO Vehiculo VALUES ('2734WND',FALSE);
INSERT INTO Vehiculo VALUES ('1010CCC',FALSE);

/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
INSERT INTO VehiculoAbono VALUES ('9368BBB','Opel Corsa','VF14ID00213635032','T1');
INSERT INTO VehiculoAbono VALUES ('2345FRD','Toyota Corola','LF1RFD05130635032','T2');
INSERT INTO VehiculoAbono VALUES ('1234BCD','Audi A3','HY1RFD12365363041','T3');
INSERT INTO VehiculoAbono VALUES ('0000BBB','Citroen C4','LF1RFD05650635030','T1');
INSERT INTO VehiculoAbono VALUES ('2104JHH','Renault Twizzy','PL1RFD12365263512','T2');
INSERT INTO VehiculoAbono VALUES ('1010CCC','Opel Eco','PL1RFD12632635512','T4');
/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
INSERT INTO HistorialUso VALUES ('HU1','A1','9368BBB','2016-03-14 15:34:10',NULL,NULL);
INSERT INTO HistorialUso VALUES ('HU2','A1','0000BBB','2017-03-14 15:30:13',NULL,'2017-03-15 15:31:11');
INSERT INTO HistorialUso VALUES ('HU3','A1','3405TXM','2018-03-14 20:20:16','2018-03-14 23:34:00','2018-03-14 23:25:11');
INSERT INTO HistorialUso VALUES ('HU1','A3','2345FRD','2019-03-14 05:00:12','2019-03-14 12:01:05','2019-03-14 11:59:17');
INSERT INTO HistorialUso VALUES ('HU1','A2','3405TXM','2016-03-14 15:34:46',NULL,'2016-03-14 18:34:11');
INSERT INTO HistorialUso VALUES ('HU2','A3','2345FRD','2016-04-14 15:34:13',NULL,NULL);
INSERT INTO HistorialUso VALUES ('HU2','A2','2734WND','2010-05-14 15:34:46','2012-03-14 23:34:11','2012-03-14 23:30:11');
/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
INSERT INTO Solicitud VALUES ('S1','A1','2016-02-14 15:34:11','residencial','T3','1234BCD');
INSERT INTO Solicitud VALUES ('S2','A3','2018-01-30 15:34:11','sostenible','T4','1010CCC');
INSERT INTO Solicitud VALUES ('S3','A1','2015-05-25 15:34:11','cesion','T2','2104JHH');
INSERT INTO Solicitud VALUES ('S4','A1','2009-11-25 15:34:11','residencial','T1','9368BBB');
INSERT INTO Solicitud VALUES ('S5','A2','2016-03-14 15:34:11','diurno','T1','9368BBB');
/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
INSERT INTO Servicio VALUES ('SER1','Lavacoches','S2085915C');
/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
INSERT INTO Plaza VALUES ('P1','A1',NULL,TRUE, TRUE,'residencial');
INSERT INTO Plaza VALUES ('P2','A1',NULL,TRUE, FALSE,'residencial');
INSERT INTO Plaza VALUES ('P3','A1',NULL,TRUE, FALSE,'residencial');
INSERT INTO Plaza VALUES ('P4','A1',NULL,TRUE, FALSE,'residencial');
INSERT INTO Plaza VALUES ('P1','A2',NULL,FALSE, FALSE,'rotacional');
INSERT INTO Plaza VALUES ('P2','A2',NULL,TRUE, FALSE,'rotacional');
INSERT INTO Plaza VALUES ('P3','A2',NULL,TRUE, TRUE,'rotacional');
INSERT INTO Plaza VALUES ('P4','A2',NULL,TRUE, FALSE,'rotacional');
INSERT INTO Plaza VALUES ('P1','A3',NULL,TRUE, TRUE,'residencial');
INSERT INTO Plaza VALUES ('P2','A3','SER1',FALSE, FALSE,'rotacional');
INSERT INTO Plaza VALUES ('P3','A3',NULL,TRUE, FALSE,'rotacional');
INSERT INTO Plaza VALUES ('P4','A3',NULL,FALSE, FALSE,'residencial');
/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
INSERT INTO Abono VALUES ('S1','P2','A1','2016-03-14', '2018-05-30', '2021-03-13','2018-04-25');
INSERT INTO Abono VALUES ('S4','P2','A1','2018-05-31', NULL, '2021-03-13','2019-12-03');
INSERT INTO Abono VALUES ('S2','P4','A3','2015-05-31', NULL, '2019-11-29','2019-12-25');
INSERT INTO Abono VALUES ('S3','P1','A1','2010-01-20', NULL, '2060-03-14',NULL);
/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
INSERT INTO CesionDeTitularidad VALUES ('ventaVivienda','S1','S4');
/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
INSERT INTO VehiculoAsociado VALUES ('S1','1234BCD');
INSERT INTO VehiculoAsociado VALUES ('S2','1010CCC');
INSERT INTO VehiculoAsociado VALUES ('S3','2104JHH');
INSERT INTO VehiculoAsociado VALUES ('S3','2345FRD');
INSERT INTO VehiculoAsociado VALUES ('S4','9368BBB');
INSERT INTO VehiculoAsociado VALUES ('S4','0000BBB');


