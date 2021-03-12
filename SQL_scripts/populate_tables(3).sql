/**************************************

Populates the database with default data

**************************************/

#AZIENDE
insert into Azienda values ("13579246810","Activision","SpA","San Diego");
insert into Azienda values ("02468101397","Blizzard","Sas","Irvine");
insert into Azienda values ("10111213141","RockStar","Srl","New York");
insert into Azienda values ("08944524578","SuperCell","SpA","Helsinki");
insert into Azienda values ("19283746501","Bungie","Snc","Chicago");

#ACCOUNT
insert into Account values("SilverDragon","silverdragon@gmail.com", "silverdragon","Italia","Campania","Nocera", 20.00);
insert into Account values("Sevurith","sevurith@gmail.com", "sevurith","Italia","Campania","Pagani", 50.00);
insert into Account values("Domatrem","domatrem@gmail.com", "domatrem","Italia","Campania","Salerno", 5.00);
insert into Account values("LadyKaty","ladyKaty@gmail.com", "ladyKaty","Italia", "Campania","Angri",20.00);
insert into Account values("Adeloth","adeloth@gmail.com", "adeloth","Italia","Campania","Torre", 20.00);


#GIOCHI
Insert into Gioco values ("G0001", "DayZ", "Un virus sconosciuto ha colpito il paese post-sovietico di Chernarus, tramutandone la popolazione in infetti deliranti.", "2018-12-13");
Insert into Gioco values ("G0002", "Rocket League", "Il calcio incontra ancora una volta le corse automobilistiche.", "2015-07-07");
Insert into Gioco values ("G0003", "Prey", "Indossi i panni dell’oggetto di un esperimento destinato a cambiare per sempre l’umanità.", "2017-05-04");
Insert into Gioco values ("G0004", "Arizona Sunshine", "Arizona Sunshine ti abbandona nel mezzo dell’apocalisse zombie.", "2016-12-06");
Insert into Gioco values ("G0005", "Book of Demon", "Componi e scatena il tuo mazzo di carte magiche per affrontare le armate delle tenebre.", "2018-12-13");
Insert into Gioco values ("G0006", "Insurgency", "Prova tutta l’intensità dei combattimenti moderni dove l'abilità viene premiata.", "2018-12-12");
Insert into Gioco values ("G0007", "Street Fighter V", "Vivi l'emozione di un combattimento.", "2016-02-16");
Insert into Gioco values ("G0008", "Left 4 Dead 2", "Ambientato durante l'apocalisse dei mortiviventi.", "2009-11-17");
Insert into Gioco values ("G0009", "Dungeon Rushers", "Costruisci il tuo dungeon nascondendo trappole e mostri nelle tue gallerie e sfida gli altri giocatori!", "2016-09-06");
Insert into Gioco values ("G0010", "Robothorium", "Crea il tuo team di robot, scegli i tuoi alleati e guida le tue truppe alla prossima era robotica.", "2018-06-07");
Insert into Gioco values ("G0011", "Tom Clancy", "Spara ai terroristi e non lasciarli liberi di far ciò che vogliono.", "2015-12-01");
Insert into Gioco values ("G0012", "Skyrim VR", "Skyrim VR è un enorme gioco a mondo aperto che ripropone l'indimenticabile capolavoro fantasy.", "2018-04-03");
Insert into Gioco values ("G0013", "SuperHot", "Perdi la nozione di cosa è reale. Coinvolgi il corpo e la mente.", "2017-05-25");
Insert into Gioco values ("G0014", "Ride 3", "Corri su diversi tracciati sparsi in tutto il mondo, prova l'ebbrezza della velocità sui tuoi modelli preferiti.", "2018-11-30");
Insert into Gioco values ("G0015", "Gris", "Gris è una ragazza piena di speranze e persa nel suo mondo, che si trova a vivere un'esperienza molto difficile.", "2018-12-13");
Insert into Gioco values ("G0016", "The Long Dark", "Luci intense squarciano il buio della notte. Il vento urla contro le sottili pareti della baita. Un lupo ulula in lontananza.", "2017-08-01");
Insert into Gioco values ("G0017", "Subnautica", "Scendi nelle profondità di un mondo alieno sommerso pieno di meraviglie e pericoli.", "2018-01-28");
Insert into Gioco values ("G0018", "Poltergeist", "Ti sei mai domandato cosa sentiresti se fossi un essere spettrale?", "2014-10-21");
Insert into Gioco values ("G0019", "Please, don't touch", "Un gioco che ha a che fare con le pause toilette, i cacciaviti e le esplosioni nucleari.", "2015-03-15");
Insert into Gioco values ("G0020", "Metro Exodus", "Fuggi dalle rovine della metropolitana di Mosca e intraprendi un viaggio epico.", "2017-02-15");

