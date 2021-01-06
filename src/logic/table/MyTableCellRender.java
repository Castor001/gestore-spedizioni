package logic.table;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 * Class for implementing logic to color the cell based on the status of a shipment.
 * 
 * @author &#160; &#160; Castorini Francesco
 * @see TableCellRenderer
 */
public class MyTableCellRender implements TableCellRenderer {

	/**
	 * Constant that indicates the default render of the table.
	 */
	private static final DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
	
	/**
	 * 	
	 */
	public MyTableCellRender() {
		
	}
	
	/**
	 * Method for coloring a cell based on the status of a shipment
	 * @see spedizione.Spedizione
	 */
	/*
	 * @see https://www.youtube.com/watch?v=MHIPQdM5_f4
	 */
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		/*
		 * Imposta le righe del modello che devono essere selezionate
		 */
		table.setRowSelectionAllowed(true);
		
		/*
		 * Recuperoa il componente di default della tabella
		 */
		Component c = renderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		
		/*
		 * Instanzio una variabile di tipo Color per gestire il colore 
		 */
		Color color = null;
		
		/*
		 * Se la colonna � quella dello stato (4) recupera il valore della cella e la converto in una stringa;
		 * per ogni stato assegna un valore alla variabile color e infine imposta il colore di background 
		 */
		if (column == 4) {
			Object result = table.getModel().getValueAt(row, column);
			
			String stato = result.toString();
			//System.out.println(stato);
			
			switch(stato) {
			case "in-preparazione": 
				color = Color.GREEN;
				break;
			case "in-transito":
				color = Color.YELLOW;
				break;
			case "fallita":
				color = Color.RED;
				break;
			case "ricevuta":
				color = Color.PINK;
				break;
			case "rimborso-richiesto":
				color = Color.ORANGE;
				break;
			case "rimborso-erogato":
				color = Color.MAGENTA;
				break;
			default: 
				color = Color.WHITE;
			}
		}
		c.setBackground(color);
		
		/*
		 * ritorna il componente modificato
		 */
		return c;
	}
}
