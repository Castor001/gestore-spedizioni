package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.AbstractButton;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

import logic.Archivio;
import logic.Serializza;
import logic.table.AdminTableModeRunnable;
import logic.table.AdminTableModel;
import logic.table.MyTableCellRender;
import spedizione.Spedizione;

/**
 * Class that implements the display and management of shipments by the Admin.
 * 
 * @author &#160; &#160; Castorini Francesco
 * @see Spedizione
 * @see ActionListener
 */
public class GraficaSpedizioneAdmin {
	/**
	 * The frame for this class.
	 */
	private JFrame frame;
	/**
	 * The table.
	 */
	private JTable table;
	/**
	 * Logout button.
	 */
	private JButton logout;
	/**
	 * A toggle button to manage the thread.
	 */
	private JToggleButton toggleButton;
	/**
	 * The archive of shipment
	 */
	private Archivio<Spedizione> a;
	/**
	 * An instance of Serializza's class.
	 */
	private Serializza s = new Serializza();
	/**
	 * Check the toggleButton is checked or not.
	 */
	private boolean selected;
	/**
	 *A <a href = "https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/ExecutorService.html" >ExecutorService</a> that allows you to schedule a command
	 */
	private ScheduledExecutorService exec;
	/**
	 * The table's model.
	 */
	private AbstractTableModel tm;
	
	/**
	 * indicates whether the thread is running or not:
	 * true executed, falso otherwise.
	 */
	private boolean thread = false;
	/**
	 * Constant indicating the delay for the scheduledAtFixedRate.
	 */
	private static final int DELAY = 1; 
	/**
	 * Constant indicating how often the task repeats in the ScheduledAtFixedRate method.
	 */
	private static final int TIME = 3; 
	
	/**
	 * Constructor that builds the admin view.
	 * @param isVisible Indicates whether the frame is visible or not
	 */
	public GraficaSpedizioneAdmin(boolean isVisible) {
		a = s.getSerializable();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 450);
		frame.setTitle("RIEPILOGO SPEDIZIONI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		  frame.addWindowListener(new WindowAdapter() {
		  
			  @Override 
			  public void windowClosing(WindowEvent e) {
				  if (thread)
					  exec.shutdown(); //termino lo schedulerExecutorService
				  
				 // System.out.println("Salvo e chiudo il frame"); 
				  s.serialize(a); 
			  }
		  });
		 
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JLabel labelTitolo = new JLabel("RIEPILOGO SPEDIZIONI UTENTI");
		labelTitolo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelTitolo.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(labelTitolo, BorderLayout.NORTH);
		
		JPanel panelSud = new JPanel();
		frame.getContentPane().add(panelSud, BorderLayout.SOUTH);
		
		logout = new JButton("LOGOUT");
		logout.setHorizontalAlignment(SwingConstants.LEFT);
		logout.setHorizontalAlignment(SwingConstants.LEFT);
		
		logout.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if (thread)
					  exec.shutdown(); //termino lo schedulerExecutorService
				//System.out.println("Serializzo");
				s.serialize(a);
				
				frame.setVisible(false);
				new Login(true);
			}
		});
		
		logout.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelSud.add(logout);
		
		/*
		 * Creo le classi per il table model e imposto la tabella a contenere il table model non runnable
		 */
		AdminTableModel tm = new AdminTableModel(a);
		AdminTableModeRunnable tmr = new AdminTableModeRunnable(a);
		table = new JTable(tm);
		
		toggleButton = new JToggleButton("THREAD");
		toggleButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelSud.add(toggleButton);
		toggleButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				/*
				 * Creo un pulsante astratto e controllo se è selezionato oppure no.
				*/
				AbstractButton b = (AbstractButton) e.getSource();
				selected = b.getModel().isSelected();
				
				//System.out.println("==> TOGGLE SELECTED = " + selected);
				
				if (selected) { //pulsante selezionato => esegue il thread
					thread = true;
					//System.out.println("if selected = " + selected + " Valore variabile thread " + thread);
					table.setModel(tmr);
					/*
					 * Creo un singolo thread executor  
					*/
					exec = Executors.newSingleThreadScheduledExecutor();
					/*
					 * Metodo che permette di schedulare un compito con delay e lo fa ogni TIME secondi. 
					 * 
					 * gli passo:
					 * 1 => il task da eseguire
					 * 2 => il tempo di delay sulla prima esecuzione
					 * 3 => periodo tra le successive esecuzioni
					 * 4 => l'unit� di tempo dei parametri initialDelay e period
					 * Per ulteriori informazioni vedi https://docs.oracle.com/javase/6/docs/api/java/util/concurrent/ScheduledExecutorService.html
					 */
					exec.scheduleAtFixedRate(tmr, DELAY, TIME, TimeUnit.SECONDS);
				}
				else {//gestione manuale
					//System.out.println("else Selected = " + selected + "Valore variabile thread = " + thread);
					//System.out.println("   Spengo il thread");
					thread = false;
					exec.shutdown();
					thread = false; 
					table.setModel(tm);
					setUpStatoColumn(table, table.getColumnModel().getColumn(4));
				}
			}
			
		});
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel.add(scrollPane, BorderLayout.CENTER);
		
		
		
		scrollPane.setViewportView(table); //assegno allo ScrollPane la tabella 
		/*
		 * Imposto il renderer della tabella al mio rendere personalizzato
		 * @see MyTableCellRenderer
		 */
		table.setDefaultRenderer(Object.class, new MyTableCellRender());
		/*
		 * Imposto il metodo di selezione delle righe della tabella 
		 * con la selezione singola
		 */
		table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		/*
		 * Aggiunge il MouseListener per controllare se c'� stato un triplo click 
		 * con il mouse.
		 * Con triplo click su una riga di una tabella si elimina quella riga e 
		 * di conseguenza si elimina la spedizione dall'archivio
		 */
		table.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				//System.out.println("riga selezionata: " + row);
				if (row != 0) {
					if (e.getClickCount() == 3)
						removeSelectedRows(table, row);
					
					tm.fireTableDataChanged();
				}
			}
		});	
		/*
		 * Imposta la colonna 4 (stato) a con un modello personalizzato.
		 * @see metodo setUpStatoColumn()
		 */
		setUpStatoColumn(table, table.getColumnModel().getColumn(4));
		
		
		panel.add(table.getTableHeader(), BorderLayout.NORTH);
		
		frame.setVisible(isVisible);
	}
	
	/**
	 * Method to set the state selection via the table column. 
	 * @param table The table it refers to
	 * @param statoColumn The columns to be modified
	 */
	static void setUpStatoColumn(JTable table, TableColumn statoColumn) {
		JComboBox comboBox = new JComboBox();
        comboBox.addItem("in-preparazione");
        comboBox.addItem("in-transito");
        comboBox.addItem("fallita");
        comboBox.addItem("ricevuta");
        comboBox.addItem("rimborso-richiesto");
        comboBox.addItem("rimborso-erogato");
        statoColumn.setCellEditor(new DefaultCellEditor(comboBox));
	}
	
	/**
	 * Method to remove selected rows.
	 * @param table The table it refers to
	 * @param deleteRow the row to delete
	 */
	static void removeSelectedRows(JTable table, int deleteRow) {
		/*
		 * Recupero il modello della tabella e elimino la riga passata alla funzione
		 */
		AdminTableModel model = (AdminTableModel) table.getModel();
		model.removeRow(deleteRow);
	}
}
