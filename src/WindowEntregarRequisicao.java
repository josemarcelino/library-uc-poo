import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;


 class WindowEntregarRequisicao {

	 JFrame frame;
	 private JTextField textFieldEditora;
	 private JTextField textFieldTitulo;
	 private JTextField textFieldAno;
	 private JTextField txtDatalimite;
	 private Biblioteca biblioteca;
	 private Utilizador user;
	 private JTextField textFieldItem;
	 private Requisicao r;

	/**
	 * Create the application.
	 */
	 WindowEntregarRequisicao(Biblioteca b, Utilizador u) {
		biblioteca = b;
		user = u;
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
		
		JLabel lblListaDeRequisies = new JLabel("Lista de Requisições");
		lblListaDeRequisies.setBounds(23, 12, 158, 15);
		frame.getContentPane().add(lblListaDeRequisies);
		
		final JButton btnEntregar = new JButton("Entregar");
		
		btnEntregar.setEnabled(false);
		btnEntregar.setBounds(206, 240, 103, 25);
		frame.getContentPane().add(btnEntregar);
		
		JButton btnRetroceder = new JButton("Retroceder");
		btnRetroceder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
			}
		});
		btnRetroceder.setBounds(321, 240, 114, 25);
		frame.getContentPane().add(btnRetroceder);
		
		textFieldEditora = new JTextField();
		textFieldEditora.setEditable(false);
		textFieldEditora.setBounds(321, 141, 114, 19);
		frame.getContentPane().add(textFieldEditora);
		textFieldEditora.setColumns(10);
		
		
		DefaultListModel listReqs = new DefaultListModel(); 
		for(Requisicao r : biblioteca.getlistaRequisicoes())
		{
			if(r.getDataEntrega() == null && r.getUser().getUsername().equals(user.username))
				listReqs.addElement(r.getItemRequisitado().getTitulo());
		}
		final JList list = new JList(listReqs);
		
		list.setBounds(23, 40, 185, 184);
		frame.getContentPane().add(list);
		
		textFieldTitulo = new JTextField();
		textFieldTitulo.setEditable(false);
		textFieldTitulo.setColumns(10);
		textFieldTitulo.setBounds(321, 78, 114, 19);
		frame.getContentPane().add(textFieldTitulo);
		
		textFieldAno = new JTextField();
		textFieldAno.setEditable(false);
		textFieldAno.setColumns(10);
		textFieldAno.setBounds(321, 109, 114, 19);
		frame.getContentPane().add(textFieldAno);
		
		JLabel lblTitulo = new JLabel("Titulo");
		lblTitulo.setBounds(220, 80, 70, 15);
		frame.getContentPane().add(lblTitulo);
		
		JLabel lblAno = new JLabel("Ano");
		lblAno.setBounds(220, 111, 70, 15);
		frame.getContentPane().add(lblAno);
		
		JLabel lblEditora = new JLabel("Editora");
		lblEditora.setBounds(220, 143, 70, 15);
		frame.getContentPane().add(lblEditora);
		
		txtDatalimite = new JTextField();
		txtDatalimite.setEditable(false);
		txtDatalimite.setHorizontalAlignment(SwingConstants.LEFT);
		txtDatalimite.setBounds(321, 172, 114, 19);
		frame.getContentPane().add(txtDatalimite);
		txtDatalimite.setColumns(10);
		
		JLabel lblDataLimite = new JLabel("Data Limite");
		lblDataLimite.setBounds(220, 174, 103, 15);
		frame.getContentPane().add(lblDataLimite);
		
		textFieldItem = new JTextField();
		textFieldItem.setEditable(false);
		textFieldItem.setColumns(10);
		textFieldItem.setBounds(321, 47, 114, 19);
		frame.getContentPane().add(textFieldItem);
		
		JLabel lblItem = new JLabel("Item");
		lblItem.setBounds(220, 49, 70, 15);
		frame.getContentPane().add(lblItem);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				btnEntregar.setEnabled(true);
				r = user.pesquisarRequisicao(list.getSelectedValue().toString(), biblioteca.getlistaRequisicoes());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				txtDatalimite.setText(sdf.format(r.getDataLimite()).toString());
				textFieldAno.setText(Integer.toString(r.getItemRequisitado().getAnoPublicacao()));
				textFieldEditora.setText(r.getItemRequisitado().getEditora());
				textFieldTitulo.setText(r.getItemRequisitado().getTitulo());
				if(r.getItemRequisitado().getClass().equals(DVD.class))
				{
					textFieldItem.setText("DVD");
				}
				else if(r.getItemRequisitado().getClass().equals(Livro.class))
				{
					textFieldItem.setText("Livro");
				}
			}
		});
		btnEntregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				user.entregarItem(r.getItemRequisitado(), biblioteca.getlistaRequisicoes());
				try {
					biblioteca.save(5);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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
	}
}
