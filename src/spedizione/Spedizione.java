package spedizione;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Class that represent a normal shipment.
 * 
 * <p>
 * A normal shipment consists of the following fields:
 * <ul>
 * 	<li>Code</li>
 * 	<li>Destination</li>
 *	<li>Weight</li>
 *	<li>Date</li>
 *	<li>Status</li>
 * </ul>
 * 
 * @author &#160; &#160; Castorini Francesco
 *
 */
public class Spedizione implements Serializable{
	/**
	 * Default serial version UID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The user's username that insert the shipments.
	 */
	private String username;
	/**
	 * Code
	 */
	protected String codice;
	/**
	 * Destination
	 */
	protected String destinazione;
	/**
	 * Weight
	 */
	protected String peso;
	/**
	 * Date
	 */
	protected  String data;
	/**
	 * The status of shipment.
	 * <p>
	 * The status can assume these values:
	 * <ul>
	 * 	<li>In preparazione</li>
	 * 	<li>In transito</li>
	 * 	<li>Ricevuta o fallita</li>
	 * </ul>
	 * <p>
	 * If the shipment is insured there are these two states in addition to those described above:
	 * <ul>
	 * 	<li>Rimborso richiesto</li>
	 * 	<li>Rimborso erogato</li>
	 * </ul>
	 */
	protected String stato;
	
	
	/**
	 * Maximum length for the string "Destination" when constructing the code field.
	 */
	private static final int LUNG_MAX_DEST = 3; 
	/**
	 * Patter for formatting the date. 
	 */
	private static final String PATTERN = "dd/MM/yyyy"; 
	/**
	 * Code field delimiter. 
	 */
	private static final String DEL = "-"; 
	
	
	/**
	 * Create a new shipment from a username, a destination and a weight.
	 * @param username User's username. 
	 * @param destinazione Shipment destination.
	 * @param peso Shipment weight.
	 */
	public Spedizione(String username,  String destinazione, String peso/*, String valore*/) {
		super();
		this.username = username;
		this.setDestinazione(destinazione);
		this.setPeso(peso);
		setDate();
		this.stato = "in-preparazione";
		setCodice(username);
		//this.valore = valore;
	}
	
	/**
	 * Set the code, starting from a username in input.
	 * @param username User's username
	 */
	protected void setCodice(String username) {
		String codice = "";
		codice += username;
		codice += DEL;
		
		for (int i = 0; i < LUNG_MAX_DEST; i++) {
			codice += getDestinazione().charAt(i);
		}
		
		codice += DEL;
		
		codice += data;
		Random random = new Random();
		int rand = random.nextInt(999);
		codice += DEL;
		codice += String.valueOf(rand);
		
		this.codice = codice;
	}
	
	/**
	 * Set destination. 
	 * @param destinazione the new destination.
	 */
	public void setDestinazione(String destinazione) {
		this.destinazione = destinazione;
	}
	
	/**
	 * set weight 
	 * @param peso the new weight.
	 */
	public void setPeso(String peso) {
		this.peso = peso;
	}
	
	/**
	 * Set the current date. 
	 * 
	 */
	protected void setDate() {
		SimpleDateFormat sdf = new SimpleDateFormat(PATTERN);
		this.data = sdf.format(new Date());
	}
	
	/**
	 * Set status.
	 * @param newState the new status of shipment
	 */
	public void setStato(String newState) {
		this.stato = newState;
	}

	/**
	 * Get username of user that insert a shipment.
	 * @return user's username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * Get code.
	 * @return code of shipment
	 */
	public String getCodice() {
		return codice;
	}

	/**
	 * Get the destination.
	 * @return destination of shipment
	 */
	public String getDestinazione() {
		return destinazione;
	}

	/**
	 * Get the weight.
	 * @return weight of shipment
	 */
	public String getPeso() {
		return peso;
	}

	/**
	 * Get the date.
	 * @return date of shipment
	 */
	public String getDate() {
		return this.data;
	}
	
	/**
	 * Get the status.
	 * @return status of shipment
	 */
	public String getStato() {
		return this.stato;
	}
	
	/**
	 * Get the string representation of a shipment.
	 */
	public String toString() {
		return getCodice() + " " + getDestinazione() + " " + getPeso() + " " + getDate() + " " + getStato(); 
	}
}
