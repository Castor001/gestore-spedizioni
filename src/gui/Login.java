package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import user.Admin;
import user.UtenteNormale;

/**
 * Class that implements application login.
 * 
 * @author &#160; &#160; Castorini Francesco
 *
 */
public class Login extends JFrame implements ActionListener{
	
	/**
	 * Serial Default Version UID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Insert username.
	 */
	private JTextField insertUsername;
	/**
	 * Insert password.
	 */
	private JPasswordField insertPassword;
	/**
	 * The frame of this class.
	 */
	private JFrame frame;
	/**
	 * Admin's button.
	 */
	private JRadioButton adminButton;
	/**
	 * User's button.
	 */
	private JRadioButton utenteButton;
	/**
	 * Login button.
	 */
	private JButton login;
	/**
	 * Button for new users.
	 */
	private JButton registrati;
	
	/**
	 * Constructor that creates the graphics for a user login.
	 * @param isVisible indicates whether the frame is visible or not
	 */
	public Login(boolean isVisible) {
		frame = new JFrame("Gestione Spedizioni");
		frame.setBounds(100, 100, 800, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		/*
		 * Creo un nuovo oggetto di tipo SpringLayout
		 * <a href = "https://docs.oracle.com/javase/tutorial/uiswing/layout/spring.html"> SpringLayout</a>
		 */
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JLabel labelUsername = new JLabel("Username");
		springLayout.putConstraint(SpringLayout.NORTH, labelUsername, 87, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, labelUsername, 311, SpringLayout.WEST, frame.getContentPane());
		labelUsername.setHorizontalAlignment(SwingConstants.CENTER);
		labelUsername.setFont(new Font("Calibri", Font.PLAIN, 14));
		frame.getContentPane().add(labelUsername);
		
		JLabel labelPassword = new JLabel("Password");
		springLayout.putConstraint(SpringLayout.NORTH, labelPassword, 25, SpringLayout.SOUTH, labelUsername);
		springLayout.putConstraint(SpringLayout.WEST, labelPassword, 0, SpringLayout.WEST, labelUsername);
		labelPassword.setHorizontalAlignment(SwingConstants.CENTER);
		labelPassword.setFont(new Font("Calibri", Font.PLAIN, 14));
		frame.getContentPane().add(labelPassword);
		
		insertUsername = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, insertUsername, -2, SpringLayout.NORTH, labelUsername);
		springLayout.putConstraint(SpringLayout.WEST, insertUsername, 6, SpringLayout.EAST, labelUsername);
		frame.getContentPane().add(insertUsername);
		insertUsername.setColumns(10);
		
		insertPassword = new JPasswordField();
		insertPassword.setHorizontalAlignment(SwingConstants.LEFT);
		insertPassword.setEchoChar('*');
		insertPassword.setColumns(10);
		springLayout.putConstraint(SpringLayout.WEST, insertPassword, 0, SpringLayout.WEST, insertUsername);
		springLayout.putConstraint(SpringLayout.SOUTH, insertPassword, 0, SpringLayout.SOUTH, labelPassword);
		frame.getContentPane().add(insertPassword);
		
		adminButton = new JRadioButton("Admin");
		adminButton.setFont(new Font("Calibri", Font.PLAIN, 14));
		springLayout.putConstraint(SpringLayout.NORTH, adminButton, 23, SpringLayout.SOUTH, labelPassword);
		springLayout.putConstraint(SpringLayout.WEST, adminButton, 0, SpringLayout.WEST, labelUsername);
		adminButton.addActionListener(this);
		frame.getContentPane().add(adminButton);
		
		login = new JButton("Login");
		springLayout.putConstraint(SpringLayout.NORTH, login, 24, SpringLayout.SOUTH, adminButton);
		springLayout.putConstraint(SpringLayout.WEST, login, 348, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(login);
		login.setEnabled(false);
		login.addActionListener(new ActionListener() {
			
			/*
			 * Quando si preme il pulsante login si verifica quale radio button
			 * � selezionato tra admin radio button e user radio button
			 * 
			 * Si verificano le credenziali di accesso andando a controllare con il 
			 * metodo Login presente nella classe Admin oppure nella classe Utente
			 * 
			 * Se il login � errato viene mostrato un messaggio di errore con 
			 * JOptionPane 
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String usernameInput = insertUsername.getText(); //prendo in input lo username
				String passwordInput = String.valueOf(insertPassword.getPassword()); //prendo in input la password 
				
				if (adminButton.isSelected()) {
					Admin a = new Admin();
					if(!a.login(usernameInput, passwordInput)) {
						JOptionPane.showMessageDialog(null, "Username o Password invalidi", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else {
						//System.out.println("Login corretto Admin");
						frame.setVisible(false);
						/*GraficaSpedizioneAdmin gsa = */new GraficaSpedizioneAdmin(true);
					}
						//System.out.println("Login corretto Admin");
				}
				if (utenteButton.isSelected()) {
					UtenteNormale un = new UtenteNormale();
					if(!un.login(usernameInput, passwordInput)) {
						JOptionPane.showMessageDialog(null, "Username o Password invalidi", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else {
						frame.setVisible(false);
						/*GraficaSpedizioneUnormale gsn = */new GraficaSpedizioneUnormale(true, usernameInput);
						//System.out.println("Login corretto utente normale");
					}
				}
			}
		});
		
		utenteButton = new JRadioButton("Utente");
		springLayout.putConstraint(SpringLayout.NORTH, utenteButton, 0, SpringLayout.NORTH, adminButton);
		springLayout.putConstraint(SpringLayout.EAST, utenteButton, 0, SpringLayout.EAST, insertUsername);
		utenteButton.setFont(new Font("Calibri", Font.PLAIN, 14));
		utenteButton.addActionListener(this);
		frame.getContentPane().add(utenteButton);
		/*
		 * Creo una mutua esclusione tra il radio button dell'admin e quelllo dell'
		 * utente normale 
		 */
		ButtonGroup bg = new ButtonGroup();
		bg.add(adminButton);
		bg.add(utenteButton);
		
		JLabel labelTesto = new JLabel("Non hai un account ?");
		springLayout.putConstraint(SpringLayout.NORTH, labelTesto, 267, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, labelTesto, 327, SpringLayout.WEST, frame.getContentPane());
		labelTesto.setHorizontalAlignment(SwingConstants.CENTER);
		labelTesto.setFont(new Font("Calibri", Font.PLAIN, 14));
		frame.getContentPane().add(labelTesto);
		
		registrati = new JButton("Registrati");
		springLayout.putConstraint(SpringLayout.EAST, login, 0, SpringLayout.EAST, registrati);
		springLayout.putConstraint(SpringLayout.EAST, registrati, -363, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, registrati, 26, SpringLayout.SOUTH, labelTesto);
		registrati.addActionListener(new ActionListener() {
		
			/*
			 * Se si preme sul pulsante registrati non mostro il frame attuale e 
			 * mostro il frame per la registrazione di un utente normale
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				
				frame.setVisible(false);
				/*Registrazione r =*/ new Registrazione(true);
				
			}
		});
		frame.getContentPane().add(registrati);
		/*
		 * Imposta il frame a essere visibile oppure no in base alla variabile 
		 * isVisible
		 */
		frame.setVisible(isVisible);
	}
	
	/**
	 * This method checks whether the user's radio button or the radio button
	 * admin is selected and consequently enables the login button and
	 * the other way around
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (utenteButton.isSelected() || adminButton.isSelected())
			login.setEnabled(true);
		else
			login.setEnabled(false);
	}
}
