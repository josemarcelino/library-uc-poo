import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;


public class WindowGerirAutores {

	JFrame frame;
	private JTextField textFieldEmail;
	private JTextField textFieldDataNascimento;
	private JTextField textFieldNome;
	private Biblioteca biblioteca;
	private Admin admin;
	private boolean toogleEditar = true;
	private boolean toogleSair = true;

	/**
	 * Create the application.
	 */
	public WindowGerirAutores(Biblioteca biblio, Utilizador u) {
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
		frame.setBounds(100, 100, 550, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		final JButton btnRemover = new JButton("Remover");
		final JButton btnEditar = new JButton("Editar");
		DefaultListModel listAux = new DefaultListModel(); 
		for(Autor a: biblioteca.getlistaAutores())
		{
			listAux.addElement(a.getNome());
			
		}
		final JList list = new JList(listAux);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				Autor a = admin.procurarAutor(list.getSelectedValue().toString(),biblioteca.getlistaAutores());
				textFieldNome.setText(a.getNome());
				textFieldEmail.setText(a.getEmail());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				textFieldDataNascimento.setText(sdf.format(a.getDataNascimento()).toString());
				btnRemover.setEnabled(true);
				btnEditar.setEnabled(true);
			}
		});
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds(12, 12, 172, 353);
		frame.getContentPane().add(list);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(207, 71, 70, 15);
		frame.getContentPane().add(lblNome);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(207, 102, 114, 15);
		frame.getContentPane().add(lblEmail);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setEditable(false);
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(374, 100, 162, 19);
		frame.getContentPane().add(textFieldEmail);
		
		JLabel lblDataNascimento = new JLabel("Data Nascimento");
		lblDataNascimento.setBounds(207, 133, 128, 15);
		frame.getContentPane().add(lblDataNascimento);
		
		textFieldDataNascimento = new JTextField();
		textFieldDataNascimento.setEditable(false);
		textFieldDataNascimento.setColumns(10);
		textFieldDataNascimento.setBounds(374, 131, 162, 19);
		frame.getContentPane().add(textFieldDataNascimento);
		
		textFieldNome = new JTextField();
		textFieldNome.setEditable(false);
		textFieldNome.setColumns(10);
		textFieldNome.setBounds(374, 69, 162, 19);
		frame.getContentPane().add(textFieldNome);
		
		JButton button = new JButton("Novo");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					WindowAdicionarAutor window = new WindowAdicionarAutor(biblioteca, admin);
					window.frame.setVisible(true);
					frame.setVisible(false);
					frame.dispose();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		button.setBounds(419, 12, 117, 25);
		frame.getContentPane().add(button);
		
		final JButton btnSair = new JButton("Sair");
		btnRemover.setEnabled(false);
		btnEditar.setEnabled(false);
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(toogleSair)
				{
					frame.setVisible(false);
					frame.dispose();
				}
				else
				{
					btnEditar.setText("Editar");
					btnSair.setText("Sair");
					btnRemover.setEnabled(true);
					toogleEditar = true;
					toogleSair = true;
					
					textFieldEmail.setEditable(false);
					textFieldDataNascimento.setEditable(false);
					textFieldNome.setEditable(false);
				}
			}
		});
		btnSair.setBounds(435, 340, 101, 25);
		frame.getContentPane().add(btnSair);
		
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
			
				
				admin.removerAutor(list.getSelectedValue().toString(),biblioteca.getlistaAutores());
				frame.setVisible(false);
				frame.dispose();
				
				try {
					
					biblioteca.save(2);
					WindowSucesso window = new WindowSucesso();
					window.frame.setVisible(true);
				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnRemover.setBounds(322, 340, 101, 25);
		frame.getContentPane().add(btnRemover);
		
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				if(toogleEditar)
				{					
					btnEditar.setText("Guardar");
					btnSair.setText("Cancelar");
					btnRemover.setEnabled(false);
					toogleEditar = false;
					toogleSair = false;
					textFieldEmail.setEditable(true);
					textFieldDataNascimento.setEditable(true);
					textFieldNome.setEditable(true);
					
				}
				else
				{					
					btnEditar.setText("Editar");
					btnSair.setText("Sair");
					btnRemover.setEnabled(true);
					toogleEditar = true;
					toogleSair = true;

					textFieldEmail.setEditable(false);
					textFieldDataNascimento.setEditable(false);
					textFieldNome.setEditable(false);
					
					DateFormat formatter; 
					Date date; 
					formatter = new SimpleDateFormat("yyyy-MM-dd");
					String nome_autor = list.getSelectedValue().toString(); 
					
					try {
						date = formatter.parse(textFieldDataNascimento.getText());
						
						admin.adicionarAutor(textFieldNome.getText(), date, textFieldEmail.getText(), biblioteca.getlistaAutores());
						admin.removerAutor(nome_autor, biblioteca.getlistaAutores());
						
						biblioteca.save(2);
						
						frame.setVisible(false);
						frame.dispose();
						
						WindowSucesso window = new WindowSucesso();
						window.frame.setVisible(true);
						
						
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (Exception e1) {
						e1.printStackTrace();
					}	
				}
			}
		});
		btnEditar.setBounds(207, 340, 101, 25);
		frame.getContentPane().add(btnEditar);
	}
}
