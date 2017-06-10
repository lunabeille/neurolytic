
CREATE TABLE Employee(
	id_employee  INTEGER NOT NULL ,
	name         TEXT NOT NULL ,
	firstname    TEXT NOT NULL ,
	sexe         TEXT NOT NULL ,
	birthdate    NUMERIC ,
	id_service   INTEGER NOT NULL ,
	PRIMARY KEY (id_employee) ,
	
	FOREIGN KEY (id_service) REFERENCES Service(id_service)
);



CREATE TABLE Service(
	id_service  INTEGER NOT NULL ,
	name        TEXT NOT NULL ,
	PRIMARY KEY (id_service)
);



CREATE TABLE Dataset(
	id_data       INTEGER NOT NULL ,
	data          REAL NOT NULL ,
	measure_date  NUMERIC NOT NULL ,
	id_employee   INTEGER ,
	id_task       INTEGER NOT NULL ,
	PRIMARY KEY (id_data) ,
	
	FOREIGN KEY (id_employee) REFERENCES Employee(id_employee),
	FOREIGN KEY (id_task) REFERENCES Task(id_task)
);


CREATE TABLE Task(
	id_task    INTEGER  NOT NULL ,
	libelle    TEXT ,
	criticity  INTEGER ,
	PRIMARY KEY (id_task)
);


