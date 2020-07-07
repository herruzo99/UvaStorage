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
drop domain if exists tipoAcceso;
drop domain if exists tipoPlaza;
drop domain if exists tipoAbono;
drop domain if exists motivoCesion;
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

CREATE TABLE Balance (
	id CHAR(10),
	idApar CHAR(10),
	fechaIni DATE,
	fechaFin DATE,
	PRIMARY KEY (idApar, id),
	FOREIGN KEY (idApar) REFERENCES Aparcamiento (idApar)
);

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
	bastidor CHAR(17),
	idT CHAR(10),
	PRIMARY KEY (matricula),
	FOREIGN KEY (matricula) REFERENCES Vehiculo (matricula),
	FOREIGN KEY (idT) REFERENCES Titular (id)
);
--La diferencia entre el tPago y tSalida ha de ser menor o igual a 15 minutos
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
	--CHECK( 2 > all (SELECT count(*)
	--				FROM HistorialUso HU
	--				WHERE HU.tSalida IS NULL
	--				GROUP BY HU.matricula))
);
-- tCreacion > "tiempo apertura plazo inscripción"
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
	CHECK ( idS IS NULL or disponibilidad = true)
);


-- la fechaFin del abono que se cesiona es menor que la fechaIni del abono que se crea
-- Si hay fechaFin, ha de ser mayor que fechaIni
-- Si un abono ha caducado, no puede cederse
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