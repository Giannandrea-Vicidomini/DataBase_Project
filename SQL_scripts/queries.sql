USE steamdb;

#Elencare le mail di tutti gli account
Select Email
From Account;

#Elencare gli account ordinati per credito (nickname, email, credito)
Select Nickname, Email, Credito
From Account
Order by Credito;

#Elencare i giochi acquistati dall'account "Adeloth" (Titolo del gioco, Prezzo dell'acquisto)
Select Gioco.Titolo, Acquisto.Prezzo
From Acquisto Join Gioco
	On Acquisto.IDG = Gioco.ID_Gioco
Where Acquisto.Username = "Adeloth";

#Elencare per ogni account il totale speso (Nickname, Spesa totale)
Select Username, SUM(Prezzo) as Spesa_Totale
From Acquisto
Group by Username;

#Elencare gli account il cui Nickname inizia con la "S" (Nickname)
Select Nickname
From Account
Where Nickname Like "S%";

#Elencare gli account che hanno acquistato il gioco Rocket League (Nickname, Email, Prezzo)
Select Account.Nickname, Account.Email, Acquisto.Prezzo
From Acquisto Join Account
	On Acquisto.Username = Account.Nickname
Join Gioco
	On Acquisto.IDG = Gioco.ID_Gioco
Where Gioco.Titolo = "Rocket League";

#Elencare le aziende che hanno prodotto il gioco Dungeon Rushers ma non Robothorium (Nome, Partita IVA)
Select Azienda.Nome, Azienda.PartitaIVA
From Produzione Join Azienda
	On Produzione.PIVA = Azienda.PartitaIVA
Join Gioco
	On Produzione.IDG = Gioco.ID_Gioco
Where Gioco.Titolo = "Dungeon Rushers" AND Azienda.Nome NOT IN (
	Select Azienda.Nome
    From Produzione Join Azienda
		On Produzione.PIVA = Azienda.PartitaIVA
	Join Gioco
		On Produzione.IDG = Gioco.ID_Gioco
	Where Gioco.Titolo = "Robothorium"
);

#Calcolare il numero di assistenze effettuate da ogni dipendente negli anni 2017 e 2018 (Email assistente, #assistenze)
Select Assistente.Email, Count(*) as Numero_Assistenze
From Supporto Join Assistente
	On Supporto.Assistente = Assistente.ID_Assistente
Where Supporto.DataSupporto BETWEEN "2017-01-01" AND "2018-12-31"
Group by Assistente.Email;

#Calcolare il numero di assistenze effettuate da ogni operatore donna (ID dipendente, #assistenze, sesso)
Select Dipendente.ID_Dipendente, Count(*) as Numero_Assistenze, Dipendente.Sesso
From Supporto Join Dipendente
	On Supporto.Assistente = Dipendente.ID_Dipendente
Where Dipendente.Sesso = "F"
Group by Dipendente.Nome, Dipendente.Cognome, Dipendente.Sesso;

#Per ogni account con 6 o più acquisti, determinare la spesa media (Nickname, #acquisti, spesa media)
Select Account.Nickname, Count(*) as Numero_Acquisti, AVG(Acquisto.Prezzo) as Spesa_Media
From Acquisto Join Account
	On Acquisto.Username = Account.Nickname
Group by Account.Nickname
Having Numero_Acquisti > 6;

#Determinare l'account con il maggior numero di acquisti (Nickname, #acquisti)
	#Script per la creazione della view
Create View VISTA_NACQUISTI as (
	Select Account.Nickname, Count(*) as Numero_Acquisti
    From Acquisto Join Account
		On Acquisto.Username = Account.Nickname
	Group by Account.Nickname
);

	#Esecuzione (necessaria l'esistenza della view)
Select *
From VISTA_NACQUISTI
Where Numero_Acquisti = (
	Select Max(Numero_Acquisti)
    From VISTA_NACQUISTI
);

#Determinare l'account che ha ricevuto il maggior numero di assistenze
	#Script per la creazione della view
Create View VISTA_NASSISTENZE as (
	Select Account.Nickname, Count(*) as Numero_Assistenze
    From Supporto Join Account
		On Supporto.Account = Account.Nickname
	Group by Account.Nickname
);

	#Esecuzione (necessaria l'esistenza della view)
Select *
From VISTA_NASSISTENZE
Where Numero_Assistenze = (
	Select Max(Numero_Assistenze)
    From VISTA_NASSISTENZE
);

#Elencare i dipendenti che gestiscono tutti i giochi - Seleziona tutti i dipendenti per i quali non c'è un gioco senza loro assistenza
Select Dipendente.Nome
From Dipendente
Where not exists (
	Select Gioco.Titolo
	From Gioco
    Where not exists (
		Select *
        From Gestione
        Where Gestione.Dipendente = Dipendente.ID_Dipendente AND Gestione.Gioco = Gioco.ID_Gioco
    )
);



	#Script per la cancellazione della view
Drop View VISTA_NASSISTENZE;


	#Script per la cancellazione della view
Drop View VISTA_NACQUISTI;