/*********************************************************************
If you didn't create the db yet run reset_db(1).

This file generates all the tables, make sure the db is actually empty
if it isn't run reset_db(1)
**********************************************************************/

use steamDB;


create table Gioco (
	ID_Gioco char(5) primary key,
	Titolo varchar(20) not null,
	Descrizione varchar(200) not null,
    DataRilascio date not null
);

create table Account (
	Nickname varchar(20) primary key,
	Email varchar(30) not null,
    Password varchar(20) not null,
	Nazione varchar(20) not null,
    Regione varchar(20) not null,
    Città varchar(20) not null,
	Credito decimal(6,2) not null
);

create table Dipendente (
	ID_Dipendente char(5) primary key,
    Nome varchar(15) not null,
    Cognome varchar(15) not null,
    Sesso char(1) not null
);

create table Azienda (
	PartitaIVA char(11) primary key,
    Nome varchar(20) not null,
	Tipo varchar(5) not null,
	Luogo varchar(10) not null
);

create table Genere(
    ID_Gioco varchar(5) not null,
    NomeGenere varchar(25) not null,
    foreign key (ID_Gioco) references Gioco(ID_Gioco)
        on update cascade
        on delete cascade,
    primary key(ID_Gioco,NomeGenere)
);

create table Assistente(
    ID_Assistente char(5) primary key,
    Email varchar(30) not null,
    foreign key (ID_Assistente) references Dipendente(ID_Dipendente)
        on update cascade
        on delete cascade
);

create table Produzione (
	PIVA char(11),
    IDG char(5),
    foreign key (PIVA) references Azienda(PartitaIVA)
		on update cascade
        on delete cascade,
	foreign key (IDG) references Gioco(ID_Gioco)
		on update cascade
        on delete cascade,
	primary key(PIVA, IDG)
);

create table Acquisto (
	IDG char(5),
	Username varchar(20),
    DataAcquisto date not null,
    Prezzo decimal(5,2),
    foreign key (IDG) references Gioco(ID_Gioco)
		on update cascade
        on delete cascade,
	foreign key (Username) references Account(Nickname)
		on update cascade
        on delete cascade,
	primary key (IDG, Username)
);

create table Supporto (
	Assistente char(5),
    Account varchar(20),
    DataSupporto date,
    foreign key (Assistente) references Assistente(ID_Assistente)
		on update cascade
        on delete cascade,
	foreign key (Account) references Account(Nickname)
		on update cascade
        on delete cascade,
	primary key (Assistente, Account, DataSupporto)
);

create table Gestione (
	Dipendente char(5),
    Gioco char(5),
    foreign key (Dipendente) references Dipendente(ID_Dipendente)
		on update cascade
        on delete cascade,
	foreign key (Gioco) references Gioco(ID_Gioco)
		on update cascade
        on delete cascade,
	primary key(Dipendente, Gioco)
);