package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import logic.Archivio;
import logic.Serializza;
import logic.table.ArchivioTableModel;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;

import spedizione.*;

/**
 * Class that implements the display and management of shipments of a user. 
 * 
 * @author &#160; &#160; Castorini Francesco
 * @see Spedizione
 * @see ActionListener
 */
public class GraficaSpedizioneUnormale implements ActionListener{
	/**
	 * The frame for this class.
	 */
	private JFrame frame;
	/**
	 * Insert destination.
	 */
	private JTextField destinazione;
	/**
	 * Insert weight.
	 */
	private JTextField peso;
	/**
	 * The table.
	 */
	private JTable table;
	/**
	 * Insert value.
	 */
	private JTextField valoreRichiesto;
	/**
	 * Add shipment to archive.
	 */
	private JButton aggiungi;
	/**
	 * Number of rows in the table.
	 */
	private JLabel totRighe;
	/**
	 * The table's model.
	 */
	private AbstractTableModel tm;
	/**
	 * A checkbox.
	 */
	private JCheckBox checkBoxAssicurato;
	/**
	 * An instance of Serializza's class.
	 */
	private Serializza s = new Serializza();
	/**
	 * The archive of shipment.
	 */
	private Archivio<Spedizione> a = new Archivio<Spedizione>();
	/**
	 * The archive for this user shipment.
	 */
	private Archivio<Spedizione> a2 = new Archivio<Spedizione>();
	
	/**
	 * Constructor that constructs the user's view
	 * @param isVisbile Indicates whether the frame is visible or not
	 * @param username The username of the user who logged in
	 */
	public GraficaSpedizioneUnormale(boolean isVisbile, String username) {
		/*
		 * Deserializzo l'archivio e lo assegno all'archiio a
		 */
		a = s.getSerializable();
		
		/*
		 * Questo pezzo di codice aggiunge all'archivio a2 le spedizioni 
		 * dell'utente che si � loggato con username 
		 */
		String tempUsername = null;
		for (int i=0;i<a.size();i++) {
			/*
			 * Per ogni spedizione recupero l'username, lo confronto con 
			 * l'username dell'utente loggato e se corrisponde aggiungo all'archivio
			 * a2 le spedizioni
			 */
			tempUsername = a.get(i).getUsername();
			
			if (username.equals(tempUsername))
				a2.add(a.get(i));
		}
		
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 450);
		frame.setTitle("Spedizioni di: " + username);
		frame.setLocationRelativeTo(null);
		frame.addWindowListener(new WindowAdapter() {
			/*
			 * Intercetto la chiusura del frame e salvo le spedizioni aggiunte 
			 * dall'utente e le serializzo su file
			 */
			@Override
			public void windowClosing(WindowEvent e) {
				//System.out.println("Salvo e chiudo il frame");
				s.serialize(a);
			}
		});
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		/*
		 * Crea uno SplitPane cio� una divisoria nel frame 
		 */
		JSplitPane splitPane = new JSplitPane();
		frame.getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JPanel pannelloSx = new JPanel();
		/*
		 * Crea la dimensione minima tramite l'oggetto Dimension
		 * @see Dimension 
		 */
		Dimension minimumSize = new Dimension(195, 0);
		/*
		 * Imposta la dimensione minima quando si ridimensiona lo SplitPane
		 */
		pannelloSx.setMinimumSize(minimumSize);
		/*
		 * Aggiunge alla parte sinistra dello SplitPane il pannello creato 
		 * precedentemente 
		 */
		splitPane.setLeftComponent(pannelloSx);
		
		JLabel titoloSpedizione = new JLabel("NUOVA SPEDIZIONE");
		titoloSpedizione.setFont(new Font("Tahoma", Font.PLAIN, 18));
		titoloSpedizione.setHorizontalAlignment(SwingConstants.CENTER);
		
		JCheckBox checkBoxNormale = new JCheckBox("Normale");
		
		checkBoxNormale.setSelected(true);
		
		checkBoxAssicurato = new JCheckBox("Assicurata");
		
		/*
		 * Crea una mutua esclusione tra il button spedizione normale e spedizione
		 * assicurata
		 */
		ButtonGroup bg = new ButtonGroup();
		bg.add(checkBoxNormale);;
		bg.add(checkBoxAssicurato);
		
		JLabel labelDestinazione = new JLabel("Destinazione");
		
