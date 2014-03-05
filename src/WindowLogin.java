

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPasswordField;


class WindowLogin {
	
	JFrame frame;
	private JTextField txtBoxLogin;
	private JPasswordField txtBoxPassword;

	private Biblioteca biblioteca;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	WindowLogin(Biblioteca biblio) {
		this.biblioteca = biblio;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 325, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtBoxLogin = new JTextField();
		txtBoxLogin.setBounds(186, 28, 102, 19);
		frame.getContentPane().add(txtBoxLogin);
		txtBoxLogin.setColumns(10);

		txtBoxPassword = new JPasswordField();
		txtBoxPassword.setBounds(186, 72, 102, 19);
		frame.getContentPane().add(txtBoxPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { //Função de Login!
				if(biblioteca.login(txtBoxLogin.getText(), txtBoxPassword.getText()) == 0)
				{
					try {
						Utilizador user = biblioteca.getUtilizador(txtBoxLogin.getText());
						WindowGeral window = new WindowGeral(biblioteca, user);
						window.frame.setVisible(true);
						frame.setVisible(false);
					} catch (Exception e) {
						e.printStackTrace();
				}
				}	
				else
				{
					try {
						WindowErro window = new WindowErro("Erro login");
						window.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			}
		});
		btnLogin.setBounds(173, 131, 115, 23);
		frame.getContentPane().add(btnLogin);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(31, 31, 79, 15);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(31, 75, 70, 15);
		frame.getContentPane().add(lblPassword);
		
		JButton btnNovoUtilizador = new JButton("Novo Leitor");
		btnNovoUtilizador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					WindowAdicionarUser window = new WindowAdicionarUser(biblioteca, null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnNovoUtilizador.setBounds(31, 133, 115, 21);
		frame.getContentPane().add(btnNovoUtilizador);
		
	}
}
