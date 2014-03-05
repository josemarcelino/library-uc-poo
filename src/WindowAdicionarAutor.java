import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


class WindowAdicionarAutor {

	JFrame frame;
	private JTextField textFieldNome;
	private JTextField textFieldEmail;
	private JTextField textFieldDataNascimento;
	private Biblioteca biblioteca;
	private Admin admin;
	/**
	 * Create the application.
	 */
	WindowAdicionarAutor(Biblioteca biblio, Utilizador u) {
		biblioteca = biblio;
		admin = (Admin) u;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnConcluir = new JButton("Concluir");
		btnConcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textFieldDataNascimento.getText().equals("") || textFieldEmail.getText().equals("") || textFieldNome.getText().equals(""))
				{
					try {
						WindowErro window = new WindowErro("Por favor preencha todos os campos");
						window.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				else
				{
					DateFormat formatter; 
					Date date; 
					formatter = new SimpleDateFormat("yyyy-MM-dd");
					try {
						date = formatter.parse(textFieldDataNascimento.getText());
						admin.adicionarAutor(textFieldNome.getText(), date, textFieldEmail.getText(), biblioteca.getlistaAutores());
						biblioteca.save(2);
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					frame.setVisible(false);
					frame.dispose();
					try {
						WindowSucesso window = new WindowSucesso();
						window.frame.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					frame.setVisible(false);
					frame.dispose();
				}
			}
		});
		btnConcluir.setBounds(65, 224, 117, 25);
		frame.getContentPane().add(btnConcluir);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
			}
		});
		btnCancelar.setBounds(251, 224, 117, 25);
		frame.getContentPane().add(btnCancelar);
		
		textFieldNome = new JTextField();
		textFieldNome.setBounds(254, 46, 114, 19);
		frame.getContentPane().add(textFieldNome);
		textFieldNome.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(65, 49, 70, 15);
		frame.getContentPane().add(lblNome);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(65, 95, 70, 15);
		frame.getContentPane().add(lblEmail);
		
		JLabel lblDataNascimento = new JLabel("Data Nascimento");
		lblDataNascimento.setBounds(65, 135, 130, 15);
		frame.getContentPane().add(lblDataNascimento);
		
		JLabel lblAaaammdd = new JLabel("AAAA-MM-DD");
		lblAaaammdd.setFont(new Font("Dialog", Font.PLAIN, 8));
		lblAaaammdd.setBounds(94, 152, 70, 15);
		frame.getContentPane().add(lblAaaammdd);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(254, 92, 114, 19);
		frame.getContentPane().add(textFieldEmail);
		
		textFieldDataNascimento = new JTextField();
		textFieldDataNascimento.setColumns(10);
		textFieldDataNascimento.setBounds(254, 132, 114, 19);
		frame.getContentPane().add(textFieldDataNascimento);
	}

}
