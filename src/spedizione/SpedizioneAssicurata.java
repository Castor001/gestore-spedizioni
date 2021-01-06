package spedizione;

/**
 * Class that represent an insured shipment.
 * 
 * An insured shipment adds the field:
 * <ul>
 * 	<li>value	</li>
 * </ul>
 * 
 * @author &#160; &#160; Castorini Francesco
 * @see Spedizione
 */
public class SpedizioneAssicurata extends Spedizione{
	
	/**
	 * Default serial version UID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Value of shipment
	 */
	private String valore;
	
	/**
	 * Create a new insured shipment. 
	 * 
	 * @param username User's username
	 * @param destinazione Shipment destination
	 * @param peso Weight shipment
	 * @param valore Value of shipment
	 */
	public SpedizioneAssicurata(String username, String destinazione, String peso, String valore) {
		super(username, destinazione, peso);
		this.setValore(valore);
	}
	
	/**
	 * Set value. 
	 * @param valore value of shipment
	 */
	private void setValore(String valore) {
		this.valore = valore;
	}
	
	/**
	 * Get value.
	 * @return value of insured shipment
	 */
	public String getValore() {
		return this.valore;
	}

	/**
	 * Get the string representation of an insured shipment.
	 */
	@Override
	public String toString() {
		return getCodice() + " " + getDestinazione() + " " + getPeso() + " " + getDate() + " " + getStato() + " " + getValore(); 
	}
}
