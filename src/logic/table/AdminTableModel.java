package logic.table;


import logic.Archivio;
import spedizione.Spedizione;
import spedizione.SpedizioneAssicurata;

/**
 * Class that implements the model of the table for viewing by the admin
 * 
 * @author &#160; &#160; Castorini Francesco
 * @see ArchivioTableModel
 */
public class AdminTableModel extends ArchivioTableModel {
	
	/**
	 * Default serial versio UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constant to enable debug info.
	 */
	private static final boolean DEBUG = false;
	
	
	/**
	 * Initializes the shipment archive to contain shipments. 
	 * @param a The shipment archive
	 */
	public AdminTableModel(Archivio<Spedizione> a) {
		super(a);
	}
	
	
	@Override
	public boolean isCellEditable(int row, int col) {
		
		if (String.valueOf(getValueAt(row, col)).equals("ricevuta") || String.valueOf(getValueAt(row, col)).equals("rimborso-erogato"))
			return false;
		
		if (col == 4)
			return true;
		
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
		
		Spedizione s;
		SpedizioneAssicurata sa;
		if (a.get(row) instanceof Spedizione) {
			s  = a.get(row);
			s.setStato(stato);
			a.set(row, s);
		}	
		else {
			sa = (SpedizioneAssicurata) a.get(row);
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


	/**
	 * Remove a row.
	 * @param index The row index to be deleted
	 */
	public void removeRow(int index) {
		System.out.println("==> eliminato " + index );
		a.remove(index);
		/*
		 * Dice alla tabella che i dati sono cambiati
		 */
		//fireTableDataChanged();
		fireTableRowsDeleted(index, index);
	}
	
	
	
	
}
