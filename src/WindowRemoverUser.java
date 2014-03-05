import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;


 class WindowRemoverUser {

	 JFrame frame;
	 private JTextField textFieldNome;
	 private JTextField textFieldMorada;
	 private JTextField textFieldTelefone;
	 private JTextField textFieldEmail;
	 private JTextField textFieldNumeroInterno;
	 private JTextField textFieldDatadeNascimento;
	 private JLabel lblListaDeUtilizadores;
	 private JLabel lblNome;
	 private JButton btnCancelar;
	 
	
	 private Admin admin;
	 private Biblioteca biblioteca;
	/**
	 * Create the application.
	 */
	public WindowRemoverUser(Utilizador u, Biblioteca b) {
		this.admin = (Admin) u;
		this.biblioteca = b;
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
		
		DefaultListModel listAux = new DefaultListModel(); 
		for(Utilizador u: biblioteca.getlistaUtilizadores())
		{
			listAux.addElement(u.username);
		}
		final JButton btnEliminar = new JButton("Eliminar");
		final JList list = new JList(listAux);
		
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				Utilizador u = admin.procurarUser(list.getSelectedValue().toString(),biblioteca.getlistaUtilizadores());
				textFieldNome.setText(u.getNome());
				textFieldEmail.setText(u.getEmail());
				textFieldMorada.setText(u.getMorada());
				textFieldNumeroInterno.setText(u.numeroInterno);
				textFieldTelefone.setText(Integer.toString(u.getTelefone()));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				textFieldDatadeNascimento.setText(sdf.format(u.getDataNascimento()).toString());
				btnEliminar.setEnabled(true);
			}
		});
		list.setBounds(12, 46, 152, 171);
		frame.getContentPane().add(list);
		
		textFieldNome = new JTextField();
		textFieldNome.setEditable(false);
		textFieldNome.setBounds(299, 46, 114, 19);
		frame.getContentPane().add(textFieldNome);
		textFieldNome.setColumns(10);
		
		textFieldMorada = new JTextField();
		textFieldMorada.setEditable(false);
		textFieldMorada.setBounds(299, 77, 114, 19);
		frame.getContentPane().add(textFieldMorada);
		textFieldMorada.setColumns(10);
		
		textFieldTelefone = new JTextField();
		textFieldTelefone.setEditable(false);
		textFieldTelefone.setBounds(299, 135, 114, 19);
		frame.getContentPane().add(textFieldTelefone);
		textFieldTelefone.setColumns(10);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setEditable(false);
		textFieldEmail.setBounds(299, 108, 114, 19);
		frame.getContentPane().add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		textFieldNumeroInterno = new JTextField();
		textFieldNumeroInterno.setEditable(false);
		textFieldNumeroInterno.setBounds(299, 198, 114, 19);
		frame.getContentPane().add(textFieldNumeroInterno);
		textFieldNumeroInterno.setColumns(10);
		
		textFieldDatadeNascimento = new JTextField();
		textFieldDatadeNascimento.setEditable(false);
		textFieldDatadeNascimento.setBounds(299, 166, 114, 19);
		frame.getContentPane().add(textFieldDatadeNascimento);
		textFieldDatadeNascimento.setColumns(10);
		
		JLabel lblMorada = new JLabel("Morada");
		lblMorada.setBounds(182, 79, 70, 15);
		frame.getContentPane().add(lblMorada);
		
		lblListaDeUtilizadores = new JLabel("Lista de Utilizadores");
		lblListaDeUtilizadores.setBounds(12, 19, 201, 15);
		frame.getContentPane().add(lblListaDeUtilizadores);
		
		lblNome = new JLabel("Nome");
		lblNome.setBounds(182, 48, 70, 15);
		frame.getContentPane().add(lblNome);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(182, 110, 70, 15);
		frame.getContentPane().add(lblEmail);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(182, 137, 63, 15);
		frame.getContentPane().add(lblTelefone);
		
		JLabel lblDataDeNascimento = new JLabel("Data de Nascimento");
		lblDataDeNascimento.setBounds(182, 168, 128, 15);
		frame.getContentPane().add(lblDataDeNascimento);
		
		JLabel lblNumeroInterno = new JLabel("Numero Interno");
		lblNumeroInterno.setBounds(182, 200, 128, 15);
		frame.getContentPane().add(lblNumeroInterno);
		
		btnEliminar.setEnabled(false);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg00) {
				admin.removerUser(list.getSelectedValue().toString(),biblioteca.getlistaUtilizadores());
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
		});
		btnEliminar.setBounds(176, 232, 117, 25);
		frame.getContentPane().add(btnEliminar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				frame.dispose();
			}
		});
		btnCancelar.setBounds(296, 232, 117, 25);
		frame.getContentPane().add(btnCancelar);
	}
}