		destinazione = new JTextField();
		destinazione.setColumns(10);
		destinazione.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				enableButton();
				
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				enableButton();
				
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				enableButton();
				
			}
			
		});
		
		JLabel labelPeso = new JLabel("Peso");
		
		peso = new JTextField();
		peso.setColumns(10);
		peso.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				enableButton();
				
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				enableButton();
				
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				enableButton();
				
			}
			
		});
		
		valoreRichiesto = new JTextField();
		valoreRichiesto.setColumns(10);
		valoreRichiesto.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				enableButton();
				
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				enableButton();
				
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				enableButton();
				
			}
			
		});
		JButton logout = new JButton("LOGOUT");
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * Quando si schiaccia sul pulsante logout si serializza l'archivio
				 * delle spedizioni su file
				 */
				//System.out.println("Serializzo");
				s.serialize(a);
				/*
				 * Imposta il frame a false, si distrugge e si mostra il frame 
				 * del login
				 */
				frame.setVisible(false);
				frame.dispose();
				/*Login l = */new Login(true);
			}
		});
		logout.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		
		JLabel lblNewLabel_4 = new JLabel("Rimborso: ");
		
		aggiungi = new JButton("aggiungi");
		aggiungi.setEnabled(false);
		aggiungi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * Quando si schiaccia sul pulsante aggiungi si controlla se il peso
				 * inserito sia effettivamente un numero e che sia >= di zero
				 */
				try {
					Float ppeso = Float.parseFloat(peso.getText().replace(",", "."));
					if (ppeso.compareTo(0f) <= 0) {
						JOptionPane.showMessageDialog(null, "Peso inserito non valido", "Errore", JOptionPane.ERROR_MESSAGE);
						return;
					}
						
				}catch(NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Peso inserito non valido", "Errore", JOptionPane.ERROR_MESSAGE);
				}
				
				if (checkBoxAssicurato.isSelected()) {
					try {
						Float valore = Float.parseFloat(valoreRichiesto.getText().replace(",", "."));
						if (valore.compareTo(0f) <= 0) {
							JOptionPane.showMessageDialog(null, "Valore inserito non valido", "Errore", JOptionPane.ERROR_MESSAGE);
							return;
						}
					}catch(NumberFormatException e2) {
						JOptionPane.showMessageDialog(null, "Valore inserito non valido", "Errore", JOptionPane.ERROR_MESSAGE);
					}
				}
				
				
				/*
				 * Si controlla se la spedizione � assicurata oppure no e si provvede
				 * ad aggiungerla all'archivio.
				 * Si aggiorna la tabella di conseguenza con il metodo: fireTableDataChange
				 * Si aggiornano il numero di righe presenti nella tabella 
				 */
				
				/*if (Double.parseDouble(peso.getText()) <= 0) {
					JOptionPane.showMessageDialog(null, "Peso inserito non valido", "Errore", JOptionPane.ERROR_MESSAGE);
					return;
				}*/
				
				Spedizione s = null;
				SpedizioneAssicurata sa = null;
				
				if (checkBoxAssicurato.isSelected() ) {
					
					 sa = new SpedizioneAssicurata(username, destinazione.getText(), peso.getText(), valoreRichiesto.getText());
					// System.out.println(username + " " + destinazione.getText() + " " + peso.getText() + " " + valoreRichiesto.getText());
					 a.add(sa);
					 a2.add(sa);
					 tm.fireTableDataChanged();
					 totRighe.setText("Righe: " + a.size());
				}
				else {
					s = new Spedizione(username, destinazione.getText(), peso.getText());
					//System.out.println(username + " " + destinazione.getText() + " " + peso.getText() );
					a.add(s);
					a2.add(s);
					tm.fireTableDataChanged();
					totRighe.setText("Righe: " + a.size());
				}
				/*
				 * Cancella i dati inseriti nelle JTextField impostandole a stringa vuota
				 */
				destinazione.setText("");
				peso.setText("");
				valoreRichiesto.setText("");
			}
		});
		aggiungi.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		GroupLayout gl_pannelloSx = new GroupLayout(pannelloSx);
		gl_pannelloSx.setHorizontalGroup(
			gl_pannelloSx.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pannelloSx.createSequentialGroup()
					.addGroup(gl_pannelloSx.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pannelloSx.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_pannelloSx.createParallelGroup(Alignment.LEADING)
								.addComponent(titoloSpedizione, GroupLayout.PREFERRED_SIZE, 180, Short.MAX_VALUE)
								.addGroup(gl_pannelloSx.createSequentialGroup()
									.addGroup(gl_pannelloSx.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(labelDestinazione, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(checkBoxNormale, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE))
									.addGroup(gl_pannelloSx.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_pannelloSx.createSequentialGroup()
											.addGap(18)
											.addComponent(checkBoxAssicurato, GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
										.addGroup(gl_pannelloSx.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(gl_pannelloSx.createParallelGroup(Alignment.LEADING)
												.addComponent(peso, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(destinazione, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
								.addComponent(labelPeso, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_pannelloSx.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(valoreRichiesto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pannelloSx.createSequentialGroup()
							.addGap(58)
							.addComponent(logout))
						.addGroup(gl_pannelloSx.createSequentialGroup()
							.addGap(63)
							.addComponent(aggiungi)))
					.addContainerGap())
		);
		gl_pannelloSx.setVerticalGroup(
				gl_pannelloSx.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_pannelloSx.createSequentialGroup()
						.addContainerGap()
						.addComponent(titoloSpedizione)
						.addGap(18)
						.addGroup(gl_pannelloSx.createParallelGroup(Alignment.BASELINE)
							.addComponent(checkBoxNormale)
							.addComponent(checkBoxAssicurato))
						.addGap(18)
						.addGroup(gl_pannelloSx.createParallelGroup(Alignment.BASELINE)
							.addComponent(labelDestinazione)
							.addComponent(destinazione, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_pannelloSx.createParallelGroup(Alignment.BASELINE)
							.addComponent(labelPeso)
							.addComponent(peso, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_pannelloSx.createParallelGroup(Alignment.BASELINE)
							.addComponent(valoreRichiesto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewLabel_4))
						.addGap(46)
						.addComponent(aggiungi)
						.addPreferredGap(ComponentPlacement.RELATED, 121, Short.MAX_VALUE)
						.addComponent(logout)
						.addContainerGap())
			);
		pannelloSx.setLayout(gl_pannelloSx);
		pannelloSx.setLayout(gl_pannelloSx);
		
		JPanel panel_1 = new JPanel();
		splitPane.setRightComponent(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel titoloTabella = new JLabel("RIEPILOGO SPEDIZIONI");
		titoloTabella.setFont(new Font("Tahoma", Font.PLAIN, 18));
		titoloTabella.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(titoloTabella, BorderLayout.NORTH);
		
		/*
		 * Scrive il numero di righe presenti nella tabella 
		 */
		totRighe = new JLabel("Totale righe: " + a.size());
		panel_1.add(totRighe, BorderLayout.SOUTH);
		
		JPanel panel = new JPanel();
		//panel.setBackground(Color.RED);
		panel_1.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		/*
		 * Imposta che ci devono sempre essere le barre di scorrimento laterali
		 */
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel.add(scrollPane, BorderLayout.CENTER);
		
		/*
		 * Assegna al modello della tabella astratto la classe ArchivioTableModel
		 */
		tm = new ArchivioTableModel(a2);
		table = new JTable(tm);
		/*
		 * Assegna allo ScrollPane un viewport e gli assegno la tabella 
		 */
		scrollPane.setViewportView(table);
		
		setUpStatoColumn(table, table.getColumnModel().getColumn(4));
		/*
		 * Imposto l'header delle colonne in un pannello posizionato a nord 
		 * rispetto allo ScrollPane recuperando l'header con il metodo .getTableHeader
		 */
		panel.add(table.getTableHeader(), BorderLayout.NORTH);
		/*
		 * Imposto il frame a essere visibile oppure no in base al valore del 
		 * parametro isVisible
		 */
		frame.setVisible(isVisbile);
	}

	/**
	 * Sets the state selection via the table column.
	 * @param table the table it refers to
	 * @param statoColumn the columns to be modified
	 */
	static void setUpStatoColumn(JTable table, TableColumn statoColumn) {
		JComboBox comboBox = new JComboBox();
        comboBox.addItem("rimborso-richiesto");
        
        statoColumn.setCellEditor(new DefaultCellEditor(comboBox));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Enable or disable the add button.
	 */
	public void enableButton() {
		if (checkBoxAssicurato.isSelected()) {
			if (peso.getText().equals("") || destinazione.getText().equals("") || valoreRichiesto.getText().equals(""))
				aggiungi.setEnabled(false);
			else
				aggiungi.setEnabled(true);
		}
		else {
			if (peso.getText().equals("") || destinazione.getText().equals(""))
				aggiungi.setEnabled(false);
			else
				aggiungi.setEnabled(true);
		}
	}
}