import java.awt.EventQueue;

import javax.naming.ldap.Rdn;
import javax.swing.JFrame;
import java.awt.GridLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;


class WindowAdicionarUser {

	JFrame frame;
	private JTextField textFieldNome;
	private JTextField textFieldMorada;
	private JTextField textFieldTelefone;
	private JTextField textFieldEmail;
	private JTextField textFieldData;
	private JTextField textFieldUsername;
	private JPasswordField passwordField;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private Biblioteca biblioteca;
	private Utilizador user;
	private JTextField textFieldCategoriaProfissional;
	/**
	 * Create the application.
	 */
	WindowAdicionarUser(Biblioteca biblio, Utilizador user) {
		this.biblioteca = biblio;
		this.user = user;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 522, 399);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(9, 37, 40, 15);
		lblNome.setHorizontalAlignment(SwingConstants.LEFT);
		frame.getContentPane().add(lblNome);
		
		textFieldNome = new JTextField();
		textFieldNome.setBounds(99, 37, 107, 19);
		frame.getContentPane().add(textFieldNome);
		textFieldNome.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Tipo");
		lblNewLabel.setBounds(237, 37, 121, 15);
		frame.getContentPane().add(lblNewLabel);
		
		final JRadioButton rdbtnProfessor = new JRadioButton("Professor");
		buttonGroup.add(rdbtnProfessor);
		rdbtnProfessor.setBounds(372, 33, 114, 23);
		frame.getContentPane().add(rdbtnProfessor);
		
		JLabel lblMorada = new JLabel("Morada");
		lblMorada.setBounds(9, 67, 54, 15);
		lblMorada.setHorizontalAlignment(SwingConstants.LEFT);
		frame.getContentPane().add(lblMorada);
		
		textFieldMorada = new JTextField();
		textFieldMorada.setBounds(99, 67, 107, 19);
		textFieldMorada.setColumns(10);
		frame.getContentPane().add(textFieldMorada);
		
		final JRadioButton rdbtnAluno = new JRadioButton("Aluno");
		buttonGroup.add(rdbtnAluno);
		rdbtnAluno.setBounds(372, 63, 114, 23);
		frame.getContentPane().add(rdbtnAluno);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(9, 98, 62, 15);
		lblTelefone.setHorizontalAlignment(SwingConstants.LEFT);
		frame.getContentPane().add(lblTelefone);
		
		textFieldTelefone = new JTextField();
		textFieldTelefone.setBounds(99, 98, 107, 19);
		textFieldTelefone.setColumns(10);
		frame.getContentPane().add(textFieldTelefone);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(9, 125, 37, 15);
		lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
		frame.getContentPane().add(lblEmail);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(99, 125, 107, 19);
		textFieldEmail.setColumns(10);
		frame.getContentPane().add(textFieldEmail);
		
		JLabel lblDataNascimento = new JLabel("Data Nascimento");
		lblDataNascimento.setBounds(237, 234, 121, 15);
		lblDataNascimento.setHorizontalAlignment(SwingConstants.LEFT);
		frame.getContentPane().add(lblDataNascimento);
		
		textFieldData = new JTextField();
		textFieldData.setBounds(372, 232, 114, 19);
		textFieldData.setColumns(10);
		frame.getContentPane().add(textFieldData);
		
