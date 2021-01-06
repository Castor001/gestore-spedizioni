package logic.table;

import java.util.Random;

import logic.Archivio;
import spedizione.Spedizione;
import spedizione.SpedizioneAssicurata;


/**
 * Class that implements the model of the table for viewing by the admin and the state changes
 * every x seconds by a thread. 
 * 
 * @author &#160; &#160; Castorini Francesco
 */
public class AdminTableModeRunnable extends AdminTableModel implements Runnable{
	
	/**
	 * Default Serial Version UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Instance Random class
	 */
	private static Random rand = new Random(); 
	
	/**
	 * Initializes the shipment archive to contain shipments. 
	 * @param a The shipment archive
	 */
	public AdminTableModeRunnable(Archivio<Spedizione> a) {
		super(a);
	}
	
	@Override
	public boolean isCellEditable(int row, int col) {
		/*
		 * ATTENZIONE ! 
		 * LA TABELLA NON � MODIFICABILE DALL'UTENTE QUANDO STO ESEGUENDO IL THREAD 
		 */
		return false;
	}

	/**
	 * Each time it is called, the status of a shipment changes.
	 * 
	 * <p>
	 * Whenever it is called it pulls a random shipment from the shipment archive, check in
	 * what state the extracted shipment is.
	 * </p>
	 * <p>
	 * For the state it is in, it extracts 10 random numbers and averages them; extracts a random threshold number
	 * which controls whether to set the status of the shipment in question to the next status.
	 * </p>
	 */
	@Override
	public void run() {

		int soglia_rand;
		int prob = 0;
		int estratto;
		int c = 10;

		int randIndex = getRandom(a.size()); // estraggo numero random tra 0 e a.size()
		System.out.println("\tIndice: " + randIndex);

		if (a.get(randIndex).getStato().equals("in-preparazione")) {
			soglia_rand = getRandom(999);
			for (int i = 0; i < c; i++) {
				estratto = getRandom(999);
				prob += estratto;
			}
			prob /= c;
			System.out.println("Prob di Stato = in-preparazione " + prob + "   soglia_rand = " + soglia_rand);

			if (prob > soglia_rand) {
				System.out.println("ENTRO NELLO STATO IN-TRANSITO");
				a.get(randIndex).setStato("in-transito");
			}
		} else if (a.get(randIndex).getStato().equals("in-transito")) {// faccio fallire la spedizione
			soglia_rand = getRandom(999);
			// System.out.println("soglia => " + soglia);
			for (int i = 0; i < c; i++) {
				estratto = rand.nextInt(999);
				prob += estratto;
			}

			System.out.println("Prob di Stato = in-transito " + prob + "   soglia_rand = " + soglia_rand);
			prob /= c; // creo la media
			System.out.println("probabilit� = " + prob);

			if (prob > soglia_rand) {
				System.out.println("Entro nello stato fallito");
				a.get(randIndex).setStato("fallita");
			}
		}

		else if (a.get(randIndex).getStato().equals("fallita") && a.get(randIndex) instanceof SpedizioneAssicurata) {
			soglia_rand = getRandom(999);
			for (int i = 0; i < c; i++) {
				estratto = rand.nextInt(999);
				prob += estratto;
			}
			System.out.println("Prob di Stato = fallita " + prob + "   soglia_rand = " + soglia_rand);
			prob /= c; // creo la media if (prob > soglia_rand ) {
			System.out.println("Entro nello stato rimborso erogato");
			a.get(randIndex).setStato("rimborso-erogato");
		}

		fireTableCellUpdated(randIndex, 4);
	}
		
	
	
	/**
	 * Returns a random integer.
	 * @param bound the upper bound (exclusive). Must be positive
	 * @return Random int number
	 */
	private static int getRandom(int bound) {
		return rand.nextInt(bound);
	}
	
	
}
