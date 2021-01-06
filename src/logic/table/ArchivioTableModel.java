package logic.table;

import javax.swing.table.AbstractTableModel;

import logic.Archivio;
import spedizione.Spedizione;
import spedizione.SpedizioneAssicurata;

/**
 * Class that implements the model of the table for viewing by a normal user.
 * 
 * @author &#160; &#160; Castorini Francesco
 * @see AbstractTableModel
 * @see logic.Archivio
 */
public class ArchivioTableModel extends AbstractTableModel {
	
	/**
	 * Serial Default Version UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constant to enable debug info.
	 */
	private static final boolean DEBUG = false;
	
	/**
	 * Archive containing user shipments.
	 */
	protected Archivio<Spedizione> a;
	/**
	 * Table header
	 */
	protected String[] nomeColonna = {"codice", "destinazione", "peso", "data", "stato", "valore"};
	
	/**
	 * Initializes the shipment archive to contain shipments.
	 * @param a The shipment archive
	 */
	public ArchivioTableModel(Archivio<Spedizione> a) {
		this.a = a;
	}
	
	/**
	 * Return the number of row.
	 */
	@Override
	public int getRowCount() {
		return a.size();
	}
	
	/**
	 * Return the number of column. 
	 */
	@Override
	public int getColumnCount() {
		return nomeColonna.length;
	}
	
	/**
	 * Return the column name .
	 * @param col the number of row
	 */
	public String getColumnName(int col) {
		return nomeColonna[col];
	}

	/**
	 * Set the cell value to contain shipment data.
	 * <p>
	 * If the shipment is not insured, the value field remains empty.
	 */
	@Override
	public Object getValueAt(int row, int col) {
		
		switch(col) {
		case 0: return a.get(row).getCodice();
		case 1: return a.get(row).getDestinazione();
		case 2: return a.get(row).getPeso();
		case 3: return a.get(row).getDate();
		case 4: return a.get(row).getStato();
		case 5: 
			if (a.get(row) instanceof SpedizioneAssicurata)
				return ((SpedizioneAssicurata)a.get(row)).getValore();
			else 
				return "";
		}
		return null;
	}
	
	/**
	 * Check if a given cell is editable.
	 */
	public boolean isCellEditable(int row, int col) {
		
		
		if (col == 4)  {
			//System.out.println("==> sono nellla funzione isCellEditable");
			if (String.valueOf(getValueAt(row, col)).equals("fallita"))
				return true;
		}
		
		return false;
	}
	
	/**
	 * Setting a table cell to contain a given value.
	 */
	public void setValueAt(Object value, int row, int col) {
		
		if (DEBUG)
			System.out.println("Setting value at " + row + "," + col + " to " + value + " (an instance of "
					+ value.getClass() + ")");

		String stato = value.toString();
		
		
		SpedizioneAssicurata sa;
		if (a.get(row) instanceof SpedizioneAssicurata) {
			sa  = (SpedizioneAssicurata) a.get(row);
			sa.setStato(stato);
			a.set(row, sa);
		}	
		
		//dico alla tabella che i dati sono cambiati
		fireTableCellUpdated(row, col);
	
		if (DEBUG) {
			System.out.println("-----NUOVI DATI----");
			System.out.println("---STAMPO L'ARCHIVIO-----");
			for (int i = 0; i < a.size(); i++) {
				System.out.println("-->" + a.toString());
			}
		}
	}
}