		JLabel lblaaaammdd = new JLabel("(AAAA-MM-DD)");
		lblaaaammdd.setBounds(258, 249, 78, 13);
		lblaaaammdd.setFont(new Font("Dialog", Font.PLAIN, 10));
		lblaaaammdd.setHorizontalAlignment(SwingConstants.LEFT);
		frame.getContentPane().add(lblaaaammdd);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(9, 289, 72, 15);
		frame.getContentPane().add(lblUsername);
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(99, 289, 107, 19);
		textFieldUsername.setColumns(10);
		frame.getContentPane().add(textFieldUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(237, 289, 70, 15);
		frame.getContentPane().add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(372, 287, 114, 19);
		frame.getContentPane().add(passwordField);
		
		JButton btnConcluir = new JButton("Concluir");
		
		btnConcluir.setBounds(122, 339, 107, 25);
		frame.getContentPane().add(btnConcluir);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
			}
		});
		btnCancelar.setBounds(274, 339, 114, 25);
		frame.getContentPane().add(btnCancelar);
		
		final JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(237, 171, 86, 15);
		frame.getContentPane().add(lblCategoria);
		
		textFieldCategoriaProfissional = new JTextField();
		textFieldCategoriaProfissional.setBounds(372, 169, 114, 19);
		frame.getContentPane().add(textFieldCategoriaProfissional);
		textFieldCategoriaProfissional.setColumns(10);
		
		final JLabel lblProfissional = new JLabel("Profissional");
		lblProfissional.setBounds(237, 187, 107, 15);
		frame.getContentPane().add(lblProfissional);
		
		lblCategoria.setVisible(false);
		lblProfissional.setVisible(false);
		textFieldCategoriaProfissional.setVisible(false);
		
		final JRadioButton rdbtnAdministrador = new JRadioButton("Administrador");
		rdbtnAdministrador.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if(rdbtnAdministrador.isSelected())
				{
					lblCategoria.setVisible(true);
					lblProfissional.setVisible(true);
					textFieldCategoriaProfissional.setVisible(true);
				}
				else if(rdbtnAdministrador.isSelected() == false)
				{
					lblCategoria.setVisible(false);
					lblProfissional.setVisible(false);
					textFieldCategoriaProfissional.setVisible(false);
				}
			}
		});
		buttonGroup.add(rdbtnAdministrador);
		rdbtnAdministrador.setBounds(372, 90, 149, 23);
		frame.getContentPane().add(rdbtnAdministrador);
		btnConcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textFieldNome.getText().equals("") || textFieldMorada.getText().equals("") || textFieldTelefone.getText().equals("") || textFieldEmail.getText().equals("") || textFieldUsername.getText().equals("") || passwordField.getText().equals("") || (rdbtnAluno.isSelected()==false && rdbtnProfessor.isSelected()==false && rdbtnAdministrador.isSelected()) || rdbtnAdministrador.isSelected() && textFieldCategoriaProfissional.getText().equals(""))
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
					Date dataNasc; 
					formatter = new SimpleDateFormat("yyyy-MM-dd");
					int tipo = -1;
					if(rdbtnProfessor.isSelected())
					{
						tipo = 0;
						try {
							dataNasc = formatter.parse(textFieldData.getText());
							biblioteca.adicionarUser(tipo, textFieldNome.getText(), textFieldMorada.getText(), Integer.parseInt(textFieldTelefone.getText()), textFieldEmail.getText(), dataNasc, textFieldUsername.getText(), passwordField.getText(), null);
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
					else if(rdbtnAluno.isSelected())
					{
						tipo = 1;
						try {
							dataNasc = formatter.parse(textFieldData.getText());
							biblioteca.adicionarUser(tipo, textFieldNome.getText(), textFieldMorada.getText(), Integer.parseInt(textFieldTelefone.getText()), textFieldEmail.getText(), dataNasc, textFieldUsername.getText(), passwordField.getText(), null);
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
					else if(rdbtnAdministrador.isSelected())
					{
						tipo = 2;
						try {
							dataNasc = formatter.parse(textFieldData.getText());
							biblioteca.adicionarUser(tipo, textFieldNome.getText(), textFieldMorada.getText(), Integer.parseInt(textFieldTelefone.getText()), textFieldEmail.getText(), dataNasc, textFieldUsername.getText(), passwordField.getText(), textFieldCategoriaProfissional.getText());
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

					try {
						biblioteca.save(1);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
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
		if(this.user == null || this.user.getClass().equals(Leitor.class))
		{
			rdbtnAdministrador.setVisible(false);
		}
		else
		{
			rdbtnAdministrador.setVisible(true);
		}
	}
}
