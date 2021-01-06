package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.WindowAdapter;

import user.UtenteNormale;
/**
 * Class that implements graphics for a new user's registration.
 * 
 * @author &#160; &#160; Castorini Francesco
 *
 */
public class Registrazione extends JFrame implements ActionListener {

	/**
	 * Default serial version UID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Insert username from user.
	 */
	private JTextField insertUsername;
	/**
	 * Insert password from user.
	 */
	private JPasswordField insertPassword;
	/**
	 * Insert password from user.
	 */
	private JTextField insertEmail;
	/**
	 * Button for register new user.
	 */
	private JButton registrati;
	/**
	 * The frame for this class.
	 */
	private JFrame frame;
	

	/**
	 * Public constructor to call the registration form.
	 * 
	 * @param isVisible indicates whether the frame is visible or not
	 */
	public Registrazione(boolean isVisible) {
		//this.isVisible = isVisible;
		frame = new JFrame("Registrazione");
		frame.setBounds(100, 100, 800, 450);
		//frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //elimina l'oggetto frame, ma mantiene l'applicazione in esecuzione.
		frame.addWindowListener(new WindowAdapter() {
			/**
			 * Method that intercepts the closing of the frame.
			 * <p>
			 * When a frame's close method is intercepted, it releases the resources allocated by
			 * this frame and shows the login frame
			 */
			
			  public void windowClosing(WindowEvent e) {
				  //System.out.println("Chiudo il frame"); 
				  frame.dispose();
				  /*Login l =*/ new Login(true);
			  }
		});
		/*
		 * Serve per centrare il frame nello schermo
		 */
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JLabel titolo = new JLabel("REGISTRAZIONE");
		titolo.setFont(new Font("Dialog", Font.BOLD, 18));
		titolo.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(titolo, BorderLayout.NORTH);

		JPanel panel = new JPanel();

		frame.getContentPane().add(panel, BorderLayout.CENTER);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);

		JLabel usernameLabel = new JLabel("Username");
		sl_panel.putConstraint(SpringLayout.NORTH, usernameLabel, 89, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, usernameLabel, 228, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, usernameLabel, 315, SpringLayout.WEST, panel);
		usernameLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		usernameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(usernameLabel);

		insertUsername = new JTextField();
		sl_panel.putConstraint(SpringLayout.NORTH, insertUsername, 89, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, insertUsername, 21, SpringLayout.EAST, usernameLabel);
		sl_panel.putConstraint(SpringLayout.EAST, insertUsername, 139, SpringLayout.EAST, usernameLabel);
		panel.add(insertUsername);
		/*
		 * Imposto la larghezza del JTextField, tramite il numero di colonne
		 */
		insertUsername.setColumns(10);
		/*
		 * Recupero il modello del documento con getDocument() e aggiungo un 
		 * ascoltatore di eventi che permette di abilitare il pulsante di 
		 * registrazione
		 */
		insertUsername.getDocument().addDocumentListener(new DocumentListener() {
			/*
			 * Metodo di default
			 */
			@Override
			public void insertUpdate(DocumentEvent e) {
				enableButton();
			}
			
			/*
			 * Metodo di default
			 */
			@Override
			public void removeUpdate(DocumentEvent e) {
				enableButton();
			}
			
			/*
			 * Metodo di default
			 */
			@Override
			public void changedUpdate(DocumentEvent e) {
				enableButton();
			}
		});		

		registrati = new JButton("REGISTRATI");
		registrati.setFont(new Font("Dialog", Font.BOLD, 16));
		sl_panel.putConstraint(SpringLayout.WEST, registrati, 290, SpringLayout.WEST, panel);
		/*
		 * Imposta di default il pulsante a non essere abilitato
		 */
		registrati.setEnabled(false);
		registrati.addActionListener(this);
		panel.add(registrati);

		JLabel emailLabel = new JLabel("Indirizzo");
		sl_panel.putConstraint(SpringLayout.NORTH, emailLabel, 33, SpringLayout.SOUTH, usernameLabel);
		sl_panel.putConstraint(SpringLayout.WEST, emailLabel, 0, SpringLayout.WEST, usernameLabel);
		emailLabel.setHorizontalAlignment(SwingConstants.LEFT);
		emailLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		panel.add(emailLabel);

		JLabel passwordLabel = new JLabel("Password");
		sl_panel.putConstraint(SpringLayout.NORTH, passwordLabel, 22, SpringLayout.SOUTH, emailLabel);
		sl_panel.putConstraint(SpringLayout.NORTH, registrati, 24, SpringLayout.SOUTH, passwordLabel);
		sl_panel.putConstraint(SpringLayout.WEST, passwordLabel, 0, SpringLayout.WEST, usernameLabel);
		sl_panel.putConstraint(SpringLayout.EAST, passwordLabel, 0, SpringLayout.EAST, usernameLabel);
		passwordLabel.setHorizontalAlignment(SwingConstants.LEFT);
		passwordLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		panel.add(passwordLabel);

		insertEmail = new JTextField();
		sl_panel.putConstraint(SpringLayout.WEST, insertEmail, 0, SpringLayout.WEST, insertUsername);
		sl_panel.putConstraint(SpringLayout.SOUTH, insertEmail, 0, SpringLayout.SOUTH, emailLabel);
		sl_panel.putConstraint(SpringLayout.EAST, insertEmail, 0, SpringLayout.EAST, insertUsername);
		insertEmail.setColumns(10);
		
		/*
		 * Uguale a quello di sopra
		 */
		insertEmail.getDocument().addDocumentListener(new DocumentListener() {

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
		
		panel.add(insertEmail);

		insertPassword = new JPasswordField();
		sl_panel.putConstraint(SpringLayout.NORTH, insertPassword, 2, SpringLayout.NORTH, passwordLabel);
		sl_panel.putConstraint(SpringLayout.WEST, insertPassword, 0, SpringLayout.WEST, insertUsername);
		sl_panel.putConstraint(SpringLayout.EAST, insertPassword, 0, SpringLayout.EAST, insertUsername);
		insertPassword.setEchoChar('*');
		insertPassword.setColumns(10);
		insertPassword.getDocument().addDocumentListener(new DocumentListener() {
			/*
			 * Uguale a quello di sopra
			 */
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
		
		panel.add(insertPassword);
		
		/*
		 * Imposta il frame a essere visibile oppure no in base al valore del paramtro
		 * isVisible
		 */
		frame.setVisible(isVisible);
	}
	
	/**
	 * Method to validate the new registration or not.
	 * If registration is successful you will be redirected to the login page e
	 * this frame is destroyed.
	 * 
	 * If the registration has failed an error message is shown.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		String username = insertUsername.getText();
		String password = String.valueOf(insertPassword.getPassword());
		String email = insertEmail.getText();

		UtenteNormale u = new UtenteNormale();
		if (!u.salvaUtente(username, password, email)) {
			JOptionPane.showMessageDialog(null, "Un utente esiste gi� con questo username", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else {
			
			frame.setVisible(false);
			frame.dispose();
			// mostro la pagina di login indicandogli che isVisible sia true
			/*Login l =*/ new Login(true);
		}

	}
	/**
	 * Service function to enable or disable the display of the registered button. 
	 */
	public void enableButton() {
		if (insertUsername.getText().equals("") || insertEmail.getText().equals("") || String.valueOf(insertPassword.getPassword()).equals(""))
			registrati.setEnabled(false);
		else 
			registrati.setEnabled(true);
	}
}