#GENERE
Insert into Genere values ("G0001","Horror");
Insert into Genere values ("G0002","Azione");
Insert into Genere values ("G0003","Fps");
Insert into Genere values ("G0004","Horror");
Insert into Genere values ("G0005","RPG");
Insert into Genere values ("G0006","Avventura");
Insert into Genere values ("G0007","Sparatutto");
Insert into Genere values ("G0008","FPS");
Insert into Genere values ("G0008","Horror");
Insert into Genere values ("G0009","Roguelike");
Insert into Genere values ("G0010","Azione");
Insert into Genere values ("G0011","Infiltrazione");
Insert into Genere values ("G0012","RPG");
Insert into Genere values ("G0012","Fantasy");
Insert into Genere values ("G0013","Sci-fi");
Insert into Genere values ("G0014","Sport");
Insert into Genere values ("G0015","RPG");
Insert into Genere values ("G0016","Avventura");
Insert into Genere values ("G0017","Horror");
Insert into Genere values ("G0018","Horror");
Insert into Genere values ("G0019","Horror");
Insert into Genere values ("G0020","Azione");

#ACQUISTI
insert into Acquisto values ("G0001", "SilverDragon", "2018-12-15", 45.99);
insert into Acquisto values ("G0002", "Sevurith", "2016-05-20", 24.99);
insert into Acquisto values ("G0003", "Domatrem", "2017-11-14", 19.99);
insert into Acquisto values ("G0004", "LadyKaty", "2017-02-18", 26.80);
insert into Acquisto values ("G0005", "Adeloth", "2018-12-14", 49.99);
insert into Acquisto values ("G0006", "SilverDragon", "2018-12-16", 56.70);
insert into Acquisto values ("G0007", "Domatrem", "2017-05-16", 24.50);
insert into Acquisto values ("G0008", "Adeloth", "2012-09-29", 5.00);
insert into Acquisto values ("G0009", "SilverDragon", "2017-08-27", 14.60);
insert into Acquisto values ("G0010", "Sevurith", "2016-10-12", 18.90);
insert into Acquisto values ("G0011", "LadyKaty", "2018-08-26", 20.00);
insert into Acquisto values ("G0012", "SilverDragon", "2018-06-14", 45.30);
insert into Acquisto values ("G0013", "Adeloth", "2018-08-12", 70.00);
insert into Acquisto values ("G0014", "SilverDragon", "2019-01-02", 25.70);
insert into Acquisto values ("G0015", "Domatrem", "2019-01-01", 32.99);
insert into Acquisto values ("G0016", "LadyKaty", "2017-10-13", 21.50);
insert into Acquisto values ("G0017", "LadyKaty", "2018-05-17", 30.00);
insert into Acquisto values ("G0018", "Adeloth", "2016-12-11", 12.80);
insert into Acquisto values ("G0019", "Sevurith", "2017-10-09", 10.00);
insert into Acquisto values ("G0020", "Sevurith", "2017-03-15", 28.90);
insert into Acquisto values ("G0005", "LadyKaty", "2018-12-22", 49.99);
insert into Acquisto values ("G0007", "SilverDragon", "2018-01-15", 24.50);
insert into Acquisto values ("G0014", "Domatrem", "2019-01-01", 28.90);
insert into Acquisto values ("G0019", "Domatrem", "2018-02-13", 12.80);
insert into Acquisto values ("G0020", "SilverDragon", "2017-07-29", 30.00);
insert into Acquisto values ("G0006", "LadyKaty", "2018-12-21", 55.40);
insert into Acquisto values ("G0014", "Adeloth", "2019-01-01", 28.90);
insert into Acquisto values ("G0020", "Domatrem", "2017-09-27", 40.00);
insert into Acquisto values ("G0017", "Adeloth", "2018-07-18", 32.50);
insert into Acquisto values ("G0013", "Sevurith", "2018-10-23", 68.40);
insert into Acquisto values ("G0002", "SilverDragon", "2017-12-28", 15.70);
insert into Acquisto values ("G0001", "LadyKaty", "2018-12-17", 45.99);
insert into Acquisto values ("G0015", "Adeloth", "2019-01-01", 32.99);
insert into Acquisto values ("G0019", "SilverDragon", "2015-07-29", 28.70);
insert into Acquisto values ("G0016", "Domatrem", "2017-12-11", 24.70);
insert into Acquisto values ("G0008", "SilverDragon", "2010-10-25", 8.50);


