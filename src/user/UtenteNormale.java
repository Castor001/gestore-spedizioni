package user;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * Class that implements a normal user.
 * 
 * @author &#160; &#160; Castorini Francesco
 * @see AbstractUser
 */
public class UtenteNormale extends AbstractUser {

	/**
	 * Constant to define the name of the file where to save information about a user.
	 */
	private static final String FILE_PATH = "users.txt"; // file dove salvo nome utente, password e email
	
	/**
	 * Text scanner to read input from text files.
	 */
	private static Scanner x;
	
	/**
	 * 
	 */
	public UtenteNormale() {
	}

	@Override
	public boolean login(String username, String password) {
		File file = new File("");
		File f = new File(FILE_PATH);
		//System.out.println("Percorso del file: " + f.getPath());
		//System.out.println("Percorso assoluto del file: " + f.getAbsolutePath());
		boolean found = false;
		String tempUsername = "";
		String tempPassword = "";
		String tempEmail = ""; //questa variabile ci vuole per far leggere anche la mail, se non lo fai sballa la lettura
		//System.out.println(file.getAbsolutePath() + file.separator + "src" + file.separator + FILE_PATH);
		String s = file.getAbsolutePath() + File.separator + FILE_PATH;
		try {
			x = new Scanner(new File(s));
			x.useDelimiter("[,\n]");
			
			while (x.hasNext() && !found) {
				tempUsername = x.next();
				tempPassword = x.next();
				tempEmail = x.next(); //leggo anche la mail altrimenti mi sballa la lettura
				//System.out.println("HO LETTO: " + tempUsername + " " + tempPassword);
				
				if (tempUsername.trim().equals(username.trim()) && tempPassword.trim().equals(password.trim())) {
					//System.out.println(tempUsername + "  " + tempPassword);
					found = true;
				}
			}
			x.close();
		}
		catch(Exception e) {
			System.out.println("Errore di login ");
			//System.out.println(file.getAbsolutePath() + file.separator + "src" + file.separator + FILE_PATH);
			
			e.printStackTrace();
		}
		
		if (found)
			return true;
		return false;
	}

	/**
	 * Search username in the users file.
	 * 
	 * @param username lo username dell'utente.
	 * @return true se esiste un utente con quell'username, falso altrimenti.
	 */
	private boolean existUser(String username) {
		File file = new File("");
		boolean found = false;
		String tempUsername = "";
		String s = file.getAbsolutePath() + File.separator + FILE_PATH;
		try {
			x = new Scanner(new File(s));
			x.useDelimiter("[,\n]");

			while (x.hasNext() && !found) {
				tempUsername = x.next();

				if (tempUsername.trim().equals(username.trim()) ) {
					//System.out.println(tempUsername);
					found = true;
				}
			}
			x.close();
			
			if (found)
				return true;
		}
		catch(Exception e) {
			System.out.println("Errore nel cercare utente");
			//e.printStackTrace();
		}
		return false;
	}

	/**
	 * Save a user in the file.
	 * 
	 * @param username User's username.
	 * @param password User's password.
	 * @param email User's email.
	 * @return true if user saved, false if the user already exists.
	 */
	public boolean salvaUtente(String username, String password, String email) {
		
		if (existUser(username))
			return false;
		
		File file = new File("");
		String s = file.getAbsolutePath() + File.separator + FILE_PATH;
		try {
			FileWriter out = new FileWriter(s, true);
			
			out.write(username.trim()); //metodo trim restituisce una stringa con tutto lo spazio rimosso
			out.write(",");
			out.write(password.trim());
			out.write(",");
			out.write(email.trim());
			out.write("\n");
			out.flush();
			out.close();
			//System.out.println("Ho scritto sul file il nuovo utente ");
		}
		catch (Exception e) {
			System.out.println("Errore salva utente ");
			//e.printStackTrace();
		}
		
		return true;
	}
}
