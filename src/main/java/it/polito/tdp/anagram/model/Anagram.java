package it.polito.tdp.anagram.model;

import java.util.ArrayList;
import java.util.List;

public class Anagram {
	
	public List<String> anagrammi(String parola) {
		
		//aggiungiamo struttura dati in cui i risultati vengono via via depositati ricorsivamente
		List<String> risultato = new ArrayList<>();
		
		permuta("", parola, 0, risultato); //lancia la ricorsione
		
		return risultato;
	}

	//livello = lunghezza della soluzione parziale finora costruita
	//livello iniziale = 0
	//parziale = stringa che contiene l'angramma incompleto in fase di costruzione
	//lettere = le lettere della parola iniziale che ancora non abbiamo utilizzato =
	//        =  il sottoproblema che dobbiamo risolvere
	//risultato = lista che contiene gli anagrammi trovati
	
	private void permuta(String parziale, String lettere, int livello, List<String> risultato) {
		
		if (lettere.length()==0) { //caso terminale, punto A
			
			//la soluzione parziale è anche una soluzione completa!
			
			//solo nel caso terminale allora aggiungiamo alla lista il risultato trovato!!!
			// non abbiamo il punto E, azioni da eseguire sempre
			
	// -----> 1 se volessimo controllare di aggiungere solo parole valide potremmo qui verificare che parziale
	//          esiste nel dizionario
			
			risultato.add(parziale);
			
		}else {
			
			//fare ricorsione
			//sottoproblema == una lettera (un singolo carattere) della variabile 'lettere'
			// andiamo ad aggiungere una alla volta le lettere ancora da analizzare con il for
			
			for (int pos=0; pos<lettere.length(); pos++) {
				
				// estraiamo la lettera da aggiungere alla soluzione parziale di questo livello
				char tentativo = lettere.charAt(pos);
				
				//anziche modificare le nostre variabile parziale e lettere ne creiamo di nuove
				//perche non inficia efficienza IN QUESTO CASO perche sono solo stringhe, così non
				//serve backtracking
				
				//componiamo anagramma, punto B, nuova soluzione su cui fare ricorsione
				String nuovaParziale = parziale + tentativo;
				
				//rimuoviamo la lettera aggiunta dalla lista di quelle ancora da aggiungere
				
				//perche in realta substring, 0 pos, si ferma a pos-1, quindi stiamo togliendo la lettera in posizione pos
				String nuovaLettere = lettere.substring(0, pos)+lettere.substring(pos+1); //togli il carattere pos da lettere;
					
	// -----> 2 se volessimo controllare di aggiungere solo parole valide potremmo qui verificare che nuovaParziale
	//          esiste nel dizionario come PREFISSO per tagliare un intero ramo, perche se non fosse sappiamo gia cosi che
	//          le soluzioni che ne nascerebbero non sarebbero valide
				
				//RICORSIONE con nuovi dati appena costruiti
				permuta(nuovaParziale, nuovaLettere, livello+1, risultato);
				
				//BACKTRACKING SERVE PER RIMETTERE A POSTO LE VARIBILI DENTRO IL FOR SE LE ABBIAMO MODIFICATE
				//SIAMO DENTRO UN FOR SAREBBE IMPAZZITO SE NO
				//in questo caso non serve perche abbiamo creato nuove variabili anziche modificarle
				//rimetti a posto 'parziale'
				//rimetti a posto 'lettere'
			}
		}
	}
}