#DIPENDENTI
insert into Dipendente(ID_Dipendente, Nome, Cognome, Sesso) values ("D0001", "Davide", "De Lucia", "M");
insert into Dipendente(ID_Dipendente, Nome, Cognome, Sesso) values ("D0002", "Martina", "Senatore", "F");
insert into Dipendente(ID_Dipendente, Nome, Cognome, Sesso) values ("D0003", "Lucia", "Memoli", "F");
insert into Dipendente(ID_Dipendente, Nome, Cognome, Sesso) values ("D0004", "Francesco", "Sorrentino", "M");
insert into Dipendente(ID_Dipendente, Nome, Cognome, Sesso) values ("D0005", "Luca", "Conti", "M");
insert into Dipendente(ID_Dipendente, Nome, Cognome, Sesso) values ("D0006", "Aligi", "Comandini", "M");
insert into Dipendente(ID_Dipendente, Nome, Cognome, Sesso) values ("D0007", "Michele", "Poggi", "M");

#ASSISTENTI
insert into Assistente(ID_Assistente,Email) values ("D0001","ddelucia@gmail.com");
insert into Assistente(ID_Assistente,Email) values ("D0002","msenatore@gmail.com");
insert into Assistente(ID_Assistente,Email) values ("D0003","lmemoli@gmail.com");
insert into Assistente(ID_Assistente,Email) values ("D0004","fsorrentino@gmail.com");
insert into Assistente(ID_Assistente,Email) values ("D0005","lconti@gmail.com");

#SUPPORTO
insert into Supporto values ("D0001","SilverDragon", "2018-02-24");
insert into Supporto values ("D0002","Domatrem", "2017-05-25");
insert into Supporto values ("D0003","LadyKaty", "2017-12-19");
insert into Supporto values ("D0004","Adeloth", "2016-11-27");
insert into Supporto values ("D0005","Domatrem", "2018-09-26");
insert into Supporto values ("D0005","LadyKaty", "2017-04-15");
insert into Supporto values ("D0002","Sevurith", "2016-08-16");
insert into Supporto values ("D0003","Domatrem", "2018-09-12");
insert into Supporto values ("D0001","LadyKaty", "2019-01-02");
insert into Supporto values ("D0004","SilverDragon", "2017-05-29");
insert into Supporto values ("D0003","Domatrem", "2017-04-27");
insert into Supporto values ("D0004","Adeloth", "2016-11-19");
insert into Supporto values ("D0005","Sevurith", "2018-07-23");


#INSERIMENTO GESTIONE
insert into Gestione values("D0003","G0001");
insert into Gestione values("D0005","G0002");
insert into Gestione values("D0002","G0003");
insert into Gestione values("D0001","G0004");
insert into Gestione values("D0004","G0005");
insert into Gestione values("D0003","G0006");
insert into Gestione values("D0005","G0007");
insert into Gestione values("D0002","G0008");
insert into Gestione values("D0001","G0009");
insert into Gestione values("D0004","G0010");
insert into Gestione values("D0003","G0011");
insert into Gestione values("D0005","G0012");
insert into Gestione values("D0002","G0013");
insert into Gestione values("D0001","G0014");
insert into Gestione values("D0004","G0015");
insert into Gestione values("D0003","G0016");
insert into Gestione values("D0005","G0017");
insert into Gestione values("D0002","G0018");
insert into Gestione values("D0001","G0019");
insert into Gestione values("D0004","G0020");

#PRODUZIONE
insert into Produzione values ("08944524578", "G0005");
insert into Produzione values ("08944524578", "G0006");
insert into Produzione values ("02468101397", "G0016");
insert into Produzione values ("02468101397", "G0019");
insert into Produzione values ("19283746501", "G0007");
insert into Produzione values ("19283746501", "G0011");
insert into Produzione values ("13579246810", "G0012");
insert into Produzione values ("13579246810", "G0001");
insert into Produzione values ("10111213141", "G0002");
insert into Produzione values ("10111213141", "G0003");
insert into Produzione values ("19283746501", "G0008");
insert into Produzione values ("19283746501", "G0015");
insert into Produzione values ("08944524578", "G0004");
insert into Produzione values ("08944524578", "G0010");
insert into Produzione values ("10111213141", "G0009");
insert into Produzione values ("10111213141", "G0013");
insert into Produzione values ("13579246810", "G0014");
insert into Produzione values ("13579246810", "G0018");
insert into Produzione values ("02468101397", "G0017");
insert into Produzione values ("02468101397", "G0020");

