package main;

import java.io.File;
import java.io.IOException;

import gui.Login;
import logic.Serializza;
import spedizione.Spedizione;
import logic.Archivio;
/**
 * The main class of the program.
 * 
 * @author &#160; &#160; Castorini Francesco
 *
 */
public class Main {
	/**
	 * Main of the program
	 * @param args (not used)
	 */
	public static void main(String[] args) {
		/*
		 * Controlla se esiste il file Archivio.ser. 
		 * Se non esiste lo crea serializzando un archivio vuoto
		 */
		File f = new File("Archivio.ser");
		//File f1 = new File("users.txt");
		if (!f.exists()) {
			Archivio<Spedizione> a = new Archivio<Spedizione>();

			Serializza s = new Serializza();
			s.serialize(a);
		}
		File f2 = new File("users.txt");
		try {
			f2.createNewFile(); 
		} catch (IOException e) {
			System.out.println("Errore");
			e.printStackTrace();
			System.exit(-2);
		}
		/*
		 * Richiama il frame Login 
		 */
		new Login(true);
	}
}
