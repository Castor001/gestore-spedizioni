/**
 * <h2>A system shipping in Java </h2>
 * <p>
 * It includes a esay to use {@link javax.swing} GUI
 * </p>
 * <p>
 * Allows you to log in as a normal user or as an administrator 
 * </p>
 * <p>
 * 	<img src = "../doc-files/login.png" alt = ""/>
 * </p>
 * <p>
 * The administrator can:
 * <ul>
 * 	<li>Change the status of shipments</li>
 * 	<li>Cancel shipments that have already reached final status</li>
 * 	<li>Run a thread that every 3 seconds changes the value of a shipment taken randomly</li>
 * </ul>
 * 
 * <p>
 * 	<img src = "../doc-files/admin.png" alt = ""/>
 * </p>
 * <p>
 * A normal user can:
 * <ul>
 * 	<li>Enter a new shipment</li>
 * 	<li>View the status of shipments</li>
 * </ul>
 * <p>
 * 	<img src = "../doc-files/utente.png" alt = "" />
 * </p>
 * <br>
 * @author &#160; &#160; Castorini Francesco
 */
module spedizione {
	exports logic.table;
	exports spedizione;
	exports gui;
	exports main;
	exports logic;
	exports user;

	requires java.desktop;
}