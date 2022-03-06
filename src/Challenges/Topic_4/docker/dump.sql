create table docker_academy_db.ROLE (
    id_role bigint PRIMARY KEY,
    name_role varchar(255)
);

create table docker_academy_db.USER (
    identification_number bigint PRIMARY KEY,
    username varchar(255),
    name varchar(255),
    last_name varchar(255),    
    address varchar(255),
    zip_code_city varchar(255),
    state varchar(255),
    country varchar(255),
    UNIQUE KEY `unique_username_constraint` (`username`)
);

CREATE TABLE docker_academy_db.USER_ROLES (
  `identification_number` bigint NOT NULL,
  `id_role` bigint NOT NULL,
  KEY `id_role_PK` (`id_role`),
  KEY `identification_number_FK` (`identification_number`),
  CONSTRAINT `identification_number_FK` FOREIGN KEY (`identification_number`) REFERENCES `USER` (`identification_number`),
  CONSTRAINT `id_role_PK` FOREIGN KEY (`id_role`) REFERENCES `ROLE` (`id_role`)
);

insert into docker_academy_db.ROLE (id_role, name_role) values (1, "STANDARD");
insert into docker_academy_db.ROLE (id_role, name_role) values (2, "ADMIN");

insert into docker_academy_db.USER (username, name, last_name, identification_number, address, zip_code_city, state, country) values ('igascone0', 'Iggy', 'Gascone', '6378585115', '62620 Old Shore Street', '110915', 'Yutan', 'China');
insert into docker_academy_db.USER (username, name, last_name, identification_number, address, zip_code_city, state, country) values ('jhabbijam1', 'Joan', 'Habbijam', '5275793325', '746 Declaration Trail', '110640', 'Johor Bahru', 'Malaysia');
insert into docker_academy_db.USER (username, name, last_name, identification_number, address, zip_code_city, state, country) values ('sledner2', 'Sybille', 'Ledner', '0647892403', '35 Scoville Parkway', '110152', 'Wanmao', 'China');
insert into docker_academy_db.USER (username, name, last_name, identification_number, address, zip_code_city, state, country) values ('cdebeauchemp3', 'Carlyn', 'De Beauchemp', '6799282820', '10 Welch Parkway', '110786', 'As Sālimīyah', 'Kuwait');
insert into docker_academy_db.USER (username, name, last_name, identification_number, address, zip_code_city, state, country) values ('krhelton4', 'Kris', 'Rhelton', '6208791017', '5 Donald Point', '110476', 'Soloma', 'Guatemala');
insert into docker_academy_db.USER (username, name, last_name, identification_number, address, zip_code_city, state, country) values ('apearlman5', 'Amil', 'Pearlman', '8407711455', '2 Randy Park', '110780', 'Vân Đình', 'Vietnam');
insert into docker_academy_db.USER (username, name, last_name, identification_number, address, zip_code_city, state, country) values ('hlarge6', 'Hugo', 'Large', '3589758281', '8299 Gerald Center', '110316', 'Krylovskaya', 'Russia');
insert into docker_academy_db.USER (username, name, last_name, identification_number, address, zip_code_city, state, country) values ('pknewstubb7', 'Prentice', 'Knewstubb', '7939277129', '83 Everett Crossing', '110307', 'La Eduvigis', 'Argentina');
insert into docker_academy_db.USER (username, name, last_name, identification_number, address, zip_code_city, state, country) values ('gmaylam8', 'Gretchen', 'Maylam', '1187376352', '9092 Spenser Terrace', '110993', 'Imtarfa', 'Malta');
insert into docker_academy_db.USER (username, name, last_name, identification_number, address, zip_code_city, state, country) values ('fitzhayek9', 'Fanechka', 'Itzhayek', '7759386748', '13209 Marquette Avenue', '110792', 'Daxing', 'China');

insert into docker_academy_db.USER_ROLES (identification_number, id_role) values (647892403, 2);
insert into docker_academy_db.USER_ROLES (identification_number, id_role) values (1187376352, 1);
insert into docker_academy_db.USER_ROLES (identification_number, id_role) values (3589758281, 2);
insert into docker_academy_db.USER_ROLES (identification_number, id_role) values (5275793325, 1);
insert into docker_academy_db.USER_ROLES (identification_number, id_role) values (6208791017, 1);
insert into docker_academy_db.USER_ROLES (identification_number, id_role) values (6378585115, 1);
insert into docker_academy_db.USER_ROLES (identification_number, id_role) values (6799282820, 1);
insert into docker_academy_db.USER_ROLES (identification_number, id_role) values (7759386748, 1);
insert into docker_academy_db.USER_ROLES (identification_number, id_role) values (7939277129, 1);
insert into docker_academy_db.USER_ROLES (identification_number, id_role) values (8407711455, 1);


CREATE USER 'accessUser'@'%' IDENTIFIED BY 'accessUserPass';
GRANT ALL PRIVILEGES ON docker_academy_db.* TO 'accessUser'@'%';