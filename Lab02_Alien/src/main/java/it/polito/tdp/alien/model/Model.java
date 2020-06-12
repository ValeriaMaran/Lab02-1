package it.polito.tdp.alien.model;
import java.util.*;

public class Model {
	String p1Inserita;
	String p2Inserita;
	private Map <String, String> traduzioni;
	private Map<String, List<String>> traduzioniMultiple;
	List <String> traduzioniLista = new LinkedList<String>();
	
	
	
	public Model() {
		super();
		this.traduzioni = new TreeMap<String,String>();
		this.traduzioniMultiple = new TreeMap<String, List<String>>();
	}

	/**
	 * una volta inserite due parole, questo metodo aggiunge nel database la parola aliena e la rispettiva traduzione.
	 * @param p1Inserita parola aliena.
	 * @param p2Inserita traduzione parola aliena.
	 */
	public void addParole(String p1Inserita, String p2Inserita) {
		p1Inserita = p1Inserita.toLowerCase();
		p2Inserita = p2Inserita.toLowerCase();
		
		if(contieneNumeri(p1Inserita)==true) {
			throw new IllegalStateException("La parola inserita contiene un numero");
		}
		if(contieneNumeri(p2Inserita)) {
			throw new IllegalStateException("La parola inserita contiene un numero");
		}
		
		traduzioni.put(p1Inserita, p2Inserita);
	}
	public void addParoleMultiple(String p1Inserita,String p2Inserita) {
		p1Inserita.toLowerCase();
		p2Inserita.toLowerCase();
		if(contieneNumeri(p1Inserita)==true || contieneNumeri(p2Inserita)==true) {
			throw new IllegalStateException("La parola inserita contiene numeri non solo caratteri alfabetici");
		}
		if(traduzioniMultiple.containsKey(p1Inserita)==true) {
			traduzioniLista.add(p2Inserita);
			traduzioniMultiple.put(p1Inserita, traduzioniLista);
		}
		else{
			traduzioniLista.add(p2Inserita);
			traduzioniMultiple.put(p1Inserita, traduzioniLista);
		}
		System.out.print(traduzioniMultiple);
	}
	public List<String> traduzioniMultipleMetodo(String p1Inserita) {
		p1Inserita.toLowerCase();
		if(contieneNumeri(p1Inserita)==true) {
			throw new IllegalStateException("La parola inserita contiene numeri");
			
		}
		return traduzioniMultiple.get(p1Inserita);
	}
	/**
	 * Data una parola permette di cercare quella parola nella mappa, se presente da una traduzione altrimenti restituisce un eccezione.
	 * @param p1Inserita
	 * @return
	 */
	public String translateWord(String p1Inserita) {
		p1Inserita.toLowerCase();
		String s="";
		if(contieneNumeri(p1Inserita)==true) {
			throw new IllegalStateException("La parola inserita contiene un numero");
		}
		if(traduzioni.containsKey(p1Inserita)==true) {
			return s=traduzioni.get(p1Inserita);
		}
		else 
			s="La parola non è presente nel database";
			throw new IllegalStateException(s+"\n");
		
	}
	public void clearAll() {
		traduzioni.clear();
	}
	public boolean contieneNumeri(String parola) {
		for(int i = 0; i<parola.length(); i++) {
			if(Character.isDigit(parola.charAt(i))==true) {
				return true;
			}
		}
		return false;
	}
}
